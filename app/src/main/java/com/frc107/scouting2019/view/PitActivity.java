package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.PitViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

/**
 * Created by Matt on 9/30/2017.
 */

public class PitActivity extends AppCompatActivity {
    private EditText teamNumberEditText;
    private RadioGroup teleopPreferenceRadioGroup;
    private EditText cubeNumberInSwitchEditText;
    private EditText cubeNumberInScaleEditText;
    private EditText cubeNumberInExchangeEditText;
    private RadioGroup climbRadioGroup;
    private RadioGroup climbHelpRadioGroup;
    private RadioGroup programmingLanguageRadioGroup;
    private EditText habitatTimeEditText;
    private EditText arcadeGameEditText;
    private EditText commentsEditText;

    private PitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit);

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
        viewModel = new PitViewModel(questions);

        RadioGroup sandstormOperationsRadioQuestion = findViewById(R.id.sandstormOperationsRadioQuestion);
        sandstormOperationsRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormOperationsRadioQuestion, checkedId));
        RadioGroup sandstormPreferenceRadioQuestion = findViewById(R.id.sandstormPreferenceRadioQuestion);
        sandstormPreferenceRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormPreferenceRadioQuestion, checkedId));
        RadioGroup highestRocketLevelSandstormRadioQuestion = findViewById(R.id.highestRocketLevelSandstormRadioQuestion);
        highestRocketLevelSandstormRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.highestRocketLevelSandstormRadioQuestion, checkedId));
        RadioGroup highestHabitatLevelRadioQuestion = findViewById(R.id.highestHabitatLevelRadioQuestion);
        highestHabitatLevelRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.highestHabitatLevelRadioQuestion, checkedId));
        RadioGroup programmingLanguageRadioQuestion = findViewById(R.id.programmingLanguageRadioQuestion);
        programmingLanguageRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.programmingLanguageRadioQuestion, checkedId));

        teamNumberEditText = findViewById(R.id.pit_teamNumber_editText);
        habitatTimeEditText = findViewById(R.id.pit_habitatTime_editText);
        arcadeGameEditText = findViewById(R.id.pit_arcadeGame_editText);
        commentsEditText = findViewById(R.id.pit_comments_editText);

        teamNumberEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setAnswer(R.id.pit_teamNumber_editText, s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });


        habitatTimeEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setAnswer(R.id.pit_habitatTime_editText, s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });


        arcadeGameEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setAnswer(R.id.pit_arcadeGame_editText, s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });
        commentsEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setAnswer(R.id.pit_comments_editText, s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });

        checkForPermissions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.send_data:
                startActivity(new Intent(this, SendDataActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void savePitData(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocus(findViewById(unfinishedQuestionId), this);
            return;
        }

        boolean hasWritePermissions = PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasWritePermissions) {
            Toast.makeText(getApplicationContext(), "No write permissions.", Toast.LENGTH_LONG).show();
            return;
        }

        String saveResponse = viewModel.save();

        Toast.makeText(getApplicationContext(), saveResponse, Toast.LENGTH_LONG).show();

        setResult(RESULT_OK);

        finish();
    }

    public void takeAndCompressPhoto(View view) {
        String teamNumber = viewModel.getAnswerForQuestion(R.id.pit_teamNumber_editText);
        if (teamNumber == null) {
            ViewUtils.requestFocus(findViewById(R.id.pit_teamNumber_editText), this);
            return;
        }

        boolean hasCameraPermissions = PermissionUtils.getPermissions(this, Manifest.permission.CAMERA);
        if (!hasCameraPermissions) {
            Toast.makeText(getApplicationContext(), "No camera permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        boolean hasWritePermissions = PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasWritePermissions) {
            Toast.makeText(getApplicationContext(), "No write permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        boolean hasReadPermissions = PermissionUtils.getPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!hasReadPermissions) {
            Toast.makeText(getApplicationContext(), "No read permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        File photoFile = viewModel.createPhotoFile();
        Uri outputUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            startActivityForResult(takePictureIntent, 0);
        } else {
            Toast.makeText(getApplicationContext(), "Failure trying to take picture.", Toast.LENGTH_LONG).show();
            return;
        }

        boolean didCompressPhoto = viewModel.compressPhoto();
        if (!didCompressPhoto) {
            Toast.makeText(getApplicationContext(), "Failure while compressing picture.", Toast.LENGTH_LONG).show();
        }
    }

    private void checkForPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }
}
