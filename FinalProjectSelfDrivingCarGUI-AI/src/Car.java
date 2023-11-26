
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class Car extends Circle {

    private static final int CAR_RADIUS = 15;
    private static final int SENSOR_COUNT = 7;
    private static final Color CAR_COLOR = Color.IVORY;

    private double velocity;
    private double angularVelocity;
    private double fitnessScore;
    private Sensor[] sensorArray;

    private Point2D position; // Current position of the car
    private double angle;     // Current angle of the car, in radian
    private boolean alive;    // A flag to indicate if the car is still alive


    public Car(double angularVelocity, Point2D initialPosition) {

        super(CAR_RADIUS);
        this.setFill(CAR_COLOR);
        this.angularVelocity = angularVelocity;
        this.fitnessScore = 0;
        this.velocity = 5;

        this.position = initialPosition;
        this.setCenterX(initialPosition.getX());
        this.setCenterY(initialPosition.getY());

        this.angle = 0;
        this.alive = true;

        this.sensorArray = new Sensor[SENSOR_COUNT];

        for (int i = 0; i < SENSOR_COUNT; i++) {
            this.sensorArray[i] = new Sensor(i, this);
        }



//
//        // Calculate the starting angle of the cone
//        double startAngle = angle - coneAngle / 2;
//
//        // Calculate the angle increment for each sensor
//        double angleIncrement = coneAngle / (numSensors - 1);
//
//        for (int i = -2; i < numSensors - 2; i++) {
//
//            //double sensorAngle = startAngle + i * angleIncrement;
//            double sensorX = position.getX() + sensorRadius * Math.cos(-i * Math.PI / 5) * 4;
//            double sensorY = position.getY() + sensorRadius * Math.sin(-i * Math.PI / 5) * 4;
//
//            sensorArray[i + 2] = new Line();
//            sensorArray[i + 2].setStartX(position.getX());
//            sensorArray[i + 2].setStartY(position.getY());
//            sensorArray[i + 2].setEndX(sensorX);
//            sensorArray[i + 2].setEndY(sensorY);
//
//            sensorArray[i + 2].setTranslateX(this.getTranslateX() + this.getWidth() / 2);
//
//            double x = ((double) 1 / 2) * (sensorArray[i + 2].getEndX() - sensorArray[i + 2].getStartX());
//
//            double y = ((double) 1 / 2) * (sensorArray[i + 2].getEndY() - sensorArray[i + 2].getStartY());
//
//            sensorArray[i + 2].setTranslateX(sensorArray[i + 2].getTranslateX() + x - 5);
//            sensorArray[i + 2].setTranslateY(sensorArray[i + 2].getTranslateY() + y);
//        }
    }

    public double getVELOCITY() {

        return this.velocity;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public double getAngularVelocity() {
        return this.angularVelocity;
    }

    public double getFitnessScore() {
        return this.fitnessScore;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public double getAngle() {
        return this.angle;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void update() {
        if (alive) {
            // Update the car's position and angle based on its velocity and angular velocity
            double deltaX = velocity * Math.cos(Math.toRadians(angle));
            double deltaY = velocity * Math.sin(Math.toRadians(angle));
            position = position.add(deltaX, deltaY);
            angle += angularVelocity;

            // Check for collision or boundary conditions, update alive accordingly
            if (isCollidingWithObstacle() || isOutOfBoundary()) {
                alive = false;
            }

            // Update the fitness score (you can define the scoring mechanism)
            fitnessScore += 1; // For example, increase the score on each update
        }
    }

    private boolean isCollidingWithObstacle() {
        // Implement collision detection logic here
        // You may need to check if any part of the car's body is colliding with an obstacle
        return false; // Replace with your collision detection code
    }

    private boolean isOutOfBoundary() {
        // Implement boundary check logic here
        // Check if the car has gone out of the valid boundary
        return false; // Replace with your boundary check code
    }

    //Matrix multiplications method for neuronetworking
    static double multiplyVectors(double[] a, double[] b) {

        if (a.length != b.length) {
            throw new IllegalArgumentException("Input vectors must have the same length");
        }

        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }

        return result;
    }


    public Sensor[] getSensorArray() {
        return sensorArray;
    }



}
