package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Main;
import sample.pool.LocationSolver;
import sample.pool.Pool;
import sample.pool.RSSISignal;
import sample.pool.Robot;

public class Controller {



    @FXML
    private HBox hBoxUp;


    @FXML
    private Pane root;

    @FXML
    private Button btnNext;


    public void initialize(){
        this.hBoxUp.setPrefWidth(Main.MAX_WIDTH);
        this.hBoxUp.setPrefHeight(Main.MAX_HEIGHT/8);
        this.btnNext.setAlignment(Pos.BASELINE_RIGHT);
        Pool pool = new Pool(this.root, this.hBoxUp);
        pool.show();
        RSSISignal rssiSignal = new RSSISignal(pool);
        rssiSignal.sendSignal();
        LocationSolver locationSolver = new LocationSolver(pool.getMotherRobot(), pool.getRobotsList(), pool.getSateliteList());
        locationSolver.displayPosibilites(0,1, Color.RED, this.root);
        locationSolver.displayPosibilites(1,1, Color.GREEN, this.root);
        locationSolver.displayPosibilites(2, 1, Color.YELLOW, this.root);

        this.hBoxUp.toFront();

        pool.getMotherRobot().getShape().toFront();
        locationSolver.solveLocation();

    }

    public void nextModel(){
        Stage stage = (Stage)this.root.getScene().getWindow();
        try {
            this.root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
            stage.setScene(new Scene(root, Main.MAX_WIDTH, Main.MAX_HEIGHT));

        }
        catch(Exception e){
            e.printStackTrace();

        }


    }



}
