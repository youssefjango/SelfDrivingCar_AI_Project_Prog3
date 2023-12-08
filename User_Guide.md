![Capture d’écran (27)](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/76130774/73d24b47-7307-49f3-8751-4380a45d43d7)
# User Guide


## Table of Contents
### 1  : [Project Description](#1-project-description)

### 2 : [Program Functionality](#2-program-functionality)
- 2.1 [***Number of Cars**](#21-number-of-cars)
- 2.2 [***RGB sliders***](#22-rgb-sliders)
- 2.4 [***Mutation Rate***](#24-mutation-rate)
- 2.5 [***Car Speed and Angular Velocity***](#25-car-speed-and-angular-velocity)
- 2.6 [***Sensor Length***](#26-sensor-length)
- 2.7 [***Recommended Settings***](#27-recommended-settings)
- 2.8 [***Save Settings***](#28-save-settings)
- 2.9 [***Start Race***](#29-start-race)
- 2.10 [***Reset***](#210-reset)
- 2.11 [***Pause***](#211-pauseplay)
- 2.12 [***Exit***](#212-exit)
- 2.13 [***Generation***](#213-generation)
- 2.14 [***Sound***](#214-sound)
- 2.15 [***Neurons Per Layer***](#215-neurons-per-layer)
- 2.16 [***NeuroNetwork***](#216-neuronetwork)
### 3 : [Troubleshooting](#3-troubleshooting)
---
<br/><br/><br/><br/><br/><br/><br/><br/>
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/76130774/76ab5701-0e81-4280-b5ee-487c63973f4d)
<br/><br/>
# 1. ***Project Description***
With the use of concepts such as neural networks and machine-based learning, our program creates customizable and autonomous cars that are tasked with completing a race track by speeding up or slowing down along the way in order to make turns and/or move around obstacles in its way. Whenever these vehicles collide with the walls, they will restart from the beginning and learn from their previous mistakes, made possible with the use of mathematical algorithms associated with linear algebra and mechanics motion formulae. 


<br/><br/><br/><br/><br/><br/><br/><br/>
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/76130774/3e8b9452-96c5-423b-91d4-dc0efed5f14f)
<br/><br/>
# 2. ***Program Functionality***
Upon pressing the start button on the main menu, the user will gain access to the simulation tab where they will need to interact with a series of buttons, sliders, and text fields in order to make the program run.

## 2.1 ***Number of Cars**
In this text field, the user will need to input the number of cars they would like to create in the racetrack. 

***Warning: creating too many cars will result in slower performance due to Java only utilizing one core of the computer.***

## 2.2 ***RGB sliders***
These sliders are used to change the color of the cars that will appear when the user starts the race. By changing these 3 sliders (Red, Green, Blue), the user will be able to obtain any color or shade that they desire. (The color that they create is be shown in the circle to the right of the sliders)

## 2.3 ***Place selection***
For aesthetic purposes, our program also gives the user the freedom to change the location where the race will take place using a combo box, which contains the following options: Earth, Moon, or Mars.

*NOTE: changing the location of the race will have no effect on the race*

## 2.4 ***Mutation Rate***
This texfield takes an input between 0 and 1. Every generations, the best car will give its neural network to all other cars except that a small mutation will happen in the values of the weights for each car's neural network except the best one. The mutation is random and the closer it is to one, the further the new value is from the original for each mutation. So a high mutation rate may show quicker progress in early generations, but a lower mutation rate will achieve more consistent results for all cars once it has learned the proper way of driving.

*Inputting a number that does not respect the range (0-1) will result in an alert pop-up prompting you to use a different value*

## 2.5 ***Car Speed and Angular Velocity***
These fields require the user to input the speed at which the cars travel (car speed) and turn (angular velocity). It is highly recommended to input values smaller than 10 due to problems with framerates caused by JavaFX.

*Note: Not limiting the speed and angular velocity of the cars was intentional. The reason for this is because we invite the user to push the program to its limits. If the cars go out of bounds, please consult the Troubleshooting section of this user guide.*

## 2.6 ***Sensor Length***
Interacting with this slider will modify the length of the 7 sensors attached to each car. This will dictate the maximum distance that the cars can detect the walls.

## 2.7 ***Recommended Settings***
For user-friendly purposes, the program has an option to automatically input recommended values into the required fields upon pressing this button.

## 2.8 ***Save Settings***
Although this button is called "Save settings", it serves as a confirmation button. Upon resetting the race, the inputs will need to be inputted once again.

## 2.9 ***Start Race***
This button will be enabled once all the required text fields have been filled. Upon being pressed, the cars will appear and begin moving towards the finish line. The cars will continue respawning even after finishing the race in order to find the fastest route.

## 2.10 ***Reset***
The reser buttons resets all values entered by the user and displays all the data entered for this run of the simulation as well as the list of all the best fitness scores per generation in a new temporary window. the user can now reenter all inputs and run the simulation again.

## 2.11 ***Pause/Play***
This button will be enabled once the race has begun. Upon being pressed, all cars will cease to move until it is pressed again.

## 2.12 ***Exit***
This button is used to close the program.

## 2.13 ***Generation***
This uneditable text field serves as a way for the user to keep track of the number of generations of cars (how many times the cars have all died and respawned) since the race has begun.

## 2.14  ***Sound***
This slider serves as a way to change the volume of the music playing in the background. Sliding it to the left will decrease the audio and sliding it to the right will increase it.

## 2.15 ***Neurons Per Layer***
In this textfield, the user can chose the number of hidden layers and the number of neurons for each specific hidden layer. The user simply has to separate the numbers by a comma. 

## 2.16 ***Neural Network***
![image](https://miro.medium.com/v2/resize:fit:720/format:webp/1*3fA77_mLNiJTSgZFhYnU0Q.png)

This program uses the theory of neural network and deep-learning. In our program, each car has a unique neural network that has input layer of 7 neurons, an output layer of 2 neurons, and as many hidden layers desired by the user. The user can even select the number of neurons for each specific hidden layer. At the end of each generation, when all the cars have crashes, the program will setup
the next generation by cloning the best car's neural network to all the other cars. Then, all cars except the best one will undergo a small random mutation  that depends in significance based on the mutation rate. This will lead to certain evolution of the cars and eventually perfection of the cars' ability to drive.

If you would like to learn more, please click [here](https://www.ibm.com/topics/neural-networks).

<br/><br/><br/><br/><br/><br/><br/><br/>
# 3. ***Troubleshooting***
Due to less-than-optimal frame rates on the Netbeans IDE, the cars may potentially glitch out of the designated racing grounds if the user uses an excessively large speed or angular velocity. If this problem occurs, please press the reset button and use lower inputs or reboot the application. 

*If this problem remains unsolved, please contact our customer service team for further assistance.*
