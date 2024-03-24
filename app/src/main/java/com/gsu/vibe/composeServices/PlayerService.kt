package com.gsu.vibe.composeServices

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.telecom.ConnectionService
import androidx.core.net.toUri
import java.net.URI

class PlayerService: Service() {

    private val mediaPath = "/data/data/com.gsu.vibe/files/"


    private val binder = PlayerBinder()
    private lateinit var player: MediaPlayer

//    private val connection = object  : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            val binder = service as PlayerService.binder
//            musicService = binder.getService()
//            isBound = true
//            musicService.playMusic() // Автоматически начать воспроизведение
//            //        }
//
//            override fun onServiceDisconnected(name: ComponentName?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
    }


    inner class PlayerBinder: Binder(){
        fun getService() = this@PlayerService
    }

    fun setTrackUri(trackURI: String) {
        player = MediaPlayer.create(this, trackURI.toUri())
    }

    fun play(){
        player.start()
    }


}