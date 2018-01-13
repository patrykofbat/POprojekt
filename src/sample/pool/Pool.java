package sample.pool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pool {
    private Pane root;
    private HBox hBox;
    private MotherRobot motherRobot;
    private ArrayList<Robot> robotsList;
    private ArrayList<Satelite> sateliteList;

    public Pool(Pane root, HBox hBox) {
        this.robotsList = new ArrayList<>();
        this.sateliteList = new ArrayList<>();
        this.root = root;
        this.hBox = hBox;

    }

    public ArrayList<Robot> getRobotsList() {
        return robotsList;
    }

    public ArrayList<Satelite> getSateliteList() {
        return sateliteList;
    }


    public void generateMother(){
        double y = Main.MAX_HEIGHT/4 + (Math.random() * 2 *Main.MAX_HEIGHT/4);
        double x = Main.MAX_WIDTH/7 + (Math.random() * 4 * Main.MAX_WIDTH/7);
        MotherRobot motherRobot = new MotherRobot( x, y, this.hBox);
        this.motherRobot = motherRobot;
        this.root.getChildren().add(motherRobot.getShape());
    }

    /**
     * This method generate robots
     * @param density tells how much robots
     * you goona generate per div
     * */
    private void generateRobots(int density) {
        double xStep = Main.MAX_WIDTH / 7;
        double yStep = Main.MAX_HEIGHT / 7;

        for (double i = 0; i < Main.MAX_HEIGHT; i += yStep) {
            for (double j = 0; j < Main.MAX_WIDTH; j += xStep) {
                for (int k = 0; k < density; k++) {
                    double y = i + (Math.random() * yStep);
                    double x = j + (Math.random() * xStep);
                    Robot robot = new Robot(x, y, this.hBox);
                    this.robotsList.add(robot);
//                    this.root.getChildren().add(robot.getShape());
                    robot.getShape().toBack();
                }
            }
        }

    }

    /**
     * Method which is responsible for
     * rendering grid
     * */
    private void renderGrid(){
        double xStep = Main.MAX_WIDTH / 7;
        double yStep = Main.MAX_HEIGHT / 7;

        // loops generate grid
        for(double i = Main.MAX_HEIGHT/8; i < Main.MAX_HEIGHT; i += yStep){
            Line line = new Line();

            line.setStroke(Color.BLACK);
            line.setStrokeWidth(3);

            line.setStartX(0);
            line.setStartY(i);
            line.setEndX(800);
            line.setEndY(i);

            this.root.getChildren().add(line);
        }

        for(int j = 0; j < Main.MAX_WIDTH; j += xStep){
            Line line = new Line();

            line.setStroke(Color.BLACK);
            line.setStrokeWidth(3);

            line.setStartX(j);
            line.setStartY(100);
            line.setEndX(j);
            line.setEndY(600);

            this.root.getChildren().add(line);
        }
    }



    private void renderSatelite(){
        Random randomNumber = new Random();
        List<Node> nodes =new ArrayList<Node>();
        double [] coordinate = new double[6];
        List<Paint> colors = Arrays.asList(Color.RED,Color.GREEN, Color.YELLOW);
        int j =0;

        for(int i=0; i<3; i++){
            double x = 0;
            double y = 0;
            if(i == 0) {
                y = Main.MAX_HEIGHT / 4 + (Math.random() *3 * Main.MAX_HEIGHT / 4);
                x = 2* Main.MAX_WIDTH / 7 + (Math.random()  * Main.MAX_WIDTH / 20);
            }
            if(i == 1){
                y = Main.MAX_HEIGHT / 4 + (Math.random()  * Main.MAX_HEIGHT / 10);
                x = Main.MAX_WIDTH / 7 + (Math.random() * 4 * Main.MAX_WIDTH / 7);
            }

            if(i == 2){
                y = Main.MAX_HEIGHT / 4 + (Math.random() * 3 * Main.MAX_HEIGHT / 4);
                x = 4 * Main.MAX_WIDTH / 7 + (Math.random() * Main.MAX_WIDTH / 20);
            }
            Satelite satelite = new Satelite(x, y, colors.get(i));

            coordinate[j] = x;
            coordinate[j+1] = y;

            nodes.add(satelite.addSateliteSignal(x, y));
            nodes.add(satelite.getShape());

            this.sateliteList.add(satelite);
            j += 2;

        }
        Polygon polygon = new Polygon();
        polygon.setStroke(Color.WHITE);

        for(double point : coordinate)
            polygon.getPoints().add(point);

        polygon.setFill(Color.rgb(255, 255, 255, .7));
        polygon.setMouseTransparent(true);

        root.getChildren().add(polygon);
        root.getChildren().addAll(nodes);


    }


    public void show(){
//        renderGrid();
        generateRobots(500);
        renderSatelite();
        generateMother();
        this.motherRobot.getShape().toFront();

    }

    public MotherRobot getMotherRobot() {
        return motherRobot;
    }
}
