package com.example.logginscreen.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.logginscreen.`interface`.ItemClickListener
import kotlinx.android.synthetic.main.source_news_layout.view.*

class ListSourceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private lateinit var itemClickListener: ItemClickListener

    var source_title = itemView.source_news_name

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!, adapterPosition)
    }

}