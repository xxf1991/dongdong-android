package com.example.dongdong.util;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class RequestQueueManager {

    private final String TAG = getClass().getSimpleName();

    private static RequestQueueManager sInstance;

    /**
     * Global request queue for Volley.
     */
    private static RequestQueue mRequestQueue;

    /**
     * Context for Volley request queue.
     */
    private static Context mContext;

    private RequestQueueManager() {
    }

    public static RequestQueueManager getInstance() {
        if (sInstance == null) {
            sInstance = new RequestQueueManager();
        }
        return sInstance;
    }

    /**
     * Initialize the context.
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
    }

    /**
     * @return The Volley request queue, the queue will be created if it is null.
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue will be created
        // when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    /**
     * Add request to the global request queue.
     *
     * @param request
     * @param tag the default tag is {@link #TAG}
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        // set the default tag if the tag is empty
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", request.getUrl());

        getRequestQueue().add(request);
    }

    /**
     * Add request to the global request queue.
     *
     * @param request
     * @see #addToRequestQueue
     */
    public <T> void addToRequestQueue(Request<T> request) {
        addToRequestQueue(request, null);
    }

    /**
     * Cancel all pending requests by specified tag.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
