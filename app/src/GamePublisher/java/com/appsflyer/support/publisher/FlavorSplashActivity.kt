package com.appsflyer.support.publisher

import android.content.Intent


class FlavorSplashActivity : SplashActivity() {
    override fun gotoMainActivity() {
        startActivity(Intent(this, PlayGameActivity::class.java))
        finish()
    }
}
