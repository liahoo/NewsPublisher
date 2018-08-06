package com.appsflyer.support.publisher

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.concurrent.Executors


class PublisherApp: Application() {
    companion object {
        val TAG = PublisherApp::class.java.simpleName
        var gaid: String? = null
        var isLimitAdTrackingEnabled: Boolean = false
    }
    override fun onCreate() {
        super.onCreate()
        retrieveGaid()
    }

    private fun retrieveGaid() {
        gaid = retrieveCachedGaid()
        Executors.newSingleThreadExecutor().execute {
            try {
                AdvertisingIdClient.getAdvertisingIdInfo(applicationContext)?.let {
                    Log.i(TAG, "Get GAID={${it.id}}, isLimitAdTrackingEnabled:${it.isLimitAdTrackingEnabled}")
                    gaid=it.id
                    isLimitAdTrackingEnabled = it.isLimitAdTrackingEnabled
                    restoreGaid(gaid)
                } ?: Log.e(TAG, "Get Advertising ID info failed!")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun restoreGaid(gaid: String?) {
        getSharedPreferences("AdInfo", Context.MODE_PRIVATE).edit().putString("GAID", gaid).apply()
    }
    private fun retrieveCachedGaid(): String?{
        return getSharedPreferences("AdInfo", Context.MODE_PRIVATE).getString("GAID", null)
    }
}