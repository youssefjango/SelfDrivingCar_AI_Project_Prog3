/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author 2278304
 */
public class Visuals extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      Label title = new Label("Interface Visualizations");
        
        
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
        
        
        Button save = new Button("Save Settings:");
        userInterface.add(save, 0, 5);
        
        Button start = new Button("Start Race:");
        userInterface.add(start, 0, 6);
        
        Button reset =new Button("Reset Race:");
        userInterface.add(reset, 0, 7);
        
        Button play = new Button("Pause / Play:");
        userInterface.add(play, 0, 8);
        
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
        
        
        
        Scene scene = new Scene(root, 800, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
   
    
}
