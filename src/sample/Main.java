package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
                Pane root = FXMLLoader.load(getClass().getResource("view/menu.fxml"));
                primaryStage.setTitle("Hello World");
                Scene scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
                primaryStage.show();


    }


    public static void main(String[] args) throws IOException {
       launch(args);
    }
    }
