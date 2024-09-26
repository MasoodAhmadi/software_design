package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExerciseApplication extends Application {

    private AreaChart<Number, Number> chart;
    private ComboBox<String> deviceGroupComboBox;
    private ComboBox<String> dataTypeComboBox;
    private ComboBox<String> timeframeComboBox;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Sidebar for navigation (set background color to light green and width)
        VBox sidebar = new VBox(15); // Increase spacing between buttons to 15
        sidebar.setPadding(new Insets(15)); // Padding inside the sidebar
        sidebar.setStyle("-fx-background-color: lightgreen;"); // Light green background
        sidebar.setMinWidth(150); // Set a wider sidebar width

        // Sidebar buttons
        Button homeButton = new Button("Home");
        Button settingsButton = new Button("Settings");

        // Quit button added to the sidebar
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        // Add buttons to the sidebar (Quit button after Settings)
        sidebar.getChildren().addAll(homeButton, settingsButton, quitButton);

        // Add the sidebar to the left of the BorderPane
        root.setLeft(sidebar);

        // Define pages to switch between
        HBox homePage = createHomePage();
        VBox settingsPage = createSettingsPage();

        // Chart container
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        chart = new AreaChart<>(xAxis, yAxis);

        // Create a VBox to center the chart and place buttons under it
        VBox chartContainer = new VBox(15); // Spacing between chart and buttons
        chartContainer.setAlignment(Pos.CENTER); // Center the content
        chartContainer.setPadding(new Insets(15));

        CheckBox stepMarkCheckBox = new CheckBox("Step");
        CheckBox caloriesMarkCheckBox = new CheckBox("Calories");

        VBox checkBoxContainer = new VBox(10);
        checkBoxContainer.setAlignment(Pos.CENTER);
        checkBoxContainer.getChildren().addAll(stepMarkCheckBox, caloriesMarkCheckBox);

        chartContainer.getChildren().addAll(chart, checkBoxContainer);

        root.setCenter(chartContainer);

        homeButton.setOnAction(e -> root.setCenter(homePage));
        settingsButton.setOnAction(e -> root.setCenter(settingsPage));

        primaryStage.setTitle("Exercise Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHomePage() {
        HBox homePage = new HBox();
        homePage.setPadding(new Insets(20));
        Button homeContent = new Button("Welcome to Home Page!");
        homePage.getChildren().add(homeContent);
        return homePage;
    }

    private VBox createSettingsPage() {
        VBox settingsPage = new VBox(10);
        settingsPage.setPadding(new Insets(20));
        Button settingsContent = new Button("Settings Page");
        settingsPage.getChildren().add(settingsContent);
        return settingsPage;
    }

    private void uploadData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" ");
                    double x = Double.parseDouble(data[0]);
                    double y = Double.parseDouble(data[1]);
                    series.getData().add(new XYChart.Data<>(x, y));
                }
                chart.getData().clear();
                chart.getData().add(series);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
