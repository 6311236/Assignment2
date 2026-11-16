/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import javafx.scene.paint.Color;

/**
 *
 * @author antho
 */
public class Car {
    
    private String type;
    
    private int number;
    
    private double x;
    private double y;
    private double speed;      // ILL HAVE SPEED in pixels per second
    
    private Color color;
    private String colorName;
    
    public Car (String type, int number, Color color, String colorName, double x, double y) {
        
        this.type = type;
        this.number = number;
        this.color = color;
        this.colorName = colorName;
        this.x = x;
        this.y = y;
        this.speed = 0;
    }
    
    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    /**
     * used to return a brief description of the car so like "Car #45 (Red)".
     * @return the description of the car
     */
    public String getDescription() {
        
        return "Car #" + number + " (" + colorName + ")";
    }
}
