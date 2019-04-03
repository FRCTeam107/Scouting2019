package com.frc107.scouting2019.view.wrappers;

import android.widget.RadioGroup;

import com.frc107.scouting2019.viewmodel.ScoutViewModel;

public class RadioWrapper {
    private RadioGroup radioGroup;
    private ScoutViewModel viewModel;
    private int id;

    /**
     * Create a TextWrapper, used for handling text changes.
     * @param radioGroup The RadioGroup to listen to.
     * @param viewModel The ViewModel to set the answers in.
     */
    public RadioWrapper(RadioGroup radioGroup, ScoutViewModel viewModel) {
        this.viewModel = viewModel;
        this.radioGroup = radioGroup;
        this.id = radioGroup.getId();
        this.radioGroup.setOnCheckedChangeListener(createChangeListener());
    }

    // TODO: rename to getOnCheckedChangeListener.
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
