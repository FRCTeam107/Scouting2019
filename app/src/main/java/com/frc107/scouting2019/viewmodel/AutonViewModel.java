package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.ScoutModelBase;
import com.frc107.scouting2019.model.data.CheckBoxQuestion;
import com.frc107.scouting2019.model.data.NumberQuestion;
import com.frc107.scouting2019.model.data.QuestionBase;
import com.frc107.scouting2019.model.data.RadioButtonQuestion;
import com.frc107.scouting2019.model.data.TextQuestion;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class AutonViewModel extends ViewModel {
    private ScoutModelBase model;

    public AutonViewModel(QuestionBase... questions) {
       model = new ScoutModelBase("Auton", questions);
    }

    public boolean setAnswer(int questionID, String answer) {
        ArrayList<QuestionBase> questions = model.getQuestions();
        for (QuestionBase question : questions) {
            if (question.getId() == questionID) {
                if (question instanceof TextQuestion) {
                    ((TextQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setAnswer(int questionID, int answer) {
        ArrayList<QuestionBase> questions = model.getQuestions();
        for (QuestionBase question : questions) {
            if (question.getId() == questionID) {
                if (question instanceof NumberQuestion) {
                    ((NumberQuestion) question).setAnswer(answer);
                    return true;
                }

                if (question instanceof RadioButtonQuestion) {
                    ((RadioButtonQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setAnswer(int questionID, boolean answer) {
        ArrayList<QuestionBase> questions = model.getQuestions();
        for (QuestionBase question : questions) {
            if (question.getId() == questionID) {
                if (question instanceof CheckBoxQuestion) {
                    ((CheckBoxQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }
}
