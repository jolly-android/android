package com.counting.kidsgame.drawing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
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
import com.counting.kidsgame.R;
import com.plattysoft.leonids.ParticleSystem;

import java.io.PrintStream;

public class DrawingPicture extends View {
    private static final float TOUCH_TOLERANCE = 0.0f;
    public static Canvas drawCanvas;

    
    DrawActivity f4669a;

    
    RectF f4670b;
    public Bitmap canvasBitmap;
    private Paint canvasPaint;
    private Paint circlePaint;
    private boolean drawEraser = false;
    private Paint drawPaint;
    private Path drawPath;
    private final DrawingPicture drawingPicture;
    public int gapPlaySound = 0;
    public Bitmap kidBitmap;
    public boolean kidBitmapDrawn;
    public boolean kidBitmapNeedDrawn;
    public Bitmap kidBitmapSticker;
    private float mX;
    private float mY;

    public DrawingPicture(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4669a = (DrawActivity) context;
        this.f4670b = new RectF();
        setupDrawing();
        this.drawingPicture = this;
    }

    private void setPointOfSparkImage(float f, float f2) {
        DrawActivity.iv.setX(f);
        DrawActivity.iv.setY(f2);
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
    }

    public Bitmap getImage() {
        setDrawingCacheEnabled(true);
        return getDrawingCache();
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i2) / ((float) width), ((float) i) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f4670b.set(0.0f, 0.0f, (float) MyConstant.drawWidth, (float) MyConstant.drawHeight);
        try {
            int i = this.gapPlaySound + 1;
            this.gapPlaySound = i;
            if (i == 100) {
                this.gapPlaySound = 0;
            }
            drawCanvas.drawPath(this.drawPath, this.drawPaint);
            canvas.drawBitmap(this.canvasBitmap, (Rect) null, this.f4670b, this.canvasPaint);
            if (MyConstant.erase && this.drawEraser) {
                if (this.gapPlaySound % 30 == 0) {
                    this.f4669a.mediaPlayer.playSound(R.raw.eraser);
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
                canvas.drawBitmap(bitmap, (Rect) null, this.f4670b, this.canvasPaint);
            }
        } catch (Exception unused) {
        }
    }

    
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        try {
            PrintStream printStream = System.err;
            printStream.println("onSizeChanged called:" + this.f4669a.getResources().getConfiguration().orientation + "---" + i + "---" + i2);
            this.canvasBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.canvasBitmap);
            drawCanvas = canvas;
            canvas.drawColor(-1);
            MyConstant.drawWidth = i;
            MyConstant.drawHeight = i2;
            this.canvasBitmap.eraseColor(-1);
            MyConstant.pixels = new int[(MyConstant.drawWidth * MyConstant.drawHeight)];
        } catch (Exception unused) {
            System.gc();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        MyConstant.eraseX = x;
        MyConstant.eraseY = y;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mX = x;
            this.mY = y;
            if (MyConstant.selectedTool != 0) {
                this.drawPaint.setStrokeWidth((float) MyConstant.strokeWidth);
                if (MyConstant.erase) {
                    this.drawPaint.setShader((Shader) null);
                    this.drawPaint.setColor(-1);
                } else {
                    this.drawPaint.setColor(MyConstant.drawColor);
                }
                this.drawPath.moveTo(x, y);
                this.drawPath.lineTo(x, y);
                this.drawEraser = true;
            }
        } else if (action == 1) {
            this.f4669a.mediaPlayer.playColorRandomSound();
            setPointOfSparkImage(this.mX, this.mY);
            startOneShotParticle(DrawActivity.iv);
            try {
                if (MyConstant.selectedTool == 0) {
                    Bitmap bitmap = this.kidBitmap;
                    if (bitmap != null && (!this.kidBitmapDrawn || this.kidBitmapNeedDrawn)) {
                        drawCanvas.drawBitmap(bitmap, (Rect) null, this.f4670b, this.canvasPaint);
                        this.kidBitmapDrawn = true;
                        this.kidBitmapNeedDrawn = false;
                    }
                    Bitmap bitmap2 = this.canvasBitmap;
                    int[] iArr = MyConstant.pixels;
                    int i = MyConstant.drawWidth;
                    bitmap2.getPixels(iArr, 0, i, 0, 0, i, MyConstant.drawHeight);
                    int pixel = this.canvasBitmap.getPixel((int) this.mX, (int) this.mY);
                    int red = Color.red(pixel);
                    int green = Color.green(pixel);
                    int blue = Color.blue(pixel);
                    if (red < 255 && red == green && red == blue && red <= 0) {
                        return false;
                    }
                    invalidate();
                    Bitmap bitmap3 = this.canvasBitmap;
                    int[] iArr2 = MyConstant.pixels;
                    int i2 = MyConstant.drawWidth;
                    bitmap3.setPixels(iArr2, 0, i2, 0, 0, i2, MyConstant.drawHeight);
                } else {
                    this.kidBitmapNeedDrawn = true;
                    this.drawPath.lineTo(this.mX, this.mY);
                    drawCanvas.drawPath(this.drawPath, this.drawPaint);
                    this.drawPath.reset();
                    this.drawEraser = false;
                }
            } catch (Exception unused) {
            }
        } else if (action != 2) {
            return false;
        } else {
            if (MyConstant.selectedTool != 0) {
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
        }
        invalidate();
        return true;
    }

    public void setKidsImage() {
        invalidate();
        this.kidBitmapDrawn = false;
    }

    public void setPathColor(int i) {
        this.drawPaint.setShader((Shader) null);
        this.drawPaint.setColor(i);
    }

    public void setPattern(String str) {
        invalidate();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(str, "drawable", this.f4669a.getPackageName()));
        Shader.TileMode tileMode = Shader.TileMode.REPEAT;
        BitmapShader bitmapShader = new BitmapShader(decodeResource, tileMode, tileMode);
        this.drawPaint.setColor(-1);
        this.drawPaint.setShader(bitmapShader);
    }

    public void startNew() {
        this.kidBitmap = null;
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void startOneShotParticle(View view) {
        new ParticleSystem((Activity) this.f4669a, 5, (int) R.drawable.spark_bluedot, 200).setSpeedRange(0.45f, 0.75f).oneShot(view, 4);
        new ParticleSystem((Activity) this.f4669a, 5, (int) R.drawable.effect_star1, 300).setSpeedRange(0.35f, 0.7f).oneShot(view, 3);
        new ParticleSystem((Activity) this.f4669a, 5, (int) R.drawable.effect_star2, 400).setSpeedRange(0.3f, 0.68f).oneShot(view, 2);
        new ParticleSystem((Activity) this.f4669a, 5, (int) R.drawable.effect_star3, 250).setSpeedRange(0.42f, 0.6f).oneShot(view, 4);
        new ParticleSystem((Activity) this.f4669a, 5, (int) R.drawable.spark_yellowdot, 350).setSpeedRange(0.37f, 0.65f).oneShot(view, 3);
    }
}
