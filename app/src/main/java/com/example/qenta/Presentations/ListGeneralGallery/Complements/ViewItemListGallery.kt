package com.example.qenta.Presentations.ListGeneralGallery.Complements

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.R

class ViewItemListGallery @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)  {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_card_item_list,this,true)
        searchViewItems()
        addViewListeners()
    }

    private var cardView        : CardView ?= null
    private var image           : ImageView ?= null
    private var nameUser        : TextView ?= null
    private var comment         : TextView ?= null

    private fun searchViewItems(){
        cardView        = findViewById(R.id.card_container_item)
        image           = findViewById(R.id.iv_photo_item)
        nameUser        = findViewById(R.id.tv_name_user_card)
        comment         = findViewById(R.id.tv_comment_card)
    }

    private fun addViewListeners(){
        image?.setOnClickListener {
            listenerItem?.invoke(item!!)
        }
    }

    private var item : Item ?= null
    fun conItem(item : Item): ViewItemListGallery {
        this.item = item
        return this
    }

    fun updateView() : ViewItemListGallery {
        if(item == null){
            return this
        }

        post {
            updateImage()
            updateLabels()
        }
        return this
    }

    private fun updateImage(){
        if(item!!.previewURL == null ){ return }
        try {
            Glide
                .with(context)
                .load(item!!.previewURL)
                .placeholder(R.drawable.playa_la_roca)
                .into(image!!)
        } catch (e: Exception) {
            Log.e("error","",e)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateLabels() {
        if (item?.user == null && item?.comments == null) { return }
        nameUser?.setText(item!!.user)
        comment?.setText(item!!.comments.toString())
    }

    private var listenerItem: ((Item)->Unit)? = null
    fun conListenerItem(listenerItem: ((Item)->Unit)): ViewItemListGallery {
        this.listenerItem = listenerItem
        return this
    }

}