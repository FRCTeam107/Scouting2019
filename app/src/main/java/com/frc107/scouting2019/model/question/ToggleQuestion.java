package com.frc107.scouting2019.model.question;

public class ToggleQuestion extends Question<Boolean> {
    private boolean answer;

    public ToggleQuestion(String name, int id, int position) {
        super(name, id, false, position);
    }

    @Override
    public boolean hasAnswer() {
        // Since it's a toggle, we can't exactly differentiate between an intentional or unintentional empty selection.
        return true;
    }

    @Override
    public void setAnswer(Boolean answer) {
        this.answer = answer == null ? false : answer;
    }

    @Override
    public Boolean getAnswer() {
        return answer;
    }

    @Override
    public String getAnswerAsString() {
        int asInt = answer ? 1 : 0;
        return asInt + "";
    }
}
