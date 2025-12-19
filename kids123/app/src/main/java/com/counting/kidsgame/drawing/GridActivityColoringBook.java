package com.counting.kidsgame.drawing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.counting.kidsgame.gallery.GalleryActivity;
import com.counting.kidsgame.media.MediaPlayerSoundAndMusic;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

public class GridActivityColoringBook extends Activity implements View.OnClickListener {
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
        Intent intent2 = new Intent(this, DrawActivityBook.class);
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
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    GridActivityColoringBook.this.finishActivity();
                }
            }, 500);
        } else if (id == R.id.gallery_btn) {
            clickBounceAnim(view);
            this.myMediaPlayer.playSound(R.raw.click);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    GridActivityColoringBook.this.startActivity(new Intent(GridActivityColoringBook.this, GalleryActivity.class));
                }
            }, 500);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);


        Integer[] numArr = MyConstant.bitmapIdsBook;
        MyConstant.selected_bitmapIds = numArr;
        int length = numArr.length;


        MyConstant.selected_soundIds = MyConstant.soundIdsBook;
        intialize();
        setContentView(R.layout.grid_layout_book);
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
        final int length2 = MyConstant.selected_bitmapIds.length;
        mHlvCustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GridActivityColoringBook.gridPos = i;
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                view.setFocusableInTouchMode(false);
                if (i == length2 - 1) {
                    Toast.makeText(GridActivityColoringBook.this, R.string.long_press, Toast.LENGTH_LONG).show();
                } else if (i == 0) {
                    GridActivityColoringBook.this.finishActivityOnItemSelect();
                } else {
                    GridActivityColoringBook.this.myMediaPlayer.playSound(MyConstant.selected_soundIds[i].intValue());
                    MyConstant.selectedImageFromBitmap = i;
                    MyConstant.fromGridActivityColoringBook = true;
                    MyConstant.selectedTool = 1;
                    GridActivityColoringBook.this.finishActivityOnItemSelect();
                }
            }
        });
        mHlvCustomList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                Uri uri;
                if (i != length2 - 1) {
                    return false;
                }


                return false;
            }
        });
    }


    public void onDestroy() {
        this.mediaPlayerSoundAndMusic.destroyMusic();
        super.onDestroy();
        System.err.println("grid on Destroy");
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
