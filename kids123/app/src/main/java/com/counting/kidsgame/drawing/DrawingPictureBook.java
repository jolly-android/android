package com.counting.kidsgame.drawing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.ViewCompat;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.QueueLinearFloodFiller;
import com.counting.kidsgame.R;
import com.plattysoft.leonids.ParticleSystem;

import java.io.PrintStream;

public class DrawingPictureBook extends View {
    public static Canvas drawCanvasBook;

    
    DrawActivityBook f4671a;

    
    RectF f4672b = new RectF();
    public Bitmap canvasBitmap;
    private Paint canvasPaint;
    private Paint circlePaint;
    private boolean drawEraser = false;
    private Paint drawPaint;
    private Path drawPath;
    private final DrawingPictureBook drawingPicture;
    public int gapPlaySound = 0;
    public Bitmap kidBitmap;
    public boolean kidBitmapDrawn;
    public boolean kidBitmapNeedDrawn;
    private float mX;
    private float mY;

    public DrawingPictureBook(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4671a = (DrawActivityBook) context;
        setupDrawing();
        this.drawingPicture = this;
    }

    private void setDefaultBrushSize() {
        MyConstant.BRUSH_SIZE = 20;
        this.drawPaint.setStrokeWidth((float) 20);
    }

    private void setPointOfSparkImage(float f, float f2) {
        DrawActivityBook.iv.setX(f);
        DrawActivityBook.iv.setY(f2);
    }

    private void setupDrawing() {
        this.drawPath = new Path();
        Paint paint = new Paint();
        this.drawPaint = paint;
        paint.setAntiAlias(true);
        this.drawPaint.setStyle(Paint.Style.STROKE);
        this.drawPaint.setStrokeJoin(Paint.Join.ROUND);
        this.drawPaint.setStrokeCap(Paint.Cap.ROUND);
        this.drawPaint.setPathEffect(new CornerPathEffect(10.0f));
        this.canvasPaint = new Paint(4);
        Paint paint2 = new Paint(1);
        this.circlePaint = paint2;
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        setDefaultBrushSize();
    }

    
    public void dispatchDraw(Canvas canvas) {
        this.f4672b.set(0.0f, 0.0f, (float) MyConstant.drawWidth, (float) MyConstant.drawHeight);
        try {
            int i = this.gapPlaySound + 1;
            this.gapPlaySound = i;
            if (i == 100) {
                this.gapPlaySound = 0;
            }
        } catch (Exception unused) {
            this.gapPlaySound = 1;
        }
        canvas.save();
        try {
            drawCanvasBook.drawPath(this.drawPath, this.drawPaint);
            canvas.drawBitmap(this.canvasBitmap, (Rect) null, this.f4672b, this.canvasPaint);
            if (MyConstant.SELECTED_TOOL == 2 && this.drawEraser) {
                if (this.gapPlaySound % 30 == 0) {
                    this.f4671a.mediaPlayer.playSound(R.raw.eraser);
                }
                this.drawPaint.setShader((Shader) null);
                this.circlePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                canvas.drawCircle(MyConstant.eraseX, MyConstant.eraseY, MyConstant.eraseR, this.circlePaint);
                this.circlePaint.setColor(-1);
                float f = MyConstant.eraseX;
                float f2 = MyConstant.eraseY;
                double d2 = (double) MyConstant.eraseR;
                Double.isNaN(d2);
                canvas.drawCircle(f, f2, (float) (d2 * 0.9d), this.circlePaint);
            }
            Bitmap bitmap = this.kidBitmap;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, (Rect) null, this.f4672b, this.canvasPaint);
            }
            canvas.restore();
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = MyConstant.onSizeCalled + 1;
        MyConstant.onSizeCalled = i5;
        if (i5 < 2) {
            if (MyConstant.drawWidth == 0 || MyConstant.drawHeight == 0) {
                MyConstant.drawWidth = 700;
                MyConstant.drawHeight = 700;
            }
            if (this.canvasBitmap == null) {
                this.canvasBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.canvasBitmap);
                drawCanvasBook = canvas;
                canvas.drawColor(-1);
            }
            MyConstant.drawWidth = i;
            MyConstant.drawHeight = i2;
            MyConstant.pixels = new int[(MyConstant.drawWidth * MyConstant.drawHeight)];
            PrintStream printStream = System.err;
            printStream.println("  MyConstant.pixels:" + MyConstant.pixels.length);
            DrawActivityBook.getDrawActivityBook().insertBitmap();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        MyConstant.eraseX = x;
        MyConstant.eraseY = y;
        int action = motionEvent.getAction() & 255;
        int i = -1;
        if (action == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            try {
                if (MyConstant.SELECTED_TOOL != 0) {
                    this.drawPaint.setStrokeWidth((float) MyConstant.BRUSH_SIZE);
                    if (MyConstant.SELECTED_TOOL != 2) {
                        if (!DrawActivityBook.ispatternClicked) {
                            System.err.println("test--4");
                            setPathColor(MyConstant.DRAW_COLOR);
                            System.err.println("test--5");
                            this.drawPath.moveTo(x, y);
                            this.drawPath.lineTo(x, y);
                            this.drawEraser = true;
                        }
                    }
                    System.err.println("test--1");
                    if (MyConstant.SELECTED_TOOL == 2) {
                        System.err.println("test--2");
                        this.drawPaint.setShader((Shader) null);
                        this.drawPaint.setColor(-1);
                        this.drawPaint.setStrokeWidth((float) MyConstant.ERASER_WIDTH);
                    } else {
                        System.err.println("test--3");
                        this.drawPaint.setColor(MyConstant.DRAW_COLOR);
                    }
                    System.err.println("test--5");
                    this.drawPath.moveTo(x, y);
                    this.drawPath.lineTo(x, y);
                    this.drawEraser = true;
                } else {
                    PrintStream printStream = System.err;
                    printStream.println("MyConstant.SELECTED_TOOL test--6" + MyConstant.DRAW_COLOR);
                    this.drawPaint.setColor(MyConstant.DRAW_COLOR);
                    setPathColor(MyConstant.DRAW_COLOR);
                }
                PrintStream printStream2 = System.err;
                printStream2.println("MyConstant.SELECTED_TOOL::" + MyConstant.SELECTED_TOOL);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } else if (action == 1) {
            try {
                setPointOfSparkImage(this.mX, this.mY);
                startOneShotParticle(DrawActivityBook.iv);
                this.f4671a.mediaPlayer.playColorRandomSound();
                PrintStream printStream3 = System.err;
                printStream3.println("MyConstant.SELECTED_TOOL up::" + MyConstant.SELECTED_TOOL);
                if (MyConstant.SELECTED_TOOL == 0) {
                    PrintStream printStream4 = System.err;
                    printStream4.println("ooo::>>" + this.kidBitmap + "--" + this.kidBitmapDrawn + "---" + this.kidBitmapNeedDrawn);
                    if (this.kidBitmap != null && (!this.kidBitmapDrawn || this.kidBitmapNeedDrawn)) {
                        PrintStream printStream5 = System.err;
                        printStream5.println("ooo::" + drawCanvasBook + "--" + this.kidBitmap + "---" + this.canvasPaint);
                        drawCanvasBook.drawBitmap(this.kidBitmap, (Rect) null, this.f4672b, this.canvasPaint);
                        this.kidBitmapDrawn = true;
                        this.kidBitmapNeedDrawn = false;
                    }
                    Bitmap bitmap = this.canvasBitmap;
                    int[] iArr = MyConstant.pixels;
                    int i2 = MyConstant.drawWidth;
                    bitmap.getPixels(iArr, 0, i2, 0, 0, i2, MyConstant.drawHeight);
                    int pixel = this.canvasBitmap.getPixel((int) this.mX, (int) this.mY);
                    int red = Color.red(pixel);
                    int green = Color.green(pixel);
                    int blue = Color.blue(pixel);
                    if (red >= 255 || red != green || red != blue) {
                        i = pixel;
                    } else if (red <= 0) {
                        return false;
                    }
                    invalidate();
                    QueueLinearFloodFiller queueLinearFloodFiller = new QueueLinearFloodFiller(i, MyConstant.DRAW_COLOR);
                    queueLinearFloodFiller.setTolerance(60);
                    queueLinearFloodFiller.floodFill((int) this.mX, (int) this.mY);
                    Bitmap bitmap2 = this.canvasBitmap;
                    int[] iArr2 = MyConstant.pixels;
                    int i3 = MyConstant.drawWidth;
                    bitmap2.setPixels(iArr2, 0, i3, 0, 0, i3, MyConstant.drawHeight);
                } else {
                    this.kidBitmapNeedDrawn = true;
                    this.drawPath.lineTo(this.mX, this.mY);
                    drawCanvasBook.drawPath(this.drawPath, this.drawPaint);
                    this.drawPath.reset();
                    this.drawEraser = false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (action != 2) {
            return false;
        } else {
            try {
                if (MyConstant.SELECTED_TOOL != 0) {
                    float abs = Math.abs(x - this.mX);
                    float abs2 = Math.abs(y - this.mY);
                    if (abs >= 0.0f || abs2 >= 0.0f) {
                        Path path = this.drawPath;
                        float f = this.mX;
                        float f2 = this.mY;
                        path.quadTo(f, f2, (f + x) / 2.0f, (f2 + y) / 2.0f);
                        this.mX = x;
                        this.mY = y;
                    }
                }
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            }
        }
        invalidate();
        return true;
    }

    public void setKidsImage() {
        invalidate();
        Bitmap bitmap = this.canvasBitmap;
        if (bitmap != null) {
            bitmap.eraseColor(-1);
        } else {
            onSizeChanged(MyConstant.drawWidth, MyConstant.drawHeight, MyConstant.drawWidth, MyConstant.drawHeight);
        }
        this.kidBitmapDrawn = false;
    }

    public void setPathColor(int i) {
        System.err.println("color cliked inside color");
        this.drawPaint.setShader((Shader) null);
        this.drawPaint.setColor(i);
    }

    public void setPattern(String str) {
        PrintStream printStream = System.err;
        printStream.println("color cliked inside pattern" + str);
        invalidate();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(str, "drawable", this.f4671a.getPackageName()));
        Shader.TileMode tileMode = Shader.TileMode.REPEAT;
        BitmapShader bitmapShader = new BitmapShader(decodeResource, tileMode, tileMode);
        this.drawPaint.setColor(-1);
        this.drawPaint.setShader(bitmapShader);
    }

    public void startNew() {
        this.kidBitmap = null;
        drawCanvasBook.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void startOneShotParticle(View view) {
        new ParticleSystem((Activity) this.f4671a, 5, (int) R.drawable.spark_bluedot, 200).setSpeedRange(0.45f, 0.75f).oneShot(view, 4);
        new ParticleSystem((Activity) this.f4671a, 5, (int) R.drawable.effect_star1, 300).setSpeedRange(0.35f, 0.7f).oneShot(view, 3);
        new ParticleSystem((Activity) this.f4671a, 5, (int) R.drawable.effect_star2, 400).setSpeedRange(0.3f, 0.68f).oneShot(view, 2);
        new ParticleSystem((Activity) this.f4671a, 5, (int) R.drawable.effect_star3, 250).setSpeedRange(0.42f, 0.6f).oneShot(view, 4);
        new ParticleSystem((Activity) this.f4671a, 5, (int) R.drawable.spark_yellowdot, 350).setSpeedRange(0.37f, 0.65f).oneShot(view, 3);
    }
}
