package com.example.dongdong.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import com.example.dongdong.ui.fragment.BaseFragment;
import com.example.dongdong.ui.fragment.PrivateMainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deofly
 * @since 1.0 2014/12/03
 */
public class TabsAdapter extends FragmentPagerAdapter
        implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private final Context mContext;
    private final RadioGroup mTabGroup;
    private final ViewPager mViewPager;

    private final List<TabInfo> mTabs = new ArrayList<TabInfo>();

    private final class TabInfo {

        private final int resource;
        private final Class<?> cls;
        private final Bundle args;

        private TabInfo(int resource, Class<? extends BaseFragment> cls, Bundle args) {
            this.resource = resource;
            this.cls = cls;
            this.args = args;
        }
    }

    public TabsAdapter(FragmentActivity context, RadioGroup tabGroup, ViewPager viewPager) {
        super(context.getSupportFragmentManager());

        mContext = context;
        mTabGroup = tabGroup;
        mViewPager = viewPager;

        mTabGroup.setOnCheckedChangeListener(this);
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void add(int resource, Class<? extends BaseFragment> cls, Bundle args) {
        TabInfo tabInfo = new TabInfo(resource, cls, args);
        mTabs.add(tabInfo);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int i) {
        TabInfo tabInfo = mTabs.get(i);

        Fragment fragment = Fragment.instantiate(mContext, mTabs.get(i).cls.getName(), tabInfo.args);
        return fragment;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        mTabGroup.check(mTabs.get(i).resource);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < mTabs.size(); i++) {
            if (mTabs.get(i).resource == checkedId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
