package com.frc107.scouting2019.model.question;

import java.util.ArrayList;

public class NumberQuestion extends Question<Integer> {
    private Integer answer;
    private ArrayList<Integer> illegalValues;

    /**
     * Create a question with a number answer.
     * @param name The name of the question. At this point, it's only used for debugging.
     * @param id The id of the question to differentiate it.
     * @param needsAnswer Does it need an answer?
     */
    public NumberQuestion(String name, int id, boolean needsAnswer) {
        super(name, id, needsAnswer);
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

    /**
     * Set a number that, when it is the answer, will mark the question as unfinished.
     * @param value The illegal value.
     */
    public void addIllegalValue(int value) {
        illegalValues.add(value);
    }
}
