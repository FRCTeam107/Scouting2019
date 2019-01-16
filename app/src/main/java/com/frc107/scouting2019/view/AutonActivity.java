package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.data.RadioButtonQuestion;
import com.frc107.scouting2019.model.data.RadioQuestionOption;
import com.frc107.scouting2019.model.data.TextQuestion;
import com.frc107.scouting2019.viewmodel.AutonViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AutonActivity extends AppCompatActivity {
    private AutonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);

        viewModel = new AutonViewModel(
                new TextQuestion(R.id.testEditText, true),
                new RadioButtonQuestion(R.id.testRadioQuestion, true, new RadioQuestionOption(R.id.leftStartingLocation_Radiobtn, getString(R.string.leftStarting)),
                                                                                    new RadioQuestionOption(R.id.centerStartingLocation_Radiobtn, getString(R.string.centerStarting)),
                                                                                    new RadioQuestionOption(R.id.rightStartingLocation_Radiobtn, getString(R.string.rightStarting)))
        );
        ((EditText) findViewById(R.id.testEditText)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setAnswer(R.id.testEditText, s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });
        ((RadioGroup) findViewById(R.id.testRadioQuestion)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewModel.setAnswer(R.id.testRadioQuestion, checkedId);
            }
        });

        checkForPermissions();
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

    /* This method will change the text entered into the app into a string if it is not already*/
    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }

    private void checkForPermissions() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
