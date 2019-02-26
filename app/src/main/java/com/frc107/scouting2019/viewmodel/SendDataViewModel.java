package com.frc107.scouting2019.viewmodel;

import android.content.Context;
import android.net.Uri;

import com.frc107.scouting2019.model.SendDataModel;

import java.util.ArrayList;

public class SendDataViewModel {
    private SendDataModel model;

    public SendDataViewModel() {
        model = new SendDataModel();
    }

    public boolean concatenateMatchData() {
        return model.concatenateData();
    }

    public boolean compressPhotos() {
        return model.compressPhotos();
    }

    public ArrayList<Uri> getPhotoUriList(Context context) {
        return model.getPhotoUriList(context);
    }
}
