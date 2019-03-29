package com.frc107.scouting2019.model.question;

public class ToggleQuestion extends Question<Boolean> {
    private boolean answer;

    /**
     * Create a question that has a boolean answer.
     * @param name The name of the question. At this point, it's only used for debugging.
     * @param id The id of the question to differentiate it.
     */
    public ToggleQuestion(String name, int id) {
        super(name, id, false);
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
