/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author antho
 */
public class CarRaceView {
    
    private static final double SCENE_WIDTH = 1030.0;
    private static final double SCENE_HEIGHT = 550.0;

    private CarRaceModel model;

    private Scene slideshowScene;

    
    public CarRaceView(CarRaceModel model) {
        
        this.model = model;
        
        slideshowScene = createSlideshowScene();
    }
    
    private Scene createSlideshowScene() {
        

        Label greetingLabel = new Label("Welcome to the Grand Prix of JavaFX!");
        greetingLabel.setId("greeting-label");

        
        VBox root = new VBox(15, greetingLabel);
        
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setId("slideshow-root");

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        
        scene.getStylesheets().add(getClass().getResource("/styles.css").toString());
        
        return scene;
    }
    
        private VBox createSlide(Car car, int slideNumber) {
            
        // ALL MY PNGS FOR THE CAR DONT FORGET .PNG AFT
        
        Image image = new Image(getClass().getResource("/images/car" + slideNumber + ".png").toString());
        ImageView imageView = new ImageView(image);

        Label carLabel = new Label(car.getDescription() + " - " + car.getType());
        carLabel.getStyleClass().add("slide-label");

        VBox slide = new VBox(15, imageView, carLabel);
        slide.setAlignment(Pos.CENTER);

        return slide;
    }
    
    public Scene getSlideshowScene() {
        
        return slideshowScene;
    }
    
}


