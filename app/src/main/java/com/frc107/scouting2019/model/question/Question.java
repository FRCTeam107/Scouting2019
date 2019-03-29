package com.frc107.scouting2019.model.question;

/**
 * @param <T> The type of the answer.
 */
public abstract class Question<T> {
    /**
     * name is used for debugging, so that we can easily have a way to make
     * sure that questions are being saved in the correct order.
     */
    private String name;
    private int id;
    private boolean needsAnswer;
    private boolean ignoreAnswer;

    /**
     * Creates a basic question. Don't use this.
     */
    public Question(String name, int id, boolean needsAnswer) {
        this.name = name;
        this.id = id;
        this.needsAnswer = needsAnswer;
    }

    public boolean needsAnswer() {
        return needsAnswer;
    }

    public void setNeedsAnswer(boolean needsAnswer) {
        this.needsAnswer = needsAnswer;
    }

    /**
     * @param ignoreAnswer Whether the answer shouldn't be saved.
     */
    public void setIgnoreAnswer(boolean ignoreAnswer) {
        this.ignoreAnswer = ignoreAnswer;
    }

    /**
     * @return Whether the answer shouldn't be saved or not.
     */
    public boolean answerCanBeIgnored() {
        return ignoreAnswer;
    }

    public boolean hasAnswer() {
        return getAnswer() != null;
    }

    public abstract void setAnswer(T answer);
    public abstract T getAnswer();

    /**
     * @return The answer in a saveable format.
     */
    public abstract String getAnswerAsString();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
