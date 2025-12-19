package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;
import com.plattysoft.leonids.ParticleSystem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberFindActivity extends Activity implements View.OnClickListener {
    String A;
    float B;
    MyMediaPlayer C;
    TranslateAnimation D;
    final Handler E = new Handler(Looper.getMainLooper());
    final Handler F = new Handler(Looper.getMainLooper());
    final Handler G = new Handler(Looper.getMainLooper());

    
    ArrayList<ImageView> f4374a;
    private Animation anim;
    private Animation animation21;
    private ArrayList<Integer> arrThreeShape;

    
    protected int f4375b;
    private ImageView btnHome;

    
    Resources f4376c;
    private Animation clockwise;

    
    String f4377d;
    String e;
    String f;
    private Animation floating;
    private Typeface font;
    String g;
    String h;
    private Intent i;
    
    public ImageView imageViewCar;
    
    public ImageView imageViewCar2;
    
    public ImageView imageViewDuck;
    private ImageView imageViewRocket;
    
    public ImageView imageViewScooter;
    
    public ImageView imageViewTeddy;
    String j;
    String k;
    String l;
    String m;
    private MediaPlayer mPause;
    private MediaPlayer mWin;
    private MyAdView myAdView;
    String n;
    String o;
    String p;
    String q;
    String r;
    private Integer[] randomInt;
    private Integer[] randomShape;
    String s;
    
    public ImageView shape1;
    
    public ImageView shape2;
    
    public ImageView shape3;
    String t;
    
    public TranslateAnimation translate3;
    
    public TranslateAnimation translate5;
    
    public TranslateAnimation translate7;
    
    public TranslateAnimation translate9;
    
    public TextView tvShape;
    String u;
    String v;
    String w;
    String x;
    ImageView y;
    String z;

    
    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    
    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
        this.shape1 = null;
        this.shape2 = null;
        this.shape3 = null;
        System.gc();
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setCar2Animation() {
        this.imageViewCar2.setClickable(false);
        this.C.playSound(R.raw.effect_move);
        TranslateAnimation translateAnimation = new TranslateAnimation(3000.0f, 0.0f, 0.0f, 0.0f);
        this.translate7 = translateAnimation;
        translateAnimation.setDuration(2000);
        this.translate7.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewCar2.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startAnimation(numberFindActivity.imageViewCar2);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -1800.0f, 0.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewCar2.startAnimation(NumberFindActivity.this.translate7);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.imageViewCar2.startAnimation(translateAnimation2);
    }

    private void setCarAnimation() {
        this.imageViewCar.setClickable(false);
        this.C.playSound(R.raw.effect_confused);
        TranslateAnimation translateAnimation = new TranslateAnimation(3000.0f, 0.0f, 0.0f, 0.0f);
        this.translate5 = translateAnimation;
        translateAnimation.setDuration(2000);
        this.translate5.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewCar.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startAnimation(numberFindActivity.imageViewCar);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -3000.0f, 0.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewCar.startAnimation(NumberFindActivity.this.translate5);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.imageViewCar.startAnimation(translateAnimation2);
    }

    private void setDuckAnimation() {
        this.imageViewDuck.setClickable(false);
        this.C.playSound(R.raw.duckling);
        TranslateAnimation translateAnimation = new TranslateAnimation(2500.0f, 0.0f, 0.0f, 0.0f);
        this.translate9 = translateAnimation;
        translateAnimation.setDuration(2000);
        this.translate9.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewDuck.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startAnimation(numberFindActivity.imageViewDuck);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -1800.0f, 0.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewDuck.startAnimation(NumberFindActivity.this.translate9);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.imageViewDuck.startAnimation(translateAnimation2);
    }

    
    public void setRandomShape() {
        this.shape1.setClickable(true);
        this.shape2.setClickable(true);
        this.shape3.setClickable(true);
        this.f4374a = new ArrayList<>();
        this.arrThreeShape = new ArrayList<>();
        this.randomInt = storeRandomNumbers(20);
        this.randomShape = storeRandomNumbers(20);
        this.f4374a.add(this.shape1);
        this.f4374a.add(this.shape2);
        this.f4374a.add(this.shape3);
        Integer[] numArr = this.randomInt;
        if (numArr[0] == this.randomShape[0]) {
            this.arrThreeShape.add(numArr[7]);
        } else {
            this.arrThreeShape.add(numArr[0]);
        }
        Integer[] numArr2 = this.randomInt;
        if (numArr2[1] == this.randomShape[0]) {
            this.arrThreeShape.add(numArr2[4]);
        } else {
            this.arrThreeShape.add(numArr2[1]);
        }
        this.arrThreeShape.add(this.randomShape[0]);
        Collections.shuffle(this.arrThreeShape);
        for (int i2 = 0; i2 < 3; i2++) {
            switch (this.arrThreeShape.get(i2).intValue()) {
                case 1:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a1).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a1);
                        break;
                    }
                case 2:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a2).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused2) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a2);
                        break;
                    }
                case 3:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a3).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused3) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a3);
                        break;
                    }
                case 4:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a4).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused4) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a4);
                        break;
                    }
                case 5:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a5).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused5) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a5);
                        break;
                    }
                case 6:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a6).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused6) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a6);
                        break;
                    }
                case 7:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a7).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused7) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a7);
                        break;
                    }
                case 8:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a8).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused8) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a8);
                        break;
                    }
                case 9:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a9).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused9) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a9);
                        break;
                    }
                case 10:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a10).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused10) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a10);
                        break;
                    }
                case 11:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a11).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused11) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a11);
                        break;
                    }
                case 12:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a12).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused12) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a12);
                        break;
                    }
                case 13:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a13).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused13) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a13);
                        break;
                    }
                case 14:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a14).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused14) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a14);
                        break;
                    }
                case 15:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a15).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused15) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a15);
                        break;
                    }
                case 16:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a16).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused16) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a16);
                        break;
                    }
                case 17:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a17).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused17) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a17);
                        break;
                    }
                case 18:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a18).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused18) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a18);
                        break;
                    }
                case 19:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a19).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused19) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a19);
                        break;
                    }
                case 20:
                    if (this.arrThreeShape.get(i2) == this.randomShape[0]) {
                        this.f4374a.get(i2).setTag(this.z);
                    } else {
                        this.f4374a.get(i2).setTag(this.A);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.a20).into(this.f4374a.get(i2));
                        break;
                    } catch (Exception unused20) {
                        this.f4374a.get(i2).setImageResource(R.drawable.a20);
                        break;
                    }
            }
        }
    }

    private void setScooterAnimation() {
        this.imageViewScooter.setClickable(false);
        this.C.playSound(R.raw.boing);
        TranslateAnimation translateAnimation = new TranslateAnimation(1800.0f, 0.0f, 0.0f, 0.0f);
        this.translate3 = translateAnimation;
        translateAnimation.setDuration(2000);
        this.translate3.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewScooter.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startFloatAnimation(numberFindActivity.imageViewScooter);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -2000.0f, 0.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewScooter.startAnimation(NumberFindActivity.this.translate3);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.imageViewScooter.startAnimation(translateAnimation2);
    }

    private void setTeddyAnimation() {
        this.C.playSound(R.raw.yuhhu);
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        this.imageViewTeddy.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.imageViewCar2.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startFloatAnimation(numberFindActivity.imageViewTeddy);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }

    private void setTrainAnimation() {
        this.y.clearAnimation();
        this.y.setClickable(false);
        this.C.playSound(R.raw.yuhhu);
        TranslateAnimation translateAnimation = new TranslateAnimation(-1800.0f, 0.0f, 0.0f, 0.0f);
        this.D = translateAnimation;
        translateAnimation.setDuration(2000);
        this.D.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity.this.y.setClickable(true);
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.startAnimation(numberFindActivity.y);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 2500.0f, 0.0f, 0.0f);
        translateAnimation2.setDuration(2000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberFindActivity numberFindActivity = NumberFindActivity.this;
                numberFindActivity.y.startAnimation(numberFindActivity.D);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.y.startAnimation(translateAnimation2);
    }

    
    public void setVoice() {
        switch (this.randomShape[0].intValue()) {
            case 1:
                this.tvShape.setText(this.f4377d);
                speakWords("n_1");
                return;
            case 2:
                this.tvShape.setText(this.e);
                speakWords("n_2");
                return;
            case 3:
                this.tvShape.setText(this.f);
                speakWords("n_3");
                return;
            case 4:
                this.tvShape.setText(this.g);
                speakWords("n_4");
                return;
            case 5:
                this.tvShape.setText(this.h);
                speakWords("n_5");
                return;
            case 6:
                this.tvShape.setText(this.j);
                speakWords("n_6");
                return;
            case 7:
                this.tvShape.setText(this.k);
                speakWords("n_7");
                return;
            case 8:
                this.tvShape.setText(this.l);
                speakWords("n_8");
                return;
            case 9:
                this.tvShape.setText(this.m);
                speakWords("n_9");
                return;
            case 10:
                this.tvShape.setText(this.n);
                speakWords("n_10");
                return;
            case 11:
                this.tvShape.setText(this.o);
                speakWords("n_11");
                return;
            case 12:
                this.tvShape.setText(this.p);
                speakWords("n_12");
                return;
            case 13:
                this.tvShape.setText(this.q);
                speakWords("n_13");
                return;
            case 14:
                this.tvShape.setText(this.r);
                speakWords("n_14");
                return;
            case 15:
                this.tvShape.setText(this.s);
                speakWords("n_15");
                return;
            case 16:
                this.tvShape.setText(this.t);
                speakWords("n_16");
                return;
            case 17:
                this.tvShape.setText(this.u);
                speakWords("n_17");
                return;
            case 18:
                this.tvShape.setText(this.v);
                speakWords("n_18");
                return;
            case 19:
                this.tvShape.setText(this.w);
                speakWords("n_19");
                return;
            case 20:
                this.tvShape.setText(this.x);
                speakWords("n_20");
                return;
            default:
                return;
        }
    }

    private void speakWords(final String str) {
        this.F.postDelayed(new Runnable() {
            public void run() {
                int identifier = NumberFindActivity.this.getBaseContext().getResources().getIdentifier("touch", "raw", NumberFindActivity.this.getBaseContext().getPackageName());
                if (identifier != 0) {
                    NumberFindActivity.this.C.playSound(identifier);
                }
                final int identifier2 = NumberFindActivity.this.getBaseContext().getResources().getIdentifier(str.toLowerCase(), "raw", NumberFindActivity.this.getBaseContext().getPackageName());
                NumberFindActivity.this.G.postDelayed(new Runnable() {
                    public void run() {
                        int i = identifier2;
                        if (i != 0) {
                            try {
                                NumberFindActivity.this.C.playSound(i);
                            } catch (Exception unused) {
                                NumberFindActivity.this.C.playSound(R.raw.click);
                            }
                        }
                    }
                }, 500);
            }
        }, 500);
    }

    
    public void startAnimation(ImageView imageView) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        this.animation21 = loadAnimation;
        imageView.startAnimation(loadAnimation);
        this.animation21.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }

    
    public void startFloatAnimation(ImageView imageView) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.floatingg);
        this.floating = loadAnimation;
        imageView.startAnimation(loadAnimation);
        this.floating.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }

    private void startOneShotParticle(ImageView imageView) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.15f, 0.35f).oneShot(imageView, 20);
    }

    public float getFloatvalue() {
        TypedValue typedValue = new TypedValue();
        this.f4376c.getValue(R.dimen.speech_rate, typedValue, true);
        return typedValue.getFloat();
    }

    public void getTouchShapeString() {
        this.f4377d = this.f4376c.getString(R.string.touchA);
        this.e = this.f4376c.getString(R.string.touchB);
        this.f = this.f4376c.getString(R.string.touchC);
        this.g = this.f4376c.getString(R.string.touchD);
        this.h = this.f4376c.getString(R.string.touchE);
        this.j = this.f4376c.getString(R.string.touchF);
        this.k = this.f4376c.getString(R.string.touchG);
        this.l = this.f4376c.getString(R.string.touchH);
        this.m = this.f4376c.getString(R.string.touchI);
        this.n = this.f4376c.getString(R.string.touchJ);
        this.o = this.f4376c.getString(R.string.touchK);
        this.p = this.f4376c.getString(R.string.touchL);
        this.q = this.f4376c.getString(R.string.touchM);
        this.r = this.f4376c.getString(R.string.touchN);
        this.s = this.f4376c.getString(R.string.touchO);
        this.t = this.f4376c.getString(R.string.touchP);
        this.u = this.f4376c.getString(R.string.touchQ);
        this.v = this.f4376c.getString(R.string.touchR);
        this.w = this.f4376c.getString(R.string.touchS);
        this.x = this.f4376c.getString(R.string.touchT);
        this.z = this.f4376c.getString(R.string.correct);
        this.A = this.f4376c.getString(R.string.incorrect);
    }
    @Override
    public void onBackPressed() {
        try {
            this.C.StopMp();
            this.E.removeCallbacksAndMessages((Object) null);
            this.F.removeCallbacksAndMessages((Object) null);
            this.G.removeCallbacksAndMessages((Object) null);
            finishActivity();
        } catch (Exception unused) {
            this.C.StopMp();
            finishActivity();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView1 /*2131296710*/:
                if (((String) this.shape1.getTag()).equals(this.z)) {
                    this.shape1.setClickable(false);
                    System.out.println("correct1");
                    this.shape1.startAnimation(this.clockwise);
                    this.C.playSound(R.raw.drag_right);
                    startOneShotParticle(this.shape1);
                    this.C.speakApplause();
                    this.E.postDelayed(new Runnable() {
                        public void run() {
                            NumberFindActivity.this.shape1.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.shape2.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.shape3.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.tvShape.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.setRandomShape();
                            NumberFindActivity.this.setVoice();
                            NumberFindActivity.this.shape1.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.shape2.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.shape3.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.tvShape.setVisibility(View.VISIBLE);
                        }
                    }, 1500);
                    return;
                }
                this.C.playSound(R.raw.wrong);
                this.shape1.startAnimation(this.anim);
                return;
            case R.id.imageView2 /*2131296711*/:
                if (((String) this.shape2.getTag()).equals(this.z)) {
                    this.shape2.setClickable(false);
                    System.out.println("correct2");
                    this.shape2.startAnimation(this.clockwise);
                    this.C.playSound(R.raw.drag_right);
                    startOneShotParticle(this.shape2);
                    this.C.speakApplause();
                    this.E.postDelayed(new Runnable() {
                        public void run() {
                            NumberFindActivity.this.shape1.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.shape2.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.shape3.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.tvShape.setVisibility(View.INVISIBLE);
                            NumberFindActivity.this.setRandomShape();
                            NumberFindActivity.this.setVoice();
                            NumberFindActivity.this.shape1.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.shape2.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.shape3.setVisibility(View.VISIBLE);
                            NumberFindActivity.this.tvShape.setVisibility(View.VISIBLE);
                        }
                    }, 1500);
                    return;
                }
                this.C.playSound(R.raw.wrong);
                this.shape2.startAnimation(this.anim);
                return;
            case R.id.imageView3 /*2131296712*/:
                try {
                    if (((String) this.shape3.getTag()).equals(this.z)) {
                        this.shape3.setClickable(false);
                        System.out.println("correct3");
                        this.shape3.startAnimation(this.clockwise);
                        this.C.playSound(R.raw.drag_right);
                        startOneShotParticle(this.shape3);
                        this.C.speakApplause();
                        this.E.postDelayed(new Runnable() {
                            public void run() {
                                NumberFindActivity.this.shape1.setVisibility(View.INVISIBLE);
                                NumberFindActivity.this.shape2.setVisibility(View.INVISIBLE);
                                NumberFindActivity.this.shape3.setVisibility(View.INVISIBLE);
                                NumberFindActivity.this.tvShape.setVisibility(View.INVISIBLE);
                                NumberFindActivity.this.setRandomShape();
                                NumberFindActivity.this.setVoice();
                                NumberFindActivity.this.shape1.setVisibility(View.VISIBLE);
                                NumberFindActivity.this.shape2.setVisibility(View.VISIBLE);
                                NumberFindActivity.this.shape3.setVisibility(View.VISIBLE);
                                NumberFindActivity.this.tvShape.setVisibility(View.VISIBLE);
                            }
                        }, 1500);
                        return;
                    }
                    this.C.playSound(R.raw.wrong);
                    this.shape3.startAnimation(this.anim);
                    return;
                } catch (Exception unused) {
                    return;
                }
            case R.id.imageViewCar /*2131296714*/:
                setCarAnimation();
                return;
            case R.id.imageViewCar2 /*2131296715*/:
                setCar2Animation();
                return;
            case R.id.imageViewDuck /*2131296716*/:
                setDuckAnimation();
                return;
            case R.id.imageViewScooter /*2131296718*/:
                setScooterAnimation();
                return;
            case R.id.imageViewTeddy /*2131296719*/:
                setTeddyAnimation();
                return;
            case R.id.imageViewTrain /*2131296720*/:
                setTrainAnimation();
                return;
            default:
                return;
        }
    }

    @Override
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
        this.f4375b = 0;
        setContentView(R.layout.number_find);
        this.C = new MyMediaPlayer(this);
        this.f4376c = getResources();
        this.B = getFloatvalue();
        getTouchShapeString();
        this.tvShape = (TextView) findViewById(R.id.tvShape);
        this.shape1 = (ImageView) findViewById(R.id.imageView1);
        this.shape2 = (ImageView) findViewById(R.id.imageView2);
        this.shape3 = (ImageView) findViewById(R.id.imageView3);
        this.shape1.setOnClickListener(this);
        this.shape2.setOnClickListener(this);
        this.shape3.setOnClickListener(this);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "arlrdbd.ttf");
        this.font = createFromAsset;
        this.tvShape.setTypeface(createFromAsset);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewTrain);
        this.y = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageViewScooter);
        this.imageViewScooter = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageViewCar);
        this.imageViewCar = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageViewCar2);
        this.imageViewCar2 = imageView4;
        imageView4.setOnClickListener(this);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageViewDuck);
        this.imageViewDuck = imageView5;
        imageView5.setOnClickListener(this);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageViewTeddy);
        this.imageViewTeddy = imageView6;
        imageView6.setOnClickListener(this);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.anim = loadAnimation;
        loadAnimation.setDuration(320);
        this.clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        setRandomShape();
        this.tvShape.setVisibility(View.VISIBLE);
        setVoice();
        startAnimation(this.imageViewCar);
        startAnimation(this.imageViewCar2);
        startFloatAnimation(this.imageViewScooter);
        startFloatAnimation(this.imageViewTeddy);
        startAnimation(this.y);
        startAnimation(this.imageViewDuck);
        ImageView imageView7 = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView7;
        imageView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NumberFindActivity.this.clickBounceAnim(view);
                try {
                    NumberFindActivity.this.C.StopMp();
                    NumberFindActivity.this.playClickSound();
                    NumberFindActivity.this.E.removeCallbacksAndMessages((Object) null);
                    NumberFindActivity.this.F.removeCallbacksAndMessages((Object) null);
                    NumberFindActivity.this.G.removeCallbacksAndMessages((Object) null);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            NumberFindActivity.this.finishActivity();
                        }
                    }, 500);
                } catch (Exception unused) {
                    NumberFindActivity.this.C.StopMp();
                    NumberFindActivity.this.playClickSound();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            NumberFindActivity.this.finishActivity();
                        }
                    }, 500);
                }
            }
        });
        this.myAdView = new MyAdView(this);
        
        setAd();
    }

    public void onDestroy() {
        try {
            this.C.StopMp();
            this.E.removeCallbacksAndMessages((Object) null);
            this.F.removeCallbacksAndMessages((Object) null);
            this.G.removeCallbacksAndMessages((Object) null);
            finishActivity();
        } catch (Exception unused) {
            this.C.StopMp();
            finishActivity();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            this.C.StopMp();
            this.E.removeCallbacksAndMessages((Object) null);
            this.F.removeCallbacksAndMessages((Object) null);
            this.G.removeCallbacksAndMessages((Object) null);
            finishActivity();
        } catch (Exception unused) {
            this.C.StopMp();
            finishActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            RemoveBackButton.hideBackButtonBar(this);
            this.C.StopMp();
        } catch (Exception unused) {
        }
    }

    public void playClickSound() {
        this.C.StopMp();
        this.C.playSound(R.raw.click);
    }

    public ArrayList<Integer> randomize(int i2) {
        Integer valueOf;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < i2; i3++) {
            do {
                valueOf = Integer.valueOf(random.nextInt(i2) + 1);
            } while (arrayList.contains(valueOf));
            arrayList.add(valueOf);
        }
        return arrayList;
    }

    public Integer[] storeRandomNumbers(int i2) {
        ArrayList<Integer> randomize = randomize(i2);
        Collections.shuffle(randomize);
        Integer[] numArr = new Integer[i2];
        randomize.toArray(numArr);
        return numArr;
    }
}
