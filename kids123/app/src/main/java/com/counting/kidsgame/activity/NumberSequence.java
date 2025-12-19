package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;
import com.plattysoft.leonids.ParticleSystem;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberSequence extends Activity implements View.OnClickListener {

    
    boolean f4435a;
    private Animation anim;
    private ImageView bottom1;
    private ImageView bottom2;
    private ImageView bottom3;
    private ImageView bottom4;
    private ArrayList<Integer> bottomRanomArr;
    private ImageView btnHome;
    
    public ImageView clickedBottomImageview;
    
    public int clickedBottomImageviewSeq;
    private Animation clockwise;
    private Animation floatingg;
    
    public Animation floatingg1;
    
    public Animation floatingg2;
    
    public Animation floatingg3;
    
    public Animation floatingg4;
    private Intent i;
    private ImageView inside_imageview1;
    private ImageView inside_imageview2;
    private ImageView inside_imageview3;
    private ImageView inside_imageview4;
    private Intent intent;
    
    public MyMediaPlayer mediaPlayer;
    private MyAdView myAdView;
    private Animation myAnim;
    private int myRandomNo;
    private int randomNo;
    private ArrayList<Integer> randomNoArray;
    private Resources res;
    private ImageView selectedLayout;
    private int selectedNo;
    private String stringCompare;
    private ImageView top1;
    private ImageView top2;
    private ImageView top3;
    private ArrayList<Integer> topRandomArray;
    private Animation zoom;

    private void actionBottomButtonOnClick(ImageView imageView, ImageView imageView2, int i2) {
        if (imageView.getTag().equals(Integer.valueOf(this.selectedNo))) {
            startOneShotParticle(imageView);
            this.mediaPlayer.playSound(R.raw.bubble_pop);
            startOneShotParticle(this.selectedLayout);
            imageView.clearAnimation();
            imageView2.clearAnimation();
            imageView.setClickable(false);
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            this.selectedLayout.clearAnimation();
            this.selectedLayout.setImageResource(findTextFromCount(this.selectedNo));
            this.selectedLayout.startAnimation(this.clockwise);
            try {
                this.mediaPlayer.playSound(findSoundFromCount(this.selectedNo));
            } catch (Exception unused) {
            }
        } else {
            this.clickedBottomImageview = imageView;
            this.clickedBottomImageviewSeq = i2;
            this.mediaPlayer.playSound(R.raw.wrong);
            imageView.startAnimation(this.anim);
        }
    }

    
    public void addGroups() {
        if (!this.f4435a) {
            this.mediaPlayer.playSound(R.raw.complete_the_pattern);
        }
        visibleBottomImageView();
        startAnimation();
        this.bottomRanomArr = new ArrayList<>();
        this.topRandomArray = new ArrayList<>();
        this.res = getApplicationContext().getResources();
        int randomNo2 = getRandomNo(18);
        this.randomNo = randomNo2;
        addTopRandomNo(randomNo2, this.topRandomArray);
        this.randomNoArray = randomize(20);
        setTopLetters(this.randomNo);
        setBottomLetters(getBottomRandomNo(this.bottomRanomArr));
    }

    private void addTopRandomNo(int i2, ArrayList<Integer> arrayList) {
        arrayList.add(Integer.valueOf(i2));
        int i3 = i2 + 1;
        arrayList.add(Integer.valueOf(i3));
        int i4 = i2 + 2;
        arrayList.add(Integer.valueOf(i4));
        this.myRandomNo = getRandomNo(3);
        PrintStream printStream = System.out;
        printStream.println("myRandomNo::" + this.myRandomNo);
        int i5 = this.myRandomNo;
        if (i5 == 1) {
            this.selectedNo = i2;
        } else if (i5 == 2) {
            this.selectedNo = i3;
        } else if (i5 == 3) {
            this.selectedNo = i4;
        }
    }

    
    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    private int findSoundFromCount(int i2) {
        switch (i2) {
            case 1:
                return R.raw.n_1;
            case 2:
                return R.raw.n_2;
            case 3:
                return R.raw.n_3;
            case 4:
                return R.raw.n_4;
            case 5:
                return R.raw.n_5;
            case 6:
                return R.raw.n_6;
            case 7:
                return R.raw.n_7;
            case 8:
                return R.raw.n_8;
            case 9:
                return R.raw.n_9;
            case 10:
                return R.raw.n_10;
            case 11:
                return R.raw.n_11;
            case 12:
                return R.raw.n_12;
            case 13:
                return R.raw.n_13;
            case 14:
                return R.raw.n_14;
            case 15:
                return R.raw.n_15;
            case 16:
                return R.raw.n_16;
            case 17:
                return R.raw.n_17;
            case 18:
                return R.raw.n_18;
            case 19:
                return R.raw.n_19;
            case 20:
                return R.raw.n_20;
            default:
                return i2;
        }
    }

    private int findTextFromCount(int i2) {
        switch (i2) {
            case 1:
                return R.drawable.a1;
            case 2:
                return R.drawable.a2;
            case 3:
                return R.drawable.a3;
            case 4:
                return R.drawable.a4;
            case 5:
                return R.drawable.a5;
            case 6:
                return R.drawable.a6;
            case 7:
                return R.drawable.a7;
            case 8:
                return R.drawable.a8;
            case 9:
                return R.drawable.a9;
            case 10:
                return R.drawable.a10;
            case 11:
                return R.drawable.a11;
            case 12:
                return R.drawable.a12;
            case 13:
                return R.drawable.a13;
            case 14:
                return R.drawable.a14;
            case 15:
                return R.drawable.a15;
            case 16:
                return R.drawable.a16;
            case 17:
                return R.drawable.a17;
            case 18:
                return R.drawable.a18;
            case 19:
                return R.drawable.a19;
            case 20:
                return R.drawable.a20;
            default:
                return i2;
        }
    }

    
    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    private ArrayList<Integer> getBottomRandomNo(ArrayList<Integer> arrayList) {
        arrayList.add(Integer.valueOf(this.selectedNo));
        if (this.randomNoArray.get(0).equals(Integer.valueOf(this.selectedNo))) {
            arrayList.add(this.randomNoArray.get(5));
        } else {
            arrayList.add(this.randomNoArray.get(0));
        }
        if (this.randomNoArray.get(1).equals(Integer.valueOf(this.selectedNo))) {
            arrayList.add(this.randomNoArray.get(5));
        } else {
            arrayList.add(this.randomNoArray.get(1));
        }
        if (this.randomNoArray.get(2).equals(Integer.valueOf(this.selectedNo))) {
            arrayList.add(this.randomNoArray.get(5));
        } else {
            arrayList.add(this.randomNoArray.get(2));
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }

    private int getRandomNo(int i2) {
        return new Random().nextInt(i2) + 1;
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setBottomLetters(ArrayList<Integer> arrayList) {
        this.bottom1.setTag(arrayList.get(0));
        this.bottom2.setTag(arrayList.get(1));
        this.bottom3.setTag(arrayList.get(2));
        this.bottom4.setTag(arrayList.get(3));
        this.bottom1.setImageResource(findTextFromCount(arrayList.get(0).intValue()));
        this.bottom2.setImageResource(findTextFromCount(arrayList.get(1).intValue()));
        this.bottom3.setImageResource(findTextFromCount(arrayList.get(2).intValue()));
        this.bottom4.setImageResource(findTextFromCount(arrayList.get(3).intValue()));
    }

    private void setTopLetters(int i2) {
        int i3 = this.myRandomNo;
        if (i3 == 1) {
            this.top1.setImageResource(R.drawable.questionmark);
            this.top2.setImageResource(findTextFromCount(i2 + 1));
            this.top3.setImageResource(findTextFromCount(i2 + 2));
            this.top1.startAnimation(this.myAnim);
            this.selectedLayout = this.top1;
        } else if (i3 == 2) {
            this.top1.setImageResource(findTextFromCount(i2));
            this.top2.setImageResource(R.drawable.questionmark);
            this.top3.setImageResource(findTextFromCount(i2 + 2));
            this.top2.startAnimation(this.myAnim);
            this.selectedLayout = this.top2;
        } else if (i3 == 3) {
            this.top1.setImageResource(findTextFromCount(i2));
            this.top2.setImageResource(findTextFromCount(i2 + 1));
            this.top3.setImageResource(R.drawable.questionmark);
            this.top3.startAnimation(this.myAnim);
            this.selectedLayout = this.top3;
        }
    }

    private void startAnimation() {
        this.inside_imageview1.clearAnimation();
        this.inside_imageview2.clearAnimation();
        this.inside_imageview3.clearAnimation();
        this.inside_imageview4.clearAnimation();
        this.bottom1.clearAnimation();
        this.bottom2.clearAnimation();
        this.bottom3.clearAnimation();
        this.bottom4.clearAnimation();
        this.inside_imageview1.startAnimation(this.floatingg1);
        this.inside_imageview2.startAnimation(this.floatingg2);
        this.inside_imageview3.startAnimation(this.floatingg3);
        this.inside_imageview4.startAnimation(this.floatingg4);
        this.bottom1.startAnimation(this.floatingg1);
        this.bottom2.startAnimation(this.floatingg2);
        this.bottom3.startAnimation(this.floatingg3);
        this.bottom4.startAnimation(this.floatingg4);
    }

    private void startOneShotParticle(ImageView imageView) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.15f, 0.35f).oneShot(imageView, 20);
    }

    private void visibleBottomImageView() {
        this.bottom1.setVisibility(View.VISIBLE);
        this.inside_imageview1.setVisibility(View.VISIBLE);
        this.bottom2.setVisibility(View.VISIBLE);
        this.inside_imageview2.setVisibility(View.VISIBLE);
        this.bottom3.setVisibility(View.VISIBLE);
        this.inside_imageview3.setVisibility(View.VISIBLE);
        this.bottom4.setVisibility(View.VISIBLE);
        this.inside_imageview4.setVisibility(View.VISIBLE);
        this.bottom1.setClickable(true);
        this.bottom2.setClickable(true);
        this.bottom3.setClickable(true);
        this.bottom4.setClickable(true);
    }

    public void onBackPressed() {
        this.mediaPlayer.StopMp();
        finishActivity();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bottom1 /*2131296390*/:
                actionBottomButtonOnClick(this.bottom1, this.inside_imageview1, 1);
                return;
            case R.id.bottom2 /*2131296391*/:
                actionBottomButtonOnClick(this.bottom2, this.inside_imageview2, 2);
                return;
            case R.id.bottom3 /*2131296392*/:
                actionBottomButtonOnClick(this.bottom3, this.inside_imageview3, 3);
                return;
            case R.id.bottom4 /*2131296393*/:
                actionBottomButtonOnClick(this.bottom4, this.inside_imageview4, 4);
                return;
            default:
                switch (id) {
                    case R.id.top1 /*2131297037*/:
                        if (this.myRandomNo != 1) {
                            this.top1.startAnimation(this.zoom);
                            this.mediaPlayer.playSound(findSoundFromCount(this.randomNo));
                            return;
                        }
                        return;
                    case R.id.top2 /*2131297038*/:
                        if (this.myRandomNo != 2) {
                            this.top2.startAnimation(this.zoom);
                            this.mediaPlayer.playSound(findSoundFromCount(this.randomNo + 1));
                            return;
                        }
                        return;
                    case R.id.top3 /*2131297039*/:
                        if (this.myRandomNo != 3) {
                            this.top3.startAnimation(this.zoom);
                            this.mediaPlayer.playSound(findSoundFromCount(this.randomNo + 2));
                            return;
                        }
                        return;
                    default:
                        return;
                }
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.number_sequence);
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
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.mediaPlayer = myMediaPlayer;
        myMediaPlayer.playSound(R.raw.complete_the_pattern);
        this.bottomRanomArr = new ArrayList<>();
        this.topRandomArray = new ArrayList<>();
        this.res = getApplicationContext().getResources();
        int randomNo2 = getRandomNo(18);
        this.randomNo = randomNo2;
        addTopRandomNo(randomNo2, this.topRandomArray);
        this.randomNoArray = randomize(20);
        this.myAnim = AnimationUtils.loadAnimation(this, R.anim.zoomin_zoomout);
        this.top1 = (ImageView) findViewById(R.id.top1);
        this.top2 = (ImageView) findViewById(R.id.top2);
        this.top3 = (ImageView) findViewById(R.id.top3);
        this.top1.setOnClickListener(this);
        this.top2.setOnClickListener(this);
        this.top3.setOnClickListener(this);
        this.inside_imageview1 = (ImageView) findViewById(R.id.inside_imageview1);
        this.inside_imageview2 = (ImageView) findViewById(R.id.inside_imageview2);
        this.inside_imageview3 = (ImageView) findViewById(R.id.inside_imageview3);
        this.inside_imageview4 = (ImageView) findViewById(R.id.inside_imageview4);
        this.bottom1 = (ImageView) findViewById(R.id.bottom1);
        this.bottom2 = (ImageView) findViewById(R.id.bottom2);
        this.bottom3 = (ImageView) findViewById(R.id.bottom3);
        this.bottom4 = (ImageView) findViewById(R.id.bottom4);
        this.bottom1.setOnClickListener(this);
        this.bottom2.setOnClickListener(this);
        this.bottom3.setOnClickListener(this);
        this.bottom4.setOnClickListener(this);
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        this.clockwise = loadAnimation;
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        NumberSequence.this.addGroups();
                    }
                }, 2000);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        this.floatingg1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.floatingg);
        this.floatingg2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.floatingg2);
        this.floatingg3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.floatingg);
        this.floatingg4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.floatingg2);
        this.inside_imageview1.startAnimation(this.floatingg1);
        this.inside_imageview2.startAnimation(this.floatingg2);
        this.inside_imageview3.startAnimation(this.floatingg3);
        this.inside_imageview4.startAnimation(this.floatingg4);
        this.bottom1.startAnimation(this.floatingg1);
        this.bottom2.startAnimation(this.floatingg2);
        this.bottom3.startAnimation(this.floatingg3);
        this.bottom4.startAnimation(this.floatingg4);
        setTopLetters(this.randomNo);
        setBottomLetters(getBottomRandomNo(this.bottomRanomArr));
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.anim = loadAnimation2;
        loadAnimation2.setDuration(320);
        this.anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (NumberSequence.this.clickedBottomImageviewSeq == 1) {
                    NumberSequence.this.clickedBottomImageview.startAnimation(NumberSequence.this.floatingg1);
                } else if (NumberSequence.this.clickedBottomImageviewSeq == 2) {
                    NumberSequence.this.clickedBottomImageview.startAnimation(NumberSequence.this.floatingg2);
                } else if (NumberSequence.this.clickedBottomImageviewSeq == 3) {
                    NumberSequence.this.clickedBottomImageview.startAnimation(NumberSequence.this.floatingg3);
                } else if (NumberSequence.this.clickedBottomImageviewSeq == 4) {
                    NumberSequence.this.clickedBottomImageview.startAnimation(NumberSequence.this.floatingg4);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("showInterstitial animal.....");
                NumberSequence.this.mediaPlayer.StopMp();
                NumberSequence.this.playClickSound();
                NumberSequence.this.clickBounceAnim(view);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        NumberSequence.this.finishActivity();
                    }
                }, 500);
            }
        });
        this.myAdView = new MyAdView(this);
        
        setAd();
    }

    
    public void onDestroy() {
        super.onDestroy();
        this.mediaPlayer.StopMp();
    }

    
    public void onPause() {
        super.onPause();
        this.f4435a = true;
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
        this.f4435a = false;
    }

    public void playClickSound() {
        this.mediaPlayer.StopMp();
        this.mediaPlayer.playSound(R.raw.click);
    }

    public ArrayList<Integer> randomize(int i2) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < i2; i3++) {
            Integer valueOf = Integer.valueOf(random.nextInt(i2) + 1);
            if (!arrayList.contains(valueOf)) {
                arrayList.add(valueOf);
            }
        }
        return arrayList;
    }
}
