package com.example.dongdong.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.dongdong.model.BaseData;
import com.example.dongdong.model.BaseEntry;
import com.example.dongdong.model.BaseHttpManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deofly
 * @since 1.0 2014/12/01
 */
public abstract class BaseHttpAdapter<E extends BaseEntry, T extends BaseData<E>>
        extends ArrayAdapter<String>
        implements Listener<T>, ErrorListener {

    protected List<E> mEntryList;

    protected T mRawData;

    protected BaseHttpManager<T> mHttpManager;

    protected boolean mIsLoading;

    protected boolean mHasMoreDataToLoad;

    public BaseHttpAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(List<E> newEntryList) {
        mIsLoading = false;

        if (!newEntryList.isEmpty()) {
            mEntryList.addAll(newEntryList);
            notifyDataSetChanged();
        }
    }

    protected boolean shouldLoadMoreData(List<E> entryList, int position) {
        // if showing the last set of data, request for the next set of data
        boolean hasScrollRangeReached = (position > mEntryList.size() - mHttpManager.getPaginationPerPage());

        // TODO: last page not considered
        return hasScrollRangeReached && mIsLoading;
    }

    protected void loadMoreData() {
        mIsLoading = true;

        mHttpManager.sendRequest(this, this, mRawData.getPage()+1);
    }

    protected abstract BaseHttpManager<T> getHttpManger(BaseHttpManager<T> httpManager);

    protected abstract View getView(E entry, View convertView, ViewGroup parent);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (shouldLoadMoreData(mEntryList, position)) {
            loadMoreData();
        }

        E entry = mEntryList.get(position);

        return getView(entry, convertView, parent);
    }

    @Override
    public int getCount() {
        return mEntryList.size();
    }

    @Override
    public void onResponse(T t) {
        if (t != null) {
            mRawData = t;
            mEntryList.addAll(t.getEntryList());

            notifyDataSetChanged();
        }

        mIsLoading = false;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        mIsLoading = false;
    }
}
