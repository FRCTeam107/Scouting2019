package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.SandstormModel;
import com.frc107.scouting2019.model.question.Question;

public class SandstormViewModel extends ScoutViewModel {
    public SandstormViewModel() {
        model = new SandstormModel();
    }

    public void finish() {
        ((SandstormModel) model).finish();
    }
}
