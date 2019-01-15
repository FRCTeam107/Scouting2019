package com.frc107.scouting2019.model.data;

public class TextQuestion implements IQuestion<String> {
    private boolean needsAnswer;
    private String answer;
    private String name;

    public TextQuestion(String name, boolean needsAnswer) {
        this.needsAnswer = needsAnswer;
        this.name = name;
    }

    @Override
    public boolean needsAnswer() {
        return needsAnswer;
    }

    @Override
    public boolean hasAnswer() {
        return answer.length() > 0;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public String getName() {
        return name;
    }
}
