package com.frc107.scouting2019.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import androidx.core.content.FileProvider;

public class SendDataModel {
    public static final int MATCH = 0,
                            PIT = 1;

    public boolean compressPhotos() {
        FileUtils fileUtils = Scouting.FILE_UTILS;
        File[] photos = fileUtils.getPhotos();

        for (File photo : photos) {
            boolean success = compressPhoto(photo);
            if (!success)
                return false;
        }

        return true;
    }

    private boolean compressPhoto(File file) {
        /*try {
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
        }*/
        try {
            //FileInputStream inputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out);

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(out.toByteArray());
            out.close();
            outputStream.close();

            return true;
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
        }
        return false;
    }

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
        String header = "\n";
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

        String fileName = prefix + "Concatenated.csv";
        File newFile = new File(fileUtils.getScoutingDirectory(), fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile, false)) {
            fileOutputStream.write(builder.toString().getBytes());
            return true;
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
        }

        return false;
    }

    public File getMatchFile() {
        return Scouting.FILE_UTILS.getDeviceMatchFile();
    }

    public File getPitFile() {
        return Scouting.FILE_UTILS.getDevicePitFile();
    }
}
