package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.TeleopModel;
import com.frc107.scouting2019.model.question.Question;

public class TeleopViewModel extends ScoutViewModel {
    public TeleopViewModel(String autonData, Question... questions) {
        super(questions);
        model = new TeleopModel(autonData, questions);
    }
}
