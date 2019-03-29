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

    private String getTeamNumber() {
        return getAnswerForQuestion(R.id.pit_teamNumber_editText);
    }

    public File createPhotoFile() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
        dir.mkdirs();

        File file = new File(dir, getTeamNumber() + ".jpg");

        try {
            file.createNewFile();
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
            return null;
        }

        return file;
    }

    public boolean compressPhoto() {
        return Scouting.FILE_UTILS.compressPhoto(getTeamNumber());
    }

    @Override
    public Question[] getQuestions() {
        Question[] questions = {
                new TextQuestion("pitTeamNum", R.id.pit_teamNumber_editText, true, 0),
                new RadioQuestion("pitSandstormOp", R.id.sandstormOperationsRadioQuestion, true, 1,
                        new RadioQuestion.Option(R.id.visionSystemSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.cameraDrivingSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.blindDrivingSandstorm_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noDrivingSandstorm_Radiobtn, 3)),
                new RadioQuestion("pitSandstormPref", R.id.sandstormPreferenceRadioQuestion, true,2,
                        new RadioQuestion.Option(R.id.cargoshipPreferenceSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.rocketshipPreferenceSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.noPreferenceSandstorm_Radiobtn, 2)),
                new RadioQuestion("pitHighestRocketLevel", R.id.highestRocketLevelSandstormRadioQuestion, true, 3,
                        new RadioQuestion.Option(R.id.topRocketLevelSandstorm_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.middleRocketLevelSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.bottomRocketLevelSandstorm_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noRocketLevelSandstorm_Radiobtn, 3)),
                new RadioQuestion("pitHighestHabLevel", R.id.highestHabitatLevelRadioQuestion, true, 4,
                        new RadioQuestion.Option(R.id.topHabitatLevel_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.middleHabitatLevel_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.bottomHabitatLevel_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noHabitatLevel_Radiobtn, 3)),
                new TextQuestion("pitHabTime", R.id.pit_habitatTime_editText, true, 5),
                new RadioQuestion("pitLanguage", R.id.programmingLanguageRadioQuestion, true, 6,
                        new RadioQuestion.Option(R.id.javaProgrammingLanguage_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.cppProgrammingLanguage_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.labviewProgrammingLanguage_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.otherProgrammingLanguage_Radiobtn, 3)),

                new TextQuestion("pitBonus", R.id.pit_bonusQuestion_editText, true, 7),
                new TextQuestion("pitComments", R.id.pit_comments_editText, true, 8)
        };
        return questions;
    }

    @Override
    public void onNumberQuestionAnswered(int questionId, Integer answer) { }

    @Override
    public void onTextQuestionAnswered(int questionId, String answer) { }

    @Override
    public void onRadioQuestionAnswered(int questionId, int answerId) { }
}
