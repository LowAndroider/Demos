package com.exam.main.model;

import com.exam.main.contract.MainContract;

/**
 * @author LowAndroider
 * @date 15:06
 */

public class MainModel implements MainContract.Model {

    /**
     * 当前选择的页面序号
     */
    private int index;

    public int getCurrentIndex() {
        return index;
    }

    @Override
    public void setCurrentIndex(int index) {
        this.index = index;
    }
}
