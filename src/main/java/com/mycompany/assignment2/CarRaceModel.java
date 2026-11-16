/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import java.util.ArrayList;

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

    public ArrayList<Car> getCars() {
        return cars;
    }
    
}
