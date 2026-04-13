package com.example.sqlassist.utils;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {

    //Fade in animation
    public static void fadeIn(Node node, int durationMs) {
        FadeTransition ft = new FadeTransition(Duration.millis(durationMs), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    //Button hover
    public static void addHoverScale(Node node) {
        node.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(120), node);
            st.setToX(1.08);
            st.setToY(1.08);
            st.play();
        });

        node.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(120), node);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

    //Shake animation
    public static void shake(Node node) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.play();
    }

    //Slide in from left
    public static void slideIn(Node node, int durationMs) {
        node.setTranslateX(-200);
        TranslateTransition tt = new TranslateTransition(Duration.millis(durationMs), node);
        tt.setToX(0);
        tt.play();
    }

    //Bounce animation
    public static void clickBounce(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(120), node);
        st.setFromX(1.0);
        st.setFromY(1.0);
        st.setToX(0.92);
        st.setToY(0.92);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }

}