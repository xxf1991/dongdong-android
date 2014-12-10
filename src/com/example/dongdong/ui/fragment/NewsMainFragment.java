package com.example.dongdong.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;
import com.example.dongdong.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.dongdong.ui.NewsAdapter;

/**
 * Created by xxf on 2014/10/5 0005.
 */
public class NewsMainFragment extends BaseFragment{
    String[] orders;
    View view;
    ListView listView;
    NewsAdapter newsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_main, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)view.findViewById(R.id.listView);
        newsAdapter = new NewsAdapter(getActivity());
        SampleAdapter adapter = new SampleAdapter(getActivity());
        for (int i = 0; i < 20; i++) {
            adapter.add(new SampleItem("发货很快机会会尽快", "三甲胺发布今年可免费加速度对吧", R.drawable.ic_sports_badminton));
        }
        listView.setAdapter(newsAdapter);
    }

    private class SampleItem {
        public String tv_title, tv_abstract;
        public int iv;
        public SampleItem(String tv_title, String tv_abstract, int iv) {
            this.tv_title = tv_title;
            this.tv_abstract = tv_abstract;
            this.iv = iv;
        }
    }

    public class SampleAdapter extends ArrayAdapter<SampleItem> {

        public SampleAdapter(Context context) {
            super(context, 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //if (convertView == null) {
                if(position == 0){
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news_viewpager,null);
                    return convertView;
                }
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news_main, null);
            //}
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            iv.setImageResource(getItem(position).iv);
            TextView title = (TextView) convertView.findViewById(R.id.tv_title);
            title.setText(getItem(position).tv_title);
            TextView abs = (TextView) convertView.findViewById(R.id.tv_abstract);
            abs.setText(getItem(position).tv_abstract);

            return convertView;
        }

    }

}
