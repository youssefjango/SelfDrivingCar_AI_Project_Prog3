

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Simulation {

    private static Simulation singleSimulation;


    //TODO remove
    ArrayList<Shape> intersections = new ArrayList();

    ArrayList<Integer> fitnessList = new ArrayList();

    //creating a timer
    AnimationTimer timer = new MyTimer();
    BooleanProperty started = new SimpleBooleanProperty(false);
    boolean pause = true;


    //Track relateed shapes
    ArrayList<Shape> bordersList = new ArrayList();
    Shape borders;

    //creating a list of cars
    ArrayList<Car> carList = new ArrayList();
    ArrayList<Car> carListFull = new ArrayList();

    //creating Panes
    BorderPane root = new BorderPane();
    GridPane userInterface = new GridPane();
    Pane carPane = new Pane();


    //creating texfields
    TextField angVelocity = new TextField();
    TextField noCars = new TextField();
    TextField mutRate = new TextField();
    TextField carSpeed = new TextField();
    TextField gen = new TextField();

    //Creating buttons
    Button start = new Button("");
    Button save = new Button("");
    Button reset = new Button("");
    Button play = new Button("");
    Button exit = new Button("");

    Button recommended = new Button("Use Recommended Settings");

    //creating labels
    Label title;

    //creating a neural display
    NeuralDisplay neuralDisplay;
    //lastcar for neural display
    Car lastCar;


    //creating sliders for color selection
    Label redSlider = new Label("Red");
    Slider sliderRed = new Slider(0, 180, 10);
    Label greenSlider = new Label("Green");
    Slider sliderGreen = new Slider(0, 180, 10);
    Label blueSlider = new Label("Blue");
    Slider sliderBlue = new Slider(0, 255, 10);
    Rectangle colorSelector = new Rectangle(50, 50, Color.BLACK);
    static MediaPlayer player;
    Slider volumeSlider = new Slider(0, 1, 0.5);
    ImageView volumeSliderImage;

    ArrayList<Car> deadCars = new ArrayList<>();

    //instanciating menubar
    MenuBar menuBar;


    //instanciating layersoptions
    Label neuronsPerLayerLabel = new Label("Neurons per Layer:");
    TextField neuronsPerLayerTextField = new TextField();
    int[] layers;

    Simulation() {

    }

    void draw() {

        this.setUserInterface();
        this.setTextFieldNbCars();
        this.setColorSelector();
        this.setUpSliders();
        this.setColorBox();
        this.setTextFieldMutRate();
        this.setTextFieldCarSpeed();
        this.setTextFieldAngleVelocity();
        carPane.setId("pane");

        this.setButtons();
        this.setGenerationsOfCars();

        root.setLeft(userInterface);
        this.setTitle();

        this.setCarPane();
        this.setTrack();

        this.setCheckInputs();
        this.setupMenuBar();

        player = WelcomeController.getPlayer();

    }

    private void applySettings() {
        try {
            String[] neuronsPerLayerArray = neuronsPerLayerTextField.getText().split(",");
            int[] neuronsPerLayer = new int[neuronsPerLayerArray.length];

            // Convert the string values to integers
            for (int i = 0; i < neuronsPerLayerArray.length; i++) {
                neuronsPerLayer[i] = Integer.parseInt(neuronsPerLayerArray[i].trim());
            }

            layers = neuronsPerLayer;
        } catch (NumberFormatException ex) {
            // Handle the case where the input is not a valid integer
            System.err.println("Invalid input. Please enter valid integers for neurons per layer.");
        }
    }


    private void setupMenuBar() {
        menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        helpMenu.getItems().addAll(aboutMenuItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Set the menu bar to the top of the BorderPane
        root.setTop(menuBar);

        // Set actions for menu items
        saveMenuItem.setOnAction(e -> {
            // Handle save action
            if (!save.isDisable()) {
                save.arm();
                save.fire();
            } else {
                showAlert("Invalid Input", "Please enter a valid input for all textfields (the values should be numbers).");
            }
        });

        exitMenuItem.setOnAction(e -> {
            // Handle exit action
            System.exit(0);
        });

        aboutMenuItem.setOnAction(e -> showAboutWindow());

    }

    private void showAboutWindow() {
        // Create a new stage for the About window
        Stage aboutStage = new Stage();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setTitle("About");

        // Customize the instructions string
        String instructions = "First, enter the number of cars. \n";
        instructions += "Second, chose a mutation rate. Have it above 0 and under 1. \n";
        instructions += "Third, enter a velocity. \n";
        instructions += "Fourth, enter an angular velocity. \n";
        instructions += "Now, press the save button followed by the play button. \n\n";
        instructions += "NOTE: YOU CAN CLICK ON A CAR TO SHOW ITS NEURAL NETWORK!";

        // Create a label with the instructions
        Label label = new Label(instructions);
        label.setPadding(new Insets(10));

        // Set up the scene
        Scene scene = new Scene(new VBox(label), 500, 150);
        aboutStage.setScene(scene);

        // Show the About window
        aboutStage.show();
    }

    private void showEndWindow() {
        // Create a new stage for the End window
        Stage endStage = new Stage();
        endStage.initModality(Modality.APPLICATION_MODAL);
        endStage.setTitle("Ending Table");

        // Customize the ending string
        String endMessage = "Here is the list of all the fitness scores in order so you can see the evolution over time: \n";
        endMessage+= fitnessList + "\n";

        String endInputs = "Your inputs were: \n";
        endInputs+="Number of cars: " +noCars.getText()+"\n";
        endInputs+="Mutation rate: " +mutRate.getText()+"\n";
        endInputs+="Velocity: " +carSpeed.getText()+"\n";
        endInputs+="Angular Velocity: " +angVelocity.getText()+"\n";

        StringBuilder endLayers = new StringBuilder("Layers: 7 input layers + " + layers.length + " hidden layers + 2 output layers\n");

        int layerNumber = 1;
        for (int neurons : layers) {
            endLayers.append("Hidden layer ").append(layerNumber++).append(": ").append(neurons).append(" neurons\n");
        }
        endInputs+=endLayers.toString();
        endMessage+=endInputs;

        // Create a label with the instructions
        Label label = new Label(endMessage);
        label.setPadding(new Insets(10));

        // Set up the scene
        Scene scene = new Scene(new VBox(label), 550, 250);
        endStage.setScene(scene);

        // Show the About window
        endStage.show();
    }

    private void setCheckInputs() {
        //TEXTFIELD EVENTS
        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        noCars.setOnKeyReleased(e -> {
            checkTextInputs(e, noCars, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        mutRate.setOnKeyReleased(e -> {
            checkTextInputsDecimal(e, mutRate, noCars, mutRate, carSpeed, angVelocity, save, start);
            checkMutateValue();
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        carSpeed.setOnKeyReleased(e -> {
            checkTextInputs(e, carSpeed, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

        //CHECK IF INPUT IN TEXTFIELDS ARE NUMBERS (VALID) AND MAKES SURE ALL FIELDS HAVE TEXT BEFORE RUNNING PROGRAM
        angVelocity.setOnKeyReleased(e -> {
            checkTextInputs(e, angVelocity, noCars, mutRate, carSpeed, angVelocity, save, start);
        });
        neuronsPerLayerTextField.setOnKeyReleased(e -> {
            checkTextInputsComma(e, neuronsPerLayerTextField, noCars, mutRate, carSpeed, angVelocity, save, start);
        });

    }


    private void setTrack() {

        //==========================================================================================================================================
        //RACETRACK LINES AND SHAPES
        //Area where cars appear
        Rectangle startPoint = new Rectangle(0, 0, 200, 100);
        startPoint.setTranslateX(700);
        startPoint.setTranslateY(700);
        startPoint.setStroke(Color.BLACK);
        startPoint.setFill(Color.GREEN);

        //Line behind the starting point
        Line startWall = new Line(0, 800, 900, 800);

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
        //second part of the turn
        Line firstTurn2Part2 = new Line(200, 800, 430, 800);
        //third part of the turn
        Line firstTurn2Part3 = new Line(430, 800, 632, 664);


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
        //second part of the turn
        Line secondturn2Part2 = new Line(632, 0, 698, 0);
        //third part of the turn
        Line secondturn2Part3 = new Line(698, 0, 900, 100);


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

        //area where cars finish
        Rectangle finishLine = new Rectangle(0, 0, 200, 100);
        finishLine.setStroke(Color.BLACK);
        finishLine.setFill(Color.RED);

        //Makes a wall behind the finish line
        Line finishWall = new Line(0, 0, 900, 0);
        //ADDING THE SHAPES TO THE CAR PANE FOR MAKING THE RACE TRACK
        carPane.getChildren().addAll(startWall, startPoint, finishLine, lineLeft, line1, firstTurn1, firstTurn2Part1, firstTurn2Part2, firstTurn2Part3, line2, secondturn1, secondturn2Part1, secondturn2Part2, secondturn2Part3, line3, line4, lineRight, finishWall);
        //carPane.setStyle("-fx-background-image: url(\"/Images/grassBackground.jpg\");");
        bordersList.add(startWall);
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
        bordersList.add(finishWall);


        setBorders();

    }

    private void setBorders() {
        borders = Shape.union(bordersList.get(0), bordersList.get(1));
        for (int i = 2; i < bordersList.size(); i++) {
            borders = Shape.union(borders, bordersList.get(i));
        }
        carPane.getChildren().add(borders);
    }

    private void setCarPane() {
        //==========================================================================================================================================
        //SCENE AND PANES (creating pane and scene for the simulation)
        //CAR PANE
        //AREA WHERE CARS AND RACETRACK WILL BE SHOWN
        //carPane.setMaxWidth(900);
        //carPane.setMaxHeight(800);
        //carPane.setBackground(Background.fill(Color.CYAN));

        //carPane.setBorder(Border.stroke(Color.BLACK));
        root.setCenter(carPane);
    }

    private void setTitle() {
        title = new Label("Interface Visualizations");
        root.setTop(title);
        title.setTranslateX(400);
        title.setTranslateY(0);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
    }

    private void setGenerationsOfCars() {
        userInterface.add(new Label("Generation:"), 0, 10);
        gen.setText(0 + "");
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

        //recommended settings button
        userInterface.add(recommended,1,6);

        //VOLUME SLIDER
        volumeSliderImage = new ImageView(new Image("/Images/volume2.png") );
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

            timer.start();
            started.set(true);
            //Changes disable property of buttons accordingly...
            reset.setDisable(false);
            play.setDisable(false);
            start.setDisable(true);

            //For loop to create as many cars as user inputted into text field.
            for (int i = 0; i < Integer.parseInt(noCars.getText()); i++) {
                // Create a car instance
                Car car = new Car(new Point2D(800, 800), layers); // Provide initial position
                Car.setANGULAR_VELOCITY(Double.parseDouble(angVelocity.getText()));
                Car.setVELOCITY(Double.parseDouble(carSpeed.getText()));
                car.setFill(Color.rgb((int) sliderRed.getValue(), (int) sliderGreen.getValue(), (int) sliderBlue.getValue()));

                // Add the car's body to the Pane
                carPane.getChildren().add(car);
                for (Sensor sensor : car.getSensorArray()) {
                    carPane.getChildren().add(sensor);
                }


                carList.add(car);
                carListFull.add(car);
                car.setOnMouseClicked(mouseEvent -> {

                    lastCar = car;
                    showNeuralDisplay(car);


                });
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
            timer.stop();
            showEndWindow();
            save.setDisable(true);
            start.setDisable(true);
            play.setDisable(true);
            reset.setDisable(true);
            started.set(false);
            noCars.clear();
            mutRate.clear();
            carSpeed.clear();
            angVelocity.clear();
            neuronsPerLayerTextField.clear();
            carPane.getChildren().remove(neuralDisplay);
            gen.setText("0");
            sliderRed.adjustValue(0);
            sliderGreen.adjustValue(0);
            sliderBlue.adjustValue(0);
            System.out.println();
            for (Car x : carListFull) {

                carPane.getChildren().remove(x);
                for (Line y : x.getSensorArray()) {
                    carPane.getChildren().remove(y);
                }
            }
            carPane.getChildren().removeAll(intersections);

            intersections.clear();
            carListFull.clear();
            carList.clear();

        });

        //==========================================================================================================================================
        //PLAY/PAUSE BUTTON EVENT
        //Pauses the race until the use clicks the button once more.
        play.setOnAction(e -> {
            //Pause controls for program.
            if (pause) {
                play.setStyle("-fx-background-image: url(\"/Images/pausebutton.png\");");
                timer.stop();
                pause = false;
            } else {
                play.setStyle("-fx-background-image: url(\"/Images/playbutton.png\");");
                timer.start();
                pause = true;
            }
        });

        //==========================================================================================================================================
        //closes the application upon pressing on the exit button
        exit.setOnAction(e -> {
            System.exit(0);
        });

        userInterface.add(neuronsPerLayerLabel, 0, 12);
        userInterface.add(neuronsPerLayerTextField, 1, 12);
        save.setOnAction(actionEvent -> {
            applySettings();
            start.setDisable(false);
        });

        recommended.setOnAction(actionEvent -> {

            mutRate.setText("0.3");
            noCars.setText("5");
            angVelocity.setText("3");
            carSpeed.setText("3");
            neuronsPerLayerTextField.setText("4");
            save.setDisable(false);

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

    private void setTextFieldNbCars() {
        userInterface.add(new Label("Number Of Cars:"), 0, 0);
        noCars.setMaxWidth(50);
        noCars.setText("1");
        userInterface.add(noCars, 1, 0);
    }


    private void setTextFieldMutRate() {
        userInterface.add(new Label("Mutation Rate:"), 0, 2);
        mutRate.setMaxWidth(50);

        noCars.setText("1");
        userInterface.add(mutRate, 1, 2);
    }

    private void setTextFieldCarSpeed() {
        userInterface.add(new Label("Car Speed:"), 0, 3);
        carSpeed.setMaxWidth(50);
        noCars.setText("1");

        userInterface.add(carSpeed, 1, 3);
    }

    private void setTextFieldAngleVelocity() {
        userInterface.add(new Label("Angular Velocity:"), 0, 4);

        angVelocity.setMaxWidth(50);
        userInterface.add(angVelocity, 1, 4);
        noCars.setText("1");

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

    //public void setBorders(Line[] x ) {this.borders = x;}

    //style settings to make the buttons turn grey when mouse is hovering over it and goes back to normal when the mouse is no longer on top of it.
    public void highlightButton(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: white");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color:  #f0f0f0");
        });
    }

    class MyTimer extends AnimationTimer {

        @Override
        public void handle(long l) {
            carPane.getChildren().removeAll(intersections);
            intersections.clear();
            calculateSensorsLength();
            moveCar();
            checkCarCollision();
            removeDeadCars();
            addFitness();
            checkIfAllCarsDead();
            if (lastCar != null) {
                showNeuralDisplay(lastCar);
            }
        }

    }

    public void checkTextInputs(KeyEvent e, TextField select, TextField noCars, TextField mutRate, TextField carSpeed, TextField angVelocity, Button save, Button start) {
        if (!String.valueOf(e.getCode()).contains("DIGIT")) {
            select.setText("");
            showAlert("Invalid Input", "Please enter a valid input (the values should be numbers).");
        }
        if (!noCars.getText().isEmpty() && !mutRate.getText().isEmpty() && !carSpeed.getText().isEmpty() && !angVelocity.getText().isEmpty() && !neuronsPerLayerTextField.getText().isEmpty()) {
            save.setDisable(false);
        } else {
            save.setDisable(true);
        }
    }

    public void checkTextInputsDecimal(KeyEvent e, TextField select, TextField noCars, TextField mutRate, TextField carSpeed, TextField angVelocity, Button save, Button start) {
        if (!String.valueOf(e.getCode()).contains("DIGIT") && !String.valueOf(e.getCode()).contains("PERIOD")) {
            select.setText("");
        }
        if (!noCars.getText().isEmpty() && !mutRate.getText().isEmpty() && !carSpeed.getText().isEmpty() && !angVelocity.getText().isEmpty() && !neuronsPerLayerTextField.getText().isEmpty()) {
            save.setDisable(false);
        } else {
            save.setDisable(true);
        }


    }

    public void checkTextInputsComma(KeyEvent e, TextField select, TextField noCars, TextField mutRate, TextField carSpeed, TextField angVelocity, Button save, Button start) {
        if (!String.valueOf(e.getCode()).contains("DIGIT") && !String.valueOf(e.getCode()).contains("COMMA")) {
            select.setText("");
            showAlert("Invalid Input", "Please enter a valid input for the number of layers. Each numbers should be separated by a comma");

        }
        if (!noCars.getText().isEmpty() && !mutRate.getText().isEmpty() && !carSpeed.getText().isEmpty() && !angVelocity.getText().isEmpty() && !neuronsPerLayerTextField.getText().isEmpty()) {
            save.setDisable(false);
        } else {
            save.setDisable(true);
        }
    }

    private void checkMutateValue() {
        try {
            double value = Double.valueOf(mutRate.getText());

            if (value < 0 || value >= 1) {
                showAlert("Invalid Mutation Rate", "Please enter a value between 0 and 1.");
                mutRate.setText("");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid numeric value for Mutation Rate.");
            mutRate.setText("");

        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Show and wait for the user to acknowledge the alert
        alert.showAndWait();
    }

    public void addFitness() {

        for (Car car : carList) {
            car.setFitnessScore(car.getFitnessScore() + 1);

        }

    }

    private void checkCarCollision() {
        for (Car car : carList) {

            Shape intersection = Shape.intersect(car, borders);
            if (intersection.getBoundsInParent().getWidth() != -1) {
                deadCars.add(car);
                break;
            }

        }
    }

    private void checkIfAllCarsDead() {


        boolean allDead = true;
        for (Car car : carListFull) {

            if (!deadCars.contains(car)) {
                allDead = false;
            }

        }

        if (allDead) {
            setupNextGeneration();
        }

    }

    public void setupNextGeneration() {

        System.err.println("Generation " + gen.getText() + " finished");
        Car highestFitness = deadCars.getLast();
        fitnessList.add(highestFitness.getFitnessScore());

        gen.setText(Integer.valueOf(gen.getText()) + 1 + "");
        for (Car car : carListFull) {

            car.setRotate(90);
            car.setFitnessScore(0);
            car.setCenterX(800);
            car.setCenterY(700);
            for (Sensor sensor : car.getSensorArray()) {
                carPane.getChildren().add(sensor);
            }
            carList.add(car);
            if (car == highestFitness) {
                continue;
            }
            car.setBrain(highestFitness.getBrain().clone());

            car.getBrain().mutate();


        }

        deadCars.clear();

    }


    private void removeDeadCars() {
        for (Car car : deadCars) {
            for (Sensor sensor : car.getSensorArray()) {
                carPane.getChildren().remove(sensor);
            }
            carList.remove(car);
        }

    }

    private void calculateSensorsLength() {
        for (Car car : carList) {
            for (int i = 0; i < car.getSensorArray().length; i++) {
                double projectedLength = (checkCollisionWithWall(i, car));
                car.getSensorArray()[i].setProjectedLength(projectedLength);
            }
        }
    }

    private void moveCar() {
        int count = 0;
        for (Car car : carList) {

            double[] projectedLengths = new double[car.getSensorArray().length];

            for (int i = 0; i < projectedLengths.length; i++) {
                projectedLengths[i] = car.getSensorArray()[i].getProjectedLength();
            }
            double[] predictions = car.getBrain().predict(projectedLengths);

            car.update(predictions[0], predictions[1]); //the car will jsut go fowrard here

        }
    }

    private void showNeuralDisplay(Car car) {


        if (carPane.getChildren().contains(neuralDisplay)) {

            carPane.getChildren().remove(neuralDisplay);
            neuralDisplay = new NeuralDisplay(car);
            carPane.getChildren().add(neuralDisplay);
        } else {
            neuralDisplay = new NeuralDisplay(car);
            carPane.getChildren().add(neuralDisplay);
        }

    }


    public double checkCollisionWithWall(int order, Car car) {
        double distance = Double.MAX_VALUE; // Initialize with a large value
        Line sensor = car.getSensorArray()[order];
        Shape intersection = Shape.intersect(sensor, borders);
        intersection.setTranslateX(-userInterface.getWidth());
        intersection.setTranslateY(-title.getHeight() - menuBar.getHeight());
        if (intersection.getBoundsInParent().getWidth() != -1) {
            // Calculate corners of the bounding box
            Bounds bounds = intersection.getBoundsInParent();
            double[][] corners = {
                    {bounds.getMinX(), bounds.getMinY()},
                    {bounds.getMinX(), bounds.getMaxY()},
                    {bounds.getMaxX(), bounds.getMinY()},
                    {bounds.getMaxX(), bounds.getMaxY()}
            };

            // Find the closest corner
            double closestX = 0, closestY = 0;
            for (double[] corner : corners) {
                double cornerDistance = calculateDistanceToPoint(sensor, corner[0], corner[1]);
                if (cornerDistance < distance) {
                    distance = cornerDistance;
                    closestX = corner[0];
                    closestY = corner[1];
                }
            }

            // Draw the circle at the closest corner


            // Check if any collision was detected
            if (distance == Double.MAX_VALUE) {
                return 0;
            } else {
                Circle circle = new Circle(closestX, closestY, 5, Color.RED);
                intersections.add(circle);
                carPane.getChildren().add(circle);
                // Return the smallest distance
                return (Sensor.getLength() - distance) / Sensor.getLength();
            }
        }
        return 0;
    }

    // Method to calculate the distance from a sensor line to a point
    private double calculateDistanceToPoint(Line sensor, double x, double y) {
        // Get the center coordinates of the shapes
        double centerX1 = sensor.getStartX();
        double centerY1 = sensor.getStartY();

        double distance = Math.sqrt(Math.pow(x - centerX1, 2) + Math.pow(y - centerY1, 2));
        return distance;
    }

    public static Simulation getSimulationInstance() {
        if (singleSimulation == null) {
            singleSimulation = new Simulation();
        }
        return singleSimulation;
    }
}
