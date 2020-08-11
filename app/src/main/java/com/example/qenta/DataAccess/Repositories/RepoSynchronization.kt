package com.example.qenta.DataAccess.Repositories

import android.content.Context
import com.example.qenta.DataAccess.DBLocal.ModelsLocal.Item
import com.example.quenta.DataAccess.DBLocal.DBQenta
import com.example.quenta.DataAccess.DBLocal.ModelsLocal.Count
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoSynchronization {

    fun onInsertCount(context: Context, count: Count){
        GlobalScope.launch {
            DBQenta.getInstance(context).countDao().insert(count)
        }
    }

    fun onInsertItems(context: Context, array: MutableList<Item>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBQenta.getInstance(context).itemDao().insertList(array))
        }
    }

    fun deleteAllCount(context: Context) =
        GlobalScope.launch {
        DBQenta.getInstance(context).countDao().nukeCount()
        }

    fun deleteAllItems(context: Context) =
        GlobalScope.launch {
        DBQenta.getInstance(context).itemDao().nukeItem()
        }

    fun getAllitem(context: Context)
            = DBQenta.getInstance(context).itemDao().getList()

    fun getAllitemSearch(context: Context, string: String)
            = DBQenta.getInstance(context).itemDao().getListSearch(string)

    fun getCount(context: Context)
            = DBQenta.getInstance(context).countDao().getCount()

    }