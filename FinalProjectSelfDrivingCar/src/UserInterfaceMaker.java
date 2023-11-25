
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author YOUSSEF
 */
class UserInterfaceMaker {

    private Pane root;
    private TextField noCars;
    private TextField mutRate;
    private TextField carSpeed;
    private TextField angVelocity;
    private Button save;
    private Button start;

    UserInterfaceMaker(int mode) {
        if (mode == 1) {
            Label title = new Label("THE AI CAR MODEL");
            title.setId("title");
            GridPane userInterface = new GridPane();
            userInterface.setAlignment(Pos.CENTER_LEFT);
            userInterface.setHgap(10);
            userInterface.setVgap(5);
            userInterface.setPadding(new Insets(10, 10, 10, 10));

            userInterface.add(new Label("Number Of Cars:"), 0, 0);
            this.noCars = new TextField();
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
            this.mutRate = new TextField();
            mutRate.setMaxWidth(50);
            userInterface.add(mutRate, 1, 2);

            //CAR SPEED
            userInterface.add(new Label("Car Speed:"), 0, 3);
            this.carSpeed = new TextField();
            carSpeed.setMaxWidth(50);
            userInterface.add(carSpeed, 1, 3);

            //ANGULAR VELOCITY
            userInterface.add(new Label("Angular Velocity:"), 0, 4);
            this.angVelocity = new TextField();
            angVelocity.setMaxWidth(50);
            userInterface.add(angVelocity, 1, 4);

            //SAVE BUTTON
            this.save = new Button("Save Settings");
            save.setDisable(true);
            userInterface.add(save, 0, 6);

            //START BUTTON
            this.start = new Button("Start Race");
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
            this.root = new BorderPane();
            ((BorderPane) root).setLeft(userInterface);
            ((BorderPane) root).setTop(title);
            title.setTranslateX(400);
            title.setTranslateY(10);
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        }
    }

    public Pane getRoot() {
        return root;
    }

    public void textFieldChecker() {
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
    }

    private void checkTextInputs(KeyEvent e, TextField select, TextField noCars, TextField mutRate, TextField carSpeed, TextField angVelocity, Button save, Button start) {
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

}
