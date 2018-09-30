package com.exam.login.presenter;

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.exam.basecomponent.net.CallBackWithBean
import com.exam.basecomponent.util.TypeUtil
import com.exam.login.contract.LoginContract
import com.exam.main.MainActivity
import com.exam.util.net.ApiService
import com.exam.util.param.User

class LoginPresenter : LoginContract.Presenter {


    override fun login(name: String, pwd: String,context: Context?) {
        val user = User(name,pwd)
        val type = TypeUtil.getType<Map<String,Any?>>()
        ApiService.login(user,object : CallBackWithBean<Map<String,Any?>> (context,type) {
            override fun deal(data: Map<String, Any?>?) {
                if(0 == data?.get("code")) {
                    val intent = Intent(context,MainActivity::class.java)
                    context?.startActivity(intent)
                }
            }
        })
    }

}