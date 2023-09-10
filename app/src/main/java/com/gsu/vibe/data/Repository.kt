package com.gsu.vibe.data

import android.annotation.SuppressLint
import android.content.Context
import com.gsu.vibe.data.database.DataBase
import com.gsu.vibe.data.database.SoundsForMixerData
import com.gsu.vibe.data.models.SoundModel

object Repository {


    fun getSoundsForMixer(type: SoundsForMixerType): ArrayList<SoundModel> {

        val list: ArrayList<SoundModel> = when (type) {
            SoundsForMixerType.ANIMALS -> SoundsForMixerData.getAnimals()
            SoundsForMixerType.NATURE -> SoundsForMixerData.getNature()
            SoundsForMixerType.INSTRUMENTS -> SoundsForMixerData.getInstrument()
            else -> {SoundsForMixerData.getAnimals()}
        }

        return list
    }

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
    fun getFavoritesSounds(context: Context) : List<SoundModel>{

        val sharedPreference =  context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
        val allSoundsList = getSounds(SoundType.ALL)
        val favoritesList = mutableListOf<SoundModel>()

        for(sound in allSoundsList){
            if (sharedPreference.getBoolean(sound.name, false)){
                favoritesList.add(sound)
            }
        }
        return favoritesList
    }

    fun getFavoritesStatus(context: Context, nameSound: String) : Boolean{
        val sharedPreference =  context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
        return sharedPreference.getBoolean(nameSound, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun addStatusSoundToFavorites(context: Context, nameSound: String, state: Boolean){
        val sharedPreference =  context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE)
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

    enum class SoundsForMixerType{
        ANIMALS,NATURE,INSTRUMENTS, BINUA
    }

}