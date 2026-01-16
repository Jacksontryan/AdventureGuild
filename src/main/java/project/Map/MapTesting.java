package project.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MapTesting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final double[] scale = {1.0};
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MapTesting");
        primaryStage.show();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        final Map[] map = {new Map(1500, 1000, .3)};
        Canvas canvas = new Canvas(1500,1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        colorMap(gc, map[0].getMap());
        pane.getChildren().add(canvas);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.Z) {
                    scale[0] *= 1.5;
                    pane.setScaleX(scale[0]);
                    pane.setScaleY(scale[0]);
                }else if (keyEvent.getCode() == KeyCode.X) {
                    scale[0] /= 1.5;
                    pane.setScaleX(scale[0]);
                    pane.setScaleY(scale[0]);
                }else if (keyEvent.getCode() == KeyCode.UP) {
                    pane.setLayoutY(pane.getLayoutY() + 25);
                }else if (keyEvent.getCode() == KeyCode.DOWN) {
                    pane.setLayoutY(pane.getLayoutY() - 25);
                }else if (keyEvent.getCode() == KeyCode.LEFT) {
                    pane.setLayoutX(pane.getLayoutX() + 25);
                }else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    pane.setLayoutX(pane.getLayoutX() - 25);
                }
                keyEvent.consume();
            }
        });

        Button newMapButton = new Button("New Map");
        pane.getChildren().add(newMapButton);
        newMapButton.setLayoutX(0);
        newMapButton.setLayoutY(0);
        newMapButton.setOnMouseClicked((event) -> {
            map[0] = new Map(1500, 1000, .3);
            canvas.setHeight(1000);
            canvas.setWidth(1500);
            pane.setMaxWidth(1500);
            pane.setMinWidth(1500);
            pane.setPrefWidth(1500);
            pane.setPrefHeight(1000);
            pane.setMinHeight(1000);
            pane.setMaxHeight(1000);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
            colorMap(gc, map[0].getMap());
            ArrayList<Map.Point> coast = map[0].getCoast();
            for (int i = 0; i < coast.size(); i++) {
                gc.setFill(Color.PINK);
                gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
            }
        });

        Button trimButton = new Button("Trim");
        pane.getChildren().add(trimButton);
        trimButton.setLayoutX(0);
        trimButton.setLayoutY(40);
        trimButton.setOnMouseClicked((event) -> {
            map[0].trimMap();
            canvas.setHeight(map[0].getMap()[0].length);
            canvas.setWidth(map[0].getMap().length);
            pane.setMaxWidth(map[0].getMap().length);
            pane.setMinWidth(map[0].getMap().length);
            pane.setPrefWidth(map[0].getMap().length);
            pane.setMinHeight(map[0].getMap()[0].length);
            pane.setMaxHeight(map[0].getMap()[0].length);
            pane.setPrefHeight(map[0].getMap()[0].length);
            primaryStage.setHeight(map[0].getMap()[0].length);
            primaryStage.setWidth(map[0].getMap().length);
            colorMap(gc, map[0].getMap());
            ArrayList<Map.Point> coast = map[0].getCoast();
            for (int i = 0; i < coast.size(); i++) {
                gc.setFill(Color.PINK);
                gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
            }
        });

        ArrayList<Map.Point> coast = map[0].getCoast();
        for (int i = 0; i < coast.size(); i++) {
            gc.setFill(Color.PINK);
            gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
        }


    }

    private void colorMap(GraphicsContext gc, char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]=='l'){
                    gc.setFill(Color.FORESTGREEN);
                    gc.fillRect(i, j, 1, 1);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(0.1);
                    gc.strokeRect(i, j, 1, 1);
                }else if(map[i][j]=='h'){
                    gc.setFill(Color.DARKGREEN);
                    gc.fillRect(i, j, 1, 1);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(0.1);
                    gc.strokeRect(i, j, 1, 1);
                }else if(map[i][j]=='o') {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(i, j, 1, 1);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(0.1);
                    gc.strokeRect(i, j, 1, 1);
                }else if(map[i][j]=='w'){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i, j, 1, 1);
                }else if(map[i][j]=='m'){
                    gc.setFill(Color.RED);
                    gc.fillRect(i, j, 1, 1);
                } else if (map[i][j] == 'p') {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(i, j, 1, 1);
                }
            }
        }
    }
}
