package com.app.poc

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("answers")
    fun getAnswers( @Query("page") page:Int ,
                    @Query("pagesize") pagesize:Int ,
                    @Query("site") site:String ):Call<StackApiResponse>
}