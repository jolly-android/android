package com.counting.kidsgame.mathequation;

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
import android.widget.ImageView;

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
import java.util.Random;

public class MathEquationGameActivity extends Activity implements View.OnClickListener {
    MyMediaPlayer A;
    ArrayList<NumberModel> B;
    ArrayList<NumberModel> C;
    int D;
    int E;
    int F = 0;
    int G = 0;
    MyAdView H;
    boolean I;
    int J;
    int K;
    int L;
    int M;
    int N = 0;
    boolean O = false;
    int P = 0;
    int Q;
    int R1;
    int S;

    
    ImageView f4740a;

    
    ImageView f4741b;

    
    ImageView f4742c;

    
    ImageView f4743d;
    ImageView e;
    ImageView f;
    ImageView g;
    ImageView h;
    
    public Handler handler = new Handler(Looper.getMainLooper());
    ImageView i;
    ImageView j;
    ImageView k;
    ImageView l;
    ImageView m;
    
    public MyMediaPlayer mp;
    ImageView n;
    ImageView o;
    ImageView p;
    ImageView q;
    ImageView r;
    ImageView s;
    ImageView t;
    ImageView u;
    FrameLayout v;
    
    public View view;
    FrameLayout w;
    FrameLayout x;
    ConstraintLayout y;
    boolean z = false;

    class MyDragListener implements View.OnDragListener {
        MyDragListener() {
        }

        public boolean onDrag(View view, final DragEvent dragEvent) {
            if (dragEvent.getLocalState() != null) {
                View unused = MathEquationGameActivity.this.view = (View) dragEvent.getLocalState();
            }
            int action = dragEvent.getAction();
            if (action != 1) {
                if (action != 3) {
                    if (action != 4) {
                        if (action == 5 && MathEquationGameActivity.this.view != null) {
                            Log.d("ACTION_DRAG_ENTERED", MathEquationGameActivity.this.view.getTag() + " : " + view.getTag());
                        }
                    }
                } else if (MathEquationGameActivity.this.view != null) {
                    Log.d("DRAG_TEST1111", MathEquationGameActivity.this.view.getTag() + " : " + view.getTag());
                    if (MathEquationGameActivity.this.view.getTag().equals(view.getTag())) {
                        MathEquationGameActivity.this.view.setVisibility(View.INVISIBLE);
                        view.setVisibility(View.VISIBLE);
                        MathEquationGameActivity.this.A.playSound(R.raw.drag_right);
                        MathEquationGameActivity.this.starAnimate(view);
                        MathEquationGameActivity.this.numberMatching((ImageView) view);
                        MathEquationGameActivity.this.A.speakApplause();
                    } else {
                        MathEquationGameActivity.this.A.playSound(R.raw.drag_wrong);
                        MathEquationGameActivity.this.view.setVisibility(View.VISIBLE);
                    }
                }
                if (MathEquationGameActivity.this.view != null) {
                    Log.d("ACTION_DRAG_ENDED", MathEquationGameActivity.this.view.getTag() + " : " + view.getTag());
                    MathEquationGameActivity.this.view.post(new Runnable() {
                        public void run() {
                            if (MathEquationGameActivity.this.dropEventNotHandled(dragEvent)) {
                                MathEquationGameActivity.this.view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            } else if (MathEquationGameActivity.this.view != null) {
                MathEquationGameActivity.this.view.clearAnimation();
                MathEquationGameActivity.this.view.setVisibility(View.INVISIBLE);
                Log.d("ACTION_DRAG_STARTED", MathEquationGameActivity.this.view.getTag() + " : " + view.getTag());
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
                MathEquationGameActivity.this.g.clearAnimation();
                MathEquationGameActivity.this.g.setVisibility(View.INVISIBLE);
                MathEquationGameActivity.this.A.playSound(R.raw.click);
                MathEquationGameActivity.this.playSound((FrameLayout) view);
            } else if (action != 1) {
                return false;
            } else {
                view.setVisibility(View.VISIBLE);
            }
            return false;
        }
    }

    private void additionOfFirstCase(int i2) {
        this.L = 0;
        while (this.L < this.B.size()) {
            if (this.B.get(this.L).getNumber() == this.D) {
                this.h.setImageResource(this.B.get(this.L).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(this.L));
                this.h.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.p.setImageResource(this.B.get(this.L).getImg());
            }
            this.L++;
        }
        this.M = 0;
        while (this.M < this.B.size()) {
            if (this.B.get(this.M).getNumber() == this.E) {
                this.i.setImageResource(this.B.get(this.M).getImg());
                this.C.add(this.B.get(this.M));
                this.i.setTag(Integer.valueOf(this.B.get(this.M).getImg()));
                this.q.setImageResource(this.B.get(this.M).getImg());
            }
            this.M++;
        }
        this.K = 0;
        while (this.K < this.B.size()) {
            if (this.B.get(this.K).getNumber() == i2) {
                this.j.setImageResource(this.B.get(this.K).getImg());
                this.C.add(this.B.get(this.K));
                this.j.setTag(Integer.valueOf(this.B.get(this.K).getImg()));
                this.r.setImageResource(this.B.get(this.K).getImg());
            }
            this.K++;
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
    }

    private void additionOfSecondCase(int i2) {
        this.L = 0;
        while (this.L < this.B.size()) {
            if (this.B.get(this.L).getNumber() == this.D) {
                this.h.setImageResource(this.B.get(this.L).getImg());
                this.i.setImageResource(this.B.get(this.L).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(this.L));
                this.C.add(this.B.get(this.L));
                this.h.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.i.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.p.setImageResource(this.B.get(this.L).getImg());
                this.q.setImageResource(this.B.get(this.L).getImg());
            }
            this.L++;
        }
        this.K = 0;
        while (this.K < this.B.size()) {
            if (this.B.get(this.K).getNumber() == i2) {
                this.j.setImageResource(this.B.get(this.K).getImg());
                this.C.add(this.B.get(this.K));
                this.j.setTag(Integer.valueOf(this.B.get(this.K).getImg()));
                this.r.setImageResource(this.B.get(this.K).getImg());
            }
            this.K++;
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
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
        startGame();
        this.P = 0;
        this.N = 0;
        this.I = true;
    }

    private void hintMove(final ImageView imageView) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -400.0f);
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

    private void moveFish(final View view2) {
        TranslateAnimation translateAnimation;
        boolean z2 = this.O;
        if (!z2) {
            translateAnimation = new TranslateAnimation(0.0f, -3000.0f, 0.0f, 0.0f);
        } else if (z2) {
            translateAnimation = new TranslateAnimation(0.0f, 3000.0f, 0.0f, 0.0f);
            this.O = false;
        } else {
            translateAnimation = null;
        }
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(false);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                view2.setVisibility(View.INVISIBLE);
                MathEquationGameActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        view2.setVisibility(View.VISIBLE);
                        view2.setClickable(true);
                    }
                }, 1500);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view2.setClickable(false);
            }
        });
    }

    
    public void numAnimation(View view2) {
        view2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
    }

    
    public void numberMatching(ImageView imageView) {
        int id = imageView.getId();
        if (id == R.id.num1) {
            this.h.setVisibility(View.INVISIBLE);
            this.P++;
        } else if (id == R.id.num2) {
            this.i.setVisibility(View.INVISIBLE);
            this.P++;
        } else if (id == R.id.result) {
            this.j.setVisibility(View.INVISIBLE);
            this.P++;
        }
        if (this.P == 3) {
            this.v.setVisibility(View.INVISIBLE);
            this.w.setVisibility(View.INVISIBLE);
            this.x.setVisibility(View.INVISIBLE);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    int i = 0;
                    while (true) {
                        mathEquationGameActivity.Q = i;
                        MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                        if (mathEquationGameActivity2.Q < mathEquationGameActivity2.B.size()) {
                            MathEquationGameActivity mathEquationGameActivity3 = MathEquationGameActivity.this;
                            int number = mathEquationGameActivity3.B.get(mathEquationGameActivity3.Q).getNumber();
                            MathEquationGameActivity mathEquationGameActivity4 = MathEquationGameActivity.this;
                            if (number == mathEquationGameActivity4.D) {
                                if (!mathEquationGameActivity4.z) {
                                    mathEquationGameActivity4.A.playSound(mathEquationGameActivity4.B.get(mathEquationGameActivity4.Q).getSound());
                                }
                                MathEquationGameActivity mathEquationGameActivity5 = MathEquationGameActivity.this;
                                mathEquationGameActivity5.numAnimation(mathEquationGameActivity5.p);
                            }
                            mathEquationGameActivity = MathEquationGameActivity.this;
                            i = mathEquationGameActivity.Q + 1;
                        } else {
                            return;
                        }
                    }
                }
            }, 1200);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    int i = mathEquationGameActivity.J;
                    if (i == 1) {
                        if (!mathEquationGameActivity.z) {
                            mathEquationGameActivity.A.playSound(R.raw.plus);
                        }
                    } else if (i == 2 && !mathEquationGameActivity.z) {
                        mathEquationGameActivity.A.playSound(R.raw.minus);
                    }
                    MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                    mathEquationGameActivity2.numAnimation(mathEquationGameActivity2.n);
                }
            }, 2000);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    int i = 0;
                    while (true) {
                        mathEquationGameActivity.R1 = i;
                        MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                        if (mathEquationGameActivity2.R1 >= mathEquationGameActivity2.B.size()) {
                            break;
                        }
                        MathEquationGameActivity mathEquationGameActivity3 = MathEquationGameActivity.this;
                        int number = mathEquationGameActivity3.B.get(mathEquationGameActivity3.R1).getNumber();
                        MathEquationGameActivity mathEquationGameActivity4 = MathEquationGameActivity.this;
                        if (number == mathEquationGameActivity4.E && !mathEquationGameActivity4.z) {
                            mathEquationGameActivity4.A.playSound(mathEquationGameActivity4.B.get(mathEquationGameActivity4.R1).getSound());
                        }
                        MathEquationGameActivity mathEquationGameActivity5 = MathEquationGameActivity.this;
                        mathEquationGameActivity5.numAnimation(mathEquationGameActivity5.q);
                        mathEquationGameActivity = MathEquationGameActivity.this;
                        i = mathEquationGameActivity.R1 + 1;
                    }
                    MathEquationGameActivity mathEquationGameActivity6 = MathEquationGameActivity.this;
                    if (mathEquationGameActivity6.z) {
                        mathEquationGameActivity6.A.StopMp();
                    }
                }
            }, 3000);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    if (!mathEquationGameActivity.z) {
                        mathEquationGameActivity.A.playSound(R.raw.equals);
                    }
                    MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                    mathEquationGameActivity2.numAnimation(mathEquationGameActivity2.o);
                    MathEquationGameActivity mathEquationGameActivity3 = MathEquationGameActivity.this;
                    if (mathEquationGameActivity3.z) {
                        mathEquationGameActivity3.A.StopMp();
                    }
                }
            }, 4000);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    if (mathEquationGameActivity.J == 1) {
                        mathEquationGameActivity.S = 0;
                        while (true) {
                            MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                            if (mathEquationGameActivity2.S < mathEquationGameActivity2.B.size()) {
                                MathEquationGameActivity mathEquationGameActivity3 = MathEquationGameActivity.this;
                                int number = mathEquationGameActivity3.B.get(mathEquationGameActivity3.S).getNumber();
                                MathEquationGameActivity mathEquationGameActivity4 = MathEquationGameActivity.this;
                                if (number == mathEquationGameActivity4.F) {
                                    if (!mathEquationGameActivity4.z) {
                                        mathEquationGameActivity4.A.playSound(mathEquationGameActivity4.B.get(mathEquationGameActivity4.S).getSound());
                                    }
                                    MathEquationGameActivity mathEquationGameActivity5 = MathEquationGameActivity.this;
                                    mathEquationGameActivity5.numAnimation(mathEquationGameActivity5.r);
                                }
                                MathEquationGameActivity.this.S++;
                            } else {
                                return;
                            }
                        }
                    } else {
                        mathEquationGameActivity.S = 0;
                        while (true) {
                            MathEquationGameActivity mathEquationGameActivity6 = MathEquationGameActivity.this;
                            if (mathEquationGameActivity6.S < mathEquationGameActivity6.B.size()) {
                                MathEquationGameActivity mathEquationGameActivity7 = MathEquationGameActivity.this;
                                int number2 = mathEquationGameActivity7.B.get(mathEquationGameActivity7.S).getNumber();
                                MathEquationGameActivity mathEquationGameActivity8 = MathEquationGameActivity.this;
                                if (number2 == mathEquationGameActivity8.G) {
                                    if (!mathEquationGameActivity8.z) {
                                        mathEquationGameActivity8.A.playSound(mathEquationGameActivity8.B.get(mathEquationGameActivity8.S).getSound());
                                    }
                                    MathEquationGameActivity mathEquationGameActivity9 = MathEquationGameActivity.this;
                                    mathEquationGameActivity9.numAnimation(mathEquationGameActivity9.r);
                                }
                                MathEquationGameActivity.this.S++;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }, 5000);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                    if (!mathEquationGameActivity.z) {
                        mathEquationGameActivity.A.playSound(R.raw.yuhhu);
                    }
                    MathEquationGameActivity.this.handler.postDelayed(new Runnable() {
                        public void run() {
                            MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                            if (!mathEquationGameActivity.z) {
                                mathEquationGameActivity.A.playSound(R.raw.submarine_anim);
                            }
                            MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                            mathEquationGameActivity2.subBodyGone(mathEquationGameActivity2.y);
                        }
                    }, 2000);
                }
            }, 5500);
        }
    }

    
    public void playSound(FrameLayout frameLayout) {
        if (!this.z) {
            switch (frameLayout.getId()) {
                case R.id.FramOp1:
                    this.A.playSound(this.C.get(0).getSound());
                    return;
                case R.id.FramOp2:
                    this.A.playSound(this.C.get(1).getSound());
                    return;
                case R.id.FramOp3:
                    this.A.playSound(this.C.get(2).getSound());
                    return;
                default:
                    return;
            }
        }
    }

    
    public void popupItem() {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                MathEquationGameActivity.this.v.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.s.setVisibility(View.VISIBLE);
                MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                if (!mathEquationGameActivity.z) {
                    mathEquationGameActivity.A.playSound(R.raw.wordpop);
                }
            }
        }, 1200);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                MathEquationGameActivity.this.w.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.t.setVisibility(View.VISIBLE);
                MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                if (!mathEquationGameActivity.z) {
                    mathEquationGameActivity.A.playSound(R.raw.wordpop);
                }
            }
        }, 1700);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                MathEquationGameActivity.this.x.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.u.setVisibility(View.VISIBLE);
                MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                if (!mathEquationGameActivity.z) {
                    mathEquationGameActivity.A.playSound(R.raw.wordpop);
                }
                MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                if (!mathEquationGameActivity2.z) {
                    mathEquationGameActivity2.A.playSound(R.raw.lets_start);
                }
                MathEquationGameActivity mathEquationGameActivity3 = MathEquationGameActivity.this;
                if (!mathEquationGameActivity3.I) {
                    mathEquationGameActivity3.f.setVisibility(View.VISIBLE);
                    MathEquationGameActivity.this.f.setClickable(false);
                    Animation loadAnimation = AnimationUtils.loadAnimation(MathEquationGameActivity.this.getApplicationContext(), R.anim.zoom_in_out_low);
                    loadAnimation.setDuration(500);
                    MathEquationGameActivity.this.f.startAnimation(loadAnimation);
                }
                MathEquationGameActivity.this.s.setClickable(true);
                MathEquationGameActivity.this.t.setClickable(true);
                MathEquationGameActivity.this.u.setClickable(true);
            }
        }, 2200);
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adView);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.H.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setEquation() {
        this.J = new Random().nextInt(2) + 1;
        this.D = new Random().nextInt(9) + 1;
        this.E = new Random().nextInt(10) + 1;
        int i2 = this.J;
        if (i2 == 1) {
            this.n.setImageResource(R.drawable.plus);
            int i3 = this.D;
            int i4 = this.E;
            int i5 = i3 + i4;
            this.F = i5;
            if (i3 != i4) {
                additionOfFirstCase(i5);
            } else if (i3 == i4) {
                additionOfSecondCase(i5);
            }
        } else if (i2 == 2) {
            this.n.setImageResource(R.drawable.number_additiongame_minus);
            int i6 = this.D;
            int i7 = this.E;
            if (i6 > i7) {
                int i8 = i6 - i7;
                this.G = i8;
                subtractionOfFirstCase(i8);
            } else if (i6 < i7) {
                int i9 = i6 + i7;
                int i10 = i9 - i7;
                this.E = i10;
                int i11 = i9 - i10;
                this.D = i11;
                int i12 = i11 - i10;
                this.G = i12;
                subtractionOfFirstCase(i12);
            } else if (i6 == i7) {
                int i13 = i6 + 1;
                this.D = i13;
                int i14 = i13 - i7;
                this.G = i14;
                subtractionOfFirstCase(i14);
            }
        }
        this.h.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.i.setColorFilter(getResources().getColor(R.color.imageNamecolor));
        this.j.setColorFilter(getResources().getColor(R.color.imageNamecolor));
    }

    private void setId() {
        this.f4740a = (ImageView) findViewById(R.id.homeBtn);
        this.h = (ImageView) findViewById(R.id.num1);
        this.i = (ImageView) findViewById(R.id.num2);
        this.j = (ImageView) findViewById(R.id.result);
        this.p = (ImageView) findViewById(R.id.num1Real);
        this.q = (ImageView) findViewById(R.id.num2Real);
        this.r = (ImageView) findViewById(R.id.resultReal);
        this.k = (ImageView) findViewById(R.id.opt1);
        this.l = (ImageView) findViewById(R.id.opt2);
        this.m = (ImageView) findViewById(R.id.opt3);
        this.f4741b = (ImageView) findViewById(R.id.fish1);
        this.f4742c = (ImageView) findViewById(R.id.fish2);
        this.f4743d = (ImageView) findViewById(R.id.fish3);
        this.e = (ImageView) findViewById(R.id.fish4);
        this.s = (ImageView) findViewById(R.id.bubble1);
        this.t = (ImageView) findViewById(R.id.bubble2);
        this.u = (ImageView) findViewById(R.id.bubble3);
        this.n = (ImageView) findViewById(R.id.oprator);
        this.f = (ImageView) findViewById(R.id.hintHand);
        this.o = (ImageView) findViewById(R.id.equal);
        this.g = (ImageView) findViewById(R.id.hint2Hand);
        this.y = (ConstraintLayout) findViewById(R.id.subBodyLayout);
        this.v = (FrameLayout) findViewById(R.id.FramOp1);
        this.w = (FrameLayout) findViewById(R.id.FramOp2);
        this.x = (FrameLayout) findViewById(R.id.FramOp3);
    }

    private void setNumberDrag() {
        this.v.setOnTouchListener(new MyTouchListener());
        this.w.setOnTouchListener(new MyTouchListener());
        this.x.setOnTouchListener(new MyTouchListener());
        this.h.setOnDragListener(new MyDragListener());
        this.i.setOnDragListener(new MyDragListener());
        this.j.setOnDragListener(new MyDragListener());
    }

    
    public void starAnimate(View view2) {
        new ParticleSystem((Activity) this, 100, (int) R.drawable.effect_star2, 600).setSpeedRange(0.15f, 0.5f).oneShot(view2, 10);
    }

    private void startGame() {
        ArrayList<NumberModel> arrayList = new ArrayList<>();
        this.B = arrayList;
        arrayList.clear();
        this.B.add(new NumberModel(R.drawable.a1, 0, R.raw.n_1));
        this.B.add(new NumberModel(R.drawable.a1, 1, R.raw.n_1));
        this.B.add(new NumberModel(R.drawable.a2, 2, R.raw.n_2));
        this.B.add(new NumberModel(R.drawable.a3, 3, R.raw.n_3));
        this.B.add(new NumberModel(R.drawable.a4, 4, R.raw.n_4));
        this.B.add(new NumberModel(R.drawable.a5, 5, R.raw.n_5));
        this.B.add(new NumberModel(R.drawable.a6, 6, R.raw.n_6));
        this.B.add(new NumberModel(R.drawable.a7, 7, R.raw.n_7));
        this.B.add(new NumberModel(R.drawable.a8, 8, R.raw.n_8));
        this.B.add(new NumberModel(R.drawable.a9, 9, R.raw.n_9));
        this.B.add(new NumberModel(R.drawable.a10, 10, R.raw.n_10));
        this.B.add(new NumberModel(R.drawable.a11, 11, R.raw.n_11));
        this.B.add(new NumberModel(R.drawable.a12, 12, R.raw.n_12));
        this.B.add(new NumberModel(R.drawable.a13, 13, R.raw.n_13));
        this.B.add(new NumberModel(R.drawable.a14, 14, R.raw.n_14));
        this.B.add(new NumberModel(R.drawable.a15, 15, R.raw.n_15));
        this.B.add(new NumberModel(R.drawable.a16, 16, R.raw.n_16));
        this.B.add(new NumberModel(R.drawable.a17, 17, R.raw.n_17));
        this.B.add(new NumberModel(R.drawable.a18, 18, R.raw.n_18));
        this.B.add(new NumberModel(R.drawable.a19, 19, R.raw.n_19));
        this.B.add(new NumberModel(R.drawable.a20, 20, R.raw.n_20));
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.f4740a.setOnClickListener(this);
        this.f4741b.setOnClickListener(this);
        this.f4742c.setOnClickListener(this);
        this.f4743d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f4741b.setVisibility(View.VISIBLE);
        this.f4742c.setVisibility(View.VISIBLE);
        this.f4743d.setVisibility(View.VISIBLE);
        this.e.setVisibility(View.VISIBLE);
        this.f.setVisibility(View.INVISIBLE);
        this.g.setVisibility(View.INVISIBLE);
        this.y.setVisibility(View.INVISIBLE);
        this.v.setEnabled(false);
        this.w.setEnabled(false);
        this.x.setEnabled(false);
        this.v.setVisibility(View.INVISIBLE);
        this.w.setVisibility(View.INVISIBLE);
        this.x.setVisibility(View.INVISIBLE);
        this.s.setVisibility(View.INVISIBLE);
        this.t.setVisibility(View.INVISIBLE);
        this.u.setVisibility(View.INVISIBLE);
        this.f.setOnClickListener(this);
        this.f.setClickable(false);
        this.s.setClickable(false);
        this.t.setClickable(false);
        this.u.setClickable(false);
        setEquation();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                MathEquationGameActivity.this.y.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.A.StopMp();
                MathEquationGameActivity.this.mp.StopMp();
                MathEquationGameActivity mathEquationGameActivity = MathEquationGameActivity.this;
                if (!mathEquationGameActivity.z) {
                    mathEquationGameActivity.mp.playSound(R.raw.submarine_anim);
                }
                MathEquationGameActivity mathEquationGameActivity2 = MathEquationGameActivity.this;
                mathEquationGameActivity2.subBodyCome(mathEquationGameActivity2.y);
            }
        }, 1500);
    }

    private void startOneShotParticle(View view2) {
        new ParticleSystem((Activity) this, 10, (int) R.drawable.bubble, 600).setSpeedRange(0.45f, 0.5f).oneShot(view2, 10);
    }

    
    public void subBodyCome(View view2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(3000.0f, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                MathEquationGameActivity.this.A.StopMp();
                MathEquationGameActivity.this.handler.postDelayed(new Runnable() {
                    public void run() {
                        MathEquationGameActivity.this.popupItem();
                    }
                }, 500);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                MathEquationGameActivity.this.h.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.i.setVisibility(View.VISIBLE);
                MathEquationGameActivity.this.j.setVisibility(View.VISIBLE);
            }
        });
    }

    
    public void subBodyGone(View view2) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -3200.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view2.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                MathEquationGameActivity.this.gameRestart();
            }

            public void onAnimationRepeat(Animation animation) {
                Log.e("ds","");
            }

            public void onAnimationStart(Animation animation) {
                Log.e("ds","");
            }
        });
    }

    private void subtractionOfFifthCase(int i2) {
        this.L = 0;
        while (this.L < this.B.size()) {
            if (this.B.get(this.L).getNumber() == i2) {
                this.i.setImageResource(this.B.get(this.L).getImg());
                this.j.setImageResource(this.B.get(this.L).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(this.L));
                this.C.add(this.B.get(this.L));
                this.i.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.j.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.q.setImageResource(this.B.get(this.L).getImg());
                this.r.setImageResource(this.B.get(this.L).getImg());
            }
            this.L++;
        }
        this.M = 0;
        while (this.M < this.B.size()) {
            if (this.B.get(this.M).getNumber() == this.D) {
                this.h.setImageResource(this.B.get(this.M).getImg());
                ArrayList<NumberModel> arrayList2 = new ArrayList<>();
                this.C = arrayList2;
                arrayList2.clear();
                this.C.add(this.B.get(this.M));
                this.h.setTag(Integer.valueOf(this.B.get(this.M).getImg()));
                this.p.setImageResource(this.B.get(this.M).getImg());
            }
            this.M++;
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
    }

    private void subtractionOfFirstCase(int i2) {
        for (int i3 = 0; i3 < this.B.size(); i3++) {
            if (this.B.get(i3).getNumber() == this.D) {
                this.h.setImageResource(this.B.get(i3).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(i3));
                this.h.setTag(Integer.valueOf(this.B.get(i3).getImg()));
                this.p.setImageResource(this.B.get(i3).getImg());
            }
        }
        for (int i4 = 0; i4 < this.B.size(); i4++) {
            if (this.B.get(i4).getNumber() == this.E) {
                this.i.setImageResource(this.B.get(i4).getImg());
                this.C.add(this.B.get(i4));
                this.i.setTag(Integer.valueOf(this.B.get(i4).getImg()));
                this.q.setImageResource(this.B.get(i4).getImg());
            }
        }
        for (int i5 = 0; i5 < this.B.size(); i5++) {
            if (this.B.get(i5).getNumber() == i2) {
                this.j.setImageResource(this.B.get(i5).getImg());
                this.C.add(this.B.get(i5));
                this.j.setTag(Integer.valueOf(this.B.get(i5).getImg()));
                this.r.setImageResource(this.B.get(i5).getImg());
            }
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
    }

    private void subtractionOfFourthCase(int i2) {
        this.L = 0;
        while (this.L < this.B.size()) {
            if (this.B.get(this.L).getNumber() == i2) {
                this.h.setImageResource(this.B.get(this.L).getImg());
                this.j.setImageResource(this.B.get(this.L).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(this.L));
                this.C.add(this.B.get(this.L));
                this.h.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.j.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.p.setImageResource(this.B.get(this.L).getImg());
                this.r.setImageResource(this.B.get(this.L).getImg());
            }
            this.L++;
        }
        this.M = 0;
        while (this.M < this.B.size()) {
            if (this.B.get(this.M).getNumber() == this.E) {
                this.i.setImageResource(this.B.get(this.M).getImg());
                ArrayList<NumberModel> arrayList2 = new ArrayList<>();
                this.C = arrayList2;
                arrayList2.clear();
                this.C.add(this.B.get(this.M));
                this.i.setTag(Integer.valueOf(this.B.get(this.M).getImg()));
                this.q.setImageResource(this.B.get(this.M).getImg());
            }
            this.M++;
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
    }

    private void subtractionOfThirdCase(int i2) {
        this.L = 0;
        while (this.L < this.B.size()) {
            if (this.B.get(this.L).getNumber() == this.D) {
                this.h.setImageResource(this.B.get(this.L).getImg());
                this.i.setImageResource(this.B.get(this.L).getImg());
                ArrayList<NumberModel> arrayList = new ArrayList<>();
                this.C = arrayList;
                arrayList.clear();
                this.C.add(this.B.get(this.L));
                this.C.add(this.B.get(this.L));
                this.h.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.i.setTag(Integer.valueOf(this.B.get(this.L).getImg()));
                this.p.setImageResource(this.B.get(this.L).getImg());
                this.q.setImageResource(this.B.get(this.L).getImg());
            }
            this.L++;
        }
        this.K = 0;
        while (this.K < this.B.size()) {
            if (this.B.get(this.K).getNumber() == i2) {
                this.j.setImageResource(this.B.get(this.K).getImg());
                this.C.add(this.B.get(this.K));
                this.j.setTag(Integer.valueOf(this.B.get(this.K).getImg()));
                this.r.setImageResource(this.B.get(this.K).getImg());
            }
            this.K++;
        }
        this.k.setImageResource(this.C.get(0).getImg());
        this.l.setImageResource(this.C.get(1).getImg());
        this.m.setImageResource(this.C.get(2).getImg());
        this.v.setTag(Integer.valueOf(this.C.get(0).getImg()));
        this.w.setTag(Integer.valueOf(this.C.get(1).getImg()));
        this.x.setTag(Integer.valueOf(this.C.get(2).getImg()));
    }

    public void onBackPressed() {
        this.A.StopMp();
        this.mp.StopMp();
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        finish();
        MyConstant.showNewApp = true;
    }

    public void onClick(View view2) {
        this.f.clearAnimation();
        this.f.setVisibility(View.INVISIBLE);
        int id = view2.getId();
        if (id != R.id.homeBtn) {
            switch (id) {
                case R.id.bubble1:
                    startOneShotParticle(this.s);
                    this.s.setVisibility(View.INVISIBLE);
                    this.s.setClickable(false);
                    this.N++;
                    this.A.playSound(R.raw.baloon_blast);
                    break;
                case R.id.bubble2:
                    startOneShotParticle(this.t);
                    this.t.setVisibility(View.INVISIBLE);
                    this.t.setClickable(false);
                    this.N++;
                    this.A.playSound(R.raw.baloon_blast);
                    break;
                case R.id.bubble3:
                    startOneShotParticle(this.u);
                    this.u.setVisibility(View.INVISIBLE);
                    this.N++;
                    this.A.playSound(R.raw.baloon_blast);
                    break;
                default:
                    switch (id) {
                        case R.id.fish1:
                            moveFish(this.f4741b);
                            this.A.playSound(R.raw.boing);
                            break;
                        case R.id.fish2:
                            this.O = true;
                            this.A.playSound(R.raw.boing);
                            moveFish(this.f4742c);
                            break;
                        case R.id.fish3:
                            moveFish(this.f4743d);
                            this.A.playSound(R.raw.yuhhu);
                            break;
                        case R.id.fish4:
                            moveFish(this.e);
                            this.A.playSound(R.raw.effect_confused);
                            break;
                    }
            }
        } else {
            this.A.playSound(R.raw.click);
            clickBounceAnim(view2);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MathEquationGameActivity.this.A.StopMp();
                    MathEquationGameActivity.this.onBackPressed();
                }
            }, 300);
        }
        if (this.N == 3) {
            this.N = 0;
            this.A.playSound(R.raw.dragmatch);
            if (!this.I) {
                this.g.setVisibility(View.VISIBLE);
                hintMove(this.g);
            }
            this.v.setEnabled(true);
            this.w.setEnabled(true);
            this.x.setEnabled(true);
            setNumberDrag();
        }
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_addition_game);
        this.A = new MyMediaPlayer(this);
        this.mp = new MyMediaPlayer(this);
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
        setId();
        startGame();
        this.H = new MyAdView(this);
        
        setAd();
    }

    
    public void onPause() {
        super.onPause();
        this.z = true;
        this.A.StopMp();
    }

    
    public void onResume() {
        super.onResume();
        this.z = false;
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void onStop() {
        super.onStop();
        this.z = true;
        this.handler.removeCallbacksAndMessages(0);
    }
}
