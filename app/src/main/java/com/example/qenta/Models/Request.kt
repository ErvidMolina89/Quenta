package com.example.qenta.Models

import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item

class Request : BaseModel()  {

    var total       : Int? = null
    var totalHits   : Int? = null
    var hits        : MutableList<Item>? = null
}