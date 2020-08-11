package com.example.qenta.Presentations.ListGeneralGallery.Implementations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.qenta.Base.App
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.R
import kotlinx.android.synthetic.main.fragment_detail_gallery.*

class FragmentDetailGalleryItem: BaseFragment() {

    private var viewPrincial : View ?= null
    var item: Item? = null
    private var animator_y   : ObjectAnimator? = null
    private var animator_set : AnimatorSet? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincial = inflater.inflate(R.layout.fragment_detail_gallery,null,false)
        return viewPrincial
    }

    override fun onResume() {
        super.onResume()
        paintImageDetailItem()
        viewPrincial?.post {
            animationEjeY()
        }
    }

    private fun paintImageDetailItem(){
        if(item!!.largeImageURL == null ){ return }
        try {
            Glide
                .with(App.mContext!!)
                .load(item!!.largeImageURL)
                .placeholder(R.drawable.playa_la_roca)
                .into(iv_img_detail_gallery)
        } catch (e: Exception) {
            Log.e("error","",e)
        }
    }

    private fun animationEjeY(){
        viewPrincial?.post {
            animator_y = ObjectAnimator.ofFloat(iv_img_detail_gallery, "y", -20f)
            animator_y?.setDuration(250)
            animator_set = AnimatorSet()
            animator_set?.play(animator_y)
            animator_set?.start()
        }
    }

    interface OnFragmentInteractionListener {

    }

    companion object {
        fun newInstance() =
            FragmentDetailGalleryItem().apply {
                arguments = Bundle().apply {}
            }
    }
}