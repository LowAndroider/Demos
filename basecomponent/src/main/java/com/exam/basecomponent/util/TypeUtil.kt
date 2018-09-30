package com.exam.basecomponent.util

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @author LowAndroider
 *
 * @date 2018/9/30
 */
class TypeUtil {
    companion object {
        fun <T> getType(): Type {
            return object : TypeToken<T>() {}.type
        }
    }
}
