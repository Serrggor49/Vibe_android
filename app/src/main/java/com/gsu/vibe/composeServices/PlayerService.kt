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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// сервис для compose плеера
class PlayerService : Service() {

    lateinit var player: MediaPlayer
    private val mediaPath = "/data/data/com.gsu.vibe/files/"
    private var track: SoundModel? = null
    var track2 = MutableStateFlow(SoundModel())
    private val binder = PlayerBinder()

    //val currentTimeStateFlow: MutableStateFlow<Long> = MutableStateFlow(0) // время которое стоит на slideBar
    var trackTime = 1000 // фактическое время трека
    var duration = 10000L // время выставленное пользователем
    var timer: CountDownTimer? = null


    override fun onBind(intent: Intent): IBinder = binder

    inner class PlayerBinder : Binder() {
        fun getService() = this@PlayerService
    }

    fun setCurrentTrack2(currentTrack: MutableStateFlow<SoundModel>) {
        track2 = currentTrack
    }

    fun setCurrentTrack(currentTrack: SoundModel) {
        track = currentTrack
        player = MediaPlayer.create(this, ("$mediaPath${track?.name}").toUri())
        player.isLooping = true
        trackTime = player.duration
        duration = currentTrack.durationInMs
        setCurrentTime(track2.value.currentTrackTime.toLong())
        Log.d("MyLogs88", "setCurrentTrack()")
        Log.d("MyLogs88", "current = ${currentTrack.name}")
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
        setCurrentTime(track2.value.currentTrackTime.toLong())
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

        //((duration - timeToEnd) % (mediaPlayerArray[0]!!.duration))
        Log.d("MyLogs88", "seekTime = ${seekTime}")

        player.seekTo(seekTime)

        //currentTimeStateFlow.value = time  //
        //if (timer == null) {
        timer = object : CountDownTimer(duration - time, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                //currentTimeStateFlow.value = currentTimeStateFlow.value + 1000

                currentTimeInTimer += 1000
                CoroutineScope(Dispatchers.Main).launch {
                    //track2.value = track2.value.copy(currentTrackTime = currentTimeStateFlow.value.toInt())
                    track2.value = track2.value.copy(currentTrackTime = currentTimeInTimer.toInt())
                    Log.d("MyLogs87", "track2.value = ${track2.value.currentTrackTime}")
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




