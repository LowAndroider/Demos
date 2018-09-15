package com.exam.main.contract;

/**
 * @author LowAndroider
 * @date 15:06
 */

public interface MainContract {
    interface Model {
        /**
         * 设置当前页面的标识
         */
        void setCurrentIndex(int index);

        /**
         * @return 当前页面的标识
         */
        int getCurrentIndex();
    }

    interface View {

        /**
         * 切换页面
         * @param index
         */
        void switchCard(int index);
    }

    interface Presenter {
        /**
         * 设置当前页面的标识
         */
        void setCurrentIndex(int index,View view);
    }
}
