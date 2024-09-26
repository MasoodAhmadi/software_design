package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ChartContainer {

    private AreaChart<Number, Number> chart;
    private CheckBox stepMarkCheckBox;
    private CheckBox caloriesMarkCheckBox;
    private VBox chartContainer;

    // Data for today (cumulative)
    private final int[] todaySteps = { 0, 20, 80, 300, 411, 550, 790, 932, 1074 };
    private final int[] todayCalories = { 75, 155, 300, 430, 511, 698, 785, 867, 915 };
    private final String[] todayTime = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00" };

    // Data for this week (totals)
    private final int[] weekSteps = { 6500, 11025, 7450, 7923, 10002, 4999, 1074 };
    private final int[] weekCalories = { 2000, 3140, 2300, 2560, 2990, 1780, 470 };
    private final String[] weekDays = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

    public ChartContainer() {
        // Chart initialization
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        chart = new AreaChart<>(xAxis, yAxis);

        // Checkboxes for Steps and Calories
        stepMarkCheckBox = new CheckBox("Step");
        caloriesMarkCheckBox = new CheckBox("Calories");

        // VBox to center the chart and place checkboxes under it
        chartContainer = new VBox(15);
        chartContainer.setAlignment(Pos.CENTER);
        chartContainer.setPadding(new Insets(15));

        VBox checkBoxContainer = new VBox(10);
        checkBoxContainer.setAlignment(Pos.CENTER);
        checkBoxContainer.getChildren().addAll(stepMarkCheckBox, caloriesMarkCheckBox);

        chartContainer.getChildren().addAll(chart, checkBoxContainer);
    }

    public VBox getChartContainer() {
        return chartContainer;
    }

    public void showData() {
        // Clear previous data
        chart.getData().clear();

        // Display today's or this week's data based on user checkbox selections
        if (stepMarkCheckBox.isSelected()) {
            XYChart.Series<Number, Number> stepsSeries = new XYChart.Series<>();
            stepsSeries.setName("Steps");

            // Add data points (example: showing today's data)
            for (int i = 0; i < todaySteps.length; i++) {
                stepsSeries.getData().add(new XYChart.Data<>(i + 1, todaySteps[i]));
            }

            chart.getData().add(stepsSeries);
        }

        if (caloriesMarkCheckBox.isSelected()) {
            XYChart.Series<Number, Number> caloriesSeries = new XYChart.Series<>();
            caloriesSeries.setName("Calories");

            // Add data points (example: showing today's data)
            for (int i = 0; i < todayCalories.length; i++) {
                caloriesSeries.getData().add(new XYChart.Data<>(i + 1, todayCalories[i]));
            }

            chart.getData().add(caloriesSeries);
        }
    }
}
