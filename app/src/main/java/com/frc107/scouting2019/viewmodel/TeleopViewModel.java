package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.CycleModel;
import com.frc107.scouting2019.model.question.Question;

public class TeleopViewModel extends ScoutViewModel {
    public TeleopViewModel(Question... questions) {
        model = new CycleModel(questions);
    }
}
