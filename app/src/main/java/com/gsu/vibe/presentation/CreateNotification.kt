package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.session.MediaSession
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.gsu.vibe.R
import com.gsu.vibe.services.NotificationActionServices

class CreateNotification {

    val CHANNEL_ID = "channel1"
    val ACTION_PREVIOUS = "actionprevious"
    val ACTION_PLAY = "action_play"
    val ACTION_PAUSE = "action_pause"
    val ACTION_NEXT = "action_next"

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("UnspecifiedImmutableFlag")
    fun createNotification(context: Context, pos: Int, nameType: String, trackName: String, backgroundImage: Int){

        val icon = BitmapFactory.decodeResource(context.resources, backgroundImage)  // картинка с правой стороны уведомления (например, для обложки трека)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        val mediaSessionCompat = MediaSessionCompat(context, "tag")

        var pendingIntentPlay: PendingIntent? = null
        var drw_play: Int? = null
        if (pos == 0){
            pendingIntentPlay = null
            //drw_play = 0
            drw_play = R.drawable.ic_focus_bar
        }
        else if(pos == 1){
            val intentPlay = Intent(context, NotificationActionServices::class.java).setAction(ACTION_PLAY)
            //pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT)
            pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_MUTABLE)
            drw_play = R.drawable.ic_pause_button
        }

        else{
            val intentPlay = Intent(context, NotificationActionServices::class.java).setAction(ACTION_PAUSE)
            //pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT)
            pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_MUTABLE)
            drw_play = R.drawable.ic_play_button
        }


        val style = androidx.media.app.NotificationCompat.MediaStyle()


        val mediaStyle = Notification.MediaStyle()
            .setMediaSession(mediaSessionCompat.sessionToken.token as MediaSession.Token?)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_focus_bar)
            .setContentTitle(trackName)
            .setContentText(nameType)
            //    .setLargeIcon(icon)  // если убрать, то не будет разворачиваться. Но не будет и обложки
            .setOnlyAlertOnce(true)
            .setShowWhen(false)
            //.addAction(drw_previous, "Previous", pendingIntentPrevious)
            .addAction(drw_play, "Play", pendingIntentPlay)

            //                .addAction(drw_next, "Next", pendingIntentNext)
//                .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
//                    .setShowActionsInCompactView(0, 1, 2)
//                    .setMediaSession(mediaSessionCompat.sessionToken))

            .setStyle(style.setShowActionsInCompactView(0))  // изначальный
         //   .setStyle(style.setMediaSession(mediaSessionCompat.sessionToken))  // ничего не показывает
            .build()

        notificationManagerCompat.notify(1, notification)

    }


}