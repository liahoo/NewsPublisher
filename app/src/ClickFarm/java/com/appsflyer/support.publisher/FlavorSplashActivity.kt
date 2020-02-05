package com.appsflyer.support.publisher

import android.content.Intent


class FlavorSplashActivity : SplashActivity() {
    override fun gotoMainActivity() {
        startActivity(Intent(this, ClickFarmActivity::class.java))
        finish()
    }
}
