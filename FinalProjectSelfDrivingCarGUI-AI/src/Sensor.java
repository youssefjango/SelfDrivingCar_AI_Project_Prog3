


import javafx.beans.binding.Bindings;
import javafx.scene.shape.Line;

/**
 *
 * This class consists of the sensors that are contained in the car. They are used to help the artificial intelligence depict the borders of the track
 */
public class Sensor extends Line {

    private static double LENGTH = 150;
    private double projectedNormalizedLength;

    /**
     *
     * @param order
     * @param car
     */
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

    /**
     *
     * @param length
     */
    public static void setSensorLength(double length){
        LENGTH = length;
    };

    /**
     *
     * @return
     */
    public static double getLength() {
        return LENGTH;
    }

    /**
     *
     * @return
     */
    public double getProjectedLength() {
        return projectedNormalizedLength;
    }

    /**
     *
     * @param projectedLength
     */
    public void setProjectedLength(double projectedLength) {
        this.projectedNormalizedLength = projectedLength;
    }
}
