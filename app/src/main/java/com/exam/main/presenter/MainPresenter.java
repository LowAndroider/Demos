package com.exam.main.presenter;

import com.exam.main.contract.MainContract;
import com.exam.main.model.MainModel;

/**
 * @author LowAndroider
 * @date 15:06
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Model model = new MainModel();

    @Override
    public void setCurrentIndex(int index, MainContract.View view) {
        model.setCurrentIndex(index);
        view.switchCard(index);
    }
}
