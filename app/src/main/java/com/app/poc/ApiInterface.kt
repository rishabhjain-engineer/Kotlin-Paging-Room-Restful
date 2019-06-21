package com.app.poc

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

   /* @GET("answers")
    fun getAnswers( @Query("page") page:Int ,
                    @Query("pagesize") pagesize:Int ,
                    @Query("site") site:String ):Call<JsonElement>*/


    @GET("test_paging")
    fun getOrders( @Query("page") page:Int ,
                    @Query("per_page") pagesize:Int):Call<ApiResponse>
}