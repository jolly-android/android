package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.work.WorkRequest;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;
import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberSameActivity extends Activity implements View.OnClickListener {
    String A;

    
    int f4412a = 0;

    
    Resources f4413b;
    private ImageView btnHome;
    private ImageView butObj;
    private ImageView button1;
    private ImageView button10;
    private ImageView button11;
    private ImageView button12;
    private ImageView button13;
    private ImageView button14;
    private ImageView button15;
    private ImageView button16;
    private ImageView button17;
    private ImageView button18;
    private ImageView button19;
    private ImageView button2;
    private ImageView button20;
    private ImageView button21;
    private ImageView button22;
    private ImageView button23;
    private ImageView button24;
    private ImageView button3;
    private ImageView button4;
    private ImageView button5;
    private ImageView button6;
    private ImageView button7;
    private ImageView button8;
    private ImageView button9;

    
    float f4414c;
    
    public int countFinish = 0;

    
    private Drawable f4415d;
    private Drawable d1;
    MyMediaPlayer e;
    boolean f;
    
    public FrameLayout frameLayout;
    String g;
    String h;
    
    public boolean homeButtonClicked = false;
    private Intent i;
    private Intent intent;
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
    String s;
    private ArrayList<ImageView> shapeBtnArry;
    private Object shapeTag;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    
    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    
    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    private void restartActivity() {
        if (!this.homeButtonClicked) {
            this.e.StopMp();
            Intent intent2 = getIntent();
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            startActivity(intent2);
        }
    }

    private void setAd() {
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout2);
        } else {
            frameLayout2.setVisibility(View.GONE);
        }
    }

    
    public void setshapeButtonRandomly() {
        this.randomInt = storeRandomNumbers(24);
        for (int i2 = 0; i2 < 24; i2++) {
            switch (i2) {
                case 0:
                case 1:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b1);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.g);
                    break;
                case 2:
                case 3:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b2);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.h);
                    break;
                case 4:
                case 5:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b3);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.j);
                    break;
                case 6:
                case 7:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b4);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.k);
                    break;
                case 8:
                case 9:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b5);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.l);
                    break;
                case 10:
                case 11:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b6);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.m);
                    break;
                case 12:
                case 13:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b7);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.n);
                    break;
                case 14:
                case 15:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b8);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.o);
                    break;
                case 16:
                case 17:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b9);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.p);
                    break;
                case 18:
                case 19:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b10);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.q);
                    break;
                case 20:
                case 21:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b11);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.r);
                    break;
                case 22:
                case 23:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b12);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.s);
                    break;
                case 24:
                case 25:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b13);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.t);
                    break;
                case 26:
                case 27:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b14);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.u);
                    break;
                case 28:
                case 29:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b15);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.v);
                    break;
                case 30:
                case 31:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b16);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.w);
                    break;
                case 32:
                case 33:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b17);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.x);
                    break;
                case 34:
                case 35:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b18);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.y);
                    break;
                case 36:
                case 37:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b19);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.z);
                    break;
                case 38:
                case 39:
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setImageResource(R.drawable.b20);
                    this.shapeBtnArry.get(this.randomInt[i2].intValue() - 1).setTag(this.A);
                    break;
            }
        }
    }

    private void speakWords(String str) {
        this.e.StopMp();
        int identifier = getBaseContext().getResources().getIdentifier(str.toLowerCase(), "raw", getBaseContext().getPackageName());
        if (identifier != 0) {
            this.e.StopMp();
            this.e.playSound(identifier);
        }
    }

    private void speakWordsTouch(String str) {
        if (!this.f) {
            this.e.StopMp();
            int identifier = getBaseContext().getResources().getIdentifier("touch", "raw", getBaseContext().getPackageName());
            if (identifier != 0) {
                this.f = true;
                this.e.StopMp();
                this.e.playSound(identifier);
            }
            final int identifier2 = getBaseContext().getResources().getIdentifier(str.toLowerCase(), "raw", getBaseContext().getPackageName());
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    if (identifier2 != 0) {
                        NumberSameActivity.this.e.StopMp();
                        NumberSameActivity.this.e.playSound(identifier2);
                        NumberSameActivity.this.f = false;
                    }
                }
            }, 500);
        }
    }

    private void startOneEmitterParticle() {
        this.e.playSound(R.raw.game_end);
        new ParticleSystem((Activity) this, 80, (int) R.drawable.spark_blue2, (long) WorkRequest.MIN_BACKOFF_MILLIS).setSpeedRange(1.0f, 2.0f).setRotationSpeed(544.0f).oneShot(findViewById(R.id.emiter_top_right), 100);
        new ParticleSystem((Activity) this, 80, (int) R.drawable.spark_blue2, (long) WorkRequest.MIN_BACKOFF_MILLIS).setSpeedRange(0.2f, 0.5f).setRotationSpeed(544.0f).oneShot(findViewById(R.id.emiter_center_left), 50);
        new ParticleSystem((Activity) this, 80, (int) R.drawable.spark_red, (long) WorkRequest.MIN_BACKOFF_MILLIS).setSpeedRange(1.0f, 2.0f).setRotationSpeed(544.0f).oneShot(findViewById(R.id.emiter_center_right), 50);
        new ParticleSystem((Activity) this, 80, (int) R.drawable.spark_red, (long) WorkRequest.MIN_BACKOFF_MILLIS).setSpeedRange(1.0f, 2.0f).setRotationSpeed(544.0f).oneShot(findViewById(R.id.emiter_top_left), 100);
    }

    public void ResetALLBtns() {
        this.button1.setVisibility(View.VISIBLE);
        this.button2.setVisibility(View.VISIBLE);
        this.button3.setVisibility(View.VISIBLE);
        this.button4.setVisibility(View.VISIBLE);
        this.button5.setVisibility(View.VISIBLE);
        this.button6.setVisibility(View.VISIBLE);
        this.button7.setVisibility(View.VISIBLE);
        this.button8.setVisibility(View.VISIBLE);
        this.button9.setVisibility(View.VISIBLE);
        this.button10.setVisibility(View.VISIBLE);
        this.button11.setVisibility(View.VISIBLE);
        this.button12.setVisibility(View.VISIBLE);
        this.button13.setVisibility(View.VISIBLE);
        this.button14.setVisibility(View.VISIBLE);
        this.button15.setVisibility(View.VISIBLE);
        this.button16.setVisibility(View.VISIBLE);
        this.button17.setVisibility(View.VISIBLE);
        this.button18.setVisibility(View.VISIBLE);
        this.button19.setVisibility(View.VISIBLE);
        this.button20.setVisibility(View.VISIBLE);
        this.button21.setVisibility(View.VISIBLE);
        this.button22.setVisibility(View.VISIBLE);
        this.button23.setVisibility(View.VISIBLE);
        this.button24.setVisibility(View.VISIBLE);
        ArrayList<ImageView> arrayList = new ArrayList<>();
        this.shapeBtnArry = arrayList;
        arrayList.add(this.button1);
        this.shapeBtnArry.add(this.button2);
        this.shapeBtnArry.add(this.button3);
        this.shapeBtnArry.add(this.button4);
        this.shapeBtnArry.add(this.button5);
        this.shapeBtnArry.add(this.button6);
        this.shapeBtnArry.add(this.button7);
        this.shapeBtnArry.add(this.button8);
        this.shapeBtnArry.add(this.button9);
        this.shapeBtnArry.add(this.button10);
        this.shapeBtnArry.add(this.button11);
        this.shapeBtnArry.add(this.button12);
        this.shapeBtnArry.add(this.button13);
        this.shapeBtnArry.add(this.button14);
        this.shapeBtnArry.add(this.button15);
        this.shapeBtnArry.add(this.button16);
        this.shapeBtnArry.add(this.button17);
        this.shapeBtnArry.add(this.button18);
        this.shapeBtnArry.add(this.button19);
        this.shapeBtnArry.add(this.button20);
        this.shapeBtnArry.add(this.button21);
        this.shapeBtnArry.add(this.button22);
        this.shapeBtnArry.add(this.button23);
        this.shapeBtnArry.add(this.button24);
    }

    public void UpdateView(ImageView imageView) {
        if (this.f4412a <= 1) {
            imageView.setVisibility(View.INVISIBLE);
            Object tag = imageView.getTag();
            this.shapeTag = tag;
            speakWords((String) tag);
            this.butObj = imageView;
        } else if (this.shapeTag.equals((String) imageView.getTag())) {
            this.f4412a = 0;
            int i2 = this.countFinish + 1;
            this.countFinish = i2;
            if (i2 == 12) {
                startWellDone();
            }
            imageView.setVisibility(View.INVISIBLE);
            this.butObj.setVisibility(View.INVISIBLE);
            this.e.playSound(R.raw.right);
            this.e.speakApplause();
        } else {
            this.e.playSound(R.raw.wrong);
            speakWordsTouch((String) this.shapeTag);
        }
    }

    public float getFloatvalue() {
        TypedValue typedValue = new TypedValue();
        this.f4413b.getValue(R.dimen.speech_rate, typedValue, true);
        return typedValue.getFloat();
    }

    public void getShapeString() {
        this.g = this.f4413b.getString(R.string.a);
        this.h = this.f4413b.getString(R.string.b);
        this.j = this.f4413b.getString(R.string.c);
        this.k = this.f4413b.getString(R.string.d);
        this.l = this.f4413b.getString(R.string.e);
        this.m = this.f4413b.getString(R.string.f);
        this.n = this.f4413b.getString(R.string.g);
        this.o = this.f4413b.getString(R.string.h);
        this.p = this.f4413b.getString(R.string.i);
        this.q = this.f4413b.getString(R.string.j);
        this.r = this.f4413b.getString(R.string.k);
        this.s = this.f4413b.getString(R.string.l);
        this.t = this.f4413b.getString(R.string.m);
        this.u = this.f4413b.getString(R.string.n);
        this.v = this.f4413b.getString(R.string.o);
        this.w = this.f4413b.getString(R.string.p);
        this.x = this.f4413b.getString(R.string.q);
        this.y = this.f4413b.getString(R.string.r);
        this.z = this.f4413b.getString(R.string.s);
        this.A = this.f4413b.getString(R.string.t);
    }

    public void onBackPressed() {
        this.e.StopMp();
        this.homeButtonClicked = true;
        finishActivity();
    }

    public void onClick(View view) {
        this.f4412a++;
        switch (view.getId()) {
            case R.id.button1 /*2131296439*/:
                UpdateView(this.button1);
                return;
            case R.id.button10 /*2131296440*/:
                UpdateView(this.button10);
                return;
            case R.id.button11 /*2131296441*/:
                UpdateView(this.button11);
                return;
            case R.id.button12 /*2131296442*/:
                UpdateView(this.button12);
                return;
            case R.id.button13 /*2131296443*/:
                UpdateView(this.button13);
                return;
            case R.id.button14 /*2131296444*/:
                UpdateView(this.button14);
                return;
            case R.id.button15 /*2131296445*/:
                UpdateView(this.button15);
                return;
            case R.id.button16 /*2131296446*/:
                UpdateView(this.button16);
                return;
            case R.id.button17 /*2131296447*/:
                UpdateView(this.button17);
                return;
            case R.id.button18 /*2131296448*/:
                UpdateView(this.button18);
                return;
            case R.id.button19 /*2131296449*/:
                UpdateView(this.button19);
                return;
            case R.id.button2 /*2131296450*/:
                UpdateView(this.button2);
                return;
            case R.id.button20 /*2131296451*/:
                UpdateView(this.button20);
                return;
            case R.id.button21 /*2131296452*/:
                UpdateView(this.button21);
                return;
            case R.id.button22 /*2131296453*/:
                UpdateView(this.button22);
                return;
            case R.id.button23 /*2131296454*/:
                UpdateView(this.button23);
                return;
            case R.id.button24 /*2131296455*/:
                UpdateView(this.button24);
                return;
            case R.id.button3 /*2131296456*/:
                UpdateView(this.button3);
                return;
            case R.id.button4 /*2131296457*/:
                UpdateView(this.button4);
                return;
            case R.id.button5 /*2131296458*/:
                UpdateView(this.button5);
                return;
            case R.id.button6 /*2131296459*/:
                UpdateView(this.button6);
                return;
            case R.id.button7 /*2131296460*/:
                UpdateView(this.button7);
                return;
            case R.id.button8 /*2131296461*/:
                UpdateView(this.button8);
                return;
            case R.id.button9 /*2131296462*/:
                UpdateView(this.button9);
                return;
            default:
                return;
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.homeButtonClicked = false;
        setContentView(R.layout.number_same);
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.e = myMediaPlayer;
        myMediaPlayer.playSound(R.raw.matchingpair);
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
        System.out.println("callig times1");
        this.f4413b = getResources();
        this.f4414c = getFloatvalue();
        getShapeString();
        this.button1 = (ImageView) findViewById(R.id.button1);
        this.button2 = (ImageView) findViewById(R.id.button2);
        this.button3 = (ImageView) findViewById(R.id.button3);
        this.button4 = (ImageView) findViewById(R.id.button4);
        this.button5 = (ImageView) findViewById(R.id.button5);
        this.button6 = (ImageView) findViewById(R.id.button6);
        this.button7 = (ImageView) findViewById(R.id.button7);
        this.button8 = (ImageView) findViewById(R.id.button8);
        this.button9 = (ImageView) findViewById(R.id.button9);
        this.button10 = (ImageView) findViewById(R.id.button10);
        this.button11 = (ImageView) findViewById(R.id.button11);
        this.button12 = (ImageView) findViewById(R.id.button12);
        this.button13 = (ImageView) findViewById(R.id.button13);
        this.button14 = (ImageView) findViewById(R.id.button14);
        this.button15 = (ImageView) findViewById(R.id.button15);
        this.button16 = (ImageView) findViewById(R.id.button16);
        this.button17 = (ImageView) findViewById(R.id.button17);
        this.button18 = (ImageView) findViewById(R.id.button18);
        this.button19 = (ImageView) findViewById(R.id.button19);
        this.button20 = (ImageView) findViewById(R.id.button20);
        this.button21 = (ImageView) findViewById(R.id.button21);
        this.button22 = (ImageView) findViewById(R.id.button22);
        this.button23 = (ImageView) findViewById(R.id.button23);
        this.button24 = (ImageView) findViewById(R.id.button24);
        this.button1.setOnClickListener(this);
        this.button2.setOnClickListener(this);
        this.button3.setOnClickListener(this);
        this.button4.setOnClickListener(this);
        this.button5.setOnClickListener(this);
        this.button6.setOnClickListener(this);
        this.button7.setOnClickListener(this);
        this.button8.setOnClickListener(this);
        this.button9.setOnClickListener(this);
        this.button10.setOnClickListener(this);
        this.button11.setOnClickListener(this);
        this.button12.setOnClickListener(this);
        this.button13.setOnClickListener(this);
        this.button14.setOnClickListener(this);
        this.button15.setOnClickListener(this);
        this.button16.setOnClickListener(this);
        this.button17.setOnClickListener(this);
        this.button18.setOnClickListener(this);
        this.button19.setOnClickListener(this);
        this.button20.setOnClickListener(this);
        this.button21.setOnClickListener(this);
        this.button22.setOnClickListener(this);
        this.button23.setOnClickListener(this);
        this.button24.setOnClickListener(this);
        ResetALLBtns();
        this.btnHome = (ImageView) findViewById(R.id.btnHome);
        this.frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        this.btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NumberSameActivity.this.e.StopMp();
                NumberSameActivity.this.playClickSound();
                boolean unused = NumberSameActivity.this.homeButtonClicked = true;
                NumberSameActivity.this.clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberSameActivity.this.finishActivity();
                    }
                }, 500);
            }
        });
        setshapeButtonRandomly();
        this.myAdView = new MyAdView(this);
        
        setAd();
    }

    public void onDestroy() {
        super.onDestroy();
        this.e.StopMp();
        System.gc();
        Runtime.getRuntime().gc();
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void playClickSound() {
        this.e.StopMp();
        this.e.playSound(R.raw.click);
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

    public void releaseMemory() {
        this.button1.setImageDrawable((Drawable) null);
        this.button2.setImageDrawable((Drawable) null);
        this.button3.setImageDrawable((Drawable) null);
        this.button4.setImageDrawable((Drawable) null);
        this.button5.setImageDrawable((Drawable) null);
        this.button6.setImageDrawable((Drawable) null);
        this.button7.setImageDrawable((Drawable) null);
        this.button8.setImageDrawable((Drawable) null);
        this.button9.setImageDrawable((Drawable) null);
        this.button10.setImageDrawable((Drawable) null);
        this.button11.setImageDrawable((Drawable) null);
        this.button12.setImageDrawable((Drawable) null);
        this.button13.setImageDrawable((Drawable) null);
        this.button14.setImageDrawable((Drawable) null);
        this.button15.setImageDrawable((Drawable) null);
        this.button16.setImageDrawable((Drawable) null);
        this.button17.setImageDrawable((Drawable) null);
        this.button18.setImageDrawable((Drawable) null);
        this.button19.setImageDrawable((Drawable) null);
        this.button20.setImageDrawable((Drawable) null);
        this.button21.setImageDrawable((Drawable) null);
        this.button22.setImageDrawable((Drawable) null);
        this.button23.setImageDrawable((Drawable) null);
        this.button24.setImageDrawable((Drawable) null);
    }

    public void startWellDone() {
        this.e.playSound(R.raw.game_end);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                NumberSameActivity.this.e.playSound(R.raw.matchingpair);
                NumberSameActivity.this.frameLayout.setVisibility(View.GONE);
                NumberSameActivity.this.releaseMemory();
                int unused = NumberSameActivity.this.countFinish = 0;
                NumberSameActivity.this.ResetALLBtns();
                NumberSameActivity.this.setshapeButtonRandomly();
            }
        }, 3000);
    }

    public Integer[] storeRandomNumbers(int i2) {
        ArrayList<Integer> randomize = randomize(i2);
        Collections.shuffle(randomize);
        Integer[] numArr = new Integer[i2];
        randomize.toArray(numArr);
        return numArr;
    }
}
