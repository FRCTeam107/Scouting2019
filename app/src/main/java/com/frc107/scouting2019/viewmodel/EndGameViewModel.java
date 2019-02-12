package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.EndGameModel;
import com.frc107.scouting2019.model.SandstormModel;
import com.frc107.scouting2019.model.question.Question;

public class EndGameViewModel extends ScoutViewModel {
    public EndGameViewModel(Question... questions) {
        model = new EndGameModel(questions);
    }

    public String finish() {
        return ((EndGameModel) model).finish();
    }
}
