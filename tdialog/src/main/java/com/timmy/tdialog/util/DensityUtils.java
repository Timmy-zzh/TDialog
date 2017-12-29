package com.timmy.tdialog.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;

import java.lang.reflect.Field;

//常用单位转换的辅助类
public class DensityUtils {
    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context
     * @param
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获得屏幕宽/高
     */
    public static int getWindowHeight(Activity acitvity) {
        return acitvity.getWindowManager().getDefaultDisplay().getHeight();
    }

    public static int getWindowWidth(Activity acitvity) {
        return acitvity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static Rect getBottomCartRect(Activity activity) {
        int left = (int) (getWindowWidth(activity) - 40 * getDensity(activity));
        int top = (int) (getWindowHeight(activity) - 60 * getDensity(activity));
        return new Rect(left, top, left + 2, top + 2);
    }

    public static int getStatusBarHeight(Context context) {
        if (context == null)
            return 0;
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
