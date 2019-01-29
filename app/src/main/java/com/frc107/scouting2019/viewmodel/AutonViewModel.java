package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.SandstormModel;
import com.frc107.scouting2019.model.question.Question;

public class AutonViewModel extends ScoutViewModel {
    public AutonViewModel(Question... questions) {
        model = new SandstormModel(questions);
    }

    public void finish() {
        ((SandstormModel) model).finish();
    }
}
