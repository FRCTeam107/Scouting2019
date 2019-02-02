package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.EndGameViewModel;
import com.frc107.scouting2019.viewmodel.SandstormViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EndGameActivity extends AppCompatActivity {


    private RadioGroup testRadioGroup;

    private EndGameViewModel viewModel;

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

        viewModel = new EndGameViewModel(questions);


        RadioGroup sandstormStartingPositionRadioQuestion = findViewById(R.id.sandstormStartingPositionRadioQuestion);
        sandstormStartingPositionRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingPositionRadioQuestion, checkedId));

        RadioGroup sandstormStartingGamePieceRadioQuestion = findViewById(R.id.sandstormStartingGamePieceRadioQuestion);
        sandstormStartingGamePieceRadioQuestion.setOnCheckedChangeListener((group, checkedId) -> viewModel.setAnswer(R.id.sandstormStartingGamePieceRadioQuestion, checkedId));


        checkForPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



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

        String saveResponse = viewModel.save();

        Toast.makeText(getApplicationContext(), saveResponse, Toast.LENGTH_LONG).show();

        setResult(RESULT_OK);

        finish();
    }
}
