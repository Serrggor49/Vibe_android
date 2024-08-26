package com.gsu.vibe.composeServices

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.gsu.vibe.V_ACTION_CLOSE
import com.gsu.vibe.V_CHANGE_PLAYER_STATE

// создание notification для compose плеера

class PlayerNotificationManager(){

    private val channelId = "MusicServiceChannel"

    fun newNotification(context: Context, title: String, subtitle: String, largeIcon: Bitmap, iconLogo: Int, playOrPauseButtonIcon: Int, ): Notification {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Player Notification",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        val playPausePendingIntent = PendingIntent.getService(
            context,
            0,
            Intent(context, PlayerService::class.java).apply {
                action = V_CHANGE_PLAYER_STATE
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Настраиваем уведомление
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(iconLogo)
            .setContentTitle(title)
            .setContentText(subtitle)
            .setLargeIcon(largeIcon)  // Обложка трека
            .setOngoing(true)  // запрет на удаление уведомления
            .addAction(
                playOrPauseButtonIcon,
                if (true) "Pause" else "Play",
                playPausePendingIntent
            )
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0)
            )
            .build()

        // Запускаем уведомление
        return notification
    }

}




