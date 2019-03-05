package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.utils.ViewUtils;
import com.frc107.scouting2019.viewmodel.SendDataViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import butterknife.BindView;

/**
 * Created by Matt on 10/9/2017.
 */

public class SendDataActivity extends AppCompatActivity {
    private SendDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        viewModel = new SendDataViewModel();
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

    public void concatenateMatchData(View view) {
        String result = "Failure concatenating data.";
        if (viewModel.concatenateMatchData()) {
            result = "Successfully concatenated data.";
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    public void concatenatePitData(View view) {
        String result = "Failure concatenating data.";
        if (viewModel.concatenatePitData()) {
            result = "Successfully concatenated data.";
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    public void sendRobotPhotos(View view) {
        if (!PermissionUtils.getPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/jpeg");
        intent.setPackage("com.android.bluetooth");

        ArrayList<Uri> uriList = viewModel.getPhotoUriList(this);
        if (uriList.isEmpty()) {
            Toast.makeText(this, "No photos!", Toast.LENGTH_SHORT).show();
        } else {
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
            startActivity(Intent.createChooser(intent, "Share app"));
        }
    }

    public void sendMatchData(View view) {
        sendFile(viewModel.getMatchFile());
    }

    public void sendPitData(View view) {
        sendFile(viewModel.getPitFile());
    }

    public void sendConcatMatchData(View view) {
        sendFile(viewModel.getConcatMatchFile());
    }

    public void sendConcatPitData(View view) {
        sendFile(viewModel.getConcatPitFile());
    }

    private void sendFile(File file) {
        if (file == null) {
            Toast.makeText(this, "File does not exist.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(PermissionUtils.getPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage("com.android.bluetooth");
            intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file));
            startActivity(Intent.createChooser(intent, "Share app"));
        }
    }
}