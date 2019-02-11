package com.frc107.scouting2019.utils;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.frc107.scouting2019.ScoutingConstants;

public class ViewUtils {
    public static boolean requestFocus(View v, Activity activity){
        if(v.requestFocus()){
            activity.getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE));
            return true;
        }
        return false;
    }

    public static void requestFocusToUnfinishedQuestion(View view, Activity activity) {
        requestFocus(view, activity);
        Toast.makeText(activity.getApplicationContext(), ScoutingConstants.UNFINISHED_QUESTION_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}