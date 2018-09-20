package com.exam.main

import android.support.v4.app.Fragment

import com.exam.R
import com.exam.basecomponent.MvpActivity
import com.exam.basecomponent.view.CViewPager
import com.exam.category.CategoryFragment
import com.exam.homepage.HomeFragment
import com.exam.main.contract.MainContract
import com.exam.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : MvpActivity<MainContract.Presenter>(), MainContract.View {

    private var vp: CViewPager? = null

    private var cPagerAdapter: CPagerAdapter? = null

    private var fragments: MutableList<Fragment>? = null

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        vp = findViewById(R.id.vp1)
        fragments = ArrayList()
        fragments?.add(HomeFragment())
        fragments?.add(CategoryFragment())
        fragments?.add(HomeFragment())

        cPagerAdapter = CPagerAdapter(supportFragmentManager, fragments!!)
        vp?.adapter = cPagerAdapter

        vp?.offscreenPageLimit = 3

        rgMenu!!.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb1 -> presenter.setCurrentIndex(0, this@MainActivity)
                R.id.rb2 -> presenter.setCurrentIndex(1, this@MainActivity)
                R.id.rb3 -> presenter.setCurrentIndex(2, this@MainActivity)
            }
        }
    }

    override fun getPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    override fun switchCard(index: Int) {
        vp!!.currentItem = index
    }
}
