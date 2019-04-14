package com.frc107.scouting2019.questionWrappers;

import android.widget.RadioGroup;

import com.frc107.scouting2019.ScoutViewModel;

public class RadioWrapper {
    private RadioGroup radioGroup;
    private ScoutViewModel viewModel;
    private int id;

    public RadioWrapper(RadioGroup radioGroup, ScoutViewModel viewModel) {
        this.viewModel = viewModel;
        this.radioGroup = radioGroup;
        this.id = radioGroup.getId();
        this.radioGroup.setOnCheckedChangeListener(createChangeListener());
    }

    private RadioGroup.OnCheckedChangeListener createChangeListener() {
        return (group, checkedId) -> viewModel.setAnswer(id, checkedId);
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void cleanUp() {
        radioGroup.setOnCheckedChangeListener(null);
        radioGroup = null;
    }

    public void clear() {
        radioGroup.clearCheck();
    }
}
