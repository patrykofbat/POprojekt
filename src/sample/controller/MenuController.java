package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;


public class MenuController {

    @FXML
    private AnchorPane anchor;


    @FXML
    private Button btn;


    @FXML
    public void handleSubmit(){
        Stage stage = (Stage)btn.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
            stage.setScene(new Scene(root, Main.MAX_WIDTH, Main.MAX_HEIGHT));
        }
        catch (Exception e){

        }

    }

    public void initialize(){
        anchor.setMaxSize(Main.MAX_WIDTH, Main.MAX_HEIGHT);

    }









}

