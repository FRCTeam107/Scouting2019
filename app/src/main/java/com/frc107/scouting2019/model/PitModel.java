package com.frc107.scouting2019.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.model.question.Question;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PitModel extends ScoutModel {
    public PitModel(Question... questions) {
        super(questions);
        setFileNameHeader("Pit");
    }

    public File createPhotoFile() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
        dir.mkdirs();

        String teamNumber = getTeamNumber();
        File file = new File(dir, teamNumber + ".jpg");

        try {
            file.createNewFile();
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
            return null;
        }

        return file;
    }

    public boolean compressPhoto() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
        String teamNumber = getTeamNumber();
        File file = new File(dir, teamNumber + ".jpg");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out);

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(out.toByteArray());
            inputStream.close();
            out.close();
            outputStream.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String getCSVRowHeader() {
        return getTeamNumber();
    }
}
