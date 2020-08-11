package com.example.qenta.Presentations.ListGeneralGallery.Interfaces

import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.qenta.Models.MessageResponse

interface IFragmentListGalleryListener {
    fun responseListGallery(request: List<Item>)
    fun failureService(response: MessageResponse)
}