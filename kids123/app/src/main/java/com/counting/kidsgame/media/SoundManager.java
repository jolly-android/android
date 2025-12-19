package com.counting.kidsgame.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.counting.kidsgame.R;

import java.util.HashMap;

public class SoundManager {
    private static SoundManager _instance;
    private static AudioManager mAudioManager;
    private static Context mContext;
    private static SoundPool mSoundPool;
    private static HashMap<Integer, Integer> mSoundPoolMap;

    private SoundManager() {
    }

    public static void addSound(int i, int i2) {
        mSoundPoolMap.put(Integer.valueOf(i), Integer.valueOf(mSoundPool.load(mContext, i2, 1)));
    }

    public static void cleanup() {
        mSoundPool.release();
        mSoundPool = null;
        mSoundPoolMap.clear();
        mAudioManager.unloadSoundEffects();
        _instance = null;
    }

    public static synchronized SoundManager getInstance() {
        SoundManager soundManager;
        synchronized (SoundManager.class) {
            if (_instance == null) {
                _instance = new SoundManager();
            }
            soundManager = _instance;
        }
        return soundManager;
    }

    public static void initSounds(Context context) {
        mContext = context;
        if (Build.VERSION.SDK_INT >= 21) {
            mSoundPool = new SoundPool.Builder().setMaxStreams(4).build();
        } else {
            mSoundPool = new SoundPool(4, 3, 1);
        }
        mSoundPoolMap = new HashMap<>();
        mAudioManager = (AudioManager) mContext.getSystemService("audio");
    }

    public static void loadSounds() {
        mSoundPoolMap.put(1, Integer.valueOf(mSoundPool.load(mContext, R.raw.click, 1)));
        mSoundPoolMap.put(2, Integer.valueOf(mSoundPool.load(mContext, R.raw.pop, 1)));
        mSoundPoolMap.put(3, Integer.valueOf(mSoundPool.load(mContext, R.raw.memory_correct, 1)));
        mSoundPoolMap.put(4, Integer.valueOf(mSoundPool.load(mContext, R.raw.colortouch12, 1)));
        mSoundPoolMap.put(5, Integer.valueOf(mSoundPool.load(mContext, R.raw.game_end, 1)));
        mSoundPoolMap.put(6, Integer.valueOf(mSoundPool.load(mContext, R.raw.please, 1)));
        mSoundPoolMap.put(7, Integer.valueOf(mSoundPool.load(mContext, R.raw.tap, 1)));
    }

    public static void muteSound() {
        mSoundPool.setVolume(1, 0.0f, 0.0f);
    }

    public static void playLoopedSound(int i) {
        float streamVolume = ((float) mAudioManager.getStreamVolume(3)) / ((float) mAudioManager.getStreamMaxVolume(3));
        mSoundPool.play(mSoundPoolMap.get(Integer.valueOf(i)).intValue(), streamVolume, streamVolume, 1, -1, 1.0f);
    }

    public static void playSound(int i, float f) {
        try {
            mSoundPool.play(mSoundPoolMap.get(Integer.valueOf(i)).intValue(), 1.0f, 1.0f, 1, 0, f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSound(int i) {
        mSoundPool.stop(mSoundPoolMap.get(Integer.valueOf(i)).intValue());
    }
}
