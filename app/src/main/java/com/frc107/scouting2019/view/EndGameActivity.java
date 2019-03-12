package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.view.wrappers.RadioWrapper;
import com.frc107.scouting2019.viewmodel.EndGameViewModel;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EndGameActivity extends BaseActivity {
    private RadioWrapper habLevelWrapper;
    private RadioWrapper defenseWrapper;

    private CheckBox defenseAllMatchCheckbox;
    private CheckBox foulsCheckbox;

    private EndGameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        viewModel = new EndGameViewModel();

        getSupportActionBar().setTitle("Team: " + Scouting.getInstance().getTeamNumber());

        habLevelWrapper = new RadioWrapper(findViewById(R.id.endGameHabitatLevelRadioQuestion), viewModel);
        defenseWrapper = new RadioWrapper(findViewById(R.id.endGameDefenseRadioQuestion), viewModel);

        defenseAllMatchCheckbox = findViewById(R.id.endGameDefenseAllMatch_chkbx);
        defenseAllMatchCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.endGameDefenseAllMatch_chkbx, isChecked));

        foulsCheckbox = findViewById(R.id.endGameFouls_chkbx);
        foulsCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.endGameFouls_chkbx, isChecked));

        checkForPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        habLevelWrapper.cleanUp();
        defenseWrapper.cleanUp();
        defenseAllMatchCheckbox.setOnCheckedChangeListener(null);
        foulsCheckbox.setOnCheckedChangeListener(null);

        viewModel = null;
    }

    private void checkForPermissions() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void saveData(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        boolean hasWritePermissions = PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasWritePermissions) {
            Toast.makeText(getApplicationContext(), "No write permissions.", Toast.LENGTH_LONG).show();
            return;
        }

        String saveResponse = viewModel.finish();

        Toast.makeText(getApplicationContext(), saveResponse, Toast.LENGTH_LONG).show();

        finish();
    }
}
