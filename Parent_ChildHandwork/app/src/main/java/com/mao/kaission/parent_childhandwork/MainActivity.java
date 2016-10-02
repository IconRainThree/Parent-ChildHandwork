package com.mao.kaission.parent_childhandwork;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mao.kaission.parent_childhandwork.base.BaseActivity;
import com.mao.kaission.parent_childhandwork.fragment.MyFragment;

/**
 * MainActivity中显示viewpager
 */
public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private MyFragment myFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_main,myFragment);
        fragmentTransaction.commit();

    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        myFragment = new MyFragment();
    }
}
