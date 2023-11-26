
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
import InterfaceComponents.Interface;
import ArtificialIntelligenceComponents.Sensor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author 2278304
 */
public class SelfDrivingCar extends Application {

    Interface simulation = new Interface();
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        simulation.draw();

        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene menu = new Scene(root);

        //SCENE
        scene = new Scene(simulation.getRoot(), 1500, 1000);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        this.test();

        primaryStage.setTitle("Self Driving Cars");
        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void test() {
        simulation.carPane.getChildren().add(simulation.car);
        double rotSpeed = 0.8;
        double carVel = 3;

        for (Sensor sensor : simulation.car.getSensorArray()) {
            simulation.carPane.getChildren().add(sensor);
        }
        scene.setOnKeyPressed(
                e -> {
                    switch (e.getCode().toString()) {
                        case ("W") -> {
                            double deltaX = carVel * Math.cos(Math.toRadians(simulation.car.getRotate()));
                            double deltaY = carVel * Math.sin(Math.toRadians(simulation.car.getRotate()));

                            simulation.car.setCenterX(simulation.car.getCenterX() - deltaX);
                            simulation.car.setCenterY(simulation.car.getCenterY() - deltaY);
                        }
                        case ("S") -> {
                            for (int i = 0; i < simulation.car.getSensorArray().length; i++) {

                                System.out.println("The sensor number " + (int) (i + 1) + " detects a distance of: " + checkCollisionWithWall(simulation.getBordersList(), i));

                            }
                            System.out.println();

                        }
                        case ("D") -> {
                            simulation.car.setRotate(simulation.car.getRotate() + rotSpeed);
                        }
                        case ("A") -> {
                            simulation.car.setRotate(simulation.car.getRotate() - rotSpeed);

                        }

                    }
                }
        );
    }

    public double checkCollisionWithWall(List<Shape> raceTrack, int order) {
        double distance = Double.MAX_VALUE; // Initialize with a large value
        Line sensor = simulation.car.getSensorArray()[order];

        for (Iterator<Shape> it = raceTrack.iterator(); it.hasNext();) {
            Shape wall = it.next();
            Shape intersection = Shape.intersect(sensor, wall);
            if (intersection.getBoundsInLocal().getWidth() != -1) {
                // Collision detected, calculate distance from sensor to wall
                double lastDistance = calculateDistance(sensor, wall);
                if (lastDistance < distance) {
                    distance = lastDistance; // Update distance only if smaller
                }
            }
        }

        // Check if any collision was detected
        if (distance == Double.MAX_VALUE) {
            // No collision detected
            return -1;
        } else {
            // Return the smallest distance
            return distance;
        }
    }

    private double calculateDistance(Shape shape1, Shape shape2) {
        // Get the center coordinates of the shapes
        double centerX1 = shape1.getBoundsInParent().getCenterX();
        double centerY1 = shape1.getBoundsInParent().getCenterY();
        double centerX2 = shape2.getBoundsInParent().getCenterX();
        double centerY2 = shape2.getBoundsInParent().getCenterY();

        // Calculate the distance between the centers of the shapes
        double distance = Math.sqrt(Math.pow(centerX2 - centerX1, 2) + Math.pow(centerY2 - centerY1, 2));

        //System.out.println("calculated distance is " + distance);
        return distance;
    }

    //IF CARS CRASHED EQUAL TO NUMBER OF CARS, DO SOMETHING
//    public boolean checkCollisions(Car currCar) {
//
//        for (Line x : bordersList) {
//
//            if (currCar.getBody().getBoundsInParent().intersects(x.getBoundsInParent())) {
//                if (carCrashed != carList.size()) {
//                    carCrashed++;
//                } else {
//                    //START NEW GENERATION BECAUSE ALL CARS HAVE BEEN CRASHED
//                }
//                return true;
//            }
//        }
//        return false;
//    }
    //IF CARS CRASHED EQUAL TO NUMBER OF CARS, DO SOMETHING
//    public ArrayList<Double> checkDistance(Car currCar) {
//        ArrayList<Double> distances = new ArrayList<>();
//        for (Line border : bordersList) {
//            for (Line sensor : currCar.getSensorArray()) {
//                if (sensor.getBoundsInParent().intersects(border.getBoundsInParent())) {
//                    //FIND TWO EQUATIONS ONE FOR BORDER AND ONE FOR SENSOR
//                    double aBorder = (border.getStartY() - border.getEndY()) / (border.getStartX() - border.getEndX());
//                    double bBorder = border.getStartY() - (aBorder * border.getStartX());
//                    double aSensor = (sensor.getStartY() - sensor.getEndY()) / (sensor.getStartX() - sensor.getEndX());
//                    //FIND SOLUTION X,Y WHICH CORRESPONDS TO THEIR INTERSECTION
//                    double bSensor = sensor.getStartY() - (aBorder * sensor.getStartX());
//                    double xSolution = (bBorder - bSensor) / (aSensor - bSensor);
//                    double ySolution = aBorder * xSolution + bBorder;
//                    //FIND THE LENGTH OF THE VECTOR FROM THE START OF THE SENSOR TO THE END (DISTANCE)
//                    distances.add(Math.sqrt(Math.pow((xSolution - sensor.getStartX()), 2)
//                            + Math.pow(ySolution - sensor.getStartY(), 2)));
//
//                }
//            }
//        }
//        return distances;
//    }
    public static void main(String[] args) {
        launch(args);
    }

}
