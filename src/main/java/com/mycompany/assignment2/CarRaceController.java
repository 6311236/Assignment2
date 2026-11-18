/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class CarRaceController {
    
    private static final double FADE_TIME = 800; // IN MILLISECONDS BOTH
    private static final double DISPLAY_TIME = 2500;
    
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
    public CarRaceController(CarRaceModel model, CarRaceView view, Stage stage) {
        
        this.model = model;
        this.view = view;
        this.stage = stage;
    }
    
    public void startSlideshow() {
        
        stage.setScene(view.getSlideshowScene());

        //FADE TRANS
        
        SequentialTransition slideshow = new SequentialTransition();

        for (VBox slide : view.getSlides()) {
            
            FadeTransition fadeIn = new FadeTransition(new Duration(FADE_TIME), slide);
            
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            FadeTransition fadeOut = new FadeTransition(new Duration(FADE_TIME), slide);
            
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setDelay(new Duration(DISPLAY_TIME));

            slideshow.getChildren().addAll(fadeIn, fadeOut);
        }

        slideshow.play();
    }
    
}
