package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.widget.ImageViewCompat;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.media.SoundManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;
import com.google.android.gms.common.ConnectionResult;
import com.plattysoft.leonids.ParticleSystem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberMatchActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "NumberMatchActivity";
    public static String speakSound = "";

    
    ImageView f4402a;
    private ArrayList<Integer> arrFourShape;

    
    ImageView f4403b;

    
    ImageView f4404c;

    
    ImageView f4405d;
    ImageView e;
    
    public Integer[] egg_cracks = {Integer.valueOf(R.drawable.eg_1), Integer.valueOf(R.drawable.eg_2), Integer.valueOf(R.drawable.eg_3), Integer.valueOf(R.drawable.eg_4), Integer.valueOf(R.drawable.eg_5), Integer.valueOf(R.drawable.eg_6)};
    ImageView f;
    public ArrayList<ImageView> fourNumBtnList;
    ImageView g;
    ImageView h;
    ImageView i;
    public int image_id;
    ImageView j;
    ImageView k;
    ImageView l;
    ImageView m;
    private MyAdView myAdView;
    ImageView n;
    LinearLayout o;
    ArrayList<ImageView> p;
    String q;
    String r;
    private Integer[] randomInt;
    private Integer[] randomShape;
    public int re_count = 0;
    MyMediaPlayer s;
    final Handler t = new Handler(Looper.getMainLooper());
    final Handler u = new Handler(Looper.getMainLooper());

    private void FinishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    
    public void SetVisibilityButtons(boolean z) {
        if (z) {
            this.o.setVisibility(View.VISIBLE);
            this.f4404c.setVisibility(View.VISIBLE);
            if (this.fourNumBtnList.size() > 0) {
                for (int i2 = 0; i2 < this.fourNumBtnList.size(); i2++) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.img_visible);
                    loadAnimation.setStartOffset((long) (i2 * 300));
                    this.fourNumBtnList.get(i2).setVisibility(View.VISIBLE);
                    this.fourNumBtnList.get(i2).startAnimation(loadAnimation);
                }
                return;
            }
            return;
        }
        this.o.setVisibility(View.GONE);
        this.f4405d.setVisibility(View.GONE);
        this.e.setVisibility(View.GONE);
        this.f.setVisibility(View.GONE);
        this.g.setVisibility(View.GONE);
    }

    private void UpdateRewardUI(int i2, int i3) {
        this.f4403b.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.egg_shake));
        if (i2 <= 5) {
            this.f4403b.setImageResource(this.egg_cracks[i2].intValue());
        }
        if (i2 == 5) {
            this.f4403b.setImageResource(R.drawable.chick_anim_final);
            ((AnimationDrawable) this.f4403b.getDrawable()).start();
            startOneShotParticle(this.f4403b);
            this.s.playSound(R.raw.clap);
            this.t.postDelayed(new Runnable() {
                public void run() {
                    NumberMatchActivity numberMatchActivity = NumberMatchActivity.this;
                    numberMatchActivity.f4403b.setImageResource(numberMatchActivity.egg_cracks[0].intValue());
                    NumberMatchActivity numberMatchActivity2 = NumberMatchActivity.this;
                    numberMatchActivity2.re_count = 0;
                    numberMatchActivity2.j.setImageResource(R.drawable.nm_star_blank);
                    NumberMatchActivity.this.k.setImageResource(R.drawable.nm_star_blank);
                    NumberMatchActivity.this.l.setImageResource(R.drawable.nm_star_blank);
                    NumberMatchActivity.this.m.setImageResource(R.drawable.nm_star_blank);
                    NumberMatchActivity.this.n.setImageResource(R.drawable.nm_star_blank);
                    NumberMatchActivity.this.s.playSound(R.raw.appear);
                }
            }, 4000);
        }
    }

    private void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    public static int getRandom(Integer[] numArr) {
        return numArr[new Random().nextInt(numArr.length)].intValue();
    }

    private void init() {
        this.f4402a = (ImageView) findViewById(R.id.btnHome);
        this.f4403b = (ImageView) findViewById(R.id.iv_reward_img);
        this.f4404c = (ImageView) findViewById(R.id.iv_match_num);
        this.o = (LinearLayout) findViewById(R.id.num_lay);
        this.f4405d = (ImageView) findViewById(R.id.iv_num_one);
        this.e = (ImageView) findViewById(R.id.iv_num_two);
        this.f = (ImageView) findViewById(R.id.iv_num_three);
        this.g = (ImageView) findViewById(R.id.iv_num_four);
        this.h = (ImageView) findViewById(R.id.iv_crack_img);
        this.i = (ImageView) findViewById(R.id.iv_hen);
        this.j = (ImageView) findViewById(R.id.iv_pb_1);
        this.k = (ImageView) findViewById(R.id.iv_pb_2);
        this.l = (ImageView) findViewById(R.id.iv_pb_3);
        this.m = (ImageView) findViewById(R.id.iv_pb_4);
        this.n = (ImageView) findViewById(R.id.iv_pb_5);
        this.q = getResources().getString(R.string.correct);
        this.r = getResources().getString(R.string.incorrect);
        ArrayList<ImageView> arrayList = new ArrayList<>();
        this.fourNumBtnList = arrayList;
        arrayList.add(this.f4405d);
        this.fourNumBtnList.add(this.e);
        this.fourNumBtnList.add(this.f);
        this.fourNumBtnList.add(this.g);
        this.f4402a.setOnClickListener(this);
        this.f4403b.setOnClickListener(this);
        this.f4404c.setOnClickListener(this);
        this.f4405d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
        SetVisibilityButtons(true);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    
    public void setMatchNum() {
        switch (this.randomShape[0].intValue()) {
            case 1:
                try {
                    Picasso.get().load((int) R.drawable.a1).into(this.f4404c);
                } catch (Exception unused) {
                    this.f4404c.setImageResource(R.drawable.a1);
                }
                speakSound = "n_1";
                return;
            case 2:
                try {
                    Picasso.get().load((int) R.drawable.a2).into(this.f4404c);
                } catch (Exception unused2) {
                    this.f4404c.setImageResource(R.drawable.a2);
                }
                speakSound = "n_2";
                return;
            case 3:
                try {
                    Picasso.get().load((int) R.drawable.a3).into(this.f4404c);
                } catch (Exception unused3) {
                    this.f4404c.setImageResource(R.drawable.a3);
                }
                speakSound = "n_3";
                return;
            case 4:
                try {
                    Picasso.get().load((int) R.drawable.a4).into(this.f4404c);
                } catch (Exception unused4) {
                    this.f4404c.setImageResource(R.drawable.a4);
                }
                speakSound = "n_4";
                return;
            case 5:
                try {
                    Picasso.get().load((int) R.drawable.a5).into(this.f4404c);
                } catch (Exception unused5) {
                    this.f4404c.setImageResource(R.drawable.a5);
                }
                speakSound = "n_5";
                return;
            case 6:
                try {
                    Picasso.get().load((int) R.drawable.a6).into(this.f4404c);
                } catch (Exception unused6) {
                    this.f4404c.setImageResource(R.drawable.a6);
                }
                speakSound = "n_6";
                return;
            case 7:
                try {
                    Picasso.get().load((int) R.drawable.a7).into(this.f4404c);
                } catch (Exception unused7) {
                    this.f4404c.setImageResource(R.drawable.a7);
                }
                speakSound = "n_7";
                return;
            case 8:
                try {
                    Picasso.get().load((int) R.drawable.a8).into(this.f4404c);
                } catch (Exception unused8) {
                    this.f4404c.setImageResource(R.drawable.a8);
                }
                speakSound = "n_8";
                return;
            case 9:
                try {
                    Picasso.get().load((int) R.drawable.a9).into(this.f4404c);
                } catch (Exception unused9) {
                    this.f4404c.setImageResource(R.drawable.a9);
                }
                speakSound = "n_9";
                return;
            case 10:
                try {
                    Picasso.get().load((int) R.drawable.a10).into(this.f4404c);
                } catch (Exception unused10) {
                    this.f4404c.setImageResource(R.drawable.a10);
                }
                speakSound = "n_10";
                return;
            case 11:
                try {
                    Picasso.get().load((int) R.drawable.a11).into(this.f4404c);
                } catch (Exception unused11) {
                    this.f4404c.setImageResource(R.drawable.a11);
                }
                speakSound = "n_11";
                return;
            case 12:
                try {
                    Picasso.get().load((int) R.drawable.a12).into(this.f4404c);
                } catch (Exception unused12) {
                    this.f4404c.setImageResource(R.drawable.a12);
                }
                speakSound = "n_12";
                return;
            case 13:
                try {
                    Picasso.get().load((int) R.drawable.a13).into(this.f4404c);
                } catch (Exception unused13) {
                    this.f4404c.setImageResource(R.drawable.a13);
                }
                speakSound = "n_13";
                return;
            case 14:
                try {
                    Picasso.get().load((int) R.drawable.a14).into(this.f4404c);
                } catch (Exception unused14) {
                    this.f4404c.setImageResource(R.drawable.a14);
                }
                speakSound = "n_14";
                return;
            case 15:
                try {
                    Picasso.get().load((int) R.drawable.a15).into(this.f4404c);
                } catch (Exception unused15) {
                    this.f4404c.setImageResource(R.drawable.a15);
                }
                speakSound = "n_15";
                return;
            case 16:
                try {
                    Picasso.get().load((int) R.drawable.a16).into(this.f4404c);
                } catch (Exception unused16) {
                    this.f4404c.setImageResource(R.drawable.a16);
                }
                speakSound = "n_16";
                return;
            case 17:
                try {
                    Picasso.get().load((int) R.drawable.a17).into(this.f4404c);
                } catch (Exception unused17) {
                    this.f4404c.setImageResource(R.drawable.a17);
                }
                speakSound = "n_17";
                return;
            case 18:
                try {
                    Picasso.get().load((int) R.drawable.a18).into(this.f4404c);
                } catch (Exception unused18) {
                    this.f4404c.setImageResource(R.drawable.a18);
                }
                speakSound = "n_18";
                return;
            case 19:
                try {
                    Picasso.get().load((int) R.drawable.a19).into(this.f4404c);
                } catch (Exception unused19) {
                    this.f4404c.setImageResource(R.drawable.a19);
                }
                speakSound = "n_19";
                return;
            case 20:
                try {
                    Picasso.get().load((int) R.drawable.a20).into(this.f4404c);
                } catch (Exception unused20) {
                    this.f4404c.setImageResource(R.drawable.a20);
                }
                speakSound = "n_20";
                return;
            default:
                return;
        }
    }

    
    public void setRandomShape() {
        speakSound = "";
        this.f4405d.setClickable(true);
        this.e.setClickable(true);
        this.f.setClickable(true);
        this.g.setClickable(true);
        this.p = new ArrayList<>();
        this.arrFourShape = new ArrayList<>();
        this.randomInt = storeRandomNumbers(20);
        this.randomShape = storeRandomNumbers(20);
        this.p.add(this.f4405d);
        this.p.add(this.e);
        this.p.add(this.f);
        this.p.add(this.g);
        Integer[] numArr = this.randomInt;
        if (numArr[0] == this.randomShape[0]) {
            this.arrFourShape.add(numArr[7]);
        } else {
            this.arrFourShape.add(numArr[0]);
        }
        Integer[] numArr2 = this.randomInt;
        if (numArr2[1] == this.randomShape[0]) {
            this.arrFourShape.add(numArr2[4]);
        } else {
            this.arrFourShape.add(numArr2[1]);
        }
        Integer[] numArr3 = this.randomInt;
        if (numArr3[2] == this.randomShape[0]) {
            this.arrFourShape.add(numArr3[8]);
        } else {
            this.arrFourShape.add(numArr3[2]);
        }
        this.arrFourShape.add(this.randomShape[0]);
        Collections.shuffle(this.arrFourShape);
        for (int i2 = 0; i2 < 4; i2++) {
            switch (this.arrFourShape.get(i2).intValue()) {
                case 1:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b1).into(this.p.get(i2));
                        break;
                    } catch (Exception unused) {
                        this.p.get(i2).setImageResource(R.drawable.b1);
                        break;
                    }
                case 2:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b2).into(this.p.get(i2));
                        break;
                    } catch (Exception unused2) {
                        this.p.get(i2).setImageResource(R.drawable.b2);
                        break;
                    }
                case 3:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b3).into(this.p.get(i2));
                        break;
                    } catch (Exception unused3) {
                        this.p.get(i2).setImageResource(R.drawable.b3);
                        break;
                    }
                case 4:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b4).into(this.p.get(i2));
                        break;
                    } catch (Exception unused4) {
                        this.p.get(i2).setImageResource(R.drawable.b4);
                        break;
                    }
                case 5:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b5).into(this.p.get(i2));
                        break;
                    } catch (Exception unused5) {
                        this.p.get(i2).setImageResource(R.drawable.b5);
                        break;
                    }
                case 6:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b6).into(this.p.get(i2));
                        break;
                    } catch (Exception unused6) {
                        this.p.get(i2).setImageResource(R.drawable.b6);
                        break;
                    }
                case 7:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b7).into(this.p.get(i2));
                        break;
                    } catch (Exception unused7) {
                        this.p.get(i2).setImageResource(R.drawable.b7);
                        break;
                    }
                case 8:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b8).into(this.p.get(i2));
                        break;
                    } catch (Exception unused8) {
                        this.p.get(i2).setImageResource(R.drawable.b8);
                        break;
                    }
                case 9:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b9).into(this.p.get(i2));
                        break;
                    } catch (Exception unused9) {
                        this.p.get(i2).setImageResource(R.drawable.b9);
                        break;
                    }
                case 10:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b10).into(this.p.get(i2));
                        break;
                    } catch (Exception unused10) {
                        this.p.get(i2).setImageResource(R.drawable.b10);
                        break;
                    }
                case 11:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b11).into(this.p.get(i2));
                        break;
                    } catch (Exception unused11) {
                        this.p.get(i2).setImageResource(R.drawable.b11);
                        break;
                    }
                case 12:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b12).into(this.p.get(i2));
                        break;
                    } catch (Exception unused12) {
                        this.p.get(i2).setImageResource(R.drawable.b12);
                        break;
                    }
                case 13:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b13).into(this.p.get(i2));
                        break;
                    } catch (Exception unused13) {
                        this.p.get(i2).setImageResource(R.drawable.b13);
                        break;
                    }
                case 14:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b14).into(this.p.get(i2));
                        break;
                    } catch (Exception unused14) {
                        this.p.get(i2).setImageResource(R.drawable.b14);
                        break;
                    }
                case 15:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b15).into(this.p.get(i2));
                        break;
                    } catch (Exception unused15) {
                        this.p.get(i2).setImageResource(R.drawable.b15);
                        break;
                    }
                case 16:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b16).into(this.p.get(i2));
                        break;
                    } catch (Exception unused16) {
                        this.p.get(i2).setImageResource(R.drawable.b16);
                        break;
                    }
                case 17:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b17).into(this.p.get(i2));
                        break;
                    } catch (Exception unused17) {
                        this.p.get(i2).setImageResource(R.drawable.b17);
                        break;
                    }
                case 18:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b18).into(this.p.get(i2));
                        break;
                    } catch (Exception unused18) {
                        this.p.get(i2).setImageResource(R.drawable.b18);
                        break;
                    }
                case 19:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b19).into(this.p.get(i2));
                        break;
                    } catch (Exception unused19) {
                        this.p.get(i2).setImageResource(R.drawable.b19);
                        break;
                    }
                case 20:
                    if (this.arrFourShape.get(i2) == this.randomShape[0]) {
                        this.p.get(i2).setTag(this.q);
                    } else {
                        this.p.get(i2).setTag(this.r);
                    }
                    try {
                        Picasso.get().load((int) R.drawable.b20).into(this.p.get(i2));
                        break;
                    } catch (Exception unused20) {
                        this.p.get(i2).setImageResource(R.drawable.b20);
                        break;
                    }
            }
        }
    }

    private void speakOut(String str) {
        int identifier = getBaseContext().getResources().getIdentifier(str, "raw", getBaseContext().getPackageName());
        if (identifier != 0) {
            this.s.StopMp();
            this.s.playSound(identifier);
        }
    }

    private void startOneShotParticle(ImageView imageView) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.25f, 0.45f).oneShot(imageView, 20);
    }

    public void ResetActivity() {
        this.f4405d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        try {
            this.s.StopMp();
            this.t.removeCallbacksAndMessages((Object) null);
            this.u.removeCallbacksAndMessages((Object) null);
        } catch (Exception unused) {
            this.s.StopMp();
        }
        FinishActivity();
    }

    public void loadProgressBar(int i2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.img_visible);
        if (i2 == 1) {
            this.j.setImageResource(R.drawable.nm_star);
            this.j.startAnimation(loadAnimation);
        } else if (i2 == 2) {
            this.j.setImageResource(R.drawable.nm_star);
            this.k.setImageResource(R.drawable.nm_star);
            this.k.startAnimation(loadAnimation);
        } else if (i2 == 3) {
            this.j.setImageResource(R.drawable.nm_star);
            this.k.setImageResource(R.drawable.nm_star);
            this.l.setImageResource(R.drawable.nm_star);
            this.l.startAnimation(loadAnimation);
        } else if (i2 == 4) {
            this.j.setImageResource(R.drawable.nm_star);
            this.k.setImageResource(R.drawable.nm_star);
            this.l.setImageResource(R.drawable.nm_star);
            this.m.setImageResource(R.drawable.nm_star);
            this.m.startAnimation(loadAnimation);
        } else if (i2 == 5) {
            this.j.setImageResource(R.drawable.nm_star);
            this.k.setImageResource(R.drawable.nm_star);
            this.l.setImageResource(R.drawable.nm_star);
            this.m.setImageResource(R.drawable.nm_star);
            this.n.setImageResource(R.drawable.nm_star);
            this.n.startAnimation(loadAnimation);
        } else {
            this.j.setImageResource(R.drawable.nm_star_blank);
            this.k.setImageResource(R.drawable.nm_star_blank);
            this.l.setImageResource(R.drawable.nm_star_blank);
            this.m.setImageResource(R.drawable.nm_star_blank);
            this.n.setImageResource(R.drawable.nm_star_blank);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        ResetActivity();
    }

    public void onClick(View view) {
        int i2 = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        try {
            if (this.re_count == 4) {
                i2 = PathInterpolatorCompat.MAX_NUM_POINTS;
            }
            this.image_id = this.egg_cracks[0].intValue();
            Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
            int id = view.getId();
            if (id == R.id.btnHome) {
                SoundManager.playSound(1, 1.0f);
                clickBounceAnim(view);
                this.t.postDelayed(new Runnable() {
                    public void run() {
                        NumberMatchActivity.this.ResetActivity();
                    }
                }, 500);
            } else if (id == R.id.iv_hen) {
                this.i.startAnimation(AnimationUtils.loadAnimation(this, R.anim.clockwise));
                this.s.playSound(R.raw.chicken_anim);
            } else if (id != R.id.iv_reward_img) {
                switch (id) {
                    case R.id.iv_num_four /*2131296770*/:
                        String str = (String) this.g.getTag();
                        Log.d(TAG, str);
                        if (str.equals(this.q)) {
                            SetVisibilityButtons(false);
                            this.g.setClickable(false);
                            speakOut(speakSound);
                            this.s.playSound(R.raw.egg_crack);
                            int i3 = this.re_count + 1;
                            this.re_count = i3;
                            UpdateRewardUI(i3, this.image_id);
                            ImageViewCompat.setImageTintList(this.f4404c, (ColorStateList) null);
                            this.f4404c.setAlpha(1.0f);
                            loadProgressBar(this.re_count);
                            this.t.postDelayed(new Runnable() {
                                public void run() {
                                    NumberMatchActivity.this.f4404c.setVisibility(View.INVISIBLE);
                                    NumberMatchActivity.this.setRandomShape();
                                    NumberMatchActivity.this.setMatchNum();
                                    NumberMatchActivity.this.SetVisibilityButtons(true);
                                    ImageViewCompat.setImageTintList(NumberMatchActivity.this.f4404c, ColorStateList.valueOf(Color.parseColor("#000000")));
                                    NumberMatchActivity.this.f4404c.setAlpha(0.2f);
                                }
                            }, (long) i2);
                            return;
                        }
                        this.s.playSound(R.raw.wrong);
                        this.g.startAnimation(loadAnimation);
                        return;
                    case R.id.iv_num_one /*2131296771*/:
                        String str2 = (String) this.f4405d.getTag();
                        Log.d(TAG, "" + str2);
                        if (str2.equals(this.q)) {
                            SetVisibilityButtons(false);
                            this.f4405d.setClickable(false);
                            speakOut(speakSound);
                            this.s.playSound(R.raw.egg_crack);
                            int i4 = this.re_count + 1;
                            this.re_count = i4;
                            UpdateRewardUI(i4, this.image_id);
                            ImageViewCompat.setImageTintList(this.f4404c, (ColorStateList) null);
                            this.f4404c.setAlpha(1.0f);
                            loadProgressBar(this.re_count);
                            this.t.postDelayed(new Runnable() {
                                public void run() {
                                    NumberMatchActivity.this.f4404c.setVisibility(View.INVISIBLE);
                                    NumberMatchActivity.this.setRandomShape();
                                    NumberMatchActivity.this.setMatchNum();
                                    NumberMatchActivity.this.SetVisibilityButtons(true);
                                    ImageViewCompat.setImageTintList(NumberMatchActivity.this.f4404c, ColorStateList.valueOf(Color.parseColor("#000000")));
                                    NumberMatchActivity.this.f4404c.setAlpha(0.2f);
                                }
                            }, (long) i2);
                            return;
                        }
                        this.s.playSound(R.raw.wrong);
                        this.f4405d.startAnimation(loadAnimation);
                        return;
                    case R.id.iv_num_three /*2131296772*/:
                        String str3 = (String) this.f.getTag();
                        Log.d(TAG, str3);
                        if (str3.equals(this.q)) {
                            SetVisibilityButtons(false);
                            this.f.setClickable(false);
                            speakOut(speakSound);
                            this.s.playSound(R.raw.egg_crack);
                            int i5 = this.re_count + 1;
                            this.re_count = i5;
                            UpdateRewardUI(i5, this.image_id);
                            ImageViewCompat.setImageTintList(this.f4404c, (ColorStateList) null);
                            this.f4404c.setAlpha(1.0f);
                            loadProgressBar(this.re_count);
                            this.t.postDelayed(new Runnable() {
                                public void run() {
                                    NumberMatchActivity.this.f4404c.setVisibility(View.INVISIBLE);
                                    NumberMatchActivity.this.setRandomShape();
                                    NumberMatchActivity.this.setMatchNum();
                                    NumberMatchActivity.this.SetVisibilityButtons(true);
                                    ImageViewCompat.setImageTintList(NumberMatchActivity.this.f4404c, ColorStateList.valueOf(Color.parseColor("#000000")));
                                    NumberMatchActivity.this.f4404c.setAlpha(0.2f);
                                }
                            }, (long) i2);
                            return;
                        }
                        this.s.playSound(R.raw.wrong);
                        this.f.startAnimation(loadAnimation);
                        return;
                    case R.id.iv_num_two /*2131296773*/:
                        String str4 = (String) this.e.getTag();
                        Log.d(TAG, str4);
                        if (str4.equals(this.q)) {
                            SetVisibilityButtons(false);
                            this.e.setClickable(false);
                            speakOut(speakSound);
                            this.s.playSound(R.raw.egg_crack);
                            int i6 = this.re_count + 1;
                            this.re_count = i6;
                            UpdateRewardUI(i6, this.image_id);
                            ImageViewCompat.setImageTintList(this.f4404c, (ColorStateList) null);
                            this.f4404c.setAlpha(1.0f);
                            loadProgressBar(this.re_count);
                            this.t.postDelayed(new Runnable() {
                                public void run() {
                                    NumberMatchActivity.this.f4404c.setVisibility(View.INVISIBLE);
                                    NumberMatchActivity.this.setRandomShape();
                                    NumberMatchActivity.this.setMatchNum();
                                    NumberMatchActivity.this.SetVisibilityButtons(true);
                                    ImageViewCompat.setImageTintList(NumberMatchActivity.this.f4404c, ColorStateList.valueOf(Color.parseColor("#000000")));
                                    NumberMatchActivity.this.f4404c.setAlpha(0.2f);
                                }
                            }, (long) i2);
                            return;
                        }
                        this.s.playSound(R.raw.wrong);
                        this.e.startAnimation(loadAnimation);
                        return;
                    default:
                        return;
                }
            } else {
                this.f4403b.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.egg_shake));
                this.s.playSound(R.raw.chick);
            }
        } catch (Exception unused) {
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_number_match);
        this.s = new MyMediaPlayer(this);
        init();
        setRandomShape();
        setMatchNum();
        this.s.playSound(R.raw.appear);
        this.myAdView = new MyAdView(this);
        
        setAd();
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    
    public void onStart() {
        super.onStart();
        this.f4403b.setImageResource(this.egg_cracks[0].intValue());
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
