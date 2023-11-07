package com.example.demo;

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
import java.util.List;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

/**
 *
 * @author 2278304
 */
public class SelfDrivingCar extends Application {

    Rectangle car1 = new Rectangle(90, 90);
    boolean pause = true;
    ArrayList<Line> bordersList = new ArrayList();
    ArrayList<Shape> raceTrack = new ArrayList();
    ArrayList<Car> carList = new ArrayList();
    int carCrashed = 0;

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("Interface Visualizations");
        AnimationTimer timer = new MyTimer();

        GridPane userInterface = new GridPane();
        userInterface.setAlignment(Pos.CENTER_LEFT);
        userInterface.setHgap(10);
        userInterface.setVgap(5);
        userInterface.setPadding(new Insets(10, 10, 10, 10));

        userInterface.add(new Label("Number Of Cars:"), 0, 0);
        TextField noCars = new TextField();
        noCars.setMaxWidth(50);
        userInterface.add(noCars, 1, 0);

        //CAR COLOR PICKER
        //BOX THAT WILL DISPLAY THE COLOR THAT HAS BEEN MADE USING THE SLIDERS
        Rectangle color = new Rectangle(50, 50, Color.BLACK);
        userInterface.add(color, 1, 1);
        userInterface.setVgap(10);

        //RED SLIDER
        Label redSlider = new Label("Red");
        Slider sliderRed = new Slider(0, 180, 10);
        sliderRed.setMinWidth(150);
        sliderRed.setMajorTickUnit(10);
        sliderRed.setMinorTickCount(9);
        sliderRed.setBlockIncrement(1);

        //GREEN SLIDER
        Label greenSlider = new Label("Green");
        Slider sliderGreen = new Slider(0, 180, 10);
        sliderGreen.setMinWidth(150);
        sliderGreen.setMajorTickUnit(10);
        sliderGreen.setMinorTickCount(9);
        sliderGreen.setBlockIncrement(1);

        //BLUE SLIDER 
        Label blueSlider = new Label("Blue");
        Slider sliderBlue = new Slider(0, 255, 10);
        sliderBlue.setMinWidth(150);
        sliderBlue.setMajorTickUnit(10);
        sliderBlue.setMinorTickCount(9);
        sliderBlue.setBlockIncrement(1);

        sliderRed.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            color.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });
        sliderGreen.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            color.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });
        sliderBlue.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            color.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });

        //VBOX FOR COLOR PICKER
        VBox colorBox = new VBox(5, redSlider, sliderRed, greenSlider, sliderGreen, blueSlider, sliderBlue);
        userInterface.add(colorBox, 0, 1);

        //MUTATION RATE:
        userInterface.add(new Label("Mutation Rate:"), 0, 2);
        TextField mutRate = new TextField();
        mutRate.setMaxWidth(50);
        userInterface.add(mutRate, 1, 2);

        //CAR SPEED
        userInterface.add(new Label("Car Speed:"), 0, 3);
        TextField carSpeed = new TextField();
        carSpeed.setMaxWidth(50);
        userInterface.add(carSpeed, 1, 3);

        //ANGULAR VELOCITY
        userInterface.add(new Label("Angular Velocity:"), 0, 4);
        TextField angVelocity = new TextField();
        angVelocity.setMaxWidth(50);
        userInterface.add(angVelocity, 1, 4);

        //SAVE BUTTON
        Button save = new Button("Save Settings");
        save.setDisable(true);
        userInterface.add(save, 0, 6);

        //START BUTTON
        Button start = new Button("Start Race");
        start.setDisable(true);
        userInterface.add(start, 0, 7);

        //RESET BUTTON
        Button reset = new Button("Reset Race");
        reset.setDisable(true);
        userInterface.add(reset, 0, 8);

        //PLAY AND PAUSE BUTTON
        Button play = new Button("Pause / Play");
        play.setDisable(true);
        userInterface.add(play, 0, 9);

        //GENERATION OF CAR(s)
        userInterface.add(new Label("Generation:"), 0, 10);
        TextField gen = new TextField();
        gen.setMaxWidth(50);
        gen.setEditable(false);
        userInterface.add(gen, 1, 10);

        //PANE ROOT CREATION
        BorderPane root = new BorderPane();
        root.setLeft(userInterface);
        root.setTop(title);
        title.setTranslateX(400);
        title.setTranslateY(10);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        //==========================================================================================================================================
        //SCENE AND PANES (creating pane and scene for the simulation)
        //CAR PANE 
        //AREA WHERE CARS AND RACETRACK WILL BE SHOWN
        StackPane carPane = new StackPane();
        carPane.setMaxWidth(900);
        carPane.setMaxHeight(800);
        Scene carScene = new Scene(carPane, 100, 5);

        carPane.setBorder(Border.stroke(Color.BLACK));
        carPane.setAlignment(Pos.CENTER);
        root.setCenter(carPane);

        //SCENE
        Scene scene = new Scene(root, 1500, 1000);

        primaryStage.setTitle("Self Driving Cars");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //==========================================================================================================================================
        //RACETRACK LINES AND SHAPES
        
        Rectangle startPoint = new Rectangle(0,0,200,100);
        startPoint.setTranslateX(-350);
        startPoint.setTranslateY(-350);
        startPoint.setStroke(Color.BLACK);
        startPoint.setFill(Color.TRANSPARENT);
        
        //First Line of the track
        Line line1 = new Line(0, 0, 0, 500);
        line1.setTranslateY(-150);
        line1.setTranslateX(-250);

        //Smaller arc on the first turn of the race track
        Arc firstTurn1 = new Arc(0, 0, 150, 200, 220, 100);
        firstTurn1.setType(ArcType.OPEN);
        firstTurn1.setStroke(Color.BLACK);
        firstTurn1.setFill(Color.TRANSPARENT);
        firstTurn1.setTranslateX(-134);
        firstTurn1.setTranslateY(137);

        //Bigger arc on the first turn of the race track
        Arc firstTurn2 = new Arc(0, 0, 411, 380, 220, 100);
        firstTurn2.setType(ArcType.OPEN);
        firstTurn2.setStroke(Color.BLACK);
        firstTurn2.setFill(Color.TRANSPARENT);
        firstTurn2.setTranslateX(-134);
        firstTurn2.setTranslateY(306);
        
        //Second Line of the track
        Line line2 = new Line(0, 0, 0, 400);
        line2.setTranslateX(-19);
        line2.setTranslateY(-100);
        
        //Smaller arc on the second turn of the race track
        Arc secondturn1 = new Arc(0, 0, 305, 270, 400, 100);
        secondturn1.setType(ArcType.OPEN);
        secondturn1.setStroke(Color.BLACK);
        secondturn1.setFill(Color.TRANSPARENT);
        secondturn1.setTranslateX(215);
        secondturn1.setTranslateY(-348);
        
        //Third Line of the track
        Line line3 = new Line(0, 0, 0, 436);
        line3.setTranslateX(181);
        line3.setTranslateY(19);
        
        //Bigger arc on the second turn of the race track
        Arc secondturn2 = new Arc(0, 0, 44, 40, 400, 100);
        secondturn2.setType(ArcType.OPEN);
        secondturn2.setStroke(Color.BLACK);
        secondturn2.setFill(Color.TRANSPARENT);
        secondturn2.setTranslateX(216);
        secondturn2.setTranslateY(-207);
        
        //Fourth Line of the track
        Line line4 = new Line(0, 0, 0, 598);
        line4.setTranslateX(249);
        line4.setTranslateY(100);
        
        Rectangle finishLine = new Rectangle(0,0,200,100);
        finishLine.setTranslateX(349);
        finishLine.setTranslateY(349);
        finishLine.setStroke(Color.BLACK);
        finishLine.setFill(Color.TRANSPARENT);

        //Adding lines of the track to an arrayList for collision purposes later on
        raceTrack.add(line1);
        raceTrack.add(line2);
        raceTrack.add(line3);
        raceTrack.add(line4);
        raceTrack.add(firstTurn1);
        raceTrack.add(firstTurn2);
        raceTrack.add(secondturn1);
        raceTrack.add(secondturn2);
        
        //ADDING THE SHAPES TO THE CAR PANE FOR MAKING THE RACE TRACK
        carPane.getChildren().addAll(startPoint, line1, firstTurn1, firstTurn2, line2, secondturn1, secondturn2, line3, line4, finishLine);
        //==========================================================================================================================================

        //TEXTFIELD EVENTS
        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        noCars.setOnKeyReleased(e -> {
            checkTextInputs(e, noCars, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        mutRate.setOnKeyReleased(e -> {
            checkTextInputs(e, mutRate, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        carSpeed.setOnKeyReleased(e -> {
            checkTextInputs(e, carSpeed, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        angVelocity.setOnKeyReleased(e -> {
            checkTextInputs(e, angVelocity, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //==========================================================================================================================================
        //START BUTTON EVENT
        //MAKE IT SO THAT IT CREATES NUMBER OF CARS WRITTEN IN THE noCars TEXTFIELD
        //ALSO CREATES THEM ONCE START BUTTON IS PRESSED, BUTTON GREYED OUT UNTIL RACE FINISHED OR RESET BUTTON CLICKED
        // Car car2 = new Car(5);
        //carPane.getChildren().addAll(car2.getBody());
        start.setOnAction(e -> {

            //Pause controls for program.
            if (pause) {

                pause = false;
                timer.start();

            } else if (!pause) {

                pause = true;
                timer.stop();

            }

            //Changes disable property of buttons accordingly...
            save.setDisable(false);
            reset.setDisable(false);
            start.setDisable(true);
            play.setDisable(false);

            //For loop to create as many cars as user inputted into text field.
            for (int i = 0; i < Integer.parseInt(noCars.getText()); i++) {
                // Create a car instance
                Car car = new Car(0, new Point2D(800, 800)); // Provide initial position

                // Set the position and color of the car's body
                car.getBody().setX(car.getPosition().getX());
                car.getBody().setY(car.getPosition().getY());
                car.getBody().setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));

                // Add the car's body to the Pane
                carPane.getChildren().add(car.getBody());

                // Add the sensor lines to the Pane
                for (Line sensor : car.sensorArray) {
                    sensor.setStroke(Color.RED);
                    carPane.getChildren().add(sensor);
                }

                carList.add(car);
            }
        });

        //==========================================================================================================================================
        //RESET BUTTON EVENT
        //It resets all of the textfields and greys out the buttons like how it was originally, it also resets the slider values and clears the list of cars in the arrayList for cars.
        reset.setOnAction(e -> {
            save.setDisable(true);
            start.setDisable(true);
            reset.setDisable(true);
            play.setDisable(true);
            noCars.clear();
            mutRate.clear();
            carSpeed.clear();
            angVelocity.clear();
            gen.clear();
            sliderRed.adjustValue(0);
            sliderGreen.adjustValue(0);
            sliderBlue.adjustValue(0);

            for (Car x:carList) {

                carPane.getChildren().remove(x.getBody());
                for (Line y:x.sensorArray) {carPane.getChildren().remove(y);}
            }

            carList.clear();
        });

    }

    public void checkTextInputs(KeyEvent e, TextField select, TextField noCars, TextField mutRate, TextField carSpeed, TextField angVelocity, Button save, Button start) {
        if (!String.valueOf(e.getCode()).contains("DIGIT")) {
            select.setText("");
        }
        if (!noCars.getText().isEmpty() && !mutRate.getText().isEmpty() && !carSpeed.getText().isEmpty() && !angVelocity.getText().isEmpty()) {
            save.setDisable(false);
            start.setDisable(false);
        } else {
            save.setDisable(true);
            start.setDisable(true);
        }
    }

    //IF CARS CRASHED EQUAL TO NUMBER OF CARS, DO SOMETHING
    public boolean checkCollisions(Car currCar) {

        for (Line x : bordersList) {

            if (currCar.getBody().getBoundsInParent().intersects(x.getBoundsInParent())) {
                if (carCrashed != carList.size()) {
                    carCrashed++;
                } else {
                    //START NEW GENERATION BECAUSE ALL CARS HAVE BEEN CRASHED
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class MyTimer extends AnimationTimer {

        @Override
        public void handle(long l) {

        }
    }

}
