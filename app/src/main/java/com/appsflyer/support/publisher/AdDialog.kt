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
import java.net.URLEncoder
import java.util.*

class AdDialog: DialogFragment() {
    val TAG = AdDialog::class.java.simpleName
    val baseUrl = "https://app.appsflyer.com/com.candyapp.appsflyer?pid=masterclass&c=publisherapps&af_adset=dlnow&af_click_lookback=1h"
    val impBaseUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=masterclass&c=publisherapps&af_adset=dlnow&af_viewthrough_lookback=1h"
    val append = "&af_r=${URLEncoder.encode(BuildConfig.DOWNLOAD_URL, "UTF-8")}&advertising_id=${PublisherApp.gaid}"
    val itemList = listOf(
            ClickItem(1,"Download the Candy Shopping App Now", null,
                    targetUrl = "${baseUrl}&af_ad=General&af_adset_id=t1&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}",
                    impressUrl = "${impBaseUrl}&af_ad=General&af_adset_id=t1&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}"),
            ClickItem(1, "Buy M&Ms in an App", null,
                    targetUrl = "${baseUrl}&af_ad=MM&af_adset_id=t2&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}",
                    impressUrl = "${impBaseUrl}&af_ad=MM&af_adset_id=t2&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}"),
            ClickItem(1, "Buy Skittles in an App", null,
                    targetUrl = "${baseUrl}&af_ad=Skittles&af_adset_id=t3&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}",
                    impressUrl = "${impBaseUrl}&af_ad=Skittles&af_adset_id=t3&af_siteid=ABC&af_cost_currency=USD&af_cost_value=1${append}"),
            ClickItem(1, null, R.mipmap.mm,
                    targetUrl = "${baseUrl}&af_ad=MM&af_adset_id=iMM&af_siteid=ABC&af_cost_currency=USD&af_cost_value=2${append}",
                    impressUrl = "${impBaseUrl}&af_ad=MM&af_adset_id=iMM&af_siteid=ABC&af_cost_currency=USD&af_cost_value=2${append}"),
            ClickItem(1, null, R.mipmap.skittles,
                    targetUrl = "${baseUrl}&af_ad=Skittles&af_adset_id=iSK&af_siteid=ABC&af_cost_currency=USD&af_cost_value=2${append}",
                    impressUrl = "${impBaseUrl}&af_ad=Skittles&af_adset_id=iSK&af_siteid=ABC&af_cost_currency=USD&af_cost_value=2${append}")
    )

    private lateinit var ad: ClickItem
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val itemIndex = Random().nextInt(itemList.size)
        Log.d(TAG, "[onCreateView] item index: ${itemIndex}")
        ad =  itemList.get(itemIndex)
        val v = inflater!!.inflate(R.layout.dialog_ad, container, false)
        val tvTitle: TextView? = v.findViewById(R.id.textAd)
        tvTitle?.let {
            it.setCompoundDrawablesWithIntrinsicBounds(0,0,0, ad.image ?: 0)
            it.text = ad.text
            it.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ad.targetUrl))) }
        }
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnClose: Button? = view?.findViewById(R.id.btnClose)
        btnClose?.setOnClickListener { dismiss(); activity.finish(); }
        sendImpression()
    }

    private fun sendImpression() {
        Log.d(TAG, "[sendImpression]")
        Log.i(TAG,"Request:\n ${ad.impressUrl}")
        val queue = Volley.newRequestQueue(activity)
        val stringRequest = StringRequest(Request.Method.GET, ad.impressUrl,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    Log.i(TAG,"Response: \n ${response}")
                },
                Response.ErrorListener { Log.i(TAG,"Response Error!!") })
        stringRequest.tag = ad.text
        queue.add(stringRequest)
    }
}