package com.gsu.vibe.composeServices

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.core.net.toUri
import com.gsu.vibe.composeScreens.player.MediaPlayerComposeViewModel
import com.gsu.vibe.data.models.SoundModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// сервис для compose плеера
const val mediaPath = "/data/data/com.gsu.vibe/files/"

class PlayerService : Service() {

    lateinit var player: MediaPlayer
    var track = MutableStateFlow(SoundModel())
    private val binder = PlayerBinder()

    var trackTime = 1000 // фактическое время трека
    var duration = 10000L // время выставленное пользователем
    var timer: CountDownTimer? = null

    override fun onBind(intent: Intent): IBinder = binder

    inner class PlayerBinder : Binder() {
        fun getService() = this@PlayerService
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
        Log.d("MyLogs88", "play()")
        player.start()
        setCurrentTime(track.value.currentTrackTime.toLong())
    }

    fun pause() {
        player.pause()
        Log.d("MyLogs88", "pause()")
        timer?.cancel()
        timer = null
    }

    fun setCurrentTime(time: Long) {
        Log.d("MyLogs88", "setCurrentTime()")

        timer?.cancel()
        timer = null

        var currentTimeInTimer = time
        val seekTime = ((duration - (duration - time)) % (player.duration)).toInt()
        Log.d("MyLogs88", "seekTime = ${seekTime}")
        player.seekTo(seekTime)
        timer = object : CountDownTimer(duration - time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeInTimer += 1000
                CoroutineScope(Dispatchers.Main).launch {
                    track.value = track.value.copy(currentTrackTime = currentTimeInTimer.toInt())
                }
            }

            override fun onFinish() {
//                player.stop()
//                stopSelf()
                Log.d("MyLogs88", "stopSelf()")
            }
        }.start()

        Log.d("MyLogs88", "setCurrentTime() time = ${time}")
    }


}




