package com.frc107.scouting2019.viewmodel;

import android.widget.EditText;

import com.frc107.scouting2019.BR;
import com.frc107.scouting2019.R;
import com.frc107.scouting2019.model.PitModel;
import com.frc107.scouting2019.model.question.Question;

import java.io.File;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class PitViewModel extends ScoutViewModel {
    public PitViewModel() {
        model = new PitModel();
    }

    public File createPhotoFile() {
        return ((PitModel) model).createPhotoFile();
    }

    public boolean compressPhoto() {
        return ((PitModel) model).compressPhoto();
    }
}
