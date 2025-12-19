package com.counting.kidsgame.gallery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.counting.kidsgame.R;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.BounceInterpolator;


import java.io.File;
import java.io.FileOutputStream;

public class ItemViewerActivity extends Activity {

    
    ImageView f4724a;

    
    ImageView f4725b;

    
    ImageView f4726c;

    
    ImageView f4727d;
    String e;
    MyMediaPlayer f;

    
    public void animateClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_in));
    }

    
    public void clickBounceAnim(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        loadAnimation.setInterpolator(new BounceInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, GalleryActivity.class));
    }

    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_item_viewer);
        this.f = new MyMediaPlayer(this);
        String string = getIntent().getExtras().getString("index");
        this.e = string;
        Bitmap decodeFile = BitmapFactory.decodeFile(string);
        ImageView imageView = (ImageView) findViewById(R.id.img);
        this.f4724a = imageView;
        imageView.setImageBitmap(decodeFile);
        ImageView imageView2 = (ImageView) findViewById(R.id.delete);
        this.f4726c = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ItemViewerActivity.this.clickBounceAnim(view);
                ItemViewerActivity.this.f.playSound(R.raw.click);
                final File absoluteFile = new File(ItemViewerActivity.this.e).getAbsoluteFile();
                int screenHeight = DisplayManager.getScreenHeight(ItemViewerActivity.this);
                int screenWidth = DisplayManager.getScreenWidth(ItemViewerActivity.this);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.height = screenHeight - (screenHeight / 5);
                layoutParams.width = screenWidth - (screenWidth / 5);
                layoutParams.gravity = 17;
                try {
                    final Dialog dialog = new Dialog(ItemViewerActivity.this, R.style.AlertDialogCustom);
                    dialog.getWindow().setFlags(8, 8);
                    dialog.setContentView(R.layout.dialog_draw_save);
                    RemoveBackButton.hideNavigationDialog(dialog);
                    ((ImageView) dialog.findViewById(R.id.imageview)).setImageResource(R.drawable.delete_page);
                    ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);
                    ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            ItemViewerActivity.this.animateClick(view);
                            ItemViewerActivity.this.f.playSound(R.raw.click);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                public void run() {
                                    dialog.dismiss();
                                }
                            }, 300);
                        }
                    });
                    ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(final View view) {
                            ItemViewerActivity.this.animateClick(view);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                public void run() {
                                    ItemViewerActivity.this.animateClick(view);
                                    ItemViewerActivity.this.f.playSound(R.raw.click);
                                    dialog.dismiss();
                                    if (absoluteFile.delete()) {
                                        ItemViewerActivity.this.onBackPressed();
                                    }
                                }
                            }, 300);
                        }
                    });
                    dialog.show();
                    dialog.getWindow().clearFlags(8);
                } catch (Exception unused) {
                }
            }
        });
        ImageView imageView3 = (ImageView) findViewById(R.id.back);
        this.f4727d = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ItemViewerActivity.this.clickBounceAnim(view);
                ItemViewerActivity.this.f.playSound(R.raw.click);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        ItemViewerActivity.this.onBackPressed();
                    }
                }, 500);
            }
        });
        ImageView imageView4 = (ImageView) findViewById(R.id.share);
        this.f4725b = imageView4;
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ItemViewerActivity.this.f.playSound(R.raw.click);
                ItemViewerActivity.this.clickBounceAnim(view);
                ItemViewerActivity.this.sharePicture();
            }
        });
    }

    
    public void onResume() {
        super.onResume();
        RemoveBackButton.hideBackButtonBar(this);
    }

    public void sharePicture() {
        String packageName = getPackageName();
        Bitmap decodeFile = BitmapFactory.decodeFile(this.e);
        try {
            File file = new File(getCacheDir(), "images");
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file + "/image.png");
            decodeFile.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        File file2 = new File(new File(getCacheDir(), "images"), "image.png");
        Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file2);
        if (uriForFile != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.addFlags(1);
            intent.setDataAndType(uriForFile, getContentResolver().getType(uriForFile));
            intent.putExtra("android.intent.extra.TEXT", "Checkout my amazing art with this awesome app: https://play.google.com/store/apps/details?id=" + packageName);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            startActivity(Intent.createChooser(intent, "Choose an app"));
        }
    }
}
