package com.gsu.vibe.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationActionServices : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(Intent("TRAKS_TRAKS")
            .putExtra("actionName", intent?.action))
    }

}