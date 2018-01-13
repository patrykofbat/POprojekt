package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static double MAX_WIDTH;
    public static double MAX_HEIGHT;

    @Override
    public void start(Stage primaryStage) throws Exception{
                Pane root = FXMLLoader.load(getClass().getResource("view/menu.fxml"));
                primaryStage.setTitle("Robot Survivor");
                ImageView imageView = new ImageView(new Image(new File("view/Robots.jpg").toURI().toURL().toExternalForm()));
                root.getChildren().add(imageView);
                primaryStage.setMaximized(true);
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                MAX_WIDTH = bounds.getWidth();
                MAX_HEIGHT =  bounds.getHeight();

                Scene scene = new Scene(root, MAX_WIDTH, MAX_HEIGHT);
                primaryStage.setScene(scene);
                primaryStage.show();


    }


    public static void main(String[] args) throws IOException {
       launch(args);
    }
    }
