package com.example.demo2;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;

public class Car {


    private Rectangle body;
    private double velocity;
    private double angularVelocity;
    private double fitnessScore;
    public Line[] sensorArray;

    private Point2D position; // Current position of the car
    private double angle;     // Current angle of the car
    private boolean alive;    // A flag to indicate if the car is still alive

    final private int carWidth = 20;
   final private int carLength = 80;
    public Car(double angularVelocity, Point2D initialPosition){

        this.body = new Rectangle(carLength, carWidth);
        this.angularVelocity = angularVelocity;
        this.fitnessScore = 0;
        this.velocity = 5;

        this.position = initialPosition;
        this.angle = 0;
        this.alive = true;

        this.sensorArray = new Line[5]; // Initialize the sensor array with 5 Line objects
        for (int i = 0; i < 5; i++) {
            sensorArray[i] = new Line(); // Initialize each sensor Line
        }

// Initialize the sensor array and position them in a cone pattern in front of the car
        double sensorRadius = 60; // Adjust the radius as needed
        double coneAngle = 90;    // Angle of the cone
        int numSensors = 5;       // Number of sensors

        // Initialize the sensor array
        this.sensorArray = new Line[numSensors];

        // Calculate the starting angle of the cone
        double startAngle = angle - coneAngle / 2;

        // Calculate the angle increment for each sensor
        double angleIncrement = coneAngle / (numSensors - 1);

        for (int i = -2; i < numSensors-2; i++) {

            //double sensorAngle = startAngle + i * angleIncrement;
            double sensorX = position.getX() + sensorRadius * Math.cos(-i * Math.PI/5);
            double sensorY = position.getY() + sensorRadius * Math.sin(-i * Math.PI/5);

            sensorArray[i+2] = new Line();
            sensorArray[i+2].setStartX(position.getX());
            sensorArray[i+2].setStartY(position.getY());
            sensorArray[i+2].setEndX(sensorX);
            sensorArray[i+2].setEndY(sensorY);

            sensorArray[i+2].setTranslateX(body.getTranslateX()+body.getWidth()/2);

            double x = ((double)1/2) * (sensorArray[i+2].getEndX()-sensorArray[i+2].getStartX());


            double y = ((double)1/2) * (sensorArray[i+2].getEndY()-sensorArray[i+2].getStartY());

            sensorArray[i+2].setTranslateX(sensorArray[i+2].getTranslateX()+x-5);
            sensorArray[i+2].setTranslateY(sensorArray[i+2].getTranslateY()+y);
        }
    }

    public Rectangle getBody() {

        return this.body;

    }

    public double getVELOCITY() {

        return this.velocity;
    }


    public double getVelocity() {
        return this.velocity;
    }

    public double getAngularVelocity() {
        return this.angularVelocity;
    }

    public double getFitnessScore() {
        return this.fitnessScore;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public double getAngle() {
        return this.angle;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void update() {
        if (alive) {
            // Update the car's position and angle based on its velocity and angular velocity
            double deltaX = velocity * Math.cos(Math.toRadians(angle));
            double deltaY = velocity * Math.sin(Math.toRadians(angle));
            position = position.add(deltaX, deltaY);
            angle += angularVelocity;

            // Check for collision or boundary conditions, update alive accordingly
            if (isCollidingWithObstacle() || isOutOfBoundary()) {
                alive = false;
            }

            // Update the fitness score (you can define the scoring mechanism)
            fitnessScore += 1; // For example, increase the score on each update
        }
    }

    private boolean isCollidingWithObstacle() {
        // Implement collision detection logic here
        // You may need to check if any part of the car's body is colliding with an obstacle
        return false; // Replace with your collision detection code
    }

    private boolean isOutOfBoundary() {
        // Implement boundary check logic here
        // Check if the car has gone out of the valid boundary
        return false; // Replace with your boundary check code
    }


}


