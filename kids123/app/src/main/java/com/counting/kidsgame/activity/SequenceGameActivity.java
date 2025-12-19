package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SequenceGameActivity extends Activity implements View.OnClickListener {


    Integer[] f4471a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private Animation animShake;


    float f4472b;


    Resources f4473c;


    private Drawable f4474d;
    private Drawable d1;
    MyMediaPlayer e;
    private Typeface font;
    private ImageView home;

    public boolean homeButtonClicked = false;
    private Intent i;
    private ImageView iv;
    private ImageView iv1;
    private ImageView iv10;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv7;
    private ImageView iv8;
    private ImageView iv9;
    private int length;
    private MediaPlayer mpRight;
    private MediaPlayer mpWrong;
    private MyAdView myAdView;
    private boolean pressedEight = false;
    private boolean pressedFive = false;
    private boolean pressedFour = false;
    private boolean pressedNne = false;
    private boolean pressedOne = false;
    private boolean pressedSeven = false;
    private boolean pressedSix = false;
    private boolean pressedThree = false;
    private boolean pressedTwo = false;

    public RelativeLayout rl;
    private RelativeLayout rl_1;
    private RelativeLayout rl_10;
    private RelativeLayout rl_2;
    private RelativeLayout rl_3;
    private RelativeLayout rl_4;
    private RelativeLayout rl_5;
    private RelativeLayout rl_6;
    private RelativeLayout rl_7;
    private RelativeLayout rl_8;
    private RelativeLayout rl_9;
    private TextView tv;
    private TextView tv1;
    private TextView tv10;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tvInfo;


    public void addGroups() {
        this.pressedOne = false;
        this.pressedTwo = false;
        this.pressedThree = false;
        this.pressedFour = false;
        this.pressedFive = false;
        this.pressedSix = false;
        this.pressedSeven = false;
        this.pressedEight = false;
        this.pressedNne = false;
        this.f4471a = storeRandomNumbers(10);
        this.rl_1.setBackgroundResource(0);
        this.iv1.setVisibility(View.VISIBLE);
        this.rl_1.setVisibility(View.VISIBLE);
        this.tv1.setVisibility(View.VISIBLE);
        this.tv1.setText(Integer.toString(this.f4471a[0].intValue()));
        this.rl_2.setBackgroundResource(0);
        this.iv2.setVisibility(View.VISIBLE);
        this.rl_2.setVisibility(View.VISIBLE);
        this.tv2.setVisibility(View.VISIBLE);
        this.tv2.setText(Integer.toString(this.f4471a[1].intValue()));
        this.rl_3.setBackgroundResource(0);
        this.iv3.setVisibility(View.VISIBLE);
        this.rl_3.setVisibility(View.VISIBLE);
        this.tv3.setVisibility(View.VISIBLE);
        this.tv3.setText(Integer.toString(this.f4471a[2].intValue()));
        this.rl_4.setBackgroundResource(0);
        this.iv4.setVisibility(View.VISIBLE);
        this.rl_4.setVisibility(View.VISIBLE);
        this.tv4.setVisibility(View.VISIBLE);
        this.tv4.setText(Integer.toString(this.f4471a[3].intValue()));
        this.rl_5.setBackgroundResource(0);
        this.iv5.setVisibility(View.VISIBLE);
        this.rl_5.setVisibility(View.VISIBLE);
        this.tv5.setVisibility(View.VISIBLE);
        this.tv5.setText(Integer.toString(this.f4471a[4].intValue()));
        this.rl_6.setBackgroundResource(0);
        this.iv6.setVisibility(View.VISIBLE);
        this.rl_6.setVisibility(View.VISIBLE);
        this.tv6.setVisibility(View.VISIBLE);
        this.tv6.setText(Integer.toString(this.f4471a[5].intValue()));
        this.rl_7.setBackgroundResource(0);
        this.iv7.setVisibility(View.VISIBLE);
        this.rl_7.setVisibility(View.VISIBLE);
        this.tv7.setVisibility(View.VISIBLE);
        this.tv7.setText(Integer.toString(this.f4471a[6].intValue()));
        this.rl_8.setBackgroundResource(0);
        this.iv8.setVisibility(View.VISIBLE);
        this.rl_8.setVisibility(View.VISIBLE);
        this.tv8.setVisibility(View.VISIBLE);
        this.tv8.setText(Integer.toString(this.f4471a[7].intValue()));
        this.rl_9.setBackgroundResource(0);
        this.iv9.setVisibility(View.VISIBLE);
        this.rl_9.setVisibility(View.VISIBLE);
        this.tv9.setVisibility(View.VISIBLE);
        this.tv9.setText(Integer.toString(this.f4471a[8].intValue()));
        this.rl_10.setBackgroundResource(0);
        this.iv10.setVisibility(View.VISIBLE);
        this.rl_10.setVisibility(View.VISIBLE);
        this.tv10.setVisibility(View.VISIBLE);
        this.tv10.setText(Integer.toString(this.f4471a[9].intValue()));
    }


    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    private void disableAll() {
        this.rl_1.setEnabled(false);
        this.rl_2.setEnabled(false);
        this.rl_3.setEnabled(false);
        this.rl_4.setEnabled(false);
        this.rl_5.setEnabled(false);
        this.rl_6.setEnabled(false);
        this.rl_7.setEnabled(false);
        this.rl_8.setEnabled(false);
        this.rl_9.setEnabled(false);
        this.rl_10.setEnabled(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                SequenceGameActivity.this.enableAll();
            }
        }, 300);
    }


    public void enableAll() {
        this.rl_1.setEnabled(true);
        this.rl_2.setEnabled(true);
        this.rl_3.setEnabled(true);
        this.rl_4.setEnabled(true);
        this.rl_5.setEnabled(true);
        this.rl_6.setEnabled(true);
        this.rl_7.setEnabled(true);
        this.rl_8.setEnabled(true);
        this.rl_9.setEnabled(true);
        this.rl_10.setEnabled(true);
    }


    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    private void restartActivity() {
        if (!this.homeButtonClicked) {
            this.e.StopMp();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void speakWords(String str) {
        int identifier = getBaseContext().getResources().getIdentifier(str, "raw", getBaseContext().getPackageName());
        if (identifier != 0) {
            this.e.StopMp();
            this.e.playSound(identifier);
        }
    }

    public float getFloatvalue() {
        TypedValue typedValue = new TypedValue();
        this.f4473c.getValue(R.dimen.speech_rate, typedValue, true);
        return typedValue.getFloat();
    }

    public void onBackPressed() {
        this.e.StopMp();
        this.homeButtonClicked = true;
        finishActivity();
    }

    public void onClick(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        this.rl = relativeLayout;
        this.iv = (ImageView) relativeLayout.getChildAt(0);
        this.tv = (TextView) this.rl.getChildAt(1);
        disableAll();
        if (Integer.parseInt(this.tv.getText().toString()) == 1) {
            if (!this.pressedOne) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_1);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedOne = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 2) {
            if (this.pressedOne) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_2);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedTwo = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 3) {
            if (this.pressedTwo) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_3);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedThree = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 4) {
            if (this.pressedThree) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_4);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedFour = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 5) {
            if (this.pressedFour) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_5);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedFive = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 6) {
            if (this.pressedFive) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_6);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedSix = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 7) {
            if (this.pressedSix) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_7);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedSeven = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 8) {
            if (this.pressedSeven) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_8);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedEight = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) == 9) {
            if (this.pressedEight) {
                this.e.StopMp();
                this.e.playSound(R.raw.baloon_blast);
                this.e.playSound(R.raw.n_9);
                this.iv.setVisibility(View.INVISIBLE);
                this.tv.setVisibility(View.INVISIBLE);
                this.rl.setBackgroundResource(R.drawable.baloon_blast);
                this.rl.postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                    }
                }, 100);
                this.pressedNne = true;
            } else {
                this.e.playSound(R.raw.wrong);
                this.rl.startAnimation(this.animShake);
            }
        }
        if (Integer.parseInt(this.tv.getText().toString()) != 10) {
            return;
        }
        if (this.pressedNne) {
            this.e.StopMp();
            this.e.playSound(R.raw.baloon_blast);
            this.e.playSound(R.raw.n_10);
            this.iv.setVisibility(View.INVISIBLE);
            this.tv.setVisibility(View.INVISIBLE);
            this.rl.setBackgroundResource(R.drawable.baloon_blast);
            this.rl.postDelayed(new Runnable() {
                public void run() {
                    SequenceGameActivity.this.rl.setVisibility(View.INVISIBLE);
                }
            }, 100);
            startWellDone();
            return;
        }
        this.e.playSound(R.raw.wrong);
        this.rl.startAnimation(this.animShake);
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
        this.homeButtonClicked = false;
        this.length = 0;
        setContentView(R.layout.activity_sequence_game);
        this.e = new MyMediaPlayer(this);
        speakWords("tap_numbers");
        this.f4473c = getResources();
        this.f4472b = getFloatvalue();
        this.font = Typeface.createFromAsset(getAssets(), "arlrdbd.ttf");
        this.f4471a = storeRandomNumbers(10);
        this.myAdView = new MyAdView(this);

        setAd();
    }

    public void onDestroy() {
        super.onDestroy();
        this.e.StopMp();
    }


    public void onPause() {
        super.onPause();
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }


    public void onStart() {
        super.onStart();
        TextView textView = (TextView) findViewById(R.id.tvTextNumber);
        this.tvInfo = textView;
        textView.setTypeface(this.font);
        ImageView imageView = (ImageView) findViewById(R.id.home1);
        this.home = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SequenceGameActivity.this.e.StopMp();
                SequenceGameActivity.this.playClickSound();
                boolean unused = SequenceGameActivity.this.homeButtonClicked = true;
                SequenceGameActivity.this.clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        SequenceGameActivity.this.finishActivity();
                    }
                }, 500);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_first);
        this.rl_1 = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rl_2);
        this.rl_2 = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) findViewById(R.id.rl_3);
        this.rl_3 = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        RelativeLayout relativeLayout4 = (RelativeLayout) findViewById(R.id.rl_4);
        this.rl_4 = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        RelativeLayout relativeLayout5 = (RelativeLayout) findViewById(R.id.rl_5);
        this.rl_5 = relativeLayout5;
        relativeLayout5.setOnClickListener(this);
        RelativeLayout relativeLayout6 = (RelativeLayout) findViewById(R.id.rl_6);
        this.rl_6 = relativeLayout6;
        relativeLayout6.setOnClickListener(this);
        RelativeLayout relativeLayout7 = (RelativeLayout) findViewById(R.id.rl_7);
        this.rl_7 = relativeLayout7;
        relativeLayout7.setOnClickListener(this);
        RelativeLayout relativeLayout8 = (RelativeLayout) findViewById(R.id.rl_8);
        this.rl_8 = relativeLayout8;
        relativeLayout8.setOnClickListener(this);
        RelativeLayout relativeLayout9 = (RelativeLayout) findViewById(R.id.rl_9);
        this.rl_9 = relativeLayout9;
        relativeLayout9.setOnClickListener(this);
        RelativeLayout relativeLayout10 = (RelativeLayout) findViewById(R.id.rl_10);
        this.rl_10 = relativeLayout10;
        relativeLayout10.setOnClickListener(this);
        this.iv1 = (ImageView) findViewById(R.id.iv1);
        this.iv2 = (ImageView) findViewById(R.id.iv2);
        this.iv3 = (ImageView) findViewById(R.id.iv3);
        this.iv4 = (ImageView) findViewById(R.id.iv4);
        this.iv5 = (ImageView) findViewById(R.id.iv5);
        this.iv6 = (ImageView) findViewById(R.id.iv6);
        this.iv7 = (ImageView) findViewById(R.id.iv7);
        this.iv8 = (ImageView) findViewById(R.id.iv8);
        this.iv9 = (ImageView) findViewById(R.id.iv9);
        this.iv10 = (ImageView) findViewById(R.id.iv10);
        TextView textView2 = (TextView) findViewById(R.id.tv1);
        this.tv1 = textView2;
        textView2.setTypeface(this.font);
        this.tv1.setText(Integer.toString(this.f4471a[0].intValue()));
        TextView textView3 = (TextView) findViewById(R.id.tv2);
        this.tv2 = textView3;
        textView3.setTypeface(this.font);
        this.tv2.setText(Integer.toString(this.f4471a[1].intValue()));
        TextView textView4 = (TextView) findViewById(R.id.tv3);
        this.tv3 = textView4;
        textView4.setTypeface(this.font);
        this.tv3.setText(Integer.toString(this.f4471a[2].intValue()));
        TextView textView5 = (TextView) findViewById(R.id.tv4);
        this.tv4 = textView5;
        textView5.setTypeface(this.font);
        this.tv4.setText(Integer.toString(this.f4471a[3].intValue()));
        TextView textView6 = (TextView) findViewById(R.id.tv5);
        this.tv5 = textView6;
        textView6.setTypeface(this.font);
        this.tv5.setText(Integer.toString(this.f4471a[4].intValue()));
        TextView textView7 = (TextView) findViewById(R.id.tv6);
        this.tv6 = textView7;
        textView7.setTypeface(this.font);
        this.tv6.setText(Integer.toString(this.f4471a[5].intValue()));
        TextView textView8 = (TextView) findViewById(R.id.tv7);
        this.tv7 = textView8;
        textView8.setTypeface(this.font);
        this.tv7.setText(Integer.toString(this.f4471a[6].intValue()));
        TextView textView9 = (TextView) findViewById(R.id.tv8);
        this.tv8 = textView9;
        textView9.setTypeface(this.font);
        this.tv8.setText(Integer.toString(this.f4471a[7].intValue()));
        TextView textView10 = (TextView) findViewById(R.id.tv9);
        this.tv9 = textView10;
        textView10.setTypeface(this.font);
        this.tv9.setText(Integer.toString(this.f4471a[8].intValue()));
        TextView textView11 = (TextView) findViewById(R.id.tv10);
        this.tv10 = textView11;
        textView11.setTypeface(this.font);
        this.tv10.setText(Integer.toString(this.f4471a[9].intValue()));
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.animShake = loadAnimation;
        loadAnimation.setDuration(320);
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

    public void startWellDone() {
        this.e.playSound(R.raw.game_end);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                SequenceGameActivity.this.addGroups();
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
