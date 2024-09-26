package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ExerciseApplication extends Application {

    private ChartContainer chartContainer;
    private Sidebar sidebar;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 600);

        chartContainer = new ChartContainer();
        root.setCenter(chartContainer.getChartContainer());

        sidebar = new Sidebar(primaryStage, chartContainer);
        root.setLeft(sidebar.getSidebar());

        primaryStage.setTitle("Tekwan fitness");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
