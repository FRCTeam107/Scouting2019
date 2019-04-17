package com.frc107.scouting2019.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class TeamDetails {
    private int teamNumber;
    private int cargoNum;
    private int maxCargo;
    private double averageCargo;
    private int hatchNum;
    private int maxHatch;
    private double averageHatchPanels;
    private int defenseCount;
    private int effectiveDefenseNum;
    private boolean rocket1, rocket2, rocket3;
    private boolean hab1, hab2, hab3;
    private int cycleNum;
    private ArrayList<Integer> matches;

    public TeamDetails() {
        matches = new ArrayList<Integer>();
    }

    public int getTeamNumber() {
        return teamNumber;
    }
    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void incrementCargoNum() {
        cargoNum++;
    }
    public double getAverageCargo() {
        return averageCargo;
    }

    public void incrementHatchNum() {
        hatchNum++;
    }
    public double getAverageHatchPanels() {
        return averageHatchPanels;
    }

    public int getDefenseCount() {
        return defenseCount;
    }
    public void incrementDefenseNum() {
        defenseCount++;
    }
    public int getEffectiveDefenseNum() {
        return effectiveDefenseNum;
    }
    public void incrementEffectiveDefenseNum() {
        effectiveDefenseNum++;
    }

    public boolean canAccessRocketOne() {
        return rocket1;
    }
    public void setCanAccessRocketOne(boolean rocket1) {
        this.rocket1 = rocket1;
    }
    public boolean canAccessRocketTwo() {
        return rocket2;
    }
    public void setCanAccessRocketTwo(boolean rocket2) {
        this.rocket2 = rocket2;
    }
    public boolean canAccessRocketThree() {
        return rocket3;
    }
    public void setCanAccessRocketThree(boolean rocket3) {
        this.rocket3 = rocket3;
    }

    public boolean canClimbHabOne() {
        return hab1;
    }
    public void setCanClimbHabOne(boolean hab1) {
        this.hab1 = hab1;
    }
    public boolean canClimbHabTwo() {
        return hab2;
    }
    public void setCanClimbHabTwo(boolean hab2) {
        this.hab2 = hab2;
    }
    public boolean canClimbHabThree() {
        return hab3;
    }
    public void setCanClimbHabThree(boolean hab3) {
        this.hab3 = hab3;
    }

    public void incrementCycleNum() {
        cycleNum++;
    }

    public void calculateAverages() {
        BigDecimal bdCargo = new BigDecimal((double) cargoNum / (double) matches.size());
        bdCargo = bdCargo.setScale(3, RoundingMode.HALF_UP);
        averageCargo = bdCargo.doubleValue();

        BigDecimal bdHatch = new BigDecimal((double) hatchNum / (double) matches.size());
        bdHatch = bdHatch.setScale(3, RoundingMode.HALF_UP);
        averageHatchPanels = bdHatch.doubleValue();
    }

    public void addMatch(int matchNum) {
        matches.add(matchNum);
    }
    public boolean hasMatch(int matchNum) {
        return matches.contains(matchNum);
    }
}
