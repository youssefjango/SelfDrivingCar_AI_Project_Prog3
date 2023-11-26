package InterfaceComponents;

import RacetrackAndActors.Car;

import ArtificialIntelligenceComponents.Sensor;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

public class Interface {

    public Car car = new Car(3, new Point2D(800, 800));

    //creating a timer
    AnimationTimer timer = new MyTimer();
    boolean pause = true;

    //Track relateed shapes
    ArrayList<Shape> bordersList = new ArrayList();
    ArrayList<Shape> raceTrack = new ArrayList();

    //creating a list of cars
    ArrayList<Car> carList = new ArrayList();

    //creating Panes
    BorderPane root = new BorderPane();
    GridPane userInterface = new GridPane();
    public Pane carPane = new Pane();

    //Line[] borders;
    //creating texfields
    TextField angVelocity = new TextField();
    TextField noCars = new TextField();
    TextField mutRate = new TextField();
    TextField carSpeed = new TextField();
    TextField gen = new TextField();

    //Creating buttons
    Button start = new Button();
    Button save = new Button("Save Settings");
    Button reset = new Button();
    Button play = new Button();
    Button exit = new Button();

    //creating sliders for color selection
    Label redSlider = new Label("Red");
    Slider sliderRed = new Slider(0, 180, 0);
    Label greenSlider = new Label("Green");
    Slider sliderGreen = new Slider(0, 180, 0);
    Label blueSlider = new Label("Blue");
    Slider sliderBlue = new Slider(0, 255, 0);
    Rectangle colorSelector = new Rectangle(50, 50, Color.BLACK);
    MediaPlayer player;
    Slider volumeSlider = new Slider(0,1,0.5);
    ImageView volumeSliderImage;

    public void draw() {

        this.setUserInterface();
        this.setTextFieldNbCars();
        this.setColorSelector();
        this.setUpSliders();
        this.setColorBox();
        this.setTextFieldMutRate();
        this.setTextFieldCarSpeed();
        this.setTextFieldAngleVelocity();

        this.setButtons();
        this.setGenerationsOfCars();

        root.setLeft(userInterface);
        this.setTitle();

        this.setCarPane();
        this.setTrack();

        this.setCheckInputs();

    }

    private void setCheckInputs() {
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

    }

    private void setTrack() {

        //==========================================================================================================================================
        //RACETRACK LINES AND SHAPES
        Rectangle startPoint = new Rectangle(0, 0, 200, 100);

        startPoint.setStroke(Color.BLACK);
        startPoint.setFill(Color.RED);

        //Left wall line of the track
        Line lineLeft = new Line(0, 0, 0, 800);

        //First Line of the track
        Line line1 = new Line(0, 0, 0, 500);
        line1.setTranslateX(200);

        //Smaller arc on the first turn of the race track
        Arc firstTurn1 = new Arc(0, 0, 150, 200, 220, 100);
        firstTurn1.setType(ArcType.OPEN);
        firstTurn1.setStroke(Color.BLACK);
        firstTurn1.setFill(Color.TRANSPARENT);
        firstTurn1.setTranslateX(315);
        firstTurn1.setTranslateY(372);

        //Bigger turn on the first turn of the race track
        //CANNOT USE ANOTHER ARC BECAUSE IT HAS INVISIBLE COLLISIONS, NOT ONLY ON THE VISIBLE PART OF THE ARC.
        //first part of the turn
        Line firstTurn2Part1 = new Line(0, 664, 200, 800);
        firstTurn2Part1.setStroke(Color.BLACK);
        firstTurn2Part1.setFill(Color.TRANSPARENT);
        //second part of the turn
        Line firstTurn2Part2 = new Line(200, 800, 430, 800);
        firstTurn2Part1.setStroke(Color.BLACK);
        firstTurn2Part1.setFill(Color.TRANSPARENT);
        //third part of the turn
        Line firstTurn2Part3 = new Line(430, 800, 632, 664);
        firstTurn2Part3.setStroke(Color.BLACK);
        firstTurn2Part3.setFill(Color.TRANSPARENT);
        
        //Second Line of the track
        Line line2 = new Line(0, 0, 0, 400);
        line2.setTranslateX(430);
        line2.setTranslateY(100);

        //Smaller arc on the second turn of the race track
        Arc secondturn1 = new Arc(0, 0, 44, 40, 400, 100);
        secondturn1.setType(ArcType.OPEN);
        secondturn1.setStroke(Color.BLACK);
        secondturn1.setFill(Color.TRANSPARENT);
        secondturn1.setTranslateX(666);
        secondturn1.setTranslateY(253);

        //Bigger arc on the second turn of the race track
        //CANNOT USE ANOTHER ARC BECAUSE IT HAS INVISIBLE COLLISIONS, NOT ONLY ON THE VISIBLE PART OF THE ARC.
        //first part of the turn
        Line secondturn2Part1 = new Line(430, 100, 632, 0);
        secondturn2Part1.setStroke(Color.BLACK);
        secondturn2Part1.setFill(Color.TRANSPARENT);
        //second part of the turn
        Line secondturn2Part2 = new Line(632, 0, 698, 0);
        secondturn2Part2.setStroke(Color.BLACK);
        secondturn2Part2.setFill(Color.TRANSPARENT);
        //third part of the turn
        Line secondturn2Part3 = new Line(698, 0, 900, 100);
        secondturn2Part3.setStroke(Color.BLACK);
        secondturn2Part3.setFill(Color.TRANSPARENT);
        
        //Third Line of the track
        Line line3 = new Line(0, 0, 0, 436);
        line3.setTranslateX(632);
        line3.setTranslateY(228);

        //Fourth Line of the track
        Line line4 = new Line(0, 0, 0, 570);
        line4.setTranslateX(700);
        line4.setTranslateY(228);

        //Right wall line of the track
        Line lineRight = new Line(0, 0, 0, 800);
        lineRight.setTranslateX(900);

        Rectangle finishLine = new Rectangle(0, 0, 200, 100);
        finishLine.setTranslateX(700);
        finishLine.setTranslateY(700);
        finishLine.setStroke(Color.BLACK);
        finishLine.setFill(Color.GREEN);

        //Adding lines of the track to an arrayList for collision purposes later on
        raceTrack.add(lineLeft);
        raceTrack.add(line1);
        raceTrack.add(line2);
        raceTrack.add(line3);
        raceTrack.add(line4);
        raceTrack.add(firstTurn1);
        raceTrack.add(firstTurn2Part1);
        raceTrack.add(firstTurn2Part2);
        raceTrack.add(firstTurn2Part3);
        raceTrack.add(secondturn1);
        raceTrack.add(secondturn2Part1);
        raceTrack.add(secondturn2Part2);
        raceTrack.add(secondturn2Part3);
        raceTrack.add(lineRight);
        for (Shape borderTrack : raceTrack) {
            borderTrack.setStrokeWidth(15);

        }

        //ADDING THE SHAPES TO THE CAR PANE FOR MAKING THE RACE TRACK
        carPane.getChildren().addAll(startPoint, finishLine, lineLeft, line1, firstTurn1, firstTurn2Part1, firstTurn2Part2, firstTurn2Part3, line2, secondturn1, secondturn2Part1, secondturn2Part2, secondturn2Part3, line3, line4, lineRight);
        bordersList.add(lineLeft);
        bordersList.add(line1);
        bordersList.add(line2);
        bordersList.add(line3);
        bordersList.add(line4);
        bordersList.add(firstTurn1);
        bordersList.add(firstTurn2Part1);
        bordersList.add(firstTurn2Part2);
        bordersList.add(firstTurn2Part3);
        bordersList.add(secondturn1);
        bordersList.add(secondturn2Part1);
        bordersList.add(secondturn2Part2);
        bordersList.add(secondturn2Part3);
        bordersList.add(lineRight);

    }

    private void setCarPane() {
        //==========================================================================================================================================
        //SCENE AND PANES (creating pane and scene for the simulation)
        //CAR PANE
        //AREA WHERE CARS AND RACETRACK WILL BE SHOWN
        carPane.setMaxWidth(900);
        carPane.setMaxHeight(800);
        carPane.setBackground(Background.fill(Color.CYAN));
        carPane.setId("pane");
        carPane.setBorder(Border.stroke(Color.BLACK));
        root.setCenter(carPane);
    }

    private void setTitle() {
        Label title = new Label("Interface Visualizations");
        root.setTop(title);
        title.setTranslateX(400);
        title.setTranslateY(10);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
    }

    private void setGenerationsOfCars() {
        userInterface.add(new Label("Generation:"), 0, 10);
        gen.setMaxWidth(50);
        gen.setEditable(false);
        userInterface.add(gen, 1, 10);
    }

    private void setButtons() {

        //SAVE BUTTON
        save.setDisable(true);
        highlightButton(save);
        userInterface.add(save, 0, 6);

        //START BUTTON
        start.setDisable(true);
        userInterface.add(start, 0, 7);
        start.setMinHeight(100);
        start.setMinWidth(100);
        start.setStyle("-fx-background-image: url(\"/Images/start.jpg\");");

        //RESET BUTTON
        reset.setDisable(true);
        userInterface.add(reset, 0, 8);
        reset.setMinHeight(50);
        reset.setMaxWidth(50);
        reset.setStyle("-fx-background-image: url(\"/Images/reset.png\");");
        
        //PLAY AND PAUSE BUTTON
        play.setDisable(true);
        userInterface.add(play, 0, 9);
        play.setMinHeight(100);
        play.setMinWidth(100);
        play.setStyle("-fx-background-image: url(\"/Images/playbutton.png\");");
        
        //EXIT BUTTON
        userInterface.add(exit, 1, 9);
        exit.setMinHeight(100);
        exit.setMinWidth(100);
        exit.setStyle("-fx-background-image: url(\"/Images/exit.png\");");
        
        //VOLUME SLIDER
        volumeSliderImage = new ImageView(new Image("/Images/volume2.png"));
        userInterface.add(volumeSliderImage, 0, 11);
        userInterface.add(volumeSlider, 1, 11);
        volumeSlider.setTranslateX(-120);
        
        //START BUTTON EVENT
        //MAKE IT SO THAT IT CREATES NUMBER OF CARS WRITTEN IN THE noCars TEXTFIELD
        //ALSO CREATES THEM ONCE START BUTTON IS PRESSED, BUTTON GREYED OUT UNTIL RACE FINISHED OR RESET BUTTON CLICKED
        // Car car2 = new Car(5);
        //carPane.getChildren().addAll(car2.getBody());
        //==========================================================================================================================================
        start.setOnAction(e -> {

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
                car.setCenterX(car.getPosition().getX());
                car.setCenterY(car.getPosition().getY());
                car.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));

                // Add the car's body to the Pane
                carPane.getChildren().add(car);
                for (Sensor sensor : car.getSensorArray()) {
                    carPane.getChildren().add(sensor);
                }

                carList.add(car);
            }
        });
        //make button change image when hovered over with mouse
        start.setOnMouseEntered(e -> {
            start.setStyle("-fx-background-image: url(\"/Images/start2.jpg\");");
        });
        start.setOnMouseExited(e -> {
            start.setStyle("-fx-background-image: url(\"/Images/start.jpg\");");
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

            for (Car x : carList) {

                carPane.getChildren().remove(x);
                for (Line y : x.getSensorArray()) {
                    carPane.getChildren().remove(y);
                }
            }

            carList.clear();
        });
        //==========================================================================================================================================
        //PLAY/PAUSE BUTTON EVENT
        //Pauses the race until the use clicks the button once more. 
        play.setOnAction(e->{
            //Pause controls for program.
            if (pause) {
                play.setStyle("-fx-background-image: url(\"/Images/pausebutton.png\");");
                pause=false;
            }
            else{
            play.setStyle("-fx-background-image: url(\"/Images/playbutton.png\");");
            pause=true;}
        });
        //==========================================================================================================================================
        //closes the application upon pressing on the exit button
        exit.setOnAction(e -> {
            System.exit(0);
        });
    }

    
    private void setColorBox() {
        VBox colorBox = new VBox(5, redSlider, sliderRed, greenSlider, sliderGreen, blueSlider, sliderBlue);
        userInterface.add(colorBox, 0, 1);
    }

    private void setColorSelector() {
        userInterface.add(colorSelector, 1, 1);
        userInterface.setVgap(10);
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    
    private void setUpSliders() {

        //RED SLIDER
        sliderRed.setMinWidth(150);
        sliderRed.setMajorTickUnit(10);
        sliderRed.setMinorTickCount(9);
        sliderRed.setBlockIncrement(1);

        //GREEN SLIDER
        sliderGreen.setMinWidth(150);
        sliderGreen.setMajorTickUnit(10);
        sliderGreen.setMinorTickCount(9);
        sliderGreen.setBlockIncrement(1);

        //BLUE SLIDER
        sliderBlue.setMinWidth(150);
        sliderBlue.setMajorTickUnit(10);
        sliderBlue.setMinorTickCount(9);
        sliderBlue.setBlockIncrement(1);
        
        //VOLUME SLIDER
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                player.setVolume((double) newValue);
                System.out.println(player.getVolume());
        });
        

        //setting for sliders
        sliderRed.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            colorSelector.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });
        sliderGreen.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            colorSelector.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });
        sliderBlue.valueProperty().addListener((observeable, oldvalue, newvalue) -> {
            colorSelector.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));
        });
        
    }

    private void setUserInterface() {
        userInterface.setAlignment(Pos.CENTER_LEFT);
        userInterface.setHgap(10);
        userInterface.setVgap(5);
        userInterface.setPadding(new Insets(10, 10, 10, 10));
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

    private void setTextFieldNbCars() {
        userInterface.add(new Label("Number Of Cars:"), 0, 0);
        noCars.setMaxWidth(50);
        userInterface.add(noCars, 1, 0);
    }

    private void setTextFieldMutRate() {
        userInterface.add(new Label("Mutation Rate:"), 0, 2);
        mutRate.setMaxWidth(50);
        userInterface.add(mutRate, 1, 2);
    }

    private void setTextFieldCarSpeed() {
        userInterface.add(new Label("Car Speed:"), 0, 3);
        carSpeed.setMaxWidth(50);
        userInterface.add(carSpeed, 1, 3);
    }

    private void setTextFieldAngleVelocity() {
        userInterface.add(new Label("Angular Velocity:"), 0, 4);

        angVelocity.setMaxWidth(50);
        userInterface.add(angVelocity, 1, 4);
        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        angVelocity.setOnKeyReleased(e -> {
            checkTextInputs(e, angVelocity, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    
    public BorderPane getRoot() {
        root.setStyle("-fx-background-color:grey");
        return root;
    }

    public ArrayList<Shape> getBordersList() {
        return bordersList;
    }
    
    //style settings to make the buttons turn grey when mouse is hovering over it and goes back to normal when the mouse is no longer on top of it.
    public void highlightButton(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: white");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color:  #f0f0f0");
        });
    }

    //public void setBorders(Line[] x ) {this.borders = x;}
    class MyTimer extends AnimationTimer {

        @Override
        public void handle(long l) {

        }
    }

}
