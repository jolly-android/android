package com.counting.kidsgame.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.media.SoundManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

public class NumberTypingActivity extends Activity implements View.OnClickListener {


    ScrollView f4455a;


    TextView f4456b;


    ImageView f4457c;


    ImageView f4458d;
    ImageView e;
    ImageView f;
    ImageView g;
    ImageView h;
    ImageView i;
    ImageView j;
    ImageView k;
    ImageView l;
    ImageView m;
    private MyAdView myAdView;
    ImageView n;
    ImageView o;
    MyMediaPlayer p;
    SharedPreference q;


    public void FinishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }


    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void speakOut(String str) {
        int identifier = getBaseContext().getResources().getIdentifier(str, "raw", getBaseContext().getPackageName());
        if (MyConstant.SOUND_SETTING == MyConstant.SOUND_ON && identifier != 0) {
            this.p.StopMp();
            this.p.playSound(identifier);
        }
    }

    public void AppenedText(String str) {
        this.f4455a.fullScroll(130);
        this.f4456b.append(str);
    }

    public void DeleteText() {
        if (this.f4456b.getText().length() > 0) {
            TextView textView = this.f4456b;
            textView.setText(removeLastChar(textView.getText().toString()));
        }
    }

    public void init() {
        this.f4457c = (ImageView) findViewById(R.id.btnHome);
        this.f4455a = (ScrollView) findViewById(R.id.sv_txt);
        this.f4456b = (TextView) findViewById(R.id.txt_number);
        this.f4456b.setTypeface(Typeface.createFromAsset(getAssets(), "DIGITALDREAMFAT.ttf"));
        this.f4458d = (ImageView) findViewById(R.id.btn_one);
        this.e = (ImageView) findViewById(R.id.btn_two);
        this.f = (ImageView) findViewById(R.id.btn_three);
        this.g = (ImageView) findViewById(R.id.btn_four);
        this.h = (ImageView) findViewById(R.id.btn_five);
        this.i = (ImageView) findViewById(R.id.btn_six);
        this.j = (ImageView) findViewById(R.id.btn_seven);
        this.k = (ImageView) findViewById(R.id.btn_eight);
        this.l = (ImageView) findViewById(R.id.btn_nine);
        this.m = (ImageView) findViewById(R.id.btn_space);
        this.n = (ImageView) findViewById(R.id.btn_zero);
        this.o = (ImageView) findViewById(R.id.btn_delete);
        this.f4458d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        FinishActivity();
    }

    public void onClick(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.num_type_anim);
        switch (view.getId()) {
            case R.id.btn_delete:
                this.o.startAnimation(loadAnimation);
                this.p.playSound(R.raw.effect_backspace);
                DeleteText();
                return;
            case R.id.btn_eight:
                this.k.startAnimation(loadAnimation);
                AppenedText("8");
                speakOut(getResources().getString(R.string.h));
                return;
            case R.id.btn_five:
                this.h.startAnimation(loadAnimation);
                AppenedText("5");
                speakOut(getResources().getString(R.string.e));
                return;
            case R.id.btn_four:
                this.g.startAnimation(loadAnimation);
                AppenedText("4");
                speakOut(getResources().getString(R.string.d));
                return;
            case R.id.btn_nine:
                this.l.startAnimation(loadAnimation);
                AppenedText("9");
                speakOut(getResources().getString(R.string.i));
                return;
            case R.id.btn_one:
                this.f4458d.startAnimation(loadAnimation);
                AppenedText("1");
                speakOut(getResources().getString(R.string.a));
                return;
            case R.id.btn_seven:
                this.j.startAnimation(loadAnimation);
                AppenedText("7");
                speakOut(getResources().getString(R.string.g));
                return;
            case R.id.btn_six:
                this.i.startAnimation(loadAnimation);
                AppenedText("6");
                speakOut(getResources().getString(R.string.f));
                return;
            case R.id.btn_space:
                this.m.startAnimation(loadAnimation);
                this.p.playSound(R.raw.tap);
                AppenedText("_");
                return;
            case R.id.btn_three:
                this.f.startAnimation(loadAnimation);
                AppenedText("3");
                speakOut(getResources().getString(R.string.c));
                return;
            case R.id.btn_two:
                this.e.startAnimation(loadAnimation);
                AppenedText("2");
                speakOut(getResources().getString(R.string.b));
                return;
            case R.id.btn_zero:
                this.n.startAnimation(loadAnimation);
                AppenedText("10");
                speakOut(getResources().getString(R.string.j));
                return;
            default:
                return;
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MyAdmob.createAd(this, new MyAdmob.AdListener() {
                 public void OnClose() {
                Log.e("dd","");
            }

            public void OnFailed() {
                Log.e("dd","");
            }

            public void OnLoad() {
                Log.e("dd","");
            }
        });
        setContentView(R.layout.activity_number_typing);
        if (this.q == null) {
            this.q = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
        this.p = new MyMediaPlayer(this);
        MyConstant.SOUND_SETTING = this.q.getSettingSound(this);
        MyConstant.MUSIC_SETTING = this.q.getSettingMusic(this);
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();
        init();
        this.f4457c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyConstant.SOUND_SETTING == MyConstant.SOUND_ON) {
                    SoundManager.playSound(1, 1.0f);
                }
                NumberTypingActivity.this.clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberTypingActivity.this.FinishActivity();
                    }
                }, 500);
            }
        });
        this.myAdView = new MyAdView(this);

        setAd();
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }
}
