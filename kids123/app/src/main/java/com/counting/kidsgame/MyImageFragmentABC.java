package com.counting.kidsgame;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MyImageFragmentABC extends Fragment {

    
    int f4321a;

    
    int f4322b;

    
    boolean f4323c;

    
    int f4324d;
    boolean e;
    int f;
    private Typeface font;
    int g;
    int h;
    int i;
    int j;
    int k;
    ImageView l;
    ImageView m;
    int n;
    Animation o;
    Random p;
    private int page;
    private RelativeLayout rlMiddle;
    private RelativeLayout rlTop;

    public static MyImageFragmentABC newInstance(int i2, int i3, int i4, boolean z, int i5, boolean z2, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        MyImageFragmentABC myImageFragmentABC = new MyImageFragmentABC();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i2);
        bundle.putInt("imageResourceId", i3);
        bundle.putInt("imageResourceForId", i4);
        bundle.putBoolean("imageSoundIdFlag", z);
        bundle.putInt("imageSoundId", i5);
        bundle.putBoolean("imageNameSoundIDFlag", z2);
        bundle.putInt("imageNameSoundID", i6);
        bundle.putInt("imageName", i7);
        bundle.putInt("imageBackgroundColor", i8);
        bundle.putInt("imageRotaion", i9);
        bundle.putInt("imageAlphaRoation", i10);
        bundle.putInt("imageType", i12);
        bundle.putInt("pos", i11);
        myImageFragmentABC.setArguments(bundle);
        return myImageFragmentABC;
    }

    public int getRandomAnimation(int i2) {
        switch (i2) {
            case 1:
                return R.anim.bounce;
            case 2:
                return R.anim.clockwise;
            case 3:
                return R.anim.clockwise2;
            case 4:
                return R.anim.fade_in;
            case 5:
                return R.anim.move;
            case 6:
                return R.anim.scale;
            case 7:
                return R.anim.shake;
            case 8:
                return R.anim.shake1;
            case 9:
                return R.anim.slide_up;
            default:
                return R.anim.zoom_in;
        }
    }

    public int getRandomAnimationForGirl(int i2) {
        return i2 != 1 ? R.anim.shake1 : R.anim.shake;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.page = getArguments().getInt("posInt", 0);
        this.f4321a = getArguments().getInt("imageResourceId", 0);
        this.f4322b = getArguments().getInt("imageResourceForId", 0);
        this.f4323c = getArguments().getBoolean("imageSoundIdFlag", false);
        this.f4324d = getArguments().getInt("imageSoundId", 0);
        this.e = getArguments().getBoolean("imageNameSoundIDFlag", false);
        this.f = getArguments().getInt("imageNameSoundID", 0);
        this.g = getArguments().getInt("imageName", 0);
        this.h = getArguments().getInt("imageBackgroundColor", 0);
        this.i = getArguments().getInt("imageRotaion", 0);
        this.j = getArguments().getInt("imageAlphaRoation", 0);
        this.k = getArguments().getInt("imageType", 0);
        this.n = getArguments().getInt("pos", 0);
        this.p = new Random();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_image_fragment_abc, viewGroup, false);
        this.l = (ImageView) inflate.findViewById(R.id.imageView1);
        this.m = (ImageView) inflate.findViewById(R.id.imageViewAlpha);
        if (this.n == 1) {
            try {
                Glide.with((FragmentActivity) FlashCardABC.mContext).load(Integer.valueOf(this.f4321a)).into(this.l);
                Glide.with((FragmentActivity) FlashCardABC.mContext).load(Integer.valueOf(this.f4322b)).into(this.m);
            } catch (Exception unused) {
                this.l.setImageResource(this.f4321a);
                this.m.setImageResource(this.f4322b);
            }
        } else {
            try {
                Glide.with((FragmentActivity) FlashCardABC.mContext).load(Integer.valueOf(this.f4322b)).into(this.l);
                Glide.with((FragmentActivity) FlashCardABC.mContext).load(Integer.valueOf(this.f4321a)).into(this.m);
            } catch (Exception unused2) {
                this.l.setImageResource(this.f4322b);
                this.m.setImageResource(this.f4321a);
            }
        }
        this.rlTop = (RelativeLayout) inflate.findViewById(R.id.rlTop);
        this.rlMiddle = (RelativeLayout) inflate.findViewById(R.id.rlMiddle);
        TextView textView = (TextView) inflate.findViewById(R.id.tvAnimalName);
        Typeface createFromAsset = Typeface.createFromAsset(FlashCardABC.mContext.getAssets(), "arlrdbd.ttf");
        this.font = createFromAsset;
        textView.setTypeface(createFromAsset);
        textView.setText(getResources().getString(this.g));
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FlashCardABC.mContext.h.StopMp();
                int nextInt = MyImageFragmentABC.this.p.nextInt(9) + 1;
                int randomAnimation = MyImageFragmentABC.this.getRandomAnimation(nextInt);
                MyImageFragmentABC myImageFragmentABC = MyImageFragmentABC.this;
                myImageFragmentABC.o = AnimationUtils.loadAnimation(myImageFragmentABC.getActivity(), randomAnimation);
                MyImageFragmentABC myImageFragmentABC2 = MyImageFragmentABC.this;
                myImageFragmentABC2.l.startAnimation(myImageFragmentABC2.o);
                MyImageFragmentABC myImageFragmentABC3 = MyImageFragmentABC.this;
                if (!myImageFragmentABC3.e) {
                    myImageFragmentABC3.randomSoundPlay(nextInt);
                } else if (myImageFragmentABC3.n == 1) {
                    FlashCardABC.mContext.h.playSound(myImageFragmentABC3.f);
                } else {
                    FlashCardABC.mContext.h.playSound(myImageFragmentABC3.f4324d);
                }
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FlashCardABC.mContext.h.StopMp();
                int nextInt = MyImageFragmentABC.this.p.nextInt(9) + 1;
                int randomAnimation = MyImageFragmentABC.this.getRandomAnimation(nextInt);
                MyImageFragmentABC myImageFragmentABC = MyImageFragmentABC.this;
                myImageFragmentABC.o = AnimationUtils.loadAnimation(myImageFragmentABC.getActivity(), randomAnimation);
                MyImageFragmentABC myImageFragmentABC2 = MyImageFragmentABC.this;
                myImageFragmentABC2.m.startAnimation(myImageFragmentABC2.o);
                MyImageFragmentABC myImageFragmentABC3 = MyImageFragmentABC.this;
                if (!myImageFragmentABC3.f4323c) {
                    myImageFragmentABC3.randomSoundPlay(nextInt);
                } else if (myImageFragmentABC3.n == 1) {
                    FlashCardABC.mContext.h.playSound(myImageFragmentABC3.f4324d);
                } else {
                    FlashCardABC.mContext.h.playSound(myImageFragmentABC3.f);
                }
            }
        });
        return inflate;
    }

    public void randomSoundPlay(int i2) {
        switch (i2) {
            case 1:
                FlashCardABC.mContext.h.playSound(R.raw.random_comical);
                return;
            case 2:
                FlashCardABC.mContext.h.playSound(R.raw.random_anim_boing);
                return;
            case 3:
                FlashCardABC.mContext.h.playSound(R.raw.random_sticky);
                return;
            case 4:
                FlashCardABC.mContext.h.playSound(R.raw.random_effect_sparkle);
                return;
            case 5:
                FlashCardABC.mContext.h.playSound(R.raw.random_gone);
                return;
            case 6:
                FlashCardABC.mContext.h.playSound(R.raw.random_peeking);
                return;
            case 7:
                FlashCardABC.mContext.h.playSound(R.raw.random_whish);
                return;
            case 8:
                FlashCardABC.mContext.h.playSound(R.raw.random_squeaky_pop);
                return;
            case 9:
                FlashCardABC.mContext.h.playSound(R.raw.random_whiparound);
                return;
            case 10:
                FlashCardABC.mContext.h.playSound(R.raw.random_twitch);
                return;
            default:
                return;
        }
    }
}
