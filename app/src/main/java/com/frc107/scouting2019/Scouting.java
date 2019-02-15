package com.frc107.scouting2019;

import com.frc107.scouting2019.model.csv.CsvWriter;

import java.util.ArrayList;

public class Scouting {
    public static final CsvWriter CSV_GENERATOR = new CsvWriter();

    private static Scouting scouting;
    public static Scouting getInstance() {
        if (scouting == null)
            scouting = new Scouting();
        return scouting;
    }

    private Scouting() {
        cycles = new ArrayList<String>();
    }

    private String uniqueId;
    public void setUniqueId(String newUniqueId) {
        uniqueId = newUniqueId;
    }
    public String getUniqueId() {
        return uniqueId;
    }

    private String teamNumber;
    public void setTeamNumber(String newTeamNumber) {
        teamNumber = newTeamNumber;
    }
    public String getTeamNumber() {
        return teamNumber;
    }

    private String matchNumber;
    public void setMatchNumber(String newMatchNumber) {
        matchNumber = newMatchNumber;
    }
    public String getMatchNumber() {
        return matchNumber;
    }

    private String sandstormData;
    public void setSandstormData(String newSandstormData) {
        sandstormData = newSandstormData;
    }
    public String getSandstormData() {
        return sandstormData;
    }

    private ArrayList<String> cycles;
    public void addCycle(String cycle) {
        cycles.add(cycle);
    }
    public ArrayList<String> getCycles() {
        return cycles;
    }

    // TODO: Go through radiogroups and fix formatting
    // TODO: naming conventions
    // TODO: update button strings, example being save button in cycle
}
