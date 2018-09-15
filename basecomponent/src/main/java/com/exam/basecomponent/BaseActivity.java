package com.exam.basecomponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.noober.background.BackgroundLibrary;

public abstract class BaseActivity extends AppCompatActivity {

//    protected T presenter;


    /**
     * @return 返回布局id
     */
    protected abstract int getLayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        option();
        init();
    }

    /**
     * 界面内容初始化之前的操作，可选
     */
    protected void option() {}

    /**
     * 界面初始化
     */
    protected abstract void init();
}
