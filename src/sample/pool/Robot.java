package sample.pool;

import javafx.animation.FadeTransition;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.util.Duration;



public class Robot implements Drawable{


    private HBox hBox;
    private Circle circle;
    private Polygon polygon;
    private int xPosition;
    private int yPosition;
    private double[] signal;

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }


    public Robot(int xPosition, int yPosition, HBox hBox){
        this.signal = new double[3];
        this.hBox = hBox;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        this.circle = new Circle(6, Color.DARKRED);

    }

    public void setSignals(double[] signal) {
        this.signal = signal;
    }


    @Override
    public Shape getShape() {
        this.circle.setCenterX(xPosition);
        this.circle.setCenterY(yPosition);

        this.circle.setStroke(Color.SILVER);
        this.circle.setStrokeWidth(2);

        setHoverEffect();


        return this.circle;
    }


    private void setHoverEffect(){
        this.circle.setOnMouseEntered(event -> {
            Text text = new Text();
            text.setFont(new Font(20));
            text.setText("#1: " + this.signal[0] + "\n" + "#2: " + this.signal[1] + "\n" + "#3: " + this.signal[2]);
            text.setFill(Color.WHITE);
            this.hBox.getChildren().add(text);

            circle.setRadius(8);
            circle.toFront();

            FadeTransition ft = new FadeTransition(Duration.millis(500), text);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        });

        this.circle.setOnMouseExited(event -> {
            this.hBox.getChildren().clear();
            circle.setRadius(6);
            circle.toBack();
        });
    }



}



