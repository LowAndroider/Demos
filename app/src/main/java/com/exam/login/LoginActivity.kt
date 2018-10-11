package com.exam.login;

import com.exam.R
import com.exam.basecomponent.MvpActivity
import com.exam.login.presenter.LoginPresenter
import com.exam.login.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : MvpActivity<LoginContract.Presenter>(), LoginContract.View {

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        btnLogin.setOnClickListener {
            presenter.login(etName.text.toString(),etPwd.text.toString(),this)
        }

        ivSettings.setOnClickListener {

        }
    }

    override fun getPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }

    /**
     * 设置 ip 和 端口号
     */
    private fun ipConfigDialog() {

    }
}