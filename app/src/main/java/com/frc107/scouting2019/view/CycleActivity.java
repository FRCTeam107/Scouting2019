package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.ToggleQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.CycleViewModel;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;



public class CycleActivity extends AppCompatActivity {
    private RadioGroup pickupLocationRadioGroup;
    private RadioGroup itemPickedUpRadioGroup;
    private RadioGroup itemPlacedRadioGroup;
    private CheckBox defenseCheckbox;

    private CycleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        viewModel = new CycleViewModel();

        getSupportActionBar().setTitle("Team: " + Scouting.getInstance().getTeamNumber());

        pickupLocationRadioGroup = findViewById(R.id.pickupLocationRadioQuestion);
        pickupLocationRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.pickupLocationRadioQuestion, checkedId));

        itemPickedUpRadioGroup = findViewById(R.id.itemPickedUpRadioQuestion);
        itemPickedUpRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPickedUpRadioQuestion, checkedId));

        itemPlacedRadioGroup = findViewById(R.id.itemPlacedRadioQuestion);
        itemPlacedRadioGroup.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.itemPlacedRadioQuestion, checkedId));

        defenseCheckbox = findViewById(R.id.defense_chkbx);
        defenseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.setAnswer(R.id.defense_chkbx, isChecked));
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
                startActivity(new Intent(this, SendDataActivity.class));
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
        boolean allQuestionsAreUnanswered = viewModel.areNoQuestionsAnswered();

        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (!allQuestionsAreUnanswered && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (!allQuestionsAreUnanswered)
            viewModel.finishCycle();

        viewModel.turnTeleopOn();

        clearAnswers();

        invalidateOptionsMenu(); // Calling this tells Android to call onCreateOptionsMenu.
    }

    private void goToEndGame() {
        boolean allQuestionsAreUnanswered = viewModel.areNoQuestionsAnswered();

        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (!allQuestionsAreUnanswered && unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        if (!allQuestionsAreUnanswered)
            viewModel.finishCycle();

        final Intent intent = new Intent(this, EndGameActivity.class);
        startActivity(intent);

        finish();
    }

    public void goToNextCycle(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocusToUnfinishedQuestion(findViewById(unfinishedQuestionId), this);
            return;
        }

        if (!PermissionUtils.verifyWritePermissions(this))
            return;

        viewModel.finishCycle();
        clearAnswers();
    }

    private void clearAnswers() {
        pickupLocationRadioGroup.clearCheck();
        itemPickedUpRadioGroup.clearCheck();
        itemPlacedRadioGroup.clearCheck();
        defenseCheckbox.setChecked(false);
    }
}
