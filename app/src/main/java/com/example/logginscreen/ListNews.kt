package com.example.logginscreen

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logginscreen.`interface`.NewsService
import com.example.logginscreen.adapter.viewholder.ListNewsAdapter
import com.example.logginscreen.common.Common
import com.example.logginscreen.model.News
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_list_news.*
import kotlinx.android.synthetic.main.activity_news_feed.swipe_to_refresh
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNews : AppCompatActivity() {

    var source = ""
    var webHotUrl:String?=""

    lateinit var dialog:AlertDialog
    lateinit var mService: NewsService
    lateinit var adapter: ListNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        //Init view
        mService = Common.newsService
        dialog = SpotsDialog(this)

        swipe_to_refresh.setOnRefreshListener { loadNews(source,true) }

        diagonalLayout.setOnClickListener{
            //Implement soon
        }

        list_news.setHasFixedSize(true)
        list_news.layoutManager = LinearLayoutManager(this)

        if(intent != null){
            source = intent.getStringExtra("source").toString()
            if(!source.isEmpty()){
                loadNews(source, false)
            }

        }
    }

    private fun loadNews(source: String?, isRefreshed: Boolean) {
        if (isRefreshed){
            dialog.show()
            mService.getNewsFromSource(Common.getNewsAPI(source!!))
                .enqueue(object: Callback<News>{
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        dialog.dismiss()

                        //Get first article from hot news
                        Picasso.get()
                            .load(response!!.body()!!.articles!![0].urlToImage)
                            .into(top_image)

                        top_title.text = response!!.body()!!.articles!![0].title
                        top_author.text = response!!.body()!!.articles!![0].author

                        webHotUrl = response!!.body()!!.articles!![0].url

                        //Load all remain articles
                        val removeFirstItem = response!!.body()!!.articles
                        //Beacuse we get first item to hot new so we need to remove it
                        removeFirstItem!!.removeAt(0)

                        adapter = ListNewsAdapter(removeFirstItem!!, baseContext)
                        adapter.notifyDataSetChanged()
                        list_news.adapter = adapter
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
        else
        {
            swipe_to_refresh.isRefreshing = true
            mService.getNewsFromSource(Common.getNewsAPI(source!!))
                .enqueue(object: Callback<News>{
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        swipe_to_refresh.isRefreshing = false

                        //Get first article from hot news
                        Picasso.get()
                            .load(response!!.body()!!.articles!![0].urlToImage)
                            .into(top_image)

                        top_title.text = response!!.body()!!.articles!![0].title
                        top_author.text = response!!.body()!!.articles!![0].author

                        webHotUrl = response!!.body()!!.articles!![0].url

                        //Load all remain articles
                        val removeFirstItem = response!!.body()!!.articles
                        //Beacuse we get first item to hot new so we need to remove it
                        removeFirstItem!!.removeAt(0)

                        adapter = ListNewsAdapter(removeFirstItem!!, baseContext)
                        adapter.notifyDataSetChanged()
                        list_news.adapter = adapter
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }
}