package com.example.dongdong;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import com.example.dongdong.BuildConfig;
import com.example.dongdong.util.ImageCacheManager;
import com.example.dongdong.util.RequestQueueManager;

/**
 * Dongdong application.
 *
 * @author deofly
 * @since 1.0 2014/10/7
 */
public class MainApplication extends Application {

    private static final int IMAGE_CACHE_SIZE = 10 * 1024 * 1024;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectAll()
//                    .penaltyDialog()
//                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectAll()
//                    .penaltyDeath()
//                    .build());
        }

        super.onCreate();

        RequestQueueManager.getInstance().init(this);
        ImageCacheManager.getInstance().initWithMemoryCache(IMAGE_CACHE_SIZE);
    }
}
