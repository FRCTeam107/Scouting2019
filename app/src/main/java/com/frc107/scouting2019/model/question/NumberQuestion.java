package com.frc107.scouting2019.model.question;

import java.util.ArrayList;

public class NumberQuestion extends Question<Integer> {
    private Integer answer;
    private ArrayList<Integer> illegalValues;

    public NumberQuestion(String name, int id, boolean needsAnswer, int position) {
        super(name, id, needsAnswer, position);
        illegalValues = new ArrayList<>();
    }

    @Override
    public boolean hasAnswer() {
        return answer != null && !illegalValues.contains(answer);
    }

    @Override
    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public Integer getAnswer() {
        return answer;
    }

    @Override
    public String getAnswerAsString() {
        return String.valueOf(answer);
    }

    public void addIllegalValue(int value) {
        illegalValues.add(value);
    }
}
