package com.gsu.vibe.composeServices


import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.core.net.toUri
import com.gsu.vibe.R
import com.gsu.vibe.V_CHANGE_PLAYER_STATE
import com.gsu.vibe.data.models.SoundModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// сервис для compose плеера
const val mediaPath = "/data/data/com.gsu.vibe/files/"

class PlayerService : Service() {

    private val notificationId = 101

    lateinit var player: MediaPlayer
    var track = MutableStateFlow(SoundModel())
    private val binder = PlayerBinder()
    var currentVolume = 1.0f

    var trackTime = 1000 // фактическое время трека
    var duration = 10000L // время выставленное пользователем
    var timer: CountDownTimer? = null

    override fun onBind(intent: Intent): IBinder = binder

    val playerNotificationManager = PlayerNotificationManager()

    inner class PlayerBinder : Binder() {
        fun getService() = this@PlayerService
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action == V_CHANGE_PLAYER_STATE) {
            if (player.isPlaying) {
                player.pause()
                track.value.isPlaying = false
            } else {
                player.start()
                track.value.isPlaying = true
            }
            updateNotificationPlayer()
        }
        return START_STICKY
    }

    fun setCurrentTrack(currentTrack: MutableStateFlow<SoundModel>) {
        track = currentTrack
        player = MediaPlayer.create(this, ("$mediaPath${currentTrack.value.name}").toUri())
        player.isLooping = true
        trackTime = player.duration
        duration = currentTrack.value.durationInMs
        setCurrentTime(track.value.currentTrackTime.toLong())
        Log.d("MyLogs88", "current = ${currentTrack.value.name}")
    }

    fun play() {
        setCurrentTime(track.value.currentTrackTime.toLong())
        player.start()
        updateNotificationPlayer()
    }

    fun pause() {

        player.pause()
        timer?.cancel()
        timer = null
        updateNotificationPlayer()

    }


    fun setCurrentTime(time: Long) {
        timer?.cancel()
        timer = null

        var currentTimeInTimer = time
        val seekTime = ((duration - (duration - time)) % (player.duration)).toInt()
        player.seekTo(seekTime)
        timer = object : CountDownTimer(duration - time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeInTimer += 1000

                if (millisUntilFinished < 15000) {
                    currentVolume -= 0.065f
                    if (currentVolume < 0) currentVolume = 0f
                    player.setVolume(currentVolume, currentVolume)
                }
                CoroutineScope(Dispatchers.Main).launch {
                    track.value = track.value.copy(currentTrackTime = currentTimeInTimer.toInt())
                }
            }

            override fun onFinish() {
                player.release()
                timer?.cancel()
                timer = null
                restartTrack()
                //onDestroy()
            }
        }.start()

        Log.d("MyLogs88", "setCurrentTime() time = ${time}")
    }

    fun updateNotificationPlayer() {
        startForeground(
            notificationId,
            playerNotificationManager.newNotification(
                context = this,
                title = getString(track.value.title),
                subtitle = getString(track.value.subtitle),
                largeIcon = BitmapFactory.decodeResource(resources, track.value.preview),
                iconLogo = R.drawable.ic_meditation_bar_color,
                playOrPauseButtonIcon = if (player.isPlaying) R.drawable.ic_pause_button else R.drawable.ic_play_button
            )
        )
    }


    override fun onDestroy() {
        Log.d("MyLogs89", "onDestroy()")
        player.release()
        timer?.cancel()
        timer = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            stopForeground(Service.STOP_FOREGROUND_REMOVE) // Удаляет уведомление и останавливает передний план
        } else {
            stopForeground(true) // Старый метод для обратной совместимости
        }

        super.onDestroy()
    }

    // после завершение трека завершается сервис, трек становится в нулевое состояние
    fun restartTrack() {

        track.value = track.value.copy(
            isPlaying = false, currentTrackTime = 0
        )

//        track.value.apply {
//            isPlaying = false
//            currentTrackTime = 1
//
//        }
    }
}









