package com.counting.kidsgame;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlashCardABC extends FragmentActivity implements View.OnClickListener {

    public static int LIST_SIZE = 0;
    public static final int TYPE_ALPHA = 2;
    public static List<ImageClassABC> imageClassListABC;
    public static FlashCardABC mContext;
    ImageClassABC A;
    ImageClassABC B;
    ImageClassABC C;
    private MyPagerAdapter adapterViewPager;
    private ImageView btnHome;

    public ImageView btnLeft;

    public ImageView btnRight;
    private boolean clicked = false;
    MyMediaPlayer h;
    ViewPager2.OnPageChangeCallback i;
    private ArrayList<Integer> images;
    private Intent intent;
    ImageClassABC j;
    ImageClassABC k;
    ImageClassABC l;
    ImageClassABC m;
    private MyAdView myAdView;
    ImageClassABC n;
    ImageClassABC o;
    private BitmapFactory.Options options;
    ImageClassABC p;

    public int pos;
    ImageClassABC q;
    ImageClassABC r;
    private Random random;

    public LinearLayout rlTop;

    public RelativeLayout rlTopTexture;
    ImageClassABC s;
    ImageClassABC t;
    private LinearLayout thumbnailsContainer;
    ImageClassABC u;
    ImageClassABC v;

    public ViewPager2 vpPager;
    ImageClassABC w;
    ImageClassABC x;
    ImageClassABC y;
    ImageClassABC z;

    public static class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        public Fragment createFragment(int i) {
            return MyImageFragmentABC.newInstance(i, FlashCardABC.imageClassListABC.get(i).getImageResourceId(), FlashCardABC.imageClassListABC.get(i).getImageResourceForId(), FlashCardABC.imageClassListABC.get(i).isImageSoundIdFlag(), FlashCardABC.imageClassListABC.get(i).getImageSoundId(), FlashCardABC.imageClassListABC.get(i).isImageNameSoundIDFlag(), FlashCardABC.imageClassListABC.get(i).getImageNameSoundID(), FlashCardABC.imageClassListABC.get(i).getImageName(), FlashCardABC.imageClassListABC.get(i).getImageBackgroundColor(), FlashCardABC.imageClassListABC.get(i).getImageRotation(), FlashCardABC.imageClassListABC.get(i).getImageAlphaRoation(), FlashCardABC.imageClassListABC.get(i).getPos(), FlashCardABC.imageClassListABC.get(i).getImageType());
        }

        public int getItemCount() {
            return FlashCardABC.LIST_SIZE;
        }
    }

    private void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }


    public void finishActivity() {
        finish();
        MyConstant.showNewApp = true;
    }

    private void inflateThumbnails() {
        for (int i2 = 0; i2 < imageClassListABC.size(); i2++) {
            View inflate = getLayoutInflater().inflate(R.layout.item_image, (ViewGroup) null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_thumb);
            imageView.setOnClickListener(onChagePageClickListener(i2, imageView));
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            this.options = options2;
            options2.inDither = false;
            options2.inSampleSize = 3;
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageClassListABC.get(i2).getBigletterId(), this.options));
            this.thumbnailsContainer.addView(inflate);
        }
    }

    private View.OnClickListener onChagePageClickListener(final int i2, final ImageView imageView) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                FlashCardABC.this.playClickSound();
                imageView.startAnimation(AnimationUtils.loadAnimation(FlashCardABC.this.getApplicationContext(), R.anim.zoom));
                FlashCardABC.this.vpPager.setCurrentItem(i2);
            }
        };
    }

    private void releaseMemory() {
        this.adapterViewPager = null;
        this.thumbnailsContainer = null;
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    public void addAlphaSet() {
        removeListItemABC();
        imageClassListABC.add(this.j);
        imageClassListABC.add(this.k);
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
        imageClassListABC.add(this.x);
        imageClassListABC.add(this.y);
        imageClassListABC.add(this.z);
        imageClassListABC.add(this.A);
        imageClassListABC.add(this.B);
        imageClassListABC.add(this.C);
    }

    public void createAlphaSet() {


        this.j = new ImageClassABC(R.drawable.a1, R.drawable.b1, R.drawable.b1, R.drawable.obj1, true, R.raw.car, true, R.raw.n_1, R.string.alpha_a, R.color.wColor, 10, -10, 0, 2);
        this.k = new ImageClassABC(R.drawable.a2, R.drawable.b2, R.drawable.b2, R.drawable.obj2, true, R.raw.pig, true, R.raw.n_2, R.string.alpha_b, R.color.dColor, 10, -10, 0, 2);
        this.l = new ImageClassABC(R.drawable.a3, R.drawable.b3, R.drawable.b3, R.drawable.obj3, true, R.raw.teddy_bear, true, R.raw.n_3, R.string.alpha_c, R.color.cColor, 10, -10, 0, 2);
        this.m = new ImageClassABC(R.drawable.a4, R.drawable.b4, R.drawable.b4, R.drawable.obj4, true, R.raw.clock, true, R.raw.n_4, R.string.alpha_d, R.color.color3, 10, -10, 0, 2);
        this.n = new ImageClassABC(R.drawable.a5, R.drawable.b5, R.drawable.b5, R.drawable.obj5, true, R.raw.duck, true, R.raw.n_5, R.string.alpha_e, R.color.mColor, 10, -10, 0, 2);
        this.o = new ImageClassABC(R.drawable.a6, R.drawable.b6, R.drawable.b6, R.drawable.obj6, true, R.raw.ball, true, R.raw.n_6, R.string.alpha_f, R.color.uColor, 10, -10, 0, 2);
        this.p = new ImageClassABC(R.drawable.a7, R.drawable.b7, R.drawable.b7, R.drawable.obj7, true, R.raw.banana, true, R.raw.n_7, R.string.alpha_g, R.color.gColor, 10, -10, 0, 2);
        this.q = new ImageClassABC(R.drawable.a8, R.drawable.b8, R.drawable.b8, R.drawable.obj8, true, R.raw.pizza, true, R.raw.n_8, R.string.alpha_h, R.color.tShadowColor, 10, -10, 0, 2);
        this.r = new ImageClassABC(R.drawable.a9, R.drawable.b9, R.drawable.b9, R.drawable.obj9, true, R.raw.apple, true, R.raw.n_9, R.string.alpha_i, R.color.dColor, 10, -10, 0, 2);
        this.s = new ImageClassABC(R.drawable.a10, R.drawable.b10, R.drawable.b10, R.drawable.obj10, true, R.raw.fish, true, R.raw.n_10, R.string.alpha_j, R.color.color3, 10, -10, 0, 2);
        this.t = new ImageClassABC(R.drawable.a11, R.drawable.b11, R.drawable.b11, R.drawable.obj11, true, R.raw.orange, true, R.raw.n_11, R.string.alpha_k, R.color.tShadowColor, 10, -10, 0, 2);
        this.u = new ImageClassABC(R.drawable.a12, R.drawable.b12, R.drawable.b12, R.drawable.obj12, true, R.raw.butterfly, true, R.raw.n_12, R.string.alpha_l, R.color.iColor, 10, -10, 0, 2);
        this.v = new ImageClassABC(R.drawable.a13, R.drawable.b13, R.drawable.b13, R.drawable.obj13, true, R.raw.ice_cream, true, R.raw.n_13, R.string.alpha_m, R.color.mColor, 10, -10, 0, 2);
        this.w = new ImageClassABC(R.drawable.a14, R.drawable.b14, R.drawable.b14, R.drawable.obj14, true, R.raw.flower, true, R.raw.n_14, R.string.alpha_n, R.color.color3, 10, -10, 0, 2);
        this.x = new ImageClassABC(R.drawable.a15, R.drawable.b15, R.drawable.b15, R.drawable.obj15, true, R.raw.egg, true, R.raw.n_15, R.string.alpha_o, R.color.wColor, 10, -10, 0, 2);
        this.y = new ImageClassABC(R.drawable.a16, R.drawable.b16, R.drawable.b16, R.drawable.obj16, true, R.raw.balloon, true, R.raw.n_16, R.string.alpha_p, R.color.pColor, 10, -10, 0, 2);
        this.z = new ImageClassABC(R.drawable.a17, R.drawable.b17, R.drawable.b17, R.drawable.obj17, true, R.raw.muffin, true, R.raw.n_17, R.string.alpha_q, R.color.oColor, 10, -10, 0, 2);
        this.A = new ImageClassABC(R.drawable.a18, R.drawable.b18, R.drawable.b18, R.drawable.obj18, true, R.raw.blocks, true, R.raw.n_18, R.string.alpha_r, R.color.iColor, 10, -10, 0, 2);
        this.B = new ImageClassABC(R.drawable.a19, R.drawable.b19, R.drawable.b19, R.drawable.obj19, true, R.raw.star, true, R.raw.n_19, R.string.alpha_s, R.color.dColor, 10, -10, 0, 2);
        this.C = new ImageClassABC(R.drawable.a20, R.drawable.b20, R.drawable.b20, R.drawable.obj20, true, R.raw.pencil, true, R.raw.n_20, R.string.alpha_t, R.color.uColor, 10, -10, 0, 2);
    }

    public int getRandomTexture() {
        Random random2 = new Random();
        this.random = random2;
        int nextInt = random2.nextInt(5) + 1;
        if (nextInt == 1) {
            return R.drawable.patterncircle;
        }
        if (nextInt == 2) {
            return R.drawable.patternlines;
        }
        if (nextInt != 4) {
            return nextInt != 5 ? R.drawable.patternzigzag : R.drawable.patternzigzag2;
        }
        return R.drawable.patternlines2;
    }

    public void onBackPressed() {
        this.h.StopMp();
        finishActivity();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHome /*2131296407*/:
                playClickSound();
                clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        FlashCardABC.this.finishActivity();
                    }
                }, 500);
                return;
            case R.id.btnLeft /*2131296408*/:
                playClickSound();
                clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        FlashCardABC.this.vpPager.setCurrentItem(FlashCardABC.this.pos - 1);
                    }
                }, 500);
                return;
            case R.id.btnRight /*2131296415*/:
                playClickSound();
                clickBounceAnim(view);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        FlashCardABC.this.vpPager.setCurrentItem(FlashCardABC.this.pos + 1);
                    }
                }, 500);
                return;
            default:
                return;
        }
    }


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
        mContext = this;
        System.gc();
        this.h = new MyMediaPlayer(this);
        setContentView((int) R.layout.view_pager);
        createAlphaSet();
        if (imageClassListABC == null) {
            imageClassListABC = new ArrayList();
        }
        addAlphaSet();
        this.images = new ArrayList<>();
        ImageView imageView = (ImageView) findViewById(R.id.btnHome);
        this.btnHome = imageView;
        imageView.setOnClickListener(mContext);
        ImageView imageView2 = (ImageView) findViewById(R.id.btnLeft);
        this.btnLeft = imageView2;
        imageView2.setOnClickListener(mContext);
        ImageView imageView3 = (ImageView) findViewById(R.id.btnRight);
        this.btnRight = imageView3;
        imageView3.setOnClickListener(mContext);
        this.thumbnailsContainer = (LinearLayout) findViewById(R.id.container);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llTop);
        this.rlTop = linearLayout;
        linearLayout.setBackgroundColor(SupportMenu.CATEGORY_MASK);
        inflateThumbnails();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlTopTexture);
        this.rlTopTexture = relativeLayout;
        try {
            relativeLayout.setBackgroundResource(getRandomTexture());
        } catch (Exception unused) {
            this.rlTopTexture.setBackgroundResource(R.drawable.patternlines);
        }
        LIST_SIZE = imageClassListABC.size();
        this.vpPager = (ViewPager2) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getLifecycle());
        this.adapterViewPager = myPagerAdapter;
        this.vpPager.setAdapter(myPagerAdapter);
        ViewPager2.OnPageChangeCallback r3 = new ViewPager2.OnPageChangeCallback() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (i == 0) {
                    FlashCardABC.this.btnLeft.setVisibility(View.INVISIBLE);
                } else {
                    FlashCardABC.this.btnLeft.setVisibility(View.VISIBLE);
                }
                if (i == FlashCardABC.LIST_SIZE - 1) {
                    FlashCardABC.this.btnRight.setVisibility(View.INVISIBLE);
                } else {
                    FlashCardABC.this.btnRight.setVisibility(View.VISIBLE);
                }
            }

            public void onPageSelected(int i) {
                FlashCardABC.this.h.StopMp();
                FlashCardABC.this.h.playSound(R.raw.swipe);
                RemoveBackButton.hideBackButtonBar(FlashCardABC.this);
                try {
                    FlashCardABC.this.rlTop.setBackgroundResource(FlashCardABC.imageClassListABC.get(i).getImageBackgroundColor());
                    FlashCardABC.this.rlTopTexture.setBackgroundResource(FlashCardABC.this.getRandomTexture());
                } catch (Exception unused) {
                    FlashCardABC.this.rlTop.setBackgroundResource(R.color.aColor);
                    FlashCardABC.this.rlTopTexture.setBackgroundResource(R.drawable.patternlines);
                }
                int unused2 = FlashCardABC.this.pos = i;
                FlashCardABC.this.playObjectSound(i);
            }
        };
        this.i = r3;
        this.vpPager.registerOnPageChangeCallback(r3);
        this.vpPager.post(new Runnable() {
            public void run() {
                FlashCardABC flashCardABC = FlashCardABC.this;
                flashCardABC.i.onPageSelected(flashCardABC.vpPager.getCurrentItem());
            }
        });
        this.myAdView = new MyAdView(this);

        setAd();
    }

    public void onDestroy() {
        super.onDestroy();
        this.h.StopMp();
        releaseMemory();
        Runtime.getRuntime().gc();
    }


    public void onPause() {
        super.onPause();
        this.h.StopMp();
    }


    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void playClickSound() {
        this.h.StopMp();
        this.h.playSound(R.raw.click);
    }

    public void playObjectSound(int i2) {
        this.h.playSound(imageClassListABC.get(i2).getImageNameSoundID());
    }

    public void removeListItemABC() {
        List<ImageClassABC> list = imageClassListABC;
        list.removeAll(list);
    }
}
