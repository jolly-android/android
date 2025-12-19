package com.counting.kidsgame.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.counting.kidsgame.Config;
import com.counting.kidsgame.CustomArrayAdapter;
import com.counting.kidsgame.CustomData;
import com.counting.kidsgame.FlashCardABC;
import com.counting.kidsgame.HorizontalListView;
import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.biggersmaller.BiggerSmallerActivity;
import com.counting.kidsgame.cakebuilding.Number_CakeGameActivity;
import com.counting.kidsgame.drawing.GridActivityColoring;
import com.counting.kidsgame.drawing.GridActivityColoringBook;
import com.counting.kidsgame.farmingcount.Number_Object_CountingActivity;
import com.counting.kidsgame.mathequation.MathEquationGameActivity;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.media.SoundManager;
import com.counting.kidsgame.rocketlaunch.RocketCounDownActivity;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.NetworkStats;
import com.counting.kidsgame.tools.RedirectManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    public static final String TAG = "MainActivity";
    public static final int TYPE_21 = 3;
    public static final int TYPE_22 = 4;
    public static int colingBookID;
    public static MainActivity context;


    public static Boolean isBuy = Boolean.FALSE;
    public static boolean mute;

    public static int myRandomNo;
    public static int myRandomNo1;
    public static int random;

    public static SharedPreference sharedPreference;


    ImageView f4284a;


    ImageView f4285b;
    private ImageView book;
    private ImageView btnMusicSettings;
    private ImageView btnRate;


    private ImageView caps;


    ImageView f4287d;
    ImageView drawColor;
    Resources e;

    SharedPreferences g;
    MediaPlayer h;
    private ImageView home;
    private Intent i;
    private Intent intent;
    public Boolean isProblem = Boolean.FALSE;
    public boolean isRateDialogeShow = false;
    Typeface j;


    boolean m;
    private CustomData[] mCustomData;
    private HorizontalListView mHlvCustomList;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private Animation myAnim;

    public MyMediaPlayer myMediaPlayer;

    boolean n;
    CustomArrayAdapter o;
    int p;
    int q;
    int r;
    private LinearLayout scrollView;
    public SharedPreference settingSp;
    public SharedPreference sharedPreference_isShowNewApp;

    public SharedPreference sharedPreference_never;

    private Animation zoom;


    public void animateClick(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view.startAnimation(loadAnimation);
    }


    public static int getRandomAd(int i16) {
        random = new Random().nextInt(i16);
        PrintStream printStream = System.out;
        printStream.println("random--" + random);
        return random;
    }

    private void instializeMusic() {
        try {
            MediaPlayer create = MediaPlayer.create(this, R.raw.number_main);
            this.h = create;
            create.setAudioStreamType(3);
        } catch (Exception unused) {
        }
    }


    public void launchGameActivity(int i16) {
        Toast toast = null;
        switch (i16) {
            case 0:
                this.intent = new Intent(this, NumberAutoActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 1:
                this.intent = new Intent(this, FlashCardABC.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 2:
                this.intent = new Intent(this, NumberFindActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 3:
                this.intent = new Intent(this, NumberSameActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 4:
                this.intent = new Intent(this, NumberSameDragMatch.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 5:
                this.intent = new Intent(this, NumberSequence.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 6:
                playClickSound();
                colingBookID = 3;
                myRandomNo = getRandomAd(15);
                this.i = new Intent(this, GridActivityColoring.class);
                overridePendingTransition(0, 0);
                startActivity(this.i);
                return;
            case 7:
                this.intent = new Intent(this, SequenceGameActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 8:
                this.intent = new Intent(this, ChooseGameActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 9:
                this.intent = new Intent(this, NumberDragMatch.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 10:
                this.intent = new Intent(this, NumberMatchActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 11:
                this.intent = new Intent(this, NumberSortingActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 12:
                this.intent = new Intent(this, NumberTypingActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 13:
                this.intent = new Intent(this, GridActivityColoringBook.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 14:
                this.intent = new Intent(this, Number_Object_CountingActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 15:
                this.intent = new Intent(this, MathEquationGameActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
                return;
            case 16:
                this.intent = new Intent(this, Number_CakeGameActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
//                if (SharedPreference.getBuyChoise(this) > 0 || this.settingSp.getLoveCatLocked(this)) {
//                    overridePendingTransition(0, 0);
//                    startActivity(this.intent);
//                    return;
//                }
//                if (this.r == 3) {
//                    toast = Toast.makeText(this, "Please comeback after 3 days", Toast.LENGTH_SHORT);
//                }
//                if (this.r == 2) {
//                    toast = Toast.makeText(this, "Please comeback after 2 days", Toast.LENGTH_SHORT);
//                }
//                if (this.r == 1) {
//                    toast = Toast.makeText(this, "Please comeback after 1 day", Toast.LENGTH_SHORT);
//                }
//                if (toast != null) {
//                    toast.setGravity(17, 0, 0);
//                    toast.show();
//                    return;
//                }
                return;
            case 17:
                this.intent = new Intent(this, BiggerSmallerActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
//                if (SharedPreference.getBuyChoise(this) > 0 || this.settingSp.getrealCatLocked(this)) {
//                    overridePendingTransition(0, 0);
//                    startActivity(this.intent);
//                    return;
//                }
//                if (this.p == 3) {
//                    toast = Toast.makeText(this, "Please comeback after 3 days", Toast.LENGTH_SHORT);
//                }
//                if (this.p == 2) {
//                    toast = Toast.makeText(this, "Please comeback after 2 days", Toast.LENGTH_SHORT);
//                }
//                if (this.p == 1) {
//                    toast = Toast.makeText(this, "Please comeback after 1 day", Toast.LENGTH_SHORT);
//                }
//                if (toast != null) {
//                    toast.setGravity(17, 0, 0);
//                    toast.show();
//                    return;
//                }
                return;
            case 18:
                this.intent = new Intent(this, RocketCounDownActivity.class);
                overridePendingTransition(0, 0);
                startActivity(this.intent);
//                if (SharedPreference.getBuyChoise(this) > 0 || this.settingSp.getcuteCatLocked(this)) {
//                    overridePendingTransition(0, 0);
//                    startActivity(this.intent);
//                    return;
//                }
//                if (this.q == 3) {
//                    toast = Toast.makeText(this, "Please comeback after 3 days", Toast.LENGTH_SHORT);
//                }
//                if (this.q == 2) {
//                    toast = Toast.makeText(this, "Please comeback after 2 days", Toast.LENGTH_SHORT);
//                }
//                if (this.q == 1) {
//                    toast = Toast.makeText(this, "Please comeback after 1 day", Toast.LENGTH_SHORT);
//                }
//                if (toast != null) {
//                    toast.setGravity(17, 0, 0);
//                    toast.show();
//                    return;
//                }
                return;


            default:
                return;
        }
    }


    private void setupCustomLists() {
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, this.mCustomData);
        this.o = customArrayAdapter;
        final int length = this.mCustomData.length;
        this.mHlvCustomList.setAdapter((ListAdapter) customArrayAdapter);
        this.mHlvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.menuanim));
                if (i == length - 1) {
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    Toast.makeText(MainActivity.this, R.string.long_press, Toast.LENGTH_LONG).show();
                    return;
                }
                MainActivity.this.playClickSound();
                MainActivity.this.launchGameActivity(i);
            }
        });
        this.mHlvCustomList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != length - 1) {
                    return false;
                }
                MainActivity.this.playClickSound();
                MainActivity.this.launchGameActivity(i);
                return false;
            }
        });
        this.scrollView.setEnabled(false);
        this.mHlvCustomList.setFocusable(false);
        this.o.notifyDataSetChanged();
        this.scrollView.setEnabled(true);
    }

    private void setvalueMusic() {
        boolean settingMusic = this.settingSp.getSettingMusic(this);
        MyConstant.MUSIC_SETTING = settingMusic;
        if (settingMusic == MyConstant.MUSIC_ON) {
            this.btnMusicSettings.setImageResource(R.drawable.sound_on);
            startMainMusic();
            return;
        }
        this.btnMusicSettings.setImageResource(R.drawable.sound_off);
        pauseMainMusic();
    }

    public static void shareApp() {
        String packageName = context.getPackageName();
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.SUBJECT", "123 Numbers Game");
        intent2.putExtra("android.intent.extra.TEXT", "Try this awesome 123 Numbers game: https://play.google.com/store/apps/details?id=" + packageName);
        intent2.setType("text/plain");
        context.startActivity(intent2);
    }

    private void showQuitAppDialog() {
        int screenHeight = DisplayManager.getScreenHeight(this);
        int screenWidth = DisplayManager.getScreenWidth(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.height = screenHeight - (screenHeight / 8);
        layoutParams.width = screenWidth - (screenWidth / 8);
        layoutParams.gravity = 17;
        try {
            final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
            dialog.getWindow().setFlags(8, 8);
            dialog.setContentView(R.layout.dialog_quit);
            RemoveBackButton.hideNavigationDialog(dialog);
            ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);

            ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.animateClick(view);
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.animateClick(view);
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.this.myMediaPlayer.StopMp();
                            dialog.dismiss();
                            MainActivity.this.finishAffinity();
                            Process.killProcess(Process.myPid());
                            System.exit(0);
                        }
                    }, 300);
                }
            });

            dialog.show();
            dialog.getWindow().clearFlags(8);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    MainActivity.this.myMediaPlayer.StopMp();
                    MainActivity.this.myMediaPlayer.playSound(R.raw.byebye);
                }
            }, 200);
        } catch (Exception e2) {
            Log.d(TAG, "showQuitAppDialog: " + e2.toString());
        }
    }

    private void showRateAppDialog() {
        this.isRateDialogeShow = true;
        try {
            final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
            dialog.getWindow().setFlags(8, 8);
            dialog.setContentView(R.layout.dialog_rate);
            RemoveBackButton.hideNavigationDialog(dialog);
            ConstraintLayout constraintLayout = (ConstraintLayout) dialog.findViewById(R.id.bg_dialog);
            Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "english.ttf");
            TextView textView = (TextView) dialog.findViewById(R.id.btn_later);
            ConstraintLayout constraintLayout2 = (ConstraintLayout) dialog.findViewById(R.id.yes);
            TextView textView2 = (TextView) dialog.findViewById(R.id.btn_never);
            ((TextView) dialog.findViewById(R.id.title)).setTypeface(createFromAsset);
            ((TextView) dialog.findViewById(R.id.title2)).setTypeface(createFromAsset);
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoomin_zoomout_coloringbook);
            loadAnimation.setDuration(800);
            constraintLayout2.startAnimation(loadAnimation);
            if (!this.m) {
                this.myMediaPlayer.playSound(R.raw.please);
            }
            constraintLayout2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.animateClick(view);
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    MainActivity.this.sharedPreference_never.save(MainActivity.this, 1);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                            RedirectManager.rateUs(MainActivity.this);
                        }
                    }, 300);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.animateClick(view);
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    dialog.dismiss();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.animateClick(view);
                    MainActivity.this.myMediaPlayer.playSound(R.raw.click);
                    MainActivity.this.sharedPreference_never.save(MainActivity.this, 1);
                    dialog.dismiss();
                }
            });
            if (!isFinishing()) {
                dialog.show();
            }
            dialog.getWindow().clearFlags(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    private void unLockGame1() {
        this.r = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        new Date();
        Date date = new Date();
        try {
            if (!this.settingSp.getappOpenDate(this).equals("")) {
                Date parse = simpleDateFormat.parse(this.settingSp.getappOpenDate(this));
                int convert = (int) TimeUnit.DAYS.convert(date.getTime() - parse.getTime(), TimeUnit.MILLISECONDS);
                if (convert >= 1 && !this.settingSp.getLoveCatLocked(this)) {
                    this.settingSp.saveLoveCatLocked(this, true);
                }
                if (convert == 0) {
                    this.r = 1;
                } else {
                    this.r = convert;
                }
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    private void unLockGame2() {
        this.p = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        new Date();
        Date date = new Date();
        try {
            if (!this.settingSp.getappOpenDate(this).equals("")) {
                Date parse = simpleDateFormat.parse(this.settingSp.getappOpenDate(this));
                int convert = (int) TimeUnit.DAYS.convert(date.getTime() - parse.getTime(), TimeUnit.MILLISECONDS);
                if (convert >= 2 && !this.settingSp.getrealCatLocked(this)) {
                    this.settingSp.saverealCatLocked(this, true);
                }
                this.p = 2 - convert;
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    private void unLockGame3() {
        this.q = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        new Date();
        Date date = new Date();
        try {
            if (!this.settingSp.getappOpenDate(this).equals("")) {
                Date parse = simpleDateFormat.parse(this.settingSp.getappOpenDate(this));
                int convert = (int) TimeUnit.DAYS.convert(date.getTime() - parse.getTime(), TimeUnit.MILLISECONDS);
                if (convert >= 3 && !this.settingSp.getcuteCatLocked(this)) {
                    this.settingSp.saveCuteCatLocked(this, true);
                }
                this.q = 3 - convert;
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }


    public int getPromoteCode() {
        SharedPreferences sharedPreferences = getSharedPreferences("SCORE", 0);
        this.g = sharedPreferences;
        return sharedPreferences.getInt("CODE", 0);
    }

    public void musicSetting() {
        boolean z = MyConstant.MUSIC_SETTING;
        boolean z2 = MyConstant.MUSIC_ON;
        if (z == z2) {
            boolean z3 = MyConstant.MUSIC_OFF;
            MyConstant.MUSIC_SETTING = z3;
            this.settingSp.saveSettingMusic(this, z3);
            setvalueMusic();
            return;
        }
        MyConstant.MUSIC_SETTING = z2;
        this.settingSp.saveSettingMusic(this, z2);
        setvalueMusic();
    }
    
    @Override
    public void onActivityResult(int i16, int i17, Intent intent2) {
        super.onActivityResult(i16, i17, intent2);
        if (i16 == 1111 && i17 != -1) {
            Log.d(TAG, "Update flow failed! Result code: " + i17);
        }
    }
    @Override
    public void onBackPressed() {
        this.myMediaPlayer.StopMp();
        showQuitAppDialog();
    }
    @Override
    public void onClick(View view) {
        if (!this.n) {
            this.n = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    MainActivity.this.n = false;
                }
            }, 1000);
            switch (view.getId()) {
                case R.id.book:
                    this.book.startAnimation(this.myAnim);
                    playClickSound();
                    colingBookID = 3;
                    myRandomNo = getRandomAd(15);
                    this.i = new Intent(this, GridActivityColoringBook.class);
                    overridePendingTransition(0, 0);
                    startActivity(this.i);
                    return;
                case R.id.btnHome:
                    playClickSound();
                    this.home.startAnimation(this.myAnim);
                    Toast.makeText(this, R.string.long_press, Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btnMusicSettings:
                    playClickSound();
                    this.btnMusicSettings.startAnimation(this.myAnim);
                    Toast.makeText(this, R.string.long_press, Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btnRate:
                    playClickSound();
                    this.btnRate.startAnimation(this.myAnim);
                    Toast.makeText(this, R.string.long_press, Toast.LENGTH_SHORT).show();
                    return;


                case R.id.btnShare:
                    playClickSound();
                    this.f4287d.startAnimation(this.myAnim);
                    Toast.makeText(this, R.string.long_press, Toast.LENGTH_SHORT).show();
                    return;
                case R.id.caps:
                    this.caps.startAnimation(this.myAnim);
                    playClickSound();
                    colingBookID = 3;
                    myRandomNo = getRandomAd(15);
                    this.i = new Intent(this, GridActivityColoring.class);
                    overridePendingTransition(0, 0);
                    startActivity(this.i);
                    return;


                case R.id.next:
                    this.f4284a.startAnimation(this.myAnim);
                    playClickSound();
                    this.mHlvCustomList.scrollToLeft();
                    return;
                case R.id.prev:
                    this.f4285b.startAnimation(this.myAnim);
                    playClickSound();
                    this.mHlvCustomList.scrollToRight();
                    return;

                default:
                    return;
            }
        }
    }

    ImageView privacy;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);


        this.isRateDialogeShow = false;
        MyConstant.showNewApp = false;
        setContentView(R.layout.main);
        privacy =findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.privacy_url)));
                startActivity(i);
            }
        });
        context = this;
        this.e = getResources();
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
        if (this.settingSp == null) {
            this.settingSp = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
        this.myMediaPlayer = new MyMediaPlayer(this);
        MyConstant.SOUND_SETTING = this.settingSp.getSettingSound(this);
        MyConstant.MUSIC_SETTING = this.settingSp.getSettingMusic(this);
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();
        this.j = Typeface.createFromAsset(getAssets(), "english.ttf");
        if (this.sharedPreference_never == null) {
            this.sharedPreference_never = new SharedPreference(SharedPreference.PREFS_NAME_NS, SharedPreference.PREFS_KEY_NS);
        }

        if (this.sharedPreference_isShowNewApp == null) {
            this.sharedPreference_isShowNewApp = new SharedPreference(SharedPreference.PREFS_NAME_ISSHOWNEWAPP, SharedPreference.PREFS_KEY_ISSHOWNEWAPP);
        }

        this.myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        this.myAnim.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        System.out.println("calling main activity");
        this.scrollView = (LinearLayout) findViewById(R.id.scrollView);
        this.mHlvCustomList = (HorizontalListView) findViewById(R.id.hlvCustomList);
        this.f4285b = (ImageView) findViewById(R.id.prev);
        this.f4284a = (ImageView) findViewById(R.id.next);
        this.home = (ImageView) findViewById(R.id.btnHome);

        this.f4287d = (ImageView) findViewById(R.id.btnShare);
        this.home.setOnClickListener(this);
        this.home.setOnLongClickListener(this);
        this.f4284a.setOnClickListener(this);
        this.f4285b.setOnClickListener(this);


        this.f4287d.setOnClickListener(this);
        this.f4287d.setOnLongClickListener(this);
        this.f4287d.setVisibility(View.GONE);
        ImageView imageView = (ImageView) findViewById(R.id.caps);
        this.caps = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.book);
        this.book = imageView2;
        imageView2.setOnClickListener(this);

        ImageView imageView4 = (ImageView) findViewById(R.id.btnRate);
        this.btnRate = imageView4;
        imageView4.setOnClickListener(this);
        this.btnRate.setOnLongClickListener(this);


        ImageView imageView6 = (ImageView) findViewById(R.id.btnMusicSettings);
        this.btnMusicSettings = imageView6;
        imageView6.setOnClickListener(this);
        this.btnMusicSettings.setOnLongClickListener(this);
        if (this.sharedPreference_never.getValue(this) == 0) {
            PrintStream printStream = System.out;
            printStream.println("appStartTimes::" + WelcomeActivity.appStartTimes);
            int i16 = WelcomeActivity.appStartTimes;
            if (i16 % 2 == 0 && i16 != 0 && !WelcomeActivity.showRateDialog) {
                WelcomeActivity.showRateDialog = true;
                showRateAppDialog();
            }
        }


        if (this.settingSp.getappOpenDate(this) == null) {
            this.settingSp.saveappOpenDate(this, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        }
        unLockGame1();
        unLockGame2();
        unLockGame3();
        if (SharedPreference.getBuyChoise(this) > 0) {
            this.r = 0;
            this.p = 0;
            this.q = 0;
        }
        this.mCustomData = new CustomData[]{new CustomData(R.drawable.game1, 0), new CustomData(R.drawable.game2, 0), new CustomData(R.drawable.game3, 0), new CustomData(R.drawable.game4, 0), new CustomData(R.drawable.game5, 0), new CustomData(R.drawable.game6, 0), new CustomData(R.drawable.game7, 0), new CustomData(R.drawable.game8, 0), new CustomData(R.drawable.game9, 0), new CustomData(R.drawable.game10, 0), new CustomData(R.drawable.spot_it, 0), new CustomData(R.drawable.sorting, 0), new CustomData(R.drawable.type_game, 0), new CustomData(R.drawable.game11, 0), new CustomData(R.drawable.farming_count, 0), new CustomData(R.drawable.math_eq, 0), new CustomData(R.drawable.cake_fix, this.r), new CustomData(R.drawable.find_it, this.p), new CustomData(R.drawable.rocket, this.q)};
        setupCustomLists();


        setvalueMusic();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        System.out.println("activity destoy");
        this.myMediaPlayer.StopMp();
        this.h.stop();
        super.onDestroy();
    }
    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome:
                playClickSound();
                this.home.startAnimation(this.myAnim);
                this.myMediaPlayer.StopMp();
                showQuitAppDialog();
                break;
            case R.id.btnMusicSettings:
                playClickSound();
                musicSetting();
                break;
            case R.id.btnRate:
                playClickSound();
                RedirectManager.rateUs(this);
                break;


            case R.id.btnShare:
                playClickSound();
                shareApp();
                break;


        }
        return true;
    }


    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mRegistrationBroadcastReceiver);
        pauseMainMusic();
        this.myMediaPlayer.StopMp();
        this.m = true;
    }


    public void onResume() {
        super.onResume();
        this.m = false;
        if (SharedPreference.getBuyChoise(this) > 0) {
            isBuy = Boolean.TRUE;
        }

        unLockGame1();
        unLockGame2();
        unLockGame3();
        if (SharedPreference.getBuyChoise(this) > 0) {
            this.r = 0;
            this.p = 0;
            this.q = 0;
        }

        this.myMediaPlayer.StopMp();
        pauseMainMusic();
        instializeMusic();
        this.o.notifyDataSetChanged();
        if (NetworkStats.isNetworkAvailable(this) && !this.isRateDialogeShow && MyConstant.showNewApp) {
            MyConstant.showNewApp = false;
            if (this.sharedPreference_isShowNewApp.getDialogNoShow(this)) {
                System.err.println("else2");
                MyAdmob.showInterstitial(this);
            } else {
                MyAdmob.showInterstitial(this);
            }
        }
        MyConstant.appstart = false;
        RemoveBackButton.hideBackButtonBar(this);
        startMainMusic();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mRegistrationBroadcastReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));


    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("activity stop");
        this.myMediaPlayer.StopMp();
    }
    
    public void pauseMainMusic() {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.h.pause();
        }
    }
    
    public void playClickSound() {
        this.myMediaPlayer.StopMp();
        this.myMediaPlayer.playSound(R.raw.click);
    }

    public void setBuyChoise() {
        SharedPreference.setBuyChoise(this);
        unLockGame1();
        unLockGame2();
        unLockGame3();
        isBuy = Boolean.TRUE;
        this.r = 0;
        this.p = 0;
        this.q = 0;
        this.mCustomData = new CustomData[]{new CustomData(R.drawable.game1, 0), new CustomData(R.drawable.game2, 0), new CustomData(R.drawable.game3, 0), new CustomData(R.drawable.game4, 0), new CustomData(R.drawable.game5, 0), new CustomData(R.drawable.game6, 0), new CustomData(R.drawable.game7, 0), new CustomData(R.drawable.game8, 0), new CustomData(R.drawable.game9, 0), new CustomData(R.drawable.game10, 0), new CustomData(R.drawable.spot_it, 0), new CustomData(R.drawable.sorting, 0), new CustomData(R.drawable.type_game, 0), new CustomData(R.drawable.game11, 0), new CustomData(R.drawable.farming_count, 0), new CustomData(R.drawable.math_eq, 0), new CustomData(R.drawable.cake_fix, this.r), new CustomData(R.drawable.find_it, this.p), new CustomData(R.drawable.rocket, this.q)};
        setupCustomLists();
    }

    public void setPromoteCode(int i16) {
        SharedPreferences sharedPreferences = getSharedPreferences("SCORE", 0);
        this.g = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("CODE", i16);
        edit.apply();
    }

    public void startMainMusic() {
        mute = MyConstant.MUSIC_SETTING != MyConstant.MUSIC_ON;
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null && !mediaPlayer.isPlaying() && !mute) {
            this.h.setLooping(true);
            this.h.start();
        }
    }
}
