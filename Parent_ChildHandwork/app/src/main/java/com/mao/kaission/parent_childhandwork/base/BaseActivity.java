package com.mao.kaission.parent_childhandwork.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mao.kaission.parent_childhandwork.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
