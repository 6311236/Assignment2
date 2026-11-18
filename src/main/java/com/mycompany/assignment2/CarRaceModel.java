/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author antho
 */
public class CarRaceModel {
    
    // CONSTANTS USED FOR THE RACE TRACK
    
    public static final double TRACK_WIDTH = 900.0;
    public static final double LANE_HEIGHT = 64.0;
    public static final double CAR_LENGTH = 120.0;
    public static final double START_X = 15.0;
    public static final double FINISH_X = 870.0;

    // CONSTANTS FOR THE RANDOM SPEEDS IN PIXELS PER SEC
    
    private static final double MIN_SPEED = 90.0;
    private static final double MAX_SPEED = 150.0;

    private ArrayList<Car> cars = new ArrayList<>();
    
    private ArrayList<Car> finishOrder = new ArrayList<>();
    
    /**
     * Creates the model with 4 cars and each in its own lane. The colors match the pictures of the cars that were first shown in the slideshow
     */
    public CarRaceModel() {
        
        cars.add(new Car("Sedan", 1, Color.rgb(192, 40, 27), "Red", START_X, laneY(0)));
        cars.add(new Car("Coupe", 2, Color.rgb(30, 100, 215), "Blue", START_X, laneY(1)));
        cars.add(new Car("Hatchback", 3, Color.rgb(40, 160, 55), "Green", START_X, laneY(2)));
        cars.add(new Car("Sports Car", 4, Color.rgb(240, 195, 20), "Yellow", START_X, laneY(3)));

        // IMPORTANT TO RANDO EACH CARS SPEEDS EVERY TIME
        for (Car car : cars) {
            
            car.setSpeed(MIN_SPEED + Math.random() * (MAX_SPEED - MIN_SPEED));
        }
    }

    
    private double laneY(int lane) {
        
        return lane * LANE_HEIGHT + 6;
    }

    public double getRaceDistance() {
        
        return FINISH_X - START_X - CAR_LENGTH;
    }

    public void addFinisher(Car car) {
        
        finishOrder.add(car);
    }    

    public ArrayList<Car> getCars() {
        return cars;
    }
    
}
