package com.example.pruebavalid.Base.Implementations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qenta.R
import com.example.qenta.Utils.DialogueGenerico
import com.example.qenta.Utils.HiddenKeyBoard
import com.example.qenta.Utils.showDialogueGenerico

open class BaseFragment: Fragment() {

    var baseActivity: BaseActivity? = null
    var vista: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        baseActivity = activity as BaseActivity
        HiddenKeyBoard.newInstance(vista!!)
        return vista
    }


    fun dialogueFragment(title: String,
                                 detail: String,
                                 type: DialogueGenerico.TypeDialogue){
        DialogueGenerico
            .getInstance()
            .withTitle(title)
            .withText(detail)
            .withTypeDialogue(type)
            .withTextBtnAccept(R.string.btn_aceptar)
            .withActionBtnAccept {
                Log.e("","")
            }
        context?.showDialogueGenerico()
    }

}