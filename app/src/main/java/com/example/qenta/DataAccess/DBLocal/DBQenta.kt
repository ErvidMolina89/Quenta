package com.example.quenta.DataAccess.DBLocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quenta.DataAccess.DBLocal.Daos.CountDao
import com.example.quenta.DataAccess.DBLocal.Daos.ItemDao
import com.example.quenta.DataAccess.DBLocal.ModelsLocal.Count
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item

@Database(entities = arrayOf(
    Count::class,
    Item::class
), version = 1)
abstract class DBQenta : RoomDatabase() {

    abstract fun countDao(): CountDao
    abstract fun itemDao(): ItemDao

    companion object {
        private const val nameDB = "DBQenta"
        @Volatile
        private var INSTANCE: DBQenta? = null

        fun getInstance(context: Context): DBQenta =
                INSTANCE ?: synchronized(this) {
                    buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room
                    .databaseBuilder(context.applicationContext, DBQenta::class.java, nameDB)
                    .build()
    }

}