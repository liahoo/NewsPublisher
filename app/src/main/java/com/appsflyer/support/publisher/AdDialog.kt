package com.appsflyer.support.publisher

import android.app.DialogFragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class AdDialog: DialogFragment() {
    val TAG = AdDialog::class.java.simpleName
    private lateinit var ad: ClickItem
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val itemList = if(PublisherApp.isCandyAppInstalled(inflater.context)) ClickItems.gameAdsRetargeting else ClickItems.gameAds
        val itemIndex = Random().nextInt(itemList.size)
        Log.d(TAG, "[onCreateView] item index: ${itemIndex}")
        ad =  itemList.get(itemIndex)
        val v = inflater.inflate(R.layout.dialog_ad, container, false)
        val tvTitle: TextView? = v.findViewById(R.id.textAd)
        tvTitle?.let {
            it.setCompoundDrawablesWithIntrinsicBounds(0,0,0, ad.image ?: 0)
            it.text = ad.text
            it.setOnClickListener {
                ad.targetUrl?.replace("{epoch-time-adid}", (Date().time).toString() + "-" + PublisherApp.gaid)?.let {
                    Log.i(TAG,"click on url: $it")
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                }
            }
            it.setOnLongClickListener {
                ad.targetUrl?.replace("{epoch-time-adid}",(Date().time).toString() + "-" + PublisherApp.gaid)?.let {
                    DialogFactory.showLinkText(inflater.context, it)
                    true
                } ?: false
            }
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnClose: Button? = view.findViewById(R.id.btnClose)
        btnClose?.setOnClickListener { dismiss(); activity.finish() }
        sendImpression()
    }

    private fun sendImpression() {
        Log.d(TAG, "[sendImpression]")
        val url = ad.impressUrl?.replace("{epoch-time-adid}", (Date().time).toString() + "-" + PublisherApp.gaid)

        Log.i(TAG,"Request:\n $url")
        val queue = Volley.newRequestQueue(activity)
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    Log.i(TAG,"Response: ${response}")
                },
                Response.ErrorListener { Log.i(TAG,"Response Error!!") })
        stringRequest.tag = ad.text
        queue.add(stringRequest)
    }


}