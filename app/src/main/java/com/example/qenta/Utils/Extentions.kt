package com.example.qenta.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.internal.LinkedTreeMap
import java.lang.Exception

/**
 * Created by exsis on 5/01/18.
 */


fun LinkedTreeMap<Any,Any>.toJsonString(): String{

    var jsonString = "{"
    var count = 0

    for (i in this) {

        jsonString += "\"" + i.key + "\" : "
        if(i.value is String){
            jsonString += "\"" + i.value + "\""
        }else if(i.value is LinkedTreeMap<*, *>){
            jsonString+=(i.value as LinkedTreeMap<Any,Any>).toJsonString()
        }else if(i.value is ArrayList<*>){
            val array = i.value as ArrayList<LinkedTreeMap<Any,Any>>
            if(array.isEmpty()){
                jsonString += "[]"
            }else{
                jsonString+="["
                var countArray = 0
                for(j in array ){
                    //TODO implementar con diferentes tipos de datos
                    jsonString += j.toJsonString()
                    countArray++
                    if(countArray<array.size){
                        jsonString+=","
                    }
                }
                jsonString+="]"
            }

        } else{
            jsonString += i.value
        }
        count++
        if(count<this.size){
            jsonString+=","
        }
    }
    jsonString+="}"
    return jsonString
}

fun Context.showDialogueGenerico(){
    if(this !is AppCompatActivity){ return }
    DialogueGenerico.getInstance().showDialogueGenerico(supportFragmentManager,"DialogoGenerico")
}

fun isNetworkAvailable(context: Context): Boolean {
    try {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        return false
    }
}


fun onHiddenInputMethod(context: Context, view: View) {
    val input = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.hideSoftInputFromWindow(view.windowToken, 0)
}

fun onHiddenInput (context: Context, editText: EditText){
    val im : InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(editText.windowToken, 0)
}