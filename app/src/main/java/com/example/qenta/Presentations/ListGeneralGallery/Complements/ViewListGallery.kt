package com.example.qenta.Presentations.ListGeneralGallery.Complements

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.qenta.Base.App
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.R

class ViewListGallery @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_list_general_gallery,this,true)
        searchViewItems()
        paintGalleryItemList()
    }

    private var rv_list_general_gallery : RecyclerView? = null

    private fun searchViewItems(){
        rv_list_general_gallery = findViewById(R.id.rv_list_general_gallery)
    }

    private var adapterGalleryItemList  : AdapterGalleryItemList? = null
    private fun paintGalleryItemList(){
        this.adapterGalleryItemList = AdapterGalleryItemList()
            .conContext(App.mContext!!)
            .conRecyclerView(rv_list_general_gallery!!)
            .conListenerGalleryItemList {
                listenerListGallery?.invoke(it)
            }
    }

    private var listenerListGallery: ((Item)-> Unit)? = null
    fun conListenerGalleryItemList(listenerListGallery: (Item)-> Unit): ViewListGallery {
        this.listenerListGallery = listenerListGallery
        return this
    }

    fun conVisualizeListGallery(list: MutableList<Item>): ViewListGallery {
        post {
            adapterGalleryItemList
                ?.conListItem(list)
                ?.cargarRecycler()
        }
        return this
    }

    fun showView(){
        post {
            visibility = View.VISIBLE
            paintGalleryItemList()
        }
    }

    fun hideView(){
        post {
            visibility = View.GONE
        }
    }
}