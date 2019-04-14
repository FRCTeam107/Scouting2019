package com.frc107.scouting2019.endgame;

import com.frc107.scouting2019.ScoutViewModel;

public class EndGameViewModel extends ScoutViewModel {
    public EndGameViewModel() {
        model = new EndGameModel();
    }

    public String finish() {
        return ((EndGameModel) model).finish();
    }
}
