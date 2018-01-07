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

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MenuController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button btn;



    @FXML
    public void handleSubmit(){
        Stage stage = (Stage)btn.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
            stage.setScene(new Scene(root, 800, 600));
        }
        catch (Exception e){

        }

    }




    public void initialize(){
        start();

    }


    public void start() {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(progressBar.progressProperty(), 0)


                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(progressBar.progressProperty(), 1),
                        new KeyValue(btn.styleProperty(), "visibility:visible;")

                )
        );
        task.playFromStart();

    }




}

