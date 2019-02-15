package com.frc107.scouting2019.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PitModel extends ScoutModel {
    public PitModel() {
        super();
        setFileNameHeader("Pit");
    }

    public File createPhotoFile() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
        dir.mkdirs();

        String teamNumber = Scouting.getInstance().getTeamNumber();
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
        String teamNumber = Scouting.getInstance().getTeamNumber();
        File file = new File(dir, teamNumber + ".jpg");

        try (FileInputStream fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);

            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileInputStream.close();
            byteArrayOutputStream.close();
            fileOutputStream.close();

            return true;
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
            return false;
        }
    }

    @Override
    public Question[] getQuestions() {
        Question[] questions = {
                new TextQuestion(R.id.pit_teamNumber_editText, true),
                new RadioQuestion(R.id.sandstormOperationsRadioQuestion, true,
                        new RadioQuestion.Option(R.id.visionSystemSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.cameraDrivingSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.blindDrivingSandstorm_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noDrivingSandstorm_Radiobtn, 3)),
                new RadioQuestion(R.id.sandstormPreferenceRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoshipPreferenceSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.rocketshipPreferenceSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.noPreferenceSandstorm_Radiobtn, 2)),
                new RadioQuestion(R.id.highestRocketLevelSandstormRadioQuestion, true,
                        new RadioQuestion.Option(R.id.topRocketLevelSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.middleRocketLevelSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.bottomRocketLevelSandstorm_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noRocketLevelSandstorm_Radiobtn, 3)),
                new RadioQuestion(R.id.highestHabitatLevelRadioQuestion, true,
                        new RadioQuestion.Option(R.id.topHabitatLevel_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.middleHabitatLevel_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.bottomHabitatLevel_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noHabitatLevel_Radiobtn, 3)),
                new TextQuestion(R.id.pit_habitatTime_editText, true),
                new RadioQuestion(R.id.programmingLanguageRadioQuestion, true,
                        new RadioQuestion.Option(R.id.javaProgrammingLanguage_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.cppProgrammingLanguage_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.labviewProgrammingLanguage_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.otherProgrammingLanguage_Radiobtn, 3)),

                new TextQuestion(R.id.pit_arcadeGame_editText, true),
                new TextQuestion(R.id.pit_comments_editText, true)
        };
        return questions;
    }

    @Override
    public String getCSVRowHeader() {
        return String.valueOf(Scouting.getInstance().getTeamNumber());
    }
}
