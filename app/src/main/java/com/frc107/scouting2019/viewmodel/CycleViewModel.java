package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.CycleModel;
import com.frc107.scouting2019.model.question.Question;

public class CycleViewModel extends ScoutViewModel {
    public CycleViewModel() {
        model = new CycleModel();
    }

    public void finishCycle() {
        ((CycleModel) model).finishCycle();
    }

    public void setAllDefense(boolean allDefense) {
        ((CycleModel) model).setAllDefense(allDefense);
    }

    public boolean cycleCanBeFinished() {
        return ((CycleModel) model).cycleCanBeFinished();
    }
}
