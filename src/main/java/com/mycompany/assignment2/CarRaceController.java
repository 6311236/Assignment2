/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import javafx.stage.Stage;

/**
 *
 * @author antho
 */
public class CarRaceController {
    
    private CarRaceModel model;
    private CarRaceView view;
    private Stage stage;

    /**
     * Creates the controller and registers all the event handlers.
     *
     * @param model the model of the race
     * @param view  the view of the race
     * @param stage the window of the application
     */
    public CarRaceController(CarRaceModel model, CarRaceView view, Stage stage)
    {
        this.model = model;
        this.view = view;
        this.stage = stage;
    }
    
}
