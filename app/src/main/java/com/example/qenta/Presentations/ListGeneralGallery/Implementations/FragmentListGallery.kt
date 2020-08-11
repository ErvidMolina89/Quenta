package com.example.qenta.Presentations.ListGeneralGallery.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quenta.Connection.Resources.Services
import com.example.pruebavalid.Base.Implementations.BaseFragment
import com.example.qenta.Base.App
import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.qenta.Models.BaseModel
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.Models.MessageResponse
import com.example.qenta.Models.Request
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryPresenter
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryView
import com.example.qenta.R
import com.example.qenta.Utils.DialogueGenerico
import com.example.qenta.Utils.onHiddenInputMethod
import kotlinx.android.synthetic.main.fragment_list_gallery.*

class FragmentListGallery: BaseFragment() {

    private var viewPrincial : View ?= null
    private var presenter: IFragmentListGalleryPresenter? = null
    private var actionPresenter = actionViewPresenter()
    var listenerFragmentListGallery: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPrincial = inflater.inflate(R.layout.fragment_list_gallery,null,false)
        presenter = FragmentListGalleryPresenter(App.mContext!!, actionPresenter)
        return viewPrincial
    }

    override fun onResume() {
        super.onResume()
            callService(Request(), object :
                IRetrofitParcelable {},
                Services.list_total_gallery)

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
        override fun responseListGallery(request: List<Item>) {
            visualizarList(request.toMutableList())
        }

        override fun failureService(response: MessageResponse) {
            failedMessage(response.Message!!, response.Code.toString())
        }
    }

    private fun visualizarList(list: MutableList<Item>){
        view_list_gallery
            .conVisualizeListGallery(list)
            .conListenerGalleryItemList {
                listenerFragmentListGallery?.callViewListServiceTrack(it)
            }
        onHiddenInputMethod(this.context!!, et_search_gallery)
    }

    private fun failedMessage(msg: String, title: String) {
        dialogueFragment(title, msg, DialogueGenerico.TypeDialogue.ERROR)
    }

    interface OnFragmentInteractionListener {
        fun callViewListServiceTrack(item: Item)

    }

    companion object {
        fun newInstance() = FragmentListGallery().apply {
                arguments = Bundle().apply {}
            }
    }
}