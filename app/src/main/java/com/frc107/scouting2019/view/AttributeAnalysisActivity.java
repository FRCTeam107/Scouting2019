package com.frc107.scouting2019.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.viewmodel.AttributeAnalysisViewModel;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.frc107.scouting2019.R;

public class AttributeAnalysisActivity extends BaseActivity implements IUIAnalysisListener {
    private ListView elementListView;
    private Button chooseAttributeButton;
    private AttributeAnalysisViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_analysis);
        elementListView = findViewById(R.id.elementListView);
        chooseAttributeButton = findViewById(R.id.analysisChooseAttributeButton);
        viewModel = new AttributeAnalysisViewModel(this);
        viewModel.loadData();
    }

    @Override
    public void onDataLoaded() {
        findViewById(R.id.analysisProgressBar).setVisibility(View.GONE);
        AnalysisAdapter elementAdapter = new AnalysisAdapter(this, viewModel.getElements());
        elementListView.setAdapter(elementAdapter);
    }

    public void attributeButtonPressed(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick an attribute");
        dialogBuilder.setItems(Scouting.ATTRIBUTES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: implement functionality
            }
        });
        dialogBuilder.show();
    }
}
