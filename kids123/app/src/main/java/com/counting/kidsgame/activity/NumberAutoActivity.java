package com.counting.kidsgame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import java.util.List;

public class NumberAutoActivity extends Activity implements View.OnClickListener {
    public static final int TYPE_ALPHA = 2;
    public static List<ImageClassABC> imageClassListABC;
    MyMediaPlayer A;


    ImageClassABC f4329a;
    private Animation anim;
    private Animation anim1;

    public Animation anim10;

    public Animation anim11;

    public Animation anim12;

    public Animation anim13;

    public Animation anim14;

    public Animation anim15;

    public Animation anim16;

    public Animation anim17;

    public Animation anim18;

    public Animation anim19;

    public Animation anim2;

    public Animation anim20;

    public Animation anim3;

    public Animation anim4;

    public Animation anim5;

    public Animation anim6;

    public Animation anim7;

    public Animation anim8;

    public Animation anim9;

    public ArrayList<Boolean> animationActiveaArr;
    private ArrayList<Animation> animationArr;


    ImageClassABC f4330b;
    private ImageView btnHome;

    public ImageView btnPlaySong;

    public ImageView btnStartABCDAuto;
    private ImageView btna;

    public ImageView btnb;

    public ImageView btnc;

    public ImageView btnd;

    public ImageView btne;

    public ImageView btnf;

    public ImageView btng;

    public ImageView btnh;

    public ImageView btni;

    public ImageView btnj;

    public ImageView btnk;

    public ImageView btnl;

    public ImageView btnm;

    public ImageView btnn;

    public ImageView btno;

    public ImageView btnp;

    public ImageView btnq;

    public ImageView btnr;

    public ImageView btns;

    public ImageView btnt;


    private NumberAutoActivity f4331c;


    ImageClassABC f4332d;
    ImageClassABC e;
    ImageClassABC f;
    ImageClassABC g;
    ImageClassABC h;
    private Intent i;

    public boolean isABCDAutoPlaying = false;
    private boolean isABCDSelfPlaying = false;

    public boolean isSongPlaying = false;
    ImageClassABC j;
    private int k;
    ImageClassABC l;

    public int length;
    ImageClassABC m;
    private MediaPlayer mp;
    private MediaPlayer mpToy;
    private MyAdView myAdView;
    ImageClassABC n;
    ImageClassABC o;
    ImageClassABC p;
    ImageClassABC q;
    ImageClassABC r;
    ImageClassABC s;
    public SharedPreference settingSp;
    private boolean start = false;
    ImageClassABC t;
    ImageClassABC u;
    ImageClassABC v;
    ImageClassABC w;
    protected boolean x;
    Resources y;
    float z;

    private void checkBeforeABCDSelfPlay(String str, ImageView imageView, Animation animation) {
        if (!this.isSongPlaying && !this.isABCDAutoPlaying) {
            imageView.startAnimation(animation);
            this.isABCDSelfPlaying = true;
        }
    }

    private void checkBeforeStartABCDAuto(ImageView imageView) {
        if (this.isSongPlaying) {
            this.mp.stop();
            this.mp.reset();
            this.mp.release();
            this.mp = null;
            this.length = 0;
            this.isSongPlaying = false;
        } else if (this.isABCDSelfPlaying) {
            this.isABCDSelfPlaying = false;
            clearActiveAnimation();
        }
        boolean z2 = !this.start;
        this.start = z2;
        if (z2) {
            this.A.playSound(R.raw.toy);
            this.isABCDAutoPlaying = true;
            clearActiveAnimation();
            imageView.setImageResource(R.drawable.btn_stop);
            imageView.startAnimation(this.anim);
            this.btna.startAnimation(this.anim1);
            disableClick();
        } else {
            this.A.playSound(R.raw.toy);
            this.isABCDAutoPlaying = false;
            imageView.setImageResource(R.drawable.btn_auto);
            enableClick();
        }
        this.btnPlaySong.setVisibility(View.VISIBLE);
    }

    private void checkBeforeStartSong(ImageView imageView) {
        disableClick();
        if (this.isABCDSelfPlaying) {
            clearActiveAnimation();
            this.isABCDSelfPlaying = false;
        } else if (this.isABCDAutoPlaying) {
            clearActiveAnimation();
            this.isABCDAutoPlaying = false;
        }
        playsong();
    }

    private void clearActiveAnimation() {
    }


    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }


    public void finishActivity() {
        this.A.StopMp(this.mp);
        this.A.StopMp(this.mpToy);
        this.A.StopMp();
        finish();
        MyConstant.showNewApp = true;
    }

    public static boolean isXLargeScreen(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    private void playsong() {
        if (!this.isSongPlaying) {
            this.isABCDAutoPlaying = false;
            this.btnStartABCDAuto.setImageResource(R.drawable.btn_auto);
            this.isSongPlaying = true;
            this.btnPlaySong.startAnimation(this.anim);
            this.A.playSound(R.raw.toy);
            this.A.StopMp(this.mp);
            MediaPlayer create = MediaPlayer.create(this, R.raw.count_song);
            this.mp = create;
            create.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.seekTo(NumberAutoActivity.this.length);
                    mediaPlayer.start();
                    NumberAutoActivity.this.btnPlaySong.setVisibility(View.INVISIBLE);
                }
            });
            this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    int unused = NumberAutoActivity.this.length = 0;
                    NumberAutoActivity.this.enableClick();
                    NumberAutoActivity.this.btnPlaySong.setVisibility(View.VISIBLE);
                    boolean unused2 = NumberAutoActivity.this.isSongPlaying = false;
                }
            });
        }
    }

    private void releaseMemory() {
        this.btna.setImageResource(0);
        this.btna.setImageDrawable((Drawable) null);
        this.btnb.setImageResource(0);
        this.btnb.setImageDrawable((Drawable) null);
        this.btnc.setImageResource(0);
        this.btnc.setImageDrawable((Drawable) null);
        this.btnd.setImageResource(0);
        this.btnd.setImageDrawable((Drawable) null);
        this.btne.setImageResource(0);
        this.btne.setImageDrawable((Drawable) null);
        this.btnf.setImageResource(0);
        this.btnf.setImageDrawable((Drawable) null);
        this.btng.setImageResource(0);
        this.btng.setImageDrawable((Drawable) null);
        this.btnh.setImageResource(0);
        this.btnh.setImageDrawable((Drawable) null);
        this.btni.setImageResource(0);
        this.btni.setImageDrawable((Drawable) null);
        this.btnj.setImageResource(0);
        this.btnj.setImageDrawable((Drawable) null);
        this.btnk.setImageResource(0);
        this.btnk.setImageDrawable((Drawable) null);
        this.btnl.setImageResource(0);
        this.btnl.setImageDrawable((Drawable) null);
        this.btnm.setImageResource(0);
        this.btnm.setImageDrawable((Drawable) null);
        this.btnn.setImageResource(0);
        this.btnn.setImageDrawable((Drawable) null);
        this.btno.setImageResource(0);
        this.btno.setImageDrawable((Drawable) null);
        this.btnp.setImageResource(0);
        this.btnp.setImageDrawable((Drawable) null);
        this.btnq.setImageResource(0);
        this.btnq.setImageDrawable((Drawable) null);
        this.btnr.setImageResource(0);
        this.btnr.setImageDrawable((Drawable) null);
        this.btns.setImageResource(0);
        this.btns.setImageDrawable((Drawable) null);
        this.btnt.setImageResource(0);
        this.btnt.setImageDrawable((Drawable) null);
        this.btna = null;
        this.btnb = null;
        this.btnc = null;
        this.btnd = null;
        this.btne = null;
        this.btnf = null;
        this.btng = null;
        this.btnh = null;
        this.btni = null;
        this.btnj = null;
        this.btnk = null;
        this.btnl = null;
        this.btnm = null;
        this.btnn = null;
        this.btno = null;
        this.btnp = null;
        this.btnq = null;
        this.btnr = null;
        this.btns = null;
        this.btnt = null;
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setAnimationListners() {
        this.anim1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(0, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnb.startAnimation(NumberAutoActivity.this.anim2);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                System.out.println("anim start");
                NumberAutoActivity.this.animationActiveaArr.set(0, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.a));
            }
        });
        this.anim2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(1, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnc.startAnimation(NumberAutoActivity.this.anim3);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(1, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.b));
            }
        });
        this.anim3.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(2, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnd.startAnimation(NumberAutoActivity.this.anim4);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(2, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.c));
            }
        });
        this.anim4.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(3, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btne.startAnimation(NumberAutoActivity.this.anim5);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(3, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.d));
            }
        });
        this.anim5.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(4, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnf.startAnimation(NumberAutoActivity.this.anim6);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(4, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.e));
            }
        });
        this.anim6.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(5, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btng.startAnimation(NumberAutoActivity.this.anim7);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(5, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.f));
            }
        });
        this.anim7.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(6, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnh.startAnimation(NumberAutoActivity.this.anim8);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(6, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.g));
            }
        });
        this.anim8.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(7, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btni.startAnimation(NumberAutoActivity.this.anim9);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(7, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.h));
            }
        });
        this.anim9.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(8, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnj.startAnimation(NumberAutoActivity.this.anim10);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(8, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.i));
            }
        });
        this.anim10.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(9, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnk.startAnimation(NumberAutoActivity.this.anim11);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(9, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.j));
            }
        });
        this.anim11.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(10, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnl.startAnimation(NumberAutoActivity.this.anim12);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(10, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.k));
            }
        });
        this.anim12.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(11, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnm.startAnimation(NumberAutoActivity.this.anim13);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(11, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.l));
            }
        });
        this.anim13.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(12, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnn.startAnimation(NumberAutoActivity.this.anim14);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(12, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.m));
            }
        });
        this.anim14.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(13, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btno.startAnimation(NumberAutoActivity.this.anim15);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(13, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.n));
            }
        });
        this.anim15.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(14, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnp.startAnimation(NumberAutoActivity.this.anim16);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(14, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.o));
            }
        });
        this.anim16.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(15, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnq.startAnimation(NumberAutoActivity.this.anim17);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(15, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.p));
            }
        });
        this.anim17.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(16, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnr.startAnimation(NumberAutoActivity.this.anim18);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(16, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.q));
            }
        });
        this.anim18.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(17, Boolean.TRUE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btns.startAnimation(NumberAutoActivity.this.anim19);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(17, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.r));
            }
        });
        this.anim19.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(18, Boolean.FALSE);
                if (NumberAutoActivity.this.isABCDAutoPlaying) {
                    NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                    if (numberAutoActivity.x) {
                        numberAutoActivity.btnt.startAnimation(NumberAutoActivity.this.anim20);
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(18, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.s));
            }
        });
        this.anim20.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(19, Boolean.FALSE);
                boolean unused = NumberAutoActivity.this.isABCDAutoPlaying = false;
                NumberAutoActivity.this.enableClick();
                NumberAutoActivity.this.btnStartABCDAuto.setImageResource(R.drawable.btn_auto);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                NumberAutoActivity.this.animationActiveaArr.set(19, Boolean.TRUE);
                NumberAutoActivity numberAutoActivity = NumberAutoActivity.this;
                numberAutoActivity.x = numberAutoActivity.isABCDAutoPlaying;
                NumberAutoActivity.this.speakOut(NumberAutoActivity.this.y.getString(R.string.t));
            }
        });
    }


    public void speakOut(String str) {
        int identifier = getBaseContext().getResources().getIdentifier(str, "raw", getBaseContext().getPackageName());
        if (identifier != 0) {
            this.A.StopMp();
            this.A.playSound(identifier);
        }
    }

    public void addAlphaSet() {
        removeListItemABC();
        imageClassListABC.add(this.f4329a);
        imageClassListABC.add(this.f4330b);
        imageClassListABC.add(this.f4332d);
        imageClassListABC.add(this.e);
        imageClassListABC.add(this.f);
        imageClassListABC.add(this.g);
        imageClassListABC.add(this.h);
        imageClassListABC.add(this.j);
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
        imageClassListABC.add(this.w);
    }

    public void createAlphaSet() {
        this.f4329a = new ImageClassABC(R.drawable.a1, R.drawable.b1, R.drawable.b1, R.drawable.obj1, true, R.raw.n_1, true, R.raw.n_1, R.string.alpha_a, R.color.wColor, 10, -10, 0, 2);
        this.f4330b = new ImageClassABC(R.drawable.a2, R.drawable.b2, R.drawable.b2, R.drawable.obj2, true, R.raw.n_2, true, R.raw.n_2, R.string.alpha_b, R.color.bColor, 10, -10, 0, 2);
        this.f4332d = new ImageClassABC(R.drawable.a3, R.drawable.b3, R.drawable.b3, R.drawable.obj3, true, R.raw.n_3, true, R.raw.n_3, R.string.alpha_c, R.color.cColor, 10, -10, 0, 2);
        this.e = new ImageClassABC(R.drawable.a4, R.drawable.b4, R.drawable.b4, R.drawable.obj4, true, R.raw.n_4, true, R.raw.n_4, R.string.alpha_d, R.color.dColor, 10, -10, 0, 2);
        this.f = new ImageClassABC(R.drawable.a5, R.drawable.b5, R.drawable.b5, R.drawable.obj5, true, R.raw.n_5, true, R.raw.n_5, R.string.alpha_e, R.color.mColor, 10, -10, 0, 2);
        this.g = new ImageClassABC(R.drawable.a6, R.drawable.b6, R.drawable.b6, R.drawable.obj6, true, R.raw.n_6, true, R.raw.n_6, R.string.alpha_f, R.color.bColor, 10, -10, 0, 2);
        this.h = new ImageClassABC(R.drawable.a7, R.drawable.b7, R.drawable.b7, R.drawable.obj7, true, R.raw.n_7, true, R.raw.n_7, R.string.alpha_g, R.color.gColor, 10, -10, 0, 2);
        this.j = new ImageClassABC(R.drawable.a8, R.drawable.b8, R.drawable.b8, R.drawable.obj8, true, R.raw.n_8, true, R.raw.n_8, R.string.alpha_h, R.color.iColor, 10, -10, 0, 2);
        this.l = new ImageClassABC(R.drawable.a9, R.drawable.b9, R.drawable.b9, R.drawable.obj9, true, R.raw.n_9, true, R.raw.n_9, R.string.alpha_i, R.color.dColor, 10, -10, 0, 2);
        this.m = new ImageClassABC(R.drawable.a10, R.drawable.b10, R.drawable.b10, R.drawable.obj10, true, R.raw.n_10, true, R.raw.n_10, R.string.alpha_j, R.color.wColor, 10, -10, 0, 2);
        this.n = new ImageClassABC(R.drawable.a11, R.drawable.b11, R.drawable.b11, R.drawable.obj11, true, R.raw.n_11, true, R.raw.n_11, R.string.alpha_k, R.color.jColor, 10, -10, 0, 2);
        this.o = new ImageClassABC(R.drawable.a12, R.drawable.b12, R.drawable.b12, R.drawable.obj12, true, R.raw.n_12, true, R.raw.n_12, R.string.alpha_l, R.color.iColor, 10, -10, 0, 2);
        this.p = new ImageClassABC(R.drawable.a13, R.drawable.b13, R.drawable.b13, R.drawable.obj13, true, R.raw.n_13, true, R.raw.n_13, R.string.alpha_m, R.color.mColor, 10, -10, 0, 2);
        this.q = new ImageClassABC(R.drawable.a14, R.drawable.b14, R.drawable.b14, R.drawable.obj14, true, R.raw.n_14, true, R.raw.n_14, R.string.alpha_n, R.color.dColor, 10, -10, 0, 2);
        this.r = new ImageClassABC(R.drawable.a15, R.drawable.b15, R.drawable.b15, R.drawable.obj15, true, R.raw.n_15, true, R.raw.n_15, R.string.alpha_o, R.color.wColor, 10, -10, 0, 2);
        this.s = new ImageClassABC(R.drawable.a16, R.drawable.b16, R.drawable.b16, R.drawable.obj16, true, R.raw.n_16, true, R.raw.n_16, R.string.alpha_p, R.color.pColor, 10, -10, 0, 2);
        this.t = new ImageClassABC(R.drawable.a17, R.drawable.b17, R.drawable.b17, R.drawable.obj17, true, R.raw.n_17, true, R.raw.n_17, R.string.alpha_q, R.color.oColor, 10, -10, 0, 2);
        this.u = new ImageClassABC(R.drawable.a18, R.drawable.b18, R.drawable.b18, R.drawable.obj18, true, R.raw.n_18, true, R.raw.n_18, R.string.alpha_r, R.color.iColor, 10, -10, 0, 2);
        this.v = new ImageClassABC(R.drawable.a19, R.drawable.b19, R.drawable.b19, R.drawable.obj19, true, R.raw.n_19, true, R.raw.n_19, R.string.alpha_s, R.color.hColor, 10, -10, 0, 2);
        this.w = new ImageClassABC(R.drawable.a20, R.drawable.b20, R.drawable.b20, R.drawable.obj20, true, R.raw.n_20, true, R.raw.n_20, R.string.alpha_t, R.color.gColor, 10, -10, 0, 2);
    }

    public void disableClick() {
        this.btna.setEnabled(false);
        this.btnb.setEnabled(false);
        this.btnc.setEnabled(false);
        this.btnd.setEnabled(false);
        this.btne.setEnabled(false);
        this.btnf.setEnabled(false);
        this.btng.setEnabled(false);
        this.btnh.setEnabled(false);
        this.btni.setEnabled(false);
        this.btnj.setEnabled(false);
        this.btnk.setEnabled(false);
        this.btnl.setEnabled(false);
        this.btnm.setEnabled(false);
        this.btnn.setEnabled(false);
        this.btno.setEnabled(false);
        this.btnp.setEnabled(false);
        this.btnq.setEnabled(false);
        this.btnr.setEnabled(false);
        this.btns.setEnabled(false);
        this.btnt.setEnabled(false);
    }

    public void enableClick() {
        this.btna.setEnabled(true);
        this.btnb.setEnabled(true);
        this.btnc.setEnabled(true);
        this.btnd.setEnabled(true);
        this.btne.setEnabled(true);
        this.btnf.setEnabled(true);
        this.btng.setEnabled(true);
        this.btnh.setEnabled(true);
        this.btni.setEnabled(true);
        this.btnj.setEnabled(true);
        this.btnk.setEnabled(true);
        this.btnl.setEnabled(true);
        this.btnm.setEnabled(true);
        this.btnn.setEnabled(true);
        this.btno.setEnabled(true);
        this.btnp.setEnabled(true);
        this.btnq.setEnabled(true);
        this.btnr.setEnabled(true);
        this.btns.setEnabled(true);
        this.btnt.setEnabled(true);
    }

    public float getFloatvalue() {
        TypedValue typedValue = new TypedValue();
        this.y.getValue(R.dimen.speech_rate, typedValue, true);
        return typedValue.getFloat();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.A.StopMp(this.mp);
        this.A.StopMp(this.mpToy);
        finishActivity();
    }

    public void onClick(View view) {
        String string = this.y.getString(R.string.a);
        String string2 = this.y.getString(R.string.b);
        String string3 = this.y.getString(R.string.c);
        String string4 = this.y.getString(R.string.d);
        String string5 = this.y.getString(R.string.e);
        String string6 = this.y.getString(R.string.f);
        String string7 = this.y.getString(R.string.g);
        String string8 = this.y.getString(R.string.h);
        String string9 = this.y.getString(R.string.i);
        String string10 = this.y.getString(R.string.j);
        String string11 = this.y.getString(R.string.k);
        String string12 = this.y.getString(R.string.l);
        String string13 = this.y.getString(R.string.m);
        String string14 = this.y.getString(R.string.n);
        String str = string;
        String string15 = this.y.getString(R.string.o);
        String str2 = string2;
        String string16 = this.y.getString(R.string.p);
        String str3 = string3;
        String string17 = this.y.getString(R.string.q);
        String str4 = string4;
        String string18 = this.y.getString(R.string.r);
        String str5 = string5;
        String string19 = this.y.getString(R.string.s);
        String str6 = string6;
        String string20 = this.y.getString(R.string.t);
        try {
            int id = view.getId();
            String str7 = string7;
            if (id == R.id.btnPlaySong) {
                checkBeforeStartSong(this.btnPlaySong);
            } else if (id != R.id.btnStart) {
                switch (id) {
                    case R.id.buttona /*2131296464*/:
                        checkBeforeABCDSelfPlay(str, this.btna, this.anim1);
                        return;
                    case R.id.buttonb /*2131296465*/:
                        checkBeforeABCDSelfPlay(str2, this.btnb, this.anim2);
                        return;
                    default:
                        switch (id) {
                            case R.id.buttonc /*2131296468*/:
                                checkBeforeABCDSelfPlay(str3, this.btnc, this.anim3);
                                return;
                            case R.id.buttond /*2131296469*/:
                                checkBeforeABCDSelfPlay(str4, this.btnd, this.anim4);
                                return;
                            case R.id.buttone /*2131296470*/:
                                checkBeforeABCDSelfPlay(str5, this.btne, this.anim5);
                                return;
                            case R.id.buttonf /*2131296471*/:
                                checkBeforeABCDSelfPlay(str6, this.btnf, this.anim6);
                                return;
                            case R.id.buttong /*2131296472*/:
                                checkBeforeABCDSelfPlay(str7, this.btng, this.anim7);
                                return;
                            case R.id.buttonh /*2131296473*/:
                                checkBeforeABCDSelfPlay(string8, this.btnh, this.anim8);
                                return;
                            case R.id.buttoni /*2131296474*/:
                                checkBeforeABCDSelfPlay(string9, this.btni, this.anim9);
                                return;
                            case R.id.buttonj /*2131296475*/:
                                checkBeforeABCDSelfPlay(string10, this.btnj, this.anim10);
                                return;
                            case R.id.buttonk /*2131296476*/:
                                checkBeforeABCDSelfPlay(string11, this.btnk, this.anim11);
                                return;
                            case R.id.buttonl /*2131296477*/:
                                checkBeforeABCDSelfPlay(string12, this.btnl, this.anim12);
                                return;
                            case R.id.buttonm /*2131296478*/:
                                checkBeforeABCDSelfPlay(string13, this.btnm, this.anim13);
                                return;
                            case R.id.buttonn /*2131296479*/:
                                checkBeforeABCDSelfPlay(string14, this.btnn, this.anim14);
                                return;
                            case R.id.buttono /*2131296480*/:
                                checkBeforeABCDSelfPlay(string15, this.btno, this.anim15);
                                return;
                            case R.id.buttonp /*2131296481*/:
                                checkBeforeABCDSelfPlay(string16, this.btnp, this.anim16);
                                return;
                            case R.id.buttonq /*2131296482*/:
                                checkBeforeABCDSelfPlay(string17, this.btnq, this.anim17);
                                return;
                            case R.id.buttonr /*2131296483*/:
                                checkBeforeABCDSelfPlay(string18, this.btnr, this.anim18);
                                return;
                            case R.id.buttons /*2131296484*/:
                                checkBeforeABCDSelfPlay(string19, this.btns, this.anim19);
                                return;
                            case R.id.buttont /*2131296485*/:
                                checkBeforeABCDSelfPlay(string20, this.btnt, this.anim20);
                                return;
                            default:
                                return;
                        }
                }
            } else {
                checkBeforeStartABCDAuto(this.btnStartABCDAuto);
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }
    
    @Override
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
        this.length = 0;
        setContentView(R.layout.number_auto);
        createAlphaSet();
        if (imageClassListABC == null) {
            imageClassListABC = new ArrayList();
        }
        addAlphaSet();
        if (this.settingSp == null) {
            this.settingSp = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
        this.A = new MyMediaPlayer(this);
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();
        speakOut("tap_numbers");
        this.y = getResources();
        this.z = getFloatvalue();
        this.btna = (ImageView) findViewById(R.id.buttona);
        this.btnb = (ImageView) findViewById(R.id.buttonb);
        this.btnc = (ImageView) findViewById(R.id.buttonc);
        this.btnd = (ImageView) findViewById(R.id.buttond);
        this.btne = (ImageView) findViewById(R.id.buttone);
        this.btnf = (ImageView) findViewById(R.id.buttonf);
        this.btng = (ImageView) findViewById(R.id.buttong);
        this.btnh = (ImageView) findViewById(R.id.buttonh);
        this.btni = (ImageView) findViewById(R.id.buttoni);
        this.btnj = (ImageView) findViewById(R.id.buttonj);
        this.btnk = (ImageView) findViewById(R.id.buttonk);
        this.btnl = (ImageView) findViewById(R.id.buttonl);
        this.btnm = (ImageView) findViewById(R.id.buttonm);
        this.btnn = (ImageView) findViewById(R.id.buttonn);
        this.btno = (ImageView) findViewById(R.id.buttono);
        this.btnp = (ImageView) findViewById(R.id.buttonp);
        this.btnq = (ImageView) findViewById(R.id.buttonq);
        this.btnr = (ImageView) findViewById(R.id.buttonr);
        this.btns = (ImageView) findViewById(R.id.buttons);
        this.btnt = (ImageView) findViewById(R.id.buttont);
        this.btnStartABCDAuto = (ImageView) findViewById(R.id.btnStart);
        this.btnPlaySong = (ImageView) findViewById(R.id.btnPlaySong);
        this.btna.setOnClickListener(this);
        this.btnb.setOnClickListener(this);
        this.btnc.setOnClickListener(this);
        this.btnd.setOnClickListener(this);
        this.btne.setOnClickListener(this);
        this.btnf.setOnClickListener(this);
        this.btng.setOnClickListener(this);
        this.btnh.setOnClickListener(this);
        this.btni.setOnClickListener(this);
        this.btnj.setOnClickListener(this);
        this.btnk.setOnClickListener(this);
        this.btnl.setOnClickListener(this);
        this.btnm.setOnClickListener(this);
        this.btnn.setOnClickListener(this);
        this.btno.setOnClickListener(this);
        this.btnp.setOnClickListener(this);
        this.btnq.setOnClickListener(this);
        this.btnr.setOnClickListener(this);
        this.btns.setOnClickListener(this);
        this.btnt.setOnClickListener(this);
        this.btna.setImageResource(R.drawable.b1);
        this.btnb.setImageResource(R.drawable.b2);
        this.btnc.setImageResource(R.drawable.b3);
        this.btnd.setImageResource(R.drawable.b4);
        this.btne.setImageResource(R.drawable.b5);
        this.btnf.setImageResource(R.drawable.b6);
        this.btng.setImageResource(R.drawable.b7);
        this.btnh.setImageResource(R.drawable.b8);
        this.btni.setImageResource(R.drawable.b9);
        this.btnj.setImageResource(R.drawable.b10);
        this.btnk.setImageResource(R.drawable.b11);
        this.btnl.setImageResource(R.drawable.b12);
        this.btnm.setImageResource(R.drawable.b13);
        this.btnn.setImageResource(R.drawable.b14);
        this.btno.setImageResource(R.drawable.b15);
        this.btnp.setImageResource(R.drawable.b16);
        this.btnq.setImageResource(R.drawable.b17);
        this.btnr.setImageResource(R.drawable.b18);
        this.btns.setImageResource(R.drawable.b19);
        this.btnt.setImageResource(R.drawable.b20);
        this.btnStartABCDAuto.setOnClickListener(this);
        this.btnPlaySong.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NumberAutoActivity.this.clickBounceAnim(view);
                System.out.println("showInterstitial animal.....");
                NumberAutoActivity.this.A.StopMp();
                NumberAutoActivity.this.playClickSound();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        NumberAutoActivity.this.finishActivity();
                    }
                }, 500);
            }
        });
        this.animationActiveaArr = new ArrayList<>();
        for (int i2 = 0; i2 < 26; i2++) {
            this.animationActiveaArr.add(Boolean.FALSE);
        }
        this.myAdView = new MyAdView(this);

        setAd();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.A.StopMp(this.mp);
        this.A.StopMp(this.mpToy);
        releaseMemory();
        System.gc();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.A.StopMp(this.mp);
        this.A.StopMp(this.mpToy);
        finishActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.f4331c = this;
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.anim = loadAnimation;
        loadAnimation.setDuration(320);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim1 = loadAnimation2;
        loadAnimation2.setFillAfter(true);
        Animation loadAnimation3 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim2 = loadAnimation3;
        loadAnimation3.setFillAfter(true);
        Animation loadAnimation4 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim3 = loadAnimation4;
        loadAnimation4.setFillAfter(true);
        Animation loadAnimation5 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim4 = loadAnimation5;
        loadAnimation5.setFillAfter(true);
        Animation loadAnimation6 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim5 = loadAnimation6;
        loadAnimation6.setFillAfter(true);
        Animation loadAnimation7 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim6 = loadAnimation7;
        loadAnimation7.setFillAfter(true);
        Animation loadAnimation8 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim7 = loadAnimation8;
        loadAnimation8.setFillAfter(true);
        Animation loadAnimation9 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim8 = loadAnimation9;
        loadAnimation9.setFillAfter(true);
        Animation loadAnimation10 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim9 = loadAnimation10;
        loadAnimation10.setFillAfter(true);
        Animation loadAnimation11 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim10 = loadAnimation11;
        loadAnimation11.setFillAfter(true);
        Animation loadAnimation12 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim11 = loadAnimation12;
        loadAnimation12.setFillAfter(true);
        Animation loadAnimation13 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim12 = loadAnimation13;
        loadAnimation13.setFillAfter(true);
        Animation loadAnimation14 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim13 = loadAnimation14;
        loadAnimation14.setFillAfter(true);
        Animation loadAnimation15 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim14 = loadAnimation15;
        loadAnimation15.setFillAfter(true);
        Animation loadAnimation16 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim15 = loadAnimation16;
        loadAnimation16.setFillAfter(true);
        Animation loadAnimation17 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim16 = loadAnimation17;
        loadAnimation17.setFillAfter(true);
        Animation loadAnimation18 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim17 = loadAnimation18;
        loadAnimation18.setFillAfter(true);
        Animation loadAnimation19 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim18 = loadAnimation19;
        loadAnimation19.setFillAfter(true);
        Animation loadAnimation20 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim19 = loadAnimation20;
        loadAnimation20.setFillAfter(true);
        Animation loadAnimation21 = AnimationUtils.loadAnimation(this.f4331c, R.anim.zoom);
        this.anim20 = loadAnimation21;
        loadAnimation21.setFillAfter(true);
        ArrayList<Animation> arrayList = new ArrayList<>();
        this.animationArr = arrayList;
        arrayList.add(this.anim1);
        this.animationArr.add(this.anim2);
        this.animationArr.add(this.anim3);
        this.animationArr.add(this.anim4);
        this.animationArr.add(this.anim5);
        this.animationArr.add(this.anim6);
        this.animationArr.add(this.anim7);
        this.animationArr.add(this.anim8);
        this.animationArr.add(this.anim9);
        this.animationArr.add(this.anim10);
        this.animationArr.add(this.anim11);
        this.animationArr.add(this.anim12);
        this.animationArr.add(this.anim13);
        this.animationArr.add(this.anim14);
        this.animationArr.add(this.anim15);
        this.animationArr.add(this.anim16);
        this.animationArr.add(this.anim17);
        this.animationArr.add(this.anim18);
        this.animationArr.add(this.anim19);
        this.animationArr.add(this.anim20);
        setAnimationListners();
    }

    public void playClickSound() {
        this.A.StopMp();
        this.A.playSound(R.raw.click);
    }

    public void removeListItemABC() {
        List<ImageClassABC> list = imageClassListABC;
        list.removeAll(list);
    }
}
