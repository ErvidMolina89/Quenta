package com.example.qenta.Presentations.ListGeneralGallery.Interfaces

import com.example.quenta.Connection.Resources.Services
import com.example.qenta.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.qenta.Models.BaseModel

interface IFragmentListGalleryBL {
    fun callService(objectResponse: BaseModel, objectSend: IRetrofitParcelable, service: Services)
}