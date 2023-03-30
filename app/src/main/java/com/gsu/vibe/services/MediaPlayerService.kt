package com.gsu.vibe.services

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.media.session.PlaybackState.ACTION_PAUSE
import android.media.session.PlaybackState.ACTION_PLAY
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.gsu.vibe.R

class MediaPlayerService : Service(), MediaPlayer.OnCompletionListener {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnCompletionListener(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")
        when (action) {
            "play" -> {
                val uri = intent.getStringExtra("uri")
                mediaPlayer.apply {
                    reset()
                    setDataSource(applicationContext, Uri.parse(uri))
                    prepare()
                    start()
                }
            }
            "pause" -> {
                mediaPlayer.pause()
            }
            "stop" -> {
                mediaPlayer.stop()
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCompletion(mp: MediaPlayer?) {
        stopSelf()
    }
}