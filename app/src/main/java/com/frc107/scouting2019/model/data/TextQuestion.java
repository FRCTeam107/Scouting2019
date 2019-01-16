package com.frc107.scouting2019.model.data;

public class TextQuestion extends QuestionBase<String> {
    private boolean needsAnswer;
    private String answer;

    public TextQuestion(int id, boolean needsAnswer) {
        super(id);
        this.needsAnswer = needsAnswer;
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
}
