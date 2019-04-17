package com.frc107.scouting2019.model;

import android.util.SparseArray;

import java.util.HashMap;

public interface IAnalysisListener {
    void onDataLoaded(SparseArray<TeamDetails> map);
}
