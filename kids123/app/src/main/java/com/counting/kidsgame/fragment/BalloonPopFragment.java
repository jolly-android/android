package com.counting.kidsgame.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.counting.kidsgame.R;
import com.counting.kidsgame.media.MyMediaPlayer;

import java.util.ArrayList;
import java.util.Random;

public class BalloonPopFragment extends Fragment implements View.OnClickListener {
    public static int popCount;

    
    View f4234a;

    
    MyMediaPlayer f4235b;
    private int[] ballonLayList = {R.layout.fragment_balloon_pop1, R.layout.fragment_balloon_pop2, R.layout.fragment_balloon_pop3, R.layout.fragment_balloon_pop4};
    private ImageView balloon1;
    private ImageView balloon10;
    private ImageView balloon11;
    private ImageView balloon2;
    private ImageView balloon3;
    private ImageView balloon4;
    private ImageView balloon5;
    private ImageView balloon6;
    private ImageView balloon7;
    private ImageView balloon8;
    private ImageView balloon9;
    public ArrayList<ImageView> balloonBtnList;
    private int[] colorballoons = {R.drawable.bl1, R.drawable.bl2, R.drawable.bl3, R.drawable.bl4};
    private Animation popout;

    public static int getRandom(int[] iArr) {
        return iArr[new Random().nextInt(iArr.length)];
    }

    public void init() {
        this.balloon1 = (ImageView) this.f4234a.findViewById(R.id.balloon1);
        this.balloon2 = (ImageView) this.f4234a.findViewById(R.id.balloon2);
        this.balloon3 = (ImageView) this.f4234a.findViewById(R.id.balloon3);
        this.balloon4 = (ImageView) this.f4234a.findViewById(R.id.balloon4);
        this.balloon5 = (ImageView) this.f4234a.findViewById(R.id.balloon5);
        this.balloon6 = (ImageView) this.f4234a.findViewById(R.id.balloon6);
        this.balloon7 = (ImageView) this.f4234a.findViewById(R.id.balloon7);
        this.balloon8 = (ImageView) this.f4234a.findViewById(R.id.balloon8);
        this.balloon9 = (ImageView) this.f4234a.findViewById(R.id.balloon9);
        this.balloon10 = (ImageView) this.f4234a.findViewById(R.id.balloon10);
        this.balloon11 = (ImageView) this.f4234a.findViewById(R.id.balloon11);
        popCount = 0;
        this.balloon1.setOnClickListener(this);
        this.balloon2.setOnClickListener(this);
        this.balloon3.setOnClickListener(this);
        this.balloon4.setOnClickListener(this);
        this.balloon5.setOnClickListener(this);
        this.balloon6.setOnClickListener(this);
        this.balloon7.setOnClickListener(this);
        this.balloon8.setOnClickListener(this);
        this.balloon9.setOnClickListener(this);
        this.balloon10.setOnClickListener(this);
        this.balloon11.setOnClickListener(this);
        this.balloon1.setImageResource(getRandom(this.colorballoons));
        this.balloon2.setImageResource(getRandom(this.colorballoons));
        this.balloon3.setImageResource(getRandom(this.colorballoons));
        this.balloon4.setImageResource(getRandom(this.colorballoons));
        this.balloon5.setImageResource(getRandom(this.colorballoons));
        this.balloon6.setImageResource(getRandom(this.colorballoons));
        this.balloon7.setImageResource(getRandom(this.colorballoons));
        this.balloon8.setImageResource(getRandom(this.colorballoons));
        this.balloon9.setImageResource(getRandom(this.colorballoons));
        this.balloon10.setImageResource(getRandom(this.colorballoons));
        this.balloon11.setImageResource(getRandom(this.colorballoons));
        ArrayList<ImageView> arrayList = new ArrayList<>();
        this.balloonBtnList = arrayList;
        arrayList.add(this.balloon1);
        this.balloonBtnList.add(this.balloon2);
        this.balloonBtnList.add(this.balloon3);
        this.balloonBtnList.add(this.balloon4);
        this.balloonBtnList.add(this.balloon5);
        this.balloonBtnList.add(this.balloon6);
        this.balloonBtnList.add(this.balloon7);
        this.balloonBtnList.add(this.balloon8);
        this.balloonBtnList.add(this.balloon9);
        this.balloonBtnList.add(this.balloon10);
        this.balloonBtnList.add(this.balloon11);
        if (this.balloonBtnList.size() > 0) {
            for (int i = 0; i < this.balloonBtnList.size(); i++) {
                if (Build.VERSION.SDK_INT >= 23) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.img_visible);
                    loadAnimation.setStartOffset((long) (i * 300));
                    this.balloonBtnList.get(i).setVisibility(View.VISIBLE);
                    this.balloonBtnList.get(i).startAnimation(loadAnimation);
                } else {
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.img_visible);
                    loadAnimation2.setStartOffset((long) (i * 300));
                    this.balloonBtnList.get(i).setVisibility(View.VISIBLE);
                    this.balloonBtnList.get(i).startAnimation(loadAnimation2);
                }
            }
        }
    }

    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.popout = AnimationUtils.loadAnimation(getContext(), R.anim.img_gone);
        } else {
            this.popout = AnimationUtils.loadAnimation(getActivity(), R.anim.img_gone);
        }
        int i = popCount + 1;
        popCount = i;
        if (i > 10) {
            this.f4235b.playSound(R.raw.colortouch1);
            onDetach();
        }
        switch (view.getId()) {
            case R.id.balloon1 /*2131296361*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon1.startAnimation(this.popout);
                this.balloon1.setVisibility(View.GONE);
                this.balloon1.setClickable(false);
                return;
            case R.id.balloon10 /*2131296362*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon10.startAnimation(this.popout);
                this.balloon10.setVisibility(View.GONE);
                this.balloon10.setClickable(false);
                return;
            case R.id.balloon11 /*2131296363*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon11.startAnimation(this.popout);
                this.balloon11.setVisibility(View.GONE);
                this.balloon11.setClickable(false);
                return;
            case R.id.balloon2 /*2131296364*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon2.startAnimation(this.popout);
                this.balloon2.setVisibility(View.GONE);
                this.balloon2.setClickable(false);
                return;
            case R.id.balloon3 /*2131296365*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon3.startAnimation(this.popout);
                this.balloon3.setVisibility(View.GONE);
                this.balloon3.setClickable(false);
                return;
            case R.id.balloon4 /*2131296366*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon4.startAnimation(this.popout);
                this.balloon4.setVisibility(View.GONE);
                this.balloon4.setClickable(false);
                return;
            case R.id.balloon5 /*2131296367*/:
                this.balloon5.startAnimation(this.popout);
                this.balloon5.setVisibility(View.GONE);
                this.balloon5.setClickable(false);
                return;
            case R.id.balloon6 /*2131296368*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon6.startAnimation(this.popout);
                this.balloon6.setVisibility(View.GONE);
                this.balloon6.setClickable(false);
                return;
            case R.id.balloon7 /*2131296369*/:
                this.balloon7.startAnimation(this.popout);
                this.balloon7.setVisibility(View.GONE);
                this.balloon7.setClickable(false);
                return;
            case R.id.balloon8 /*2131296370*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon8.startAnimation(this.popout);
                this.balloon8.setVisibility(View.GONE);
                this.balloon8.setClickable(false);
                return;
            case R.id.balloon9 /*2131296371*/:
                this.f4235b.playSound(R.raw.baloon_blast);
                this.balloon9.startAnimation(this.popout);
                this.balloon9.setVisibility(View.GONE);
                this.balloon9.setClickable(false);
                return;
            default:
                return;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f4234a = layoutInflater.inflate(getRandom(this.ballonLayList), viewGroup, false);
        if (Build.VERSION.SDK_INT >= 23) {
            this.f4235b = new MyMediaPlayer(getContext());
        } else {
            this.f4235b = new MyMediaPlayer(getActivity());
        }
        init();
        return this.f4234a;
    }

    public void onDetach() {
        super.onDetach();
        for (int i = 0; i < this.balloonBtnList.size(); i++) {
            this.balloonBtnList.get(i).setVisibility(View.GONE);
        }
    }
}
