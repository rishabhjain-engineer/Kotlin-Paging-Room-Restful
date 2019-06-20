package com.app.poc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

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

        val viewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityViewModel::class.java)

        //viewModel.insertDataToDB()

        val adapter = ItemPageListAdapter(this@MainActivity)

        viewModel.getItemPagedList().observe(this@MainActivity, object : Observer<PagedList<StackApiResponse.Items>> {
            override fun onChanged(t: PagedList<StackApiResponse.Items>?) {
                Log.e("Rishabh", "on CHange Method called: SIZE: " + t!!.size)
                adapter.submitList(t)
            }

        })

        mRecyclerView.adapter = adapter
    }
}
