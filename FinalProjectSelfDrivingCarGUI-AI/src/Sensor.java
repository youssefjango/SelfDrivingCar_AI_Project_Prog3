


import javafx.beans.binding.Bindings;
import javafx.scene.shape.Line;

public class Sensor extends Line {

    private static double LENGTH = 150;
    private double projectedNormalizedLength;

    public Sensor(int order, Car car) {

        this.startXProperty().bind(car.centerXProperty());
        this.startYProperty().bind(car.centerYProperty());

        this.endXProperty().bind(Bindings.createDoubleBinding(()
                        -> car.centerXProperty().get() - LENGTH * Math.cos(
                        Math.toRadians((-90 + 30 * order) + car.rotateProperty().get())),
                car.centerXProperty(), car.rotateProperty()));
        this.endYProperty().bind(Bindings.createDoubleBinding(()
                        -> car.centerYProperty().get() - LENGTH * Math.sin(
                        Math.toRadians((-90 + 30 * order) + car.rotateProperty().get())),
                car.centerYProperty(), car.rotateProperty()));

    }
    public static void setSensorLength(double length){
        LENGTH = length;
    };

    public static double getLength() {
        return LENGTH;
    }

    public double getProjectedLength() {
        return projectedNormalizedLength;
    }

    public void setProjectedLength(double projectedLength) {
        this.projectedNormalizedLength = projectedLength;
    }
}
