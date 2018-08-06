package com.appsflyer.support.publisher

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_news.*
import java.net.URL
import java.net.URLEncoder
import java.util.*

class NewsActivity : Activity(), ItemClickSupport.OnItemClickListener, ItemClickSupport.OnItemLongClickListener {
    override fun onItemLongClicked(recyclerView: RecyclerView, position: Int, v: View): Boolean {
        if(recyclerAdapter.getItemViewType(position)==0) return false
        recyclerAdapter.get(position)?.targetUrl?.let {
            AlertDialog.Builder(this)
                    .setMessage(it)
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
        }
        return true
    }

    override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
        if(recyclerAdapter.getItemViewType(position)==0) return
        recyclerAdapter.get(position)?.targetUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(it.replace("{epoch-time-adid}",(Date().time).toString() + "-" + PublisherApp.gaid))
            ))
        }
    }

    fun onLongClicked(item: ClickItem): Boolean {
        item.targetUrl?.let {
            AlertDialog.Builder(this)
                    .setMessage(it.replace("{epoch-time-adid}",(Date().time).toString() + "-" + PublisherApp.gaid))
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
        }
        return true
    }

    fun onClick(item: ClickItem) {
        item.targetUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        } ?: Toast.makeText(this, "Oops, I can't get the target URL for you!", Toast.LENGTH_LONG).show()
    }

    private lateinit var recyclerAdapter: CardRecyclerAdapter<ClickItem>

    lateinit var newsList: List<ClickItem>
    private fun initItems(){
        val baseUrl = "http://bit.do/es7Bt"
        newsList = listOf(
                ClickItem(0, getString(R.string.article_text_1), R.mipmap.skittlesstill),
                ClickItem(1, null, R.mipmap.mm, baseUrl + "?clickid={epoch-time-adid}"),
                ClickItem(0, getString(R.string.article_text_2), R.mipmap.salted_caramel),
                ClickItem(1, "Buy Skittles in an App", null, baseUrl + "?clickid={epoch-time-adid}"),
                ClickItem(0, getString(R.string.article_text_3), R.mipmap.ruby_chocolate_barry_callebaut),
                ClickItem(1, "Buy M&Ms in an App", null, baseUrl + "?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}"),
                ClickItem(0, getString(R.string.article_text_4), R.mipmap.jelly_belly_beer),
                ClickItem(1, "Download the Candy Shopping App Now", null, baseUrl + "?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}"),
                ClickItem(0, getString(R.string.article_text_5), R.mipmap.pg_18_3d_printer),
                ClickItem(1, null, R.mipmap.skittles, baseUrl + "?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        initItems()
        recyclerAdapter = CardRecyclerAdapter()
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter.addAll(newsList)
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this).setOnItemLongClickListener(this)
    }
}