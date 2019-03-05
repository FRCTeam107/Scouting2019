package com.frc107.scouting2019.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import androidx.core.content.FileProvider;

public class SendDataModel {
    public static final int MATCH = 0;
    public static final int PIT = 1;

    public ArrayList<Uri> getPhotoUriList(Context context) {
        ArrayList<Uri> uriList = new ArrayList<Uri>();
        for (File photo :  Scouting.FILE_UTILS.getPhotos()) {
            Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", photo);
            uriList.add(uri);
        }
        return uriList;
    }

    public boolean concatenateData(int type) {
        String prefix = "Match";
        if (type == PIT)
            prefix = "Pit";

        StringBuilder builder = new StringBuilder();
        String header = "header\n";
        builder.append(header);

        FileUtils fileUtils = Scouting.FILE_UTILS;

        if (!fileUtils.directoryExists()) {
            fileUtils.createDirectory();
        }

        File[] files = fileUtils.getFilesInDirectory();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (!file.getName().startsWith(prefix))
                continue;

            String content = fileUtils.getContentFromFile(file);
            builder.append(content);
        }

        String fileName = "Concatenated" + prefix + ".csv";
        File newFile = new File(fileUtils.getScoutingDirectory(), fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile, false);
             FileWriter fileWriter = new FileWriter(fileOutputStream.getFD())) {
            if (newFile.exists())
                fileWriter.write("");

            fileWriter.write(builder.toString());
            return true;
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
        }

        return false;
    }

    public File getMatchFile() {
        return Scouting.FILE_UTILS.getFile("Match" + Scouting.getInstance().getUniqueId() + ".csv");
    }

    public File getPitFile() {
        return Scouting.FILE_UTILS.getFile("Pit" + Scouting.getInstance().getUniqueId() + ".csv");
    }

    public File getConcatMatchFile() {
        return Scouting.FILE_UTILS.getFile("ConcatenatedMatch.csv");
    }

    public File getConcatPitFile() {
        return Scouting.FILE_UTILS.getFile("ConcatenatedPit.csv");
    }
}
