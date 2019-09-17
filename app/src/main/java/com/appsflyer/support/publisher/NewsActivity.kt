package com.appsflyer.support.publisher

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_news.*
import java.util.*

class NewsActivity : Activity(), ItemClickSupport.OnItemClickListener, ItemClickSupport.OnItemLongClickListener {
    override fun onItemLongClicked(recyclerView: RecyclerView, position: Int, v: View): Boolean {
        if(recyclerAdapter.getItemViewType(position)==0) return false
        recyclerAdapter.get(position)?.targetUrl?.let {
            DialogFactory.showLinkText(this@NewsActivity, it.replace("{epoch-time-adid}",(Date().time).toString() + "-" + PublisherApp.gaid))
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

    private lateinit var recyclerAdapter: CardRecyclerAdapter<ClickItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        recyclerAdapter = CardRecyclerAdapter()
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter.addAll(ClickItems.news)
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(this).setOnItemLongClickListener(this)
    }
}
