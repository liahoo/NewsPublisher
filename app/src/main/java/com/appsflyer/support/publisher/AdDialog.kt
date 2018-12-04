package com.appsflyer.support.publisher

import android.app.AlertDialog
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
    val itemList = listOf(
            ClickItem(1, null, R.mipmap.mm,
                    targetUrl = "http://bit.do/eCfwa?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}",
                    impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=MM&af_adset_id=iMM&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=22"),
            ClickItem(1, "Buy Skittles in an App", null,
                    targetUrl = "http://bit.do/eCfwd?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}",
                    impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=Skittles&af_adset_id=t3&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=15&af_fingerprint_attribution=false"),
            ClickItem(1, "Buy M&Ms in an App", null,
                    targetUrl = "http://bit.do/eCfwh?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}",
                    impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=MM&af_adset_id=t2&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12"),
            ClickItem(1,"Download the Candy Shopping App Now", null,
                    targetUrl = "http://bit.do/eCfwj?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}",
                    impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=General&af_adset_id=t1&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=21"),
            ClickItem(1, null, R.mipmap.skittles,
                    targetUrl = "http://bit.do/eCfwm?advertising_id=${PublisherApp.gaid}&clickid={epoch-time-adid}",
                    impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=Skittles&af_adset_id=iSK&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=29")
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
            it.setOnClickListener {
                ad.targetUrl?.replace("{epoch-time-adid}", (Date().time).toString() + "-" + PublisherApp.gaid)?.let {
                    Log.i(TAG,"click on url: $it")
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                }
            }
            it.setOnLongClickListener {
                AlertDialog.Builder(activity)
                        .setMessage(ad.targetUrl?.replace("{epoch-time-adid}",(Date().time).toString() + "-" + PublisherApp.gaid))
                        .setPositiveButton(android.R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show(); true
            }
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