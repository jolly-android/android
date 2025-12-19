package com.counting.kidsgame.ads;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.counting.kidsgame.R;

public class MyAdView {
    private Activity activity;

    public MyAdView(Activity activity2) {
        this.activity = activity2;
    }

    private AdSize getAdSize() {
        Display defaultDisplay = this.activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(this.activity, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }

    public void SetAD(FrameLayout frameLayout) {
        AdView adView = new AdView(this.activity);
        adView.setAdUnitId(activity.getString(R.string.google_baner));
        frameLayout.removeAllViews();
        frameLayout.addView(adView);
        AdRequest build = new AdRequest.Builder().build();
        adView.setAdSize(getAdSize());
        adView.loadAd(build);
        adView.setAdListener(new AdListener() {
            public void onAdClicked() {
                super.onAdClicked();
            }

            public void onAdClosed() {
                super.onAdClosed();
            }

            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d("BANNER_AD", "onAdFailedToLoad: " + loadAdError);
            }

            public void onAdImpression() {
                super.onAdImpression();
            }

            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("BANNER_AD", "onAdLoaded: ");
            }

            public void onAdOpened() {
                super.onAdOpened();
            }
        });
    }
}
