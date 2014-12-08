package com.example.dongdong.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.example.dongdong.R;
import com.example.dongdong.ui.adapter.TabsAdapter;
import com.example.dongdong.ui.fragment.BaseFragment;
import com.example.dongdong.ui.fragment.DiscoveryMainFragment;
import com.example.dongdong.ui.fragment.MessageMainFragment;
import com.example.dongdong.ui.fragment.PrivateMainFragment;
import com.example.dongdong.util.ToastUtils;

import java.util.Map;

public class MainActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    private RadioGroup mTabGroup;

    private static final long PRESS_INTERVAL_IF_EXIT = 2000; // milliseconds
    private long mLastExitTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost);

        ViewPager viewPager = (ViewPager) findViewById(R.id.paper);
        mTabGroup = (RadioGroup) findViewById(R.id.tabs);

        TabsAdapter tabsAdapter = new TabsAdapter(this, mTabGroup, viewPager);

        tabsAdapter.add(R.id.tab_news, BaseFragment.class, null);
        tabsAdapter.add(R.id.tab_discovery, DiscoveryMainFragment.class, null);
        tabsAdapter.add(R.id.tab_message, MessageMainFragment.class, null);
        tabsAdapter.add(R.id.tab_private, PrivateMainFragment.class, null);

        // restore data
        if (savedInstanceState != null) {
            int currentCheckedId = savedInstanceState.getInt(TAG, 0);
            mTabGroup.check(currentCheckedId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAG, mTabGroup.getCheckedRadioButtonId());
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mLastExitTime) > PRESS_INTERVAL_IF_EXIT) {
            ToastUtils.show(this, "再按一次退出程序");
            mLastExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
