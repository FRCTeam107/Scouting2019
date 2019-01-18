package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.ScoutModel;
import com.frc107.scouting2019.model.question.Question;

import androidx.lifecycle.ViewModel;

public abstract class ScoutViewModel extends ViewModel {
    protected ScoutModel model;

    public ScoutViewModel(String name, Question... questions) {
       model = new ScoutModel(name, questions);
    }

    public int getTeamNumber() {
        return model.getTeamNumber();
    }

    public void setTeamNumber(int teamNumber) {
        model.setTeamNumber(teamNumber);
    }

    public int getMatchNumber() {
        return model.getMatchNumber();
    }

    public void setMatchNumber(int matchNumber) {
        model.setMatchNumber(matchNumber);
    }

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

    public String getResult() {
        return model.getResult();
    }
}
