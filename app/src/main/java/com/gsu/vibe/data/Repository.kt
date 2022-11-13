package com.gsu.vibe.data

import com.gsu.vibe.data.database.DataBase
import com.gsu.vibe.data.models.SoundModel

object Repository {

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

    enum class SoundType {
        SLEEP,
        FOCUS,
        MEDITATION,
        NATURE,
        FAVORITES,
        ALL
    }

}