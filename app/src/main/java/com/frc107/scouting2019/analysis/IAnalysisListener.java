package com.frc107.scouting2019.analysis;

import android.util.SparseArray;

public interface IAnalysisListener {
    void onDataLoaded(SparseArray<TeamDetails> detailsArray, boolean error);
}
