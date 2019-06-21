package com.app.poc.Room

import android.arch.persistence.room.*
import android.content.Context


//@Database(entities= arrayOf(ItemEntity::class), version = 1)

public abstract class ItemDatabase: RoomDatabase() {

    public abstract fun itemDao():ItemDao


    companion object {

        private var Instance:ItemDatabase? = null

        @Synchronized
        public fun getDatabaseInstance(context:Context):ItemDatabase {

            if(Instance == null) {
                Instance =
                        Room.databaseBuilder(context.applicationContext,
                             ItemDatabase::class.java,
                            "item_database")
                            .fallbackToDestructiveMigration()
                            .build()
            }
                return Instance!!
        }

    }

}