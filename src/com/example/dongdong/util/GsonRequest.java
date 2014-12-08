package com.example.dongdong.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class GsonRequest<T> extends Request<T> {

    /**
     * Gson parser
     */
    private final Gson mGson;

    /**
     * Class type for the response
     */
    private final Class<T> mClass;

    /**
     * Callback for response delivery
     */
    private final Listener<T> mListener;

    public GsonRequest(int method, String url, Class<T> objClass,
                       Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClass = objClass;
        this.mListener = listener;
        this.mGson = new Gson(); // TODO: Config gson here
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}
