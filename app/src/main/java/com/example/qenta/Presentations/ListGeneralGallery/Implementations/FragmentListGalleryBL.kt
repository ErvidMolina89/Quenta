package com.example.qenta.Presentations.ListGeneralGallery.Implementations

import android.content.Context
import android.util.Log
import com.example.qenta.Base.App
import com.example.quenta.Connection.Resources.Services
import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.qenta.DataAccess.Repositories.RepoService
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.DataAccess.Repositories.IRepository
import com.example.qenta.DataAccess.Repositories.RepoSynchronization
import com.example.qenta.Models.BaseModel
import com.example.qenta.Models.MessageResponse
import com.example.qenta.Models.Request
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryBL
import com.example.qenta.Presentations.ListGeneralGallery.Interfaces.IFragmentListGalleryListener
import com.example.qenta.R
import com.example.qenta.Utils.isNetworkAvailable
import com.example.quenta.DataAccess.DBLocal.ModelsLocal.Count

class FragmentListGalleryBL (listener : IFragmentListGalleryListener, context: Context):
    IFragmentListGalleryBL {

    private val synchronization = RepoSynchronization()

    private val context : Context
    private val listener : IFragmentListGalleryListener

    init {
        this.context = context
        this.listener = listener
    }
    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        try {
            if (isNetworkAvailable(context)){
                RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories())
                if (revisionBD() == true){
                    RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories())
                }
            } else {
                if (service == Services.list_total_gallery) {
                    synchronization.getAllitem(context).observeForever {
                        if (it?.size == 0) {
                            val msg = MessageResponse()
                            msg.Code = R.string.Internet
                            msg.Message =
                                context.resources.getString(R.string.detail_falla_Internet)
                            listener.failureService(msg)
                        } else {
                            listener.responseListGallery(it)
                        }
                    }
                }else {
                    RepoSynchronization().getAllitem(App.mContext!!).observeForever {
                        if (it?.size != 0){
                            listener.responseListGallery(it)
                        }else{
                            val msg = MessageResponse()
                            msg.Code = R.string.Internet
                            msg.Message =
                                context.resources.getString(R.string.detail_falla_Internet)
                            listener.failureService(msg)
                        }
                    }
                }
            }

            RepoService(context)
                .callService(objectResponse, objectSend, service, ListenerRepositories())
            RepoSynchronization().deleteAllCount(context)
            RepoSynchronization().deleteAllItems(context)
        }catch (e: Exception){

        }
    }

    private inner class ListenerRepositories : IRepository {

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.list_total_gallery  -> {
                    if (objectResponse == null){return}
                    val response = objectResponse as Request
                    listener.responseListGallery(response.hits?.toMutableList()!!)
                    synchronizationBD(objectResponse)
                }
                Services.list_search_gallery  -> {
                    val response = objectResponse as Request
                    listener.responseListGallery(response.hits?.toMutableList()!!)
                    synchronizationBD(objectResponse)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.list_total_gallery   -> {  }
                Services.list_search_gallery   -> {  }
                else -> {  }
            }
        }

    }

    private fun revisionBD(): Boolean {
        var res: Boolean = true
        synchronization.getAllitem(context).observeForever {
            if (it?.size == 0) {
                res = false
            } else res = true
        }
        return res
    }

    private fun synchronizationBD(request: Request){
        val count = Count()
        count.total = request.total
        count.totalHits = request.totalHits
        RepoSynchronization().onInsertCount(context, count)
        RepoSynchronization().onInsertItems(context, request.hits!!)
    }

}