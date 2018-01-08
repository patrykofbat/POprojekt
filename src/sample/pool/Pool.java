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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pool {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
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
        MotherRobot motherRobot = new MotherRobot(7 + (int)(Math.random() * 786), 107 + (int)(Math.random() * 486), this.hBox);
        this.motherRobot = motherRobot;
        Text text = new Text("M");
        text.setFont(Font.font("Werdana", FontWeight.BOLD,14));
        text.setX(motherRobot.getXPosition()-7);
        text.setY(motherRobot.getYPosition()+4);
        text.toFront();
        this.root.getChildren().add(motherRobot.getShape());
        this.root.getChildren().add(text);
    }

    /**
     * This method generate robots
     * @param density tells how much robots
     * you goona generate per div
     * */
    private void generateRobots(int density) {
        int xStep = WIDTH / 7;
        int yStep = HEIGHT / 7;

        for (int i = 107; i < HEIGHT; i += yStep) {
            for (int j = 7; j < WIDTH; j += xStep) {
                for (int k = 0; k < density; k++) {
                    int y = i + (int) (Math.random() * yStep);
                    int x = j + (int) (Math.random() * xStep);
                    Robot robot = new Robot((x > 793 ? 793 : x), (y > 593 ? 593 : y), this.hBox);
                    this.robotsList.add(robot);
                    this.root.getChildren().add(robot.getShape());
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
        int xStep = WIDTH / 7;
        int yStep = HEIGHT / 7;

        // loops generate grid
        for(int i = 100; i < HEIGHT; i += yStep){
            Line line = new Line();

            line.setStroke(Color.BLACK);
            line.setStrokeWidth(3);

            line.setStartX(0);
            line.setStartY(i);
            line.setEndX(800);
            line.setEndY(i);

            this.root.getChildren().add(line);
        }

        for(int j = 0; j < WIDTH; j += xStep){
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

            int y = 107 + (int)(Math.random() * 486);
            int x = 7 + (int)(Math.random() * 786);
            Satelite satelite = new Satelite(x, y, colors.get(i));

            coordinate[j] = x;
            coordinate[j+1] = y;

            nodes.add(satelite.addSateliteSignal(x, y));
            nodes.add(satelite.getShape());

            this.sateliteList.add(satelite);
            j += 2;

        }
        Polygon polygon = new Polygon();

        for(double point : coordinate)
            polygon.getPoints().add(point);

        polygon.setStroke(Color.BLACK);
        polygon.setFill(Color.rgb(255, 255, 255, .7));
        polygon.setMouseTransparent(true);

        root.getChildren().add(polygon);
        root.getChildren().addAll(nodes);


    }


    public void show(){
        renderGrid();
        generateRobots(50);
        renderSatelite();
        generateMother();
    }

    public MotherRobot getMotherRobot() {
        return motherRobot;
    }
}
