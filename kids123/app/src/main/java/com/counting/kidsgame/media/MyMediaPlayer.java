package com.counting.kidsgame.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.util.Log;

import java.util.Random;

public class MyMediaPlayer {


    MediaPlayer f4769a = null;


    Context f4770b;


    AudioManager f4771c;
    private String colorSoundString;

    public int length = 0;

    public MyMediaPlayer(Context context) {
        this.f4770b = context;
        this.f4771c = (AudioManager) context.getSystemService("audio");
    }

    private String getRandomApplause() {
        int nextInt = new Random().nextInt(5) + 1;
        if (nextInt == 1) {
            return "applause_excellent";
        }
        if (nextInt == 2) {
            return "applause_greatjob";
        }
        if (nextInt == 3) {
            return "applause_intelligent";
        }
        if (nextInt != 4) {
            return nextInt != 5 ? "applause_excellent" : "applause_youdid";
        }
        return "applause_terrific";
    }

    public void StopMp() {
        MediaPlayer mediaPlayer = this.f4769a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.f4769a.reset();
                this.f4769a.release();
                this.f4769a = null;
                this.length = 0;
                System.gc();
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }

    public String getRandomColorSound() {
        switch (new Random().nextInt(9) + 1) {
            case 1:
                this.colorSoundString = "colortouch1";
                break;
            case 2:
                this.colorSoundString = "colortouch2";
                break;
            case 3:
                this.colorSoundString = "colortouch3";
                break;
            case 4:
                this.colorSoundString = "colortouch4";
                break;
            case 5:
                this.colorSoundString = "colortouch5";
                break;
            case 6:
                this.colorSoundString = "colortouch6";
                break;
            case 7:
                this.colorSoundString = "colortouch7";
                break;
            case 8:
                this.colorSoundString = "colortouch8";
                break;
            case 9:
                this.colorSoundString = "colortouch9";
                break;
            case 10:
                this.colorSoundString = "colortouch10";
                break;
            default:
                this.colorSoundString = "colortouch1";
                break;
        }
        return this.colorSoundString;
    }

    public String getRandomSelectArtSound() {
        switch (new Random().nextInt(7) + 1) {
            case 1:
                return "art1";
            case 2:
                return "art2";
            case 4:
                return "art4";
            case 5:
                return "art5";
            case 6:
                return "art6";
            case 7:
                return "art7";
            case 8:
                return "art8";
            default:
                return "art3";
        }
    }

    public void playColorRandomSound() {
        int identifier = this.f4770b.getResources().getIdentifier(getRandomColorSound().toLowerCase(), "raw", this.f4770b.getPackageName());
        if (identifier != 0) {
            playSound(identifier);
        }
    }

    public void playSelectArtRandomSound() {
        int identifier = this.f4770b.getResources().getIdentifier(getRandomSelectArtSound().toLowerCase(), "raw", this.f4770b.getPackageName());
        if (identifier != 0) {
            playSound(identifier);
        }
    }

    public void playSound(int i) {
        MediaPlayer create = MediaPlayer.create(this.f4770b, i);
        this.f4769a = create;
        if (create != null) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    PlaybackParams playbackParams = new PlaybackParams();
                    playbackParams.setPitch(1.18f);
                    this.f4769a.setPlaybackParams(playbackParams);
                }
                this.f4769a.seekTo(this.length);
                this.f4769a.start();
                this.f4769a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        int unused = MyMediaPlayer.this.length = 0;
                    }
                });
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }

    public void speakApplause() {
        int identifier = this.f4770b.getResources().getIdentifier(getRandomApplause().toLowerCase(), "raw", this.f4770b.getPackageName());
        if (identifier != 0) {
            playSound(identifier);
        }
    }

    public void StopMp(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                this.length = 0;
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
    }
}
