package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.view.wrappers.TextWrapper;
import com.frc107.scouting2019.viewmodel.SandstormViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SandstormActivity extends BaseActivity {

    private RadioGroup sandstormStartingPositionRadioQuestion;
    private RadioGroup itemPickedUpRadioGroup;
    private RadioGroup itemPlacedSandstormRadioGroup;

    private TextWrapper teamNumWrapper;
    private TextWrapper matchNumWrapper;

    private SandstormViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

        viewModel = new SandstormViewModel();

        sandstormStartingPositionRadioQuestion = findViewById(R.id.sandstormStartingPositionRadioQuestion);
        sandstormStartingPositionRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingPositionRadioQuestion, checkedId));

        itemPickedUpRadioGroup = findViewById(R.id.sandstormStartingGamePieceRadioQuestion);
        itemPickedUpRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingGamePieceRadioQuestion, checkedId));

        itemPlacedSandstormRadioGroup = findViewById(R.id.sandstormItemPlacedRadioQuestion);
        itemPlacedSandstormRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormItemPlacedRadioQuestion, checkedId));

        teamNumWrapper = new TextWrapper(this, viewModel, R.id.teamNumberEditText);
        matchNumWrapper = new TextWrapper(this, viewModel, R.id.matchNumberEditText);

        checkForPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        teamNumWrapper.cleanUp();
        matchNumWrapper.cleanUp();

        viewModel = null;
    }

    private void checkForPermissions() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void goToCycle(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }
        viewModel.finish();
        clearAnswers();
        ViewUtils.requestFocus(teamNumWrapper.getEditText(), this);
        final Intent intent = new Intent(this, CycleActivity.class);
        startActivity(intent);

    }

    private void clearAnswers() {
        teamNumWrapper.clear();
        matchNumWrapper.clear();
        sandstormStartingPositionRadioQuestion.clearCheck();
        itemPickedUpRadioGroup.clearCheck();
        itemPlacedSandstormRadioGroup.clearCheck();
    }
}
