package com.counting.kidsgame;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public class ProxyDrawable extends Drawable {
    private Drawable mProxy;

    public ProxyDrawable(Drawable drawable) {
        this.mProxy = drawable;
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    public int getOpacity() {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -2;
    }

    public Drawable getProxy() {
        return this.mProxy;
    }

    public void setAlpha(int i) {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
    }

    public void setDither(boolean z) {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            drawable.setDither(z);
        }
    }

    public void setFilterBitmap(boolean z) {
        Drawable drawable = this.mProxy;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    public void setProxy(Drawable drawable) {
        if (drawable != this) {
            this.mProxy = drawable;
        }
    }
}
