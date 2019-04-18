package com.frc107.scouting2019.model;

import android.util.SparseArray;

import com.frc107.scouting2019.view.IUIAnalysisListener;

public class AttributeAnalysisModel implements IAnalysisListener {
    private IUIAnalysisListener listener;
    private String[] teamNumbers;
    private String[] attributes;
    private SparseArray<TeamDetails> detailsArray;

    public AttributeAnalysisModel(IUIAnalysisListener listener) {
        this.listener = listener;
    }

    public void loadData() {
        new LoadDataTask(this).execute();
    }

    @Override
    public void onDataLoaded(SparseArray<TeamDetails> detailsArray) {
        this.detailsArray = detailsArray;
        listener.onDataLoaded();
        loadAttributes();
    }

    private void loadAttributes() {
        teamNumbers = new String[detailsArray.size()];
        attributes = new String[detailsArray.size()];
        for (int i = 0; i < detailsArray.size(); i++) {
            teamNumbers[i] = detailsArray.keyAt(i) + "";
            attributes[i] = detailsArray.valueAt(i).getAverageCargo() + "";
        }
    }

    public String[] getTeamNumbers() {
        return teamNumbers;
    }

    public String[] getAttributes() {
        return attributes;
    }
}
