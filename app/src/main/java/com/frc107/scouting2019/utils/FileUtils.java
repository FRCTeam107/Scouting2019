package com.frc107.scouting2019.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.Scouting;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
    private FileOutputStream fileOutputStream;
    private File directory;

    public FileUtils() {
        directory = new File(Environment.getExternalStorageDirectory() + "/Scouting");
    }

    public boolean directoryExists() {
        return directory.exists();
    }

    public boolean createDirectory() {
        return directory.mkdirs();
    }

    public File[] getFilesInDirectory() {
        return directory.listFiles();
    }

    public File[] getPhotos() {
        File directory = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
        return directory.listFiles();
    }

    public File getPhoto(String teamNumber) {
        File photo = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos/" + teamNumber + ".jpg");
        if (!photo.exists())
            return null;

        return photo;
    }

    public boolean compressPhoto(String teamNumber) {
        try {
            File file = getPhoto(teamNumber);
            FileInputStream fileInputStream = new FileInputStream(file);

            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            if (bitmap == null) {
                Log.d("Scouting", "Bitmap is null");
                return false;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            return true;
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
        }
        return false;
    }

    public File getScoutingDirectory() {
        return directory;
    }

    public String writeData(String fileNameHeader, String data) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (!directory.exists())
                directory.mkdirs();

            String uniqueId = Scouting.getInstance().getUniqueId();
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

    public String getContentFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append('\n');
            }

            return builder.toString();
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
        }

        return "";
    }
}
