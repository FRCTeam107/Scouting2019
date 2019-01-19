package com.frc107.scouting2019.model.question;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioQuestion extends Question<Integer> {
    private ArrayList<RadioQuestionOption> options;
    private RadioQuestionOption selectedOption;
    private boolean needsAnswer;

    public RadioQuestion(int id, boolean needsAnswer, RadioQuestionOption... options) {
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
        for (RadioQuestionOption option : options) {
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
}

