package com.app.poc

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemPageListAdapter(var context: Context) :
    PagedListAdapter<Order, ItemPageListAdapter.ItemViewHolder>(DIFF_CAllBACK) {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {

        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.single_row, p0, false)
        val itemViewHolder = ItemViewHolder(view)
        return itemViewHolder
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {


        val item: Order? = getItem(p1)


        p0.textview.setText(item!!.nested_cust_name)

        var list: List<NestedOrder> = item.nested_order

        var abc:String = ""

        for ( i in 0..list.size-1){
            abc = abc.plus(item.nested_order.get(i).second_nested_cust_name)
        }

        p0.desctextview.setText(abc)

    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textview: TextView
        var desctextview: TextView

        init {
            textview = itemView.findViewById(R.id.single_name)
            desctextview = itemView.findViewById(R.id.single_description)
        }
    }


    companion object {

        private val DIFF_CAllBACK: DiffUtil.ItemCallback<Order> = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(p0: Order, p1: Order): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: Order, p1: Order): Boolean {
                return p0.equals(p1)
            }

        }
    }
}

