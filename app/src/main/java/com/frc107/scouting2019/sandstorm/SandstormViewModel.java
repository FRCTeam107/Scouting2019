package com.frc107.scouting2019.sandstorm;

import com.frc107.scouting2019.ScoutViewModel;

public class SandstormViewModel extends ScoutViewModel {
    public SandstormViewModel() {
        model = new SandstormModel();
    }

    public void finish() {
        ((SandstormModel) model).finish();
    }

    public int getTeamNumber() {
        return ((SandstormModel) model).getTeamNumber();
    }

    public boolean shouldAllowStartingPiece() {
        return ((SandstormModel) model).shouldAllowStartingPiece();
    }
}
