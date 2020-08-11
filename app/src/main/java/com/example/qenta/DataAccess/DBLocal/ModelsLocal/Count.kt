package com.example.quenta.DataAccess.DBLocal.ModelsLocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.qenta.Models.BaseModel


@Entity
data class Count (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IdCount")           var idCount: Int? = null,
    @ColumnInfo(name = "total")             var total: Int? = null,
    @ColumnInfo(name = "totalHits")         var totalHits: Int? = null
):BaseModel()