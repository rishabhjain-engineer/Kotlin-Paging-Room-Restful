package com.app.poc

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.util.Log

class ItemDataSourceFactory : DataSource.Factory<Int, Order>() {

    private val itemMutableLiveData:MutableLiveData<PageKeyedDataSource<Int, Order>> =
        MutableLiveData()

    override fun create(): DataSource<Int, Order> {

        val itemDataSource = ItemDataSource()
        Log.e("Rishabh","CREATE MUTABLE PAGE KEY DATA")
        itemMutableLiveData.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemMutableLiveData():MutableLiveData<PageKeyedDataSource<Int, Order>> {
        Log.e("Rishabh","GET MUTABLE PAGE KEY DATA")
        return itemMutableLiveData
    }


}