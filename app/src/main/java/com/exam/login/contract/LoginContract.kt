package com.exam.login.contract

import android.content.Context

interface LoginContract {

    interface View

    interface Presenter {

        fun login(name:String,pwd:String,context:Context?)
    }
}