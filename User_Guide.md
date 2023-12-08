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
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/0dc1cfc7-7232-4ea7-99dd-b291659846d2)

In this text field, the user will need to input the number of cars they would like to create in the racetrack. 

***Warning: creating too many cars will result in slower performance due to Java only utilizing one core of the computer.***

## 2.2 ***RGB sliders***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/596d1f70-c56c-44ff-acf7-b314746696d3)

These sliders are used to change the color of the cars that will appear when the user starts the race. By changing these 3 sliders (Red, Green, Blue), the user will be able to obtain any color or shade that they desire. (The color that they create is be shown in the circle to the right of the sliders)

## 2.3 ***Place selection***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/24ed81c1-0965-436e-994c-183526bcf389)

For aesthetic purposes, our program also gives the user the freedom to change the location where the race will take place using a combo box, which contains the following options: Earth, Moon, or Mars.

*NOTE: changing the location of the race will have no effect on the race*

## 2.4 ***Mutation Rate***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/738edc14-6416-4e17-a32c-f34729f7ef1e)

This text field takes an input between 0 and 1. Every generation, the best car will give its neural network to all other cars except that a small mutation will happen in the values of the weights for each car's neural network except the best one. The mutation is random and the closer it is to one, the further the new value is from the original for each mutation. So a high mutation rate may show quicker progress in early generations, but a lower mutation rate will achieve more consistent results for all cars once it has learned the proper way of driving.

*Inputting a number that does not respect the range (0-1) will result in an alert pop-up prompting you to use a different value*

## 2.5 ***Car Speed and Angular Velocity***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/30c6f270-3a4c-4a50-92ed-27d2cb13eaf5)

These fields require the user to input the speed at which the cars travel (car speed) and turn (angular velocity). It is highly recommended to input values smaller than 10 due to problems with framerates caused by JavaFX.

*Note: Not limiting the speed and angular velocity of the cars was intentional. The reason for this is because we invite the user to push the program to its limits. If the cars go out of bounds, please consult the Troubleshooting section of this user guide.*

## 2.6 ***Sensor Length***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/9cd8dcbf-e596-47c2-be79-27c43c94c0e0)

Interacting with this slider will modify the length of the 7 sensors attached to each car. This will dictate the maximum distance that the cars can detect the walls.

## 2.7 ***Recommended Settings***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/f6c2184c-1da4-4334-9c37-4355b2be3837)

For user-friendly purposes, the program has an option to automatically input recommended values into the required fields upon pressing this button.

## 2.8 ***Save Settings***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/0591ab60-83ef-4d9f-b30d-470fa35169b8)

Although this button is called "Save settings", it serves as a confirmation button. Upon resetting the race, the inputs will need to be inputted once again.

## 2.9 ***Start Race***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/db0fff83-e087-466f-a1b0-bebfb42b02f3)

This button will be enabled once all the required text fields have been filled. Upon being pressed, the cars will appear and begin moving towards the finish line. The cars will continue respawning even after finishing the race in order to find the fastest route.

## 2.10 ***Reset***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/4a7802f2-001d-42ce-9642-2f813ce2d0bb)

The reser buttons resets all values entered by the user and displays all the data entered for this run of the simulation as well as the list of all the best fitness scores per generation in a new temporary window. the user can now reenter all inputs and run the simulation again.

## 2.11 ***Pause/Play***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/ab50c8ea-3c80-4891-afb7-cc7cab8ee30b)

This button will be enabled once the race has begun. Upon being pressed, all cars will cease to move until it is pressed again.

## 2.12 ***Exit***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/4ebde257-8a9c-467e-be41-8505c2c24ee3)

This button is used to close the program.

## 2.13 ***Generation***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/b8d90b95-3ada-4626-9b76-8fdae7f2ea1a)

This uneditable text field serves as a way for the user to keep track of the number of generations of cars (how many times the cars have all died and respawned) since the race has begun.

## 2.14  ***Sound***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/970c8232-383b-4408-aeb8-3cfa67505013)

This slider serves as a way to change the volume of the music playing in the background. Sliding it to the left will decrease the audio and sliding it to the right will increase it.

## 2.15 ***Neurons Per Layer***
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/73d6fac3-8c5e-402a-862c-97b213e827fe)

In this text field, the user can choose the number of hidden layers and the number of neurons for each specific hidden layer. The user simply has to separate the numbers by a comma. 

## 2.16 ***Neural Network***
![image](https://miro.medium.com/v2/resize:fit:720/format:webp/1*3fA77_mLNiJTSgZFhYnU0Q.png)
![image](https://github.com/youssefjango/SelfDrivingCar_AI_Project_Prog3/assets/145494945/ee6099a6-f242-4456-9a5d-f5720dfa25f0)

This program uses the theory of neural networks and deep learning. In our program, each car has a unique neural network that has an input layer of 7 neurons, an output layer of 2 neurons, and as many hidden layers as desired by the user. The user can even select the number of neurons for each specific hidden layer. At the end of each generation, when all the cars have crashed, the program will set up the next generation by cloning the best car's neural network to all the other cars. Then, all cars except the best one will undergo a small random mutation  that depends on significance based on the mutation rate. This will lead to certain evolution of the cars and eventually perfection of the cars' ability to drive.

If you would like to learn more, please click [here](https://www.ibm.com/topics/neural-networks).

<br/><br/><br/><br/><br/><br/><br/><br/>
# 3. ***Troubleshooting***
Due to less-than-optimal frame rates on the Netbeans IDE, the cars may potentially glitch out of the designated racing grounds if the user uses an excessively large speed or angular velocity. If this problem occurs, please press the reset button and use lower inputs or reboot the application. 

*If this problem remains unsolved, please contact our customer service team for further assistance.*
*Note: all text fields have automatic error detections, meaning they will not permit any illegal characters. For instance, if the user were to type in a letter into any of the text fields, they will receive an alert, which warns them about the problem and erases the input.*
