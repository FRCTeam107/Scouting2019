package com.frc107.scouting2019.view;

import android.content.Intent;
import android.os.Bundle;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.viewmodel.InitialsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ScouterInitialsActivity extends AppCompatActivity {
    private InitialsViewModel viewModel;

    private TextInputEditText scouterInitialsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouter_initials);
        viewModel = new InitialsViewModel();

        scouterInitialsEditText = findViewById(R.id.scouterInitials_input);
        scouterInitialsEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                viewModel.setInitials(s.toString());
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) { }
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

    public void submitInitials(View view) {
        String initals = viewModel.getInitials();
        if (initals.length() == 0)
            return;

        startActivity(new Intent(this, SandstormActivity.class));
    }
}