package com.frc107.scouting2019.model;

import android.os.Build;
import android.util.SparseArray;

import com.frc107.scouting2019.view.IUIAnalysisListener;

import java.util.ArrayList;

public class AttributeAnalysisModel implements IAnalysisListener {
    private IUIAnalysisListener listener;
    private String[] teamNumbers;
    private String[] attributes;
    private ArrayList<AnalysisElement> elements;
    private SparseArray<TeamDetails> detailsArray;

    public AttributeAnalysisModel(IUIAnalysisListener listener) {
        this.listener = listener;
        teamNumbers = new String[0];
        attributes = new String[0];
        elements = new ArrayList<>();
    }

    public void loadData() {
        new LoadDataTask(this).execute();
    }

    @Override
    public void onDataLoaded(SparseArray<TeamDetails> detailsArray) {
        this.detailsArray = detailsArray;
        loadAttributes();
        listener.onDataLoaded();
    }

    private void loadAttributes() {
        teamNumbers = new String[detailsArray.size()];
        attributes = new String[detailsArray.size()];
        for (int i = 0; i < detailsArray.size(); i++) {
            teamNumbers[i] = detailsArray.keyAt(i) + "";
            attributes[i] = detailsArray.valueAt(i).getAverageCargo() + "";
            elements.add(new AnalysisElement(teamNumbers[i], attributes[i]));
        }
    }

    public ArrayList<AnalysisElement> getElements() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            elements.sort((o1, o2) -> o1.getAttribute().compareTo(o2.getAttribute()));
        }
        return elements;
    }

    public String[] getTeamNumbers() {
        return teamNumbers;
    }

    public String[] getAttributes() {
        return attributes;
    }
}
