package com.frc107.scouting2019.view.wrappers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.frc107.scouting2019.viewmodel.ScoutViewModel;

public class TextWrapper {
    private EditText editText;
    private TextWatcher textWatcher;
    private ScoutViewModel viewModel;
    private int id;

    /**
     * Create a TextWrapper, used for handling text changes.
     * @param editText The EditText to listen to.
     * @param viewModel The ViewModel to set the answers in.
     */
    public TextWrapper(EditText editText, ScoutViewModel viewModel) {
        this.viewModel = viewModel;
        this.editText = editText;
        this.id = editText.getId();

        textWatcher = createTextWatcher();
        this.editText.addTextChangedListener(textWatcher);
    }

    // TODO: rename to getTextWatcher.
    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleText(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        };
    }

    public EditText getEditText() {
        return editText;
    }

    /**
     * TODO: rename to onTextChanged
     * Called when text is changed in the text watcher.
     * @param text The new text.
     */
    public void handleText(String text) {
        if (text == null)
            text = "";

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
