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

    /**
     * @param answer The boolean that you are setting the answer to. If this is null, the answer will be set to false.
     */
    @Override
    public void setAnswer(Boolean answer) {
        this.answer = answer == null ? false : answer;
    }

    @Override
    public Boolean getAnswer() {
        return answer;
    }

    /**
     * @return A string containing 1 for true or 0 for false.
     */
    @Override
    public String getAnswerAsString() {
        int asInt = answer ? 1 : 0;
        return asInt + "";
    }
}
