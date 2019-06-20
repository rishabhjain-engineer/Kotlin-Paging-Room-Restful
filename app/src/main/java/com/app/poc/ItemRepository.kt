package com.app.poc

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.os.AsyncTask
import com.app.poc.Room.ItemDao
import com.app.poc.Room.ItemDatabase
import com.app.poc.Room.ItemEntity

class ItemRepository(application: Application) {

    private var itemDao:ItemDao
   // private var allItemsList:LiveData<PagedList<ItemEntity>>

    init {
        val itemDatabase = ItemDatabase.getDatabaseInstance(application)
        itemDao = itemDatabase.itemDao()
        //allItemsList = itemDao.getAllItems()
    }


    fun insert(list: PagedList<ItemEntity>){
        InsertItemAsyncTask(itemDao,list).execute()
    }

    fun update(item:ItemEntity){
        UpdateItemAsyncTask(itemDao, item).execute()
    }

    fun delete(item:ItemEntity){
        DeleteItemAsyncTask(itemDao,item).execute()
    }


    fun deleteAll(){
        DeleteAllItemAsyncTask(itemDao).execute()
    }

    //fun getAllItems():LiveData<PagedList<ItemEntity>> {
        //return allItemsList
  //  }

    private companion object {

         class InsertItemAsyncTask(var itemDao: ItemDao, var list:PagedList<ItemEntity>) : AsyncTask<Void, Void, Unit>() {
             override fun doInBackground(vararg params: Void?) {
                 itemDao.insert(list)
             }

         }

        class UpdateItemAsyncTask(var itemDao: ItemDao, var item:ItemEntity) : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg params: Void?) {
                itemDao.update(item)
            }

        }


        class DeleteItemAsyncTask(var itemDao: ItemDao, var item:ItemEntity) : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg params: Void?) {
                itemDao.delete(item)
            }


        }

        class DeleteAllItemAsyncTask(var itemDao: ItemDao) : AsyncTask<Void, Void, Unit>() {
            override fun doInBackground(vararg params: Void?) {
                itemDao.deleteAllItems()
            }


        }
    }

}