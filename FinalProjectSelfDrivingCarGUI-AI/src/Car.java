
import java.util.ArrayDeque;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * Class which consists of the car used for the races
 */
public class Car extends Circle implements Drivable{

    private static float learningRate = 0.3f;
    private static final int CAR_RADIUS = 15;
    
    private static final Color CAR_COLOR = Color.IVORY;
    private static final double MIN_SPEED = 1;

    private static double angularVelocity = 3;
    private static double velocity = 3;


    private int fitnessScore;
    private Sensor[] sensorArray;

    private NeuralNetwork brain;

    /**
     *
     * @param initialPosition
     * @param layers
     */
    public Car(Point2D initialPosition,int[] layers) {

        super(CAR_RADIUS);
        this.setFill(CAR_COLOR);
        this.fitnessScore = 0;


        this.setCenterX(initialPosition.getX());
        this.setCenterY(initialPosition.getY());

        this.setRotate(90);

        this.sensorArray = new Sensor[SENSOR_COUNT];

        for (int i = 0; i < SENSOR_COUNT; i++) {
            this.sensorArray[i] = new Sensor(i, this);
        }

        Deque<Integer> allLayersList = new ArrayDeque<Integer>();

        for (int nbNeurons:layers) allLayersList.add(nbNeurons);

        allLayersList.addFirst(sensorArray.length);
        allLayersList.addLast(2);

        int[] allLayersArray = allLayersList.stream().mapToInt(Integer::intValue).toArray();

        this.brain = new NeuralNetwork(learningRate, allLayersList.stream().mapToInt(Integer::intValue).toArray());

    }

    /**
     *
     * @param angle
     * @param speed
     */
    public void update(double angle, double speed) {

        this.setRotate(this.getRotate() + angle * angularVelocity);
        double deltaX = velocity *  Math.max(Math.abs(speed), MIN_SPEED) * Math.cos(Math.toRadians(this.getRotate()));
        double deltaY = velocity *  Math.max(Math.abs(speed), MIN_SPEED) * Math.sin(Math.toRadians(this.getRotate()));
        this.setCenterX(this.getCenterX() - deltaX);
        this.setCenterY(this.getCenterY() - deltaY);


        // Update the fitness score
        fitnessScore += 1;

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

    /**
     *
     * @return
     */
    public Sensor[] getSensorArray() {
        return sensorArray;
    }

    /**
     *
     * @return
     */
    public NeuralNetwork getBrain() {
        return brain;
    }

    /**
     *
     * @param brain
     */
    public void setBrain(NeuralNetwork brain) {
        this.brain = brain;
    }

    /**
     *
     * @return
     */
    public int getFitnessScore() {

        return this.fitnessScore;
    }

    /**
     *
     * @param score
     */
    public void setFitnessScore(int score) {

        this.fitnessScore = score;

    }
    
    /**
     *
     * @param angularVelocity
     */
    public static void setAngularVelocity(double angularVelocity) {
        Car.angularVelocity = angularVelocity;
    }

    /**
     *
     * @param velocity
     */
    public static void setVelocity(double velocity) {
        Car.velocity = velocity;
    }

    /**
     *
     * @param learningRate
     */
    public static void setLearningRate(float learningRate) {
        Car.learningRate = learningRate;
    }
}