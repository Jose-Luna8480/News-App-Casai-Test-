package com.example.logginscreen.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logginscreen.ListNews
import com.example.logginscreen.R
import com.example.logginscreen.`interface`.ItemClickListener
import com.example.logginscreen.model.WebSite

class ListSourceAdapter(private val context: Context, private val webSite: WebSite):RecyclerView.Adapter<ListSourceViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.source_news_layout, parent, false)
        return ListSourceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return webSite.sources!!.size
    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {
        holder!!.source_title.text = webSite.sources!![position].name

        holder.setItemClickListener(object :ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context,ListNews::class.java)
                intent.putExtra("Source",webSite.sources!![position].id)
                context.startActivity(intent)
            }
        })
    }

}