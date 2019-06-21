package com.app.poc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Rishabh","Main activity on create")
        mRecyclerView = findViewById(R.id.recycler_view)
        mLayoutManager = LinearLayoutManager(this@MainActivity)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.setHasFixedSize(true)

        //hitApi()

        val viewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityViewModel::class.java)

        //viewModel.insertDataToDB()

        val adapter = ItemPageListAdapter(this@MainActivity)

        viewModel.getItemPagedList().observe(this@MainActivity, object : Observer<PagedList<Order>> {
            override fun onChanged(t: PagedList<Order>?) {
                Log.e("Rishabh", "on CHange Method called: SIZE: " + t!!.size)
                adapter.submitList(t)
            }

        })

        mRecyclerView.adapter = adapter
    }

   /* private fun hitApi(){
        RetrofitClient
            .getInstance()
            ?.getApi()
            ?.getAnswers(1, 50, "stackoverflow")
            ?.enqueue(object : Callback<JsonElement> {
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    Log.e("Rishabh", "Load Initial Failure")
                }

                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                    if (response.body() != null) {
                        val d = response.body().toString()
                        val jsonObject = JSONObject(d)
                        Log.e("Rishabh","acctivity response: "+jsonObject.toString())
                    }
                }

            })
    }*/
}
