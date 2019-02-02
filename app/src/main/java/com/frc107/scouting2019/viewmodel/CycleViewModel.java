package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.CycleModel;
import com.frc107.scouting2019.model.question.Question;

public class CycleViewModel extends ScoutViewModel {
    public CycleViewModel(Question... questions) {
        model = new CycleModel(questions);
    }
}
