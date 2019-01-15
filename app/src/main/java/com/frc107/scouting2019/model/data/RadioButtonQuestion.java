package com.frc107.scouting2019.model.data;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioButtonQuestion implements IQuestion<String> {
    private ArrayList<String> possibleAnswers;
    private int selectedAnswerIndex;
    private boolean needsAnswer;
    private String name;

    public RadioButtonQuestion(String name, boolean needsAnswer, String... possibleAnswers) {
        this.name = name;
        this.needsAnswer = needsAnswer;
        selectedAnswerIndex = -1;
        this.possibleAnswers = new ArrayList<>(Arrays.asList(possibleAnswers));
    }

    @Override
    public boolean needsAnswer() {
        return needsAnswer;
    }

    @Override
    public boolean hasAnswer() {
        return selectedAnswerIndex != -1;
    }

    @Override
    public void setAnswer(String answer) {
        selectedAnswerIndex = possibleAnswers.indexOf(answer);
    }

    @Override
    public String getAnswer() {
        String answer = selectedAnswerIndex == -1 ? null : possibleAnswers.get(selectedAnswerIndex);
        return answer;
    }

    @Override
    public String getName() {
        return name;
    }
}
