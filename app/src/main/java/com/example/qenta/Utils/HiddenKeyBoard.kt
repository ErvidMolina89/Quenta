package com.example.qenta.Utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import org.jetbrains.anko.forEachChild

class HiddenKeyBoard {

    companion object {
        fun newInstance(view: View){
            val hiddenKeyBoard =
                HiddenKeyBoard()
            hiddenKeyBoard.hiddenInInitialView(view)
            hiddenKeyBoard.hidden(view)
        }
    }


    private fun hidden(view: View){
        when(view){
            is ConstraintLayout ->{navigateConstraint     (view,::hidden) }
            is LinearLayout         ->{navigateLinearLayout   (view,::hidden) }
            is RelativeLayout       ->{navigateRelativeLayout (view,::hidden) }
            is TableLayout          ->{navigateTableLayout    (view,::hidden) }
            is TableRow             ->{navigateTableRow       (view,::hidden) }
            is CardView ->{navigateCardview       (view,::hidden) }
            is EditText             ->{}
            is AppCompatEditText ->{}
            is AutoCompleteTextView ->{}
            else->{
                listennerHiden(view)
            }
        }
    }

    private fun listennerHiden(view:View){
        view.setOnTouchListener { view, motionEvent ->
            val imm : InputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
    private fun hiddenInInitialView(view : View){
        val imm : InputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
    private fun navigateConstraint          (view : ConstraintLayout,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
    private fun navigateLinearLayout        (view : LinearLayout    ,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
    private fun navigateRelativeLayout      (view : RelativeLayout  ,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
    private fun navigateTableLayout         (view : TableLayout     ,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
    private fun navigateTableRow            (view : TableRow        ,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
    private fun navigateCardview            (view : CardView        ,hidden :(View)->Unit)
    {
        listennerHiden(view)
        view.forEachChild {
            hidden(it)
        }
    }
}