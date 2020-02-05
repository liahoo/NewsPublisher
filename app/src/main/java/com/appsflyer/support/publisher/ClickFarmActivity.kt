package com.appsflyer.support.publisher

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_click_farm.*
import java.text.SimpleDateFormat
import java.util.*

class ClickFarmActivity: Activity() {
    val trackingLink = "https://fascode.com?app_id={appId}&pid=fascode_int&c={campaign}&&advertising_id={GAID}"
    val trackingLinkFascode = "https://fascode.com?app_id={appId}&c={campaign}&&advertising_id={GAID}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_farm)
        editPackageName.setText(retrievePackageName())
        btnInternal.setOnClickListener {
            savePackageName()
            WebViewActivity.start(this, createTrackingLink("fascode_int"))
        }
        btnInternalFascode.setOnClickListener {
            savePackageName()
            WebViewActivity.start(this,  createTrackingLink())
        }
        btnExternal.setOnClickListener {
            savePackageName()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(createTrackingLink())))
        }
        btnExternalFascode.setOnClickListener {
            savePackageName()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(createTrackingLink("fascode_int"))))
        }
    }

    private fun retrievePackageName(): String? {
        val prefs = getSharedPreferences("link-parameters", 0)
        return prefs.getString("app_id", null)
    }

    private fun savePackageName() {
        val prefs = getSharedPreferences("link-parameters", 0)
        with(prefs) {
            edit().putString("app_id", editPackageName?.text?.toString()).commit()
        }
    }

    private fun getDateString(): String {
        return SimpleDateFormat("yyyyMMdd").format(Date())
    }

    private fun createTrackingLink(pid: String = ""): String {
        return trackingLink
                .replace("{appId}", editPackageName.text?.toString() ?: "")
                .replace("{pid}", pid)
                .replace("{campaign}", getDateString())
                .replace("{GAID}", GaidHelper.gaid ?: "")

    }
}