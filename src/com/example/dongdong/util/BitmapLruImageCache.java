package com.example.dongdong.util;

import android.graphics.Bitmap;

import static com.android.volley.toolbox.ImageLoader.*;

/**
 * Basic LRU memory cache.
 *
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class BitmapLruImageCache extends LruCache<String, Bitmap> implements ImageCache {

    public BitmapLruImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String s) {
        return get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        put(s, bitmap);
    }
}
