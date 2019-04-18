package com.frc107.scouting2019.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.viewmodel.TeamAnalysisViewModel;

import android.view.View;
import android.widget.TextView;

public class TeamAnalysisActivity extends BaseActivity implements IUIAnalysisListener {
    private TeamAnalysisViewModel viewModel;
    private TextView teamNumberTextView;
    private TextView averageCargoTextView;
    private TextView averageHatchTextView;
    private TextView defenseCountTextView;
    private TextView effectiveDefenseCountTextView;
    private TextView habOneTextView;
    private TextView habTwoTextView;
    private TextView habThreeTextView;
    private TextView rocketOneTextView;
    private TextView rocketTwoTextView;
    private TextView rocketThreeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        teamNumberTextView = findViewById(R.id.analysisTeamNumberTextView);
        averageCargoTextView = findViewById(R.id.averageCargoTextView);
        averageHatchTextView = findViewById(R.id.averageHatchPanelTextView);
        defenseCountTextView = findViewById(R.id.defenseCountTextView);
        effectiveDefenseCountTextView = findViewById(R.id.effectiveDefenseCountTextView);
        habOneTextView = findViewById(R.id.habOneTextView);
        habTwoTextView = findViewById(R.id.habTwoTextView);
        habThreeTextView = findViewById(R.id.habThreeTextView);
        rocketOneTextView = findViewById(R.id.rocketOneTextView);
        rocketTwoTextView = findViewById(R.id.rocketTwoTextView);
        rocketThreeTextView = findViewById(R.id.rockettThreeTextView);

        viewModel = new TeamAnalysisViewModel(this);
        viewModel.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel = null;
    }

    private void onTeamNumberChanged(String teamNumber) {
        if (teamNumber.length() == 0)
            teamNumber = "-1";

        viewModel.setTeamNumber(teamNumber);

        teamNumberTextView.setText(teamNumber);

        averageHatchTextView.setText(viewModel.getAverageHatchPanels() + "");
        averageCargoTextView.setText(viewModel.getAverageCargo() + "");
        
        defenseCountTextView.setText(viewModel.getDefenseCount() + "");
        effectiveDefenseCountTextView.setText(viewModel.getEffectiveDefenseCount() + "");
        
        habOneTextView.setText(viewModel.canClimbHabOne() + "");
        habTwoTextView.setText(viewModel.canClimbHabTwo() + "");
        habThreeTextView.setText(viewModel.canClimbHabThree() + "");
        
        rocketOneTextView.setText(viewModel.canAccessRocketOne() + "");
        rocketTwoTextView.setText(viewModel.canAccessRocketTwo() + "");
        rocketThreeTextView.setText(viewModel.canAccessRocketThree() + "");
    }

    @Override
    public void onDataLoaded() {
        findViewById(R.id.analysisProgressBar).setVisibility(View.GONE);
        findViewById(R.id.analysisTeamNumberButton).setEnabled(true);
    }

    public void teamNumberButtonPressed(View view) {
        String[] teamNumbers = viewModel.getTeamNumbers();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pick a team number");
        dialogBuilder.setItems(teamNumbers, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onTeamNumberChanged(teamNumbers[which]);
            }
        });
        dialogBuilder.show();
    }
}
