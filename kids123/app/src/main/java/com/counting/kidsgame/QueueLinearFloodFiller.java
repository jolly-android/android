package com.counting.kidsgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.Queue;

public class QueueLinearFloodFiller {

    
    protected int f4462a;

    
    protected int f4463b;

    
    protected Bitmap f4464c;

    
    protected boolean[] f4465d;
    protected Queue<FloodFillRange> e;
    protected int[] f;
    protected int[] g;
    protected int h;

    protected class FloodFillRange {
        public int endX;
        public int f4Y;
        public int startX;

        public FloodFillRange(int i, int i2, int i3) {
            this.startX = i;
            this.endX = i2;
            this.f4Y = i3;
        }
    }

    public QueueLinearFloodFiller(Bitmap bitmap) {
        this.f4464c = null;
        this.g = new int[]{0, 0, 0};
        this.h = 0;
        this.f4463b = 0;
        this.f4462a = 0;
        this.f = new int[]{0, 0, 0};
        copyImage(bitmap);
    }

    
    public boolean a(int i) {
        int[] iArr = MyConstant.pixels;
        int i2 = (iArr[i] >>> 16) & 255;
        int i3 = (iArr[i] >>> 8) & 255;
        int i4 = iArr[i] & 255;
        int[] iArr2 = this.f;
        int i5 = iArr2[0];
        int[] iArr3 = this.g;
        return i2 >= i5 - iArr3[0] && i2 <= iArr2[0] + iArr3[0] && i3 >= iArr2[1] - iArr3[1] && i3 <= iArr2[1] + iArr3[1] && i4 >= iArr2[2] - iArr3[2] && i4 <= iArr2[2] + iArr3[2];
    }

    
    public void b(int i, int i2) {
        int i3 = (this.h * i2) + i;
        int i4 = i;
        do {
            MyConstant.pixels[i3] = this.f4462a;
            boolean[] zArr = this.f4465d;
            zArr[i3] = true;
            i4--;
            i3--;
            if (i4 < 0 || zArr[i3] || !a(i3)) {
                int i5 = i4 + 1;
                int i6 = (this.h * i2) + i;
            }
            MyConstant.pixels[i3] = this.f4462a;
            boolean[] zArr2 = this.f4465d;
            zArr2[i3] = true;
            i4--;
            i3--;
            break;
        } while (!a(i3));
        int i52 = i4 + 1;
        int i62 = (this.h * i2) + i;
        do {
            MyConstant.pixels[i62] = this.f4462a;
            boolean[] zArr3 = this.f4465d;
            zArr3[i62] = true;
            i++;
            i62++;
            if (i >= this.h || zArr3[i62] || !a(i62)) {
                this.e.offer(new FloodFillRange(i52, i - 1, i2));
            }
            MyConstant.pixels[i62] = this.f4462a;
            boolean[] zArr32 = this.f4465d;
            zArr32[i62] = true;
            i++;
            i62++;
            break;
        } while (!a(i62));
        this.e.offer(new FloodFillRange(i52, i - 1, i2));
    }

    
    public void c() {
        this.f4465d = new boolean[MyConstant.pixels.length];
        this.e = new LinkedList();
    }

    public void copyImage(Bitmap bitmap) {
        this.h = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.f4463b = height;
        this.f4464c = Bitmap.createBitmap(this.h, height, Bitmap.Config.RGB_565);
        new Canvas(this.f4464c).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        int i = this.h;
        int i2 = this.f4463b;
        int[] iArr = new int[(i * i2)];
        MyConstant.pixels = iArr;
        this.f4464c.getPixels(iArr, 0, i, 1, 1, i - 1, i2 - 1);
    }

    public void floodFill(int i, int i2) {
        System.err.println("boook:: floodFill");
        c();
        int[] iArr = this.f;
        if (iArr[0] == 0) {
            int i3 = MyConstant.pixels[(this.h * i2) + i];
            iArr[0] = (i3 >> 16) & 255;
            iArr[1] = (i3 >> 8) & 255;
            iArr[2] = i3 & 255;
        }
        b(i, i2);
        while (this.e.size() > 0) {
            FloodFillRange remove = this.e.remove();
            int i4 = this.h;
            int i5 = remove.f4Y;
            int i6 = remove.startX;
            int i7 = ((i5 + 1) * i4) + i6;
            int i8 = (i4 * (i5 - 1)) + i6;
            int i9 = i5 - 1;
            int i10 = i5 + 1;
            while (i6 <= remove.endX) {
                if (remove.f4Y > 0 && !this.f4465d[i8] && a(i8)) {
                    b(i6, i9);
                }
                if (remove.f4Y < this.f4463b - 1 && !this.f4465d[i7] && a(i7)) {
                    b(i6, i10);
                }
                i7++;
                i8++;
                i6++;
            }
        }
    }

    public int getFillColor() {
        return this.f4462a;
    }

    public Bitmap getImage() {
        return this.f4464c;
    }

    public int[] getTolerance() {
        return this.g;
    }

    public void setFillColor(int i) {
        this.f4462a = i;
    }

    public void setTargetColor(int i) {
        this.f[0] = Color.red(i);
        this.f[1] = Color.green(i);
        this.f[2] = Color.blue(i);
    }

    public void setTolerance(int[] iArr) {
        this.g = iArr;
    }

    public void useImage(Bitmap bitmap) {
        this.h = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.f4463b = height;
        this.f4464c = bitmap;
        int i = this.h;
        int[] iArr = new int[(i * height)];
        MyConstant.pixels = iArr;
        bitmap.getPixels(iArr, 0, i, 1, 1, i - 1, height - 1);
    }

    public void setTolerance(int i) {
        System.err.println("boook:: setTolerance");
        this.g = new int[]{i, i, i};
    }

    public QueueLinearFloodFiller(int i, int i2) {
        this.f4464c = null;
        this.g = new int[]{0, 0, 0};
        this.h = 0;
        this.f4463b = 0;
        this.f4462a = 0;
        this.f = new int[]{0, 0, 0};
        this.h = MyConstant.drawWidth;
        this.f4463b = MyConstant.drawHeight;
        setFillColor(i2);
        setTargetColor(i);
    }
}
