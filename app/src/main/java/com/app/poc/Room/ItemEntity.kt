package com.app.poc.Room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.app.poc.StackApiResponse

@Entity(tableName = "Item_table")

class ItemEntity {

    @PrimaryKey(autoGenerate = true)
    private var id:Int = 0

    private var score:String

    @Embedded
    private var owner:StackApiResponse.Owner

    constructor(score:String, owner:StackApiResponse.Owner)  {
        this.score =score
        this.owner = owner
    }

    fun getScore():String {
        return score
    }

    fun getOwner():StackApiResponse.Owner {
        return owner
    }

    fun getId():Int{
        return id
    }

    fun setId(id:Int){
        this.id = id
    }


}