package com.gsu.vibe.composeScreens.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MediaPlayerComposeViewModel : ViewModel() {

    private val _state = MutableStateFlow(SoundModel())
    val state: StateFlow<SoundModel> = _state

    //var testString = "0"

    init {
       // randn1()
    }

    val repository: Repository = Repository
    var listAllSounds = repository.getSounds((Repository.SoundType.ALL))

    fun setCurrentSound(name: String) {
        _state.value = listAllSounds.filter { it.name == name }[0]
        Log.d("MyLogs33", "songName in viewmodel = ${state.value.background}")
    }

    fun getListForFocusForCompose() = repository.getListForFocusForCompose()

    fun randn1() {

        viewModelScope.launch {
            while (true) {
                delay(2000)
              //  _state.value = SoundModel(name = Random.nextInt(0, 100).toString())
                //_state.value.name = Random.nextInt(0, 100).toString()

                Log.d("MyLogs554", "name = ${state.value.name}")
            }
        }
    }
}


