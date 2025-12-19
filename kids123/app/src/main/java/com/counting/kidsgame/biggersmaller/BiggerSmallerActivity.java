package com.counting.kidsgame.biggersmaller;

import android.app.Activity;
import android.app.Dialog;
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

import androidx.constraintlayout.widget.ConstraintLayout;

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
import java.util.List;
import java.util.Random;

public class BiggerSmallerActivity extends Activity implements View.OnClickListener {
    ConstraintLayout A;
    ConstraintLayout B;
    ConstraintLayout C;
    ConstraintLayout D;
    int[] E = {R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5};
    MyAdView F;
    int G = 0;
    int H = 0;
    int I = 0;
    int J = 0;
    int K = 0;
    int L = 0;
    int M = 0;
    int N = 0;
    int O = 0;

    
    boolean f4504a = false;

    
    MyMediaPlayer f4505b;

    
    ArrayList<NumbersModel> f4506c;

    
    List<Integer> f4507d = new ArrayList();
    int e = 0;
    ImageView f;
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
    ImageView q;
    ImageView r;
    ImageView s;
    ImageView t;
    ImageView u;
    ImageView v;
    private View view;
    ImageView w;
    ImageView x;
    ConstraintLayout y;
    ConstraintLayout z;

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

    private void correctAnimation(View view2) {
        view2.setVisibility(View.GONE);
    }

    
    public void disableAll() {
        this.y.setEnabled(false);
        this.z.setEnabled(false);
        this.A.setEnabled(false);
        this.B.setEnabled(false);
        this.C.setEnabled(false);
    }

    
    public void enableAll() {
        this.y.setEnabled(true);
        this.z.setEnabled(true);
        this.A.setEnabled(true);
        this.B.setEnabled(true);
        this.C.setEnabled(true);
    }

    private void fiveIteamVisible() {
        Collections.shuffle(this.f4506c);
        this.J = this.f4506c.get(0).getNumber();
        this.K = this.f4506c.get(1).getNumber();
        this.L = this.f4506c.get(2).getNumber();
        this.M = this.f4506c.get(3).getNumber();
        this.N = this.f4506c.get(4).getNumber();
        this.f4507d.add(Integer.valueOf(this.J));
        this.f4507d.add(Integer.valueOf(this.K));
        this.f4507d.add(Integer.valueOf(this.L));
        this.f4507d.add(Integer.valueOf(this.M));
        this.f4507d.add(Integer.valueOf(this.N));
        this.H = ((Integer) Collections.min(this.f4507d)).intValue();
        this.I = ((Integer) Collections.max(this.f4507d)).intValue();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.y.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.k.setImageResource(biggerSmallerActivity.f4506c.get(0).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.z.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.l.setImageResource(biggerSmallerActivity.f4506c.get(1).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.A.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.m.setImageResource(biggerSmallerActivity.f4506c.get(2).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 2000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.B.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.n.setImageResource(biggerSmallerActivity.f4506c.get(3).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.C.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.o.setImageResource(biggerSmallerActivity.f4506c.get(4).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 3000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.questionAsk();
            }
        }, 3500);
    }

    private void fourIteamVisible() {
        Collections.shuffle(this.f4506c);
        this.J = this.f4506c.get(0).getNumber();
        this.K = this.f4506c.get(1).getNumber();
        this.L = this.f4506c.get(2).getNumber();
        this.M = this.f4506c.get(3).getNumber();
        this.f4507d.add(Integer.valueOf(this.J));
        this.f4507d.add(Integer.valueOf(this.K));
        this.f4507d.add(Integer.valueOf(this.L));
        this.f4507d.add(Integer.valueOf(this.M));
        this.H = ((Integer) Collections.min(this.f4507d)).intValue();
        this.I = ((Integer) Collections.max(this.f4507d)).intValue();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.y.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.k.setImageResource(biggerSmallerActivity.f4506c.get(0).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.z.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.l.setImageResource(biggerSmallerActivity.f4506c.get(1).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 2000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.A.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.m.setImageResource(biggerSmallerActivity.f4506c.get(2).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.B.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.n.setImageResource(biggerSmallerActivity.f4506c.get(3).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 2500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.questionAsk();
            }
        }, 3000);
    }

    
    public void gameRestart() {
        final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
        dialog.getWindow().setFlags(8, 8);
        dialog.setContentView(R.layout.popupdilog);
        RemoveBackButton.hideNavigationDialog(dialog);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.replay);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BiggerSmallerActivity.this.f4505b.playSound(R.raw.click);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.O = 0;
                biggerSmallerActivity.e = 0;
                biggerSmallerActivity.f4507d.clear();
                int i = 0;
                while (true) {
                    BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                    int[] iArr = biggerSmallerActivity2.E;
                    if (i < iArr.length) {
                        ((ImageView) biggerSmallerActivity2.findViewById(iArr[i])).setImageResource(R.drawable.rocket_launcher_grey_star);
                        i++;
                    } else {
                        biggerSmallerActivity2.startGame();
                        dialog.dismiss();
                        imageView.setClickable(false);
                        BiggerSmallerActivity.this.handler.postDelayed(new Runnable() {
                            public void run() {
                                imageView.setClickable(true);
                            }
                        }, 1000);
                        return;
                    }
                }
            }
        });
        ((ImageView) dialog.findViewById(R.id.noreplay)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BiggerSmallerActivity.this.f4505b.playSound(R.raw.click);
                dialog.dismiss();
                BiggerSmallerActivity.this.finish();
            }
        });
        if (!isFinishing()) {
            dialog.show();
        }
    }

    
    public void popUpIteamVisible() {
        if (this.O == 0) {
            this.e = 1;
        }
        int i2 = this.e;
        if (i2 == 1) {
            twoIteamVisible();
        } else if (i2 == 2) {
            threeIteamVisible();
        } else if (i2 == 3) {
            fourIteamVisible();
        } else if (i2 == 4) {
            fiveIteamVisible();
        } else if (i2 == 5) {
            fiveIteamVisible();
        }
    }

    
    public void questionAsk() {
        int nextInt = new Random().nextInt(2) + 1;
        this.G = nextInt;
        if (nextInt != 1) {
            if (nextInt == 2 && !this.f4504a) {
                this.f4505b.playSound(R.raw.which_one_is_smaller);
            }
        } else if (!this.f4504a) {
            this.f4505b.playSound(R.raw.which_one_is_bigger);
        }
        this.w.setClickable(true);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.enableAll();
            }
        }, 2000);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adView);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.F.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setViewId() {
        this.p = (ImageView) findViewById(R.id.homeBtn);
        this.f = (ImageView) findViewById(R.id.basket1);
        this.g = (ImageView) findViewById(R.id.basket2);
        this.h = (ImageView) findViewById(R.id.basket3);
        this.i = (ImageView) findViewById(R.id.basket4);
        this.j = (ImageView) findViewById(R.id.basket5);
        this.k = (ImageView) findViewById(R.id.num1);
        this.l = (ImageView) findViewById(R.id.num2);
        this.m = (ImageView) findViewById(R.id.num3);
        this.n = (ImageView) findViewById(R.id.num4);
        this.o = (ImageView) findViewById(R.id.num5);
        this.y = (ConstraintLayout) findViewById(R.id.b1Layout);
        this.z = (ConstraintLayout) findViewById(R.id.b2Layout);
        this.A = (ConstraintLayout) findViewById(R.id.b3Layout);
        this.B = (ConstraintLayout) findViewById(R.id.b4Layout);
        this.C = (ConstraintLayout) findViewById(R.id.b5Layout);
        this.D = (ConstraintLayout) findViewById(R.id.tractorLayout);
        this.q = (ImageView) findViewById(R.id.truckbasket);
        this.r = (ImageView) findViewById(R.id.s1);
        this.s = (ImageView) findViewById(R.id.s2);
        this.t = (ImageView) findViewById(R.id.s3);
        this.u = (ImageView) findViewById(R.id.s4);
        this.v = (ImageView) findViewById(R.id.s5);
        this.w = (ImageView) findViewById(R.id.boy);
        this.x = (ImageView) findViewById(R.id.hintHand);
    }

    private void shakeAnimation(View view2) {
        view2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
    }

    private void shuffleList() {
        ArrayList<NumbersModel> arrayList = new ArrayList<>();
        this.f4506c = arrayList;
        arrayList.clear();
        this.f4506c.add(new NumbersModel(1, R.raw.n_1, R.drawable.a1));
        this.f4506c.add(new NumbersModel(2, R.raw.n_2, R.drawable.a2));
        this.f4506c.add(new NumbersModel(3, R.raw.n_3, R.drawable.a3));
        this.f4506c.add(new NumbersModel(4, R.raw.n_4, R.drawable.a4));
        this.f4506c.add(new NumbersModel(5, R.raw.n_5, R.drawable.a5));
        this.f4506c.add(new NumbersModel(6, R.raw.n_6, R.drawable.a6));
        this.f4506c.add(new NumbersModel(7, R.raw.n_7, R.drawable.a7));
        this.f4506c.add(new NumbersModel(8, R.raw.n_8, R.drawable.a8));
        this.f4506c.add(new NumbersModel(9, R.raw.n_9, R.drawable.a9));
        this.f4506c.add(new NumbersModel(10, R.raw.n_10, R.drawable.a10));
        this.f4506c.add(new NumbersModel(11, R.raw.n_11, R.drawable.a11));
        this.f4506c.add(new NumbersModel(12, R.raw.n_12, R.drawable.a12));
        this.f4506c.add(new NumbersModel(13, R.raw.n_13, R.drawable.a13));
        this.f4506c.add(new NumbersModel(14, R.raw.n_14, R.drawable.a14));
        this.f4506c.add(new NumbersModel(15, R.raw.n_15, R.drawable.a15));
        this.f4506c.add(new NumbersModel(16, R.raw.n_16, R.drawable.a16));
        this.f4506c.add(new NumbersModel(17, R.raw.n_17, R.drawable.a17));
        this.f4506c.add(new NumbersModel(18, R.raw.n_18, R.drawable.a18));
        this.f4506c.add(new NumbersModel(19, R.raw.n_19, R.drawable.a19));
        this.f4506c.add(new NumbersModel(20, R.raw.n_20, R.drawable.a20));
        Collections.shuffle(this.f4506c);
    }

    private void starPrint() {
        this.f4505b.playSound(R.raw.wordpop);
        for (int i2 = 0; i2 < this.E.length; i2++) {
            int i3 = this.O;
            if (i2 < i3) {
                if (i3 == 1) {
                    startOneShotParticle(this.r);
                } else if (i3 == 2) {
                    startOneShotParticle(this.s);
                } else if (i3 == 3) {
                    startOneShotParticle(this.t);
                } else if (i3 == 4) {
                    startOneShotParticle(this.u);
                } else if (i3 == 5) {
                    startOneShotParticle(this.v);
                }
                ((ImageView) findViewById(this.E[i2])).setImageResource(R.drawable.rocket_launcher_yellow_star);
                this.q.setVisibility(View.VISIBLE);
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                        if (!biggerSmallerActivity.f4504a) {
                            biggerSmallerActivity.tractorGone(biggerSmallerActivity.D);
                        }
                    }
                }, 1000);
            }
        }
    }

    
    public void startGame() {
        this.y.setVisibility(View.INVISIBLE);
        this.z.setVisibility(View.INVISIBLE);
        this.A.setVisibility(View.INVISIBLE);
        this.B.setVisibility(View.INVISIBLE);
        this.C.setVisibility(View.INVISIBLE);
        this.x.setVisibility(View.INVISIBLE);
        this.D.setVisibility(View.INVISIBLE);
        this.q.setVisibility(View.INVISIBLE);
        this.y.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.w.setOnClickListener(this);
        shuffleList();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.tractorCome(biggerSmallerActivity.D);
            }
        }, 1000);
    }

    private void startOneShotParticle(ImageView imageView) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.spark, 600).setSpeedRange(0.15f, 0.5f).oneShot(imageView, 10);
    }

    private void threeIteamVisible() {
        Collections.shuffle(this.f4506c);
        this.J = this.f4506c.get(0).getNumber();
        this.K = this.f4506c.get(1).getNumber();
        this.L = this.f4506c.get(2).getNumber();
        this.f4507d.add(Integer.valueOf(this.J));
        this.f4507d.add(Integer.valueOf(this.K));
        this.f4507d.add(Integer.valueOf(this.L));
        this.H = ((Integer) Collections.min(this.f4507d)).intValue();
        this.I = ((Integer) Collections.max(this.f4507d)).intValue();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.y.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.k.setImageResource(biggerSmallerActivity.f4506c.get(0).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.z.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.l.setImageResource(biggerSmallerActivity.f4506c.get(1).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 2000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.A.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.m.setImageResource(biggerSmallerActivity.f4506c.get(2).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.questionAsk();
            }
        }, 2500);
    }

    
    public void tractorCome(View view2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2500.0f, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                BiggerSmallerActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        BiggerSmallerActivity.this.popUpIteamVisible();
                    }
                }, 1000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                BiggerSmallerActivity.this.disableAll();
                BiggerSmallerActivity.this.f4505b.playSound(R.raw.tractor_anim);
            }
        });
    }

    
    public void tractorGone(View view2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -2000.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1500);
        translateAnimation.setFillAfter(true);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                BiggerSmallerActivity.this.q.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                        int i = biggerSmallerActivity.O;
                        if (i == 5) {
                            biggerSmallerActivity.gameRestart();
                            BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                            if (!biggerSmallerActivity2.f4504a) {
                                biggerSmallerActivity2.f4505b.playSound(R.raw.clap);
                            }
                        } else if (i >= 1) {
                            biggerSmallerActivity.f4507d.clear();
                            BiggerSmallerActivity biggerSmallerActivity3 = BiggerSmallerActivity.this;
                            biggerSmallerActivity3.e++;
                            if (!biggerSmallerActivity3.f4504a) {
                                biggerSmallerActivity3.tractorCome(biggerSmallerActivity3.D);
                            }
                        }
                    }
                }, 1000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                BiggerSmallerActivity.this.w.setClickable(false);
                BiggerSmallerActivity.this.disableAll();
                BiggerSmallerActivity.this.y.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.z.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.A.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.B.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.C.setVisibility(View.INVISIBLE);
                BiggerSmallerActivity.this.f4505b.playSound(R.raw.tractor_anim);
            }
        });
    }

    private void twoIteamVisible() {
        Collections.shuffle(this.f4506c);
        this.J = this.f4506c.get(0).getNumber();
        this.K = this.f4506c.get(1).getNumber();
        this.f4507d.add(Integer.valueOf(this.J));
        this.f4507d.add(Integer.valueOf(this.K));
        this.H = ((Integer) Collections.min(this.f4507d)).intValue();
        this.I = ((Integer) Collections.max(this.f4507d)).intValue();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.y.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.k.setImageResource(biggerSmallerActivity.f4506c.get(0).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1000);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.z.setVisibility(View.VISIBLE);
                BiggerSmallerActivity biggerSmallerActivity = BiggerSmallerActivity.this;
                biggerSmallerActivity.l.setImageResource(biggerSmallerActivity.f4506c.get(1).getNumberImg());
                BiggerSmallerActivity biggerSmallerActivity2 = BiggerSmallerActivity.this;
                if (!biggerSmallerActivity2.f4504a) {
                    biggerSmallerActivity2.f4505b.playSound(R.raw.wordpop);
                }
            }
        }, 1500);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                BiggerSmallerActivity.this.questionAsk();
            }
        }, 2000);
    }

    public void onBackPressed() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.f4505b.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    public void onClick(View view2) {
        int id = view2.getId();
        if (id == R.id.boy) {
            this.f4505b.StopMp();
            int i2 = this.G;
            if (i2 == 1) {
                if (!this.f4504a) {
                    this.f4505b.playSound(R.raw.which_one_is_bigger);
                }
            } else if (i2 == 2) {
                if (!this.f4504a) {
                    this.f4505b.playSound(R.raw.which_one_is_smaller);
                }
            } else if (!this.f4504a) {
                this.f4505b.playSound(R.raw.yuhhu);
            }
        } else if (id != R.id.homeBtn) {
            switch (id) {
                case R.id.b1Layout:
                    int i3 = this.I;
                    int i4 = this.J;
                    if (i3 == i4 && this.G == 1) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        starPrint();
                        correctAnimation(this.y);
                        return;
                    } else if (this.G == 2 && this.H == i4) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.y);
                        starPrint();
                        return;
                    } else {
                        if (!this.f4504a) {
                            this.f4505b.playSound(R.raw.not_this_one);
                        }
                        shakeAnimation(this.y);
                        disableAll();
                        this.handler.postDelayed(new Runnable() {
                            public void run() {
                                BiggerSmallerActivity.this.enableAll();
                            }
                        }, 500);
                        return;
                    }
                case R.id.b2Layout:
                    int i5 = this.I;
                    int i6 = this.K;
                    if (i5 == i6 && this.G == 1) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.z);
                        starPrint();
                        return;
                    } else if (this.H == i6 && this.G == 2) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.z);
                        starPrint();
                        return;
                    } else {
                        if (!this.f4504a) {
                            this.f4505b.playSound(R.raw.not_this_one);
                        }
                        shakeAnimation(this.z);
                        disableAll();
                        this.handler.postDelayed(new Runnable() {
                            public void run() {
                                BiggerSmallerActivity.this.enableAll();
                            }
                        }, 500);
                        return;
                    }
                case R.id.b3Layout:
                    int i7 = this.I;
                    int i8 = this.L;
                    if (i7 == i8 && this.G == 1) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.A);
                        starPrint();
                        return;
                    } else if (this.H == i8 && this.G == 2) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.A);
                        starPrint();
                        return;
                    } else {
                        if (!this.f4504a) {
                            this.f4505b.playSound(R.raw.not_this_one);
                        }
                        shakeAnimation(this.A);
                        disableAll();
                        this.handler.postDelayed(new Runnable() {
                            public void run() {
                                BiggerSmallerActivity.this.enableAll();
                            }
                        }, 500);
                        return;
                    }
                case R.id.b4Layout:
                    int i9 = this.I;
                    int i10 = this.M;
                    if (i9 == i10 && this.G == 1) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.B);
                        starPrint();
                        return;
                    } else if (this.H == i10 && this.G == 2) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.B);
                        starPrint();
                        return;
                    } else {
                        if (!this.f4504a) {
                            this.f4505b.playSound(R.raw.not_this_one);
                        }
                        shakeAnimation(this.B);
                        disableAll();
                        this.handler.postDelayed(new Runnable() {
                            public void run() {
                                BiggerSmallerActivity.this.enableAll();
                            }
                        }, 500);
                        return;
                    }
                case R.id.b5Layout:
                    int i11 = this.I;
                    int i12 = this.N;
                    if (i11 == i12 && this.G == 1) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.C);
                        starPrint();
                        return;
                    } else if (this.H == i12 && this.G == 2) {
                        if (!this.f4504a) {
                            this.f4505b.speakApplause();
                        }
                        this.O++;
                        disableAll();
                        correctAnimation(this.C);
                        starPrint();
                        return;
                    } else {
                        if (!this.f4504a) {
                            this.f4505b.playSound(R.raw.not_this_one);
                        }
                        shakeAnimation(this.C);
                        disableAll();
                        this.handler.postDelayed(new Runnable() {
                            public void run() {
                                BiggerSmallerActivity.this.enableAll();
                            }
                        }, 500);
                        return;
                    }
                default:
                    return;
            }
        } else {
            this.f4505b.playSound(R.raw.click);
            clickBounceAnim(view2);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    BiggerSmallerActivity.this.onBackPressed();
                }
            }, 300);
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bigger_smaller);
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
        this.f4505b = new MyMediaPlayer(this);
        setViewId();
        startGame();
        this.F = new MyAdView(this);
        
        setAd();
    }

    
    public void onPause() {
        super.onPause();
        this.f4504a = true;
        this.f4505b.StopMp();
    }

    
    public void onResume() {
        super.onResume();
        this.f4504a = false;
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void onStop() {
        super.onStop();
        this.f4504a = true;
        this.handler.removeCallbacksAndMessages(0);
    }
}
