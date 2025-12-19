package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberSameDragMatch extends Activity {
    private static final String IMAGEVIEW_TAG1 = "The Android Logo1";
    private static final String IMAGEVIEW_TAG2 = "The Android Logo2";
    private static final String IMAGEVIEW_TAG3 = "The Android Logo3";
    private static final String IMAGEVIEW_TAG4 = "The Android Logo4";
    public static final int TYPE_ALPHA = 2;
    public static List<ImageClassABC> imageClassListABC;


    ImageClassABC f4422a;
    private Animation animation;
    private Animation animation2;


    ImageClassABC f4423b;
    private ImageView btnHome;


    ImageClassABC f4424c;


    ImageClassABC f4425d;

    public boolean dragEnded = false;
    private ImageView dust;
    ImageClassABC e;
    ImageClassABC f;
    ImageClassABC g;
    ImageClassABC h;

    public boolean homeButtonClicked = false;

    public int i = 0;
    private LinearLayout imageParent;
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
    private int[] numBottom;
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
            NumberSameDragMatch numberSameDragMatch = NumberSameDragMatch.this;
            if (!numberSameDragMatch.w) {
                numberSameDragMatch.w = true;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberSameDragMatch.this.w = false;
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
                    NumberSameDragMatch.this.playObjectSound(((Integer) view.getTag()).intValue());
                }
            }
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            View unused = NumberSameDragMatch.this.view = (View) dragEvent.getLocalState();
            int action = dragEvent.getAction();
            if (action == 1) {
                System.out.println("Drag started");
                boolean unused2 = NumberSameDragMatch.this.dragEnded = false;
                return true;
            } else if (action == 3) {
                PrintStream printStream = System.out;
                printStream.println("Drag drop:" + NumberSameDragMatch.this.view);
                int unused3 = NumberSameDragMatch.this.j = 0;
                if (NumberSameDragMatch.this.view == null) {
                    return false;
                }
                System.out.println("Drag drop if");
                if (view == NumberSameDragMatch.this.findViewById(R.id.image4) || view == NumberSameDragMatch.this.findViewById(R.id.image5) || view == NumberSameDragMatch.this.findViewById(R.id.image6)) {
                    if (NumberSameDragMatch.this.view.getTag().equals(view.getTag())) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                NumberSameDragMatch.removeFromParent(NumberSameDragMatch.this.view);
                            }
                        });
                        if (view == NumberSameDragMatch.this.findViewById(R.id.image4)) {
                            System.out.println("Drag drop test 4");
                            NumberSameDragMatch.this.myImage4.setVisibility(View.INVISIBLE);
                            NumberSameDragMatch.this.inside_image4.setImageResource(R.drawable.box_redopen);
                            NumberSameDragMatch numberSameDragMatch = NumberSameDragMatch.this;
                            numberSameDragMatch.startOneShotParticle(numberSameDragMatch.inside_image4);
                            NumberSameDragMatch.u(NumberSameDragMatch.this);
                        } else if (view == NumberSameDragMatch.this.findViewById(R.id.image5)) {
                            NumberSameDragMatch.this.myImage5.setVisibility(View.INVISIBLE);
                            NumberSameDragMatch.this.inside_image5.setImageResource(R.drawable.box_blueopen);
                            NumberSameDragMatch numberSameDragMatch2 = NumberSameDragMatch.this;
                            numberSameDragMatch2.startOneShotParticle(numberSameDragMatch2.inside_image5);
                            NumberSameDragMatch.u(NumberSameDragMatch.this);
                        } else if (view == NumberSameDragMatch.this.findViewById(R.id.image6)) {
                            NumberSameDragMatch.this.myImage6.setVisibility(View.INVISIBLE);
                            NumberSameDragMatch.this.inside_image6.setImageResource(R.drawable.box_greenopen);
                            NumberSameDragMatch numberSameDragMatch3 = NumberSameDragMatch.this;
                            numberSameDragMatch3.startOneShotParticle(numberSameDragMatch3.inside_image6);
                            NumberSameDragMatch.u(NumberSameDragMatch.this);
                        }
                        NumberSameDragMatch.this.mediaPlayer.speakApplause();
                        NumberSameDragMatch.this.mediaPlayer.playSound(R.raw.drag_right);
                        if (NumberSameDragMatch.this.i == NumberSameDragMatch.this.item_count - 1) {
                            NumberSameDragMatch.this.mediaPlayer.playSound(R.raw.clap);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                public void run() {
                                    NumberSameDragMatch.this.addGroups();
                                }
                            }, 2000);
                        }
                        if (NumberSameDragMatch.this.j == 1) {
                            NumberSameDragMatch.i(NumberSameDragMatch.this);
                        }
                    } else {
                        NumberSameDragMatch.this.view.setVisibility(View.VISIBLE);
                        NumberSameDragMatch.this.view.invalidate();
                        NumberSameDragMatch.this.mediaPlayer.playSound(R.raw.drag_wrong);
                    }
                }
                return true;
            } else if (action == 4) {
                System.out.println("Drag ended");
                if (NumberSameDragMatch.this.view != null) {
                    NumberSameDragMatch.this.view.post(new Runnable() {
                        public void run() {
                            NumberSameDragMatch.this.visibleAllView();
                            if (NumberSameDragMatch.this.view != null) {
                                NumberSameDragMatch.this.view.setVisibility(View.VISIBLE);
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
                return true;
            }
        }
    }


    public void addGroups() {
        try {
            this.mediaPlayer.playSound(R.raw.matchingpair);
            this.i = 0;
            setRandomNo();
            this.imageParent.addView(this.myImage1);
            this.imageParent.addView(this.myImage2);
            this.imageParent.addView(this.myImage3);
            this.inside_image4.setImageResource(R.drawable.box_red);
            this.inside_image5.setImageResource(R.drawable.box_blue);
            this.inside_image6.setImageResource(R.drawable.box_green);
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
        this.mediaPlayer.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    static int i(NumberSameDragMatch numberSameDragMatch) {
        int i2 = numberSameDragMatch.i;
        numberSameDragMatch.i = i2 + 1;
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
            Picasso.get().load(imageClassListABC.get(this.numBottom[0]).getImageResourceId()).into(this.myImage4);
            Picasso.get().load(imageClassListABC.get(this.numBottom[1]).getImageResourceId()).into(this.myImage5);
            Picasso.get().load(imageClassListABC.get(this.numBottom[2]).getImageResourceId()).into(this.myImage6);
        } catch (Exception unused) {
            this.myImage4.setImageResource(imageClassListABC.get(this.numBottom[0]).getImageResourceId());
            this.myImage5.setImageResource(imageClassListABC.get(this.numBottom[1]).getImageResourceId());
            this.myImage6.setImageResource(imageClassListABC.get(this.numBottom[2]).getImageResourceId());
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
        Collections.shuffle(arrayList);
        this.numBottom = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            this.numBottom[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
    }



    public void startOneShotParticle(ImageView imageView) {
    }

    static int u(NumberSameDragMatch numberSameDragMatch) {
        int i2 = numberSameDragMatch.j;
        numberSameDragMatch.j = i2 + 1;
        return i2;
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

    public void addAlphaSet() {
        removeListItemABC();
        imageClassListABC.add(this.f4422a);
        imageClassListABC.add(this.f4423b);
        imageClassListABC.add(this.f4424c);
        imageClassListABC.add(this.f4425d);
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

        this.f4422a = new ImageClassABC(R.drawable.a1, R.drawable.b1, R.drawable.b1, R.drawable.obj1, true, R.raw.n_1, true, R.raw.n_1, R.string.alpha_a, R.color.wColor, 10, -10, 0, 2);
        this.f4423b = new ImageClassABC(R.drawable.a2, R.drawable.b2, R.drawable.b2, R.drawable.obj2, true, R.raw.n_2, true, R.raw.n_2, R.string.alpha_b, R.color.bColor, 10, -10, 0, 2);
        this.f4424c = new ImageClassABC(R.drawable.a3, R.drawable.b3, R.drawable.b3, R.drawable.obj3, true, R.raw.n_3, true, R.raw.n_3, R.string.alpha_c, R.color.cColor, 10, -10, 0, 2);
        this.f4425d = new ImageClassABC(R.drawable.a4, R.drawable.b4, R.drawable.b4, R.drawable.obj4, true, R.raw.n_4, true, R.raw.n_4, R.string.alpha_d, R.color.dColor, 10, -10, 0, 2);
        this.e = new ImageClassABC(R.drawable.a5, R.drawable.b5, R.drawable.b5, R.drawable.obj5, true, R.raw.n_5, true, R.raw.n_5, R.string.alpha_e, R.color.mColor, 10, -10, 0, 2);
        this.f = new ImageClassABC(R.drawable.a6, R.drawable.b6, R.drawable.b6, R.drawable.obj6, true, R.raw.n_6, true, R.raw.n_6, R.string.alpha_f, R.color.bColor, 10, -10, 0, 2);
        this.g = new ImageClassABC(R.drawable.a7, R.drawable.b7, R.drawable.b7, R.drawable.obj7, true, R.raw.n_7, true, R.raw.n_7, R.string.alpha_g, R.color.gColor, 10, -10, 0, 2);
        this.h = new ImageClassABC(R.drawable.a8, R.drawable.b8, R.drawable.b8, R.drawable.obj8, true, R.raw.n_8, true, R.raw.n_8, R.string.alpha_h, R.color.iColor, 10, -10, 0, 2);
        this.k = new ImageClassABC(R.drawable.a9, R.drawable.b9, R.drawable.b9, R.drawable.obj9, true, R.raw.n_9, true, R.raw.n_9, R.string.alpha_i, R.color.dColor, 10, -10, 0, 2);
        this.l = new ImageClassABC(R.drawable.a10, R.drawable.b10, R.drawable.b10, R.drawable.obj10, true, R.raw.n_10, true, R.raw.n_10, R.string.alpha_j, R.color.wColor, 10, -10, 0, 2);
        this.m = new ImageClassABC(R.drawable.a11, R.drawable.b11, R.drawable.b11, R.drawable.obj11, true, R.raw.n_11, true, R.raw.n_11, R.string.alpha_k, R.color.jColor, 10, -10, 0, 2);
        this.n = new ImageClassABC(R.drawable.a12, R.drawable.b12, R.drawable.b12, R.drawable.obj12, true, R.raw.n_12, true, R.raw.n_12, R.string.alpha_l, R.color.iColor, 10, -10, 0, 2);
        this.o = new ImageClassABC(R.drawable.a13, R.drawable.b13, R.drawable.b13, R.drawable.obj13, true, R.raw.n_13, true, R.raw.n_13, R.string.alpha_m, R.color.mColor, 10, -10, 0, 2);
        this.p = new ImageClassABC(R.drawable.a14, R.drawable.b14, R.drawable.b14, R.drawable.obj14, true, R.raw.n_14, true, R.raw.n_14, R.string.alpha_n, R.color.dColor, 10, -10, 0, 2);
        this.q = new ImageClassABC(R.drawable.a15, R.drawable.b15, R.drawable.b15, R.drawable.obj15, true, R.raw.n_15, true, R.raw.n_15, R.string.alpha_o, R.color.wColor, 10, -10, 0, 2);
        this.r = new ImageClassABC(R.drawable.a16, R.drawable.b16, R.drawable.b16, R.drawable.obj16, true, R.raw.n_16, true, R.raw.n_16, R.string.alpha_p, R.color.pColor, 10, -10, 0, 2);
        this.s = new ImageClassABC(R.drawable.a17, R.drawable.b17, R.drawable.b17, R.drawable.obj17, true, R.raw.n_17, true, R.raw.n_17, R.string.alpha_q, R.color.oColor, 10, -10, 0, 2);
        this.t = new ImageClassABC(R.drawable.a18, R.drawable.b18, R.drawable.b18, R.drawable.obj18, true, R.raw.n_18, true, R.raw.n_18, R.string.alpha_r, R.color.iColor, 10, -10, 0, 2);
        this.u = new ImageClassABC(R.drawable.a19, R.drawable.b19, R.drawable.b19, R.drawable.obj19, true, R.raw.n_19, true, R.raw.n_19, R.string.alpha_s, R.color.hColor, 10, -10, 0, 2);
        this.v = new ImageClassABC(R.drawable.a20, R.drawable.b20, R.drawable.b20, R.drawable.obj20, true, R.raw.n_20, true, R.raw.n_20, R.string.alpha_t, R.color.gColor, 10, -10, 0, 2);
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.number_same_drag_match);
        createAlphaSet();
        if (imageClassListABC == null) {
            imageClassListABC = new ArrayList();
        }
        addAlphaSet();
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.mediaPlayer = myMediaPlayer;
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
        System.out.println("callig times");
        this.homeButtonClicked = false;
        setRandomNo();
        this.imageParent = (LinearLayout) findViewById(R.id.imageParent);
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
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("showInterstitial animal.....");
                NumberSameDragMatch.this.mediaPlayer.StopMp();
                boolean unused = NumberSameDragMatch.this.homeButtonClicked = true;
                NumberSameDragMatch.this.playClickSound();
                NumberSameDragMatch.this.clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberSameDragMatch.this.finishActivity();
                    }
                }, 500);
            }
        });
        setRandomAnimalImages();
        setRandomAnimalBottomImages();
        this.myAdView = new MyAdView(this);

        setAd();
    }


    public void onDestroy() {
        super.onDestroy();
        this.mediaPlayer.StopMp();
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
