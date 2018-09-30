package com.exam.basecomponent.net

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author LowAndroider
 *
 * @date 2018/9/30
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        Log.d("httpInfo",message + "")
    }
}