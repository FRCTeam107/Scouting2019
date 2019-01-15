package com.frc107.scouting2019.model.data;

/**
 * @param <T> The type of the answer.
 */
public interface IQuestion<T> {
    boolean needsAnswer();
    boolean hasAnswer();
    void setAnswer(T answer);
    String getAnswer();
    String getName();
}
