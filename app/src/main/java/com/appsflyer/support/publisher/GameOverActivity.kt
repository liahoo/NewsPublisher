package com.appsflyer.support.publisher

import android.app.Activity
import android.app.FragmentManager
import android.os.Bundle

class GameOverActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        fragmentManager.beginTransaction().add(AdDialog(), "ad_dialog").commit()
    }

}