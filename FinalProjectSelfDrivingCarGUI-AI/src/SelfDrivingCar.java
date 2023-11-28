
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @author 2278304
 */
public class SelfDrivingCar extends Application {


    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Label title = new Label("Interface Visualizations");


        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene menu = new Scene(root);
        
        primaryStage.setTitle("Self Driving Cars");
        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
