package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.SandstormModel;
import com.frc107.scouting2019.model.question.Question;

public class SandstormViewModel extends ScoutViewModel {
    public SandstormViewModel(Question... questions) {
        model = new SandstormModel(questions);
    }
}
