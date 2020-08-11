package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quenta.DataAccess.DBLocal.ModelsLocal.Count

@Dao
abstract class CountDao : BaseDao<Count> {
    @Query("SELECT * FROM Count")
    abstract fun getCount(): LiveData<Count>

    @Query("DELETE FROM Count")
    abstract fun nukeCount()
}