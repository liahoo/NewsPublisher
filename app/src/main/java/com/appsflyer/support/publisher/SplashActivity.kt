package com.appsflyer.support.publisher

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler

abstract class SplashActivity: Activity() {
    private lateinit var timerHandler: Handler

    var count: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timerHandler = Handler(Handler.Callback({
            PublisherApp.gaid?.let {
                gotoMainActivity()
            } ?: kotlin.run {
                if(count>5){
                    alertNoGaid()
                }else {
                    timerHandler.sendEmptyMessageDelayed(count++, 200)
                }
            }
            return@Callback true
        }))
        timerHandler.sendEmptyMessageDelayed(count, 200)
    }

    open fun gotoMainActivity() {
        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }

    private fun alertNoGaid() {
        AlertDialog.Builder(this)
                .setMessage(R.string.can_not_read_gaid)
                .setPositiveButton(android.R.string.ok, { dialog, _ ->
                    dialog.dismiss()
                })
                .create().show()
    }
}