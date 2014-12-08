package com.example.dongdong.model.coachmain;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.example.dongdong.model.BaseHttpManager;
import com.example.dongdong.util.C;

import javax.xml.transform.ErrorListener;
import java.util.Map;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class CoachMainManager extends BaseHttpManager<CoachMainData> {

    @Override
    protected String getUrl() {
        return C.BASE_URL + "list/coach";
    }

    @Override
    protected int getRequestMethod() {
        return Method.GET;
    }

    @Override
    protected Map<String, String> getBaseQueryParams() {
        return null;
    }

    @Override
    protected Class getDataClass() {
        return CoachMainData.class;
    }
}
