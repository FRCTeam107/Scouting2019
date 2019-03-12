package com.frc107.scouting2019.view.wrappers;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.frc107.scouting2019.viewmodel.ScoutViewModel;

public class TextWrapper {
    private EditText editText;
    private TextWatcher textWatcher;
    private ScoutViewModel viewModel;
    private int id;

    public TextWrapper(Activity activity, ScoutViewModel viewModel, int id) {
        this.viewModel = viewModel;
        this.id = id;
        this.editText = activity.findViewById(id);

        textWatcher = getTextWatcher();
        this.editText.addTextChangedListener(textWatcher);
    }

    public EditText getEditText() {
        return editText;
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleText(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        };
    }

    public void handleText(String text) {
        viewModel.setAnswer(id, text);
    }

    public void cleanUp() {
        editText.removeTextChangedListener(textWatcher);
        editText = null;
        textWatcher = null;
    }

    public void clear() {
        editText.setText("");
    }
}
