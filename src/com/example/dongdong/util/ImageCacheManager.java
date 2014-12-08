package com.example.dongdong.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.android.volley.toolbox.ImageLoader;

import static com.android.volley.toolbox.ImageLoader.*;

/**
 * @author deofly
 * @since 11/23/14
 */
public class ImageCacheManager {

    /**
     * Volley recommends in-memory L1 cache but both disk and memory cache are provided.<br/>
     * Volley includes a L2 disk cache out of the box, but you can technically use a disk cache as
     * an L1 cache provided. You may live with a potential I/O blocking.
     */
    public enum CacheType {
        MEMORY,
        DISK
    }

    private static ImageCacheManager mInstance;

    private ImageCache mImageCache;

    private ImageLoader mImageLoader;

    private CacheType mCacheType;

    private ImageCacheManager() {
    }

    public static ImageCacheManager getInstance() {
        if (mInstance == null) {
            mInstance = new ImageCacheManager();
        }

        return mInstance;
    }

    public void initWithMemoryCache(int cacheSize) {
        mCacheType = CacheType.MEMORY;
        mImageCache = new BitmapLruImageCache(cacheSize);
    }

    public void initWithDiskCache(Context context, String uniqueName, int cacheSize,
                                  CompressFormat compressFormat, int quality) {
        mCacheType = CacheType.DISK;
        mImageCache = new DiskLruImageCache(context, uniqueName, cacheSize, compressFormat, quality);

        mImageLoader = new ImageLoader(RequestQueueManager.getInstance().getRequestQueue(), mImageCache);
    }

    public void initWithDiskCache(Context context, String uniqueName, int cacheSize,
                                  CompressFormat compressFormat) {
        mCacheType = CacheType.DISK;
        mImageCache = new DiskLruImageCache(context, uniqueName, cacheSize, compressFormat);

        mImageLoader = new ImageLoader(RequestQueueManager.getInstance().getRequestQueue(), mImageCache);
    }

    public void initWithDiskCache(Context context, String uniqueName, int cacheSize) {
        mCacheType = CacheType.DISK;
        mImageCache = new DiskLruImageCache(context, uniqueName, cacheSize);

        mImageLoader = new ImageLoader(RequestQueueManager.getInstance().getRequestQueue(), mImageCache);
    }

    public Bitmap getBitmap(String url) {
        try {
            return mImageCache.getBitmap(generateKey(url));
        } catch (NullPointerException e) {
            throw new IllegalStateException("Image cache not initialized.");
        }
    }

    public void putBitmap(String url, Bitmap bitmap) {
        try {
            mImageCache.putBitmap(generateKey(url), bitmap);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Image cache not initialized.");
        }
    }

    public ImageLoader getImageLoader() {
        try {
            return mImageLoader;
        } catch (NullPointerException e) {
            throw new IllegalStateException("Image cache not initialized.");
        }
    }

    private String generateKey(String url) {
        // TODO: rewrite hash-code-generation algorithm
        return String.valueOf(url.hashCode());
    }
}
