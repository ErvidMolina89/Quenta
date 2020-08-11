package com.example.qenta.DataAccess.Repositories

import com.example.quenta.Connection.Resources.Services
import com.example.qenta.Models.MessageResponse

interface IRepository {
    fun onSuccessResponse(objectResponse: Any?, services: Services)
    fun onFailedResponse(response: MessageResponse, services: Services)
}