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
import com.frc107.scouting2019.viewmodel.SandstormViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SandstormActivity extends AppCompatActivity {

    private EditText teamNumberEditText;
    private TextWatcher teamNumberTextWatcher;
    private EditText matchNumberEditText;
    private TextWatcher matchNumberTextWatcher;
    private RadioGroup sandstormStartingPositionRadioQuestion;
    private RadioGroup itemPickedUpRadioGroup;

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

        teamNumberEditText = findViewById(R.id.teamNumberEditText);
        teamNumberTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int teamNumber = Integer.valueOf(s.toString());
                Scouting.getInstance().setTeamNumber(teamNumber);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        };
        teamNumberEditText.addTextChangedListener(teamNumberTextWatcher);

        matchNumberEditText = findViewById(R.id.matchNumberEditText);
        matchNumberTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int matchNumber = Integer.valueOf(s.toString());
                Scouting.getInstance().setMatchNumber(matchNumber);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        };
        matchNumberEditText.addTextChangedListener(matchNumberTextWatcher);


        checkForPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        teamNumberEditText.removeTextChangedListener(teamNumberTextWatcher);
        teamNumberEditText = null;
        teamNumberTextWatcher = null;

        matchNumberEditText.removeTextChangedListener(matchNumberTextWatcher);
        matchNumberEditText = null;
        matchNumberTextWatcher = null;

        viewModel = null;
    }

    /* This method will display the options menu when the icon is pressed
     * and this will inflate the menu options for the user to choose
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /*This method will launch the correct activity
     *based on the menu option user presses
      */
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

    private void checkForPermissions() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void goToCycle(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocus(findViewById(unfinishedQuestionId), this);
            return;
        }

        viewModel.finish();
        clearAnswers();

        final Intent intent = new Intent(this, CycleActivity.class);

        startActivity(intent);

    }

    private void clearAnswers() {

        teamNumberEditText.setText("");


        matchNumberEditText.setText("");

        sandstormStartingPositionRadioQuestion.clearCheck();
        itemPickedUpRadioGroup.clearCheck();
    }
}
