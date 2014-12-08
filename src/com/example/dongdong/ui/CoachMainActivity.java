package com.example.dongdong.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.dongdong.R;
import com.example.dongdong.ui.adapter.CoachMainListAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.AdapterView.*;
import static com.handmark.pulltorefresh.library.PullToRefreshBase.*;

/**
 * @author deofly
 * @since 1.0 2014/11/2
 */
public class CoachMainActivity extends BaseActivity {

    private List<HashMap<String, Object>> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_main);

        PullToRefreshListView list = (PullToRefreshListView) findViewById(android.R.id.list);
        list.setEmptyView(findViewById(R.id.empty));
        list.setMode(Mode.BOTH);
        list.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                // Update the LastUpdatedLabel
//                pullToRefreshBase.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
//                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }
        });
        list.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {

            }
        });

        mData = new ArrayList<HashMap<String, Object>>();

        ListView actualListView = list.getRefreshableView();
        actualListView.setAdapter(new CoachMainListAdapter(this));
        actualListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private class GetDataAsyncTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
        }
    }
}
