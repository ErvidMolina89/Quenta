package com.example.qenta.Presentations

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.pruebavalid.Base.Implementations.BaseActivity
import com.example.qenta.DataAccess.Repositories.RepoSynchronization
import com.example.qenta.Models.Item
import com.example.qenta.Presentations.ListGeneralGallery.Implementations.FragmentDetailGalleryItem
import com.example.qenta.Presentations.ListGeneralGallery.Implementations.FragmentListGallery
import com.example.qenta.Presentations.ListGeneralGallery.Implementations.ViewPageFragmentList
import com.example.qenta.R
import com.example.quenta.DataAccess.DBLocal.DBQenta

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
//        invokeFragmentInit()
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
