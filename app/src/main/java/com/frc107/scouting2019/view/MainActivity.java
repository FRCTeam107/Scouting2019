package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;

import com.frc107.scouting2019.DuckActivity;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String uniqueId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Scouting.setUniqueId(uniqueId);

        Intent duckIntent = new Intent(this, DuckActivity.class);

        Button sendDataButton = findViewById(R.id.send_data_button);
        sendDataButton.setOnLongClickListener(v -> {
            startActivity(duckIntent);
            return true;
        });
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

    public void showMatch(View view) {
        startActivity(new Intent(this, ScouterInitialsActivity.class));
    }

    public void showPit(View view) {
        startActivity(new Intent(this, PitActivity.class));
    }

    public void sendData(View view) {
        startActivity(new Intent(this, SendDataActivity.class));
    }
}
