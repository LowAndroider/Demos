package com.exam.basecomponent.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author LowAndroider
 *
 * @date 2018/9/30
 */
class Converter {

    companion object {
        val gson = Gson()

        fun<T> map2Entity(map: Map<String,Any?>?) : T? {

            val json = gson.toJson(map)

            return gson.fromJson(json,object: TypeToken<T>(){}.type)
        }

        fun entity2Map(entity:Any?):Map<String,Any?> {
            val json = gson.toJson(entity)

            return gson.fromJson(json,object : TypeToken<Map<String,Any?>>(){}.type)
        }
    }


}