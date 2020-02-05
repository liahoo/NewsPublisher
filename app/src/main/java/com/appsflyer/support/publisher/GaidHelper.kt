package com.appsflyer.support.publisher

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.concurrent.Executors

object GaidHelper {
    var gaid: String? = null
    var isLimitAdTrackingEnabled: Boolean = false
    fun retrieveGaid(context: Context) {
        gaid = getCachedGaid(context)
        Executors.newSingleThreadExecutor().execute {
            try {
                AdvertisingIdClient.getAdvertisingIdInfo(context)?.let {
                    Log.i(PublisherApp.TAG, "Get GAID={${it.id}}, isLimitAdTrackingEnabled:${it.isLimitAdTrackingEnabled}")
                    gaid =it.id
                    isLimitAdTrackingEnabled = it.isLimitAdTrackingEnabled
                    saveGaid(context, gaid)
                } ?: Log.e(PublisherApp.TAG, "Get Advertising ID info failed!")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveGaid(context: Context, gaid: String?) {
        context.getSharedPreferences("AdInfo", Context.MODE_PRIVATE).edit().putString("GAID", gaid).apply()
    }
    fun getCachedGaid(context: Context): String?{
        return context.getSharedPreferences("AdInfo", Context.MODE_PRIVATE).getString("GAID", null)
    }

}