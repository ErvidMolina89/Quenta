package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quenta.DataAccess.DBLocal.ModelsLocal.Count
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item

@Dao
abstract class ItemDao : BaseDao<Item> {
    @Query("SELECT * FROM Item")
    abstract fun getList(): LiveData<List<Item>>

    @Query("SELECT * FROM Item WHERE tags = :string")
    abstract fun getListSearch(string: String): LiveData<List<Item>>

    @Query("DELETE FROM Count")
    abstract fun nukeItem()
}