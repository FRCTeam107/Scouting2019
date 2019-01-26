package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.ScoutModel;

import androidx.lifecycle.ViewModel;

public abstract class ScoutViewModel extends ViewModel {
    protected ScoutModel model;

    public int getFirstUnfinishedQuestionId() {
        return model.getFirstUnfinishedQuestionId();
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
        return model.getAnswerForQuestion(questionId);
    }

    public String getAnswerCSVRow() {
        return model.getAnswerCSVRow();
    }

    public String save() {
        // TODO: At some point, since this is disgusting, make it so that ScoutModel is never instantiated and a view-specific model is used instead.
        return model.save();
    }
}
