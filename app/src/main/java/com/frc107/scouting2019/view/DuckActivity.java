package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.view.AdminActivity;
import com.frc107.scouting2019.view.MainActivity;
import com.frc107.scouting2019.view.PitActivity;
import com.frc107.scouting2019.view.ScouterInitialsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DuckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck);
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
                startActivity(new Intent(this, AdminActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}