package com.example.dongdong.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 *
 * @author deofly
 * @since 1.0 2014/11/21
 */
public class ToastUtils {

    private ToastUtils() {
    }

    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId) {
        show(context, resId, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, format, Toast.LENGTH_SHORT, args);
    }
}
