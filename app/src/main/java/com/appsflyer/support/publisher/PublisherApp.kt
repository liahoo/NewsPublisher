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
        GaidHelper.retrieveGaid(this)
        ClickItems.init(this)
    }
}