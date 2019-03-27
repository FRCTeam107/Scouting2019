package com.frc107.scouting2019.model.question;

public class NumberQuestion extends Question<Integer> {
    private Integer answer;

    public NumberQuestion(String name, int id, boolean needsAnswer) {
        super(name, id, needsAnswer);
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
    public Integer getAnswer() {
        return answer;
    }

    @Override
    public String getAnswerAsString() {
        return String.valueOf(answer);
    }
}
