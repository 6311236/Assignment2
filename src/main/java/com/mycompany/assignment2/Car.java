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
}
