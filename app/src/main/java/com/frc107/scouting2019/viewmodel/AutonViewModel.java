package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.AutonModel;
import com.frc107.scouting2019.model.question.Question;

public class AutonViewModel extends ScoutViewModel {
    public AutonViewModel(Question... questions) {
        model = new AutonModel(questions);
    }
}
