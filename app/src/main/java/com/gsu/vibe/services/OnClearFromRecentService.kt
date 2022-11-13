package com.gsu.vibe.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.security.Provider

class OnClearFromRecentService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopSelf()
    }
}