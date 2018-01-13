package sample.pool;

import com.sun.javafx.geom.Area;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import sample.statistic.SimpleStatisticsTools;

import java.util.ArrayList;

public class LocationSolver {

    private MotherRobot motherRobot;
    private ArrayList<Robot> robotsList;
    private ArrayList<Satelite> sateliteList;

    public LocationSolver(MotherRobot motherRobot, ArrayList<Robot> robotsList, ArrayList<Satelite> sateliteList) {
        this.motherRobot = motherRobot;
        this.robotsList = robotsList;
        this.sateliteList = sateliteList;
    }

    public void printMother(){
        System.out.println("========== MOTHER =================");
        System.out.println("Mother 1#: " + this.motherRobot.getSignals()[0]);
        System.out.println("Mother 2#: " + this.motherRobot.getSignals()[1]);
        System.out.println("Mother 3#: " + this.motherRobot.getSignals()[2]);
        System.out.println("===================================");

    }

    public void printRobots(ArrayList<Robot> robots){
        for(Robot robot : robots) {
            System.out.println("Sygnał 1: " + robot.getSignals()[0]);
            System.out.println("Sygnał 2: " + robot.getSignals()[1]);
            System.out.println("Sygnał 3: " + robot.getSignals()[2]);
            System.out.println("===================================");

        }
    }


    private ArrayList<Robot> findIntersection(int satelite1, int satelite2, double averageFirst,double averageSecond, double delta) {
        ArrayList<Robot> robots = new ArrayList<>();
        for(Robot robot:this.robotsList){
            if((robot.getSignals()[satelite1] < averageFirst + delta && robot.getSignals()[satelite1] > averageFirst - delta) &&(robot.getSignals()[satelite2] < averageSecond + delta && robot.getSignals()[satelite2] > averageSecond - delta) ){
                robots.add(robot);
            }
        }
            return robots;
    }

    /**
     * @param first,second satelite determinate a line and
     * @param third solve location of mother robot in regards to the line*/
    public boolean solveLineLocation(int first, int second, int third, double delta){
        ArrayList<Robot> firstArea = this.getRobotsWithMotherSignal(first, delta);
        ArrayList<Robot> secondArea = this.getRobotsWithMotherSignal(second, delta);

        double[] firstSet = new double[firstArea.size()];
        double[] secondSet = new double[secondArea.size()];

        int i = 0;
        for(Robot robot:firstArea){
            firstSet[i] = robot.getSignals()[first];
            i++;
        }
        SimpleStatisticsTools tools = new SimpleStatisticsTools(firstSet);
        double averageFirst = tools.averageValue();

        int j = 0;
        for(Robot robot:secondArea){
            secondSet[j] = robot.getSignals()[second];
            j++;
        }
        tools.setDataSet(secondSet);
        double averageSecond = tools.averageValue();
        ArrayList<Robot> intersection = findIntersection(first,second,averageFirst,averageSecond,delta);




        double minSignal = Double.POSITIVE_INFINITY;
        double maxSignal= Double.NEGATIVE_INFINITY;
        for(Robot robot:intersection){
            if (robot.getSignals()[third] > maxSignal) {
                maxSignal = robot.getSignals()[third];
            }
            if (robot.getSignals()[third] < minSignal) {
                minSignal = robot.getSignals()[third];
            }
        }


        if(Math.abs(maxSignal - this.motherRobot.getSignals()[third]) > Math.abs(this.motherRobot.getSignals()[third] - minSignal)){
            return false;
        }
        else{
            return true;
        }

    }

    public void solveLocation(){
        if(solveLineLocation(0,1,2,1) && solveLineLocation(1,2,0,1) && solveLineLocation(2,0,1,1))
            this.motherRobot.getShape().setFill(Color.GREEN);
        else
            this.motherRobot.getShape().setFill(Color.RED);
    }



    public ArrayList<Robot> getRobotsWithMotherSignal(int satelite, double delta) {
        ArrayList<Robot> robots = new ArrayList<>();
        for(Robot robot : this.robotsList) {
            if ((robot.getSignals()[satelite] <= (this.motherRobot.getSignals()[satelite] + delta)) && (robot.getSignals()[satelite] >= (this.motherRobot.getSignals()[satelite] - delta))) {
                robots.add(robot);
            }
        }
        return robots;
    }

    /**
     * Methods which display robots with same signal as mother
     * of given satelite
     * @param satelite specify index of satelite
     * @param delta specify boundry of searching
     * */
    public void displayPosibilites(int satelite, double delta, Paint fill, Pane root){
        double minSignal = Double.POSITIVE_INFINITY;
        double maxSignal= Double.NEGATIVE_INFINITY;
        double sateliteX = this.sateliteList.get(satelite).getXPosition();
        double sateliteY = this.sateliteList.get(satelite).getYPosition();
        Robot robotMax = null;
        Robot robotMin = null;
        for(Robot robot : this.robotsList) {
            if ((robot.getSignals()[satelite] <= (this.motherRobot.getSignals()[satelite] + delta)) && (robot.getSignals()[satelite] >= (this.motherRobot.getSignals()[satelite] - delta))) {
                if (robot.getSignals()[satelite] > maxSignal) {
                    maxSignal = robot.getSignals()[satelite];
                    robotMax = robot;
                }
                if (robot.getSignals()[satelite] < minSignal) {
                    minSignal = robot.getSignals()[satelite];
                    robotMin = robot;
                }
            }
        }


            double radiusMax = Math.sqrt(Math.pow(robotMax.getXPosition() - sateliteX,2)+Math.pow(robotMax.getYPosition() - sateliteY,2));
            double radiusMin = Math.sqrt(Math.pow(robotMin.getXPosition() - sateliteX,2)+Math.pow(robotMin.getYPosition() - sateliteY,2));

            Circle circleMax = new Circle(radiusMax-3, Color.TRANSPARENT);
            Circle circleMin = new Circle(radiusMin+3, Color.TRANSPARENT);

            circleMax.setMouseTransparent(true);
            circleMin.setMouseTransparent(true);

            circleMax.setStroke(fill);
            circleMin.setStroke(fill);

            circleMax.setStrokeWidth(2);
            circleMin.setStrokeWidth(2);

            circleMax.setCenterX(sateliteX);
            circleMax.setCenterY(sateliteY);

            circleMin.setCenterX(sateliteX);
            circleMin.setCenterY(sateliteY);
            circleMax.toBack();
            circleMin.toBack();
            Shape area = Shape.subtract(circleMin, circleMax);
            if(fill == Color.YELLOW)
                area.setFill(Color.rgb(225, 245, 3, 0.4588));
            else if(fill == Color.RED)
                area.setFill(Color.rgb(211, 0, 16, 0.6941));
            else
                area.setFill(Color.rgb(4, 168, 25, 0.3765));

            area.setMouseTransparent(true);

            root.getChildren().addAll(circleMin,circleMax, area);

    }


}
