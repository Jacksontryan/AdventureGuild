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
import javafx.scene.paint.Paint;
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
        int mapWidth = 1500;
        int mapHeight = 1000;

        final Map[] map = {new Map(mapWidth, mapHeight, .3)};
        System.out.println("Map width: " + map[0].getWidth() + " height: " + map[0].getHeight());
        Canvas canvas = new Canvas(map[0].getWidth() + 100 ,map[0].getHeight() + 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        colorMap(gc, map[0]);
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
            map[0] = new Map(mapWidth, mapHeight, .3);
            canvas.setHeight(mapHeight);
            canvas.setWidth(mapWidth);
            pane.setMaxWidth(mapWidth);
            pane.setMinWidth(mapWidth);
            pane.setPrefWidth(mapWidth);
            pane.setPrefHeight(mapHeight);
            pane.setMinHeight(mapHeight);
            pane.setMaxHeight(mapHeight);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
            colorMap(gc, map[0]);
            ArrayList<Map.Point> coast = map[0].getCoast();
            /*for (int i = 0; i < coast.size(); i++) {
                gc.setFill(Color.PINK);
                gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
            }*/
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
            colorMap(gc, map[0]);
            ArrayList<Map.Point> coast = map[0].getCoast();
            /*for (int i = 0; i < coast.size(); i++) {
                gc.setFill(Color.PINK);
                gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
            }*/
        });

        /*ArrayList<Map.Point> coast = map[0].getCoast();
        for (int i = 0; i < coast.size(); i++) {
            gc.setFill(Color.PINK);
            gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
        }*/


    }

    private void colorMap(GraphicsContext gc, Map m) {
        char[][] map = m.getMap();
        double[][] grid = m.getGrid();
        double max = m.getMax();
        double heightGroup1 = max - (max * .3);
        double heightGroup2 = max - (max * .4);
        double heightGroup3 = max - (max * .5);
        double heightGroup4 = max - (max * .6);
        double heightGroup5 = max - (max * .7);
        double heightGroup6 = max - (max * .8);
        double heightGroup7 = max - (max * .9);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]=='l'){
                    Color color = new Color(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue(), Color.GREEN.getOpacity());

                    double value = grid[i][j];
                    if(value > heightGroup1) {
                        color = new Color(color.getRed(), color.getGreen() * .4, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup2) {
                        color = new Color(color.getRed(), color.getGreen() * .45, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup3) {
                        color = new Color(color.getRed(), color.getGreen() * .5, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup4) {
                        color = new Color(color.getRed(), color.getGreen() * .55, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup5) {
                        color = new Color(color.getRed(), color.getGreen() * .6, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup6) {
                        color = new Color(color.getRed(), color.getGreen() * .65, color.getBlue(), color.getOpacity());
                    }else if(value > heightGroup7) {
                        color = new Color(color.getRed(), color.getGreen() * .7, color.getBlue(), color.getOpacity());
                    }else{
                        color = new Color(color.getRed(), color.getGreen() * .75, color.getBlue(), color.getOpacity());
                    }

                    gc.setFill(color);
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
                }else if(map[i][j]=='r'){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i, j, 1, 1);
                }
            }
        }
    }


}
