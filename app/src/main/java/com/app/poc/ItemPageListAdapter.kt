package com.app.poc

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ItemPageListAdapter(var context:Context) :
    PagedListAdapter<StackApiResponse.Items, ItemPageListAdapter.ItemViewHolder>(DIFF_CAllBACK) {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {

        var view:View = LayoutInflater.from(p0.context).inflate(R.layout.single_row,p0,false)
        val itemViewHolder = ItemViewHolder(view)
        return itemViewHolder
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {

        val item: StackApiResponse.Items? = this!!.getItem(p1)

        Glide
            .with(context)
            .load(item?.owner?.profile_image)
            .into(p0.imageview)

        p0.textview.setText(item?.owner?.display_name)

    }


    class ItemViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textview:TextView
        var imageview:ImageView

        init {
            textview = itemView.findViewById(R.id.textview)
            imageview = itemView.findViewById(R.id.profile)
        }
    }


    companion object{

        private val DIFF_CAllBACK:DiffUtil.ItemCallback<StackApiResponse.Items>
                = object: DiffUtil.ItemCallback<StackApiResponse.Items>() {
            override fun areItemsTheSame(p0: StackApiResponse.Items, p1: StackApiResponse.Items): Boolean {
                return p0.answer_id == p1.answer_id
            }

            override fun areContentsTheSame(p0: StackApiResponse.Items, p1: StackApiResponse.Items): Boolean {
                return p0.equals(p1)
            }

        }
    }
}

