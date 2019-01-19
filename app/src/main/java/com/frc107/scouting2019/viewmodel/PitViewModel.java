package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.PitModel;
import com.frc107.scouting2019.model.question.Question;

import java.io.File;

public class PitViewModel extends ScoutViewModel {
    private PitModel model;

    public PitViewModel(Question... questions) {
        model = new PitModel(questions);
    }

    public File createPhotoFile() {
        return model.createPhotoFile();
    }

    public boolean compressPhoto() {
        return model.compressPhoto();
    }
}
