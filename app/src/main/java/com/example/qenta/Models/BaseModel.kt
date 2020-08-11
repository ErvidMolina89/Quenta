package com.example.qenta.Models

import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.google.gson.Gson

/**
 * Created by exsis on 30/10/17.
 */

open class BaseModel:
    IRetrofitParcelable {

    companion object {
        fun objectFromJson(json: String, type: Class<out BaseModel>): BaseModel? {
            try {
                val currentObject = Gson().fromJson(json, type)
                currentObject.doPostDeserializer()
                return currentObject
            } catch (e: com.google.gson.JsonSyntaxException) {
                return null
            }
        }
    }

    fun toJsonString(): String{
        return Gson().toJson(this)
    }

    open fun doPostDeserializer(){

    }

}