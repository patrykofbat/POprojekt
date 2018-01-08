package sample.pool;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Satelite implements Drawable {

    private Circle circle;
    private int xPosition;
    private int yPosition;

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }


    public Satelite(int xPosition, int yPosition, Paint color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.circle = new Circle(10, color);
    }

    @Override
    public Shape getShape() {
        this.circle.setCenterX(xPosition);
        this.circle.setCenterY(yPosition);
        this.circle.setStroke(Color.BLACK);
        this.circle.setStrokeWidth(2);

        this.addSateliteSignal(xPosition, yPosition);
        this.circle.setMouseTransparent(true);
        return this.circle;
    }

    public Circle addSateliteSignal(int x, int y){
        Circle circle = new Circle(10);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setMouseTransparent(true);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BROWN);
        circle.setStrokeWidth(3);

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



}
