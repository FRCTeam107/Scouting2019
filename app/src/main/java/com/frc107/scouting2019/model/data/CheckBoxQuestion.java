package com.frc107.scouting2019.model.data;

public class CheckBoxQuestion extends Question<Boolean> {
    private boolean answer;

    public CheckBoxQuestion(int id) {
        super(id);
    }

    @Override
    public boolean needsAnswer() {
        // Since it's a checkbox, it'll always have an answer: true or false.
        return false;
    }

    @Override
    public boolean hasAnswer() {
        // Since it's a checkbox, we can't exactly differentiate between an intentional or unintentional empty selection.
        return true;
    }

    @Override
    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return String.valueOf(answer);
    }
}
