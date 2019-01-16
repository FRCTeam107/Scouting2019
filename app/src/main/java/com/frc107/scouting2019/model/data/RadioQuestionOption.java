package com.frc107.scouting2019.model.data;

public class RadioQuestionOption {
    private int id;
    private String text;

    public RadioQuestionOption(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
