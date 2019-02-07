package com.frc107.scouting2019.model.question;

/**
 * @param <T> The type of the answer.
 */
public abstract class Question<T> {
    private String name; // TODO: remove this
    private int id;

    public Question(int id) {
        this.name = "";
        this.id = id;
    }

    public abstract boolean needsAnswer();
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
