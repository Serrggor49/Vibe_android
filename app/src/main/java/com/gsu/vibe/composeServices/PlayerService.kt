package com.gsu.vibe.composeServices

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.core.net.toUri

class PlayerService: Service() {

    private val mediaPath = "/data/data/com.gsu.vibe/files/"

    private val binder = PlayerBinder()
    lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class PlayerBinder: Binder(){
        fun getService() = this@PlayerService
    }

    fun setTrackUri(trackName: String) {
        player = MediaPlayer.create(this, ("$mediaPath$trackName").toUri())
    }

    fun play() = player.start()
    fun pause() = player.pause()
}