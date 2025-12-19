package com.counting.kidsgame.ads;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;

public class MyAdmob {
    private static final String TAG = "Interstitial_Ad";

    public static InterstitialAd mInterstitialAd;


    public interface AdListener {
        void OnClose();

        void OnFailed();

        void OnLoad();
    }

    public static void createAd(Activity activity, final AdListener adListener) {
        if (SharedPreference.getBuyChoise(activity) == 0) {
            InterstitialAd.load(activity, activity.getString(R.string.g_inr), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Log.i(MyAdmob.TAG, loadAdError.getMessage());
                    InterstitialAd unused = MyAdmob.mInterstitialAd = null;
                    adListener.OnFailed();
                }

                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    InterstitialAd unused = MyAdmob.mInterstitialAd = interstitialAd;
                    MyAdmob.setListener(adListener);
                    adListener.OnLoad();
                    Log.i(MyAdmob.TAG, "onAdLoaded");
                }
            });
        }
    }

    public static void setListener(final AdListener adListener) {
        InterstitialAd interstitialAd = mInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                public void onAdDismissedFullScreenContent() {
                    adListener.OnClose();
                    Log.d("TAG", "The ad was dismissed.");
                }

                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    Log.d("TAG", "The ad failed to show.");
                }

                public void onAdShowedFullScreenContent() {
                    InterstitialAd unused = MyAdmob.mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });
        }
    }

    public static void showInterstitial(Activity activity) {
        if (SharedPreference.getBuyChoise(activity) == 0) {
            InterstitialAd interstitialAd = mInterstitialAd;
            if (interstitialAd != null) {
                interstitialAd.show(activity);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        }
    }
}
