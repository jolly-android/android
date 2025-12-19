package com.counting.kidsgame.cakebuilding;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.farmingcount.NumberModel;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;
import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Number_CakeGameActivity extends Activity implements View.OnClickListener {
    TextView A;
    ConstraintLayout A0;
    TextView B;
    ConstraintLayout B0;
    TextView C;
    ConstraintLayout C0;
    TextView D;
    ConstraintLayout D0;
    TextView E;
    ConstraintLayout E0;
    TextView F;
    ConstraintLayout F0;
    TextView G;
    ConstraintLayout G0;
    TextView H;
    ConstraintLayout H0;
    TextView I;
    ConstraintLayout I0;
    TextView J;
    ConstraintLayout J0;
    TextView K;
    ConstraintLayout K0;
    TextView L;
    ConstraintLayout L0;
    TextView M;
    ConstraintLayout M0;
    TextView N;
    ConstraintLayout N0;
    TextView O;
    boolean O0 = false;
    TextView P;
    int P0;
    TextView Q;
    int Q0;
    TextView R1;
    int R0;
    ImageView S;
    MyMediaPlayer S0;
    ImageView T;
    ArrayList<NumberModel> T0;
    ImageView U;
    ArrayList<ColorModelList> U0;
    ImageView V;
    boolean V0 = false;
    ImageView W;
    MyAdView W0;
    ImageView X;
    Typeface X0;
    ImageView Y;
    int Y0;
    ImageView Z;
    int Z0 = 0;


    ConstraintLayout f4550a;
    ImageView a0;
    int a1 = 0;


    ConstraintLayout f4551b;
    ImageView b0;


    ConstraintLayout f4552c;
    ImageView c0;


    ConstraintLayout f4553d;
    ImageView d0;
    ConstraintLayout e;
    ImageView e0;
    ConstraintLayout f;
    ImageView f0;
    ConstraintLayout g;
    ImageView g0;
    ConstraintLayout h;
    ImageView h0;

    public Handler handler = new Handler(Looper.getMainLooper());
    ConstraintLayout i;
    ImageView i0;
    ImageView j;
    ImageView j0;
    ImageView k;
    ImageView k0;
    ImageView l;
    ImageView l0;
    ImageView m;
    ImageView m0;
    ImageView n;
    ImageView n0;
    ImageView o;
    ImageView o0;
    ImageView p;
    ImageView p0;
    ImageView q;
    ImageView q0;
    ImageView r;
    ImageView r0;
    ImageView s;
    ImageView s0;
    ImageView t;
    ImageView t0;
    ImageView u;
    ImageView u0;
    ImageView v;
    ImageView v0;

    public View view;
    ImageView w;
    ImageView w0;
    ImageView x;
    ImageView x0;
    TextView y;
    ConstraintLayout y0;
    TextView z;
    ConstraintLayout z0;

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, final DragEvent dragEvent) {
            if (dragEvent.getLocalState() != null) {
                View unused = Number_CakeGameActivity.this.view = (View) dragEvent.getLocalState();
            }
            int action = dragEvent.getAction();
            if (action != 1) {
                if (action != 3) {
                    if (action != 4) {
                        if (action == 5 && Number_CakeGameActivity.this.view != null) {
                            Log.d("ACTION_DRAG_ENTERED", Number_CakeGameActivity.this.view.getTag() + " : " + view.getTag());
                        }
                    }
                } else if (Number_CakeGameActivity.this.view != null) {
                    Log.d("DRAG_TEST1111", Number_CakeGameActivity.this.view.getTag() + " : " + view.getTag());
                    if (Number_CakeGameActivity.this.view.getTag().equals(view.getTag())) {
                        Number_CakeGameActivity.this.view.setVisibility(View.INVISIBLE);
                        view.setVisibility(View.VISIBLE);
                        Number_CakeGameActivity.this.S0.playSound(R.raw.drag_right);
                        Number_CakeGameActivity.this.starAnimate(view);
                        Number_CakeGameActivity.this.numberMatching((ConstraintLayout) view);
                        Number_CakeGameActivity.this.S0.speakApplause();
                    } else {
                        Number_CakeGameActivity.this.S0.playSound(R.raw.drag_wrong);
                        Number_CakeGameActivity.this.view.setVisibility(View.VISIBLE);
                    }
                }
                if (Number_CakeGameActivity.this.view != null) {
                    Log.d("ACTION_DRAG_ENDED", Number_CakeGameActivity.this.view.getTag() + " : " + view.getTag());
                    Number_CakeGameActivity.this.view.post(new Runnable() {
                        public void run() {
                            if (Number_CakeGameActivity.this.dropEventNotHandled(dragEvent)) {
                                Number_CakeGameActivity.this.view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            } else if (Number_CakeGameActivity.this.view != null) {
                Number_CakeGameActivity.this.view.clearAnimation();
                Number_CakeGameActivity.this.view.setVisibility(View.INVISIBLE);
                Log.d("ACTION_DRAG_STARTED", Number_CakeGameActivity.this.view.getTag() + " : " + view.getTag());
            }
            return true;
        }
    }

    public class MyTouchListener implements View.OnTouchListener {
        public MyTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                System.out.println("ACTION_DOWN");
                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                view.startDrag(new ClipData(view.getTag().toString(), new String[]{"text/plain"}, item), new View.DragShadowBuilder(view), view, 0);
                Number_CakeGameActivity.this.x.clearAnimation();
                Number_CakeGameActivity number_CakeGameActivity = Number_CakeGameActivity.this;
                number_CakeGameActivity.V0 = true;
                number_CakeGameActivity.x.setVisibility(View.INVISIBLE);
                Number_CakeGameActivity.this.S0.playSound(R.raw.click);
                Number_CakeGameActivity.this.playNumberSound((ConstraintLayout) view);
            } else if (action != 1) {
                return false;
            } else {
                view.setVisibility(View.VISIBLE);
            }
            return false;
        }
    }

    private void animateClick(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view2.startAnimation(loadAnimation);
    }


    public void bunnyCome(final ImageView imageView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 1000.0f, 0.0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        imageView.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Number_CakeGameActivity.this.bunnyHappy(imageView);
                Number_CakeGameActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        imageView.clearAnimation();
                        imageView.setVisibility(View.GONE);
                        Number_CakeGameActivity.this.f4550a.clearAnimation();
                        Number_CakeGameActivity.this.f4550a.setVisibility(View.INVISIBLE);
                        Number_CakeGameActivity.this.restartGame();
                    }
                }, 2500);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }


    public void bunnyHappy(final ImageView imageView) {
        imageView.setImageResource(R.drawable.bunnyhappy);
        this.S0.StopMp();
        if (!this.O0) {
            this.S0.playSound(R.raw.yuhhu);
        }
        final AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        Runnable r1 = new Runnable() {
            public void run() {
                animationDrawable.stop();
                imageView.setImageResource(R.drawable.cake_game_bunny1);
            }
        };
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.postDelayed(r1, 1500);
        }
    }

    private void cakeChange() {
        int nextInt = new Random().nextInt(2) + 1;
        this.Z0 = nextInt;
        if (nextInt == 1) {
            this.j.setImageResource(R.drawable.cake_game_1cake1);
            this.r.setImageResource(R.drawable.cake_game_1cake1);
            this.S.setImageResource(R.drawable.cake_game_1cake1);
            this.W.setImageResource(R.drawable.cake_game_1cake1);
            this.a0.setImageResource(R.drawable.cake_game_1cake1);
            this.e0.setImageResource(R.drawable.cake_game_1cake1);
            this.k.setImageResource(R.drawable.cake_game_1cake2);
            this.s.setImageResource(R.drawable.cake_game_1cake2);
            this.T.setImageResource(R.drawable.cake_game_1cake2);
            this.X.setImageResource(R.drawable.cake_game_1cake2);
            this.b0.setImageResource(R.drawable.cake_game_1cake2);
            this.f0.setImageResource(R.drawable.cake_game_1cake2);
            this.l.setImageResource(R.drawable.cake_game_1cake3);
            this.t.setImageResource(R.drawable.cake_game_1cake3);
            this.U.setImageResource(R.drawable.cake_game_1cake3);
            this.Y.setImageResource(R.drawable.cake_game_1cake3);
            this.c0.setImageResource(R.drawable.cake_game_1cake3);
            this.g0.setImageResource(R.drawable.cake_game_1cake3);
            this.m.setImageResource(R.drawable.cake_game_1cake4);
            this.u.setImageResource(R.drawable.cake_game_1cake4);
            this.V.setImageResource(R.drawable.cake_game_1cake4);
            this.Z.setImageResource(R.drawable.cake_game_1cake4);
            this.d0.setImageResource(R.drawable.cake_game_1cake4);
            this.h0.setImageResource(R.drawable.cake_game_1cake4);
        } else if (nextInt == 2) {
            this.j.setImageResource(R.drawable.cake_game_2cake1);
            this.r.setImageResource(R.drawable.cake_game_2cake1);
            this.S.setImageResource(R.drawable.cake_game_2cake1);
            this.W.setImageResource(R.drawable.cake_game_2cake1);
            this.a0.setImageResource(R.drawable.cake_game_2cake1);
            this.e0.setImageResource(R.drawable.cake_game_2cake1);
            this.k.setImageResource(R.drawable.cake_game_2cake2);
            this.s.setImageResource(R.drawable.cake_game_2cake2);
            this.T.setImageResource(R.drawable.cake_game_2cake2);
            this.X.setImageResource(R.drawable.cake_game_2cake2);
            this.b0.setImageResource(R.drawable.cake_game_2cake2);
            this.f0.setImageResource(R.drawable.cake_game_2cake2);
            this.l.setImageResource(R.drawable.cake_game_2cake3);
            this.t.setImageResource(R.drawable.cake_game_2cake3);
            this.U.setImageResource(R.drawable.cake_game_2cake3);
            this.Y.setImageResource(R.drawable.cake_game_2cake3);
            this.c0.setImageResource(R.drawable.cake_game_2cake3);
            this.g0.setImageResource(R.drawable.cake_game_2cake3);
            this.m.setImageResource(R.drawable.cake_game_2cake4);
            this.u.setImageResource(R.drawable.cake_game_2cake4);
            this.V.setImageResource(R.drawable.cake_game_2cake4);
            this.Z.setImageResource(R.drawable.cake_game_2cake4);
            this.d0.setImageResource(R.drawable.cake_game_2cake4);
            this.h0.setImageResource(R.drawable.cake_game_2cake4);
        }
    }


    public void cakeCome(ConstraintLayout constraintLayout) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -1000.0f, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        constraintLayout.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Number_CakeGameActivity number_CakeGameActivity = Number_CakeGameActivity.this;
                number_CakeGameActivity.bunnyHappy(number_CakeGameActivity.v);
                Number_CakeGameActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        Number_CakeGameActivity.this.S0.playSound(R.raw.boing);
                        Number_CakeGameActivity.this.crackCake();
                        Number_CakeGameActivity.this.popIteamCome();
                        Number_CakeGameActivity.this.v.setVisibility(View.INVISIBLE);
                    }
                }, 2500);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                Number_CakeGameActivity.this.S0.playSound(R.raw.appear);
            }
        });
    }

    private void clickBounceAnim(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view2.startAnimation(loadAnimation);
    }


    public void crackCake() {
        this.j.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.k.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.l.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.m.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.n.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.o.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.p.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.q.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.y.setTextColor(getResources().getColor(R.color.imageNamecolor));
        this.z.setTextColor(getResources().getColor(R.color.imageNamecolor));
        this.A.setTextColor(getResources().getColor(R.color.imageNamecolor));
        this.B.setTextColor(getResources().getColor(R.color.imageNamecolor));
        this.f.setVisibility(View.INVISIBLE);
        this.g.setVisibility(View.INVISIBLE);
        this.h.setVisibility(View.INVISIBLE);
        this.i.setVisibility(View.INVISIBLE);
        this.j.setVisibility(View.VISIBLE);
        this.k.setVisibility(View.VISIBLE);
        this.l.setVisibility(View.VISIBLE);
        this.m.setVisibility(View.VISIBLE);
    }


    public boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
    }


    public void hintMove(final ImageView imageView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -500.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
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


    public void numberMatching(ConstraintLayout constraintLayout) {
        switch (constraintLayout.getId()) {
            case R.id.cake1:
                this.y.setTextColor(getResources().getColor(this.U0.get(0).getColor()));
                this.n.setColorFilter(getResources().getColor(R.color.color25));
                this.j.setVisibility(View.INVISIBLE);
                this.r.setVisibility(View.VISIBLE);
                this.a1++;
                break;
            case R.id.cake2:
                this.z.setTextColor(getResources().getColor(this.U0.get(1).getColor()));
                this.o.setColorFilter(getResources().getColor(R.color.color25));
                this.k.setVisibility(View.INVISIBLE);
                this.s.setVisibility(View.VISIBLE);
                this.a1++;
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        Number_CakeGameActivity.this.f.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                break;
            case R.id.cake3:
                this.A.setTextColor(getResources().getColor(this.U0.get(2).getColor()));
                this.p.setColorFilter(getResources().getColor(R.color.color25));
                this.l.setVisibility(View.INVISIBLE);
                this.t.setVisibility(View.VISIBLE);
                this.a1++;
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        Number_CakeGameActivity.this.g.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                break;
            case R.id.cake4:
                this.B.setTextColor(getResources().getColor(this.U0.get(3).getColor()));
                this.q.setColorFilter(getResources().getColor(R.color.color25));
                this.m.setVisibility(View.INVISIBLE);
                this.u.setVisibility(View.VISIBLE);
                this.a1++;
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        Number_CakeGameActivity.this.h.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                break;
        }
        if (this.a1 == 4) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    Number_CakeGameActivity number_CakeGameActivity = Number_CakeGameActivity.this;
                    number_CakeGameActivity.bunnyCome(number_CakeGameActivity.v);
                }
            }, 1500);
        }
    }

    public void playNumberSound(ConstraintLayout constraintLayout) {
        int id = constraintLayout.getId();
        int i = 0;
        switch (id) {
            case R.id.c1pattern_cake1:
                while (i < this.T0.size()) {
                    if (this.R0 == this.T0.get(i).getNumber()) {
                        this.S0.playSound(this.T0.get(i).getSound());
                    }
                    i++;
                }
                return;
            case R.id.c1pattern_cake2:
                while (i < this.T0.size()) {
                    if (this.R0 + 1 == this.T0.get(i).getNumber()) {
                        this.S0.playSound(this.T0.get(i).getSound());
                    }
                    i++;
                }
                return;
            case R.id.c1pattern_cake3:
                while (i < this.T0.size()) {
                    if (this.R0 + 2 == this.T0.get(i).getNumber()) {
                        this.S0.playSound(this.T0.get(i).getSound());
                    }
                    i++;
                }
                return;
            case R.id.c1pattern_cake4:
                while (i < this.T0.size()) {
                    if (this.R0 + 3 == this.T0.get(i).getNumber()) {
                        this.S0.playSound(this.T0.get(i).getSound());
                    }
                    i++;
                }
                return;
            default:
                switch (id) {
                    case R.id.c2pattern_cake1:
                        break;
                    case R.id.c2pattern_cake2:
                        break;
                    case R.id.c2pattern_cake3:
                        break;
                    case R.id.c2pattern_cake4:
                        break;
                    default:
                        switch (id) {
                            case R.id.c3pattern_cake1:
                                break;
                            case R.id.c3pattern_cake2:
                                break;
                            case R.id.c3pattern_cake3:
                                break;
                            case R.id.c3pattern_cake4:
                                break;
                            default:
                                switch (id) {
                                    case R.id.c4pattern_cake1:
                                        break;
                                    case R.id.c4pattern_cake2:
                                        break;
                                    case R.id.c4pattern_cake3:
                                        break;
                                    case R.id.c4pattern_cake4:
                                        break;
                                    default:
                                        return;
                                }
                        }
                }
        }
    }

    public void popIteamCome() {
        this.y0.setVisibility(View.VISIBLE);
        this.C0.setVisibility(View.VISIBLE);
        this.G0.setVisibility(View.VISIBLE);
        this.K0.setVisibility(View.VISIBLE);
        this.z0.setVisibility(View.VISIBLE);
        this.D0.setVisibility(View.VISIBLE);
        this.H0.setVisibility(View.VISIBLE);
        this.L0.setVisibility(View.VISIBLE);
        this.A0.setVisibility(View.VISIBLE);
        this.E0.setVisibility(View.VISIBLE);
        this.I0.setVisibility(View.VISIBLE);
        this.M0.setVisibility(View.VISIBLE);
        this.B0.setVisibility(View.VISIBLE);
        this.F0.setVisibility(View.VISIBLE);
        this.J0.setVisibility(View.VISIBLE);
        this.N0.setVisibility(View.VISIBLE);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                Number_CakeGameActivity.this.i.setVisibility(View.VISIBLE);
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                Number_CakeGameActivity number_CakeGameActivity = Number_CakeGameActivity.this;
                if (!number_CakeGameActivity.V0) {
                    number_CakeGameActivity.hintMove(number_CakeGameActivity.x);
                }
                Number_CakeGameActivity.this.S0.playSound(R.raw.complete_the_pattern);
            }
        }, 1500);
    }


    public void restartGame() {
        setId();
        startGame();
        this.a1 = 0;
        this.f4550a.setVisibility(View.INVISIBLE);
        this.j.setVisibility(View.INVISIBLE);
        this.k.setVisibility(View.INVISIBLE);
        this.l.setVisibility(View.INVISIBLE);
        this.m.setVisibility(View.INVISIBLE);
        this.r.setVisibility(View.VISIBLE);
        this.s.setVisibility(View.VISIBLE);
        this.t.setVisibility(View.VISIBLE);
        this.u.setVisibility(View.VISIBLE);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adView);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.W0.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setDragDrop() {
        this.y0.setOnTouchListener(new MyTouchListener());
        this.z0.setOnTouchListener(new MyTouchListener());
        this.A0.setOnTouchListener(new MyTouchListener());
        this.B0.setOnTouchListener(new MyTouchListener());
        this.C0.setOnTouchListener(new MyTouchListener());
        this.D0.setOnTouchListener(new MyTouchListener());
        this.E0.setOnTouchListener(new MyTouchListener());
        this.F0.setOnTouchListener(new MyTouchListener());
        this.G0.setOnTouchListener(new MyTouchListener());
        this.H0.setOnTouchListener(new MyTouchListener());
        this.I0.setOnTouchListener(new MyTouchListener());
        this.J0.setOnTouchListener(new MyTouchListener());
        this.K0.setOnTouchListener(new MyTouchListener());
        this.L0.setOnTouchListener(new MyTouchListener());
        this.M0.setOnTouchListener(new MyTouchListener());
        this.N0.setOnTouchListener(new MyTouchListener());
        this.f.setOnDragListener(new MyDragListener());
        this.g.setOnDragListener(new MyDragListener());
        this.h.setOnDragListener(new MyDragListener());
        this.i.setOnDragListener(new MyDragListener());
        this.y0.setTag(1);
        this.z0.setTag(2);
        this.A0.setTag(3);
        this.B0.setTag(4);
        this.C0.setTag(1);
        this.D0.setTag(2);
        this.E0.setTag(3);
        this.F0.setTag(4);
        this.G0.setTag(1);
        this.H0.setTag(2);
        this.I0.setTag(3);
        this.J0.setTag(4);
        this.K0.setTag(1);
        this.L0.setTag(2);
        this.M0.setTag(3);
        this.N0.setTag(4);
        this.f.setTag(1);
        this.g.setTag(2);
        this.h.setTag(3);
        this.i.setTag(4);
    }

    private void setId() {
        this.f4551b = (ConstraintLayout) findViewById(R.id.cake1Layout);
        this.f4552c = (ConstraintLayout) findViewById(R.id.cake2Layout);
        this.f4553d = (ConstraintLayout) findViewById(R.id.cake3Layout);
        this.e = (ConstraintLayout) findViewById(R.id.cake4Layout);
        this.f4550a = (ConstraintLayout) findViewById(R.id.cakeFullLayout);
        this.f = (ConstraintLayout) findViewById(R.id.cake1);
        this.g = (ConstraintLayout) findViewById(R.id.cake2);
        this.h = (ConstraintLayout) findViewById(R.id.cake3);
        this.i = (ConstraintLayout) findViewById(R.id.cake4);
        this.j = (ImageView) findViewById(R.id.c1);
        this.k = (ImageView) findViewById(R.id.c2);
        this.l = (ImageView) findViewById(R.id.c3);
        this.m = (ImageView) findViewById(R.id.c4);
        this.r = (ImageView) findViewById(R.id.cReal1);
        this.s = (ImageView) findViewById(R.id.cReal2);
        this.t = (ImageView) findViewById(R.id.cReal3);
        this.u = (ImageView) findViewById(R.id.cReal4);
        this.n = (ImageView) findViewById(R.id.p1);
        this.o = (ImageView) findViewById(R.id.p2);
        this.p = (ImageView) findViewById(R.id.p3);
        this.q = (ImageView) findViewById(R.id.p4);
        TextView textView = (TextView) findViewById(R.id.tv_p1);
        this.y = textView;
        textView.setTypeface(this.X0);
        TextView textView2 = (TextView) findViewById(R.id.tv_p2);
        this.z = textView2;
        textView2.setTypeface(this.X0);
        TextView textView3 = (TextView) findViewById(R.id.tv_p3);
        this.A = textView3;
        textView3.setTypeface(this.X0);
        TextView textView4 = (TextView) findViewById(R.id.tv_p4);
        this.B = textView4;
        textView4.setTypeface(this.X0);
        this.v = (ImageView) findViewById(R.id.bunny);
        this.w = (ImageView) findViewById(R.id.homeBtn);
        this.x = (ImageView) findViewById(R.id.hint2Hand);
        this.y0 = (ConstraintLayout) findViewById(R.id.c1pattern_cake1);
        this.z0 = (ConstraintLayout) findViewById(R.id.c1pattern_cake2);
        this.A0 = (ConstraintLayout) findViewById(R.id.c1pattern_cake3);
        this.B0 = (ConstraintLayout) findViewById(R.id.c1pattern_cake4);
        this.S = (ImageView) findViewById(R.id.c1pattern_c1);
        this.T = (ImageView) findViewById(R.id.c1pattern_c2);
        this.U = (ImageView) findViewById(R.id.c1pattern_c3);
        this.V = (ImageView) findViewById(R.id.c1pattern_c4);
        this.i0 = (ImageView) findViewById(R.id.c1pattern_p1);
        this.j0 = (ImageView) findViewById(R.id.c1pattern_p2);
        this.k0 = (ImageView) findViewById(R.id.c1pattern_p3);
        this.l0 = (ImageView) findViewById(R.id.c1pattern_p4);
        TextView textView5 = (TextView) findViewById(R.id.c1pattern_tv_p1);
        this.C = textView5;
        textView5.setTypeface(this.X0);
        TextView textView6 = (TextView) findViewById(R.id.c1pattern_tv_p2);
        this.D = textView6;
        textView6.setTypeface(this.X0);
        TextView textView7 = (TextView) findViewById(R.id.c1pattern_tv_p3);
        this.E = textView7;
        textView7.setTypeface(this.X0);
        TextView textView8 = (TextView) findViewById(R.id.c1pattern_tv_p4);
        this.F = textView8;
        textView8.setTypeface(this.X0);
        this.C0 = (ConstraintLayout) findViewById(R.id.c2pattern_cake1);
        this.D0 = (ConstraintLayout) findViewById(R.id.c2pattern_cake2);
        this.E0 = (ConstraintLayout) findViewById(R.id.c2pattern_cake3);
        this.F0 = (ConstraintLayout) findViewById(R.id.c2pattern_cake4);
        this.W = (ImageView) findViewById(R.id.c2pattern_c1);
        this.X = (ImageView) findViewById(R.id.c2pattern_c2);
        this.Y = (ImageView) findViewById(R.id.c2pattern_c3);
        this.Z = (ImageView) findViewById(R.id.c2pattern_c4);
        this.m0 = (ImageView) findViewById(R.id.c2pattern_p1);
        this.n0 = (ImageView) findViewById(R.id.c2pattern_p2);
        this.o0 = (ImageView) findViewById(R.id.c2pattern_p3);
        this.p0 = (ImageView) findViewById(R.id.c2pattern_p4);
        TextView textView9 = (TextView) findViewById(R.id.c2pattern_tv_p1);
        this.G = textView9;
        textView9.setTypeface(this.X0);
        TextView textView10 = (TextView) findViewById(R.id.c2pattern_tv_p2);
        this.H = textView10;
        textView10.setTypeface(this.X0);
        TextView textView11 = (TextView) findViewById(R.id.c2pattern_tv_p3);
        this.I = textView11;
        textView11.setTypeface(this.X0);
        TextView textView12 = (TextView) findViewById(R.id.c2pattern_tv_p4);
        this.J = textView12;
        textView12.setTypeface(this.X0);
        this.G0 = (ConstraintLayout) findViewById(R.id.c3pattern_cake1);
        this.H0 = (ConstraintLayout) findViewById(R.id.c3pattern_cake2);
        this.I0 = (ConstraintLayout) findViewById(R.id.c3pattern_cake3);
        this.J0 = (ConstraintLayout) findViewById(R.id.c3pattern_cake4);
        this.a0 = (ImageView) findViewById(R.id.c3pattern_c1);
        this.b0 = (ImageView) findViewById(R.id.c3pattern_c2);
        this.c0 = (ImageView) findViewById(R.id.c3pattern_c3);
        this.d0 = (ImageView) findViewById(R.id.c3pattern_c4);
        this.q0 = (ImageView) findViewById(R.id.c3pattern_p1);
        this.r0 = (ImageView) findViewById(R.id.c3pattern_p2);
        this.s0 = (ImageView) findViewById(R.id.c3pattern_p3);
        this.t0 = (ImageView) findViewById(R.id.c3pattern_p4);
        TextView textView13 = (TextView) findViewById(R.id.c3pattern_tv_p1);
        this.K = textView13;
        textView13.setTypeface(this.X0);
        TextView textView14 = (TextView) findViewById(R.id.c3pattern_tv_p2);
        this.L = textView14;
        textView14.setTypeface(this.X0);
        TextView textView15 = (TextView) findViewById(R.id.c3pattern_tv_p3);
        this.M = textView15;
        textView15.setTypeface(this.X0);
        TextView textView16 = (TextView) findViewById(R.id.c3pattern_tv_p4);
        this.N = textView16;
        textView16.setTypeface(this.X0);
        this.K0 = (ConstraintLayout) findViewById(R.id.c4pattern_cake1);
        this.L0 = (ConstraintLayout) findViewById(R.id.c4pattern_cake2);
        this.M0 = (ConstraintLayout) findViewById(R.id.c4pattern_cake3);
        this.N0 = (ConstraintLayout) findViewById(R.id.c4pattern_cake4);
        this.e0 = (ImageView) findViewById(R.id.c4pattern_c1);
        this.f0 = (ImageView) findViewById(R.id.c4pattern_c2);
        this.g0 = (ImageView) findViewById(R.id.c4pattern_c3);
        this.h0 = (ImageView) findViewById(R.id.c4pattern_c4);
        this.u0 = (ImageView) findViewById(R.id.c4pattern_p1);
        this.v0 = (ImageView) findViewById(R.id.c4pattern_p2);
        this.w0 = (ImageView) findViewById(R.id.c4pattern_p3);
        this.x0 = (ImageView) findViewById(R.id.c4pattern_p4);
        TextView textView17 = (TextView) findViewById(R.id.c4pattern_tv_p1);
        this.O = textView17;
        textView17.setTypeface(this.X0);
        TextView textView18 = (TextView) findViewById(R.id.c4pattern_tv_p2);
        this.P = textView18;
        textView18.setTypeface(this.X0);
        TextView textView19 = (TextView) findViewById(R.id.c4pattern_tv_p3);
        this.Q = textView19;
        textView19.setTypeface(this.X0);
        TextView textView20 = (TextView) findViewById(R.id.c4pattern_tv_p4);
        this.R1 = textView20;
        textView20.setTypeface(this.X0);
        this.f4551b.setVisibility(View.INVISIBLE);
        this.f4552c.setVisibility(View.INVISIBLE);
        this.f4553d.setVisibility(View.INVISIBLE);
        this.e.setVisibility(View.INVISIBLE);
        this.y0.setVisibility(View.INVISIBLE);
        this.C0.setVisibility(View.INVISIBLE);
        this.G0.setVisibility(View.INVISIBLE);
        this.K0.setVisibility(View.INVISIBLE);
        this.z0.setVisibility(View.INVISIBLE);
        this.D0.setVisibility(View.INVISIBLE);
        this.H0.setVisibility(View.INVISIBLE);
        this.L0.setVisibility(View.INVISIBLE);
        this.A0.setVisibility(View.INVISIBLE);
        this.E0.setVisibility(View.INVISIBLE);
        this.I0.setVisibility(View.INVISIBLE);
        this.M0.setVisibility(View.INVISIBLE);
        this.B0.setVisibility(View.INVISIBLE);
        this.F0.setVisibility(View.INVISIBLE);
        this.J0.setVisibility(View.INVISIBLE);
        this.N0.setVisibility(View.INVISIBLE);
        this.x.setVisibility(View.INVISIBLE);
        this.v.setVisibility(View.VISIBLE);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.f4550a.setVisibility(View.INVISIBLE);
    }

    private void setNumberList() {
        ArrayList<NumberModel> arrayList = new ArrayList<>();
        this.T0 = arrayList;
        arrayList.clear();
        this.T0.add(new NumberModel(R.drawable.a1, 1, R.raw.n_1));
        this.T0.add(new NumberModel(R.drawable.a2, 2, R.raw.n_2));
        this.T0.add(new NumberModel(R.drawable.a3, 3, R.raw.n_3));
        this.T0.add(new NumberModel(R.drawable.a4, 4, R.raw.n_4));
        this.T0.add(new NumberModel(R.drawable.a5, 5, R.raw.n_5));
        this.T0.add(new NumberModel(R.drawable.a6, 6, R.raw.n_6));
        this.T0.add(new NumberModel(R.drawable.a7, 7, R.raw.n_7));
        this.T0.add(new NumberModel(R.drawable.a8, 8, R.raw.n_8));
        this.T0.add(new NumberModel(R.drawable.a9, 9, R.raw.n_9));
        this.T0.add(new NumberModel(R.drawable.a10, 10, R.raw.n_10));
        this.T0.add(new NumberModel(R.drawable.a11, 11, R.raw.n_11));
        this.T0.add(new NumberModel(R.drawable.a12, 12, R.raw.n_12));
        this.T0.add(new NumberModel(R.drawable.a13, 13, R.raw.n_13));
        this.T0.add(new NumberModel(R.drawable.a14, 14, R.raw.n_14));
        this.T0.add(new NumberModel(R.drawable.a15, 15, R.raw.n_15));
        this.T0.add(new NumberModel(R.drawable.a16, 16, R.raw.n_16));
        this.T0.add(new NumberModel(R.drawable.a17, 17, R.raw.n_17));
        this.T0.add(new NumberModel(R.drawable.a18, 18, R.raw.n_18));
        this.T0.add(new NumberModel(R.drawable.a19, 19, R.raw.n_19));
        this.T0.add(new NumberModel(R.drawable.a20, 20, R.raw.n_20));
        Collections.shuffle(this.T0);
        int nextInt = new Random().nextInt(16) + 1;
        this.Q0 = nextInt;
        this.R0 = nextInt;
        this.P0 = 0;
        while (this.P0 < this.T0.size()) {
            if (this.Q0 == this.T0.get(this.P0).getNumber()) {
                this.C.setText(String.valueOf(this.Q0));
                this.D.setText(String.valueOf(this.Q0 + 1));
                this.E.setText(String.valueOf(this.Q0 + 2));
                this.F.setText(String.valueOf(this.Q0 + 3));
                this.G.setText(String.valueOf(this.Q0));
                this.H.setText(String.valueOf(this.Q0 + 1));
                this.I.setText(String.valueOf(this.Q0 + 2));
                this.J.setText(String.valueOf(this.Q0 + 3));
                this.K.setText(String.valueOf(this.Q0));
                this.L.setText(String.valueOf(this.Q0 + 1));
                this.M.setText(String.valueOf(this.Q0 + 2));
                this.N.setText(String.valueOf(this.Q0 + 3));
                this.O.setText(String.valueOf(this.Q0));
                this.P.setText(String.valueOf(this.Q0 + 1));
                this.Q.setText(String.valueOf(this.Q0 + 2));
                this.R1.setText(String.valueOf(this.Q0 + 3));
                this.y.setText(String.valueOf(this.Q0));
                this.z.setText(String.valueOf(this.Q0 + 1));
                this.A.setText(String.valueOf(this.Q0 + 2));
                this.B.setText(String.valueOf(this.Q0 + 3));
            }
            this.P0++;
        }
        ArrayList<ColorModelList> arrayList2 = new ArrayList<>();
        this.U0 = arrayList2;
        arrayList2.clear();
        this.U0.add(new ColorModelList(R.color.orange));
        this.U0.add(new ColorModelList(R.color.color2));
        this.U0.add(new ColorModelList(R.color.colorGreen));
        this.U0.add(new ColorModelList(R.color.color17));
        Collections.shuffle(this.U0);
        this.C.setTextColor(getResources().getColor(this.U0.get(0).getColor()));
        this.D.setTextColor(getResources().getColor(this.U0.get(1).getColor()));
        this.E.setTextColor(getResources().getColor(this.U0.get(2).getColor()));
        this.F.setTextColor(getResources().getColor(this.U0.get(3).getColor()));
        this.G.setTextColor(getResources().getColor(this.U0.get(0).getColor()));
        this.H.setTextColor(getResources().getColor(this.U0.get(1).getColor()));
        this.I.setTextColor(getResources().getColor(this.U0.get(2).getColor()));
        this.J.setTextColor(getResources().getColor(this.U0.get(3).getColor()));
        this.K.setTextColor(getResources().getColor(this.U0.get(0).getColor()));
        this.L.setTextColor(getResources().getColor(this.U0.get(1).getColor()));
        this.M.setTextColor(getResources().getColor(this.U0.get(2).getColor()));
        this.N.setTextColor(getResources().getColor(this.U0.get(3).getColor()));
        this.O.setTextColor(getResources().getColor(this.U0.get(0).getColor()));
        this.P.setTextColor(getResources().getColor(this.U0.get(1).getColor()));
        this.Q.setTextColor(getResources().getColor(this.U0.get(2).getColor()));
        this.R1.setTextColor(getResources().getColor(this.U0.get(3).getColor()));
    }


    public void starAnimate(View view2) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.15f, 0.5f).oneShot(view2, 10);
    }

    private void startGame() {
        int nextInt = new Random().nextInt(4) + 1;
        this.Y0 = nextInt;
        if (nextInt == 1) {
            this.f4551b.setVisibility(View.VISIBLE);
        } else if (nextInt == 2) {
            this.f4552c.setVisibility(View.VISIBLE);
        } else if (nextInt == 3) {
            this.f4553d.setVisibility(View.VISIBLE);
        } else if (nextInt == 4) {
            this.e.setVisibility(View.VISIBLE);
        }
        cakeChange();
        setNumberList();
        setDragDrop();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                Number_CakeGameActivity.this.f4550a.setVisibility(View.VISIBLE);
                Number_CakeGameActivity number_CakeGameActivity = Number_CakeGameActivity.this;
                number_CakeGameActivity.cakeCome(number_CakeGameActivity.f4550a);
            }
        }, 2000);
    }

    public void onBackPressed() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.S0.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    public void onClick(View view2) {
        int id = view2.getId();
        if (id == R.id.bunny) {
            bunnyHappy(this.v);
        } else if (id == R.id.homeBtn) {
            this.S0.playSound(R.raw.click);
            clickBounceAnim(view2);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    Number_CakeGameActivity.this.onBackPressed();
                }
            }, 300);
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_number__cake_game);
        this.X0 = Typeface.createFromAsset(getAssets(), "english.ttf");
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
        this.S0 = new MyMediaPlayer(this);
        setId();
        startGame();
        this.W0 = new MyAdView(this);

        setAd();
    }


    public void onPause() {
        super.onPause();
        this.O0 = true;
        this.S0.StopMp();
    }


    public void onResume() {
        super.onResume();
        this.O0 = false;
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void onStop() {
        super.onStop();
        this.O0 = true;
        this.S0.StopMp();
        this.handler.removeCallbacksAndMessages(0);
    }
}
