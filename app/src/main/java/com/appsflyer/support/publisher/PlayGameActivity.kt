package com.appsflyer.support.publisher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play_game.*

class PlayGameActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)
        btnPlay.setOnClickListener { startActivity(Intent(this, GameOverActivity::class.java)) }
        btnListAll.setOnClickListener { startActivity(Intent(this, AdsListActivity::class.java)) }
    }
}