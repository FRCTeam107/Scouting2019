package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.utils.PermissionUtils;
import com.frc107.scouting2019.viewmodel.AdminViewModel;

import java.io.File;
import java.util.ArrayList;

import androidx.core.content.FileProvider;

/**
 * Created by Matt on 10/9/2017.
 */

public class AdminActivity extends BaseActivity {
    private AdminViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        viewModel = new AdminViewModel();
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
            Toast.makeText(this, "No photos.", Toast.LENGTH_SHORT).show();
        } else {
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
            startActivity(Intent.createChooser(intent, "Share app"));
        }
    }

    public void sendPitData(View view) {
        sendFile(viewModel.getPitFile(false));
    }

    public void sendConcatMatchData(View view) {
        sendFile(viewModel.getMatchFile(true));
    }

    public void sendConcatPitData(View view) {
        sendFile(viewModel.getPitFile(true));
    }
}