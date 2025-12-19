package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.counting.kidsgame.R;
import com.counting.kidsgame.tools.RemoveBackButton;

public class SplashScreenActivity extends Activity {


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screen);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
                SplashScreenActivity.this.finish();
            }
        }, 500);
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }
}
