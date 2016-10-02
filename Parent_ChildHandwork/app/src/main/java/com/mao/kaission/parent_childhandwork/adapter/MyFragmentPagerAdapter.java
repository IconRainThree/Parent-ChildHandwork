package com.mao.kaission.parent_childhandwork.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * description：
 * company：
 * Created by kaission on 2016.10.02.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = null;
    private List<String> tabsList = null;
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list,List<String> tabsList) {
        super(fm);
        this.list = list;
        this.tabsList = tabsList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabsList.get(position);
    }
}
