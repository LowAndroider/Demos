package com.exam.main.model

import com.exam.main.contract.MainContract

/**
 * @author LowAndroider
 * @date 15:06
 */

class MainModel : MainContract.Model {

    /**
     * 当前选择的页面序号
     */
    override var currentIndex: Int = 0
}
