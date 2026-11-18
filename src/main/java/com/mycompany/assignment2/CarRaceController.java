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
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;

/**
 *
 * @author antho
 */
public class CarRaceController {
    
    private static final double FADE_TIME = 800; // IN MILLISECONDS BOTH
    private static final double DISPLAY_TIME = 2500;
    private static final double WHEEL_TURN_TIME = 400;
    
    private CarRaceModel model;
    private CarRaceView view;
    private Stage stage;
    
    private ArrayList<TranslateTransition> carAnimations = new ArrayList<>();
    private ArrayList<RotateTransition> wheelAnimations = new ArrayList<>();
    
    private boolean raceStarted = false;

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
        
        view.getStartButton().setOnAction(e ->
        {
            startRace();
        });
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
        
        slideshow.setOnFinished(e ->
                {
            showRace();
        });

        slideshow.play();
    }
    
    private void showRace() {
        
        stage.setScene(view.getRaceScene());
    }
    
    private void createAnimations() {
        
        ArrayList<Car> cars = model.getCars();
        double distance = model.getRaceDistance();

        for (int i = 0; i < cars.size(); i++) {
            
            Car car = cars.get(i);
            Group carNode = view.getCarNodes().get(i);

            // TIME CAR WILL TAKE DEPENDING ON ITS ACT SPEED
            
            double seconds = distance / car.getSpeed();

            // MOVES CAR FROM START TO FINISH
            
            TranslateTransition move = new TranslateTransition(Duration.seconds(seconds), carNode);
            
            move.setFromX(0);
            move.setToX(distance);
            
            // SPIN BOTH WHEELS OF CAR
            
            RotateTransition backSpin = createWheelAnimation(view.getWheelNodes().get(i * 2));
            RotateTransition frontSpin = createWheelAnimation(view.getWheelNodes().get(i * 2 + 1));

            carAnimations.add(move);
    
            wheelAnimations.add(backSpin);
            wheelAnimations.add(frontSpin);
        }
    }
    
    private RotateTransition createWheelAnimation(Group wheel) {
        
        RotateTransition spin = new RotateTransition(new Duration(WHEEL_TURN_TIME), wheel);
        
        spin.setByAngle(360);
        spin.setCycleCount(Animation.INDEFINITE);

       
        spin.setInterpolator(Interpolator.LINEAR);
        return spin;
    }
    
    private void startRace() {
        
        if (!raceStarted) {
            
            createAnimations();
            raceStarted = true;
        }

        for (TranslateTransition move : carAnimations) {
            
            move.play();
        }
        
        for (RotateTransition spin : wheelAnimations) {
            
            spin.play();
        }

        view.showProgressMessage();
    }
    
}
