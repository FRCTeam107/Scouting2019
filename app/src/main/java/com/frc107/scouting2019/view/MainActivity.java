package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;

import com.frc107.scouting2019.DuckActivity;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;

import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Scouting.getInstance().setUniqueId(uniqueId);

        Intent duckIntent = new Intent(this, DuckActivity.class);

        Button sendDataButton = findViewById(R.id.admin_button);
        sendDataButton.setOnLongClickListener(v -> {
            startActivity(duckIntent);
            return true;
        });
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
