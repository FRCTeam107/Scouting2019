package com.frc107.scouting2019.model.question;

public class NumberQuestion extends Question<Integer> {
    private int answer;

    public NumberQuestion(int id) {
        super(id);
    }

    @Override
    public boolean needsAnswer() {
        return false;
    }

    @Override
    public boolean hasAnswer() {
        return true;
    }

    @Override
    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return String.valueOf(answer);
    }
}
