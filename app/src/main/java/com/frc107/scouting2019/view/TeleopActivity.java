package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.ToggleQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.TeleopViewModel;

import androidx.appcompat.app.AppCompatActivity;

import static com.frc107.scouting2019.view.SandstormActivity.AUTON_STRING_EXTRA;
import static com.frc107.scouting2019.view.SandstormActivity.MATCH_STRING_EXTRA;
import static com.frc107.scouting2019.view.SandstormActivity.TEAM_NUMBER_STRING_EXTRA;

public class TeleopActivity extends AppCompatActivity {
    private CheckBox foulsCheckBox;

    private TeleopViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        Bundle bundle = getIntent().getExtras();
        String autonData = bundle.getString(AUTON_STRING_EXTRA);
        String teamNumber = bundle.getString(MATCH_STRING_EXTRA);
        String matchNumber = bundle.getString(TEAM_NUMBER_STRING_EXTRA);

        Question[] questions = {
                new RadioQuestion(R.id.pickupLocationRadioQuestion, true,
                        new RadioQuestion.Option(R.id.portPickupLocation_Radiobtn, getString(R.string.portPickupLocation)),
                        new RadioQuestion.Option(R.id.floorPickupLocation_Radiobtn, getString(R.string.floorPickupLocation))),
                new RadioQuestion(R.id.itemPickedUpRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoItemPickedUp_Radiobtn, getString(R.string.cargoPickedUp)),
                        new RadioQuestion.Option(R.id.hatchItemPickedUp_Radiobtn, getString(R.string.hatchPickedUp))),
                new RadioQuestion(R.id.itemPlacedRadioQuestion, true,
                        new RadioQuestion.Option(R.id.topRocketItemPlaced_Radiobtn, getString(R.string.topRocketItemPlaced)),
                        new RadioQuestion.Option(R.id.middleRocketItemPlaced_Radiobtn, getString(R.string.middleRocketItemPlaced)),
                        new RadioQuestion.Option(R.id.bottomRocketItemPlaced_Radiobtn, getString(R.string.bottomRocketItemPlaced)),
                        new RadioQuestion.Option(R.id.cargoshipItemPlaced_Radiobtn, getString(R.string.cargoshipItemPlaced)),
                        new RadioQuestion.Option(R.id.floorItemPlaced_Radiobtn, getString(R.string.floorItemPlaced))),
                new ToggleQuestion(R.id.defense_chkbx)
        };
        viewModel = new TeleopViewModel(autonData, questions);
        viewModel.setTeamNumber(teamNumber);
        viewModel.setMatchNumber(matchNumber);

        RadioGroup pickupLocationRadioQuestion = findViewById(R.id.pickupLocationRadioQuestion);
        pickupLocationRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.pickupLocationRadioQuestion, checkedId));

        RadioGroup itemPickedUpRadioQuestion = findViewById(R.id.itemPickedUpRadioQuestion);
        itemPickedUpRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPickedUpRadioQuestion, checkedId));

        RadioGroup itemPlacedRadioQuestion = findViewById(R.id.itemPlacedRadioQuestion);
        itemPlacedRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPlacedRadioQuestion, checkedId));

        foulsCheckBox = findViewById(R.id.defense_chkbx);
        foulsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.defense_chkbx, isChecked));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foulsCheckBox.setOnCheckedChangeListener(null);
        foulsCheckBox = null;

        viewModel = null;
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

    public void saveData(View view) {
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

        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String saveResponse = viewModel.save(uniqueId);

        Toast.makeText(getApplicationContext(), saveResponse, Toast.LENGTH_LONG).show();

        setResult(RESULT_OK);

        finish();
    }
}
