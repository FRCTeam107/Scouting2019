package com.frc107.scouting2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.frc107.scouting2019.admin.AdminActivity;
import com.frc107.scouting2019.initials.InitialsActivity;
import com.frc107.scouting2019.pit.PitActivity;
import com.frc107.scouting2019.ui.BaseActivity;

import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForPermissions();

        TextView versionTextView = findViewById(R.id.versionNum_textView);
        versionTextView.setText(Scouting.VERSION_DATE);

        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Scouting.getInstance().setUniqueId(uniqueId);

        SharedPreferences pref = getSharedPreferences(Scouting.PREFERENCES_NAME, MODE_PRIVATE);
        String eventKey = pref.getString(Scouting.EVENT_KEY_PREFERENCE, "");
        Scouting.getInstance().setEventKey(eventKey);
    }

    public void showMatch(View view) {
        startActivity(new Intent(this, InitialsActivity.class));
    }

    public void showPit(View view) {
        startActivity(new Intent(this, PitActivity.class));
    }

    public void showAdmin(View view) {
        startActivity(new Intent(this, AdminActivity.class));
    }
}