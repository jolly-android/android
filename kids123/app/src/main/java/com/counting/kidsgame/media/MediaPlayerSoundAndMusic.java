package com.counting.kidsgame.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.counting.kidsgame.MyConstant;

public class MediaPlayerSoundAndMusic {
    private Context context;
    private MediaPlayer mPlayer1;

    public void arrangeSoundLevel() {
        AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
        int streamVolume = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        if (streamVolume < streamMaxVolume / 2) {
            audioManager.setStreamVolume(3, (streamMaxVolume * 2) / 3, 0);
        }
    }

    public void destroyMusic() {
        MediaPlayer mediaPlayer = this.mPlayer1;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void instializeMusic(Context context2, int i) {
        this.context = context2;
        try {
            MediaPlayer create = MediaPlayer.create(context2, i);
            this.mPlayer1 = create;
            create.setLooping(true);
            this.mPlayer1.setAudioStreamType(3);
        } catch (Exception unused) {
        }
    }

    public void pauseMainMusic() {
        MediaPlayer mediaPlayer = this.mPlayer1;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mPlayer1.pause();
        }
    }

    public void startMainMusic() {
        MediaPlayer mediaPlayer = this.mPlayer1;
        if (mediaPlayer != null && !mediaPlayer.isPlaying() && !MyConstant.mute) {
            this.mPlayer1.start();
        }
    }
}
