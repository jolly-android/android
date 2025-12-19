package com.counting.kidsgame.gallery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.counting.kidsgame.R;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;


import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    
    MyMediaPlayer f4717a;
    private int height;
    
    public ArrayList<String> list;
    
    public Context mCtx;
    private int width;

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        
        FrameLayout f4720a;

        
        ImageView f4721b;

        
        ImageView f4722c;

        public GalleryViewHolder(@NonNull View view) {
            super(view);
            this.f4720a = (FrameLayout) view.findViewById(R.id.imageView);
            this.f4721b = (ImageView) view.findViewById(R.id.imageViewInside);
            this.f4722c = (ImageView) view.findViewById(R.id.lock);
        }
    }

    public GalleryAdapter(Context context, ArrayList<String> arrayList) {
        this.mCtx = context;
        this.list = arrayList;
        this.f4717a = new MyMediaPlayer(context);
        cal_screenSize();
    }

    
    public void animateClick(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mCtx, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view.startAnimation(loadAnimation);
    }

    private void cal_screenSize() {
        this.height = DisplayManager.getScreenHeight((Activity) this.mCtx);
        int screenWidth = DisplayManager.getScreenWidth((Activity) this.mCtx);
        this.width = screenWidth;
        this.width = (screenWidth / 3) + 70;
        this.height = (this.height / 8) * 5;
    }

    private Bitmap getPicture(String str) {
        return BitmapFactory.decodeFile(new File(str).getAbsolutePath());
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(@NonNull GalleryViewHolder galleryViewHolder, @SuppressLint({"RecyclerView"}) final int i) {
        galleryViewHolder.f4721b.setImageBitmap(getPicture(this.list.get(i)));
        galleryViewHolder.f4722c.setVisibility(View.GONE);
        galleryViewHolder.f4720a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GalleryAdapter.this.f4717a.playSound(R.raw.click);
                GalleryAdapter.this.animateClick(view);
                Intent intent = new Intent(GalleryAdapter.this.mCtx, ItemViewerActivity.class);
                intent.putExtra("index", (String) GalleryAdapter.this.list.get(i));
                ((GalleryActivity) GalleryAdapter.this.mCtx).finish();
                GalleryAdapter.this.mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.mCtx).inflate(R.layout.grid_layout_view, (ViewGroup) null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-2, -1);
        layoutParams.height = this.height;
        layoutParams.width = this.width;
        layoutParams.setMargins(20, 30, 20, 0);
        inflate.setLayoutParams(layoutParams);
        return new GalleryViewHolder(inflate);
    }
}
