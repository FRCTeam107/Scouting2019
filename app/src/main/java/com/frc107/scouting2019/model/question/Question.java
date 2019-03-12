package com.frc107.scouting2019.model.question;

/**
 * @param <T> The type of the answer.
 */
public abstract class Question<T> {
    private String name; // TODO: remove this
    private int id;
    private boolean needsAnswer;

    public Question(int id, boolean needsAnswer) {
        this.name = "";
        this.id = id;
        this.needsAnswer = needsAnswer;
    }

    public boolean needsAnswer() {
        return needsAnswer;
    }

    public void setNeedsAnswer(boolean needsAnswer) {
        this.needsAnswer = needsAnswer;
    }

    public boolean hasAnswer() {
        return getAnswer() != null;
    }

    public abstract void setAnswer(T answer);
    public abstract T getAnswer();
    public abstract String getAnswerAsString();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
