package utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static boolean getPermissions(Activity activity, String type) {
        int permission = ContextCompat.checkSelfPermission(activity, type);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{type}, 1);
        }
        return permission == PackageManager.PERMISSION_GRANTED;
    }
}
