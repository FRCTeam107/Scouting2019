package com.frc107.scouting2019.model;

import android.util.SparseArray;

import com.frc107.scouting2019.view.IUIAnalysisListener;

public class AnalysisModel implements IAnalysisListener {
    private SparseArray<TeamDetails> teamDetailsSparseArray;
    private int teamNumber;
    private double averageCargo;
    private double averageHatchPanels;
    private int defenseCount;
    private int effectiveDefenseAmount;
    private boolean rocket1, rocket2, rocket3;
    private boolean hab1, hab2, hab3;
    private IUIAnalysisListener listener; // TODO: THIS SUCKS. FIX THIS. FIGURE OUT HOW TO DO PROPER MVVM.
    private String[] teamNumbers;

    public AnalysisModel(IUIAnalysisListener listener) {
        this.listener = listener;
        teamDetailsSparseArray = new SparseArray<>();
    }

    public void loadData() {
        new LoadDataTask(this).execute();
    }

    public void setTeamNumber(String newTeamNumber) {
        this.teamNumber = Integer.parseInt(newTeamNumber);
        TeamDetails teamDetails = teamDetailsSparseArray.get(teamNumber);
        if (teamDetails == null) {
            averageCargo = 0;
            averageHatchPanels = 0;
            defenseCount = 0;
            effectiveDefenseAmount = 0;
            hab1 = false;
            hab2 = false;
            hab3 = false;
            rocket1 = false;
            rocket2 = false;
            rocket3 = false;
            return;
        }

        averageCargo = teamDetails.getAverageCargo();
        averageHatchPanels = teamDetails.getAverageHatchPanels();
        defenseCount = teamDetails.getDefenseCount();
        effectiveDefenseAmount = teamDetails.getEffectiveDefenseNum();
        hab1 = teamDetails.canClimbHabOne();
        hab2 = teamDetails.canClimbHabTwo();
        hab3 = teamDetails.canClimbHabThree();
        rocket1 = teamDetails.canAccessRocketOne();
        rocket2 = teamDetails.canAccessRocketTwo();
        rocket3 = teamDetails.canAccessRocketThree();
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public double getAverageCargo() {
        return averageCargo;
    }

    public double getAverageHatchPanels() {
        return averageHatchPanels;
    }

    public int getDefenseCount() {
        return defenseCount;
    }

    public int getEffectiveDefenseCount() {
        return effectiveDefenseAmount;
    }

    public boolean canAccessRocketOne() {
        return rocket1;
    }

    public boolean canAccessRocketTwo() {
        return rocket2;
    }

    public boolean canAccessRocketThree() {
        return rocket3;
    }

    public boolean canClimbHabOne() {
        return hab1;
    }

    public boolean canClimbHabTwo() {
        return hab2;
    }

    public boolean canClimbHabThree() {
        return hab3;
    }

    @Override
    public void onDataLoaded(SparseArray<TeamDetails> map) {
        teamDetailsSparseArray = map;
        listener.onDataLoaded();
        loadTeamNumbers();
    }

    private void loadTeamNumbers() {
        teamNumbers = new String[teamDetailsSparseArray.size()];
        for (int i = 0; i < teamDetailsSparseArray.size(); i++) {
            int key = teamDetailsSparseArray.keyAt(i);
            teamNumbers[i] = key + "";
        }
    }

    public String[] getTeamNumbers() {
        return teamNumbers;
    }
}
