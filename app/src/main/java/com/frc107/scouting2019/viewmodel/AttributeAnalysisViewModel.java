package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.AnalysisElement;
import com.frc107.scouting2019.model.AttributeAnalysisModel;
import com.frc107.scouting2019.view.IUIAnalysisListener;

import java.util.ArrayList;

public class AttributeAnalysisViewModel {
    private AttributeAnalysisModel model;

    public AttributeAnalysisViewModel(IUIAnalysisListener listener) {
        model = new AttributeAnalysisModel(listener);
    }

    public void loadData() {
        model.loadData();
    }

    public ArrayList<AnalysisElement> getElements() {
        return model.getElements();
    }

    public String[] getTeamNumbers() {
        return model.getTeamNumbers();
    }

    public String[] getAttributes() {
        return model.getAttributes();
    }
}
