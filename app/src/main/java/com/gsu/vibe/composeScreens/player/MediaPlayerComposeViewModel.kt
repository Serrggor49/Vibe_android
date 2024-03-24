package com.gsu.vibe.composeScreens.player

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.getFormtTime
import com.gsu.vibe.services.DownloadAudioFromUrlNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.random.Random

class MediaPlayerComposeViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaPath = "/data/data/com.gsu.vibe/files/"

    @SuppressLint("StaticFieldLeak")
    private val context: Context = application

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
        downloadTrack()
    }


    fun downloadTrack() {
        viewModelScope.launch {
            Log.d("MyLogs88", "file download = ${File("$mediaPath${state.value.name}").exists()}")
            if (!File("$mediaPath${state.value.name}").exists()) {  // если трека с таким имени нет в памяти, то скачиваем
                val job = CoroutineScope(Dispatchers.IO).launch {
                    try {
                        Log.d("MyLogs88", "start download")
                        DownloadAudioFromUrlNew.download(
                            fileName = state.value.name,
                            urlString = state.value.url,
                            context = context
                        )

                        Log.d("MyLogs88", "download finished")
                        _state.value = _state.value.copy(isDownloaded = true)
                    } catch (e: java.lang.Exception) { // без этого, если будет отключение интернета во время скачивания, приложение вылетает
                        Log.d("MyLogs88", "exception = $e")
                        CoroutineScope(Dispatchers.Main).launch {
                            //requireActivity().onBackPressed()
                        }
                    }
                }
                job.join()
            }
            else{
                _state.value = _state.value.copy(isDownloaded = true)
            }
        }
    }

    fun getListForFocusForCompose() = repository.getListForFocusForCompose()

}


