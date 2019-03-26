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
import com.frc107.scouting2019.view.wrappers.RadioWrapper;
import com.frc107.scouting2019.viewmodel.CycleViewModel;

import androidx.appcompat.app.AppCompatActivity;



public class CycleActivity extends AppCompatActivity {
    private RadioWrapper pickupLocationWrapper;
    private RadioWrapper itemPickedUpWrapper;
    private RadioWrapper itemPlacedWrapper;

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

        pickupLocationWrapper = new RadioWrapper(findViewById(R.id.pickupLocationRadioQuestion), viewModel);
        itemPickedUpWrapper = new RadioWrapper(findViewById(R.id.itemPickedUpRadioQuestion), viewModel);
        itemPlacedWrapper = new RadioWrapper(findViewById(R.id.itemPlacedRadioQuestion), viewModel);

        pickupLocationRadioGroup = findViewById(R.id.pickupLocationRadioQuestion);
        itemPickedUpRadioGroup = findViewById(R.id.itemPickedUpRadioQuestion);
        itemPlacedRadioGroup = findViewById(R.id.itemPlacedRadioQuestion);
        defenseCheckbox = findViewById(R.id.defense_chkbx);
        allDefenseCheckbox = findViewById(R.id.allDefense_chkbx);

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
        }

        viewModel.setAllDefense(allDefense);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        pickupLocationWrapper.cleanUp();
        itemPickedUpWrapper.cleanUp();
        itemPlacedWrapper.cleanUp();
        defenseCheckbox.setOnCheckedChangeListener(null);

        viewModel = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cycle_teleop_menu, menu);
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
            case R.id.go_to_endgame:
                goToEndGame();
                return true;
            case R.id.clear_cycle:
                clearAnswers();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        /**
         * TODO: I think you don't need to check unfinishedQuestionId. Just check cycleCanBeFinished
         * and then use unfinishedQuestionId inside the if block
         */
        if (!cycleCanBeFinished && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (unfinishedQuestionId == -1)
            viewModel.finishCycle();

        clearAnswers();
    }

    private void clearAnswers() {
        pickupLocationWrapper.clear();
        itemPickedUpWrapper.clear();
        itemPlacedWrapper.clear();
        defenseCheckbox.setChecked(false);
        allDefenseCheckbox.setChecked(false);
    }
}
