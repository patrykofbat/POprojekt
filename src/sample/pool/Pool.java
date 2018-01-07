package sample.pool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pool {
    private Pane root;
    private HBox hBox;
    private ArrayList<Robot> robotsList;
    private ArrayList<Satelite> sateliteList;

    public Pool(Pane root, HBox hBox) {
        robotsList = new ArrayList<>();
        sateliteList = new ArrayList<>();
        this.root = root;
        this.hBox = hBox;

    }

    public ArrayList<Robot> getRobotsList() {
        return robotsList;
    }

    public ArrayList<Satelite> getSateliteList() {
        return sateliteList;
    }


    public void generateRobots(int density) {
        int width = 800;
        int height = 600;
        int xStep = width / 7;
        int yStep = height / 7;

        for (int i = 107; i < height; i += yStep) {
            for (int j = 7; j < width; j += xStep) {
                for (int k = 0; k < density; k++) {
                    int y = i + (int) (Math.random() * yStep);
                    int x = j + (int) (Math.random() * xStep);
                    Robot robot = new Robot((x > 793 ? 793 : x) ,(y > 593 ? 593 : y), this.hBox);
                    this.robotsList.add(robot);
                    this.root.getChildren().add(robot.getShape());
                    robot.getShape().toBack();
                }
            }
        }

    }



    public void renderSatelite(){
        Random randomNumber = new Random();
        List<Node> nodes =new ArrayList<Node>();
        double [] coordinate = new double[6];
        int j =0;
        for(int i=0; i<3; i++){
            int y = 107 + (int)(Math.random() * 486);
            int x = 7 + (int)(Math.random() * 786);
            Satelite satelite = new Satelite(x, y);

            coordinate[j] = x;
            coordinate[j+1] = y;

            nodes.add(addSateliteSignal(x, y));
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

    private Circle addSateliteSignal(int x, int y){
        Circle circle = new Circle(10);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.LIGHTGREEN);
        circle.setStrokeWidth(2);


        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(circle.radiusProperty(), 10)

                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(circle.radiusProperty(), 45)
                )
        );
        task.setCycleCount(Timeline.INDEFINITE);
        task.playFromStart();
        return circle;

    }

    public void show(){
        generateRobots(50);
        renderSatelite();
    }
}
