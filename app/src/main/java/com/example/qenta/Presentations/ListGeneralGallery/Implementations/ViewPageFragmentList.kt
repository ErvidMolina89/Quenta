package com.example.qenta.Presentations.ListGeneralGallery.Implementations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.quenta.Connection.Resources.Services
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.qenta.Base.App
import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.Models.BaseModel
import com.example.qenta.Models.MessageResponse
import com.example.qenta.Models.Request
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryPresenter
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryView
import com.example.qenta.R
import kotlinx.android.synthetic.main.fragment_list_gallery.*

class ViewPageFragmentList: BaseFragment() {

    private var viewPrincial : View ?= null
    private var viewPager: ViewPager ?= null
    private var presenter: IFragmentListGalleryPresenter? = null
    private var actionPresenter = actionViewPresenter()
    private var pagerAdapter: ScreenSlidePagerAdapter ? = null
    var listenerFragmentListGallery: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincial = inflater.inflate(R.layout.fragment_viewpage_list,null,false)
        pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        viewPager = viewPrincial?.findViewById(R.id.viewpage_list_gallery)
        presenter = FragmentListGalleryPresenter(App.mContext!!, actionPresenter)
        return viewPrincial
    }

    override fun onResume() {
        super.onResume()
        callService(Request(), object :
            IRetrofitParcelable {}, Services.list_total_gallery)
        btn_search_gallery.setOnClickListener {
            val complement = et_search_gallery.text.toString().replace(" ", "+")
            presenter?.callService(Request(), object :
                IRetrofitParcelable {}, Services.list_search_gallery.getUrlWithComplement(complement))
        }
    }

    fun callService(objectResponse: BaseModel,
                    objectSend: IRetrofitParcelable,
                    service: Services
    ){
        presenter?.callService(objectResponse, objectSend, service)
    }

    inner class actionViewPresenter: IFragmentListGalleryView {
        override fun responseListGallery(request: List<com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item>) {
            Log.e("","")
            sizePageCount(request.toMutableList())
            list = request.toMutableList()
            viewPager?.adapter = pagerAdapter
        }

        override fun failureService(response: MessageResponse) {

        }
    }

    var page: Int = 0
    var list : MutableList<com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item> ?= null
    var listadeitems : MutableList<MutableList<Item>> = emptyList<MutableList<Item>>().toMutableList()
    private fun sizePageCount(request: MutableList<com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item>){
        val int = request.size
        if (request.size < 20) {
            page = 1
        }else page = int / 20
        Log.e("","")
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = page

        override fun getItem(position: Int): Fragment {
            Log.e("page", page.toString())
            return FragmentListGallery()
        }
    }

    interface OnFragmentInteractionListener {

    }

    companion object {
        fun newInstance() =
            ViewPageFragmentList().apply {
                arguments = Bundle().apply {}
            }
    }
}