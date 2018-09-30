package com.exam.util.net

import com.exam.basecomponent.net.OkHttpUtil
import com.exam.util.param.User
import okhttp3.Callback

/**
 * @author LowAndroider
 * @date 2018/9/30
 */
class ApiService {

    companion object {
        /**
         * 登陆请求
         */
        fun login(info:User,callBack: Callback?) {
            OkHttpUtil.post(ApiUrl.loginUrl,info,callBack)
        }
    }
}
