package com.example.dongdong.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.dongdong.util.AppUtils;
import com.example.dongdong.util.CacheUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.*;

/**
 * Implementation of DiskLruCache by Jake Wharton
 * modified from http://stackoverflow.com/questions/10185898/using-disklrucache-in-android-
 * 4-0-does-not-provide-for-opencache-method
 *
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class DiskLruImageCache implements ImageCache {

    private static final int VALUE_COUNT = 1;

    private static final int IO_BUFFER_SIZE = 8 * 1024;

    private DiskLruCache mDiskCache;
    private CompressFormat mCompressFormat = CompressFormat.JPEG;
    private int mCompressQuality = 70;

    public DiskLruImageCache(Context context, String uniqueName, int diskCacheSize) {
        File diskCacheDir = CacheUtils.getExternalCacheDir(context, uniqueName);
        try {
            mDiskCache = DiskLruCache.open(diskCacheDir, AppUtils.getAppVersion(context),
                    VALUE_COUNT, diskCacheSize);
        } catch (IOException e) {
            // TODO: need to handle this exception
            e.printStackTrace();
        }
    }

    public DiskLruImageCache(Context context, String uniqueName, int diskCacheSize,
                             CompressFormat compressFormat) {
        this(context, uniqueName, diskCacheSize);
        if (compressFormat != null) {
            mCompressFormat = compressFormat;
        }
    }

    public DiskLruImageCache(Context context, String uniqueName, int diskCacheSize,
                             CompressFormat compressFormat, int compressQuality) {
        this(context, uniqueName, diskCacheSize, compressFormat);
        if (diskCacheSize > 0 && diskCacheSize <= 100) {
            mCompressQuality = compressQuality;
        }
    }

    private boolean writeBitmapToFile(Bitmap bitmap, DiskLruCache.Editor editor)
            throws IOException {
        BufferedOutputStream bos = null;

        try {
            OutputStream os = editor.newOutputStream(0);
            if (os == null) {
                return false;
            }

            bos = new BufferedOutputStream(os, IO_BUFFER_SIZE);
            return bitmap.compress(mCompressFormat, mCompressQuality, bos);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
    }

    @Override
    public Bitmap getBitmap(String key) {
        Bitmap bitmap = null;
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mDiskCache.get(key);
            if (snapshot == null) {
                return null;
            }

            InputStream is = snapshot.getInputStream(0);
            if (is != null) {
                BufferedInputStream bis = new BufferedInputStream(is, IO_BUFFER_SIZE);

                bitmap = BitmapFactory.decodeStream(bis);
                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }

        return bitmap;
    }

    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        DiskLruCache.Editor editor = null;
        try {
            editor = mDiskCache.edit(key);
            if (editor == null) {
                return;
            }

            if (writeBitmapToFile(bitmap, editor)) {
                mDiskCache.flush();
                editor.commit();
            } else {
                editor.abort();
            }
        } catch (IOException e) {
            try {
                if ( editor != null ) {
                    editor.abort();
                }
            } catch (IOException ignored) {
            }
        }
    }

    public boolean containsKey(String key) {
        DiskLruCache.Snapshot snapshot = null;

        try {
            snapshot = mDiskCache.get(key);
            return (snapshot != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }

        return false;
    }

    public void cleanCache() {
        try {
            mDiskCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
