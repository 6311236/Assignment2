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
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

/**
 *
 * @author antho
 */
public class CarRaceView {
    
    private static final double SCENE_WIDTH = 1030.0;
    private static final double SCENE_HEIGHT = 550.0;
    
    private static final String[] PLACES = {"1st", "2nd", "3rd", "4th"};

    private CarRaceModel model;

    private Scene slideshowScene;
    
    private Scene raceScene;
    
    private ArrayList<VBox> slides = new ArrayList<>();
    
    private ArrayList<Group> carNodes = new ArrayList<>();
    
    private ArrayList<Group> wheelNodes = new ArrayList<>();
    
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    
    private Slider speedSlider;
    private Label speedLabel;
    
    private Label titleLabel;
    private Label messageLabel;
    private VBox resultsBox;
    private Label infoLabel;

    public CarRaceView(CarRaceModel model) {
        
        this.model = model;
        
        slideshowScene = createSlideshowScene();
        
        raceScene = createRaceScene();
        
    }
    
    private Scene createSlideshowScene() {
        

        Label greetingLabel = new Label("Welcome to the Grand Prix of JavaFX!");
        greetingLabel.setId("greeting-label");
        
        StackPane slidePane = new StackPane();
        slidePane.setPrefHeight(380);

        int slideNumber = 1;
        
        for (Car car : model.getCars()) {
            
            VBox slide = createSlide(car, slideNumber);
            slides.add(slide);
            slidePane.getChildren().add(slide);
            slideNumber++;
            
            slide.setOpacity(0);
        }

        
        VBox root = new VBox(15, greetingLabel, slidePane);
        
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
        
    private Scene createRaceScene() {
        
        HBox trackArea = new HBox(createBanner("START"), createTrack(), createBanner("FINISH"));

        
        HBox bottomArea = new HBox(15, createControlBox(), createMessageBox());

        VBox root = new VBox(15, trackArea, bottomArea);
        
        root.setPadding(new Insets(15));
        root.setId("race-root");

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toString());
        return scene;
    }
    
    private VBox createBanner(String word) {
        
        VBox banner = new VBox();
        
        banner.setAlignment(Pos.CENTER);
        banner.setPrefWidth(50);
        banner.getStyleClass().add("banner");

        for (int i = 0; i < word.length(); i++) {
            
            Label letter = new Label(String.valueOf(word.charAt(i)));
            letter.getStyleClass().add("banner-letter");
            banner.getChildren().add(letter);
        }

        return banner;
    }
        
    private Pane createTrack() {
        
        int numberOfLanes = model.getCars().size();
        double trackHeight = numberOfLanes * CarRaceModel.LANE_HEIGHT;

        Pane track = new Pane();
        track.setPrefSize(CarRaceModel.TRACK_WIDTH, trackHeight);

        
        Rectangle road = new Rectangle(0, 0, CarRaceModel.TRACK_WIDTH, trackHeight);
        road.setFill(Color.rgb(55, 58, 64)); // GREY FOR ROAD
        track.getChildren().add(road);
        
        for (int lane = 1; lane < numberOfLanes; lane++) {
            
            double y = lane * CarRaceModel.LANE_HEIGHT;

            for (double x = 10; x < CarRaceModel.TRACK_WIDTH; x += 40) {
                
                Line dash = new Line(x, y, x + 20, y);
                dash.setStroke(Color.WHITE);
                dash.setStrokeWidth(2);
                track.getChildren().add(dash);
            }
        }
        
        double startLineX = CarRaceModel.START_X + CarRaceModel.CAR_LENGTH + 4;
        
        Line startLine = new Line(startLineX, 0, startLineX, trackHeight);
        
        startLine.setStroke(Color.WHITE);
        startLine.setStrokeWidth(4);
        track.getChildren().add(startLine);

        
        Line finishLine = new Line(CarRaceModel.FINISH_X + 2, 0, CarRaceModel.FINISH_X + 2, trackHeight);
        
        finishLine.setStroke(Color.WHITE);
        finishLine.setStrokeWidth(4);
        track.getChildren().add(finishLine);
        
        for (Car car : model.getCars()) {
            
            Group carNode = createCar(car);
            carNodes.add(carNode);
            track.getChildren().add(carNode);
        }

        return track;
    }
    
    
    private Group createCar(Car car) {
        
        // BODY
        
        Polygon body = new Polygon(0.0, 26.0,    // the back, top
                                   28.0, 20.0,     // the back of the roof
                                   92.0, 20.0,    // the front of the roof
                                   114.0, 26.0,  // the hood
                                   120.0, 32.0,  // the front bumper, top
                                   120.0, 42.0,   // the front bumper, bottom
                                   0.0, 42.0);     // the back bumper, bottom
        
        body.setFill(car.getColor());
        body.setStroke(Color.BLACK);

        // TOP/ROOF
        
        Polygon roof = new Polygon(30.0, 20.0,
                                   42.0, 6.0,
                                   78.0, 6.0,
                                   92.0, 20.0);
        
        roof.setFill(car.getColor());
        roof.setStroke(Color.BLACK);
        
        Rectangle backWindow = new Rectangle(43, 9, 16, 11);
        backWindow.setFill(Color.LIGHTSKYBLUE);
        Rectangle frontWindow = new Rectangle(62, 9, 15, 11);
        frontWindow.setFill(Color.LIGHTSKYBLUE);

        // WHITE PLATE W CAR NUMBER
        
        Rectangle plate = new Rectangle(46, 24, 28, 15);
        plate.setFill(Color.WHITE);
        Text numberText = new Text(57, 36, String.valueOf(car.getNumber()));
        numberText.getStyleClass().add("car-number");
        
        // WHEELS OF THE CAR
        
        Group backWheel = createWheel(28, 42);
        Group frontWheel = createWheel(92, 42);
        wheelNodes.add(backWheel);
        wheelNodes.add(frontWheel);

        
        Group carNode = new Group(body, roof, backWindow, frontWindow, plate, numberText, backWheel, frontWheel);;
        
        carNode.setLayoutX(car.getX());
        carNode.setLayoutY(car.getY());

        return carNode;
    }
    
    private Group createWheel(double centerX, double centerY) {
        
        final double RADIUS = 10.0;

        Circle tire = new Circle(centerX, centerY, RADIUS);
        tire.setFill(Color.LIGHTGRAY);
        tire.setStroke(Color.rgb(30, 30, 30));
        tire.setStrokeWidth(4);

        Line spoke = new Line(centerX, centerY, centerX + RADIUS - 2, centerY);
        spoke.setStroke(Color.rgb(30, 30, 30));
        spoke.setStrokeWidth(2);

        return new Group(tire, spoke);
    }
    
    private VBox createControlBox() {
        
        startButton = new Button("Start");
        startButton.setId("start-button");
        startButton.setPrefSize(110, 38);
        
        pauseButton = new Button("Pause");
        pauseButton.setId("pause-button");
        pauseButton.setPrefSize(110, 38);
        
        resetButton = new Button("Reset");
        resetButton.setId("reset-button");
        resetButton.setPrefSize(110, 38);

        HBox buttonBox = new HBox(15, startButton, pauseButton, resetButton);
        
        Label sliderLabel = new Label("Race Speed:");
        speedSlider = new Slider(0.5, 3.0, 1.0);
        speedSlider.setPrefWidth(180);
        speedLabel = new Label("1.0x");
        speedLabel.setId("speed-label");

        HBox sliderBox = new HBox(10, sliderLabel, speedSlider, speedLabel);
        sliderBox.setAlignment(Pos.CENTER_LEFT);

        VBox controlBox = new VBox(20, buttonBox, sliderBox);
        
        controlBox.setPadding(new Insets(15));
        controlBox.setPrefWidth(440);
        controlBox.getStyleClass().add("panel");

        return controlBox;
    }
    
    private VBox createMessageBox() {
        
        titleLabel = new Label();
        titleLabel.setId("title-label");
        
        messageLabel = new Label();
        messageLabel.setId("message-label");
        
        resultsBox = new VBox(2);
        
        infoLabel = new Label();
        infoLabel.setId("info-label");
        infoLabel.setWrapText(true);

        VBox messageBox = new VBox(6, titleLabel, messageLabel, resultsBox, infoLabel);
        
        messageBox.setPadding(new Insets(12));
        messageBox.setPrefWidth(545);
        messageBox.setPrefHeight(230);
        messageBox.getStyleClass().add("panel");
        
        showReadyMessage();

        return messageBox;
    }
    
    public void showReadyMessage() {
        
        titleLabel.setText("Ready to Race!");
        messageLabel.setText("Press Start to begin the race.");
        resultsBox.getChildren().clear();
        infoLabel.setText("");
    }
    
    public void showProgressMessage() {
        
        titleLabel.setText("Race in Progress...");
        messageLabel.setText("The cars are racing to the finish line");
    }
    
    public void showResults(Car winner, ArrayList<Car> finishOrder) {
        
        titleLabel.setText("Race Over!");
        messageLabel.setText("Congratulations to " + winner.getDescription() + ", the winner of the race!");

        resultsBox.getChildren().clear();
        Label resultsTitle = new Label("Final Standings:");
        resultsTitle.getStyleClass().add("results-title");
        resultsBox.getChildren().add(resultsTitle);

        for (int i = 0; i < finishOrder.size(); i++) {
            
            Label resultLabel = new Label(PLACES[i] + "   " + finishOrder.get(i).getDescription());
            resultLabel.getStyleClass().add("result-label");
            resultsBox.getChildren().add(resultLabel);
        }

        infoLabel.setText("Animations used: TranslateTransition (cars), " + "RotateTransition (wheels), FadeTransition (slideshow).");
    }
    
    public Scene getSlideshowScene() {
        
        return slideshowScene;
    }
    
    public Scene getRaceScene() {
        
        return raceScene;
    }
    
    public ArrayList<Group> getCarNodes() {
        
        return carNodes;
    }
    
    public ArrayList<Group> getWheelNodes() {
        
        return wheelNodes;
    }
    
    public Button getStartButton() {
        
        return startButton;
    }
    
    public Button getPauseButton() {
        
        return pauseButton;
    }
    
    public Button getResetButton() {
        
        return resetButton;
    }
    
    public ArrayList<VBox> getSlides() {
        
        return slides;
    }
    
}


