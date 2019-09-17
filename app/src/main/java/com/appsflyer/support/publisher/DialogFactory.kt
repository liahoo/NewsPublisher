package com.appsflyer.support.publisher

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import java.util.*
import android.widget.Toast
import android.content.Context.CLIPBOARD_SERVICE



object DialogFactory {
    fun showLinkText(context: Context, linkText: String) {
        val adb = AlertDialog.Builder(context)
        val dialogUrl = LayoutInflater.from(context).inflate(R.layout.dialog_url, null, false)
        dialogUrl.findViewById<TextView>(R.id.textViewUrl).text = linkText
        adb.setView(dialogUrl)
        adb.setNeutralButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }.setPositiveButton(android.R.string.copy) { dialog, _ ->
            val manager: ClipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            manager.setPrimaryClip(ClipData.newPlainText("url", linkText))
            // Show a message:
            Toast.makeText(context, "Text copied",
                    Toast.LENGTH_SHORT)
                    .show()
        }.create().show()
    }
}