package com.frc107.scouting2019.viewmodel;

import com.frc107.scouting2019.model.TeleopModel;
import com.frc107.scouting2019.model.question.Question;

public class TeleopViewModel extends ScoutViewModel {
    public TeleopViewModel(Question... questions) {
        super("Teleop", questions);
        model = new TeleopModel("Teleop", questions);
    }

    public String save(boolean hasWritePermissions, String uniqueDeviceId, String autonData) {
        // TODO: At some point, since this is disgusting, make it so that ScoutModel is never instantiated and a view-specific model is used instead.
        return ((TeleopModel) model).save(hasWritePermissions, uniqueDeviceId, autonData);
    }
}
