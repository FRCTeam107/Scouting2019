package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.PitModel;

import java.io.File;

public class PitViewModel extends ScoutViewModel {
    public PitViewModel() {
        model = new PitModel();
    }

    public File createPhotoFile() {
        return ((PitModel) model).createPhotoFile();
    }

    public boolean rotateAndCompressPhoto() {
        return ((PitModel) model).rotateAndCompressPhoto();
    }
}
