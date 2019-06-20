package com.app.poc

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import android.util.Log
import com.app.poc.Room.ItemEntity

class MainActivityViewModel:ViewModel() {

    private  var itemPagedList:LiveData<PagedList<StackApiResponse.Items>>
    private  var liveDataSource:LiveData<PageKeyedDataSource<Int,StackApiResponse.Items>>
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
       // Log.e("Rishabh","itemPagedList: "+itemPagedList.value!!.size)
        Log.e("Rishabh","--------------------------------------")
    }


    fun insertDataToDB(){
        //repository.insert(itemPagedList.value!!)
    }



    fun getItemPagedList():LiveData<PagedList<StackApiResponse.Items>> {
       // Log.e("Rishabh","itemPagedList received: "+repository.getAllItems().value!!.size)
        Log.e("Rishabh","--------------------------------------")
        Log.e("Rishabh","***************************************")
        return itemPagedList
    }

}