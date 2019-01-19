package com.frc107.scouting2019.model.question;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioQuestion extends Question<Integer> {
    private ArrayList<Option> options;
    private Option selectedOption;
    private boolean needsAnswer;

    public RadioQuestion(int id, boolean needsAnswer, Option... options) {
        super(id);
        this.needsAnswer = needsAnswer;
        this.options = new ArrayList<>(Arrays.asList(options));
    }

    @Override
    public boolean needsAnswer() {
        return needsAnswer;
    }

    @Override
    public boolean hasAnswer() {
        return selectedOption != null;
    }

    @Override
    public void setAnswer(Integer answerId) {
        for (Option option : options) {
            if (option.getId() == answerId) {
                selectedOption = option;
            }
        }
    }

    @Override
    public String getAnswer() {
        if (selectedOption == null)
            return "";

        return selectedOption.getText();
    }

    public static class Option {
        private int id;
        private String text;

        public Option(int id, String text) {
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
}

