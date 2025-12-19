package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.tools.RemoveBackButton;

public class WelcomeActivity extends Activity {
    public static int appStartTimes;
    public static int appStartTimesShowSetting;
    public static boolean showRateDialog;
    private SharedPreference sharedPreferenceLoadTime;

    public void appStartCounter() {
        this.sharedPreferenceLoadTime.save(this, appStartTimes + 1);
        appStartTimes = this.sharedPreferenceLoadTime.getValue(this);
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MyConstant.appstart = true;
        if (this.sharedPreferenceLoadTime == null) {
            this.sharedPreferenceLoadTime = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
        int value = this.sharedPreferenceLoadTime.getValue(this);
        appStartTimes = value;
        appStartTimesShowSetting = value;
        appStartCounter();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }
}
