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
public class MessageMainFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_main, container, false);

        view.findViewById(R.id.coach).setOnClickListener(this);
        view.findViewById(R.id.gym).setOnClickListener(this);
        view.findViewById(R.id.campaign).setOnClickListener(this);
        view.findViewById(R.id.friend).setOnClickListener(this);
        view.findViewById(R.id.follow).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.coach:
                break;
            case R.id.gym:
                break;
            case R.id.campaign:
                break;
            case R.id.friend:
                break;
            case R.id.follow:
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
