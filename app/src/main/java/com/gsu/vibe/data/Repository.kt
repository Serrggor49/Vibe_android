package com.gsu.vibe.data

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
import com.gsu.vibe.composeServices.PlayerService
import com.gsu.vibe.data.database.DataBase
import com.gsu.vibe.data.database.SoundsForMixerData
import com.gsu.vibe.data.models.SoundModel
import com.gsu.vibe.services.DownloadAudioFromUrlNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

@SuppressLint("StaticFieldLeak")
object Repository {

    private val mediaPath = "/data/data/com.gsu.vibe/files/"

    lateinit var context: Context

    val state: MutableStateFlow<SoundModel> = MutableStateFlow(SoundModel())
    lateinit var intentForStartPlayerService: Intent

    private var listAllSounds = getSounds(SoundType.ALL)
    fun setCurrentSound(name: String) {
        state.value = listAllSounds.filter { it.name == name }[0]
        Log.d("MyLogs33", "songName in viewmodel = ${state.value.background}")
    }


    fun setDurationTime(timeInHours: Int = 0, timeInMinutes: Int = 0, timeInSeconds: Int = 0) {
        state.value = state.value.copy(
            durationInMs = (((timeInHours * 60) + timeInMinutes) * 60 * 1000 + timeInSeconds * 1000).toLong()
        )
        downloadTrack()
    }

    fun downloadTrack() {
        CoroutineScope(Dispatchers.IO).launch {
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
                        state.value = state.value.copy(isDownloaded = true)
                    } catch (e: java.lang.Exception) { // без этого, если будет отключение интернета во время скачивания, приложение вылетает
                        Log.d("MyLogs88", "exception = $e")
                        CoroutineScope(Dispatchers.Main).launch {
                            //requireActivity().onBackPressed()
                        }
                    }
                }
                job.join()
            } else {
                state.value = state.value.copy(isDownloaded = true)
            }
        }
    }


    private var serviceBound = false
    private var musicService: PlayerService? = null
    private lateinit var serviceConnection: ServiceConnection

    fun setTimeFromSlider(time: Long) {
        if (state.value.isPlaying) {
            musicService?.setCurrentTime(time)
        } else {
            state.value = state.value.copy(currentTrackTime = time.toInt())
        }
    }

    fun stopPlayerService() {

        Log.d("MyLogs89", "stopPlayerService()")
        //if (serviceBound) {
        if (serviceBound) {
            context.unbindService(serviceConnection) // Разрываем соединение с сервисом
            serviceBound = false
        }

        if (::intentForStartPlayerService.isInitialized) context.stopService(intentForStartPlayerService)
        //}
    }


    fun playOrPauseTrack() {
        CoroutineScope(Dispatchers.Main).launch {
            if (!serviceBound) { // сервис не привязан или не создан
                intentForStartPlayerService = Intent(context, PlayerService::class.java)
                serviceConnection = object : ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        val binder = service as PlayerService.PlayerBinder
                        musicService = binder.getService()
                        serviceBound = true
                        musicService?.setCurrentTrack(state)
                        musicService?.play()
                        state.value = state.value.copy(isPlaying = true)
                    }

                    override fun onServiceDisconnected(name: ComponentName?) {
                        serviceBound = false
                    }
                }
                context.bindService(intentForStartPlayerService, serviceConnection, Context.BIND_AUTO_CREATE)
            } else {
                musicService?.let {
                    if (it.player.isPlaying) {
                        it.pause()
                        state.value = state.value.copy(isPlaying = false)
                    } else {
                        it.play()
                        state.value = state.value.copy(isPlaying = true)
                    }
                }
            }
        }
    }


    fun getSoundsForMixer(type: SoundsForMixerType): ArrayList<SoundModel> {
        val list: ArrayList<SoundModel> = when (type) {
            SoundsForMixerType.ANIMALS -> SoundsForMixerData.getAnimals()
            SoundsForMixerType.NATURE -> SoundsForMixerData.getNature()
            SoundsForMixerType.INSTRUMENTS -> SoundsForMixerData.getInstrument()
            else -> {
                SoundsForMixerData.getAnimals()
            }
        }
        return list
    }

    fun getListForSleepForCompose() = DataBase.getListForSleepForCompose()
    fun getListForMeditaionForCompose() = DataBase.getListForMeditaionForCompose()
    fun getListForFocusForCompose() = DataBase.getListForFocusForCompose()
    fun getListForNatureForCompose() = DataBase.getListForNatureForCompose()

    fun getSounds(type: SoundType): List<SoundModel> {

        val list: List<SoundModel> = when (type) {
            SoundType.SLEEP -> DataBase.getListForSleep()
            SoundType.FOCUS -> DataBase.getListForFocus()
            SoundType.MEDITATION -> DataBase.getListForMeditaion()
            SoundType.NATURE -> DataBase.getListForSleep()
            SoundType.FAVORITES -> DataBase.getListForSleep()
            SoundType.ALL -> DataBase.getAllSounds()
        }

        return list
    }

    // возвращает список звуков добавленный в избранное
    fun getFavoritesSounds(context: Context): List<SoundModel> {

        val sharedPreference = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
        val allSoundsList = getSounds(SoundType.ALL)
        val favoritesList = mutableListOf<SoundModel>()

        for (sound in allSoundsList) {
            if (sharedPreference.getBoolean(sound.name, false)) {
                favoritesList.add(sound)
            }
        }
        return favoritesList
    }

    fun getFavoritesStatus(context: Context, nameSound: String): Boolean {
        val sharedPreference = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
        return sharedPreference.getBoolean(nameSound, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun addStatusSoundToFavorites(context: Context, nameSound: String, state: Boolean) {
        val sharedPreference = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean(nameSound, state)
        editor.apply()
    }

    enum class SoundType {
        SLEEP,
        FOCUS,
        MEDITATION,
        NATURE,
        FAVORITES,
        ALL
    }

    enum class SoundsForMixerType {
        ANIMALS, NATURE, INSTRUMENTS, BINUA
    }

}