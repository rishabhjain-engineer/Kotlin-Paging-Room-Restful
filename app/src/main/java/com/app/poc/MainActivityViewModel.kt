package com.app.poc

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList

class MainActivityViewModel:ViewModel() {

    private  var itemPagedList:LiveData<PagedList<Order>>
    private  var liveDataSource:LiveData<PageKeyedDataSource<Int,Order>>
  //  private  var repository:ItemRepository

    init {

        val itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.getItemMutableLiveData()
       // repository = ItemRepository(application)

        val config:PagedList.Config =
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(10)
                    .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory,config).build()
    }


    fun insertDataToDB(){
        //repository.insert(itemPagedList.value!!)
    }



    fun getItemPagedList():LiveData<PagedList<Order>> {
        return itemPagedList
    }

}