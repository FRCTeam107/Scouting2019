package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.CycleViewModel;

import androidx.appcompat.app.AppCompatActivity;



public class CycleActivity extends AppCompatActivity {
    private RadioGroup pickupLocationRadioGroup;
    private RadioGroup itemPickedUpRadioGroup;
    private RadioGroup itemPlacedRadioGroup;
    private CheckBox defenseCheckbox;
    private CheckBox allDefenseCheckbox;

    private CycleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        viewModel = new CycleViewModel();

        getSupportActionBar().setTitle("Team: " + Scouting.getInstance().getTeamNumber());

        pickupLocationRadioGroup = findViewById(R.id.pickupLocationRadioQuestion);
        itemPickedUpRadioGroup = findViewById(R.id.itemPickedUpRadioQuestion);
        itemPlacedRadioGroup = findViewById(R.id.itemPlacedRadioQuestion);
        defenseCheckbox = findViewById(R.id.defense_chkbx);
        allDefenseCheckbox = findViewById(R.id.allDefense_chkbx);

        defenseCheckbox.setVisibility(View.INVISIBLE);
        allDefenseCheckbox.setVisibility(View.INVISIBLE);

        pickupLocationRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.pickupLocationRadioQuestion, checkedId));
        itemPickedUpRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPickedUpRadioQuestion, checkedId));
        itemPlacedRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPlacedRadioQuestion, checkedId));
        defenseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.defense_chkbx, isChecked));
        allDefenseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setAnswer(R.id.allDefense_chkbx, isChecked);
            setAllDefense(isChecked);
        });
    }

    private void setAllDefense(boolean allDefense) {
        boolean questionsEnabled = !allDefense;
        ViewUtils.setRadioGroupEnabled(pickupLocationRadioGroup, questionsEnabled);
        ViewUtils.setRadioGroupEnabled(itemPickedUpRadioGroup, questionsEnabled);
        ViewUtils.setRadioGroupEnabled(itemPlacedRadioGroup, questionsEnabled);

        defenseCheckbox.setEnabled(questionsEnabled);
        defenseCheckbox.setChecked(allDefense);

        if (allDefense) {
            pickupLocationRadioGroup.clearCheck();
            itemPickedUpRadioGroup.clearCheck();
            itemPlacedRadioGroup.clearCheck();
        } else {
            findViewById(R.id.nothingPlacedItemPlaced_Radiobtn).setEnabled(false);
        }

        viewModel.setAllDefense(allDefense);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewModel.isTeleop())
            getMenuInflater().inflate(R.menu.cycle_teleop_menu, menu);
        else
            getMenuInflater().inflate(R.menu.cycle_sandstorm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.send_data:
                startActivity(new Intent(this, AdminActivity.class));
                return true;
            case R.id.enter_teleop_cycle:
                goToTeleop();
                return true;
            case R.id.go_to_endgame:
                goToEndGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToTeleop() {
        boolean cycleCanBeFinished = viewModel.cycleCanBeFinished();
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (!cycleCanBeFinished && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (cycleCanBeFinished && unfinishedQuestionId == -1)
            viewModel.finishCycle();

        viewModel.turnTeleopOn();

        clearAnswers();

        invalidateOptionsMenu(); // Calling this tells Android to call onCreateOptionsMenu.

        defenseCheckbox.setVisibility(View.VISIBLE);
        allDefenseCheckbox.setVisibility(View.VISIBLE);

        findViewById(R.id.nothingPlacedItemPlaced_Radiobtn).setEnabled(false);
    }

    private void goToEndGame() {
        boolean cycleCanBeFinished = viewModel.cycleCanBeFinished();
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (!cycleCanBeFinished && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (cycleCanBeFinished && unfinishedQuestionId == -1)
            viewModel.finishCycle();

        final Intent intent = new Intent(this, EndGameActivity.class);
        startActivity(intent);

        finish();
    }

    public void goToNextCycle(View view) {
        boolean cycleCanBeFinished = viewModel.cycleCanBeFinished();
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (!cycleCanBeFinished && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (cycleCanBeFinished && unfinishedQuestionId == -1)
            viewModel.finishCycle();

        clearAnswers();
    }

    public void clearCycle(View view) {
        clearAnswers();
    }

    private void clearAnswers() {
        pickupLocationRadioGroup.clearCheck();
        itemPickedUpRadioGroup.clearCheck();
        itemPlacedRadioGroup.clearCheck();
        defenseCheckbox.setChecked(false);
        allDefenseCheckbox.setChecked(false);
    }
}
