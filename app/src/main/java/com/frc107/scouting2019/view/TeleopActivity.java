package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.question.CheckBoxQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.TeleopViewModel;

import androidx.appcompat.app.AppCompatActivity;

import static com.frc107.scouting2019.view.AutonActivity.AUTON_STRING_EXTRA;
import static com.frc107.scouting2019.view.AutonActivity.MATCH_STRING_EXTRA;
import static com.frc107.scouting2019.view.AutonActivity.TEAM_NUMBER_STRING_EXTRA;

public class TeleopActivity extends AppCompatActivity {
    private CheckBox foulsCheckBox;

    private TeleopViewModel viewModel;

    private String autonData;
    private String teamNumber;
    private String matchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        autonData = bundle.getString(AUTON_STRING_EXTRA);
        teamNumber = bundle.getString(MATCH_STRING_EXTRA);
        matchNumber = bundle.getString(TEAM_NUMBER_STRING_EXTRA);

        Question[] questions = {
                new CheckBoxQuestion(R.id.fouls_chkbx)
        };
        viewModel = new TeleopViewModel(questions);

        foulsCheckBox = findViewById(R.id.fouls_chkbx);
        foulsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.fouls_chkbx, isChecked));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foulsCheckBox.setOnCheckedChangeListener(null);
        foulsCheckBox = null;

        viewModel = null;
    }

    public void saveData(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocus(findViewById(unfinishedQuestionId), this);
            return;
        }

        boolean hasWritePermissions = PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String saveResponse = viewModel.save(hasWritePermissions, uniqueId, autonData);

        Toast.makeText(getApplicationContext(), saveResponse, Toast.LENGTH_LONG).show();

        setResult(RESULT_OK);

        finish();
    }
}
