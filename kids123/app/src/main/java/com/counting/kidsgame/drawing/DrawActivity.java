package com.counting.kidsgame.drawing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.counting.kidsgame.Data;
import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.counting.kidsgame.SharedPreference;
import com.counting.kidsgame.ads.MyAdView;
import com.counting.kidsgame.ads.MyAdmob;
import com.counting.kidsgame.media.MediaPlayerSoundAndMusic;
import com.counting.kidsgame.media.MyMediaPlayer;
import com.counting.kidsgame.tools.DisplayManager;
import com.counting.kidsgame.tools.RemoveBackButton;
import com.counting.kidsgame.util.CapturePhotoUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawActivity extends Activity implements View.OnClickListener {
    private static final int BOOK = 6;
    private static final int BRUSH = 2;
    private static final int ERASER = 3;
    private static final int LARGE = 1;
    private static final int NEW_PAGE = 5;
    private static final int NORMAL = 3;
    private static final int OTHER = 5;
    private static final int PAINT = 7;
    private static final int PEN = 1;
    private static final int RC_PERMISSION_WRITE_EXTERNAL_STORAGE = 101;
    private static final int SAVE = 4;
    private static int SCREEN_SIZE = 0;
    private static final int SMALL = 2;
    private static final String TAG = "FlashCardCategory";
    private static final int XLARGE = 4;
    public static Context drawActivtyContext = null;
    public static ImageView iv = null;
    public static DrawingPicture myDrawView = null;
    public static Bitmap myart = null;
    public static boolean patternSoundplay = false;


    boolean f4577a = false;


    int f4578b = 2;
    private ImageView back;
    private ImageView bt1;
    private ImageView btnHome;


    RecyclerView f4579c;
    private FrameLayout content;


    HorizontalAdapter f4580d;

    public List<Data> data;
    public ViewGroup drawView;

    public DrawerLayout drawerLayout;

    public DrawerLayout drawerLayout1;

    public View drawerView;

    public View drawerView1;
    String e = "LifeCycleEvents";
    private ImageView eraser;

    public FrameLayout horizontal_recycler_view_frameview;
    private Intent i;
    private Intent intent;

    public boolean isdraweropened = false;

    public boolean isdraweropened1 = false;

    public boolean ispatternClicked = false;
    private View iv1;
    private View iv11;
    private View iv2;
    private View iv21;
    private View iv3;
    private View iv31;

    public LinearLayout leftTop;

    public int listItemDefaultPos = -1;
    private ActionBarDrawerToggle mDrawerTogle;
    private ActionBarDrawerToggle mDrawerTogle1;
    private ImageView mPaint;
    public MyMediaPlayer mediaPlayer;
    private MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic;
    private int mode;
    private MyAdView myAdView;
    private boolean navigationClicked = false;
    private Button next;
    private Paint paint;
    private ImageView pen;
    private Button previous;

    public int row_index = -1;
    private ImageView save;
    private ImageView sound;
    private LinearLayout top_l1;
    private boolean writePermission;

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<Data> f4613a;


        Context f4614b;

        public class MyViewHolder extends RecyclerView.ViewHolder {


            ImageView f4618a;


            ImageView f4619b;

            public MyViewHolder(View view) {
                super(view);
                this.f4618a = (ImageView) view.findViewById(R.id.imageview);
                this.f4619b = (ImageView) view.findViewById(R.id.imageviewTick);
            }
        }

        public HorizontalAdapter(List<Data> list, Application application) {
            Collections.emptyList();
            this.f4613a = list;
            this.f4614b = application;
        }

        private void isDefaultPosition(int i) {
            if (i == DrawActivity.this.listItemDefaultPos) {
                DrawActivity.this.brushSelectedOnClickButton();
                if (MyConstant.erase) {
                    DrawActivity.this.turnEraserToBrush();
                }
                if (DrawActivity.this.ispatternClicked) {
                    DrawActivity.myDrawView.setPattern(this.f4613a.get(i).getTxt());
                    return;
                }
                MyConstant.DRAW_COLOR = ContextCompat.getColor(this.f4614b, this.f4613a.get(i).getColorId());
                DrawActivity.myDrawView.setPathColor(MyConstant.DRAW_COLOR);
                DrawActivity.this.horizontal_recycler_view_frameview.setBackgroundColor(MyConstant.DRAW_COLOR);
                DrawActivity.this.leftTop.setBackgroundColor(MyConstant.DRAW_COLOR);
            }
        }

        private void setSelectedColorTick(MyViewHolder myViewHolder, int i) {
            if (DrawActivity.this.listItemDefaultPos != -1) {
                if (DrawActivity.this.row_index == DrawActivity.this.listItemDefaultPos) {
                    myViewHolder.f4619b.setVisibility(View.VISIBLE);
                    int unused = DrawActivity.this.listItemDefaultPos = -1;
                    return;
                }
                myViewHolder.f4619b.setVisibility(View.INVISIBLE);
            } else if (DrawActivity.this.row_index == i) {
                myViewHolder.f4619b.setVisibility(View.VISIBLE);
            } else {
                myViewHolder.f4619b.setVisibility(View.INVISIBLE);
            }
        }

        public int getItemCount() {
            return this.f4613a.size();
        }

        int i;

        public void onBindViewHolder(MyViewHolder myViewHolder, @SuppressLint({"RecyclerView"}) final int i) {
            myViewHolder.f4618a.setImageResource(this.f4613a.get(i).imageId);
            myViewHolder.f4618a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.closeAllDrawer();
                    DrawActivity.this.brushSelectedOnClickButton();
                    int unused = DrawActivity.this.row_index = i;
                    HorizontalAdapter.this.notifyDataSetChanged();
                    if (DrawActivity.this.ispatternClicked) {
                        DrawActivity.myDrawView.setPattern(HorizontalAdapter.this.f4613a.get(i).getTxt());
                        DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                        return;
                    }
                    HorizontalAdapter horizontalAdapter = HorizontalAdapter.this;
                    MyConstant.drawColor = ContextCompat.getColor(horizontalAdapter.f4614b, horizontalAdapter.f4613a.get(i).getColorId());
                    DrawActivity.myDrawView.setPathColor(MyConstant.drawColor);
                    DrawActivity.this.horizontal_recycler_view_frameview.setBackgroundColor(MyConstant.drawColor);
                    DrawActivity.this.leftTop.setBackgroundColor(MyConstant.drawColor);

                    if (i == 0) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_white);
                    } else if (i == 1) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_black);
                    } else if (i == 2) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_brown);
                    } else if (i == 3) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_pink);
                    } else if (i == 4) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_purple);
                    } else if (i == 5) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_blue);
                    } else if (i == 6) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_green);
                    } else if (i == 7) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_yellow);
                    } else if (i == 8) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_orange);
                    } else if (i == 9) {
                        DrawActivity.this.mediaPlayer.playSound(R.raw.color_red);
                    }
                }
            });
            isDefaultPosition(DrawActivity.this.row_index);
            setSelectedColorTick(myViewHolder, i);
        }

        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_menu, viewGroup, false));
        }
    }


    public void animateClick(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view.startAnimation(loadAnimation);
    }


    public void brushSelectedOnClickButton() {
        System.err.println("brushSelectedOnClickButton 1");
        if (MyConstant.erase) {
            System.err.println("brushSelectedOnClickButton 2");
            menuSelectedClick(2);
            MyConstant.selectedTool = 1;
            MyConstant.strokeWidth = MyConstant.brushWidth;
            MyConstant.erase = false;
        }
        if (MyConstant.selectedTool == 0 && this.ispatternClicked) {
            System.err.println("brushSelectedOnClickButton 3");
            menuSelectedClick(2);
            MyConstant.selectedTool = 1;
            MyConstant.strokeWidth = MyConstant.brushWidth;
            MyConstant.erase = false;
        }
    }


    public void closeAllDrawer() {
        this.drawerLayout.closeDrawer(this.drawerView);
        this.drawerLayout1.closeDrawer(this.drawerView1);
        this.isdraweropened = false;
        this.isdraweropened1 = false;
    }


    public void disableDrawer() {
        this.drawerLayout.setVisibility(View.GONE);
        this.drawerLayout.setFocusable(false);
        this.drawerLayout.setClickable(false);
        this.drawerLayout.setEnabled(false);
    }


    public void disableDrawer1() {
        this.drawerLayout1.setVisibility(View.GONE);
        this.drawerLayout1.setFocusable(false);
        this.drawerLayout1.setClickable(false);
        this.drawerLayout1.setEnabled(false);
    }

    private void drawerImplementation() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.dr_layout);
        this.drawerView = findViewById(R.id.drawer);
        this.drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                boolean unused = DrawActivity.this.isdraweropened = false;
                boolean unused2 = DrawActivity.this.isdraweropened1 = false;
                return true;
            }
        });
        this.iv1 = findViewById(R.id.iv1);
        this.iv2 = findViewById(R.id.iv2);
        this.iv3 = findViewById(R.id.iv3);
        this.drawerLayout.closeDrawer(this.drawerView);
        this.isdraweropened = false;
        this.iv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                boolean unused = DrawActivity.this.isdraweropened = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                boolean unused2 = DrawActivity.this.ispatternClicked = false;
                DrawActivity.this.data.clear();
                DrawActivity drawActivity = DrawActivity.this;
                List unused3 = drawActivity.data = drawActivity.fill_with_data2();
                DrawActivity.this.refreshData();
            }
        });
        this.iv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                boolean unused = DrawActivity.this.isdraweropened = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                DrawActivity.this.data.clear();
                boolean unused2 = DrawActivity.this.ispatternClicked = true;
                DrawActivity drawActivity = DrawActivity.this;
                List unused3 = drawActivity.data = drawActivity.fill_with_data();
                DrawActivity.this.refreshData();
            }
        });
        this.iv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                boolean unused = DrawActivity.this.isdraweropened = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                DrawActivity.this.data.clear();
                boolean unused2 = DrawActivity.this.ispatternClicked = true;
                DrawActivity drawActivity = DrawActivity.this;
                List unused3 = drawActivity.data = drawActivity.fill_with_data1();
                DrawActivity.this.refreshData();
            }
        });
        ActionBarDrawerToggle r2 = new ActionBarDrawerToggle(this, this.drawerLayout, new Toolbar(this), R.string.app_name, R.string.black_bat) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                DrawActivity.this.mediaPlayer.playSound(R.raw.drawer_close);
                DrawActivity.this.disableDrawer();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                DrawActivity.this.mediaPlayer.playSound(R.raw.drawer);
                DrawActivity.this.enableDrawer();
            }
        };
        this.mDrawerTogle = r2;
        this.drawerLayout.addDrawerListener(r2);
    }

    private void drawerImplementation1() {
        this.drawerLayout1 = (DrawerLayout) findViewById(R.id.dr_layout1);
        this.drawerView1 = findViewById(R.id.drawer1);
        this.drawerLayout1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                boolean unused = DrawActivity.this.isdraweropened = false;
                boolean unused2 = DrawActivity.this.isdraweropened1 = false;
                return true;
            }
        });
        this.iv11 = findViewById(R.id.iv11);
        this.iv21 = findViewById(R.id.iv21);
        this.iv31 = findViewById(R.id.iv31);
        this.drawerLayout1.closeDrawer(this.drawerView1);
        this.isdraweropened1 = false;
        this.iv31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                boolean unused = DrawActivity.this.isdraweropened1 = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                MyConstant.selectedTool = 2;
                MyConstant.strokeWidth = MyConstant.penWidth;
                MyConstant.erase = false;
            }
        });
        this.iv21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                boolean unused = DrawActivity.this.isdraweropened1 = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                MyConstant.selectedTool = 1;
                MyConstant.strokeWidth = MyConstant.brushWidth;
                MyConstant.erase = false;
            }
        });
        this.iv11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                boolean unused = DrawActivity.this.isdraweropened1 = false;
                DrawActivity.this.mediaPlayer.playSound(R.raw.select);
                MyConstant.selectedTool = 4;
                MyConstant.strokeWidth = MyConstant.brush1Width;
                MyConstant.erase = false;
            }
        });
        ActionBarDrawerToggle r2 = new ActionBarDrawerToggle(this, this.drawerLayout1, new Toolbar(this), R.string.app_name, R.string.black_bat) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                DrawActivity.this.mediaPlayer.playSound(R.raw.drawer_close);
                DrawActivity.this.disableDrawer1();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                DrawActivity.this.mediaPlayer.playSound(R.raw.drawer);
                DrawActivity.this.enableDrawer1();
            }
        };
        this.mDrawerTogle1 = r2;
        this.drawerLayout1.addDrawerListener(r2);
    }


    public void enableDrawer() {
        this.drawerLayout.setVisibility(View.VISIBLE);
        this.drawerLayout.setFocusable(true);
        this.drawerLayout.setClickable(true);
        this.drawerLayout.setEnabled(true);
    }


    public void enableDrawer1() {
        this.drawerLayout1.setVisibility(View.VISIBLE);
        this.drawerLayout1.setFocusable(true);
        this.drawerLayout1.setClickable(true);
        this.drawerLayout1.setEnabled(true);
    }


    public void finishActivity() {
        this.mediaPlayer.StopMp();
        this.mediaPlayer.playSound(R.raw.click);
        finish();
        MyAdmob.showInterstitial(this);
        MyConstant.showNewApp = true;
    }

    private void intialize() {
        MyConstant.heightInPixels = DisplayManager.getScreenHeight(this);
        MyConstant.widthInPixels = DisplayManager.getScreenWidth(this);
        double d2 = (double) MyConstant.heightInPixels;
        double d3 = (double) MyConstant.widthInPixels;
        Double.isNaN(d2);
        Double.isNaN(d3);
        MyConstant.screenRatio = d2 / d3;
        MyConstant.brushWidth = MyConstant.heightInPixels / 30;
        MyConstant.brush1Width = MyConstant.heightInPixels / 15;
        MyConstant.penWidth = MyConstant.heightInPixels / 70;
        MyConstant.eraseWidth = MyConstant.heightInPixels / 12;
        MyConstant.strokeWidth = MyConstant.brushWidth;
        MyConstant.eraseR = (float) (MyConstant.eraseWidth / 2);
        MyConstant.erase = false;
        MyConstant.selectedTool = -1;
        this.writePermission = false;
    }


    public void menuSelectedClick(int i2) {
        if (i2 == 1 || i2 == 2) {
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu1pencil_sel)).into(this.pen);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.but_10)).into(this.sound);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu4eraser)).into(this.eraser);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu5save)).into(this.save);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.btn_back1)).into(this.mPaint);
        } else if (i2 == 3) {
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu1pencil)).into(this.pen);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.but_10)).into(this.sound);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu4eraser_sel)).into(this.eraser);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu5save)).into(this.save);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.btn_back1)).into(this.mPaint);
        } else if (i2 == 4) {
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu1pencil)).into(this.pen);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.but_10)).into(this.sound);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu4eraser)).into(this.eraser);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu5save_sel)).into(this.save);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.btn_back1)).into(this.mPaint);
        } else if (i2 == 7) {
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu1pencil)).into(this.pen);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.but_10)).into(this.sound);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu4eraser)).into(this.eraser);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.menu5save)).into(this.save);
            Glide.with((Activity) this).load(Integer.valueOf(R.drawable.btn_back1)).into(this.mPaint);
            this.ispatternClicked = false;
            this.data = fill_with_data2();
            refreshData();
            setDefaultColor();
            MyConstant.strokeWidth = MyConstant.penWidth;
        }
    }


    public void refreshData() {
        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this.data, getApplication());
        this.f4580d = horizontalAdapter;
        this.f4579c.setAdapter(horizontalAdapter);
        int itemCount = this.f4580d.getItemCount() - 1;
        this.listItemDefaultPos = itemCount;
        this.row_index = itemCount;
        this.f4580d.notifyDataSetChanged();
    }


    public void saveBitmap() {
        System.err.println("saveBitmap 1::");
        requestPermissionWrite();
        PrintStream printStream = System.err;
        printStream.println("saveBitmap 2::" + this.writePermission);
        if (this.writePermission) {
            System.err.println("saveBitmap 3::");
            myDrawView.setDrawingCacheEnabled(true);
            System.err.println("saveBitmap 4::");
            try {
                CapturePhotoUtils.insertImage(this, getContentResolver(), myDrawView.getDrawingCache(), "drawing", "storage");
                System.err.println("saveBitmap 5::");
                this.mediaPlayer.playSound(R.raw.camera_click);
            } catch (Exception unused) {
            }
            myDrawView.destroyDrawingCache();
        }
    }

    private void saveBitmap1() {
        System.err.println("saveBitmap 1::");
        requestPermissionWrite();
        PrintStream printStream = System.err;
        printStream.println("saveBitmap 2::" + this.writePermission);
        if (this.writePermission) {
            System.err.println("saveBitmap 3::");
            myDrawView.setDrawingCacheEnabled(true);
            System.err.println("saveBitmap 4::");
            try {
                CapturePhotoUtils.insertImage(this, getContentResolver(), myDrawView.getDrawingCache(), "drawing", "storage");
                System.err.println("saveBitmap 5::");
                this.mediaPlayer.playSound(R.raw.camera_click);
                System.err.println("saveBitmap 5::");
            } catch (Exception unused) {
            }
            myDrawView.destroyDrawingCache();
        }
    }

    private void saveBitmapOnBackPressed() {
        requestPermissionWrite();
        if (this.writePermission) {
            myDrawView.setDrawingCacheEnabled(true);
            try {
                CapturePhotoUtils.insertImage(this, getContentResolver(), myDrawView.getDrawingCache(), "drawing", "storage");
                this.mediaPlayer.playSound(R.raw.camera_click);
                myart = myDrawView.getDrawingCache();
            } catch (Exception unused) {
            }
            finishActivity();
        }
    }

    private void setAd() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.adViewTop);
        if (SharedPreference.getBuyChoise(this) == 0) {
            this.myAdView.SetAD(frameLayout);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private void setDefaultColor() {
        int color = ContextCompat.getColor(this, R.color.color2);
        MyConstant.drawColor = color;
        this.horizontal_recycler_view_frameview.setBackgroundColor(color);
        turnEraserToBrush();
        this.leftTop.setBackgroundColor(MyConstant.drawColor);
        this.row_index = this.f4580d.getItemCount() - 1;
        this.listItemDefaultPos = this.f4580d.getItemCount() - 1;
        this.f4580d.notifyDataSetChanged();
    }

    public void deletePageDialog() {
        int screenHeight = DisplayManager.getScreenHeight(this);
        int screenWidth = DisplayManager.getScreenWidth(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.height = screenHeight - (screenHeight / 5);
        layoutParams.width = screenWidth - (screenWidth / 5);
        layoutParams.gravity = 17;
        try {
            final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
            dialog.getWindow().setFlags(8, 8);
            dialog.setContentView(R.layout.dialog_draw_save);
            RemoveBackButton.hideNavigationDialog(dialog);
            ((ImageView) dialog.findViewById(R.id.imageview)).setImageResource(R.drawable.delete_page);
            ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);
            ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivity.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            try {
                                DrawActivity.this.menuSelectedClick(7);
                                DrawingPicture.drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                                DrawActivity.this.drawerLayout.closeDrawer(DrawActivity.this.drawerView);
                                DrawActivity.this.drawerLayout1.closeDrawer(DrawActivity.this.drawerView1);
                                boolean unused = DrawActivity.this.isdraweropened = false;
                                boolean unused2 = DrawActivity.this.isdraweropened1 = false;
                            } catch (Exception unused3) {
                            }
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
        } catch (Exception e2) {
            Log.d(TAG, "showQuitAppDialog: " + e2.toString());
        }
    }

    public void determineScreenSize() {
        if ((getResources().getConfiguration().screenLayout & 15) == 3) {
            System.err.println("Screen Size1: LARGE");
            SCREEN_SIZE = 1;
        } else if ((getResources().getConfiguration().screenLayout & 15) == 2) {
            System.err.println("Screen Size1: NORMAL");
            SCREEN_SIZE = 3;
        } else if ((getResources().getConfiguration().screenLayout & 15) == 1) {
            System.err.println("Screen Size1: SMALL");
            SCREEN_SIZE = 2;
        } else if ((getResources().getConfiguration().screenLayout & 15) == 4) {
            System.err.println("Screen Size1: XLARGE");
            SCREEN_SIZE = 4;
        } else {
            System.err.println("Screen Size1: UNKNOWN_CATEGORY_SCREEN_SIZE");
            SCREEN_SIZE = 5;
        }
    }

    public List<Data> fill_with_data() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Data(1, R.drawable.git_1, "g_1", R.color.color25));
        arrayList.add(new Data(2, R.drawable.git_2, "g_2", R.color.color24));
        arrayList.add(new Data(3, R.drawable.git_3, "g_3", R.color.color23));
        arrayList.add(new Data(4, R.drawable.git_4, "g_4", R.color.color22));
        arrayList.add(new Data(6, R.drawable.git_6, "g_6", R.color.color20));
        arrayList.add(new Data(7, R.drawable.git_7, "g_7", R.color.color19));
        arrayList.add(new Data(10, R.drawable.git_10, "g_10", R.color.color16));
        arrayList.add(new Data(11, R.drawable.git_11, "g_11", R.color.color15));
        return arrayList;
    }

    public List<Data> fill_with_data1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Data(1, R.drawable.pat_1, "patt_1", R.color.color25));
        arrayList.add(new Data(2, R.drawable.pat_2, "patt_2", R.color.color24));
        arrayList.add(new Data(3, R.drawable.pat_3, "patt_3", R.color.color23));
        arrayList.add(new Data(4, R.drawable.pat_4, "patt_4", R.color.color22));
        arrayList.add(new Data(5, R.drawable.pat_5, "patt_5", R.color.color21));
        arrayList.add(new Data(6, R.drawable.pat_6, "patt_6", R.color.color20));
        arrayList.add(new Data(7, R.drawable.pat_7, "patt_7", R.color.color19));
        arrayList.add(new Data(8, R.drawable.pat_8, "patt_8", R.color.color18));
        arrayList.add(new Data(9, R.drawable.pat_9, "patt_9", R.color.color17));
        arrayList.add(new Data(10, R.drawable.pat_10, "patt_10", R.color.color16));
        return arrayList;
    }

    public List<Data> fill_with_data2() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Data(10, R.drawable.white_1, "Image 1", R.color.cwhite));
        arrayList.add(new Data(9, R.drawable.black_10, "Image 2", R.color.cblack));
        arrayList.add(new Data(8, R.drawable.brown_9, "Image 3", R.color.cbrown));
        arrayList.add(new Data(7, R.drawable.pink_8, "Image 1", R.color.cpink));
        arrayList.add(new Data(6, R.drawable.blue_7, "Image 2", R.color.cpurple));
        arrayList.add(new Data(5, R.drawable.blue_6, "Image 3", R.color.cblue));
        arrayList.add(new Data(4, R.drawable.green_5, "Image 1", R.color.cgreen));
        arrayList.add(new Data(3, R.drawable.yellow_4, "Image 2", R.color.cyellow));
        arrayList.add(new Data(2, R.drawable.orange_3, "Image 3", R.color.corange));
        arrayList.add(new Data(1, R.drawable.red_2, "Image 1", R.color.cred));
        return arrayList;
    }

    public void initialization() {
        if (MyConstant.selectedTool == 1) {
            System.err.println("setDefaultColor onResume start selectedTool ");
            menuSelectedClick(1);
            MyConstant.selectedTool = 1;
            if (!this.navigationClicked) {
                MyConstant.strokeWidth = MyConstant.penWidth;
            }
            MyConstant.erase = false;
        }
        if (MyConstant.selectedImageFromBitmap > -1 && MyConstant.fromGridActivityColoringBook) {
            System.err.println("setDefaultColor onResume start selectedTool2 ");
            MyConstant.fromGridActivityColoringBook = false;
            insertKidBitmap(MyConstant.selectedImageFromBitmap);
            myDrawView.setVisibility(View.GONE);
            myDrawView.setVisibility(View.VISIBLE);
        }
    }

    public void insertKidBitmap(int i2) {
        try {
            PrintStream printStream = System.err;
            printStream.println("selectedImageFromBitmap::insertKidBitmap::" + myDrawView);
            int intValue = MyConstant.selected_bitmapIds[i2].intValue();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            myDrawView.kidBitmap = BitmapFactory.decodeResource(getResources(), intValue, options);
            PrintStream printStream2 = System.err;
            printStream2.println("selectedImageFromBitmap::this.myDrawView.kidBitmap::" + myDrawView.kidBitmap);
            double d2 = (double) MyConstant.drawHeight;
            double height = (double) myDrawView.kidBitmap.getHeight();
            Double.isNaN(d2);
            Double.isNaN(height);
            double d3 = d2 / height;
            double d4 = (double) MyConstant.drawWidth;
            double width = (double) myDrawView.kidBitmap.getWidth();
            Double.isNaN(d4);
            Double.isNaN(width);
            double d5 = d4 / width;
            PrintStream printStream3 = System.err;
            printStream3.println("selectedImageFromBitmap:: MyConstant.drawHeight::" + MyConstant.drawHeight + "," + MyConstant.drawWidth);
            PrintStream printStream4 = System.err;
            printStream4.println("selectedImageFromBitmap:: this.myDrawView.kidBitmap.getHeight()::" + myDrawView.kidBitmap.getHeight() + "," + myDrawView.kidBitmap.getWidth());
            PrintStream printStream5 = System.err;
            printStream5.println("selectedImageFromBitmap::scaleRatioHeight::" + d3 + "," + d5);
            double width2 = (double) myDrawView.kidBitmap.getWidth();
            Double.isNaN(width2);
            int i3 = (int) (width2 * d5);
            double height2 = (double) myDrawView.kidBitmap.getHeight();
            Double.isNaN(height2);
            int i4 = (int) (height2 * d3);
            DrawingPicture drawingPicture = myDrawView;
            drawingPicture.kidBitmap = Bitmap.createScaledBitmap(drawingPicture.kidBitmap, i3, i4, true);
            myDrawView.setKidsImage();
            if (MyConstant.selectedTool == -1) {
                MyConstant.selectedTool = 1;
            }
        } catch (Exception unused) {
        }
    }

    public void insertKidBitmapnew() {
        try {
            PrintStream printStream = System.err;
            printStream.println("selectedImageFromBitmap::insertKidBitmap::" + myDrawView);
            myDrawView.kidBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sheet);
            PrintStream printStream2 = System.err;
            printStream2.println("selectedImageFromBitmap::this.myDrawView.kidBitmap::" + myDrawView.kidBitmap);
            double d2 = (double) MyConstant.drawHeight;
            double height = (double) myDrawView.kidBitmap.getHeight();
            Double.isNaN(d2);
            Double.isNaN(height);
            double d3 = d2 / height;
            double d4 = (double) MyConstant.drawWidth;
            double width = (double) myDrawView.kidBitmap.getWidth();
            Double.isNaN(d4);
            Double.isNaN(width);
            double d5 = d4 / width;
            PrintStream printStream3 = System.err;
            printStream3.println("selectedImageFromBitmap:: MyConstant.drawHeight::" + MyConstant.drawHeight + "," + MyConstant.drawWidth);
            PrintStream printStream4 = System.err;
            printStream4.println("selectedImageFromBitmap:: this.myDrawView.kidBitmap.getHeight()::" + myDrawView.kidBitmap.getHeight() + "," + myDrawView.kidBitmap.getWidth());
            PrintStream printStream5 = System.err;
            printStream5.println("selectedImageFromBitmap::scaleRatioHeight::" + d3 + "," + d5);
            double width2 = (double) myDrawView.kidBitmap.getWidth();
            Double.isNaN(width2);
            int i2 = (int) (width2 * d5);
            double height2 = (double) myDrawView.kidBitmap.getHeight();
            Double.isNaN(height2);
            int i3 = (int) (height2 * d3);
            DrawingPicture drawingPicture = myDrawView;
            drawingPicture.kidBitmap = Bitmap.createScaledBitmap(drawingPicture.kidBitmap, i2, i3, true);
            myDrawView.setKidsImage();
            if (MyConstant.selectedTool == -1) {
                MyConstant.selectedTool = 1;
            }
        } catch (Exception unused) {
        }
    }

    public void onBackPressed() {
        System.err.println("onBackPresseddd1");
        savePageDialog();
    }

    public void onClick(View view) {
        this.mediaPlayer.playSound(R.raw.click);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setFocusableInTouchMode(false);
        switch (view.getId()) {
            case R.id.back:
                this.drawerLayout.closeDrawer(this.drawerView);
                this.drawerLayout1.closeDrawer(this.drawerView1);
                this.isdraweropened = false;
                this.isdraweropened1 = false;
                savePageDialog();
                return;
            case R.id.bt1:
                enableDrawer();
                if (!this.isdraweropened) {
                    this.drawerLayout.openDrawer(this.drawerView);
                    this.isdraweropened = true;
                    return;
                }
                this.drawerLayout.closeDrawer(this.drawerView);
                this.isdraweropened = false;
                return;
            case R.id.eraser:
                menuSelectedClick(3);
                MyConstant.selectedTool = 3;
                MyConstant.strokeWidth = MyConstant.eraseWidth;
                MyConstant.erase = true;
                this.drawerLayout.closeDrawer(this.drawerView);
                this.drawerLayout1.closeDrawer(this.drawerView1);
                this.isdraweropened = false;
                this.isdraweropened1 = false;
                return;
            case R.id.mPaint:
                deletePageDialog();
                return;
            case R.id.next:
                this.navigationClicked = true;
                this.ispatternClicked = false;
                this.data = fill_with_data2();
                refreshData();
                setDefaultColor();
                MyConstant.strokeWidth = MyConstant.penWidth;
                if (MyConstant.selectedImageFromBitmap < MyConstant.selected_bitmapIds.length - 2) {
                    this.previous.setVisibility(View.VISIBLE);
                    this.next.setVisibility(View.VISIBLE);
                    MyConstant.selectedImageFromBitmap++;
                    MyConstant.fromGridActivityColoringBook = true;
                    MyConstant.selectedTool = 1;
                    DrawingPicture.drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    this.mediaPlayer.playSound(MyConstant.selected_soundIds[MyConstant.selectedImageFromBitmap].intValue());
                    onResume();
                    return;
                }
                this.next.setVisibility(View.INVISIBLE);
                return;
            case R.id.pen:
                if (this.ispatternClicked) {
                    myDrawView.setPattern(this.data.get(this.row_index).getTxt());
                } else {
                    myDrawView.setPathColor(MyConstant.drawColor);
                }
                menuSelectedClick(1);
                MyConstant.erase = false;
                MyConstant.selectedTool = 2;
                MyConstant.strokeWidth = MyConstant.penWidth;
                enableDrawer1();
                if (!this.isdraweropened1) {
                    this.drawerLayout1.openDrawer(this.drawerView1);
                    this.isdraweropened1 = true;
                    return;
                }
                this.drawerLayout1.closeDrawer(this.drawerView1);
                this.isdraweropened1 = false;
                return;
            case R.id.previous:
                this.navigationClicked = true;
                this.ispatternClicked = false;
                this.data = fill_with_data2();
                refreshData();
                setDefaultColor();
                MyConstant.strokeWidth = MyConstant.penWidth;
                if (MyConstant.selectedImageFromBitmap > 0) {
                    this.previous.setVisibility(View.VISIBLE);
                    this.next.setVisibility(View.VISIBLE);
                    MyConstant.selectedImageFromBitmap--;
                    MyConstant.fromGridActivityColoringBook = true;
                    MyConstant.selectedTool = 1;
                    DrawingPicture.drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    this.mediaPlayer.playSound(MyConstant.selected_soundIds[MyConstant.selectedImageFromBitmap].intValue());
                    onResume();
                    return;
                }
                this.previous.setVisibility(View.INVISIBLE);
                return;
            case R.id.save:
                menuSelectedClick(4);
                savePageDialog1();
                this.drawerLayout.closeDrawer(this.drawerView);
                this.drawerLayout1.closeDrawer(this.drawerView1);
                this.isdraweropened = false;
                this.isdraweropened1 = false;
                return;
            case R.id.sound:
                try {
                    menuSelectedClick(2);
                    this.drawerLayout.closeDrawer(this.drawerView);
                    this.drawerLayout1.closeDrawer(this.drawerView1);
                    this.isdraweropened = false;
                    this.isdraweropened1 = false;
                    if (patternSoundplay) {
                        this.mediaPlayer.playSound(R.raw.colortouch6);
                        return;
                    } else {
                        this.mediaPlayer.playSound(MyConstant.selected_soundIds[MyConstant.selectedImageFromBitmap].intValue());
                        return;
                    }
                } catch (Exception unused) {
                }
            default:
                return;
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MyConstant.isViewRender = false;
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
        this.navigationClicked = false;
        getWindow().getDecorView().setSystemUiVisibility(4096);
        MyConstant.selected_bitmapIds = MyConstant.bitmapIds21;
        MyConstant.selected_soundIds = MyConstant.soundIds21;
        determineScreenSize();
        MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic2 = new MediaPlayerSoundAndMusic();
        this.mediaPlayerSoundAndMusic = mediaPlayerSoundAndMusic2;
        mediaPlayerSoundAndMusic2.instializeMusic(this, R.raw.num_coloringbook);
        intialize();
        setContentView(R.layout.activity_draw1);
        this.top_l1 = (LinearLayout) findViewById(R.id.top_l1);
        this.f4579c = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        this.horizontal_recycler_view_frameview = (FrameLayout) findViewById(R.id.horizontal_recycler_view_frameview);
        this.leftTop = (LinearLayout) findViewById(R.id.leftTop);
        List<Data> fill_with_data2 = fill_with_data2();
        this.data = fill_with_data2;
        this.f4580d = new HorizontalAdapter(fill_with_data2, getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        this.f4579c.setLayoutManager(linearLayoutManager);
        this.f4579c.setAdapter(this.f4580d);
        linearLayoutManager.setStackFromEnd(true);
        this.mediaPlayer = new MyMediaPlayer(this);
        myDrawView = (DrawingPicture) findViewById(R.id.draw);
        iv = (ImageView) findViewById(R.id.iv);
        this.pen = (ImageView) findViewById(R.id.pen);
        this.eraser = (ImageView) findViewById(R.id.eraser);
        this.save = (ImageView) findViewById(R.id.save);
        this.bt1 = (ImageView) findViewById(R.id.bt1);
        this.mPaint = (ImageView) findViewById(R.id.mPaint);
        this.back = (ImageView) findViewById(R.id.back);
        this.next = (Button) findViewById(R.id.next);
        this.sound = (ImageView) findViewById(R.id.sound);
        this.previous = (Button) findViewById(R.id.previous);
        this.next.setBackgroundResource(R.drawable.arrow1);
        this.previous.setBackgroundResource(R.drawable.arrow2);
        this.pen.setOnClickListener(this);
        this.eraser.setOnClickListener(this);
        this.save.setOnClickListener(this);
        this.back.setOnClickListener(this);
        this.next.setOnClickListener(this);
        this.bt1.setOnClickListener(this);
        this.previous.setOnClickListener(this);
        this.sound.setOnClickListener(this);
        this.mPaint.setOnClickListener(this);
        this.horizontal_recycler_view_frameview.setBackgroundColor(MyConstant.drawColor);
        this.leftTop.setBackgroundColor(MyConstant.drawColor);
        drawerImplementation();
        drawerImplementation1();
        this.top_l1.post(new Runnable() {
            public void run() {
                MyConstant.isViewRender = true;
                DrawActivity.this.initialization();
            }
        });
        setDefaultColor();
        this.myAdView = new MyAdView(this);

        setAd();
    }

    public void onDestroy() {
        try {
            Bitmap bitmap = myDrawView.canvasBitmap;
            if (bitmap != null) {
                bitmap.recycle();
                myDrawView.canvasBitmap = null;
            }
            Bitmap bitmap2 = myDrawView.kidBitmap;
            if (bitmap2 != null) {
                bitmap2.recycle();
                myDrawView.kidBitmap = null;
            }
        } catch (Exception unused) {
        }
        System.gc();
        Runtime.getRuntime().gc();
        this.mediaPlayerSoundAndMusic.destroyMusic();
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
        this.mediaPlayerSoundAndMusic.pauseMainMusic();
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                this.writePermission = false;
                ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE");
                return;
            }
            this.writePermission = true;
            saveBitmap();
        }
    }

    public void onResume() {
        super.onResume();
        disableDrawer();
        disableDrawer1();
        RemoveBackButton.hideBackButtonBar(this);
        System.out.println("onresumecalled");
        if (GridActivityColoring.blank) {
            this.next.setVisibility(View.INVISIBLE);
            this.previous.setVisibility(View.INVISIBLE);
            patternSoundplay = true;
            GridActivityColoring.blank = false;
        } else if (GridActivityColoring.line) {
            this.next.setVisibility(View.INVISIBLE);
            this.previous.setVisibility(View.INVISIBLE);
            patternSoundplay = true;
            GridActivityColoring.line = false;
            insertKidBitmapnew();
        } else {
            patternSoundplay = false;
        }
        if (MyConstant.selectedImageFromBitmap >= MyConstant.selected_bitmapIds.length - 2) {
            this.next.setVisibility(View.INVISIBLE);
        }
        if (MyConstant.selectedImageFromBitmap <= 0) {
            this.previous.setVisibility(View.INVISIBLE);
        }
        this.top_l1.post(new Runnable() {
            public void run() {
                if (MyConstant.isViewRender) {
                    DrawActivity.this.initialization();
                }
            }
        });
        if (MyConstant.MUSIC_SETTING == MyConstant.MUSIC_ON) {
            this.mediaPlayerSoundAndMusic.startMainMusic();
        }
    }

    public void onStart() {
        super.onStart();
        if (MyConstant.MUSIC_SETTING == MyConstant.MUSIC_ON) {
            this.mediaPlayerSoundAndMusic.startMainMusic();
        }
    }

    public void onStop() {
        super.onStop();
        this.mediaPlayerSoundAndMusic.pauseMainMusic();
    }

    public void requestPermissionWrite() {
        if (Build.VERSION.SDK_INT < 23) {
            Log.v(TAG, "Permission is granted");
            this.writePermission = true;
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission is granted");
            this.writePermission = true;
        } else {
            Log.v(TAG, "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    public void requestPermissionWrite1() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            this.writePermission = true;
        }
    }

    public void savePageDialog() {
        int screenHeight = DisplayManager.getScreenHeight(this);
        int screenWidth = DisplayManager.getScreenWidth(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.height = screenHeight - (screenHeight / 5);
        layoutParams.width = screenWidth - (screenWidth / 5);
        layoutParams.gravity = 17;
        try {
            final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
            dialog.getWindow().setFlags(8, 8);
            dialog.setContentView(R.layout.dialog_draw_exit);
            RemoveBackButton.hideNavigationDialog(dialog);
            ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);
            ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivity.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            DrawActivity.this.finishActivity();
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    DrawActivity.this.mediaPlayer.StopMp();
                    DrawActivity.this.mediaPlayer.playSound(R.raw.byebye);
                }
            }, 200);
        } catch (Exception e2) {
            Log.d(TAG, "showQuitAppDialog: " + e2.toString());
        }
    }

    public void savePageDialog1() {
        int screenHeight = DisplayManager.getScreenHeight(this);
        int screenWidth = DisplayManager.getScreenWidth(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.height = screenHeight - (screenHeight / 5);
        layoutParams.width = screenWidth - (screenWidth / 5);
        layoutParams.gravity = 17;
        try {
            final Dialog dialog = new Dialog(this, R.style.AlertDialogCustom);
            dialog.getWindow().setFlags(8, 8);
            dialog.setContentView(R.layout.dialog_draw_save);
            RemoveBackButton.hideNavigationDialog(dialog);
            ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);
            ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivity.this.animateClick(view);
                    DrawActivity.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivity.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            DrawActivity.this.saveBitmap();
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
        } catch (Exception e2) {
            Log.d(TAG, "showQuitAppDialog: " + e2.toString());
        }
    }

    public void turnEraserToBrush() {
        MyConstant.selectedTool = 1;
        MyConstant.strokeWidth = MyConstant.brushWidth;
        MyConstant.erase = false;
    }
}
