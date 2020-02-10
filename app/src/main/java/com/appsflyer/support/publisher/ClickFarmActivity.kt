package com.appsflyer.support.publisher

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_click_farm.*
import java.text.SimpleDateFormat
import java.util.*

class ClickFarmActivity: Activity() {
    val urlTrackingLink = "https://fascode.com?app_id={appId}&pid={pid}&c={campaign}&advertising_id={GAID}"
    val urlOneLink = "https://fascode.com?url={url}&pid={pid}&c={campaign}&advertising_id={GAID}"
    val urlBrandedLink = "https://fascode.com?url={url}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_farm)
        editPackageName.setText(retrievePackageName())
        editSubDomain.setText(retrieveSubDomain())
        editPath.setText(retrievePath())
        editPid.setText(retrievePid())
        editBrandedLink.setText(retrieveBrandedLink())

        /**
         * Tracking Link
         */
        btnInternalAds.setOnClickListener {
            savePackageName()
            savePid()
            InAppAdsActivity.start(activity = this,
                    url = String.format("https://app.appsflyer.com/%s", editPackageName?.text?.toString() ?: ""),
                    params = mapOf("pid" to editPid?.text?.toString()))
        }
        btnInternalWebView.setOnClickListener {
            savePackageName()
            savePid()
            WebViewActivity.start(this, createUrlForTrackingLink())
        }
        btnBrowser.setOnClickListener {
            savePackageName()
            savePid()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(createUrlForTrackingLink())))
        }

        /**
         * OneLink
         */
        btnOneLinkInAppAds.setOnClickListener  {
            savePath()
            saveSubDomain()
            InAppAdsActivity.start(this, createOneLink() ?: "https://go.onelink.me")
        }

        btnOneLinkWebView.setOnClickListener {
            savePath()
            saveSubDomain()
            WebViewActivity.start(this,  createUrlForOneLink(createOneLink() ?: "https://go.onelink.me"))
        }

        btnOneLinkBrowser.setOnClickListener {
            savePath()
            saveSubDomain()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(createUrlForOneLink(createOneLink() ?: "https://go.onelink.me"))))
        }

        /**
         * Branded Links
         */
        btnBrandedLinkInAppAds.setOnClickListener  {
            editBrandedLink?.text?.toString()?.let {
                saveBrandedLink()
                InAppAdsActivity.start(this, it)
            } ?: Toast.makeText(this, "Please input your Branded Link url", Toast.LENGTH_LONG).show()
        }

        btnBrandedLinkWebView.setOnClickListener {
            editBrandedLink?.text?.toString()?.let {
                saveBrandedLink()
                WebViewActivity.start(this, createUrlForBrandedLink(it))
            } ?: Toast.makeText(this, "Please input your Branded Link url", Toast.LENGTH_LONG).show()
        }

        btnBrandedLinkBrowser.setOnClickListener {
            editBrandedLink?.text?.toString()?.let {
                saveBrandedLink()
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(createUrlForBrandedLink(it))))
            } ?: Toast.makeText(this, "Please input your Branded Link url", Toast.LENGTH_LONG).show()
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

    private fun retrieveSubDomain(): String? {
        val prefs = getSharedPreferences("link-parameters", 0)
        return prefs.getString("sub_domain", null)
    }

    private fun saveSubDomain() {
        val prefs = getSharedPreferences("link-parameters", 0)
        with(prefs) {
            edit().putString("sub_domain", editSubDomain?.text?.toString()).commit()
        }
    }

    private fun retrievePath(): String? {
        val prefs = getSharedPreferences("link-parameters", 0)
        return prefs.getString("path", null)
    }

    private fun savePath() {
        val prefs = getSharedPreferences("link-parameters", 0)
        with(prefs) {
            edit().putString("path", editPath?.text?.toString()).commit()
        }
    }

    private fun retrievePid(): String? {
        val prefs = getSharedPreferences("link-parameters", 0)
        return prefs.getString("pid", null)
    }

    private fun savePid() {
        val prefs = getSharedPreferences("link-parameters", 0)
        with(prefs) {
            edit().putString("pid", editPid?.text?.toString()).commit()
        }
    }
    private fun retrieveBrandedLink(): String? {
        val prefs = getSharedPreferences("link-parameters", 0)
        return prefs.getString("branded_link", null)
    }

    private fun saveBrandedLink() {
        val prefs = getSharedPreferences("link-parameters", 0)
        with(prefs) {
            edit().putString("branded_link", editBrandedLink?.text?.toString()).commit()
        }
    }
    private fun createOneLink(): String? {
        val oneLinkUrl = String.format("https://%s.onelink.me", editSubDomain?.text?.toString() ?: "go")
        return editPath?.text?.toString()?.takeIf { it.isNotEmpty() }?.let {
            "$oneLinkUrl/$it"
        }?: oneLinkUrl
    }

    private fun getDateString(): String {
        return SimpleDateFormat("yyyyMMdd-HHmmss").format(Date())
    }

    private fun createUrlForTrackingLink(): String {
        return urlTrackingLink
                .replace("{appId}", editPackageName.text?.toString() ?: "")
                .replace("{pid}", editPid.text?.toString() ?: "")
                .replace("{campaign}", getDateString())
                .replace("{GAID}", GaidHelper.gaid ?: "")
    }
    private fun createUrlForOneLink(url: String): String {
        return urlOneLink
                .replace("{url}", url)
                .replace("{pid}", editPid.text?.toString() ?: "")
                .replace("{campaign}", getDateString())
                .replace("{GAID}", GaidHelper.gaid ?: "")
    }
    private fun createUrlForBrandedLink(url: String): String {
        val safeUrl = if(!url.startsWith("http")) "https://$url" else url
        return urlBrandedLink
                .replace("{url}", safeUrl)
                .replace("{campaign}", getDateString())
                .replace("{GAID}", GaidHelper.gaid ?: "")
    }
}