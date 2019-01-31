package com.frc107.scouting2019.model.csv;

import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.Scouting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CsvWriter {
    private FileOutputStream fileOutputStream;
    private File directory;

    public CsvWriter() {
        directory = new File(Environment.getExternalStorageDirectory() + "/Scouting");
    }

    public String writeData(String fileNameHeader, String data) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (!directory.exists())
                directory.mkdirs();

            String uniqueId = Scouting.getUniqueId();
            File file = new File(directory, fileNameHeader + uniqueId + ".csv");

            String message = data + "\n";

            try {
                fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                Log.d("Scouting", e.getMessage());
            } finally {
                try {
                    fileOutputStream.close();
                    return "Saved successfully.";
                } catch (IOException e) {
                    Log.d("Scouting", e.getMessage());
                    return "Failure while saving.";
                }
            }
        }
        return "SD card not found.";
    }
}
