package com.frc107.scouting2019.view;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.frc107.scouting2019.R;

public class AttributeAnalysisActivity extends BaseActivity implements IUIAnalysisListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_analysis);
    }

    @Override
    public void onDataLoaded() {

    }
}
