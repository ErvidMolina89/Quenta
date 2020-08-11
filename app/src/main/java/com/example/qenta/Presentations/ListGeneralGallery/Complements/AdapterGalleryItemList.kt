package com.example.qenta.Presentations.ListGeneralGallery.Complements

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.R


class AdapterGalleryItemList : RecyclerView.Adapter<AdapterGalleryItemList.itemListGallery>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemListGallery {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_lista_items_gallery, null, false)
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 400)
        view.layoutParams = params
        return itemListGallery(view)

    }

    override fun getItemCount(): Int {
        Log.e("Tamaño lista", listaItem.size.toString())

        return listaItem.size
    }

    override fun onBindViewHolder(holder: itemListGallery, position: Int) {
        val viewHolder = holder.view.findViewById<ViewItemListGallery>(R.id.recycler_item_list_gallery)
        viewHolder
            .conItem(listaItem[position])
            .conListenerItem {
                listenerItemGallery?.invoke(it)
            }
            .updateView()

    }

    inner class itemListGallery(val view: View): RecyclerView.ViewHolder(view)

    private var context: Context? = null
    fun conContext(context: Context): AdapterGalleryItemList {
        this.context = context
        return this
    }

    private var recyclerView: RecyclerView? = null
    fun conRecyclerView(recyclerView: RecyclerView): AdapterGalleryItemList {
        this.recyclerView = recyclerView
        return this
    }

    private var listaItem = emptyList<Item>().toMutableList()
    fun conListItem (list: MutableList<Item>): AdapterGalleryItemList {
        this.listaItem = list
        return this
    }

    private var listenerItemGallery: ((Item)-> Unit)? = null
    fun conListenerGalleryItemList(escuchador: (Item)-> Unit): AdapterGalleryItemList {
        this.listenerItemGallery = escuchador
        return this
    }

    fun cargarRecycler(){

        recyclerView?.post {
            recyclerView?.adapter = null
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = this
        }

    }
}