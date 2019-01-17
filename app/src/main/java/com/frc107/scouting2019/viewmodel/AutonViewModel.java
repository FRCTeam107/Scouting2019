package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.ScoutModelBase;
import com.frc107.scouting2019.model.data.Question;

import androidx.lifecycle.ViewModel;

public class AutonViewModel extends ViewModel {
    private ScoutModelBase model;

    public AutonViewModel(Question... questions) {
       model = new ScoutModelBase("Auton", questions);
    }

    public int getTeamNumber() {
        return model.getTeamNumber();
    }

    public int getMatchNumber() {
        return model.getMatchNumber();
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

    public String toString() {
        return model.toString();
    }
}
