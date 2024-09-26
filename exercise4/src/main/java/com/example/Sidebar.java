package com.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sidebar {

    private VBox sidebar;
    private ChartContainer chartContainer;

    public Sidebar(Stage primaryStage, ChartContainer chartContainer) {
        this.chartContainer = chartContainer;
        this.sidebar = new VBox(15);
        sidebar.setPadding(new Insets(15));
        sidebar.setStyle("-fx-background-color: lightgreen;");
        sidebar.setMinWidth(150);

        Button homeButton = new Button("Home");
        Button settingsButton = new Button("Settings");

        Button showChartButton = new Button("Show Data");
        showChartButton.setOnAction(e -> chartContainer.showData());

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        sidebar.getChildren().addAll(homeButton, settingsButton, showChartButton, quitButton);
    }

    public VBox getSidebar() {
        return sidebar;
    }
}
