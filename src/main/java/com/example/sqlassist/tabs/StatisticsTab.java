package com.example.sqlassist.tabs;

import com.example.sqlassist.utils.Animations;
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

        //Hover animation
        Animations.addHoverScale(refresh);

        //bounce animation
        refresh.setOnMouseClicked(e -> Animations.clickBounce(refresh));

        refresh.setOnAction(e -> {
            generateChart();
        });

        generateChart();

        root.setCenter(chart);
        root.setBottom(refresh);
        root.setCenter(refresh);

        this.setContent(root);

        //Animation tab
        this.setOnSelectionChanged(e-> {
            if(this.isSelected()){
                Animations.fadeIn(this.getContent(), 250);
                Animations.slideIn(this.getContent(), 250);
            }
        });
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

        //Animation on refresh chart
        Animations.fadeIn(chart, 300);
    }

    public static StatisticsTab getInstance() {
        if (instance == null) {
            instance = new StatisticsTab();
        }
        return instance;
    }
}