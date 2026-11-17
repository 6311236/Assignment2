package com.mycompany.assignment2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        CarRaceModel model = new CarRaceModel();
        CarRaceView view = new CarRaceView(model);

        
        stage.setTitle("Car Race Simulation");
        stage.setScene(view.getSlideshowScene());
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}