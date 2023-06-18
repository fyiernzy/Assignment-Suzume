package com.assignment.suzume.gui;

import java.util.List;

import com.assignment.suzume.game.ConsoleGameStatus;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.utils.MapUtils;
import com.assignment.suzume.utils.PathUtils;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MapGui extends Application {
    private static final double MAP_WIDTH = Screen.getPrimary().getVisualBounds().getWidth()/ 4.8;
    private static final double MAP_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight()- 176;

    private ConsoleGameStatus status;
    private int[][] map;
    private int npcRow; // NPC's current row
    private int npcCol; // NPC's current column
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer animationTimer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PixelMap mapOri = MapUtils.getCombinedMap();
        map = mapOri.getPixelMap();

        status = new ConsoleGameStatus(MapUtils.transformMap(map));

        primaryStage.setTitle("Suzume Map");

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Select Path");
        RadioMenuItem choice1Item = new RadioMenuItem("Path 1");
        RadioMenuItem choice2Item = new RadioMenuItem("Path 2");
        RadioMenuItem choice3Item = new RadioMenuItem("Path 3");
        RadioMenuItem choice4Item = new RadioMenuItem("Path 4");
        RadioMenuItem choice5Item = new RadioMenuItem("Path 5");
        RadioMenuItem choice6Item = new RadioMenuItem("Path 6");

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(choice1Item, choice2Item, choice3Item, choice4Item, choice5Item, choice6Item);

        menu.getItems().addAll(choice1Item, choice2Item, choice3Item, choice4Item, choice5Item, choice6Item);

        menuBar.getMenus().add(menu);

        Group root = new Group();
        VBox vbox = new VBox(menuBar, root);
        Scene scene = new Scene(vbox);

        // Adjust the dimensions of the canvas
        canvas = new Canvas(MAP_WIDTH, MAP_HEIGHT);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        drawMap(); // Draw the initial map

        primaryStage.setScene(scene);
        primaryStage.show();

        choice1Item.setOnAction(e -> handleButtonPress(0));
        choice2Item.setOnAction(e -> handleButtonPress(1));
        choice3Item.setOnAction(e -> handleButtonPress(2));
        choice4Item.setOnAction(e -> handleButtonPress(3));
        choice5Item.setOnAction(e -> handleButtonPress(4));
        choice6Item.setOnAction(e -> handleButtonPress(5));

    }

    private void drawMap() {
        // Define the size of each grid cell
        double cellWidth = 16;
        double cellHeight = 16;
        Image grass = new Image("static/images/tree.png");
        Image door = new Image("static/images/doors.png");
        Image brick = new Image("static/images/floor01.png");
        Image star = new Image("static/images/chest.png");
        Image suzume = new Image("static/images/boy.png");

        // Clear the canvas
        gc.clearRect(0, 0, MAP_WIDTH, MAP_HEIGHT);

        // Iterate over the map array and draw the elements based on their values
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int element = map[row][col];
                ImagePattern img;
                switch (element) {
                    case 1: // Obstacle
                        img = new ImagePattern(brick);
                        break;
                    case 2: // Station
                        img = new ImagePattern(door);
                        break;
                    case 3: // Destination
                        img = new ImagePattern(star);
                        break;
                    case 4: // NPC
                        img = new ImagePattern(suzume);
                        break;
                    default:
                        img = new ImagePattern(grass);
                        break;
                }

                // Calculate the position of the current grid cell
                double x = col * cellWidth;
                double y = row * cellHeight;

                // Fill the grid cell with the determined image
                gc.setFill(img);
                gc.fillRect(x, y, cellWidth, cellHeight);

                // If it's the NPC's position, draw the NPC image inside the cell
                if (row == npcRow && col == npcCol) {
                    gc.drawImage(suzume, x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    private void moveNPC(List<String> chosenPath) {
        int totalSteps = chosenPath.size();

        // Check if there are steps available for the NPC to move
        if (status.getCurrentStep() < totalSteps && status.isContinueJourney()) {
            // Update the NPC's position based on the next step
            status.updatePosition(chosenPath.get(status.getCurrentStep()));

            // Update the NPC's position variables
            npcRow = status.getCurrentRow();
            npcCol = status.getCurrentCol();

            // Get the JavaFX Application Thread to update the GUI
            Platform.runLater(this::drawMap);
        }
    }

    private void handleButtonPress(int pathIndex) {
        List<String> chosenPath = PathUtils.getShortestPath(pathIndex);

        status = new ConsoleGameStatus(MapUtils.transformMap(MapUtils.getCombinedMap().getPixelMap())); // Reset NPC's position to the starting point

        if(animationTimer != null){
            animationTimer.stop();
        }

        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            private final long interval = 300_000_000;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= interval) { // Update every 0.3 seconds
                    moveNPC(chosenPath); // Pass the chosen path as an argument
                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();
    }

} 
