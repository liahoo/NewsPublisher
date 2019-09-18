package com.appsflyer.support.publisher

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import java.util.concurrent.Executors
import android.content.pm.PackageManager




class PublisherApp: Application() {
    companion object {
        val TAG = PublisherApp::class.java.simpleName
        var gaid: String? = null
        var isLimitAdTrackingEnabled: Boolean = false
        fun isCandyAppInstalled(context: Context) =
                isPackageInstalled("com.candyapp.appsflyer", context.packageManager) ||
                        isPackageInstalled("com.internal.candyapp.appsflyer", context.packageManager)
        fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
            var found = true
            try {
                packageManager.getPackageInfo(packageName, 0)
            } catch (e: Exception) {
                found = false
            }
            return found
        }
    }
    override fun onCreate() {
        super.onCreate()
        retrieveGaid()
        ClickItems.init(this)
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