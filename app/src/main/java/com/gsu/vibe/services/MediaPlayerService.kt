package com.gsu.vibe.services

import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.gsu.vibe.R
import com.gsu.vibe.V_ACTION_CHANGE_TIME
import com.gsu.vibe.V_ACTION_CLOSE
import com.gsu.vibe.V_ACTION_PAUSE_FOR_FRAGMENT
import com.gsu.vibe.V_ACTION_PLAY_FOR_FRAGMENT
import com.gsu.vibe.V_CHANGE_PLAYER_STATE
import com.gsu.vibe.data.models.TrackForServiceModel
import com.gsu.vibe.timerKey
import com.gsu.vibe.trackForServiceModelKey

//class MediaPlayerService : Service(), MediaPlayer.OnCompletionListener {
//
//    lateinit var timer: CountDownTimer
//    //private lateinit var mediaPlayer: MediaPlayer
//    private lateinit var mediaPlayerArray: Array<MediaPlayer?>
//    var timeToEnd = -1
//
//    var currentVolume = 1.0f
//    var duration = -1
//
//    private val notificationId = 101
//    private var isPlaying = false
//    private var soundName = "null2"
//    private var soundType = ""
//    private var soundImage = 0
//
//    lateinit var notificationActionServices: NotificationActionServices
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.d("MyLogs89", "onStartCommand MediaPlayerService")
//
//        val trackForServices =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent!!.getParcelableExtra(
//                trackForServiceModelKey,
//                TrackForServiceModel::class.java
//            ) else intent!!.getParcelableExtra(trackForServiceModelKey)
//
//        if (!::notificationActionServices.isInitialized) {
//            notificationActionServices = NotificationActionServices(context = this)
//        }
//
//        if (!::mediaPlayerArray.isInitialized) {
//            val soundPaths = (trackForServices?.soundPaths)
//            if (soundPaths != null) {
//                mediaPlayerArray = Array(trackForServices.soundPaths.size){null}
//                for ((index, path) in soundPaths.withIndex()){
//                    mediaPlayerArray[index] = MediaPlayer.create(this, Uri.parse(path))
//                    mediaPlayerArray[index]?.setVolume(trackForServices.soundVolume[index], trackForServices.soundVolume[index])
//                    mediaPlayerArray[index]?.isLooping = true
//                }
//                duration = trackForServices.duration
//            }
//        }
//
//
//
////        if (intent.getIntExtra(soundNameKey, 0) != 0) { // в случае, когда нажимаем кнопку в баре, передается только action
////            soundName = getString(trackForServices!!.soundName)
////            soundType = getString(trackForServices.soundType)
////            soundImage = trackForServices.soundImage
////        }
//
//        if (trackForServices != null) { // в случае, когда нажимаем кнопку в баре, передается только action
//            soundName = getString(trackForServices.soundName)
//            if (trackForServices.soundPaths.size==1) soundType = getString(trackForServices.soundType) // иначе у нас миксер
//            soundImage = trackForServices.soundImage
//        }
//
//        val bitmap = BitmapFactory.decodeResource(resources, soundImage)
//        val icon = if (!isPlaying) R.drawable.ic_baseline_pause_circle_filled_24 else R.drawable.ic_play_buuton_3
//
//        trackForServices?.timer.let {
//
//            Log.d("MyLogs3321", "trackForServices = ${it}")
//
//            if (it != null) {
//                if (it > 0) {
//                    timeToEnd = it
//                }
//            }
//            if (::mediaPlayerArray.isInitialized) {
//                initTimer()
//            }
//        }
//
//        when (intent.action) {
//            V_CHANGE_PLAYER_STATE -> {
//                if (isPlaying) {
//
//                    //mediaPlayer.pause()
//                    mediaPlayerArray.forEach { it?.pause() }
//                    isPlaying = false
//                    timer.cancel()
//                } else {
//                    mediaPlayerArray.forEach { it?.start() }
//                    initTimer()
//                    isPlaying = true
//                }
//                startForeground(
//                    notificationId,
//                    notificationActionServices.createNotification(
//                        soundName = soundName,
//                        soundType = soundType,
//                        albumImage = bitmap,
//                        iconImage = icon,
//                      // trackForServices = null
//                    )
//                )
//
//                val intentForFragment = Intent().apply {
//                    putExtra(timerKey, timeToEnd)
//                }
//
//                if (isPlaying) {
//                    intentForFragment.action = V_ACTION_PAUSE_FOR_FRAGMENT
//                } else {
//                    intentForFragment.action = V_ACTION_PLAY_FOR_FRAGMENT
//                }
//                sendBroadcast(intentForFragment)
//            }
//
//            V_ACTION_CLOSE -> {
//                Log.d("MyLogs88", "service V_ACTION_CLOSE")
//                if (::mediaPlayerArray.isInitialized) {
//                    mediaPlayerArray.forEach { it?.release() }
//                }
//                if (::timer.isInitialized) {
//                    timer.cancel()
//                }
//
//                val intentForFragment = Intent().apply { putExtra(timerKey, timeToEnd) }
//                intentForFragment.action = V_ACTION_PLAY_FOR_FRAGMENT
//                sendBroadcast(intentForFragment)
//
//                stopSelf()
//            }
//
//            V_ACTION_CHANGE_TIME ->{
//
//                val icon2 = if (isPlaying) R.drawable.ic_baseline_pause_circle_filled_24 else R.drawable.ic_play_buuton_3
//
//                if (!isPlaying) {
//                    mediaPlayerArray.forEach { it?.pause() }
//                    //isPlaying = false
//                    timer.cancel()
//                } else {
//                    mediaPlayerArray.forEach { it?.start() }
//                    initTimer()
//                    //isPlaying = true
//                }
//                startForeground(
//                    notificationId,
//                    notificationActionServices.createNotification(
//                        soundName = soundName,
//                        soundType = soundType,
//                        albumImage = bitmap,
//                        iconImage = icon2,
//                        // trackForServices = null
//                    )
//                )
//
//                val intentForFragment = Intent().apply {
//                    putExtra(timerKey, timeToEnd)
//                }
//
//                if (isPlaying) {
//                    intentForFragment.action = V_ACTION_PAUSE_FOR_FRAGMENT
//                } else {
//                    intentForFragment.action = V_ACTION_PLAY_FOR_FRAGMENT
//                }
//                sendBroadcast(intentForFragment)
//            }
//
//        }
//
//        return START_STICKY
//    }
//
//    fun initTimer() {
//
//        if (::timer.isInitialized) {
//            timer.cancel()
//        }
//
//        if (duration > 0) {
//            val seekTime = ((duration - timeToEnd) % (mediaPlayerArray[0]!!.duration))
//            mediaPlayerArray.forEach { it?.seekTo(seekTime) }
//
//        } else {
//            mediaPlayerArray.forEach { it?.seekTo(0) }
//        }
//
//        timer = object : CountDownTimer(timeToEnd.toLong(), 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                Log.d("MyLogs3323", "timeToEnd.toLong() service = ${timeToEnd.toLong()}")
//
//                timeToEnd -= 1000
//                if (millisUntilFinished < 15000) {
//                    currentVolume -= 0.065f
//                    if (currentVolume < 0) currentVolume = 0f
//                    mediaPlayerArray.forEach { it?.setVolume(currentVolume, currentVolume) }
//                    //mediaPlayer.setVolume(currentVolume, currentVolume)
//                }
//            }
//
//            override fun onFinish() {
//                stopSelf()
//            }
//        }.start()
//    }
//
//    override fun onBind(intent: Intent?): IBinder? = null
//    override fun onCompletion(mp: MediaPlayer?) = stopSelf()
//
//}


