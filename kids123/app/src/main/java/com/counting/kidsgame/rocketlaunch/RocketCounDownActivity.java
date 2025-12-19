package com.counting.kidsgame.rocketlaunch;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.WorkRequest;

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

public class RocketCounDownActivity extends Activity implements View.OnClickListener {
    ImageView A;
    ImageView B;
    ImageView C;
    ImageView D;
    ImageView E;
    ImageView F;
    ImageView G;
    ImageView H;
    ImageView I;
    ImageView J;
    ImageView K;
    ImageView L;
    ImageView M;
    ImageView N;
    ImageView O;
    ImageView P;
    ImageView Q;
    ImageView R1;
    ImageView S;
    ImageView T;
    ImageView U;
    ImageView V;
    ImageView W;
    ImageView X;
    ImageView Y;
    ImageView Z;


    boolean f4773a = false;
    ConstraintLayout a0;


    MyMediaPlayer f4774b;
    ConstraintLayout b0;


    ArrayList<NumberModel> f4775c;
    ConstraintLayout c0;


    TextView f4776d;
    ConstraintLayout d0;
    TextView e;
    ConstraintLayout e0;
    TextView f;
    ConstraintLayout f0;
    TextView g;
    ConstraintLayout g0;
    TextView h;
    ConstraintLayout h0;

    public Handler handler = new Handler(Looper.getMainLooper());
    TextView i;
    ConstraintLayout i0;
    TextView j;
    ConstraintLayout j0;
    TextView k;
    ConstraintLayout k0;
    TextView l;
    int[] l0 = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5, R.id.star6, R.id.star7, R.id.star8, R.id.star9, R.id.star10};
    TextView m;
    int[] m0 = {R.id.s1Layout, R.id.s2Layout, R.id.s3Layout, R.id.s4Layout, R.id.s5Layout, R.id.s6Layout, R.id.s7Layout, R.id.s8Layout, R.id.s9Layout, R.id.s10Layout};
    TextView n;
    MyAdView n0;
    TextView o;
    boolean o0;
    TextView p;
    Typeface p0;
    TextView q;
    int q0 = 0;
    TextView r;
    int r0 = 1;
    TextView s;
    View s0;
    TextView t;
    boolean t0;
    TextView u;
    TextView v;
    private View view;
    TextView w;
    TextView x;
    ImageView y;
    ImageView z;

    private void animateClick(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view2.startAnimation(loadAnimation);
    }


    public void clickBounceAnim(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view2.startAnimation(loadAnimation);
    }


    public void flameAnimation(ImageView imageView) {
        imageView.setImageResource(R.drawable.flameanim);
        ((AnimationDrawable) imageView.getDrawable()).start();
        Runnable r4 = new Runnable() {
            public void run() {
            }
        };
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.postDelayed(r4, 1500);
        }
    }

    private void listIteam() {
        ArrayList<NumberModel> arrayList = new ArrayList<>();
        this.f4775c = arrayList;
        arrayList.clear();
        this.f4775c.add(new NumberModel(0, 1, R.raw.n_1));
        this.f4775c.add(new NumberModel(0, 2, R.raw.n_2));
        this.f4775c.add(new NumberModel(0, 3, R.raw.n_3));
        this.f4775c.add(new NumberModel(0, 4, R.raw.n_4));
        this.f4775c.add(new NumberModel(0, 5, R.raw.n_5));
        this.f4775c.add(new NumberModel(0, 6, R.raw.n_6));
        this.f4775c.add(new NumberModel(0, 7, R.raw.n_7));
        this.f4775c.add(new NumberModel(0, 8, R.raw.n_8));
        this.f4775c.add(new NumberModel(0, 9, R.raw.n_9));
        this.f4775c.add(new NumberModel(0, 10, R.raw.n_10));
        Collections.shuffle(this.f4775c);
        this.f4776d.setText(String.valueOf(this.f4775c.get(0).getNumber()));
        this.e.setText(String.valueOf(this.f4775c.get(1).getNumber()));
        this.f.setText(String.valueOf(this.f4775c.get(2).getNumber()));
        this.g.setText(String.valueOf(this.f4775c.get(3).getNumber()));
        this.h.setText(String.valueOf(this.f4775c.get(4).getNumber()));
        this.i.setText(String.valueOf(this.f4775c.get(5).getNumber()));
        this.j.setText(String.valueOf(this.f4775c.get(6).getNumber()));
        this.k.setText(String.valueOf(this.f4775c.get(7).getNumber()));
        this.l.setText(String.valueOf(this.f4775c.get(8).getNumber()));
        this.m.setText(String.valueOf(this.f4775c.get(9).getNumber()));
        this.a0.setOnClickListener(this);
        this.b0.setOnClickListener(this);
        this.c0.setOnClickListener(this);
        this.d0.setOnClickListener(this);
        this.e0.setOnClickListener(this);
        this.f0.setOnClickListener(this);
        this.g0.setOnClickListener(this);
        this.h0.setOnClickListener(this);
        this.i0.setOnClickListener(this);
        this.j0.setOnClickListener(this);
    }

    private void numberChcek(int i2) {
        switch (i2) {
            case 1:
                this.f4774b.playSound(R.raw.n_1);
                this.s0.setVisibility(View.INVISIBLE);
                this.n.setVisibility(View.VISIBLE);
                this.n.setText(String.valueOf(i2));
                this.I.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.I);
                return;
            case 2:
                this.f4774b.playSound(R.raw.n_2);
                this.s0.setVisibility(View.INVISIBLE);
                this.o.setVisibility(View.VISIBLE);
                this.o.setText(String.valueOf(i2));
                this.J.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.J);
                return;
            case 3:
                this.f4774b.playSound(R.raw.n_3);
                this.s0.setVisibility(View.INVISIBLE);
                this.p.setVisibility(View.VISIBLE);
                this.p.setText(String.valueOf(i2));
                this.K.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.K);
                return;
            case 4:
                this.f4774b.playSound(R.raw.n_4);
                this.s0.setVisibility(View.INVISIBLE);
                this.q.setVisibility(View.VISIBLE);
                this.q.setText(String.valueOf(i2));
                this.L.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.L);
                return;
            case 5:
                this.f4774b.playSound(R.raw.n_5);
                this.s0.setVisibility(View.INVISIBLE);
                this.r.setVisibility(View.VISIBLE);
                this.r.setText(String.valueOf(i2));
                this.M.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.M);
                return;
            case 6:
                this.s0.setVisibility(View.INVISIBLE);
                this.s.setVisibility(View.VISIBLE);
                this.s.setText(String.valueOf(i2));
                this.N.setImageResource(R.drawable.rocket_launcher_yellow_star);
                this.f4774b.playSound(R.raw.n_6);
                startOneShotParticle(this.N);
                return;
            case 7:
                this.f4774b.playSound(R.raw.n_7);
                this.s0.setVisibility(View.INVISIBLE);
                this.t.setVisibility(View.VISIBLE);
                this.t.setText(String.valueOf(i2));
                this.O.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.O);
                return;
            case 8:
                this.f4774b.playSound(R.raw.n_8);
                this.s0.setVisibility(View.INVISIBLE);
                this.u.setVisibility(View.VISIBLE);
                this.u.setText(String.valueOf(i2));
                this.P.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.P);
                return;
            case 9:
                this.f4774b.playSound(R.raw.n_9);
                this.s0.setVisibility(View.INVISIBLE);
                this.v.setVisibility(View.VISIBLE);
                this.v.setText(String.valueOf(i2));
                this.Q.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.Q);
                return;
            case 10:
                this.f4774b.playSound(R.raw.n_10);
                this.s0.setVisibility(View.INVISIBLE);
                this.w.setVisibility(View.VISIBLE);
                this.w.setText(String.valueOf(i2));
                this.R1.setImageResource(R.drawable.rocket_launcher_yellow_star);
                startOneShotParticle(this.R1);
                return;
            default:
                return;
        }
    }

    private void popUpVisible() {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.a0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.b0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.c0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 2000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.d0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.e0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 3000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.f0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 3500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.g0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 4000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.h0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 4500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.wordpop);
                }
                RocketCounDownActivity.this.i0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 5000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity.this.f4774b.playSound(R.raw.wordpop);
                RocketCounDownActivity.this.j0.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.tap_numbers);
                }
                RocketCounDownActivity.this.starEnable();
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 5600);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.o0) {
                    rocketCounDownActivity.W.setVisibility(View.VISIBLE);
                    Animation loadAnimation = AnimationUtils.loadAnimation(RocketCounDownActivity.this.getApplicationContext(), R.anim.zoom_in_out_low);
                    loadAnimation.setDuration(1000);
                    RocketCounDownActivity.this.W.startAnimation(loadAnimation);
                }
            }
        }, 6500);
    }


    public void restartGame() {
        int i2 = 0;
        this.q0 = 0;
        setViewId();
        startGame();
        this.S.setVisibility(View.INVISIBLE);
        this.k0.setVisibility(View.VISIBLE);
        this.T.setImageResource(R.drawable.rocket_launcher_launchpad_1);
        this.Y.setVisibility(View.INVISIBLE);
        while (true) {
            int[] iArr = this.l0;
            if (i2 < iArr.length) {
                ((ImageView) findViewById(iArr[i2])).setImageResource(R.drawable.rocket_launcher_grey_star);
                i2++;
            } else {
                return;
            }
        }
    }


    public void rocketGone(final View view2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -2000.0f);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(false);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                view2.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.speakApplause();
                }
                RocketCounDownActivity.this.Z.clearAnimation();
                RocketCounDownActivity.this.Z.setVisibility(View.VISIBLE);
                RocketCounDownActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        RocketCounDownActivity.this.restartGame();
                    }
                }, 1500);
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }


    public void rocketLunch() {
        this.T.setImageResource(R.drawable.rocket_launcher_launchpad_2);
        this.x.setVisibility(View.VISIBLE);
        this.x.setText("10");
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_out_low);
        loadAnimation.setDuration(1000);
        this.x.startAnimation(loadAnimation);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_10);
                }
                RocketCounDownActivity.this.R1.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.w.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_9);
                }
                RocketCounDownActivity.this.Q.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.v.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.x.setText("9");
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_8);
                }
                RocketCounDownActivity.this.P.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.u.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.x.setText("8");
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 3500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity.this.f4774b.playSound(R.raw.n_7);
                RocketCounDownActivity.this.O.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.t.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.x.setText("7");
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.StopMp();
                }
            }
        }, 4500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_6);
                }
                RocketCounDownActivity.this.N.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.s.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.x.setText("6");
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 5500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_5);
                }
                RocketCounDownActivity.this.x.setText("5");
                RocketCounDownActivity.this.M.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.r.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 6500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_4);
                }
                RocketCounDownActivity.this.x.setText("4");
                RocketCounDownActivity.this.L.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.q.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 7500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_3);
                }
                RocketCounDownActivity.this.x.setText("3");
                RocketCounDownActivity.this.K.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.p.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 8500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_2);
                }
                RocketCounDownActivity.this.x.setText("2");
                RocketCounDownActivity.this.J.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.o.setVisibility(View.INVISIBLE);
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                if (rocketCounDownActivity2.f4773a) {
                    rocketCounDownActivity2.f4774b.StopMp();
                }
            }
        }, 9500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.n_1);
                }
                RocketCounDownActivity.this.x.setText("1");
                RocketCounDownActivity.this.I.setImageResource(R.drawable.rocket_launcher_grey_star);
                RocketCounDownActivity.this.n.setVisibility(View.INVISIBLE);
            }
        }, WorkRequest.MIN_BACKOFF_MILLIS);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                if (!rocketCounDownActivity.f4773a) {
                    rocketCounDownActivity.f4774b.playSound(R.raw.rocket_anim);
                }
                RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                rocketCounDownActivity2.rocketGone(rocketCounDownActivity2.k0);
                RocketCounDownActivity.this.x.clearAnimation();
                RocketCounDownActivity.this.x.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.Z.setVisibility(View.VISIBLE);
                Animation loadAnimation = AnimationUtils.loadAnimation(RocketCounDownActivity.this.getApplicationContext(), R.anim.zoom_in_out_low);
                loadAnimation.setDuration(1000);
                RocketCounDownActivity.this.Z.startAnimation(loadAnimation);
            }
        }, 12000);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adView);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.n0.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setViewId() {
        TextView textView = (TextView) findViewById(R.id.tv1);
        this.f4776d = textView;
        textView.setTypeface(this.p0);
        TextView textView2 = (TextView) findViewById(R.id.tv2);
        this.e = textView2;
        textView2.setTypeface(this.p0);
        TextView textView3 = (TextView) findViewById(R.id.tv3);
        this.f = textView3;
        textView3.setTypeface(this.p0);
        TextView textView4 = (TextView) findViewById(R.id.tv4);
        this.g = textView4;
        textView4.setTypeface(this.p0);
        TextView textView5 = (TextView) findViewById(R.id.tv5);
        this.h = textView5;
        textView5.setTypeface(this.p0);
        TextView textView6 = (TextView) findViewById(R.id.tv6);
        this.i = textView6;
        textView6.setTypeface(this.p0);
        TextView textView7 = (TextView) findViewById(R.id.tv7);
        this.j = textView7;
        textView7.setTypeface(this.p0);
        TextView textView8 = (TextView) findViewById(R.id.tv8);
        this.k = textView8;
        textView8.setTypeface(this.p0);
        TextView textView9 = (TextView) findViewById(R.id.tv9);
        this.l = textView9;
        textView9.setTypeface(this.p0);
        TextView textView10 = (TextView) findViewById(R.id.tv10);
        this.m = textView10;
        textView10.setTypeface(this.p0);
        TextView textView11 = (TextView) findViewById(R.id.tv_1);
        this.n = textView11;
        textView11.setTypeface(this.p0);
        TextView textView12 = (TextView) findViewById(R.id.tv_2);
        this.o = textView12;
        textView12.setTypeface(this.p0);
        TextView textView13 = (TextView) findViewById(R.id.tv_3);
        this.p = textView13;
        textView13.setTypeface(this.p0);
        TextView textView14 = (TextView) findViewById(R.id.tv_4);
        this.q = textView14;
        textView14.setTypeface(this.p0);
        TextView textView15 = (TextView) findViewById(R.id.tv_5);
        this.r = textView15;
        textView15.setTypeface(this.p0);
        TextView textView16 = (TextView) findViewById(R.id.tv_6);
        this.s = textView16;
        textView16.setTypeface(this.p0);
        TextView textView17 = (TextView) findViewById(R.id.tv_7);
        this.t = textView17;
        textView17.setTypeface(this.p0);
        TextView textView18 = (TextView) findViewById(R.id.tv_8);
        this.u = textView18;
        textView18.setTypeface(this.p0);
        TextView textView19 = (TextView) findViewById(R.id.tv_9);
        this.v = textView19;
        textView19.setTypeface(this.p0);
        TextView textView20 = (TextView) findViewById(R.id.tv_10);
        this.w = textView20;
        textView20.setTypeface(this.p0);
        this.y = (ImageView) findViewById(R.id.s1);
        this.z = (ImageView) findViewById(R.id.s2);
        this.A = (ImageView) findViewById(R.id.s3);
        this.B = (ImageView) findViewById(R.id.s4);
        this.C = (ImageView) findViewById(R.id.s5);
        this.D = (ImageView) findViewById(R.id.s6);
        this.E = (ImageView) findViewById(R.id.s7);
        this.F = (ImageView) findViewById(R.id.s8);
        this.G = (ImageView) findViewById(R.id.s9);
        this.H = (ImageView) findViewById(R.id.s10);
        this.x = (TextView) findViewById(R.id.countTime);
        this.X = (ImageView) findViewById(R.id.hintHand2);
        this.Y = (ImageView) findViewById(R.id.launchBtn);
        this.Z = (ImageView) findViewById(R.id.magicReveal);
        this.a0 = (ConstraintLayout) findViewById(R.id.s1Layout);
        this.b0 = (ConstraintLayout) findViewById(R.id.s2Layout);
        this.c0 = (ConstraintLayout) findViewById(R.id.s3Layout);
        this.d0 = (ConstraintLayout) findViewById(R.id.s4Layout);
        this.e0 = (ConstraintLayout) findViewById(R.id.s5Layout);
        this.f0 = (ConstraintLayout) findViewById(R.id.s6Layout);
        this.g0 = (ConstraintLayout) findViewById(R.id.s7Layout);
        this.h0 = (ConstraintLayout) findViewById(R.id.s8Layout);
        this.i0 = (ConstraintLayout) findViewById(R.id.s9Layout);
        this.j0 = (ConstraintLayout) findViewById(R.id.s10Layout);
        this.I = (ImageView) findViewById(R.id.star1);
        this.J = (ImageView) findViewById(R.id.star2);
        this.K = (ImageView) findViewById(R.id.star3);
        this.L = (ImageView) findViewById(R.id.star4);
        this.M = (ImageView) findViewById(R.id.star5);
        this.N = (ImageView) findViewById(R.id.star6);
        this.O = (ImageView) findViewById(R.id.star7);
        this.P = (ImageView) findViewById(R.id.star8);
        this.Q = (ImageView) findViewById(R.id.star9);
        this.R1 = (ImageView) findViewById(R.id.star10);
        this.k0 = (ConstraintLayout) findViewById(R.id.rocket_Station);
        this.W = (ImageView) findViewById(R.id.hintHand);
        this.T = (ImageView) findViewById(R.id.launchpad);
        this.S = (ImageView) findViewById(R.id.flame);
        this.U = (ImageView) findViewById(R.id.rocket);
        ImageView imageView = (ImageView) findViewById(R.id.homeBtn);
        this.V = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RocketCounDownActivity.this.f4774b.playSound(R.raw.click);
                RocketCounDownActivity.this.clickBounceAnim(view);
                RocketCounDownActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        RocketCounDownActivity.this.onBackPressed();
                    }
                }, 300);
            }
        });
        this.Y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RocketCounDownActivity.this.Y.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.X.clearAnimation();
                RocketCounDownActivity.this.X.setVisibility(View.INVISIBLE);
                RocketCounDownActivity.this.S.setVisibility(View.VISIBLE);
                RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                rocketCounDownActivity.flameAnimation(rocketCounDownActivity.S);
                RocketCounDownActivity.this.rocketLunch();
                RocketCounDownActivity.this.f4774b.playSound(R.raw.bulldozer_anim);
                RocketCounDownActivity.this.o0 = true;
            }
        });
    }

    private void shakeAnimation(View view2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        loadAnimation.setDuration(500);
        view2.startAnimation(loadAnimation);
    }

    private void starDisable() {
        this.a0.setEnabled(false);
        this.b0.setEnabled(false);
        this.c0.setEnabled(false);
        this.d0.setEnabled(false);
        this.e0.setEnabled(false);
        this.f0.setEnabled(false);
        this.g0.setEnabled(false);
        this.h0.setEnabled(false);
        this.i0.setEnabled(false);
        this.j0.setEnabled(false);
    }


    public void starEnable() {
        this.a0.setEnabled(true);
        this.b0.setEnabled(true);
        this.c0.setEnabled(true);
        this.d0.setEnabled(true);
        this.e0.setEnabled(true);
        this.f0.setEnabled(true);
        this.g0.setEnabled(true);
        this.h0.setEnabled(true);
        this.i0.setEnabled(true);
        this.j0.setEnabled(true);
    }

    private void startGame() {
        starDisable();
        this.Y.setEnabled(false);
        this.S.setVisibility(View.INVISIBLE);
        this.n.setVisibility(View.INVISIBLE);
        this.o.setVisibility(View.INVISIBLE);
        this.p.setVisibility(View.INVISIBLE);
        this.q.setVisibility(View.INVISIBLE);
        this.r.setVisibility(View.INVISIBLE);
        this.s.setVisibility(View.INVISIBLE);
        this.t.setVisibility(View.INVISIBLE);
        this.u.setVisibility(View.INVISIBLE);
        this.v.setVisibility(View.INVISIBLE);
        this.w.setVisibility(View.INVISIBLE);
        this.a0.setVisibility(View.INVISIBLE);
        this.b0.setVisibility(View.INVISIBLE);
        this.c0.setVisibility(View.INVISIBLE);
        this.d0.setVisibility(View.INVISIBLE);
        this.e0.setVisibility(View.INVISIBLE);
        this.f0.setVisibility(View.INVISIBLE);
        this.g0.setVisibility(View.INVISIBLE);
        this.h0.setVisibility(View.INVISIBLE);
        this.i0.setVisibility(View.INVISIBLE);
        this.j0.setVisibility(View.INVISIBLE);
        this.W.setVisibility(View.INVISIBLE);
        this.X.setVisibility(View.INVISIBLE);
        this.Y.setVisibility(View.INVISIBLE);
        this.x.setVisibility(View.INVISIBLE);
        this.Z.setVisibility(View.INVISIBLE);
        popUpVisible();
        listIteam();
        int i2 = 0;
        while (true) {
            int[] iArr = this.l0;
            if (i2 < iArr.length) {
                ((ImageView) findViewById(iArr[i2])).setImageResource(R.drawable.rocket_launcher_grey_star);
                ((ImageView) findViewById(this.l0[i2])).setVisibility(View.VISIBLE);
                i2++;
            } else {
                return;
            }
        }
    }

    private void startOneShotParticle(View view2) {
        if (!this.f4773a) {
            this.f4774b.playSound(R.raw.wordpop);
        }
        new ParticleSystem((Activity) this, 10, (int) R.drawable.spark, 600).setSpeedRange(0.45f, 0.5f).oneShot(view2, 10);
    }

    public void onBackPressed() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.f4774b.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    public void onClick(View view2) {
        if (!this.t0) {
            this.W.clearAnimation();
            this.W.setVisibility(View.INVISIBLE);
            this.t0 = true;
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    RocketCounDownActivity.this.t0 = false;
                }
            }, 500);
            switch (view2.getId()) {
                case R.id.s10Layout:
                    this.r0 = this.f4775c.get(9).getNumber();
                    break;
                case R.id.s1Layout:
                    this.r0 = this.f4775c.get(0).getNumber();
                    break;
                case R.id.s2Layout:
                    this.r0 = this.f4775c.get(1).getNumber();
                    break;
                case R.id.s3Layout:
                    this.r0 = this.f4775c.get(2).getNumber();
                    break;
                case R.id.s4Layout:
                    this.r0 = this.f4775c.get(3).getNumber();
                    break;
                case R.id.s5Layout:
                    this.r0 = this.f4775c.get(4).getNumber();
                    break;
                case R.id.s6Layout:
                    this.r0 = this.f4775c.get(5).getNumber();
                    break;
                case R.id.s7Layout:
                    this.r0 = this.f4775c.get(6).getNumber();
                    break;
                case R.id.s8Layout:
                    this.r0 = this.f4775c.get(7).getNumber();
                    break;
                case R.id.s9Layout:
                    this.r0 = this.f4775c.get(8).getNumber();
                    break;
            }
            this.s0 = view2;
            int i2 = this.r0;
            if (i2 == 1) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 2 && this.q0 == 1) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 3 && this.q0 == 2) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 4 && this.q0 == 3) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 5 && this.q0 == 4) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 6 && this.q0 == 5) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 7 && this.q0 == 6) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 8 && this.q0 == 7) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 9 && this.q0 == 8) {
                numberChcek(i2);
                this.q0++;
            } else if (i2 == 10 && this.q0 == 9) {
                numberChcek(i2);
                this.q0++;
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        RocketCounDownActivity rocketCounDownActivity = RocketCounDownActivity.this;
                        if (rocketCounDownActivity.q0 == 10) {
                            rocketCounDownActivity.Y.setVisibility(View.VISIBLE);
                            RocketCounDownActivity.this.Y.setEnabled(true);
                            RocketCounDownActivity rocketCounDownActivity2 = RocketCounDownActivity.this;
                            if (!rocketCounDownActivity2.o0) {
                                rocketCounDownActivity2.X.setVisibility(View.VISIBLE);
                                Animation loadAnimation = AnimationUtils.loadAnimation(RocketCounDownActivity.this.getApplicationContext(), R.anim.zoom_in_out_low);
                                loadAnimation.setDuration(1000);
                                RocketCounDownActivity.this.X.startAnimation(loadAnimation);
                            }
                        }
                    }
                }, 1500);
            } else {
                this.f4774b.playSound(R.raw.wrong);
                shakeAnimation(view2);
            }
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rocket_coun_down);
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
        this.f4774b = new MyMediaPlayer(this);
        this.p0 = Typeface.createFromAsset(getAssets(), "arlrdbd.ttf");
        setViewId();
        startGame();
        this.n0 = new MyAdView(this);

        setAd();
    }


    public void onPause() {
        super.onPause();
        this.f4773a = true;
        this.f4774b.StopMp();
    }


    public void onResume() {
        super.onResume();
        this.f4773a = false;
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void onStop() {
        super.onStop();
        this.f4773a = true;
        this.handler.removeCallbacksAndMessages(0);
    }
}
