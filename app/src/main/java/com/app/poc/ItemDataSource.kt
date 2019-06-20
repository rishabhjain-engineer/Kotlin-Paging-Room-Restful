package com.app.poc

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource : PageKeyedDataSource<Int, StackApiResponse.Items>() {


    private val PAGE_SIZE = 10
    private val FIRST_PAGE: Int = 1
    private val SITE_NAME: String = "stackoverflow"

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, StackApiResponse.Items>
    ) {

        Log.e("Rishabh","-----------------Load Initial-------------------"+FIRST_PAGE)


        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
            ?.enqueue(object : Callback<StackApiResponse> {
                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load Initial Failure")
                }

                override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {


                    if(response.body() != null){
                        callback.onResult(response.body()!!.items, null, FIRST_PAGE + 1)
                    }
                }

            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, StackApiResponse.Items>) {

        Log.e("Rishabh","-----------------Load After-------------------  "+params.key)

        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getAnswers(params.key, PAGE_SIZE, SITE_NAME)
            ?.enqueue(object : Callback<StackApiResponse> {
                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load After Failure")
                }

                override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {

                    if(response.body() != null){
                        val b: Boolean = (response.body()?.has_more!!)
                      //  Log.e("Rishabh","Has More value: "+b)
                        val key: Int?
                        if (b) {
                            key = params.key + 1
                        } else {
                            key = null
                        }
                        callback.onResult(response.body()?.items!!, key)
                    }

                }

            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, StackApiResponse.Items>) {

        Log.e("Rishabh","-----------------Load BEfore-------------------  "+params.key)

        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getAnswers(params.key, PAGE_SIZE, SITE_NAME)
            ?.enqueue(object : Callback<StackApiResponse> {
                override fun onFailure(call: Call<StackApiResponse>, t: Throwable) {
                    Log.e("Rishabh", "Load Before Failure")
                }

                override fun onResponse(call: Call<StackApiResponse>, response: Response<StackApiResponse>) {

                    if(response.body() != null) {
                        val b: Boolean = (params.key > 1)

                        val key: Int?
                        if (b) {
                            key = params.key - 1
                        } else {
                            key = null
                        }
                        callback.onResult(response.body()?.items!!, key)
                    }

                }

            })
    }
}