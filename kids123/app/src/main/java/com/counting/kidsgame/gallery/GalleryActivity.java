package com.counting.kidsgame.gallery;

import android.app.Activity;
import android.content.ContextWrapper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.counting.kidsgame.R;
import com.counting.kidsgame.drawing.ImageAdapter;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends Activity {

    
    TextView f4711a;

    
    TextView f4712b;

    
    ImageView f4713c;

    
    RecyclerView f4714d;
    File[] e;
    private ArrayList<String> f = new ArrayList<>();
    MyMediaPlayer g;
    private ImageAdapter imageAdapter;

    
    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    public void getFromSdcard() {
        File dir = new ContextWrapper(this).getDir("gallery", 0);
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            this.e = listFiles;
            if (listFiles == null || listFiles.length <= 0) {
                this.f4711a.setVisibility(View.VISIBLE);
                return;
            }
            this.f4711a.setVisibility(View.GONE);
            for (File absolutePath : this.e) {
                this.f.add(absolutePath.getAbsolutePath());
            }
        }
    }

    public void onBackPressed() {
        this.g.StopMp();
        finish();
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gallery);
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.g = myMediaPlayer;
        myMediaPlayer.playSound(R.raw.colortouch1);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "english.ttf");
        TextView textView = (TextView) findViewById(R.id.heading);
        this.f4712b = textView;
        textView.setTypeface(createFromAsset);
        TextView textView2 = (TextView) findViewById(R.id.empty_msg);
        this.f4711a = textView2;
        textView2.setTypeface(createFromAsset);
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.f4713c = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GalleryActivity.this.g.playSound(R.raw.click);
                GalleryActivity.this.clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        GalleryActivity.this.onBackPressed();
                    }
                }, 300);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        this.f4714d = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.f4714d.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        getFromSdcard();
        this.f4714d.setAdapter(new GalleryAdapter(this, this.f));
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }
}
