package com.github.mohaka.ratingstar;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

class Utilities {
    private Context context;

    static Utilities with(Context context) {
        Utilities reVal = new Utilities();
        reVal.context = context;
        return reVal;
    }

    Drawable getRD(int res) {
        return ContextCompat.getDrawable(context.getApplicationContext(), res);
    }

    Drawable getRDV(int res) {
        if (Utilities.hasLollipop()) return getRD(res);
        else return VectorDrawableCompat.create(context.getResources(), res, context.getTheme());

    }

    static boolean isPointInside(Point point, Point... inBoundaries) {
        int i, j;
        boolean result = false;
        for (i = 0, j = inBoundaries.length - 1; i < inBoundaries.length; j = i++) {
            if ((inBoundaries[i].y > point.y) != (inBoundaries[j].y > point.y) &&
                    (point.x < (inBoundaries[j].x - inBoundaries[i].x) * (point.y - inBoundaries[i].y) / (inBoundaries[j].y - inBoundaries[i].y) + inBoundaries[i].x)) {
                result = !result;
            }
        }
        return result;
    }

    static Point centerPoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    static int lineLength(Point p1, Point p2) {
        double a = Math.abs(p1.x - p2.x);
        double b = Math.abs(p1.y - p2.y);

        a = Math.pow(a, 2);
        b = Math.pow(b, 2);

        return (int) Math.sqrt(a + b);
    }

    static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @ColorInt
    public static int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}
