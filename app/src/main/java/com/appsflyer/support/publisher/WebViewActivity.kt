package com.appsflyer.support.publisher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WebViewActivity: Activity() {
    companion object {

        const val EXTRA_URL: String = "EXTRA_URL"
        fun start(activity: Activity, url: String? = null) {
            val intent = Intent(activity, WebViewActivity::class.java)
            url?.let { intent.putExtra(EXTRA_URL, it) }
            activity.startActivity(intent)
        }
    }
    private val defaultUrl = "https://fascode.com?pid=fascode_int&c=${SimpleDateFormat.getDateTimeInstance().format(Date())}" + GaidHelper.gaid?.let { "&advertising_id=$it" }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webView.webViewClient = myWebViewClient
        webView.settings.javaScriptEnabled = true
//        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.loadUrl(intent.getStringExtra(EXTRA_URL) ?: defaultUrl)
    }

    val myWebViewClient = object: WebViewClient() {
        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            Log.i("MyWebViewClient", "[shouldOverrideUrlLoading] url=\n$url")
            if (url.startsWith("intent://")) {
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                if (intent != null) {
                    startActivity(intent)
                    return true
                }
            }
            return false
        }
    }
}