package com.appsflyer.support.publisher

import android.os.Bundle
import android.app.Activity
import android.content.Intent

import kotlinx.android.synthetic.main.activity_news.*

class FlavorSplashActivity : SplashActivity() {
    override fun gotoMainActivity() {
        startActivity(Intent(this, PlayGameActivity::class.java))
        finish()
    }
}
