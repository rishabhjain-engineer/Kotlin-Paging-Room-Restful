package com.app.poc

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.google.gson.JsonElement
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class ItemDataSource : PageKeyedDataSource<Int,Order>() {


    private val PAGE_SIZE = 10
    private val FIRST_PAGE: Int = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Order>) {

        Log.e("Rishabh", "-----------------Load Initial-------------------" + FIRST_PAGE)

        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getOrders(FIRST_PAGE, PAGE_SIZE)
            ?.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load Initial Failure")
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    if (response.body() != null) {
                        callback.onResult(response.body()!!.order, null, FIRST_PAGE + 1)
                    }
                }

            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {

        Log.e("Rishabh", "-----------------Load After-------------------  " + params.key)

        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getOrders(params.key, PAGE_SIZE)
            ?.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load After Failure")
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    if (response.body() != null) {
                        val b: Boolean = (response.body()!!.has_more)
                        Log.e("Rishabh","has_more: "+b)
                        val key: Int?
                        if (b) {
                            key = params.key + 1
                        } else {
                            key = null
                        }
                        callback.onResult(response.body()!!.order, key)
                    }else {
                        Log.e("Rishabh","Load after response bull")
                    }

                }

            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Order>) {

        Log.e("Rishabh", "-----------------Load BEfore-------------------  " + params.key)

        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getOrders(params.key, PAGE_SIZE)
            ?.enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load Before Failure")
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    if (response.body() != null) {
                        val b: Boolean = (params.key > 1)
                        val key: Int?
                        if (b) {
                            key = params.key - 1
                        } else {
                            key = null
                        }

                        callback.onResult(response.body()!!.order, key)
                    }

                }

            })
    }
}