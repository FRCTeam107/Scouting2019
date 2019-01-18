package com.frc107.scouting2019.model;

import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.model.question.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TeleopModel extends ScoutModel {
    public TeleopModel(String name, Question... questions) {
        super(name, questions);
    }

    public String save(boolean hasWritePermissions, String uniqueDeviceId, String autonData) {
        if(hasWritePermissions) {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting");
                dir.mkdirs();

                File file = new File(dir, "Match" + uniqueDeviceId + ".csv");

                String result = getResult();
                String message = autonData + "," + result + "\n";

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(message.getBytes());
                    fileOutputStream.close();

                    return "Saved successfully.";
                } catch (IOException e) {
                    Log.d("Scouting", e.getMessage());
                    return "IOException! Go talk to the programmers.";
                }
            } else {
                return "SD card not found.";
            }
        }
        return "No write permissions.";
    }
}
