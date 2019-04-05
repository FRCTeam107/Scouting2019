package com.frc107.scouting2019.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.utils.PermissionUtils;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            case R.id.admin_activity:
                startActivity(new Intent(this, AdminActivity.class));
                return true;
            case R.id.send_data:
                sendFile(Scouting.FILE_UTILS.getMatchFile());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendFile(File file) {
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
