package com.example.qenta.Presentations.ListGeneralGallery.Implementations

import android.content.Context
import com.example.quenta.Connection.Resources.Services
import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.Models.BaseModel
import com.example.qenta.Models.MessageResponse
import com.example.qenta.Models.Request
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.*

class FragmentListGalleryPresenter (context: Context, view : IFragmentListGalleryView):
    IFragmentListGalleryPresenter{

    private val selectView : IFragmentListGalleryView
    private val context : Context
    private val selectItemBL : IFragmentListGalleryBL

    init {
        this.context = context
        this.selectView = view
        this.selectItemBL = FragmentListGalleryBL(Listener(), context)
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        selectItemBL.callService(objectResponse, objectSend, service)
    }



    private inner class Listener : IFragmentListGalleryListener {
        override fun responseListGallery(request: List<Item>) {
            selectView.responseListGallery(request)
        }

        override fun failureService(response: MessageResponse) {
            selectView.failureService(response)
        }
    }
}