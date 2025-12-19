package com.counting.kidsgame.tools;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.core.view.InputDeviceCompat;

public abstract class RemoveBackButton {
    public static void hideBackButtonBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 30) {
            activity.getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController insetsController = activity.getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                return;
            }
            return;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(7686);
    }

    public static void hideNavigationDialog(Dialog dialog) {
        Window window;
        if (dialog != null && (window = dialog.getWindow()) != null) {
            if (Build.VERSION.SDK_INT >= 30) {
                window.setDecorFitsSystemWindows(false);
                if (window.getInsetsController() != null) {
                    window.getInsetsController().hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                    window.getInsetsController().setSystemBarsBehavior(2);
                    return;
                }
                return;
            }
            window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        }
    }
}
