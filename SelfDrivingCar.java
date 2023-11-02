package com.example.demo2;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 *
 * @author 2278304
 */
public class SelfDrivingCar extends Application {
    Rectangle car1 = new Rectangle(90,90);
    boolean pause = true;
    ArrayList<Line> bordersList = new ArrayList();
    ArrayList<Car> carList = new ArrayList();

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("Interface Visualizations");
        AnimationTimer timer = new MyTimer();

        GridPane userInterface = new GridPane();
        userInterface.setAlignment(Pos.CENTER_LEFT);
        userInterface.setHgap(10);
        userInterface.setVgap(5);
        userInterface.setPadding(new Insets(10,10,10,10));

        userInterface.add(new Label("Number Of Cars:"), 0, 0);
        TextField noCars = new TextField();
        noCars.setMaxWidth(50);
        userInterface.add(noCars, 1, 0);


        userInterface.add(new Label("Mutation Rate:"), 0, 1);
        TextField mutRate = new TextField();
        mutRate.setMaxWidth(50);
        userInterface.add(mutRate, 1, 1);

        userInterface.add(new Label("Car Speed:"), 0, 2);
        TextField carSpeed = new TextField();
        carSpeed.setMaxWidth(50);
        userInterface.add(carSpeed, 1, 2);

        userInterface.add(new Label("Angular Velocity:"), 0, 3);
        TextField angVelocity = new TextField();
        angVelocity.setMaxWidth(50);
        userInterface.add(angVelocity, 1, 3);


        Button save = new Button("Save Settings");
        userInterface.add(save, 0, 5);

        Button start = new Button("Start Race");
        userInterface.add(start, 0, 6);

        Button reset =new Button("Reset Race");
        userInterface.add(reset, 0, 7);

        Button play = new Button("Pause / Play");
        userInterface.add(play, 0, 8);

        play.setOnAction(actionEvent -> {

            if (pause) {

                pause = false;
                timer.start();

            }
            else if (!pause) {

                pause = true;
                timer.stop();

            }
        });

        userInterface.add(new Label("Generation:"), 0, 9);
        TextField gen = new TextField();
        gen.setMaxWidth(50);
        gen.setEditable(false);
        userInterface.add(gen, 1, 9);





        BorderPane root = new BorderPane();
        root.setLeft(userInterface);
        root.setTop(title);
        title.setTranslateX(400);
        title.setTranslateY(10);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;" );



        //creating pane and scene for the simulation

        StackPane carPane = new StackPane();
        carPane.setMaxWidth(900);
        carPane.setMaxHeight(800);
        Scene carScene = new Scene(carPane,100,5);
       // Car car2 = new Car(5);
        //carPane.getChildren().addAll(car2.getBody());

            // Create a car instance
            Car car = new Car(0, new Point2D(800, 800)); // Provide initial position

            // Set the position and color of the car's body
            car.getBody().setX(car.getPosition().getX());
            car.getBody().setY(car.getPosition().getY());
            car.getBody().setFill(Color.BLUE);

            // Add the car's body to the Pane
            carPane.getChildren().add(car.getBody());

            // Add the sensor lines to the Pane
            for (Line sensor : car.sensorArray) {
                sensor.setStroke(Color.RED);
                carPane.getChildren().add(sensor);
            }


        carPane.setBorder(Border.stroke(Color.BLACK));
        carPane.setAlignment(Pos.CENTER);
        root.setCenter(carPane);




        Scene scene = new Scene(root, 1500, 1000);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean checkCollisions(Car currCar) {

        for (Line x:bordersList) {

            if(currCar.getBody().getBoundsInParent().intersects(x.getBoundsInParent())) return true;

        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    class MyTimer extends AnimationTimer{


        @Override
        public void handle(long l) {



        }
    }

}