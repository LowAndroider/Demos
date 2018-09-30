package com.exam.basecomponent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
        //19表示4.4
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //虚拟键盘也透明
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(getLayout());
        option();
        init();
    }

    /**
     * 界面内容初始化之前的操作，可选
     */
    protected void option() {
    }

    /**
     * 界面初始化
     */
    protected abstract void init();
}
