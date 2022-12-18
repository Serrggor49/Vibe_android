package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.gsu.vibe.R
import com.gsu.vibe.services.NotificationActionServices

class CreateNotification {

    val CHANNEL_ID = "channel1"
    val ACTION_PREVIOUS = "actionprevious"
    val ACTION_PLAY = "action_play"
    val ACTION_NEXT = "action_next"

    @SuppressLint("UnspecifiedImmutableFlag")
    fun createNotification(context: Context, pos: Int){

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.focus_01_1f)  // картинка с правой стороны уведомления (например, для обложки трека)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, "tag")

            Log.d("MyLogs333", pos.toString())

            var pendingIntentPrevious: PendingIntent? = null
            var drw_previous: Int? = null
 //           if (pos == 0){
                pendingIntentPrevious = null
                drw_previous = 0
 //           }
 //           else{
                val intentPrevious = Intent(context, NotificationActionServices::class.java).setAction(ACTION_PREVIOUS)
                pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT)
                //drw_previous = R.drawable.ic_baseline_play_circle_filled_24
                drw_previous = R.drawable.ic_baseline_play_circle_filled_24
 //           }

            ////////////////////////////////////////////
            var pendingIntentNext: PendingIntent? = null
            var drw_next: Int? = null
            if (pos == 0){
                pendingIntentNext = null
                drw_next = 0
            }
 //           else{
                val intentNext = Intent(context, NotificationActionServices::class.java).setAction(ACTION_NEXT)
                pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT)
                drw_next = R.drawable.ic_baseline_play_circle_filled_24
 //           }

            ////////////////////////////////////////////
            var pendingIntentPlay: PendingIntent? = null
            var drw_play: Int? = null
//            if (pos != -131){
//                pendingIntentPlay = null
//                drw_play = 0
//                drw_play = R.drawable.ic_play_button
//
//            }
       //     else{
                val intentPlay = Intent(context, NotificationActionServices::class.java).setAction(ACTION_PLAY)
                pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT)
                drw_play = R.drawable.ic_play_button
         //   }


            val style = androidx.media.app.NotificationCompat.MediaStyle()

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_focus_bar)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true)
                .setShowWhen(false)
                //.addAction(drw_previous, "Previous", pendingIntentPrevious)
                .addAction(drw_play, "Play", pendingIntentPlay)
                //                .addAction(drw_next, "Next", pendingIntentNext)
//                .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
//                    .setShowActionsInCompactView(0, 1, 2)
//                    .setMediaSession(mediaSessionCompat.sessionToken))
                .setStyle(style.setShowActionsInCompactView(0))
                .build()

            notificationManagerCompat.notify(1, notification)

        }

    }


}