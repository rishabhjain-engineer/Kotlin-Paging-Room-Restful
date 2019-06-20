package com.app.poc.Room

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.PagedList
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.app.poc.StackApiResponse

@Dao
interface ItemDao {

    @Insert(onConflict=REPLACE)
    fun insert(items: PagedList<ItemEntity>?)

    @Update
    fun update(itemEntity:ItemEntity?)

    @Delete
    fun delete(itemEntity: ItemEntity?)

    @Query("DELETE FROM item_table")
    fun deleteAllItems()

    //@Query("SELECT * FROM item_table")
    //fun getAllItems(): DataSource.Factory<Int,ItemEntity>

}