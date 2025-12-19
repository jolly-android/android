package com.counting.kidsgame.drawing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.counting.kidsgame.HorizontalListView;
import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.gallery.GalleryActivity;
import com.counting.kidsgame.media.MediaPlayerSoundAndMusic;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

public class GridActivityColoring extends Activity implements View.OnClickListener {
    public static boolean blank = false;
    public static int gridPos = 0;
    public static boolean line = false;
    public static HorizontalListView mHlvCustomList;


    ImageView back;
    ImageView gallery_btn;
    Intent intent;
    MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic;

    MyMediaPlayer myMediaPlayer;


    private void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    private void intialize() {
        MyConstant.selectedImageFromBitmap = -1;
        MyConstant.heightInPixels = DisplayManager.getScreenHeight(this);
        MyConstant.widthInPixels = DisplayManager.getScreenWidth(this);
    }

    public void finishActivity() {
        finish();
    }

    public void finishActivityOnItemSelect() {
        Intent intent2 = new Intent(this, DrawActivity.class);
        this.intent = intent2;
        startActivity(intent2);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            clickBounceAnim(view);
            this.myMediaPlayer.playSound(R.raw.click);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    GridActivityColoring.this.finishActivity();
                }
            }, 500);
        } else if (id == R.id.gallery_btn) {
            clickBounceAnim(view);
            this.myMediaPlayer.playSound(R.raw.click);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    GridActivityColoring.this.startActivity(new Intent(GridActivityColoring.this, GalleryActivity.class));
                }
            }, 500);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.err.print("inside grid");

        Integer[] numArr = MyConstant.bitmapIds21;
        MyConstant.selected_bitmapIds = numArr;
        int length = numArr.length;


        MyConstant.selected_soundIds = MyConstant.soundIds21;
        intialize();
        setContentView(R.layout.grid_layout);
        this.myMediaPlayer = new MyMediaPlayer(this);
        MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic2 = new MediaPlayerSoundAndMusic();
        this.mediaPlayerSoundAndMusic = mediaPlayerSoundAndMusic2;
        mediaPlayerSoundAndMusic2.instializeMusic(this, R.raw.number_sub);
        HorizontalListView horizontalListView = (HorizontalListView) findViewById(R.id.hlvCustomList);
        mHlvCustomList = horizontalListView;
        horizontalListView.setDividerWidth(MyConstant.widthInPixels / 20);
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.back = imageView;
        imageView.setOnClickListener(this);


        ImageView imageView3 = (ImageView) findViewById(R.id.gallery_btn);
        this.gallery_btn = imageView3;
        imageView3.setOnClickListener(this);
        mHlvCustomList.setAdapter((ListAdapter) new ImageAdapter(this));
        ImageAdapter imageAdapter = new ImageAdapter(this);
        mHlvCustomList.setFocusable(false);
        imageAdapter.notifyDataSetChanged();
        mHlvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GridActivityColoring.gridPos = i;
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                view.setFocusableInTouchMode(false);
                if (SharedPreference.getBuyChoise(GridActivityColoring.this) == 0) {
                    if (i == MyConstant.selected_bitmapIds.length - 1) {
                        GridActivityColoring.this.myMediaPlayer.playSound(R.raw.click);
                        Toast.makeText(GridActivityColoring.this, R.string.long_press, Toast.LENGTH_LONG).show();
                        return;
                    }
                    GridActivityColoring.this.myMediaPlayer.playSound(MyConstant.selected_soundIds[i].intValue());
                    MyConstant.selectedImageFromBitmap = i;
                    MyConstant.fromGridActivityColoringBook = true;
                    MyConstant.selectedTool = 1;
                    GridActivityColoring.this.finishActivityOnItemSelect();
                } else {
                    GridActivityColoring.this.myMediaPlayer.playSound(MyConstant.selected_soundIds[i].intValue());
                    MyConstant.selectedImageFromBitmap = i;
                    MyConstant.fromGridActivityColoringBook = true;
                    MyConstant.selectedTool = 1;
                    GridActivityColoring.this.finishActivityOnItemSelect();
                }
            }
        });
        mHlvCustomList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != MyConstant.selected_bitmapIds.length - 1) {
                    return false;
                }


                return false;
            }
        });
    }


    public void onDestroy() {
        super.onDestroy();
        this.mediaPlayerSoundAndMusic.destroyMusic();
    }


    public void onPause() {
        super.onPause();
        this.mediaPlayerSoundAndMusic.pauseMainMusic();
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
        System.err.println("at onResume");
    }


    public void onStart() {
        super.onStart();
        if (MyConstant.MUSIC_SETTING == MyConstant.MUSIC_ON) {
            this.mediaPlayerSoundAndMusic.startMainMusic();
        }
    }
}
