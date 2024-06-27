package com.gsu.vibe.composeServices

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.core.net.toUri
import com.gsu.vibe.data.models.SoundModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


// сервис для compose плеера
class PlayerService : Service() {

    lateinit var player: MediaPlayer
    private val mediaPath = "/data/data/com.gsu.vibe/files/"
    private var track: SoundModel? = null
    private val binder = PlayerBinder()

    val currentTimeStateFlow: MutableStateFlow<Long> = MutableStateFlow(0) // время которое стоит на slideBar
    var trackTime = 1000 // фактическое время трека
    var duration = 10000L // время выставленное пользователем
    var timer: CountDownTimer? = null


    override fun onBind(intent: Intent): IBinder = binder

    inner class PlayerBinder : Binder() {
        fun getService() = this@PlayerService
    }

    fun setCurrentTrack(currentTrack: SoundModel) {
        track = currentTrack
        player = MediaPlayer.create(this, ("$mediaPath${track?.name}").toUri())
        player.isLooping = true
        trackTime = player.duration
        duration = currentTrack.durationInMs
        setCurrentTime(currentTimeStateFlow.value)
        Log.d("MyLogs88", "setCurrentTrack()")
        Log.d("MyLogs88", "current = ${currentTrack.name}")
    }

    // передаем время с которого должен играть трек
    fun setTime(timeFromSeekBar: Long){
        val duration = track!!.durationInMs
        val seekTime = ((duration - timeFromSeekBar) % (player.duration))
        currentTimeStateFlow.value = timeFromSeekBar
        //mediaPlayerArray.forEach { it?.seekTo(seekTime) }
    }

    fun play() {

//        if (currentTime != -1L) {
//
//            val seekTime = ((duration - timeToEnd) % (mediaPlayerArray[0]!!.duration))
//            mediaPlayerArray.forEach { it?.seekTo(seekTime) }
//
//            setCurrentTime(currentTime)
//        }

        Log.d("MyLogs88", "play()")
        player.start()
        setCurrentTime(currentTimeStateFlow.value)
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

        val seekTime = ( (duration - (duration - time)) % (player.duration)).toInt()

        //((duration - timeToEnd) % (mediaPlayerArray[0]!!.duration))
        Log.d("MyLogs88", "seekTime = ${seekTime}")

        player.seekTo(seekTime)

        //currentTimeStateFlow.value = time  //
        //if (timer == null) {
        timer = object : CountDownTimer(duration - time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeStateFlow.value = currentTimeStateFlow.value + 1000
                Log.d("MyLogs88", "onTick() millisUntilFinished = ${millisUntilFinished}, duration = $duration, currentTimeStateFlow = ${currentTimeStateFlow.value}")
            }

            override fun onFinish() {
//                player.stop()
//                stopSelf()
                Log.d("MyLogs88", "stopSelf()")
            }
        }.start()

        Log.d("MyLogs88", "setCurrentTime() time = ${time}")

        //}
    }


}

// время которое будет играть песня
// время трека
// значение с сикбара


