package com.gsu.vibe.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.gsu.vibe.R
import com.gsu.vibe.V_ACTION_CLOSE
import com.gsu.vibe.V_CHANGE_PLAYER_STATE
import java.lang.Exception

//class NotificationActionServices(private val context: Context) {
//
//    private val channelId = "MusicServiceChannel"
//
//    init{
//        createNotificationChannel()
//    }
//
//    fun createNotification(
//        soundName: String = "null1",
//        soundType: String = "null1",
//        albumImage: Bitmap,
//        iconImage: Int,
//        //trackForServices: TrackForServiceModel?
//    ): Notification {
//
////        val intent = Intent(context, MainActivity::class.java)
////        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        try {
//            val playIntent = Intent(context, MediaPlayerService::class.java).apply {
//                action = V_CHANGE_PLAYER_STATE
//            }
//            val playPendingIntent =
//                PendingIntent.getService(context, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)
//
//            val closeIntent =
//                Intent(context, MediaPlayerService::class.java).apply { action = V_ACTION_CLOSE }
//            val pendingCloseIntent =
//                PendingIntent.getService(context, 0, closeIntent, PendingIntent.FLAG_IMMUTABLE)
//
//            return NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.drawable.ic_meditation_bar_color)
//                .setLargeIcon(albumImage) // Устанавливаем обложку альбома
//
//                .setContentTitle(soundName)
//                .setContentText(soundType)
//                .setShowWhen(false) // не показыать время появления уведомления
//                .addAction(iconImage, V_CHANGE_PLAYER_STATE, playPendingIntent)
//                .addAction(
//                    com.google.android.material.R.drawable.ic_m3_chip_close,
//                    V_ACTION_CLOSE,
//                    pendingCloseIntent
//                )
//               // .setContentIntent(pendingIntent) // открытие приложения при нажатии на уведомление
//
//                .setStyle(androidx.media.app.NotificationCompat
//                    .MediaStyle()
//                    .setShowActionsInCompactView(0, 1) // показ кнопок в свернутом режиме
//                )
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setOnlyAlertOnce(true)
//                .setOngoing(true)
//                .setColor(ContextCompat.getColor(context, R.color.teal_7001)) // Устанавливаем цвет
//                .setColorized(true) // Применяем цвет
//                .setSound(null)
//                .build()
//        }
//        catch (e: Exception){
//            Log.d("MyLogs4431", "createNotification e = ${e}")
//            throw e
//        }
//    }
//
//
//    private fun createNotificationChannel() {
//        val name = "Music Service"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel(channelId, name, importance)
//        val audioAttributes = AudioAttributes.Builder()  // для отключения звука при создании уведомления
//            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//            .build()
//        channel.setSound(null, audioAttributes)
//        val notificationManager: NotificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//    }
//
//
//}
//
//
//class YourButton1Receiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        // Обработка нажатия на кнопку 1
//    }
//}
//
//class YourButton2Receiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        // Обработка нажатия на кнопку 2
//    }
//}
