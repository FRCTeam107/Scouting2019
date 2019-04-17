package com.frc107.scouting2019.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.frc107.scouting2019.BuildConfig;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;

import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForPermissions();

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        TextView versionTextView = findViewById(R.id.versionNum_textView);
        versionTextView.setText(String.format("v%s - %d", versionName, versionCode));

        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Scouting.getInstance().setUniqueId(uniqueId);
    }

    public void showMatch(View view) {
        startActivity(new Intent(this, ScouterInitialsActivity.class));
    }

    public void showPit(View view) {
        startActivity(new Intent(this, PitActivity.class));
    }

    public void showAdmin(View view) {
        startActivity(new Intent(this, AdminActivity.class));
    }
}
