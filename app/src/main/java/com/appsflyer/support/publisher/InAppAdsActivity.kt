package com.appsflyer.support.publisher

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.candyapp.appsflyer.ui.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_in_app_ads.*
import org.json.JSONObject

class InAppAdsActivity : Activity(){
    companion object  {
        var EXTRA_URL: String = "EXTRA_URL"
        val EXTRA_PARAMS_JSON:String="PARAMS_JSON"
        fun start(activity: Activity, url: String) {
            val intent = Intent(activity, InAppAdsActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            activity.startActivity(intent)
        }
        fun start(activity: Activity, url: String, paramsJson: String) {
            val intent = Intent(activity, InAppAdsActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            intent.putExtra(EXTRA_PARAMS_JSON, paramsJson)
            activity.startActivity(intent)
        }
        fun start(activity: Activity, url: String, params: Map<String, Any?>) {
            val json = JSONObject()
            params.entries.forEach { e -> json.put(e.key, e.value) }
            start(activity, url, json)
        }
        fun start(activity: Activity, url: String, json: JSONObject) {
            start(activity, url, json.toString())
        }

    }

    val recyclerAdapter = RecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_app_ads)
        recycleView.adapter = RecyclerAdapter()
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        intent.getStringExtra(EXTRA_URL)?.let {
            inputUrl.setText(it)
        }
        intent.getStringExtra(EXTRA_PARAMS_JSON)?.let {
            try {
                JSONObject(it).let {json ->
                    json.keys().forEach { key ->
                        recyclerAdapter.add(key, json.getString(key))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        recycleView.adapter = recyclerAdapter
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btnCancel.setOnClickListener { finish() }
        btnCreate.setOnClickListener {
            inputUrl?.text?.toString()?.let { u ->
                var url = u
                recyclerAdapter.getAll().map { param -> param[0]?.let {
                    "$it=${param[1]}"
                }}.takeIf { it.isNotEmpty() }?.joinToString("&")?.let { ps ->
                    if(url.contains("?")) url += "&$ps"
                    else url += "?$ps"
                }
                DialogFactory.showAd(this@InAppAdsActivity, url)
            } ?: Toast.makeText(this@InAppAdsActivity, "URL no input",
                    Toast.LENGTH_SHORT)
                    .show()

        }
        btnAdd.setOnClickListener {
            recyclerAdapter.add()
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}
