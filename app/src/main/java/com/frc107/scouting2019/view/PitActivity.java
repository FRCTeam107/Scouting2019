package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import androidx.annotation.NonNull;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.RadioQuestionOption;
import com.frc107.scouting2019.model.question.TextQuestion;
import com.frc107.scouting2019.viewmodel.PitViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.frc107.scouting2019.utils.FormatStringUtils;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.StringUtils;
import com.frc107.scouting2019.utils.ViewUtils;

/**
 * Created by Matt on 9/30/2017.
 */

public class PitActivity extends AppCompatActivity {
    private EditText teamNumberEditText;
    private RadioGroup teleopPreferenceRadioGroup;
    private EditText cubeNumberInSwitchEditText;
    private EditText cubeNumberInScaleEditText;
    private EditText cubeNumberInExchangeEditText;
    private RadioGroup climbRadioGroup;
    private RadioGroup climbHelpRadioGroup;
    private RadioGroup programmingLanguageRadioGroup;
    private EditText arcadeGameEditText;
    private EditText commentsEditText;

    private PitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit);

        Question[] questions = {
                new RadioQuestion(R.id.pit_teleopPreference_RadiobtnGrp, true,
                        new RadioQuestionOption(R.id.pitTeleopScale_btn, getString(R.string.pitTeleopScale)),
                        new RadioQuestionOption(R.id.pitTeleopSwitch_btn, getString(R.string.pitTeleopSwitch))),
                new TextQuestion(R.id.pit_cubeNumberInSwitch_editText, true),
                new TextQuestion(R.id.pit_cubeNumberInScale_editText, true),
                new TextQuestion(R.id.pit_cubeNumberInExchange_editText, true),
                new RadioQuestion(R.id.pit_climbBoolean_RadiobtnGrp, true,
                        new RadioQuestionOption(R.id.pitClimbYes_btn, getString(R.string.pitClimbYes)),
                        new RadioQuestionOption(R.id.pitClimbNo_btn, getString(R.string.pitClimbNo))),
                new RadioQuestion(R.id.pit_climbHelpBoolean_RadiobtnGrp, true,
                        new RadioQuestionOption(R.id.pitClimbHelp1_btn, getString(R.string.pitClimbHelp1)),
                        new RadioQuestionOption(R.id.pitClimbHelp2_btn, getString(R.string.pitClimbHelp2)),
                        new RadioQuestionOption(R.id.pitClimbHelpNo_btn, getString(R.string.pitClimbHelpNo))),
                new RadioQuestion(R.id.pit_programmingLanguage_RadiobtnGrp, true,
                        new RadioQuestionOption(R.id.pit_programmingLanguageJava_btn, getString(R.string.pitJava)),
                        new RadioQuestionOption(R.id.pit_programmingLanguageCpp_btn, getString(R.string.pitCpp)),
                        new RadioQuestionOption(R.id.pit_programmingLanguageLabview_btn, getString(R.string.pitLabview)),
                        new RadioQuestionOption(R.id.pit_programmingLanguageOther_btn, getString(R.string.pitOther))),
                new TextQuestion(R.id.pit_arcadeGame_editText, true),
                new TextQuestion(R.id.pit_comments_editText, true)
        };
        viewModel = new PitViewModel("Pit", questions);

        checkForPermissions();
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

    public void savePitData(View view) throws IOException {
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

    public void takePhoto(View view) {
        String teamNumber = viewModel.getAnswerForQuestion(R.id.pit_teamNumber_editText);
        if (teamNumber == null) {
            ViewUtils.requestFocus(findViewById(R.id.pit_teamNumber_editText), this);
            return;
        }

        boolean hasCameraPermissions = PermissionUtils.getPermissions(this, Manifest.permission.CAMERA);
        if (!hasCameraPermissions) {
            Toast.makeText(getApplicationContext(), "No camera permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        boolean hasWritePermissions = PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasWritePermissions) {
            Toast.makeText(getApplicationContext(), "No write permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        boolean hasReadPermissions = PermissionUtils.getPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!hasReadPermissions) {
            Toast.makeText(getApplicationContext(), "No read permissions.", Toast.LENGTH_LONG).show();
            checkForPermissions();
            return;
        }

        File photoFile = viewModel.createPhotoFile();

        Uri outputUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            startActivityForResult(takePictureIntent, 0);
        } else {
            Toast.makeText(getApplicationContext(), "Failure trying to take picture.", Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                compressPhoto();
            }
        }
    }

    private void compressPhoto() {
        try {
            String name = getTextInputLayoutString(pitTeamNumberInputLayout);

            File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
            File file = new File(dir, name + ".jpg");

            FileInputStream inputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out);

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(out.toByteArray());
            inputStream.close();
            out.close();
            outputStream.close();

            Toast.makeText(this, "Photo taken!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
            Toast.makeText(this, "Failed to save photo. Try again!", Toast.LENGTH_LONG).show();
        }
    }

    private void checkForPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }
}
