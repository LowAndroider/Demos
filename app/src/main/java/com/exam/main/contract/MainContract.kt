package com.exam.main.contract

/**
 * @author LowAndroider
 * @date 15:06
 */

interface MainContract {
    interface Model {

        /**
         * @return 当前页面的标识
         */
        /**
         * 设置当前页面的标识
         */
        var currentIndex: Int
    }

    interface View {

        /**
         * 切换页面
         * @param index
         */
        fun switchCard(index: Int)
    }

    interface Presenter {
        /**
         * 设置当前页面的标识
         */
        fun setCurrentIndex(index: Int, view: View)
    }
}
