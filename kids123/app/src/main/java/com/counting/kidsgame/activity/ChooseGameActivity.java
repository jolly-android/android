package com.counting.kidsgame.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ChooseGameActivity extends Activity implements View.OnClickListener {
    public static int myRandom;
    public static int optionPos;
    public static int random;


    ImageView f4253a;
    public Animation animShake;
    public Animation animationServiceList;


    ImageView f4254b;


    TextView f4255c;


    TextView f4256d;
    TextView e;
    TextView f;
    private Typeface font;
    TextView g;
    MyMediaPlayer h;
    private MyAdView myAdView;
    public Animation zoom;

    private void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }


    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    public static int getRandomAd() {
        random = new Random().nextInt(20);
        PrintStream printStream = System.out;
        printStream.println("random--" + random);
        return random;
    }

    public static int getRandomAd1(int i) {
        random = new Random().nextInt(i);
        PrintStream printStream = System.out;
        printStream.println("random--" + random);
        return random;
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }


    public void setGameScreen() {
        myRandom = getRandomAd();
        int randomAd1 = getRandomAd1(4);
        optionPos = randomAd1;
        if (randomAd1 == 0) {
            this.f4255c.setText(Integer.toString(myRandom + 1));
            ArrayList arrayList = new ArrayList();
            for (int i = 1; i < 21; i++) {
                arrayList.add(new Integer(i));
            }
            Collections.shuffle(arrayList);
            int intValue = ((Integer) arrayList.get(0)).intValue();
            int i2 = myRandom;
            if (intValue != i2 + 1) {
                this.f4256d.setText(Integer.toString(((Integer) arrayList.get(0)).intValue()));
            } else if (i2 + 1 < 4) {
                this.f4256d.setText(Integer.toString(i2 + 2));
            } else {
                this.f4256d.setText(Integer.toString(i2 - 2));
            }
            int intValue2 = ((Integer) arrayList.get(1)).intValue();
            int i3 = myRandom;
            if (intValue2 != i3 + 1) {
                this.e.setText(Integer.toString(((Integer) arrayList.get(1)).intValue()));
            } else if (i3 + 1 < 6) {
                this.e.setText(Integer.toString(i3 + 4));
            } else {
                this.e.setText(Integer.toString(i3 - 4));
            }
            int intValue3 = ((Integer) arrayList.get(2)).intValue();
            int i4 = myRandom;
            if (intValue3 != i4 + 1) {
                this.f.setText(Integer.toString(((Integer) arrayList.get(2)).intValue()));
            } else if (i4 + 1 < 8) {
                this.f.setText(Integer.toString(i4 + 6));
            } else {
                this.f.setText(Integer.toString(i4 - 6));
            }
            this.f4253a.setTag(Integer.toString(myRandom + 1));
            this.f4253a.setImageResource(MyConstant.numbersBitmaps[myRandom].intValue());
        }
        if (optionPos == 1) {
            this.f4256d.setText(Integer.toString(myRandom + 1));
            ArrayList arrayList2 = new ArrayList();
            for (int i5 = 1; i5 < 21; i5++) {
                arrayList2.add(new Integer(i5));
            }
            Collections.shuffle(arrayList2);
            int intValue4 = ((Integer) arrayList2.get(0)).intValue();
            int i6 = myRandom;
            if (intValue4 != i6 + 1) {
                this.f4255c.setText(Integer.toString(((Integer) arrayList2.get(0)).intValue()));
            } else if (i6 + 1 < 4) {
                this.f4255c.setText(Integer.toString(i6 + 2));
            } else {
                this.f4255c.setText(Integer.toString(i6 - 2));
            }
            int intValue5 = ((Integer) arrayList2.get(1)).intValue();
            int i7 = myRandom;
            if (intValue5 != i7 + 1) {
                this.e.setText(Integer.toString(((Integer) arrayList2.get(1)).intValue()));
            } else if (i7 + 1 < 6) {
                this.e.setText(Integer.toString(i7 + 4));
            } else {
                this.e.setText(Integer.toString(i7 - 4));
            }
            int intValue6 = ((Integer) arrayList2.get(2)).intValue();
            int i8 = myRandom;
            if (intValue6 != i8 + 1) {
                this.f.setText(Integer.toString(((Integer) arrayList2.get(2)).intValue()));
            } else if (i8 + 1 < 8) {
                this.f.setText(Integer.toString(i8 + 6));
            } else {
                this.f.setText(Integer.toString(i8 - 6));
            }
            this.f4253a.setTag(Integer.toString(myRandom + 1));
            this.f4253a.setImageResource(MyConstant.numbersBitmaps[myRandom].intValue());
        }
        if (optionPos == 2) {
            this.e.setText(Integer.toString(myRandom + 1));
            ArrayList arrayList3 = new ArrayList();
            for (int i9 = 1; i9 < 21; i9++) {
                arrayList3.add(new Integer(i9));
            }
            Collections.shuffle(arrayList3);
            int intValue7 = ((Integer) arrayList3.get(0)).intValue();
            int i10 = myRandom;
            if (intValue7 != i10 + 1) {
                this.f4256d.setText(Integer.toString(((Integer) arrayList3.get(0)).intValue()));
            } else if (i10 + 1 < 4) {
                this.f4256d.setText(Integer.toString(i10 + 2));
            } else {
                this.f4256d.setText(Integer.toString(i10 - 2));
            }
            int intValue8 = ((Integer) arrayList3.get(1)).intValue();
            int i11 = myRandom;
            if (intValue8 != i11 + 1) {
                this.f4255c.setText(Integer.toString(((Integer) arrayList3.get(1)).intValue()));
            } else if (i11 + 1 < 6) {
                this.f4255c.setText(Integer.toString(i11 + 4));
            } else {
                this.f4255c.setText(Integer.toString(i11 - 4));
            }
            int intValue9 = ((Integer) arrayList3.get(2)).intValue();
            int i12 = myRandom;
            if (intValue9 != i12 + 1) {
                this.f.setText(Integer.toString(((Integer) arrayList3.get(2)).intValue()));
            } else if (i12 + 1 < 8) {
                this.f.setText(Integer.toString(i12 + 6));
            } else {
                this.f.setText(Integer.toString(i12 - 6));
            }
            this.f4253a.setTag(Integer.toString(myRandom + 1));
            this.f4253a.setImageResource(MyConstant.numbersBitmaps[myRandom].intValue());
        }
        if (optionPos == 3) {
            this.f.setText(Integer.toString(myRandom + 1));
            ArrayList arrayList4 = new ArrayList();
            for (int i13 = 1; i13 < 21; i13++) {
                arrayList4.add(new Integer(i13));
            }
            Collections.shuffle(arrayList4);
            int intValue10 = ((Integer) arrayList4.get(0)).intValue();
            int i14 = myRandom;
            if (intValue10 != i14 + 1) {
                this.f4256d.setText(Integer.toString(((Integer) arrayList4.get(0)).intValue()));
            } else if (i14 + 1 < 4) {
                this.f4256d.setText(Integer.toString(i14 + 2));
            } else {
                this.f4256d.setText(Integer.toString(i14 - 2));
            }
            int intValue11 = ((Integer) arrayList4.get(1)).intValue();
            int i15 = myRandom;
            if (intValue11 != i15 + 1) {
                this.e.setText(Integer.toString(((Integer) arrayList4.get(1)).intValue()));
            } else if (i15 + 1 < 6) {
                this.e.setText(Integer.toString(i15 + 4));
            } else {
                this.e.setText(Integer.toString(i15 - 4));
            }
            int intValue12 = ((Integer) arrayList4.get(2)).intValue();
            int i16 = myRandom;
            if (intValue12 != i16 + 1) {
                this.f4255c.setText(Integer.toString(((Integer) arrayList4.get(2)).intValue()));
            } else if (i16 + 1 < 8) {
                this.f4255c.setText(Integer.toString(i16 + 6));
            } else {
                this.f4255c.setText(Integer.toString(i16 - 6));
            }
            this.f4253a.setTag(Integer.toString(myRandom + 1));
            this.f4253a.setImageResource(MyConstant.numbersBitmaps[myRandom].intValue());
        }
    }


    public int getRandomAnimation(int i) {
        switch (i) {
            case 1:
                return R.anim.bounce;
            case 2:
                return R.anim.clockwise;
            case 3:
                return R.anim.clockwise2;
            case 4:
                return R.anim.fade_in;
            case 5:
                return R.anim.move;
            case 6:
                return R.anim.scale;
            case 7:
                return R.anim.shake;
            case 8:
                return R.anim.shake1;
            case 9:
                return R.anim.slide_up;
            default:
                return R.anim.zoom_in;
        }
    }

    public void onBackPressed() {
        this.h.StopMp();
        finishActivity();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.home) {
            this.h.StopMp();
            playClickSound();
            clickBounceAnim(view);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    ChooseGameActivity.this.finishActivity();
                }
            }, 500);
        } else if (id != R.id.m1) {
            switch (id) {
                case R.id.t1:
                    if (this.f4253a.getTag().toString() == this.f4255c.getText().toString()) {
                        this.h.speakApplause();

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                ChooseGameActivity.this.setGameScreen();
                            }
                        }, 500);
                        return;
                    }
                    this.h.playSound(R.raw.wrong);
                    this.f4255c.startAnimation(this.animShake);
                    return;
                case R.id.t2:
                    if (this.f4253a.getTag().toString() == this.f4256d.getText().toString()) {
                        this.h.speakApplause();

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                ChooseGameActivity.this.setGameScreen();
                            }
                        }, 500);
                        return;
                    }
                    this.h.playSound(R.raw.wrong);
                    this.f4256d.startAnimation(this.animShake);
                    return;
                case R.id.t3:
                    if (this.f4253a.getTag().toString() == this.e.getText().toString()) {
                        this.h.speakApplause();

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                ChooseGameActivity.this.setGameScreen();
                            }
                        }, 500);
                        return;
                    }
                    this.h.playSound(R.raw.wrong);
                    this.e.startAnimation(this.animShake);
                    return;
                case R.id.t4:
                    if (this.f4253a.getTag().toString() == this.f.getText().toString()) {
                        this.h.speakApplause();

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                ChooseGameActivity.this.setGameScreen();
                            }
                        }, 500);
                        return;
                    }
                    this.h.playSound(R.raw.wrong);
                    this.f.startAnimation(this.animShake);
                    return;
                default:
                    return;
            }
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, getRandomAnimation(getRandomAd1(10)));
            this.animationServiceList = loadAnimation;
            this.f4253a.startAnimation(loadAnimation);
            randomSoundPlay(getRandomAd1(10));
        }
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_game);
        this.font = Typeface.createFromAsset(getAssets(), "arlrdbd.ttf");
        this.h = new MyMediaPlayer(this);
        this.f4253a = (ImageView) findViewById(R.id.m1);
        this.f4254b = (ImageView) findViewById(R.id.home);
        this.f4255c = (TextView) findViewById(R.id.t1);
        this.f4256d = (TextView) findViewById(R.id.t2);
        this.e = (TextView) findViewById(R.id.t3);
        this.f = (TextView) findViewById(R.id.t4);
        this.g = (TextView) findViewById(R.id.txt);
        this.f4255c.setOnClickListener(this);
        this.f4256d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.f4255c.setTypeface(this.font);
        this.f4256d.setTypeface(this.font);
        this.e.setTypeface(this.font);
        this.f.setTypeface(this.font);
        this.g.setTypeface(this.font);
        this.f4253a.setOnClickListener(this);
        this.f4254b.setOnClickListener(this);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.animShake = loadAnimation;
        loadAnimation.setDuration(320);
        this.zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        setGameScreen();
        this.h.playSound(R.raw.lets_count);
        this.myAdView = new MyAdView(this);

        setAd();
    }

    @Override
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void playClickSound() {
        this.h.StopMp();
        this.h.playSound(R.raw.click);
    }

    public void randomSoundPlay(int i) {
        switch (i) {
            case 1:
                this.h.playSound(R.raw.random_comical);
                return;
            case 2:
                this.h.playSound(R.raw.random_anim_boing);
                return;
            case 3:
                this.h.playSound(R.raw.random_sticky);
                return;
            case 4:
                this.h.playSound(R.raw.random_effect_sparkle);
                return;
            case 5:
                this.h.playSound(R.raw.random_gone);
                return;
            case 6:
                this.h.playSound(R.raw.random_peeking);
                return;
            case 7:
                this.h.playSound(R.raw.random_whish);
                return;
            case 8:
                this.h.playSound(R.raw.random_squeaky_pop);
                return;
            case 9:
                this.h.playSound(R.raw.random_whiparound);
                return;
            case 10:
                this.h.playSound(R.raw.random_twitch);
                return;
            default:
                return;
        }
    }
}
