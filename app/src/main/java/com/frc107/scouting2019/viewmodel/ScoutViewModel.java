package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.ScoutModel;

import androidx.databinding.BaseObservable;

public abstract class ScoutViewModel extends BaseObservable {
    protected ScoutModel model;

    /**
     * TODO: This also will need to use the isQuestionComplete method.
     * @return The ID of the first question that is found to be unfinished.
     */
    public int getFirstUnfinishedQuestionId() {
        return model.getFirstUnfinishedQuestionId();
    }

    public boolean isFormComplete() {
        return model.isFormComplete();
    }

    public boolean areNoQuestionsAnswered() {
        return model.areNoQuestionsAnswered();
    }

    public boolean setAnswer(int questionId, String answer) {
        return model.setAnswer(questionId, answer);
    }

    public boolean setAnswer(int questionId, int answer) {
        return model.setAnswer(questionId, answer);
    }

    public boolean setAnswer(int questionId, boolean answer) {
        return model.setAnswer(questionId, answer);
    }

    public String getAnswerForQuestion(int questionId) {
        return model.getAnswerAsString(questionId);
    }

    public String save() {
        return model.save();
    }

    public void clearAllAnswers() {
        model.clearAllQuestions();
    }
}
