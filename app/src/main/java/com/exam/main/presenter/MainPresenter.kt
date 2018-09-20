package com.exam.main.presenter

import com.exam.main.contract.MainContract
import com.exam.main.model.MainModel

/**
 * @author LowAndroider
 * @date 15:06
 */

class MainPresenter : MainContract.Presenter {

    private val model = MainModel()

    override fun setCurrentIndex(index: Int, view: MainContract.View) {
        model.currentIndex = index
        view.switchCard(index)
    }
}
