package com.example.dongdong.model;

import android.net.Uri;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.example.dongdong.util.GsonRequest;
import com.example.dongdong.util.RequestQueueManager;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import static com.android.volley.Request.*;

/**
 * @author deofly
 * @since 1.0 2014/11/30
 */
public abstract class BaseHttpManager<T extends BaseData> {

    protected int mPaginationPerPage = 10;

    protected Map<String, String> extraQueryParams;

    protected abstract String getUrl();

    protected abstract int getRequestMethod();

    protected abstract Map<String, String> getBaseQueryParams();

    protected abstract Class<T> getDataClass();

    public void setPaginationPerPage(int num) {
        mPaginationPerPage = num;
    }

    public int getPaginationPerPage() {
        return mPaginationPerPage;
    }

    public void setExtraQueryParams(Map<String, String> params) {
        extraQueryParams = params;
    }

    public void sendRequest(Listener<T> listener, ErrorListener errorListener, int page) {
        String baseUri = getUrl();
        Uri.Builder urlBuilder = Uri.parse(baseUri).buildUpon();

        Map<String, String> queryParameters = getBaseQueryParams();
        for (Entry<String, String> param : queryParameters.entrySet()) {
            String key = param.getKey();
            String value = param.getValue();
            urlBuilder.appendQueryParameter(key, value);
        }

        for (Entry<String, String> param: extraQueryParams.entrySet()) {
            String key = param.getKey();
            String value  = param.getValue();
            urlBuilder.appendQueryParameter(key, value);
        }

        String url = urlBuilder.toString();
        Class<T> dataClass = getDataClass();
        int requestMethod = getRequestMethod();
        GsonRequest<T> request = new GsonRequest<T>(requestMethod,
                url,
                dataClass,
                listener,
                errorListener);

        RequestQueueManager.getInstance().addToRequestQueue(request);
    }
}
