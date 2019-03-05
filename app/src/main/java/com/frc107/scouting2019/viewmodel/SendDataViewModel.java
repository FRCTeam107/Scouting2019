package com.frc107.scouting2019.viewmodel;

import android.content.Context;
import android.net.Uri;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.SendDataModel;

import java.io.File;
import java.util.ArrayList;

public class SendDataViewModel {
    private SendDataModel model;

    public SendDataViewModel() {
        model = new SendDataModel();
    }

    public boolean concatenateMatchData() {
        return model.concatenateData(SendDataModel.MATCH);
    }

    public boolean concatenatePitData() {
        return model.concatenateData(SendDataModel.PIT);
    }

    public ArrayList<Uri> getPhotoUriList(Context context) {
        return model.getPhotoUriList(context);
    }

    public File getMatchFile() {
        return model.getMatchFile();
    }

    public File getPitFile() {
        return model.getPitFile();
    }

    public File getConcatMatchFile() {
        return model.getConcatMatchFile();
    }

    public File getConcatPitFile() {
        return model.getConcatPitFile();
    }
}
