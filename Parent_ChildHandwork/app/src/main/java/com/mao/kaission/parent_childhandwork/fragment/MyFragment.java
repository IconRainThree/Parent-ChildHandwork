package com.mao.kaission.parent_childhandwork.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.kaission.parent_childhandwork.R;
import com.mao.kaission.parent_childhandwork.adapter.MyFragmentPagerAdapter;
import com.mao.kaission.parent_childhandwork.base.BaseFragment;
import com.mao.kaission.parent_childhandwork.fragment.myfragment.CourseFragment;
import com.mao.kaission.parent_childhandwork.fragment.myfragment.HomePageFragment;
import com.mao.kaission.parent_childhandwork.fragment.myfragment.RecordFragment;
import com.mao.kaission.parent_childhandwork.fragment.myfragment.TopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 主要界面：线性布局（垂直布局） 3个部分
 * 1.title
 * 2.tablayout和viewpager的联动
 */
public class MyFragment extends BaseFragment {

    public static final String TAG =MyFragment.class.getName();
    private Context context = null;
    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    //tab中显示的标题
    private List<String> tabsList = null;
    //显示在ViewPager中的数据
    private List<Fragment> fragmentList = null;
    private MyFragmentPagerAdapter myFragmentPagerAdapter = null;
    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my,container,false);
        initView(inflate);
        return inflate;
    }

    //初始化数据
    private void initView(View inflate) {
        tabLayout = (TabLayout) inflate.findViewById(R.id.tablayout_myfragment);
        viewPager = (ViewPager) inflate.findViewById(R.id.viewpager_myfragment);
        tabsList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        addTabs();
    }

    private void addTabs() {
        tabsList.add("首页");
        tabsList.add("教程");
        tabsList.add("排行榜");
        tabsList.add("记录");
        for (int i = 0; i < tabsList.size(); i++) {
            //添加tab到tablayout里面去
            tabLayout.addTab(tabLayout.newTab().setText(tabsList.get(i)));
        }
        initViewPager();
        //tablayout和viewpager的联动
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK,Color.WHITE);
    }

    //初始化viewpager中的数据
    private void initViewPager(){
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new CourseFragment());
        fragmentList.add(new TopFragment());
        fragmentList.add(new RecordFragment());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragmentList,tabsList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //保留viewpager中的fragment
        viewPager.setOffscreenPageLimit(4);
    }

}
