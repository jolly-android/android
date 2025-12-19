package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;

import com.counting.kidsgame.ImageClassABC;
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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberSortingActivity extends Activity implements View.OnClickListener {
    private static final String IMAGEVIEW_TAG1 = "The Android Logo1";
    private static final String IMAGEVIEW_TAG2 = "The Android Logo2";
    private static final String IMAGEVIEW_TAG3 = "The Android Logo3";
    private static final String IMAGEVIEW_TAG4 = "The Android Logo4";
    public static final String TAG = "NumberSortingActivity";
    public static final int TYPE_ALPHA = 2;
    public static List<ImageClassABC> imageClassListABC;


    ImageClassABC f4442a;
    private Animation animation;
    private Animation animation2;


    ImageClassABC f4443b;
    private ImageView btnHome;


    ImageClassABC f4444c;


    ImageClassABC f4445d;

    public boolean dragEnded = false;
    private ImageView dust;
    ImageClassABC e;
    ImageClassABC f;
    ImageClassABC g;
    ImageClassABC h;

    public boolean homeButtonClicked = false;

    public int i = 0;
    private ConstraintLayout imageParent;
    private ImageView imageQuiz;

    public ImageView inside_image4;

    public ImageView inside_image5;

    public ImageView inside_image6;
    private Intent intent;

    public int item_count = 3;

    public int j = 0;
    ImageClassABC k;
    ImageClassABC l;
    ImageClassABC m;
    private String mLang;

    public MyMediaPlayer mediaPlayer;
    private MyAdView myAdView;
    private ImageView myImage1;
    private ImageView myImage2;
    private ImageView myImage3;

    public ImageView myImage4;

    public ImageView myImage5;

    public ImageView myImage6;
    ImageClassABC n;
    private int[] num;

    public int[] numBottom;
    ImageClassABC o;
    ImageClassABC p;
    ImageClassABC q;
    ImageClassABC r;
    private Integer randomCorrect1;
    private Integer randomCorrect2;
    private Integer randomCorrect3;
    ImageClassABC s;
    ImageClassABC t;
    private TranslateAnimation translate2;
    private TextView txt_ins;
    ImageClassABC u;
    ImageClassABC v;

    public View view;
    boolean w;

    private final class MyClickListener implements View.OnTouchListener {
        private MyClickListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction() & 255;
            int pointerId = motionEvent.getPointerId((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            NumberSortingActivity numberSortingActivity = NumberSortingActivity.this;
            if (!numberSortingActivity.w) {
                numberSortingActivity.w = true;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberSortingActivity.this.w = false;
                    }
                }, 500);
                if (action != 0) {
                    if (action == 1) {
                        view.setVisibility(View.VISIBLE);
                        view.clearAnimation();
                    } else if (action != 2) {
                        return false;
                    }
                } else if (pointerId == 0) {
                    System.out.println("ACTION_DOWN");
                    PrintStream printStream = System.out;
                    printStream.println("tag ACTION_DOWN::" + view.getTag());
                    ClipData.Item item = new ClipData.Item(view.getTag().toString());
                    view.startDrag(new ClipData(view.getTag().toString(), new String[]{"text/plain"}, item), new View.DragShadowBuilder(view), view, 0);
                    view.clearAnimation();
                    view.setVisibility(View.INVISIBLE);
                    NumberSortingActivity.this.playObjectSound(((Integer) view.getTag()).intValue());
                }
            }
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            View unused = NumberSortingActivity.this.view = (View) dragEvent.getLocalState();
            int action = dragEvent.getAction();
            if (action == 1) {
                System.out.println("Drag started");
                boolean unused2 = NumberSortingActivity.this.dragEnded = false;
                return true;
            } else if (action == 3) {
                PrintStream printStream = System.out;
                printStream.println("Drag drop:" + NumberSortingActivity.this.view);
                int unused3 = NumberSortingActivity.this.j = 0;
                if (NumberSortingActivity.this.view == null) {
                    return false;
                }
                System.out.println("Drag drop if");
                if (view == NumberSortingActivity.this.findViewById(R.id.image4) || view == NumberSortingActivity.this.findViewById(R.id.image5) || view == NumberSortingActivity.this.findViewById(R.id.image6)) {
                    if (NumberSortingActivity.this.view.getTag().equals(view.getTag())) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                NumberSortingActivity.removeFromParent(NumberSortingActivity.this.view);
                            }
                        });
                        if (view == NumberSortingActivity.this.findViewById(R.id.image4)) {
                            System.out.println("Drag drop test 4");
                            try {
                                Picasso.get().load(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[0]).getImageResourceId()).into(NumberSortingActivity.this.myImage4);
                            } catch (Exception unused4) {
                                NumberSortingActivity.this.myImage4.setImageResource(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[0]).getImageResourceId());
                            }
                            NumberSortingActivity numberSortingActivity = NumberSortingActivity.this;
                            numberSortingActivity.startOneShotParticle(numberSortingActivity.inside_image4);
                            NumberSortingActivity.w(NumberSortingActivity.this);
                        } else if (view == NumberSortingActivity.this.findViewById(R.id.image5)) {
                            try {
                                Picasso.get().load(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[1]).getImageResourceId()).into(NumberSortingActivity.this.myImage5);
                            } catch (Exception unused5) {
                                NumberSortingActivity.this.myImage5.setImageResource(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[1]).getImageResourceId());
                            }
                            NumberSortingActivity numberSortingActivity2 = NumberSortingActivity.this;
                            numberSortingActivity2.startOneShotParticle(numberSortingActivity2.inside_image5);
                            NumberSortingActivity.w(NumberSortingActivity.this);
                        } else if (view == NumberSortingActivity.this.findViewById(R.id.image6)) {
                            try {
                                Picasso.get().load(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[2]).getImageResourceId()).into(NumberSortingActivity.this.myImage6);
                            } catch (Exception unused6) {
                                NumberSortingActivity.this.myImage6.setImageResource(NumberSortingActivity.imageClassListABC.get(NumberSortingActivity.this.numBottom[2]).getImageResourceId());
                            }
                            NumberSortingActivity numberSortingActivity3 = NumberSortingActivity.this;
                            numberSortingActivity3.startOneShotParticle(numberSortingActivity3.inside_image6);
                            NumberSortingActivity.w(NumberSortingActivity.this);
                        }
                        NumberSortingActivity.this.mediaPlayer.speakApplause();
                        NumberSortingActivity.this.mediaPlayer.playSound(R.raw.drag_right);
                        if (NumberSortingActivity.this.i == NumberSortingActivity.this.item_count - 1) {
                            NumberSortingActivity.this.mediaPlayer.playSound(R.raw.clap);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                public void run() {
                                    NumberSortingActivity.this.addGroups();
                                }
                            }, 2000);
                        }
                        if (NumberSortingActivity.this.j == 1) {
                            NumberSortingActivity.k(NumberSortingActivity.this);
                        }
                    } else {
                        NumberSortingActivity.this.view.setVisibility(View.VISIBLE);
                        NumberSortingActivity.this.view.invalidate();
                        NumberSortingActivity.this.mediaPlayer.playSound(R.raw.drag_wrong);
                    }
                }
                return true;
            } else if (action == 4) {
                System.out.println("Drag ended");
                if (NumberSortingActivity.this.view != null) {
                    NumberSortingActivity.this.view.post(new Runnable() {
                        public void run() {
                            NumberSortingActivity.this.visibleAllView();
                            if (NumberSortingActivity.this.view != null) {
                                NumberSortingActivity.this.view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                return true;
            } else if (action == 5) {
                System.out.println("Drag entered");
                return true;
            } else if (action != 6) {
                return false;
            } else {
                System.out.println("Drag exited");
                NumberSortingActivity.this.visibleAllView();
                return true;
            }
        }
    }


    public void addGroups() {
        try {
            this.mediaPlayer.playSound(R.raw.complete_the_pattern);
            this.i = 0;
            setRandomNo();
            this.imageParent.addView(this.myImage1);
            this.imageParent.addView(this.myImage2);
            this.imageParent.addView(this.myImage3);
            this.inside_image4.setImageResource(R.drawable.animals_3);
            this.inside_image5.setImageResource(R.drawable.animals_2);
            this.inside_image6.setImageResource(R.drawable.animals_1);
            visibleAllView();
            visibleBottomViews();
            setRandomAnimalImages();
            setRandomAnimalBottomImages();
        } catch (Exception unused) {
            finishActivity();
        }
    }


    public void clickBounceAnim(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view2.startAnimation(loadAnimation);
    }


    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    static int k(NumberSortingActivity numberSortingActivity) {
        int i2 = numberSortingActivity.i;
        numberSortingActivity.i = i2 + 1;
        return i2;
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
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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

    private void setRandomAnimalBottomImages() {
        this.myImage4.setTag(Integer.valueOf(this.numBottom[0]));
        this.myImage5.setTag(Integer.valueOf(this.numBottom[1]));
        this.myImage6.setTag(Integer.valueOf(this.numBottom[2]));
        try {
            this.myImage4.setImageResource(0);
            this.myImage5.setImageResource(0);
            this.myImage6.setImageResource(0);
            this.myImage4.setImageResource(17170445);
            this.myImage5.setImageResource(17170445);
            this.myImage6.setImageResource(17170445);
        } catch (Exception unused) {
        }
    }

    private void setRandomAnimalImages() {
        this.myImage1.setTag(Integer.valueOf(this.num[0]));
        this.myImage2.setTag(Integer.valueOf(this.num[1]));
        this.myImage3.setTag(Integer.valueOf(this.num[2]));
        try {
            Picasso.get().load(imageClassListABC.get(this.num[0]).getSmalletterId()).into(this.myImage1);
            Picasso.get().load(imageClassListABC.get(this.num[1]).getSmalletterId()).into(this.myImage2);
            Picasso.get().load(imageClassListABC.get(this.num[2]).getSmalletterId()).into(this.myImage3);
        } catch (Exception unused) {
            this.myImage1.setImageResource(imageClassListABC.get(this.num[0]).getSmalletterId());
            this.myImage2.setImageResource(imageClassListABC.get(this.num[1]).getSmalletterId());
            this.myImage3.setImageResource(imageClassListABC.get(this.num[2]).getSmalletterId());
        }
    }

    private void setRandomNo() {
        ArrayList<Integer> randomNonRepeatingIntegers = getRandomNonRepeatingIntegers(imageClassListABC.size(), 0, imageClassListABC.size() - 1);
        this.randomCorrect1 = randomNonRepeatingIntegers.get(0);
        this.randomCorrect2 = randomNonRepeatingIntegers.get(1);
        this.randomCorrect3 = randomNonRepeatingIntegers.get(2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.randomCorrect1);
        arrayList.add(this.randomCorrect2);
        arrayList.add(this.randomCorrect3);
        Collections.shuffle(arrayList);
        this.num = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.num[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        Collections.sort(arrayList);
        this.numBottom = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.numBottom[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
    }


    public void startOneShotParticle(ImageView imageView) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.25f, 0.45f).oneShot(imageView, 20);
    }


    public void visibleAllView() {
        this.myImage1.setVisibility(View.VISIBLE);
        this.myImage2.setVisibility(View.VISIBLE);
        this.myImage3.setVisibility(View.VISIBLE);
    }

    private void visibleBottomViews() {
        this.myImage4.setVisibility(View.VISIBLE);
        this.myImage5.setVisibility(View.VISIBLE);
        this.myImage6.setVisibility(View.VISIBLE);
    }

    static int w(NumberSortingActivity numberSortingActivity) {
        int i2 = numberSortingActivity.j;
        numberSortingActivity.j = i2 + 1;
        return i2;
    }

    public void addAlphaSet() {
        removeListItemABC();
        imageClassListABC.add(this.f4442a);
        imageClassListABC.add(this.f4443b);
        imageClassListABC.add(this.f4444c);
        imageClassListABC.add(this.f4445d);
        imageClassListABC.add(this.e);
        imageClassListABC.add(this.f);
        imageClassListABC.add(this.g);
        imageClassListABC.add(this.h);
        imageClassListABC.add(this.k);
        imageClassListABC.add(this.l);
        imageClassListABC.add(this.m);
        imageClassListABC.add(this.n);
        imageClassListABC.add(this.o);
        imageClassListABC.add(this.p);
        imageClassListABC.add(this.q);
        imageClassListABC.add(this.r);
        imageClassListABC.add(this.s);
        imageClassListABC.add(this.t);
        imageClassListABC.add(this.u);
        imageClassListABC.add(this.v);
    }

    public void createAlphaSet() {

        this.f4442a = new ImageClassABC(R.drawable.b1, R.drawable.b1, R.drawable.b1, R.drawable.obj1, true, R.raw.n_1, true, R.raw.n_1, R.string.alpha_a, R.color.wColor, 10, -10, 0, 2);
        this.f4443b = new ImageClassABC(R.drawable.b2, R.drawable.b2, R.drawable.b2, R.drawable.obj2, true, R.raw.n_2, true, R.raw.n_2, R.string.alpha_b, R.color.bColor, 10, -10, 0, 2);
        this.f4444c = new ImageClassABC(R.drawable.b3, R.drawable.b3, R.drawable.b3, R.drawable.obj3, true, R.raw.n_3, true, R.raw.n_3, R.string.alpha_c, R.color.cColor, 10, -10, 0, 2);
        this.f4445d = new ImageClassABC(R.drawable.b4, R.drawable.b4, R.drawable.b4, R.drawable.obj4, true, R.raw.n_4, true, R.raw.n_4, R.string.alpha_d, R.color.dColor, 10, -10, 0, 2);
        this.e = new ImageClassABC(R.drawable.b5, R.drawable.b5, R.drawable.b5, R.drawable.obj5, true, R.raw.n_5, true, R.raw.n_5, R.string.alpha_e, R.color.mColor, 10, -10, 0, 2);
        this.f = new ImageClassABC(R.drawable.b6, R.drawable.b6, R.drawable.b6, R.drawable.obj6, true, R.raw.n_6, true, R.raw.n_6, R.string.alpha_f, R.color.bColor, 10, -10, 0, 2);
        this.g = new ImageClassABC(R.drawable.b7, R.drawable.b7, R.drawable.b7, R.drawable.obj7, true, R.raw.n_7, true, R.raw.n_7, R.string.alpha_g, R.color.gColor, 10, -10, 0, 2);
        this.h = new ImageClassABC(R.drawable.b8, R.drawable.b8, R.drawable.b8, R.drawable.obj8, true, R.raw.n_8, true, R.raw.n_8, R.string.alpha_h, R.color.iColor, 10, -10, 0, 2);
        this.k = new ImageClassABC(R.drawable.b9, R.drawable.b9, R.drawable.b9, R.drawable.obj9, true, R.raw.n_9, true, R.raw.n_9, R.string.alpha_i, R.color.dColor, 10, -10, 0, 2);
        this.l = new ImageClassABC(R.drawable.b10, R.drawable.b10, R.drawable.b10, R.drawable.obj10, true, R.raw.n_10, true, R.raw.n_10, R.string.alpha_j, R.color.wColor, 10, -10, 0, 2);
        this.m = new ImageClassABC(R.drawable.b11, R.drawable.b11, R.drawable.b11, R.drawable.obj11, true, R.raw.n_11, true, R.raw.n_11, R.string.alpha_k, R.color.jColor, 10, -10, 0, 2);
        this.n = new ImageClassABC(R.drawable.b12, R.drawable.b12, R.drawable.b12, R.drawable.obj12, true, R.raw.n_12, true, R.raw.n_12, R.string.alpha_l, R.color.iColor, 10, -10, 0, 2);
        this.o = new ImageClassABC(R.drawable.b13, R.drawable.b13, R.drawable.b13, R.drawable.obj13, true, R.raw.n_13, true, R.raw.n_13, R.string.alpha_m, R.color.mColor, 10, -10, 0, 2);
        this.p = new ImageClassABC(R.drawable.b14, R.drawable.b14, R.drawable.b14, R.drawable.obj14, true, R.raw.n_14, true, R.raw.n_14, R.string.alpha_n, R.color.dColor, 10, -10, 0, 2);
        this.q = new ImageClassABC(R.drawable.b15, R.drawable.b15, R.drawable.b15, R.drawable.obj15, true, R.raw.n_15, true, R.raw.n_15, R.string.alpha_o, R.color.wColor, 10, -10, 0, 2);
        this.r = new ImageClassABC(R.drawable.b16, R.drawable.b16, R.drawable.b16, R.drawable.obj16, true, R.raw.n_16, true, R.raw.n_16, R.string.alpha_p, R.color.pColor, 10, -10, 0, 2);
        this.s = new ImageClassABC(R.drawable.b17, R.drawable.b17, R.drawable.b17, R.drawable.obj17, true, R.raw.n_17, true, R.raw.n_17, R.string.alpha_q, R.color.oColor, 10, -10, 0, 2);
        this.t = new ImageClassABC(R.drawable.b18, R.drawable.b18, R.drawable.b18, R.drawable.obj18, true, R.raw.n_18, true, R.raw.n_18, R.string.alpha_r, R.color.iColor, 10, -10, 0, 2);
        this.u = new ImageClassABC(R.drawable.b19, R.drawable.b19, R.drawable.b19, R.drawable.obj19, true, R.raw.n_19, true, R.raw.n_19, R.string.alpha_s, R.color.hColor, 10, -10, 0, 2);
        this.v = new ImageClassABC(R.drawable.b20, R.drawable.b20, R.drawable.b20, R.drawable.obj20, true, R.raw.n_20, true, R.raw.n_20, R.string.alpha_t, R.color.gColor, 10, -10, 0, 2);
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
        finishActivity();
    }

    public void onClick(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.egg_shake);
        switch (view2.getId()) {
            case R.id.inside_image4 /*2131296731*/:
                this.inside_image4.startAnimation(loadAnimation);
                this.mediaPlayer.playSound(R.raw.rabbit);
                return;
            case R.id.inside_image5 /*2131296732*/:
                this.inside_image5.startAnimation(loadAnimation);
                this.mediaPlayer.playSound(R.raw.panda);
                return;
            case R.id.inside_image6 /*2131296733*/:
                this.inside_image6.startAnimation(loadAnimation);
                this.mediaPlayer.playSound(R.raw.bull1);
                return;
            default:
                return;
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.number_sorting_lay_new);
        createAlphaSet();
        if (imageClassListABC == null) {
            imageClassListABC = new ArrayList();
        }
        addAlphaSet();
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.mediaPlayer = myMediaPlayer;
        myMediaPlayer.playSound(R.raw.complete_the_pattern);
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
        System.out.println("callig times");
        this.homeButtonClicked = false;
        setRandomNo();
        this.imageParent = (ConstraintLayout) findViewById(R.id.imageParent);
        this.txt_ins = (TextView) findViewById(R.id.txt_ins);
        this.txt_ins.setTypeface(Typeface.createFromAsset(getAssets(), "arlrdbd.ttf"));
        this.myImage1 = (ImageView) findViewById(R.id.image1);
        this.myImage2 = (ImageView) findViewById(R.id.image2);
        this.myImage3 = (ImageView) findViewById(R.id.image3);
        this.myImage4 = (ImageView) findViewById(R.id.image4);
        this.myImage5 = (ImageView) findViewById(R.id.image5);
        this.myImage6 = (ImageView) findViewById(R.id.image6);
        this.inside_image4 = (ImageView) findViewById(R.id.inside_image4);
        this.inside_image5 = (ImageView) findViewById(R.id.inside_image5);
        this.inside_image6 = (ImageView) findViewById(R.id.inside_image6);
        this.imageQuiz = (ImageView) findViewById(R.id.imageQuiz);
        this.myImage1.setOnTouchListener(new MyClickListener());
        this.myImage2.setOnTouchListener(new MyClickListener());
        this.myImage3.setOnTouchListener(new MyClickListener());
        this.myImage4.setOnDragListener(new MyDragListener());
        this.myImage5.setOnDragListener(new MyDragListener());
        this.myImage6.setOnDragListener(new MyDragListener());
        this.inside_image4.setOnClickListener(this);
        this.inside_image5.setOnClickListener(this);
        this.inside_image6.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("showInterstitial animal.....");
                NumberSortingActivity.this.mediaPlayer.StopMp();
                boolean unused = NumberSortingActivity.this.homeButtonClicked = true;
                NumberSortingActivity.this.playClickSound();
                NumberSortingActivity.this.clickBounceAnim(view);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        NumberSortingActivity.this.finishActivity();
                    }
                }, 500);
            }
        });
        setRandomAnimalImages();
        setRandomAnimalBottomImages();
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
        this.mediaPlayer.playSound(imageClassListABC.get(i2).getImageNameSoundID());
    }

    public void removeListItemABC() {
        List<ImageClassABC> list = imageClassListABC;
        list.removeAll(list);
    }
}
