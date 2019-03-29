package com.frc107.scouting2019.model.question;

public class TextQuestion extends Question<String> {
    private String answer;

    public TextQuestion(String name, int id, boolean needsAnswer, int position) {
        super(name, id, needsAnswer, position);
        this.answer = "";
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
    public String getAnswerAsString() {
        return answer;
    }
}
