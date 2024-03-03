package com.gsu.vibe.composeScreens.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.getFormtTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MediaPlayerComposeViewModel : ViewModel() {

    private val _state = MutableStateFlow(SoundModel())
    val state: StateFlow<SoundModel> = _state

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
            }
        }
    }

    val repository: Repository = Repository
    var listAllSounds = repository.getSounds((Repository.SoundType.ALL))

    fun setCurrentSound(name: String) {
        _state.value = listAllSounds.filter { it.name == name }[0]
        Log.d("MyLogs33", "songName in viewmodel = ${state.value.background}")
    }

    fun setDurationTime(timeInHours: Int = 0, timeInMinutes: Int = 0, timeInSeconds: Int = 0) {
        _state.value = _state.value.copy(
            durationInMs = ((timeInHours * 60) + timeInMinutes) * 60 * 1000 + timeInSeconds * 1000
        )
    }

    fun getListForFocusForCompose() = repository.getListForFocusForCompose()

}


