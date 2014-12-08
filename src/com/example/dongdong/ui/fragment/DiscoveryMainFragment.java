package com.example.dongdong.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dongdong.R;

/**
 * @author deofly
 * @since 1.0 2014/12/08
 */
public class DiscoveryMainFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery_main, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
