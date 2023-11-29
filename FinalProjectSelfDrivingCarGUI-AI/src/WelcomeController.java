




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class WelcomeController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private Slider volumeSlider;
    static private MediaPlayer player;
    private Simulation simulation = new Simulation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //initializing the objects needed to play mp3 files
        //music files are stored in the music folder
        Media media = new Media(getClass().getResource("music/AISoundMusic.mp3").toExternalForm());
        //second media used when user clicks "start"
        Media media2 = new Media(getClass().getResource("music/AIBackgroundSound.mp3").toExternalForm());
        player = new MediaPlayer(media);
        //automatically play the sount track once the main menu screen appears
        player.setAutoPlay(true);
        //makes the music repeat infinitely
        player.setCycleCount(MediaPlayer.INDEFINITE);
        //sets volume of music to 50% when the main menu tab shows up
        player.setVolume(0.5);
        //Adjusts volume depending on value of slider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume((double) newValue);
        });

        //creates a highlight effect on the "Start" button 
        highlightButton(start);
        start.setOnAction((ActionEvent e) -> {
            //changes the root of the scene to direct the user to the racetrack
            //applies css code to the Interface scene
            simulation.getRoot().getStylesheets().add("style.css");
            //sets up the interface of the racetrack
            simulation.draw();
            //changes the root to change windows
            start.getScene().setRoot(simulation.getRoot());
            //makes it so that the volume slider works on the ractrack window
            changeVolume(simulation.getVolumeSlider(), media2);
        });

        //creates a highlight effect on the "Exit" button 
        highlightButton(exit);
        //closes the application upon pressing on the exit button
        exit.setOnAction(e -> {
            System.exit(0);
        });
    }

    static public MediaPlayer getPlayer() {
        return player;
    }
    

    public void changeVolume(Slider slider, Media media) {
        player.stop();
        //switches the music
        player = new MediaPlayer(media);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume((double) newValue);
        });
        player.play();
    }

    //style settings to make the buttons turn grey when mouse is hovering over it and goes back to normal when the mouse is no longer on top of it.
    public void highlightButton(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: grey");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color:  #f0f0f0");
        });
    }

}
