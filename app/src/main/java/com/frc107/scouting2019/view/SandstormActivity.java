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
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.AutonViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SandstormActivity extends AppCompatActivity {
    /*This area sets and binds all of the variables that we will use in the auton activity*/
    public static final String AUTON_STRING_EXTRA = "auton_extra";

    /* These are the names of the match number and team number extras that will be passed into teleop */
    public static final String MATCH_STRING_EXTRA = "match_extra";
    public static final String TEAM_NUMBER_STRING_EXTRA = "teamnumber_extra";
    public static final int REQUEST_CODE = 1;

    private EditText teamNumberEditText;
    private TextWatcher teamNumberTextWatcher;
    private EditText matchNumberEditText;
    private TextWatcher matchNumberTextWatcher;
    private RadioGroup testRadioGroup;

    private AutonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

        Question[] questions = {
                new RadioQuestion(R.id.sandstormStartingPositionRadioQuestion, true,
                        new RadioQuestion.Option(R.id.habTwoSandstorm_Radiobtn, getString(R.string.habTwoSandstorm)),
                        new RadioQuestion.Option(R.id.habOneSandstorm_Radiobtn, getString(R.string.habOneSandstorm))),
                new RadioQuestion(R.id.sandstormStartingGamePieceRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoSandstormStartingGamePiece_Radiobtn, getString(R.string.cargoSandstormStartingGamePiece)),
                        new RadioQuestion.Option(R.id.panelSandstormStartingGamePiece_Radiobtn, getString(R.string.panelSandstormStartingGamePiece)),
                        new RadioQuestion.Option(R.id.noSandstormStartingGamePiece_Radiobtn, getString(R.string.noSandstormStartingGamePiece))),


        };

        viewModel = new AutonViewModel(questions);


        RadioGroup sandstormStartingPositionRadioQuestion = findViewById(R.id.sandstormStartingPositionRadioQuestion);
        sandstormStartingPositionRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingPositionRadioQuestion, checkedId));

        RadioGroup sandstormStartingGamePieceRadioQuestion = findViewById(R.id.sandstormStartingGamePieceRadioQuestion);
        sandstormStartingGamePieceRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingGamePieceRadioQuestion, checkedId));


        teamNumberEditText = findViewById(R.id.teamNumberEditText);
        teamNumberTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setTeamNumber(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        };
        teamNumberEditText.addTextChangedListener(teamNumberTextWatcher);

        matchNumberEditText = findViewById(R.id.matchNumberEditText);
        matchNumberTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setMatchNumber(s.toString());
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

        testRadioGroup.setOnCheckedChangeListener(null);
        testRadioGroup = null;

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

    public void goToTeleop(View view) {
        int unfinishedQuestionId = viewModel.getFirstUnfinishedQuestionId();
        if (unfinishedQuestionId != -1) {
            ViewUtils.requestFocus(findViewById(unfinishedQuestionId), this);
            return;
        }

        final Intent intent = new Intent(this, TeleopActivity.class);
        intent.putExtra(AUTON_STRING_EXTRA, viewModel.getAnswerCSVRow());
        intent.putExtra(MATCH_STRING_EXTRA, viewModel.getMatchNumber());
        intent.putExtra(TEAM_NUMBER_STRING_EXTRA, viewModel.getTeamNumber());

        startActivityForResult(intent, REQUEST_CODE);
    }
}
