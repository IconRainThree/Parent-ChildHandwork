package com.mao.kaission.parent_childhandwork.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mao.kaission.parent_childhandwork.MainActivity;
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

    public static final String TAG = MyFragment.class.getName();
    private Context context = null;
    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    //tab中显示的标题
    private List<String> tabsList = null;
    //显示在ViewPager中的数据
    private List<Fragment> fragmentList = null;
    private MyFragmentPagerAdapter myFragmentPagerAdapter = null;

    private Toolbar toolbar = null;
    private DrawerLayout drawerLayout = null;
    private ActionBarDrawerToggle toggle = null;

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
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //初始化数据
    private void initView(View inflate) {
        tabLayout = (TabLayout) inflate.findViewById(R.id.tablayout_myfragment);
        viewPager = (ViewPager) inflate.findViewById(R.id.viewpager_myfragment);
        tabsList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        initToolBar(inflate);
        addTabs();
    }

    private void initToolBar(View inflate) {
        toolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) inflate.findViewById(R.id.drawerlayout);
        //设置属性
        //设置标题
//        toolbar.setTitle("标题");
//        //设置子标题
//        toolbar.setSubtitle("子标题");
        //设置导航图标
//        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        //必须要设置这行代码,menu菜单上面的选项才能设置到toolbar上面
//       setSupportActionBar(toolbar);

        //给toolbar上面到menu设置点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_two) {
                    Toast.makeText(context, "two", Toast.LENGTH_SHORT).show();
                }else if(item.getItemId() == R.id.item_one){
                    Toast.makeText(context, "two", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        //创建开关对象
        //参数1:环境变量2.侧滑菜单3.toolbar4.5:打开菜单和关闭菜单描述信息(@string)
        toggle = new ActionBarDrawerToggle((Activity) context,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //设置同步状态
        toggle.syncState();
        //将drawerlayout监听设置为开关
        drawerLayout.setDrawerListener(toggle);
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
        tabLayout.setTabTextColors(Color.BLACK, Color.WHITE);
    }

    //初始化viewpager中的数据
    private void initViewPager() {
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new CourseFragment());
        fragmentList.add(new TopFragment());
        fragmentList.add(new RecordFragment());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList, tabsList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //保留viewpager中的fragment
        viewPager.setOffscreenPageLimit(4);
    }

}
