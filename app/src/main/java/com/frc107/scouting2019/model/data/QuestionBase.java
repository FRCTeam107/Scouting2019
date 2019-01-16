package com.frc107.scouting2019.model.data;

/**
 * @param <T> The type of the answer.
 */
public abstract class QuestionBase<T> {
    private String name;
    private int id;

    public QuestionBase(int id) {
        this.name = "";
        this.id = id;
    }

    public abstract boolean needsAnswer();
    public abstract boolean hasAnswer();
    public abstract void setAnswer(T answer);
    public abstract String getAnswer();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
