package com.frc107.scouting2019.admin;

import android.content.Context;
import android.net.Uri;

import com.frc107.scouting2019.admin.AdminModel;

import java.io.File;
import java.util.ArrayList;

public class AdminViewModel {
    private AdminModel model;

    public AdminViewModel() {
        model = new AdminModel();
    }

    public boolean concatenateMatchData() {
        return model.concatenateData(AdminModel.MATCH);
    }

    public boolean concatenatePitData() {
        return model.concatenateData(AdminModel.PIT);
    }

    public ArrayList<Uri> getPhotoUriList(Context context) {
        return model.getPhotoUriList(context);
    }

    public File getMatchFile(boolean concatenated) {
        return model.getMatchFile(concatenated);
    }

    public File getPitFile(boolean concatenated) {
        return model.getPitFile(concatenated);
    }
}
