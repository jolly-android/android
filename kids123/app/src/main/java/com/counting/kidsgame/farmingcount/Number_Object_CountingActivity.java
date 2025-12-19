package com.counting.kidsgame.farmingcount;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Collections;

public class Number_Object_CountingActivity extends Activity implements View.OnClickListener {

    
    GridLayout f4688a;

    
    LinearLayout f4689b;

    
    int f4690c;

    
    int f4691d;
    int e = 0;
    ArrayList<ImageView> f = new ArrayList<>();
    ImageView g;
    ImageView h;
    
    public Handler handler = new Handler(Looper.getMainLooper());
    ImageView i;
    ImageView j;
    ImageView k;
    ImageView l;
    ImageView m;
    ImageView n;
    ImageView o;
    ImageView p;
    ConstraintLayout q;
    boolean r = false;
    MyMediaPlayer s;
    ArrayList<VegitablesModel> t;
    ArrayList<NumberModel> u;
    MyAdView v;
    
    public View view;
    boolean w;
    boolean x = false;
    int y = 0;

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, final DragEvent dragEvent) {
            if (dragEvent.getLocalState() != null) {
                View unused = Number_Object_CountingActivity.this.view = (View) dragEvent.getLocalState();
            }
            int action = dragEvent.getAction();
            if (action != 1) {
                if (action != 3) {
                    if (action != 4) {
                        if (action == 5 && Number_Object_CountingActivity.this.view != null) {
                            Log.d("ACTION_DRAG_ENTERED", Number_Object_CountingActivity.this.view.getTag() + " : " + view.getTag());
                        }
                    }
                } else if (Number_Object_CountingActivity.this.view != null) {
                    Log.d("DRAG_TEST1111", Number_Object_CountingActivity.this.view.getTag() + " : " + view.getTag());
                    if (Number_Object_CountingActivity.this.view.getTag().equals(view.getTag())) {
                        Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                        number_Object_CountingActivity.e++;
                        number_Object_CountingActivity.view.setVisibility(View.INVISIBLE);
                        view.setVisibility(View.VISIBLE);
                        Number_Object_CountingActivity.this.s.playSound(R.raw.drag_right);
                        Number_Object_CountingActivity.this.starAnimate(view);
                        Number_Object_CountingActivity.this.vegitablesPick((ImageView) view);
                        Number_Object_CountingActivity.this.vegitableCount();
                        Number_Object_CountingActivity number_Object_CountingActivity2 = Number_Object_CountingActivity.this;
                        number_Object_CountingActivity2.x = true;
                        number_Object_CountingActivity2.handler.postDelayed(new Runnable() {
                            public void run() {
                                Number_Object_CountingActivity.this.x = false;
                            }
                        }, 500);
                        Number_Object_CountingActivity number_Object_CountingActivity3 = Number_Object_CountingActivity.this;
                        if (number_Object_CountingActivity3.e == number_Object_CountingActivity3.f4691d) {
                            number_Object_CountingActivity3.s.speakApplause();
                            Number_Object_CountingActivity.this.handler.postDelayed(new Runnable() {
                                public void run() {
                                    Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                                    if (!number_Object_CountingActivity.r) {
                                        number_Object_CountingActivity.s.playSound(R.raw.tractor_anim);
                                    }
                                    Number_Object_CountingActivity number_Object_CountingActivity2 = Number_Object_CountingActivity.this;
                                    number_Object_CountingActivity2.tractorGone(number_Object_CountingActivity2.q);
                                }
                            }, 500);
                        }
                    } else {
                        Number_Object_CountingActivity.this.s.playSound(R.raw.drag_wrong);
                        Number_Object_CountingActivity.this.view.setVisibility(View.VISIBLE);
                    }
                }
                if (Number_Object_CountingActivity.this.view != null) {
                    Log.d("ACTION_DRAG_ENDED", Number_Object_CountingActivity.this.view.getTag() + " : " + view.getTag());
                    Number_Object_CountingActivity.this.view.post(new Runnable() {
                        public void run() {
                            if (Number_Object_CountingActivity.this.dropEventNotHandled(dragEvent)) {
                                Number_Object_CountingActivity.this.view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            } else if (Number_Object_CountingActivity.this.view != null) {
                Number_Object_CountingActivity.this.view.clearAnimation();
                Number_Object_CountingActivity.this.view.setVisibility(View.INVISIBLE);
                Log.d("ACTION_DRAG_STARTED", Number_Object_CountingActivity.this.view.getTag() + " : " + view.getTag());
            }
            return true;
        }
    }

    public class MyTouchListener implements View.OnTouchListener {
        public MyTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
            if (!number_Object_CountingActivity.x) {
                number_Object_CountingActivity.x = true;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        Number_Object_CountingActivity.this.x = false;
                    }
                }, 500);
                int action = motionEvent.getAction();
                if (action == 0) {
                    System.out.println("ACTION_DOWN");
                    ClipData.Item item = new ClipData.Item(view.getTag().toString());
                    view.startDrag(new ClipData(view.getTag().toString(), new String[]{"text/plain"}, item), new View.DragShadowBuilder(view), view, 0);
                    Number_Object_CountingActivity.this.p.clearAnimation();
                    Number_Object_CountingActivity.this.p.setVisibility(View.INVISIBLE);
                    Number_Object_CountingActivity.this.s.playSound(R.raw.click);
                } else if (action != 1) {
                    return false;
                } else {
                    view.setVisibility(View.VISIBLE);
                }
            }
            return false;
        }
    }

    private void animateClick(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view2.startAnimation(loadAnimation);
    }

    private void clickBounceAnim(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view2.startAnimation(loadAnimation);
    }

    
    public boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
    }

    
    public void gameRestart() {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                Number_Object_CountingActivity.this.setViewId();
                Number_Object_CountingActivity.this.startGame();
                Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                number_Object_CountingActivity.e = 0;
                number_Object_CountingActivity.l.setImageResource(0);
                Number_Object_CountingActivity.this.m.setImageResource(0);
                Number_Object_CountingActivity.this.n.setImageResource(0);
            }
        }, 2000);
    }

    
    public void handMove(final ImageView imageView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -700.0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatMode(-1);
        translateAnimation.setRepeatCount(-1);
        imageView.startAnimation(translateAnimation);
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adView);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.v.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    
    public void setViewId() {
        this.q = (ConstraintLayout) findViewById(R.id.tractorLayout);
        this.g = (ImageView) findViewById(R.id.tractor);
        this.h = (ImageView) findViewById(R.id.patch);
        this.i = (ImageView) findViewById(R.id.numberBoard);
        this.k = (ImageView) findViewById(R.id.boy);
        this.j = (ImageView) findViewById(R.id.homeBtn);
        this.l = (ImageView) findViewById(R.id.vegitable1);
        this.m = (ImageView) findViewById(R.id.vegitable2);
        this.n = (ImageView) findViewById(R.id.vegitable3);
        this.o = (ImageView) findViewById(R.id.container);
        this.p = (ImageView) findViewById(R.id.hintHand);
        this.f4688a = (GridLayout) findViewById(R.id.mygrid);
        this.f4689b = (LinearLayout) findViewById(R.id.grid_bg_lay);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.k.setClickable(false);
        ArrayList<VegitablesModel> arrayList = new ArrayList<>();
        this.t = arrayList;
        arrayList.clear();
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg1));
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg2));
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg3));
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg4));
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg5));
        this.t.add(new VegitablesModel(R.drawable.number_object_count_subg6));
        Collections.shuffle(this.t);
        ArrayList<NumberModel> arrayList2 = new ArrayList<>();
        this.u = arrayList2;
        arrayList2.clear();
        this.u.add(new NumberModel(R.drawable.a4, 4, R.raw.n_4));
        this.u.add(new NumberModel(R.drawable.a6, 6, R.raw.n_6));
        this.u.add(new NumberModel(R.drawable.a8, 8, R.raw.n_8));
        this.u.add(new NumberModel(R.drawable.a10, 10, R.raw.n_10));
        this.u.add(new NumberModel(R.drawable.a12, 12, R.raw.n_12));
        this.u.add(new NumberModel(R.drawable.a14, 14, R.raw.n_14));
        this.u.add(new NumberModel(R.drawable.a16, 16, R.raw.n_16));
        Collections.shuffle(this.u);
        this.i.setImageResource(this.u.get(0).getImg());
        this.f4691d = 8;
        this.f4691d = this.u.get(0).getNumber();
        this.l.setImageResource(0);
        this.m.setImageResource(0);
        this.n.setImageResource(0);
        this.q.setVisibility(View.INVISIBLE);
        this.p.setVisibility(View.INVISIBLE);
        this.v = new MyAdView(this);
        
        setAd();
    }

    
    public void starAnimate(View view2) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.15f, 0.5f).oneShot(view2, 10);
    }

    
    public void startGame() {
        this.f4688a.removeAllViews();
        this.f.clear();
        int i2 = this.f4691d / 2;
        int i3 = i2 * 2;
        this.f4688a.setAlignmentMode(0);
        this.f4688a.setColumnCount(i2);
        this.f4688a.setRowCount(2);
        int screenHeight = DisplayManager.getScreenHeight(this);
        this.f4690c = screenHeight;
        float f2 = (float) (screenHeight - (screenHeight / 5));
        new LinearLayout.LayoutParams(Math.round(f2), Math.round(f2)).weight = 1.0f;
        this.f4688a.removeAllViews();
        int i4 = 500;
        for (int i5 = 0; i5 < i3; i5++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setGravity(17);
            float f3 = f2 / 4.0f;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(f3), Math.round(f3));
            layoutParams.weight = 1.0f;
            linearLayout.setLayoutParams(layoutParams);
            final ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Math.round(f3), Math.round(f3));
            layoutParams2.weight = 1.0f;
            imageView.setLayoutParams(layoutParams2);
            linearLayout.addView(imageView);
            this.f.add(imageView);
            imageView.setImageResource(this.t.get(0).getVeg());
            imageView.setOnTouchListener(new MyTouchListener());
            this.o.setOnDragListener(new MyDragListener());
            imageView.setTag(1);
            this.o.setTag(1);
            imageView.setVisibility(View.INVISIBLE);
            i4 += 300;
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    imageView.setVisibility(View.VISIBLE);
                    Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                    if (!number_Object_CountingActivity.r) {
                        number_Object_CountingActivity.s.playSound(R.raw.wordpop);
                    }
                    Animation loadAnimation = AnimationUtils.loadAnimation(Number_Object_CountingActivity.this, R.anim.veg_grow);
                    loadAnimation.setDuration(500);
                    imageView.startAnimation(loadAnimation);
                }
            }, (long) i4);
            this.f4688a.addView(linearLayout);
        }
        this.handler.postDelayed(new Runnable() {
            public void run() {
                Number_Object_CountingActivity.this.q.setVisibility(View.VISIBLE);
                Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                number_Object_CountingActivity.tractorCome(number_Object_CountingActivity.q);
                Number_Object_CountingActivity number_Object_CountingActivity2 = Number_Object_CountingActivity.this;
                if (!number_Object_CountingActivity2.r) {
                    number_Object_CountingActivity2.s.playSound(R.raw.tractor_anim);
                }
            }
        }, (long) (this.f.size() * 300));
    }

    
    public void tractorCome(ConstraintLayout constraintLayout) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2500.0f, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        constraintLayout.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                if (!number_Object_CountingActivity.w) {
                    number_Object_CountingActivity.p.setVisibility(View.VISIBLE);
                    Number_Object_CountingActivity number_Object_CountingActivity2 = Number_Object_CountingActivity.this;
                    number_Object_CountingActivity2.handMove(number_Object_CountingActivity2.p);
                }
                Number_Object_CountingActivity.this.k.setClickable(true);
                Number_Object_CountingActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        Number_Object_CountingActivity number_Object_CountingActivity = Number_Object_CountingActivity.this;
                        if (!number_Object_CountingActivity.r) {
                            number_Object_CountingActivity.s.playSound(R.raw.lets_count);
                        }
                    }
                }, (long) (Number_Object_CountingActivity.this.f.size() * 200));
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }

    
    public void tractorGone(ConstraintLayout constraintLayout) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -2000.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        constraintLayout.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Number_Object_CountingActivity.this.gameRestart();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                Number_Object_CountingActivity.this.k.setClickable(false);
                Number_Object_CountingActivity.this.w = true;
            }
        });
    }

    
    public void vegitableCount() {
        switch (this.e) {
            case 1:
                this.s.playSound(R.raw.n_1);
                return;
            case 2:
                this.s.playSound(R.raw.n_2);
                return;
            case 3:
                this.s.playSound(R.raw.n_3);
                return;
            case 4:
                this.s.playSound(R.raw.n_4);
                return;
            case 5:
                this.s.playSound(R.raw.n_5);
                return;
            case 6:
                this.s.playSound(R.raw.n_6);
                return;
            case 7:
                this.s.playSound(R.raw.n_7);
                return;
            case 8:
                this.s.playSound(R.raw.n_8);
                return;
            case 9:
                this.s.playSound(R.raw.n_9);
                return;
            case 10:
                this.s.playSound(R.raw.n_10);
                return;
            case 11:
                this.s.playSound(R.raw.n_11);
                return;
            case 12:
                this.s.playSound(R.raw.n_12);
                return;
            case 13:
                this.s.playSound(R.raw.n_13);
                return;
            case 14:
                this.s.playSound(R.raw.n_14);
                return;
            case 15:
                this.s.playSound(R.raw.n_15);
                return;
            case 16:
                this.s.playSound(R.raw.n_16);
                return;
            default:
                return;
        }
    }

    
    public void vegitablesPick(ImageView imageView) {
        if (imageView.getId() == R.id.container) {
            int i2 = this.e;
            if (i2 == 2) {
                this.l.setImageResource(this.t.get(0).getVeg());
            } else if (i2 == 4) {
                this.m.setImageResource(this.t.get(0).getVeg());
            } else if (i2 == 7) {
                this.n.setImageResource(this.t.get(0).getVeg());
            }
        }
    }

    public void onBackPressed() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.s.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    public void onClick(View view2) {
        int id = view2.getId();
        if (id == R.id.boy) {
            this.s.playSound(R.raw.yuhhu);
        } else if (id == R.id.homeBtn) {
            this.s.playSound(R.raw.click);
            clickBounceAnim(view2);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    Number_Object_CountingActivity.this.onBackPressed();
                }
            }, 300);
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_number__object__counting);
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
        this.s = new MyMediaPlayer(this);
        setViewId();
        startGame();
    }

    
    public void onPause() {
        super.onPause();
        this.r = true;
        this.s.StopMp();
    }

    
    public void onResume() {
        super.onResume();
        this.r = false;
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void onStop() {
        super.onStop();
        this.r = true;
        this.handler.removeCallbacksAndMessages(0);
    }
}
