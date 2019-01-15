package com.frc107.scouting2019.model.data;

import java.util.ArrayList;

public class NumberQuestion implements IQuestion<Integer> {
    private int answer;
    private String name;

    public NumberQuestion(String name) {
        this.name = name;
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

    @Override
    public String getName() {
        return name;
    }
}
