package com.example.qenta.Presentations

import android.os.Bundle
import com.example.pruebavalid.Base.Implementations.BaseActivity
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.Presentations.ListGeneralGallery.Implementations.FragmentDetailGalleryItem
import com.example.qenta.Presentations.ListGeneralGallery.Implementations.FragmentListGallery
import com.example.qenta.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        invokeFragmentInit()
    }

    override fun onBackPressed() {
        if (FragmentListGallery.newInstance().isVisible){
            return
        }
        super.onBackPressed()
    }

    private fun invokeFragmentInit(){
        val fragment = FragmentListGallery()
        fragment.listenerFragmentListGallery = onActionViewFragmentListGallery()
        navigationEntreFragment(R.id.container_fragment_dash, null,  fragment)
    }

    inner class onActionViewFragmentListGallery: FragmentListGallery.OnFragmentInteractionListener{
        override fun callViewListServiceTrack(item: Item) {
            invokeFragmentDetailItem(item)
        }

    }

    private fun invokeFragmentDetailItem(item: Item){
        val fragment = FragmentDetailGalleryItem()
        fragment.item = item
        navigationEntreFragment(R.id.container_fragment_dash, null,  fragment)
    }
}
