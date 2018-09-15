package com.exam.basecomponent;

/**
 * @author LowAndroider
 * @date   2018年9月14日15:02:31
 */

public abstract class MvpActivity<T> extends BaseActivity {

    protected T presenter;

    @Override
    protected void option() {
        presenter = getPresenter();
    }

    /**
     * 返回presenter对象，并初始化
     * @return presenter对象
     */
    protected abstract T getPresenter();
}
