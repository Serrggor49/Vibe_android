package com.gsu.vibe.composeScreens.player

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel
import kotlinx.coroutines.flow.StateFlow

class MediaPlayerComposeViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context: Context = application

    var isUserInteracting by mutableStateOf(false)

    val repository: Repository = Repository
    val state: StateFlow<SoundModel> = repository.state
    init {
        repository.context = context
    }

    fun setCurrentSound(name: String) = repository.setCurrentSound(name)

    fun setDurationTime(timeInHours: Int = 0, timeInMinutes: Int = 0, timeInSeconds: Int = 0) = repository.setDurationTime(timeInHours, timeInMinutes, timeInSeconds)
    fun getListForFocusForCompose() = repository.getListForFocusForCompose()
    fun setTimeFromSlider(time: Long) = repository.setTimeFromSlider(time)
    fun playOrPauseTrack(context: Context)  = repository.playOrPauseTrack()

}


