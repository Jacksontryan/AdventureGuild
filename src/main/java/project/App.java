package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project.Map.Map;
import project.Map.Map.Point;
import project.Map.Map.City;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane pane = new AnchorPane();
        final double[] scale = {1.0};

        int canvasWidth = 1500;
        int canvasHeight = 1000;

        pane.setMaxHeight(600);
        pane.setMinHeight(600);
        pane.setPrefHeight(600);
        pane.setPrefWidth(900);
        pane.setMaxWidth(900);
        pane.setMinWidth(900);

        Scene scene = new Scene(pane);

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

        scene.addEventFilter(ScrollEvent.ANY, event ->{
            if(event.getDeltaY() > 0){
                scale[0] *= 1.5;
            }else{
                scale[0] /= 1.5;
            }
            pane.setScaleX(scale[0]);
            pane.setScaleY(scale[0]);
            event.consume();
        });

        primaryStage.setScene(scene);
        Canvas canvas = new Canvas();

        pane.getChildren().add(canvas);
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        final Map[] pn = {new Map(canvasWidth, canvasHeight, .3)};

        Button btn = new Button();
        btn.setLayoutX(0);
        btn.setLayoutY(0);
        btn.setText("Click me");
        pane.getChildren().add(btn);
        final char[][][] land = {pn[0].getMap()};
//        System.out.println(land.length + " " + land[0].length +  " " + land[0][0].length);

        Button coastButton = new Button();
        coastButton.setLayoutX(0);
        coastButton.setLayoutY(320);
        coastButton.setText("Coast");
        pane.getChildren().add(coastButton);
        coastButton.setOnAction(event -> {
            ArrayList<Point> coast = pn[0].getCoast();
            for(int i = 0; i < coast.size(); i++){
                gc.setFill(Color.PINK);
                gc.fillRect(coast.get(i).getX(), coast.get(i).getY(), 1, 1);
            }
        });

        Button trimButton = new Button("Trim");
        trimButton.setLayoutX(0);
        trimButton.setLayoutY(240);
        pane.getChildren().add(trimButton);
        trimButton.setOnAction(event -> {
            pn[0].trimMap();
            land[0] = pn[0].getMap();
            int width = land[0].length;
            int height = land[0][0].length;
            canvas.setWidth(width);
            canvas.setHeight(height);
            colorLand(gc, land[0]);
        });

        Button citiesButton = new Button();
        citiesButton.setLayoutX(0);
        citiesButton.setLayoutY(40);
        citiesButton.setText("Cities");
        pane.getChildren().add(citiesButton);

        Button saveButton = new Button();
        saveButton.setLayoutX(0);
        saveButton.setLayoutY(200);
        saveButton.setText("Save");
        pane.getChildren().add(saveButton);
        saveButton.setOnAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pn[0].saveMap();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        Button thresholdButton = new Button();
        thresholdButton.setLayoutX(0);
        thresholdButton.setLayoutY(80);
        thresholdButton.setText("Threshold");
        pane.getChildren().add(thresholdButton);

        thresholdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pn[0].setThreshold(pn[0].getThreshold()+.01);
                //System.out.println(pn.getThreshold());
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }
        });

        Button lakeKiller = new Button();
        lakeKiller.setLayoutX(0);
        lakeKiller.setLayoutY(160);
        lakeKiller.setText("WaterRegion");
        pane.getChildren().add(lakeKiller);
        lakeKiller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pn[0].fillAllLakes();
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }
        });

        Button newMapButton = new Button("New Map");
        newMapButton.setLayoutX(0);
        newMapButton.setLayoutY(280);
        newMapButton.setText("New Map");
        pane.getChildren().add(newMapButton);
        newMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pn[0] = new Map(canvasWidth, canvasHeight, .3);
                canvas.setWidth(canvasWidth);
                canvas.setHeight(canvasHeight);
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }
        });

        Button islandKiller = new Button();
        pane.getChildren().add(islandKiller);
        islandKiller.setLayoutX(0);
        islandKiller.setLayoutY(120);
        islandKiller.setText("Island");
        islandKiller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pn[0].fillAllIslands();
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }
        });

        final ArrayList<City>[] cities = new ArrayList[]{pn[0].getCities()};

        citiesButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(!pn[0].getCities().isEmpty()){
                    return;
                }
                System.out.println("Cities");
                pn[0].addCities(20);
                cities[0] = pn[0].getCities();
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }
        });

        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

        });

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println("Button clicked");
                pn[0].blur(1);
                land[0] = pn[0].getMap();
                colorLand(gc, land[0]);
            }

        });

        colorLand(gc, land[0]);
        primaryStage.show();
    }

    private void colorLand(GraphicsContext gc, char[][] land) {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if(land[i][j]=='l'){
                    gc.setFill(Color.GREEN);
                    gc.fillRect(i, j, 1, 1);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(0.1);
                    gc.strokeRect(i, j, 1, 1);
                }else if(land[i][j]=='w'){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i, j, 1, 1);
                }else if(land[i][j]=='c'){
                    gc.setFill(Color.RED);
                    gc.fillRect(i, j, 1, 1);
                }
            }
        }
    }
}
