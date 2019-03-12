package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.CycleModel;
import com.frc107.scouting2019.model.question.Question;

public class CycleViewModel extends ScoutViewModel {
    public CycleViewModel() {
        model = new CycleModel();
    }

    public void turnTeleopOn() {
        ((CycleModel) model).turnTeleopOn();
    }

    public boolean isTeleop() {
        return ((CycleModel) model).isTeleop();
    }

    public void finishCycle() {
        ((CycleModel) model).finishCycle();
    }

    public void setAllDefense(boolean allDefense) {
        ((CycleModel) model).setAllDefense(allDefense);
    }
}
