package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import sample.pool.Pool;
import sample.pool.RSSISignal;
import sample.pool.Robot;

public class Controller {

    @FXML
    private HBox hBoxUp;

    @FXML
    private Circle motherRobot;

    @FXML
    private Pane root;


    public void initialize(){
        Pool pool = new Pool(this.root, this.hBoxUp);
        pool.show();
        motherRobot.setCenterX(7 + (int)(Math.random() * 786));
        motherRobot.setCenterY(107 + (int)(Math.random() * 486));
        motherRobot.toFront();
        RSSISignal rssiSignal = new RSSISignal(pool);
        rssiSignal.sendSignal();
    }



}
