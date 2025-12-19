package com.counting.kidsgame.util;

import android.view.animation.Interpolator;

public class BounceInterpolator implements Interpolator {

    
    double f4809a;

    
    double f4810b;

    public BounceInterpolator(double d2, double d3) {
        this.f4809a = d2;
        this.f4810b = d3;
    }

    public float getInterpolation(float f) {
        double d2 = (double) (-f);
        double d3 = this.f4809a;
        Double.isNaN(d2);
        double d4 = this.f4810b;
        double d5 = (double) f;
        Double.isNaN(d5);
        return (float) ((Math.pow(2.718281828459045d, d2 / d3) * -1.0d * Math.cos(d4 * d5)) + 1.0d);
    }
}
