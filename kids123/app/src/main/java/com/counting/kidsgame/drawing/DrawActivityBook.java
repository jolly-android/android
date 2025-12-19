package com.counting.kidsgame.drawing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawActivityBook extends Activity implements View.OnClickListener {
    private static final int BRUSH = 2;
    public static int DRAW_COLOR = 0;
    private static final int ERASER = 3;
    private static final int NEW_PAGE = 5;
    private static final int PAINT = 6;
    private static final int PEN = 1;
    private static final int SAVE = 4;
    private static final String TAG = "DrawActivityBook";
    private static final int ZOOM = 7;
    public static DrawActivityBook drawActivity = null;
    public static boolean ispatternClicked = false;
    public static ImageView iv;

    public static DrawingPictureBook myDrawViewBook;
    public static int newHeight;
    public static int newWidth;


    boolean f4625a;
    private ImageView back;
    private ImageView choose_colortype;
    private ImageView clear;

    public DrawerLayout dr_layout_brush;

    public DrawerLayout dr_layout_color;

    public View drawerViewBrush;

    public View drawerViewColor;
    private ImageView eraser;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view;

    public FrameLayout horizontal_recycler_view_frameview;

    public boolean isdraweropenedBrush = false;

    public boolean isdraweropenedColor = false;

    public LinearLayout leftTop;

    public int listItemDefaultPos = -1;
    private ImageView mPaint;
    public MyMediaPlayer mediaPlayer;
    private MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic;
    private MyAdView myAdView;
    private ImageView newPage;
    private ImageView pen;

    public int row_index = -1;
    private ImageView save;
    private boolean writePermission;

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<Data> f4657a;


        Context f4658b;

        public class MyViewHolder extends RecyclerView.ViewHolder {


            ImageView f4662a;


            ImageView f4663b;

            public MyViewHolder(View view) {
                super(view);
                this.f4662a = (ImageView) view.findViewById(R.id.imageview);
                this.f4663b = (ImageView) view.findViewById(R.id.imageviewTick);
            }
        }

        public HorizontalAdapter(List<Data> list, Application application) {
            Collections.emptyList();
            this.f4657a = list;
            this.f4658b = application;
        }

        private void isDefaultPosition(int i) {
            if (i == DrawActivityBook.this.listItemDefaultPos) {
                DrawActivityBook.this.brushSelectedOnClickButton();
                if (MyConstant.erase) {
                    DrawActivityBook.this.turnEraserToBrush();
                }
                if (DrawActivityBook.ispatternClicked) {
                    DrawActivityBook.myDrawViewBook.setPattern(this.f4657a.get(i).getTxt());
                    return;
                }
                MyConstant.DRAW_COLOR = ContextCompat.getColor(this.f4658b, this.f4657a.get(i).getColorId());
                DrawActivityBook.myDrawViewBook.setPathColor(MyConstant.DRAW_COLOR);
                DrawActivityBook.this.horizontal_recycler_view_frameview.setBackgroundColor(MyConstant.DRAW_COLOR);
                DrawActivityBook.this.leftTop.setBackgroundColor(MyConstant.DRAW_COLOR);
            }
        }

        private void setSelectedColorTick(MyViewHolder myViewHolder, int i) {
            if (DrawActivityBook.this.listItemDefaultPos != -1) {
                if (DrawActivityBook.this.row_index == DrawActivityBook.this.listItemDefaultPos) {
                    myViewHolder.f4663b.setVisibility(View.VISIBLE);
                    int unused = DrawActivityBook.this.listItemDefaultPos = -1;
                    return;
                }
                myViewHolder.f4663b.setVisibility(View.INVISIBLE);
            } else if (DrawActivityBook.this.row_index == i) {
                myViewHolder.f4663b.setVisibility(View.VISIBLE);
            } else {
                myViewHolder.f4663b.setVisibility(View.INVISIBLE);
            }
        }

        public int getItemCount() {
            return this.f4657a.size();
        }

        public void onBindViewHolder(MyViewHolder myViewHolder, @SuppressLint({"RecyclerView"}) final int i) {
            myViewHolder.f4662a.setImageResource(this.f4657a.get(i).imageId);
            myViewHolder.f4662a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    DrawActivityBook drawActivityBook = DrawActivityBook.this;
                    drawActivityBook.f4625a = false;
                    drawActivityBook.closeAllDrawer();
                    DrawActivityBook.this.brushSelectedOnClickButton();
                    int unused = DrawActivityBook.this.row_index = i;
                    HorizontalAdapter.this.notifyDataSetChanged();
                    if (DrawActivityBook.ispatternClicked) {
                        DrawActivityBook.myDrawViewBook.setPattern(HorizontalAdapter.this.f4657a.get(i).getTxt());
                        return;
                    }
                    HorizontalAdapter horizontalAdapter = HorizontalAdapter.this;
                    MyConstant.DRAW_COLOR = ContextCompat.getColor(horizontalAdapter.f4658b, horizontalAdapter.f4657a.get(i).getColorId());
                    DrawActivityBook.myDrawViewBook.setPathColor(MyConstant.DRAW_COLOR);
                    DrawActivityBook.this.horizontal_recycler_view_frameview.setBackgroundColor(MyConstant.DRAW_COLOR);
                    DrawActivityBook.this.leftTop.setBackgroundColor(MyConstant.DRAW_COLOR);
                }
            });
            isDefaultPosition(DrawActivityBook.this.row_index);
            setSelectedColorTick(myViewHolder, i);
        }

        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_menu, viewGroup, false));
        }
    }

    class paintClass1 implements DialogInterface.OnClickListener {


        final int f4665a;

        paintClass1(int i) {
            this.f4665a = i;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            DrawActivityBook.this.saveBitmap();
            int i2 = this.f4665a;
            if (i2 >= 0) {
                DrawActivityBook.this.insertKidBitmap(i2);
            } else {
                DrawActivityBook.myDrawViewBook.startNew();
            }
        }
    }

    class paintClass2 implements DialogInterface.OnClickListener {


        final int f4667a;

        paintClass2(int i) {
            this.f4667a = i;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            int i2 = this.f4667a;
            if (i2 >= 0) {
                DrawActivityBook.this.insertKidBitmap(i2);
            } else {
                DrawActivityBook.myDrawViewBook.startNew();
            }
        }
    }


    public void animateClick(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_low);
        loadAnimation.setDuration(100);
        view.startAnimation(loadAnimation);
    }


    public void brushSelectedOnClickButton() {
        if (MyConstant.SELECTED_TOOL == 2) {
            menuSelectedClick(1);
            MyConstant.SELECTED_TOOL = 1;
            MyConstant.strokeWidth = MyConstant.BRUSH_SIZE;
            MyConstant.erase = false;
        }
        if (MyConstant.SELECTED_TOOL == 0 && ispatternClicked) {
            menuSelectedClick(1);
            MyConstant.SELECTED_TOOL = 1;
            MyConstant.strokeWidth = MyConstant.BRUSH_SIZE;
            MyConstant.erase = false;
        }
    }

    private void chooseDrawingType() {
        MyConstant.selected_bitmapIds = MyConstant.bitmapIdsBook;
    }


    public void closeAllDrawer() {
        this.dr_layout_color.closeDrawer(this.drawerViewColor);
        this.dr_layout_brush.closeDrawer(this.drawerViewBrush);
        this.isdraweropenedColor = false;
        this.isdraweropenedBrush = false;
    }


    public void disableBrushDrawer(DrawerLayout drawerLayout) {
        drawerLayout.setVisibility(View.GONE);
        drawerLayout.setFocusable(false);
        drawerLayout.setClickable(false);
        drawerLayout.setEnabled(false);
    }


    public void disableColorDrawer(DrawerLayout drawerLayout) {
        drawerLayout.setVisibility(View.GONE);
        drawerLayout.setFocusable(false);
        drawerLayout.setClickable(false);
        drawerLayout.setEnabled(false);
    }

    private void drawerImplementationForBrush() {
        this.dr_layout_brush = (DrawerLayout) findViewById(R.id.dr_layout_brush);
        this.drawerViewBrush = findViewById(R.id.drawer_brush);
        this.dr_layout_brush.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DrawActivityBook.this.dr_layout_color.closeDrawer(DrawActivityBook.this.drawerViewColor);
                DrawActivityBook.this.dr_layout_brush.closeDrawer(DrawActivityBook.this.drawerViewBrush);
                boolean unused = DrawActivityBook.this.isdraweropenedColor = false;
                boolean unused2 = DrawActivityBook.this.isdraweropenedBrush = false;
                return true;
            }
        });
        View findViewById = findViewById(R.id.ivBrushSize1);
        View findViewById2 = findViewById(R.id.ivBrushSize2);
        View findViewById3 = findViewById(R.id.ivBrushSize3);
        this.dr_layout_brush.closeDrawer(this.drawerViewBrush);
        this.isdraweropenedBrush = false;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setBrushClick(drawActivityBook.dr_layout_brush, DrawActivityBook.this.drawerViewBrush, 20);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setBrushClick(drawActivityBook.dr_layout_brush, DrawActivityBook.this.drawerViewBrush, 40);
            }
        });
        findViewById3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setBrushClick(drawActivityBook.dr_layout_brush, DrawActivityBook.this.drawerViewBrush, 80);
            }
        });
        this.dr_layout_brush.addDrawerListener(new ActionBarDrawerToggle(this, this.dr_layout_brush, new Toolbar(this), R.string.app_name, R.string.black_bat) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                DrawActivityBook.this.mediaPlayer.playSound(R.raw.drawer_close);
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.disableBrushDrawer(drawActivityBook.dr_layout_brush);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                DrawActivityBook.this.mediaPlayer.playSound(R.raw.drawer);
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.enableBrushDrawer(drawActivityBook.dr_layout_brush);
            }
        });
    }

    private void drawerImplementationForColor() {
        this.dr_layout_color = (DrawerLayout) findViewById(R.id.dr_layout_color);
        this.drawerViewColor = findViewById(R.id.drawer_color);
        this.dr_layout_color.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DrawActivityBook.this.dr_layout_brush.closeDrawer(DrawActivityBook.this.drawerViewBrush);
                DrawActivityBook.this.dr_layout_color.closeDrawer(DrawActivityBook.this.drawerViewColor);
                boolean unused = DrawActivityBook.this.isdraweropenedBrush = false;
                boolean unused2 = DrawActivityBook.this.isdraweropenedColor = false;
                return true;
            }
        });
        View findViewById = findViewById(R.id.ivColorSize1);
        View findViewById2 = findViewById(R.id.ivColorSize2);
        View findViewById3 = findViewById(R.id.ivColorSize3);
        this.dr_layout_color.closeDrawer(this.drawerViewColor);
        this.isdraweropenedColor = false;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setFillType(drawActivityBook.dr_layout_color, DrawActivityBook.this.drawerViewColor, 0, false);
                MyConstant.TYPE_FILL = 0;
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setFillType(drawActivityBook.dr_layout_color, DrawActivityBook.this.drawerViewColor, 1, true);
                MyConstant.TYPE_FILL = 1;
            }
        });
        findViewById3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.setFillType(drawActivityBook.dr_layout_color, DrawActivityBook.this.drawerViewColor, 2, true);
                MyConstant.TYPE_FILL = 2;
            }
        });
        this.dr_layout_color.addDrawerListener(new ActionBarDrawerToggle(this, this.dr_layout_color, new Toolbar(this), R.string.app_name, R.string.black_bat) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                DrawActivityBook.this.mediaPlayer.playSound(R.raw.drawer_close);
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.disableColorDrawer(drawActivityBook.dr_layout_color);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                DrawActivityBook.this.mediaPlayer.playSound(R.raw.drawer);
                DrawActivityBook drawActivityBook = DrawActivityBook.this;
                drawActivityBook.enableColorDrawer(drawActivityBook.dr_layout_color);
            }
        });
    }


    public void enableBrushDrawer(DrawerLayout drawerLayout) {
        drawerLayout.setVisibility(View.VISIBLE);
        drawerLayout.setFocusable(true);
        drawerLayout.setClickable(true);
        drawerLayout.setEnabled(true);
    }


    public void enableColorDrawer(DrawerLayout drawerLayout) {
        drawerLayout.setVisibility(View.VISIBLE);
        drawerLayout.setFocusable(true);
        drawerLayout.setClickable(true);
        drawerLayout.setEnabled(true);
    }

    private void findByViewIds() {
        this.horizontal_recycler_view = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        this.horizontal_recycler_view_frameview = (FrameLayout) findViewById(R.id.horizontal_recycler_view_frameview);
        this.leftTop = (LinearLayout) findViewById(R.id.leftTop);
        myDrawViewBook = (DrawingPictureBook) findViewById(R.id.draw);
        this.pen = (ImageView) findViewById(R.id.pen);
        this.choose_colortype = (ImageView) findViewById(R.id.choose_colortype);
        this.back = (ImageView) findViewById(R.id.back);
        this.eraser = (ImageView) findViewById(R.id.eraser);
        this.save = (ImageView) findViewById(R.id.save);
        this.clear = (ImageView) findViewById(R.id.clear);
        this.mPaint = (ImageView) findViewById(R.id.mPaint);
        iv = (ImageView) findViewById(R.id.iv);
        this.pen.setOnClickListener(this);
        this.eraser.setOnClickListener(this);
        this.save.setOnClickListener(this);
        this.back.setOnClickListener(this);
        this.clear.setOnClickListener(this);
        this.mPaint.setOnClickListener(this);
        this.choose_colortype.setOnClickListener(this);
        this.back.setOnClickListener(this);
    }


    public void finishActivityNoSave() {
        this.mediaPlayer.StopMp();
        finish();
        MyAdmob.showInterstitial(this);
        MyConstant.showNewApp = true;
    }

    private String getDensityName() {
        int screenHeight = DisplayManager.getScreenHeight(this);
        return screenHeight <= 480 ? "mdpi" : (screenHeight > 720 || screenHeight <= 480) ? (screenHeight > 1080 || screenHeight <= 720) ? screenHeight > 1080 ? "xxhdpi" : "mdpi" : "xhdpi" : "hdpi";
    }

    public static DrawActivityBook getDrawActivityBook() {
        return drawActivity;
    }

    private List<Data> getFillTypeDate(int i) {
        if (i == 0) {
            return setBottomColorsList();
        }
        if (i == 1) {
            return setBottomPatternList();
        }
        if (i != 2) {
            return setBottomColorsList();
        }
        return setBottomGlitterList();
    }

    private void initialize() {
        MyConstant.heightInPixels = DisplayManager.getScreenHeight(this);
        MyConstant.widthInPixels = DisplayManager.getScreenWidth(this);
        double d2 = (double) MyConstant.heightInPixels;
        double d3 = (double) MyConstant.widthInPixels;
        Double.isNaN(d2);
        Double.isNaN(d3);
        MyConstant.screenRatio = d2 / d3;
        int i = MyConstant.heightInPixels / 12;
        MyConstant.eraseWidth = i;
        MyConstant.eraseR = (float) (i / 2);
        MyConstant.erase = false;
        MyConstant.SELECTED_TOOL = -1;
        this.writePermission = false;
        ispatternClicked = false;
    }

    private void initializeMediaPlayer() {
        MyMediaPlayer myMediaPlayer = new MyMediaPlayer(this);
        this.mediaPlayer = myMediaPlayer;
        myMediaPlayer.playSelectArtRandomSound();
        MediaPlayerSoundAndMusic mediaPlayerSoundAndMusic2 = new MediaPlayerSoundAndMusic();
        this.mediaPlayerSoundAndMusic = mediaPlayerSoundAndMusic2;
        mediaPlayerSoundAndMusic2.instializeMusic(this, R.raw.num_coloringbook);
    }

    private void initializeOnSizeChangedValue() {
        MyConstant.onSizeCalled = 0;
    }

    private void isBrushSelected() {
        int i = MyConstant.TYPE_FILL;
        if (i == 1 || i == 2) {
            this.pen.setImageResource(R.drawable.menu1pencil_sel);
            this.eraser.setImageResource(R.drawable.menu4eraser);
            this.mPaint.setImageResource(R.drawable.menu3bucket);
        }
    }

    private void menuSelectedClick(int i) {
        if (i == 1) {
            this.pen.setImageResource(R.drawable.menu1pencil_sel);
            this.eraser.setImageResource(R.drawable.menu4eraser);
            this.mPaint.setImageResource(R.drawable.menu3bucket);
        } else if (i == 2) {
            this.pen.setImageResource(R.drawable.menu1pencil);
            this.eraser.setImageResource(R.drawable.menu4eraser);
            this.mPaint.setImageResource(R.drawable.menu3bucket);
        } else if (i == 3) {
            this.pen.setImageResource(R.drawable.menu1pencil);
            this.eraser.setImageResource(R.drawable.menu4eraser_sel);
            this.mPaint.setImageResource(R.drawable.menu3bucket);
        } else if (i == 6) {
            this.pen.setImageResource(R.drawable.menu1pencil);
            this.eraser.setImageResource(R.drawable.menu4eraser);
            this.mPaint.setImageResource(R.drawable.menu3bucket_sel);
            ispatternClicked = false;
            refreshData(setBottomColorsList());
        } else if (i == 7) {
            this.pen.setImageResource(R.drawable.menu1pencil);
            this.eraser.setImageResource(R.drawable.menu4eraser);
            this.mPaint.setImageResource(R.drawable.menu3bucket);
        }
    }

    private void refreshData(List<Data> list) {
        HorizontalAdapter horizontalAdapter2 = new HorizontalAdapter(list, getApplication());
        this.horizontalAdapter = horizontalAdapter2;
        this.horizontal_recycler_view.setAdapter(horizontalAdapter2);
        int itemCount = this.horizontalAdapter.getItemCount() - 1;
        this.listItemDefaultPos = itemCount;
        this.row_index = itemCount;
        this.horizontalAdapter.notifyDataSetChanged();
    }


    public void saveBitmap() {
        requestPermissionWrite();
        if (this.writePermission) {
            myDrawViewBook.setDrawingCacheEnabled(true);
            try {
                CapturePhotoUtils.insertImage(this, getContentResolver(), myDrawViewBook.getDrawingCache(), "drawing", "storage");
                this.mediaPlayer.playSound(R.raw.camera_click);
            } catch (Exception unused) {
            }
            myDrawViewBook.destroyDrawingCache();
        }
    }

    private void saveBitmapOnBackPressed() {
        requestPermissionWrite();
        if (this.writePermission) {
            myDrawViewBook.setDrawingCacheEnabled(true);
            try {
                CapturePhotoUtils.insertImage(this, getContentResolver(), myDrawViewBook.getDrawingCache(), "drawing", "storage");
                this.mediaPlayer.playSound(R.raw.camera_click);
                finishActivityNoSave();
            } catch (Exception unused) {
            }
            myDrawViewBook.destroyDrawingCache();
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

    private void setBottomColorLayout(List<Data> list) {
        this.horizontalAdapter = new HorizontalAdapter(list, getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        this.horizontal_recycler_view.setLayoutManager(linearLayoutManager);
        this.horizontal_recycler_view.setAdapter(this.horizontalAdapter);
        linearLayoutManager.setStackFromEnd(true);
    }


    public void setBrushClick(DrawerLayout drawerLayout, View view, int i) {
        MyConstant.SELECTED_TOOL = 1;
        drawerLayout.closeDrawer(view);
        this.mediaPlayer.playSound(R.raw.select);
        this.isdraweropenedBrush = false;
        MyConstant.BRUSH_SIZE = i;
        MyConstant.erase = false;
    }

    private void setDefaultColor() {
        int color = ContextCompat.getColor(this, R.color.color2);
        MyConstant.DRAW_COLOR = color;
        this.horizontal_recycler_view_frameview.setBackgroundColor(color);
        this.leftTop.setBackgroundColor(MyConstant.DRAW_COLOR);
        turnEraserToBrush();
        this.row_index = 27;
        this.horizontalAdapter.notifyDataSetChanged();
    }


    public void setFillType(DrawerLayout drawerLayout, View view, int i, boolean z) {
        ispatternClicked = false;
        drawerLayout.closeDrawer(view);
        MyConstant.TYPE_FILL = i;
        this.isdraweropenedColor = false;
        ispatternClicked = z;
        refreshData(getFillTypeDate(i));
        isBrushSelected();
    }

    public void createNewPageDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_page_question).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNeutralButton(R.string.no, new paintClass2(i)).setPositiveButton(R.string.yes, new paintClass1(i));
        builder.create().show();
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
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivityBook.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            DrawingPictureBook.drawCanvasBook.drawColor(0, PorterDuff.Mode.CLEAR);
                            DrawActivityBook.myDrawViewBook.setKidsImage();
                            DrawActivityBook.this.closeAllDrawer();
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
        } catch (Exception e) {
            Log.d(TAG, "showQuitAppDialog: " + e.toString());
        }
    }

    public void insertBitmap() {
        if (MyConstant.selectedImageFromBitmap > -1 && MyConstant.fromGridActivityColoringBook) {
            MyConstant.fromGridActivityColoringBook = false;
            insertKidBitmap(MyConstant.selectedImageFromBitmap);
        }
    }
    
    char c2;
    int i2;
    double d2;
    double d3;
    double d4;
    double d5;
    double d6;
    double d7;

    public void insertKidBitmap(int i) {

        try {
            myDrawViewBook.kidBitmap = BitmapFactory.decodeResource(getResources(), MyConstant.selected_bitmapIds[i].intValue());
            double d8 = MyConstant.drawHeight;
            double height = myDrawViewBook.kidBitmap.getHeight();
            Double.isNaN(d8);
            Double.isNaN(height);
            double d9 = d8 / height;
            double d10 = MyConstant.drawWidth;
            double width = myDrawViewBook.kidBitmap.getWidth();
            Double.isNaN(d10);
            Double.isNaN(width);
            double d11 = d10 / width;
            String densityName = getDensityName();
            switch (densityName.hashCode()) {
                case -1619189395:
                    if (densityName.equals("xxxhdpi")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -745448715:
                    if (densityName.equals("xxhdpi")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 3197941:
                    if (densityName.equals("hdpi")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 3317105:
                    if (densityName.equals("ldpi")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 3346896:
                    if (densityName.equals("mdpi")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 114020461:
                    if (densityName.equals("xhdpi")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                double width2 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width2);
                newWidth = (int) (width2 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d2 * d9)) - 30;
            } else if (c2 == 1) {
                double width3 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width3);
                newWidth = (int) (width3 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d3 * d9)) - 50;
            } else if (c2 == 2) {
                double width4 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width4);
                newWidth = (int) (width4 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d4 * d9)) - 70;
            } else if (c2 == 3) {
                double width5 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width5);
                newWidth = (int) (width5 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d5 * d9)) - 120;
            } else if (c2 == 4) {
                double width6 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width6);
                newWidth = (int) (width6 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d6 * d9)) - 150;
            } else if (c2 == 5) {
                double width7 = myDrawViewBook.kidBitmap.getWidth();
                Double.isNaN(width7);
                newWidth = (int) (width7 * d11);
                Double.isNaN(myDrawViewBook.kidBitmap.getHeight());
                newHeight = ((int) (d7 * d9)) - 130;
            }
            int i3 = newHeight;
            if (i3 > 0 && (i2 = newWidth) > 0) {
                DrawingPictureBook drawingPictureBook = myDrawViewBook;
                drawingPictureBook.kidBitmap = Bitmap.createScaledBitmap(drawingPictureBook.kidBitmap, i2, i3, true);
                myDrawViewBook.setKidsImage();
                if (MyConstant.selectedTool == -1) {
                    MyConstant.selectedTool = 1;
                }
            }
            MyConstant.SELECTED_TOOL = 0;
            menuSelectedClick(6);
            MyConstant.strokeWidth = 0;
            MyConstant.erase = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onBackPressed() {
        savePageDialogOnBackPressed();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                savePageDialogOnBackPressed();
                closeAllDrawer();
                return;
            case R.id.choose_colortype:
                enableColorDrawer(this.dr_layout_color);
                if (!this.isdraweropenedColor) {
                    this.dr_layout_color.openDrawer(this.drawerViewColor);
                    this.isdraweropenedColor = true;
                    return;
                }
                this.dr_layout_color.closeDrawer(this.drawerViewColor);
                this.isdraweropenedColor = false;
                return;
            case R.id.clear:
                this.mediaPlayer.playSound(R.raw.click);
                deletePageDialog();
                System.gc();
                return;
            case R.id.eraser:
                menuSelectedClick(3);
                MyConstant.SELECTED_TOOL = 2;
                MyConstant.strokeWidth = MyConstant.eraseWidth;
                MyConstant.erase = true;
                closeAllDrawer();
                this.f4625a = true;
                return;
            case R.id.mPaint:
                menuSelectedClick(6);
                MyConstant.SELECTED_TOOL = 0;
                MyConstant.strokeWidth = 0;
                MyConstant.erase = false;
                closeAllDrawer();
                MyConstant.TYPE_FILL = 0;
                return;
            case R.id.pen:
                MyConstant.SELECTED_TOOL = 1;
                MyConstant.erase = false;
                menuSelectedClick(1);
                enableBrushDrawer(this.dr_layout_brush);
                if (!this.isdraweropenedBrush) {
                    this.dr_layout_brush.openDrawer(this.drawerViewBrush);
                    this.isdraweropenedBrush = true;
                } else {
                    this.dr_layout_brush.closeDrawer(this.drawerViewBrush);
                    this.isdraweropenedBrush = false;
                }
                if (this.f4625a) {
                    refreshData(getFillTypeDate(MyConstant.TYPE_FILL));
                    this.f4625a = false;
                    return;
                }
                return;
            case R.id.save:
                menuSelectedClick(4);
                savePageDialog();
                closeAllDrawer();
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
        drawActivity = this;
        initialize();
        initializeOnSizeChangedValue();
        initializeMediaPlayer();
        setContentView(R.layout.activity_draw_book);
        findByViewIds();
        setBottomColorLayout(getFillTypeDate(0));
        drawerImplementationForBrush();
        drawerImplementationForColor();
        chooseDrawingType();
        setDefaultColor();
        this.f4625a = false;
        this.myAdView = new MyAdView(this);

        setAd();
    }

    public void onDestroy() {
        Bitmap bitmap = myDrawViewBook.canvasBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            myDrawViewBook.canvasBitmap = null;
        }
        Bitmap bitmap2 = myDrawViewBook.kidBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            myDrawViewBook.kidBitmap = null;
        }
        super.onDestroy();
    }

    public void onPause() {
        this.mediaPlayerSoundAndMusic.pauseMainMusic();
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
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
        RemoveBackButton.hideBackButtonBar(this);
        disableColorDrawer(this.dr_layout_color);
        disableBrushDrawer(this.dr_layout_brush);
        if (MyConstant.MUSIC_SETTING == MyConstant.MUSIC_ON) {
            this.mediaPlayerSoundAndMusic.startMainMusic();
        }
        super.onResume();
    }

    public void onStop() {
        this.mediaPlayerSoundAndMusic.pauseMainMusic();
        super.onStop();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            RemoveBackButton.hideBackButtonBar(this);
        }
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
            dialog.setContentView(R.layout.dialog_draw_save);
            RemoveBackButton.hideNavigationDialog(dialog);
            ((ConstraintLayout) dialog.findViewById(R.id.bg_dialog)).setLayoutParams(layoutParams);
            ((ConstraintLayout) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivityBook.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            DrawActivityBook.this.saveBitmap();
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
        } catch (Exception e) {
            Log.d(TAG, "showQuitAppDialog: " + e.toString());
        }
    }

    public void savePageDialogOnBackPressed() {
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
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });
            ((ConstraintLayout) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DrawActivityBook.this.animateClick(view);
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.click);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DrawActivityBook.this.mediaPlayer.StopMp();
                            dialog.dismiss();
                            DrawActivityBook.this.finishActivityNoSave();
                        }
                    }, 300);
                }
            });
            dialog.show();
            dialog.getWindow().clearFlags(8);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    DrawActivityBook.this.mediaPlayer.StopMp();
                    DrawActivityBook.this.mediaPlayer.playSound(R.raw.byebye);
                }
            }, 200);
        } catch (Exception e) {
            Log.d(TAG, "showQuitAppDialog: " + e.toString());
        }
    }

    public List<Data> setBottomColorsList() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(new Data(1, R.drawable.c10_white, "Image 2", R.color.color25));
        arrayList.add(new Data(2, R.drawable.c9_grey2, "Image 1", R.color.color24));
        arrayList.add(new Data(3, R.drawable.c8_brown2, "Image 2", R.color.color23));
        arrayList.add(new Data(4, R.drawable.c8_brown1, "Image 1", R.color.color22));
        arrayList.add(new Data(6, R.drawable.c7_pink3, "Image 3", R.color.color21));
        arrayList.add(new Data(7, R.drawable.c7_pink2, "Image 2", R.color.color20));
        arrayList.add(new Data(8, R.drawable.c7_pink1, "Image 1", R.color.color19));
        arrayList.add(new Data(9, R.drawable.c6_purple3, "Image 3", R.color.color18));
        arrayList.add(new Data(10, R.drawable.c6_purple2, "Image 2", R.color.color17));
        arrayList.add(new Data(11, R.drawable.c6_purple1, "Image 1", R.color.color16));
        arrayList.add(new Data(12, R.drawable.c5_blue4, "Image 3", R.color.color15));
        arrayList.add(new Data(13, R.drawable.c5_blue3, "Image 2", R.color.color14));
        arrayList.add(new Data(14, R.drawable.c5_blue2, "Image 1", R.color.color13));
        arrayList.add(new Data(15, R.drawable.c5_blue1, "Image 3", R.color.color12));
        arrayList.add(new Data(17, R.drawable.c4_green4, "Image 2", R.color.color11));
        arrayList.add(new Data(18, R.drawable.c4_green3, "Image 1", R.color.color10));
        arrayList.add(new Data(19, R.drawable.c4_green2, "Image 3", R.color.color9));
        arrayList.add(new Data(20, R.drawable.c4_green1, "Image 2", R.color.color8));
        arrayList.add(new Data(22, R.drawable.c3_yellow2, "Image 1", R.color.color7));
        arrayList.add(new Data(23, R.drawable.c3_yellow1, "Image 3", R.color.color6));
        arrayList.add(new Data(25, R.drawable.c2_orange2, "Image 2", R.color.color5));
        arrayList.add(new Data(26, R.drawable.c2_orange1, "Image 1", R.color.color4));
        arrayList.add(new Data(27, R.drawable.c1_red3, "Image 3", R.color.color3));
        arrayList.add(new Data(28, R.drawable.c1_red2, "Image 2", R.color.color2));
        return arrayList;
    }

    public List<Data> setBottomGlitterList() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(new Data(1, R.drawable.git_1, "g_1", R.color.color25));
        arrayList.add(new Data(2, R.drawable.git_2, "g_2", R.color.color24));
        arrayList.add(new Data(3, R.drawable.git_3, "g_3", R.color.color23));
        arrayList.add(new Data(4, R.drawable.git_4, "g_4", R.color.color22));
        arrayList.add(new Data(5, R.drawable.git_5, "g_5", R.color.color21));
        arrayList.add(new Data(6, R.drawable.git_6, "g_6", R.color.color20));
        arrayList.add(new Data(7, R.drawable.git_7, "g_7", R.color.color19));
        arrayList.add(new Data(8, R.drawable.git_8, "g_8", R.color.color18));
        arrayList.add(new Data(9, R.drawable.git_9, "g_9", R.color.color17));
        arrayList.add(new Data(10, R.drawable.git_10, "g_10", R.color.color16));
        arrayList.add(new Data(11, R.drawable.git_11, "g_11", R.color.color15));
        arrayList.add(new Data(12, R.drawable.git_12, "g_12", R.color.color14));
        return arrayList;
    }

    public List<Data> setBottomPatternList() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
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

    public void turnEraserToBrush() {
        menuSelectedClick(1);
        MyConstant.SELECTED_TOOL = 1;
        MyConstant.strokeWidth = MyConstant.BRUSH_SIZE;
        MyConstant.erase = false;
    }
}
