package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.view.MotionEventCompat;

import com.counting.kidsgame.ImageClassABC;
import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.media.SoundManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberDragMatch extends Activity {
    private static final String IMAGEVIEW_TAG1 = "The Android Logo1";
    private static final String IMAGEVIEW_TAG2 = "The Android Logo2";
    private static final String IMAGEVIEW_TAG3 = "The Android Logo3";
    private static final String IMAGEVIEW_TAG4 = "The Android Logo4";
    public static final int TYPE_ALPHA = 2;


    ImageClassABC f4358a;
    private Animation animation;
    private Animation animation2;


    ImageClassABC f4359b;

    public RelativeLayout bottomlinear;
    private ImageView btnHome;


    ImageClassABC f4360c;


    ImageClassABC f4361d;

    public boolean dragEnded = false;
    private ImageView dust;
    ImageClassABC e;
    ImageClassABC f;
    ImageClassABC g;
    ImageClassABC h;

    public boolean homeButtonClicked = false;

    public int i = 0;
    public List<ImageClassABC> imageClassListABC;

    public ImageView imageQuiz;
    private Intent intent;
    private final int item_count = 4;
    ImageClassABC j;
    ImageClassABC k;
    ImageClassABC l;
    ImageClassABC m;
    private String mLang;

    public MyMediaPlayer mediaPlayer;
    private MyAdView myAdView;

    public ImageView myImage1;

    public ImageView myImage2;

    public ImageView myImage3;

    public ImageView myImage4;
    ImageClassABC n;
    private int[] num;
    ImageClassABC o;
    ImageClassABC p;
    ImageClassABC q;
    ImageClassABC r;

    public Integer randomCorrect;
    private Integer randomCorrect1;
    private Integer randomCorrect2;
    private Integer randomCorrect3;
    private Integer randomCorrect4;
    ImageClassABC s;
    ImageClassABC t;

    public LinearLayout toplinear1;

    public LinearLayout toplinear2;

    public LinearLayout toplinear3;

    public LinearLayout toplinear4;
    private TranslateAnimation translate2;
    ImageClassABC u;
    protected LinearLayout v;

    public View view;
    SharedPreference w;

    private final class MyClickListener implements View.OnTouchListener {
        private MyClickListener() {
        }

        public boolean onTouch(final View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction() & 255;
            int pointerId = motionEvent.getPointerId((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            if (action != 0) {
                if (action == 1) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                        }
                    }, 100);
                } else if (action != 2) {
                    return false;
                }
            } else if (pointerId == 0) {
                System.out.println("ACTION_DOWN");
                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                view.startDrag(new ClipData(view.getTag().toString(), new String[]{"text/plain"}, item), new View.DragShadowBuilder(view), view, 0);
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
                NumberDragMatch.this.playObjectSound(((Integer) view.getTag()).intValue());
            }
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            View unused = NumberDragMatch.this.view = (View) dragEvent.getLocalState();
            int action = dragEvent.getAction();
            if (action != 1) {
                if (action == 3) {
                    System.out.println("Drag 1");
                    if (NumberDragMatch.this.view != null) {
                        System.out.println("Drag 2");
                        if (view == NumberDragMatch.this.findViewById(R.id.bottomlinear)) {
                            System.out.println("Drag 3");
                            if (NumberDragMatch.this.view.getTag().equals(NumberDragMatch.this.randomCorrect)) {
                                System.out.println("Drag 4");
                                NumberDragMatch.this.mediaPlayer.playSound(R.raw.drag_right);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        NumberDragMatch.removeFromParent(NumberDragMatch.this.view);
                                    }
                                });
                                NumberDragMatch numberDragMatch = NumberDragMatch.this;

                                if (NumberDragMatch.this.i == 3) {
                                    NumberDragMatch.this.mediaPlayer.playSound(R.raw.clap);
                                    NumberDragMatch.this.setTrainEndAnimation();
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                        public void run() {
                                            NumberDragMatch.this.addGroups();
                                        }
                                    }, 2000);
                                }
                                NumberDragMatch.d(NumberDragMatch.this);
                                NumberDragMatch.this.refreshRandomNo();
                                NumberDragMatch.this.setQuizImage();
                            } else {
                                NumberDragMatch.this.view.setVisibility(View.VISIBLE);
                                NumberDragMatch.this.view.invalidate();
                                NumberDragMatch.this.mediaPlayer.playSound(R.raw.drag_wrong);
                            }
                        } else {
                            System.out.println("Drag 7");
                            NumberDragMatch.this.view.setVisibility(View.VISIBLE);
                            NumberDragMatch.this.view.invalidate();
                        }
                    }
                } else if (action != 4) {
                    if (action == 5) {
                        System.out.println("Drag entered");
                    }
                }
                System.out.println("Drag 8");
                if (NumberDragMatch.this.dropEventNotHandled(dragEvent)) {
                    System.out.println("Drag 9");
                    NumberDragMatch.this.visibleAllView();
                }
            } else {
                System.out.println("Drag started");
                boolean unused2 = NumberDragMatch.this.dragEnded = false;
            }
            return true;
        }
    }


    public void addGroups() {
        if (!this.homeButtonClicked) {
            this.i = 0;
            setRandomNo();
            refreshRandomNo();
            setQuizImage();
            trainComeAnimation();
            this.bottomlinear.setVisibility(View.VISIBLE);
            this.v.addView(this.toplinear1);
            this.v.addView(this.toplinear2);
            this.v.addView(this.toplinear3);
            this.v.addView(this.toplinear4);
            topLinearSetInVisible();
            setRandomAnimalImages();
        }
    }


    public void clickBounceAnim(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view2.startAnimation(loadAnimation);
    }

    private void createList() {
        if (this.imageClassListABC == null) {
            this.imageClassListABC = new ArrayList();
        }
        createAlphaSet();
        addAlphaSet();
    }

    static  int d(NumberDragMatch numberDragMatch) {
        int i2 = numberDragMatch.i;
        numberDragMatch.i = i2 + 1;
        return i2;
    }


    public boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
    }


    public void finishActivity() {
        this.mediaPlayer.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    private String getRandomApplause() {
        int nextInt = new Random().nextInt(4) + 1;
        if (nextInt == 1) {
            return "applause_excellent";
        }
        if (nextInt == 2) {
            return "applause_greatjob";
        }
        if (nextInt == 3) {
            return "applause_intelligent";
        }
        if (nextInt != 4) {
            return nextInt != 5 ? "applause_excellent" : "applause_youdid";
        }
        return "applause_terrific";
    }


    public void refreshRandomNo() {
        int i2 = this.i;
        if (i2 == 0) {
            this.randomCorrect = this.randomCorrect1;
        } else if (i2 == 1) {
            this.randomCorrect = this.randomCorrect2;
        } else if (i2 == 2) {
            this.randomCorrect = this.randomCorrect3;
        } else if (i2 == 3) {
            this.randomCorrect = this.randomCorrect4;
        }
    }


    public static void removeFromParent(View view2) {
        ViewParent parent;
        if (view2 != null && (parent = view2.getParent()) != null) {
            ((ViewGroup) parent).removeView(view2);
        }
    }

    private void restartActivity() {
        if (!this.homeButtonClicked) {
            this.mediaPlayer.StopMp();
            Intent intent2 = getIntent();
            finish();
            startActivity(intent2);
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


    public void setQuizImage() {
        this.imageQuiz.setImageResource(this.imageClassListABC.get(this.randomCorrect.intValue()).getImageResourceForId());
    }

    private void setRandomAnimalImages() {
        this.toplinear1.setTag(Integer.valueOf(this.num[0]));
        this.toplinear2.setTag(Integer.valueOf(this.num[1]));
        this.toplinear3.setTag(Integer.valueOf(this.num[2]));
        this.toplinear4.setTag(Integer.valueOf(this.num[3]));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(8, getResources().getColor(R.color.black));
        gradientDrawable.setColor(getResources().getColor(this.imageClassListABC.get(this.num[0]).getImageBackgroundColor()));
        this.toplinear1.setBackground(gradientDrawable);
        this.myImage1.setImageResource(this.imageClassListABC.get(this.num[0]).getImageResourceForId());
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setStroke(8, getResources().getColor(R.color.black));
        gradientDrawable2.setColor(getResources().getColor(this.imageClassListABC.get(this.num[1]).getImageBackgroundColor()));
        this.toplinear2.setBackground(gradientDrawable2);
        this.myImage2.setImageResource(this.imageClassListABC.get(this.num[1]).getImageResourceForId());
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setStroke(8, getResources().getColor(R.color.black));
        gradientDrawable3.setColor(getResources().getColor(this.imageClassListABC.get(this.num[2]).getImageBackgroundColor()));
        this.toplinear3.setBackground(gradientDrawable3);
        this.myImage3.setImageResource(this.imageClassListABC.get(this.num[2]).getImageResourceForId());
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setStroke(8, getResources().getColor(R.color.black));
        gradientDrawable4.setColor(getResources().getColor(this.imageClassListABC.get(this.num[3]).getImageBackgroundColor()));
        this.toplinear4.setBackground(gradientDrawable4);
        this.myImage4.setImageResource(this.imageClassListABC.get(this.num[3]).getImageResourceForId());
    }

    private void setRandomNo() {
        ArrayList<Integer> randomNonRepeatingIntegers = getRandomNonRepeatingIntegers(this.imageClassListABC.size(), 0, this.imageClassListABC.size() - 1);
        this.randomCorrect1 = randomNonRepeatingIntegers.get(0);
        this.randomCorrect2 = randomNonRepeatingIntegers.get(1);
        this.randomCorrect3 = randomNonRepeatingIntegers.get(2);
        this.randomCorrect4 = randomNonRepeatingIntegers.get(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.randomCorrect1);
        arrayList.add(this.randomCorrect2);
        arrayList.add(this.randomCorrect3);
        arrayList.add(this.randomCorrect4);
        Collections.shuffle(arrayList);
        this.num = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.num[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
    }


    public void setTrainEndAnimation() {
        this.bottomlinear.setClickable(false);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -2000.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberDragMatch.this.bottomlinear.setVisibility(View.INVISIBLE);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.bottomlinear.startAnimation(translateAnimation);
    }


    private void topLinearSetInVisible() {
        this.toplinear1.setVisibility(View.INVISIBLE);
        this.toplinear2.setVisibility(View.INVISIBLE);
        this.toplinear3.setVisibility(View.INVISIBLE);
        this.toplinear4.setVisibility(View.INVISIBLE);
    }


    public void topLinearSetVisible() {
        this.toplinear1.setVisibility(View.VISIBLE);
        this.toplinear2.setVisibility(View.VISIBLE);
        this.toplinear3.setVisibility(View.VISIBLE);
        this.toplinear4.setVisibility(View.VISIBLE);
        this.mediaPlayer.playSound(R.raw.dragmatch);
    }

    private void trainComeAnimation() {
        this.bottomlinear.setClickable(false);
        this.mediaPlayer.StopMp();
        this.mediaPlayer.playSound(R.raw.train_anim);
        TranslateAnimation translateAnimation = new TranslateAnimation(2000.0f, 0.0f, 0.0f, 0.0f);
        this.translate2 = translateAnimation;
        translateAnimation.setDuration(2000);
        this.translate2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberDragMatch.this.bottomlinear.setClickable(true);
                NumberDragMatch.this.topLinearSetVisible();
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
        this.bottomlinear.startAnimation(this.translate2);
    }


    public void visibleAllView() {
        new Handler().post(new Runnable() {
            public void run() {
                NumberDragMatch.this.toplinear1.setVisibility(View.VISIBLE);
                NumberDragMatch.this.toplinear2.setVisibility(View.VISIBLE);
                NumberDragMatch.this.toplinear3.setVisibility(View.VISIBLE);
                NumberDragMatch.this.toplinear4.setVisibility(View.VISIBLE);
                NumberDragMatch.this.myImage1.setVisibility(View.VISIBLE);
                NumberDragMatch.this.myImage2.setVisibility(View.VISIBLE);
                NumberDragMatch.this.myImage3.setVisibility(View.VISIBLE);
                NumberDragMatch.this.myImage4.setVisibility(View.VISIBLE);
            }
        });
    }

    public void addAlphaSet() {
        removeListItemABC();
        this.imageClassListABC.add(this.f4358a);
        this.imageClassListABC.add(this.f4359b);
        this.imageClassListABC.add(this.f4360c);
        this.imageClassListABC.add(this.f4361d);
        this.imageClassListABC.add(this.e);
        this.imageClassListABC.add(this.f);
        this.imageClassListABC.add(this.g);
        this.imageClassListABC.add(this.h);
        this.imageClassListABC.add(this.j);
        this.imageClassListABC.add(this.k);
        this.imageClassListABC.add(this.l);
        this.imageClassListABC.add(this.m);
        this.imageClassListABC.add(this.n);
        this.imageClassListABC.add(this.o);
        this.imageClassListABC.add(this.p);
        this.imageClassListABC.add(this.q);
        this.imageClassListABC.add(this.r);
        this.imageClassListABC.add(this.s);
        this.imageClassListABC.add(this.t);
        this.imageClassListABC.add(this.u);
    }

    public void createAlphaSet() {
        this.f4358a = new ImageClassABC(R.drawable.a1, R.drawable.b1, R.drawable.b1, R.drawable.apple, true, R.raw.apple, true, R.raw.n_1, R.string.alpha_a, R.color.wColor, 10, -10, 0, 2);
        this.f4359b = new ImageClassABC(R.drawable.a2, R.drawable.b2, R.drawable.b2, R.drawable.ball, true, R.raw.ball, true, R.raw.n_2, R.string.alpha_b, R.color.bColor, 10, -10, 0, 2);
        this.f4360c = new ImageClassABC(R.drawable.a3, R.drawable.b3, R.drawable.b3, R.drawable.balloon, true, R.raw.balloon, true, R.raw.n_3, R.string.alpha_c, R.color.cColor, 10, -10, 0, 2);
        this.f4361d = new ImageClassABC(R.drawable.a4, R.drawable.b4, R.drawable.b4, R.drawable.banana, true, R.raw.banana, true, R.raw.n_4, R.string.alpha_d, R.color.dColor, 10, -10, 0, 2);
        this.e = new ImageClassABC(R.drawable.a5, R.drawable.b5, R.drawable.b5, R.drawable.block, true, R.raw.blocks, true, R.raw.n_5, R.string.alpha_e, R.color.mColor, 10, -10, 0, 2);
        this.f = new ImageClassABC(R.drawable.a6, R.drawable.b6, R.drawable.b6, R.drawable.butterfly, true, R.raw.butterfly, true, R.raw.n_6, R.string.alpha_f, R.color.bColor, 10, -10, 0, 2);
        this.g = new ImageClassABC(R.drawable.a7, R.drawable.b7, R.drawable.b7, R.drawable.car, true, R.raw.strawberry, true, R.raw.n_7, R.string.alpha_g, R.color.gColor, 10, -10, 0, 2);
        this.h = new ImageClassABC(R.drawable.a8, R.drawable.b8, R.drawable.b8, R.drawable.clock, true, R.raw.clock, true, R.raw.n_8, R.string.alpha_h, R.color.iColor, 10, -10, 0, 2);
        this.j = new ImageClassABC(R.drawable.a9, R.drawable.b9, R.drawable.b9, R.drawable.duck, true, R.raw.duck, true, R.raw.n_9, R.string.alpha_i, R.color.dColor, 10, -10, 0, 2);
        this.k = new ImageClassABC(R.drawable.a10, R.drawable.b10, R.drawable.b10, R.drawable.egg, true, R.raw.egg, true, R.raw.n_10, R.string.alpha_j, R.color.wColor, 10, -10, 0, 2);
        this.l = new ImageClassABC(R.drawable.a11, R.drawable.b11, R.drawable.b11, R.drawable.fish, true, R.raw.fish, true, R.raw.n_11, R.string.alpha_k, R.color.jColor, 10, -10, 0, 2);
        this.m = new ImageClassABC(R.drawable.a12, R.drawable.b12, R.drawable.b12, R.drawable.flower, true, R.raw.flower, true, R.raw.n_12, R.string.alpha_l, R.color.iColor, 10, -10, 0, 2);
        this.n = new ImageClassABC(R.drawable.a13, R.drawable.b13, R.drawable.b13, R.drawable.ice_cream, true, R.raw.ice_cream, true, R.raw.n_13, R.string.alpha_m, R.color.mColor, 10, -10, 0, 2);
        this.o = new ImageClassABC(R.drawable.a14, R.drawable.b14, R.drawable.b14, R.drawable.muffin, true, R.raw.muffin, true, R.raw.n_14, R.string.alpha_n, R.color.dColor, 10, -10, 0, 2);
        this.p = new ImageClassABC(R.drawable.a15, R.drawable.b15, R.drawable.b15, R.drawable.orange, true, R.raw.orange, true, R.raw.n_15, R.string.alpha_o, R.color.wColor, 10, -10, 0, 2);
        this.q = new ImageClassABC(R.drawable.a16, R.drawable.b16, R.drawable.b16, R.drawable.pencil, true, R.raw.pencil, true, R.raw.n_16, R.string.alpha_p, R.color.pColor, 10, -10, 0, 2);
        this.r = new ImageClassABC(R.drawable.a17, R.drawable.b17, R.drawable.b17, R.drawable.pig, true, R.raw.pig, true, R.raw.n_17, R.string.alpha_q, R.color.oColor, 10, -10, 0, 2);
        this.s = new ImageClassABC(R.drawable.a18, R.drawable.b18, R.drawable.b18, R.drawable.pizza, true, R.raw.pizza, true, R.raw.n_18, R.string.alpha_r, R.color.iColor, 10, -10, 0, 2);
        this.t = new ImageClassABC(R.drawable.a19, R.drawable.b19, R.drawable.b19, R.drawable.star, true, R.raw.star, true, R.raw.n_19, R.string.alpha_s, R.color.hColor, 10, -10, 0, 2);
        this.u = new ImageClassABC(R.drawable.a20, R.drawable.b20, R.drawable.b20, R.drawable.teddy, true, R.raw.teddy_bear, true, R.raw.n_20, R.string.alpha_t, R.color.gColor, 10, -10, 0, 2);
    }

    public int getRandomInt(int i2, int i3) {
        return new Random().nextInt((i3 - i2) + 1) + i2;
    }

    public ArrayList<Integer> getRandomNonRepeatingIntegers(int i2, int i3, int i4) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (arrayList.size() < i2 - 1) {
            int randomInt = getRandomInt(i3, i4);
            if (!arrayList.contains(Integer.valueOf(randomInt))) {
                arrayList.add(Integer.valueOf(randomInt));
            }
        }
        return arrayList;
    }

    public void onBackPressed() {
        this.mediaPlayer.StopMp();
        this.homeButtonClicked = true;
        finishActivity();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.number_drag_match);
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
        createList();
        this.homeButtonClicked = false;
        if (this.w == null) {
            this.w = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
        this.mediaPlayer = new MyMediaPlayer(this);
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();
        setRandomNo();
        refreshRandomNo();
        this.v = (LinearLayout) findViewById(R.id.toplinearparent);
        this.toplinear1 = (LinearLayout) findViewById(R.id.toplinear1);
        this.toplinear2 = (LinearLayout) findViewById(R.id.toplinear2);
        this.toplinear3 = (LinearLayout) findViewById(R.id.toplinear3);
        this.toplinear4 = (LinearLayout) findViewById(R.id.toplinear4);
        this.myImage1 = (ImageView) findViewById(R.id.image1);
        this.myImage2 = (ImageView) findViewById(R.id.image2);
        this.myImage3 = (ImageView) findViewById(R.id.image3);
        this.myImage4 = (ImageView) findViewById(R.id.image4);
        this.bottomlinear = (RelativeLayout) findViewById(R.id.bottomlinear);
        this.imageQuiz = (ImageView) findViewById(R.id.imageQuiz);
        setQuizImage();
        setRandomAnimalImages();
        this.toplinear1.setOnTouchListener(new MyClickListener());
        this.toplinear1.setOnDragListener(new MyDragListener());
        this.toplinear2.setOnTouchListener(new MyClickListener());
        this.toplinear2.setOnDragListener(new MyDragListener());
        this.toplinear3.setOnTouchListener(new MyClickListener());
        this.toplinear4.setOnDragListener(new MyDragListener());
        this.toplinear4.setOnTouchListener(new MyClickListener());
        this.toplinear4.setOnDragListener(new MyDragListener());
        this.bottomlinear.setOnDragListener(new MyDragListener());
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("showInterstitial animal.....");
                NumberDragMatch.this.clickBounceAnim(view);
                NumberDragMatch.this.mediaPlayer.StopMp();
                boolean unused = NumberDragMatch.this.homeButtonClicked = true;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberDragMatch.this.finishActivity();
                    }
                }, 500);
            }
        });
        topLinearSetInVisible();
        trainComeAnimation();
        this.myAdView = new MyAdView(this);

        setAd();
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void playClickSound() {
        this.mediaPlayer.StopMp();
        this.mediaPlayer.playSound(R.raw.click);
    }

    public void playObjectSound(int i2) {
        this.mediaPlayer.StopMp();
        getResources().getString(R.string.choose);
        this.mediaPlayer.StopMp();
        this.mediaPlayer.playSound(this.imageClassListABC.get(i2).getImageSoundId());
    }

    public void removeListItemABC() {
        List<ImageClassABC> list = this.imageClassListABC;
        list.removeAll(list);
    }
}
