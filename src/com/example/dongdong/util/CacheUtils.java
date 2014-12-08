package com.example.dongdong.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * CacheUtils
 *
 * @author deofly
 * @since 1.0 2014/11/21
 */
public class CacheUtils {

    private CacheUtils() {
    }

    public static File getExternalCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (!Environment.isExternalStorageRemovable() ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //noinspection ConstantConditions
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public static File getExternalCacheDir(Context context) {
        return getExternalCacheDir(context, null);
    }
}
