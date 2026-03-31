package com.example.sqlassist.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import com.example.sqlassist.models.Category;
import com.example.sqlassist.table.CategoryTable;
import com.example.sqlassist.table.ItemTable;

import java.util.ArrayList;

public class StatisticsTab extends Tab {

    private static StatisticsTab instance;
    private PieChart chart;

    private StatisticsTab() {
        this.setText("Statistics");

        BorderPane root = new BorderPane();

        chart = new PieChart();
        chart.setTitle("Items By Category");
        chart.setLabelsVisible(true);

        Button refresh = new Button("Refresh");
        refresh.setOnAction(e -> {
            generateChart();
        });

        generateChart();

        root.setCenter(chart);
        root.setBottom(refresh);

        this.setContent(root);
    }

    public void generateChart() {
        ItemTable itemTable = ItemTable.getInstance();
        CategoryTable categoryTable = CategoryTable.getInstance();

        ArrayList<Category> categories = categoryTable.getAllCategories();
        ArrayList<PieChart.Data> data = new ArrayList<>();

        for (Category category : categories) {
            int count = itemTable.getItemCount(category.getId());

            data.add(new PieChart.Data(category.getName() + "("+count+")", count));
        }

        ObservableList<PieChart.Data> chartData =
                FXCollections.observableArrayList(data);

        chart.setData(chartData);
    }

    public static StatisticsTab getInstance() {
        if (instance == null) {
            instance = new StatisticsTab();
        }
        return instance;
    }
}