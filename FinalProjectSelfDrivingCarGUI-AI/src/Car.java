
import java.util.ArrayDeque;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Deque;

public class Car extends Circle implements Drivable{

    private static float LEARNING_RATE = 0.3f;
    private static final int CAR_RADIUS = 15;
    
    private static final Color CAR_COLOR = Color.IVORY;
    private static final double MIN_SPEED = 1;

    private static double ANGULAR_VELOCITY = 3;
    private static double VELOCITY = 3;


    private int fitnessScore;
    private Sensor[] sensorArray;

    private NeuralNetwork brain;


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

        this.brain = new NeuralNetwork(LEARNING_RATE, allLayersList.stream().mapToInt(Integer::intValue).toArray());

    }



    public void update(double angle, double speed) {

        this.setRotate(this.getRotate() + angle * ANGULAR_VELOCITY);
        double deltaX = VELOCITY *  Math.max(Math.abs(speed), MIN_SPEED) * Math.cos(Math.toRadians(this.getRotate()));
        double deltaY = VELOCITY *  Math.max(Math.abs(speed), MIN_SPEED) * Math.sin(Math.toRadians(this.getRotate()));
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


    public Sensor[] getSensorArray() {
        return sensorArray;
    }


    public NeuralNetwork getBrain() {
        return brain;
    }

    public void setBrain(NeuralNetwork brain) {
        this.brain = brain;
    }

    public int getFitnessScore() {

        return this.fitnessScore;
    }

    public void setFitnessScore(int score) {

        this.fitnessScore = score;

    }
    
    
    public static void setANGULAR_VELOCITY(double ANGULAR_VELOCITY) {
        Car.ANGULAR_VELOCITY = ANGULAR_VELOCITY;
    }

    public static void setVELOCITY(double VELOCITY) {
        Car.VELOCITY = VELOCITY;
    }

    public static void setLearningRate(float learningRate) {
        Car.LEARNING_RATE = learningRate;
    }
}