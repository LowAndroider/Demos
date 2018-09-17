package com.exam.main;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.exam.R;
import com.exam.basecomponent.MvpActivity;
import com.exam.basecomponent.view.CViewPager;
import com.exam.category.CategoryFragment;
import com.exam.homepage.HomeFragment;
import com.exam.main.contract.MainContract;
import com.exam.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpActivity<MainContract.Presenter> implements MainContract.View {

    private CViewPager vp;

    private RadioGroup rgMenu;

    private CPagerAdapter cPagerAdapter;

    private List<Fragment> fragments;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        vp = findViewById(R.id.vp1);

        rgMenu = findViewById(R.id.rgMenu);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new HomeFragment());

        cPagerAdapter = new CPagerAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(cPagerAdapter);

        vp.setOffscreenPageLimit(3);

        rgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        presenter.setCurrentIndex(0, MainActivity.this);
                        break;
                    case R.id.rb2:
                        presenter.setCurrentIndex(1, MainActivity.this);
                        break;
                    case R.id.rb3:
                        presenter.setCurrentIndex(2, MainActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void switchCard(int index) {
        vp.setCurrentItem(index);
    }
}
