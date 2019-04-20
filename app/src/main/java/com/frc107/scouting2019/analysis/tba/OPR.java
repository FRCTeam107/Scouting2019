package com.frc107.scouting2019.analysis.tba;

import java.util.HashMap;

public class OPR {
    private HashMap<Integer, String> oprMap;

    public OPR() {
        oprMap = new HashMap<>();
    }

    public boolean containsTeam(int teamNum) {
        return oprMap.containsKey(teamNum);
    }

    public void setOPR(int teamNum, String opr) {
        oprMap.put(teamNum, opr);
    }

    public String getOPR(int teamNum) {
        if (!oprMap.containsKey(teamNum))
            throw new IllegalArgumentException("Invalid team number: " + teamNum);

        return oprMap.get(teamNum);
    }
}
