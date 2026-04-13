package com.example.sqlassist;

import javafx.scene.media.AudioClip;

public class SoundPlayer {

    private static AudioClip load(String fileName) {
        try {
            return new AudioClip(SoundPlayer.class.getResource("/sounds/" + fileName).toExternalForm());
        } catch (Exception e) {
            System.out.println("Sound file not found: " + fileName);
            return null;
        }
    }

    public static void click() {
        AudioClip clip = load("click.wav");
        if (clip != null) clip.play();
    }

    public static void playSuccess() {
        AudioClip clip = load("success.wav");
        if (clip != null) clip.play();
    }

    public static void playError() {
        AudioClip clip = load("error.wav");
        if (clip != null) clip.play();
    }
}