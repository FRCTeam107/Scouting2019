package com.frc107.scouting2019;

import com.frc107.scouting2019.model.csv.CsvWriter;

public class Scouting {
    public static final CsvWriter CSV_GENERATOR = new CsvWriter();

    private static String uniqueId;
    public static void setUniqueId(String newUniqueId) {
        uniqueId = newUniqueId;
    }
    public static String getUniqueId() {
        return uniqueId;
    }

    private static int teamNumber;
    public static void setTeamNumber(int newTeamNumber) {
        teamNumber = newTeamNumber;
    }
    public static int getTeamNumber() {
        return teamNumber;
    }

    private static int matchNumber;
    public static void setMatchNumber(int newMatchNumber) {
        matchNumber = newMatchNumber;
    }
    public static int getMatchNumber() {
        return matchNumber;
    }

    private static String sandstormData;
    public static void setSandstormData(String newSandstormData) {
        sandstormData = newSandstormData;
    }
    public static String getSandstormData() {
        return sandstormData;
    }
}
