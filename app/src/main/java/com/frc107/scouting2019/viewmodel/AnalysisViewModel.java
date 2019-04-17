package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.AnalysisModel;
import com.frc107.scouting2019.view.IUIAnalysisListener;

public class AnalysisViewModel {
    private AnalysisModel model;

    public AnalysisViewModel(IUIAnalysisListener listener) {
        model = new AnalysisModel(listener);
    }

    public void loadData() {
        model.loadData();
    }

    public void setTeamNumber(String teamNumber) {
        model.setTeamNumber(teamNumber);
    }

    public double getAverageCargo() {
        return model.getAverageCargo();
    }

    public double getAverageHatchPanels() {
        return model.getAverageHatchPanels();
    }

    public int getDefenseCount() {
        return model.getDefenseCount();
    }

    public int getEffectiveDefenseCount() {
        return model.getEffectiveDefenseCount();
    }

    public boolean canAccessRocketOne() {
        return model.canAccessRocketOne();
    }

    public boolean canAccessRocketTwo() {
        return model.canAccessRocketTwo();
    }

    public boolean canAccessRocketThree() {
        return model.canAccessRocketThree();
    }

    public boolean canClimbHabOne() {
        return model.canClimbHabOne();
    }

    public boolean canClimbHabTwo() {
        return model.canClimbHabTwo();
    }

    public boolean canClimbHabThree() {
        return model.canClimbHabThree();
    }

    public String[] getTeamNumbers() {
        return model.getTeamNumbers();
    }
}
