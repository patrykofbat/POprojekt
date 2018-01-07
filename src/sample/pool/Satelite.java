package sample.pool;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

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


    public Satelite(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.circle = new Circle(10, Color.BLACK);
    }

    @Override
    public Shape getShape() {
        circle.setCenterX(xPosition);
        circle.setCenterY(yPosition);
        return this.circle;
    }



}
