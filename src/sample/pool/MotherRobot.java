package sample.pool;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class MotherRobot implements Drawable {

    private double xPosition;
    private double yPosition;
    private HBox hBox;
    private double[] signal;
    private Circle circle;

    public MotherRobot(double xPosition, double yPosition, HBox hBox) {
        this.hBox = hBox;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.circle = new Circle(10, Color.BLUE);

    }


    public void setSignals(double[] signal) {
        this.signal = signal;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    @Override
    public Shape getShape() {
        this.circle.setCenterX(xPosition);
        this.circle.setCenterY(yPosition);
        this.circle.toFront();

        this.circle.setStroke(Color.SILVER);
        this.circle.setStrokeWidth(2);
        return this.circle;
    }

    public double[] getSignals() {
        return signal;
    }
}
