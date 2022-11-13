package com.gsu.vibe.data.database

import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel

object DataBase {

    val meditation1 = SoundModel(
        name = "m1",
        title = "Сквозь время",
        subtitle = "Для сна",
        url = "https://firebasestorage.googleapis.com/v0/b/vibe-3bd24.appspot.com/o/Ария%20-%20Беспечный%20Ангел%20(2).mp3?alt=media&token=e9ae6b4c-bcb7-4625-a963-3cee7e0151ab",
      //  url = "https://chakris.app/audio/meditation_1.mp3",
        background = R.drawable.sleep_01_1b_quiet_harbor,
        foreground = R.drawable.sleep_01_1f_quiet_harbor,
        sound = R.raw.ariya
    )

    val meditation2 = SoundModel(
        name = "m2",
        title = "Шопот космоса",
        subtitle = "Для сна",
      //  url = "https://firebasestorage.googleapis.com/v0/b/vibe-3bd24.appspot.com/o/Ария%20-%20Беспечный%20Ангел%20(2).mp3?alt=media&token=e9ae6b4c-bcb7-4625-a963-3cee7e0151ab",
        url = "https://chakris.app/audio/meditation_2.mp3",
        background = R.drawable.sleep_01_2b_endless_space,
        foreground = R.drawable.sleep_01_2f_endless_space,
        sound = R.raw.ariya
    )

    val meditation3 = SoundModel(
        name = "m3",
        title = "Гармония",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_4.mp3",
        background = R.drawable.sleep_02_1b_summer_evening,
        foreground = R.drawable.sleep_02_1f_summer_evening,
        sound = R.raw.ariya
    )

    val meditation4 = SoundModel(
        name = "m4",
        title = "Ночь в пустыне",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_5.mp3",
        background = R.drawable.sleep_02_2b_calm,
        foreground = R.drawable.sleep_02_2f_calm,
        sound = R.raw.ariya
    )


    val meditation5 = SoundModel(
        name = "m5",
        title = "На встречу закату",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_7.mp3",
        background = R.drawable.sleep_03_01b_concentration_on_breathing,
        foreground = R.drawable.sleep_03_01f_concentration_on_breathing,
        sound = R.raw.ariya
    )


    val meditation6 = SoundModel(
        name = "m6",
        title = "Наедине с вечностью",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_8.mp3",
        background = R.drawable.sleep_04_1b_milky_way,
        foreground = R.drawable.sleep_04_1f_milky_way,
        sound = R.raw.ariya
    )

    val meditation7 = SoundModel(
        name = "m7",
        title = "Северный огонь",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_9.mp3",
        background = R.drawable.sleep_04_2b_northern_lights,
        foreground = R.drawable.sleep_04_2f_northern_lights,
        sound = R.raw.ariya
    )


    val meditation8 = SoundModel(
        name = "m8",
        title = "Внутренний покой",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_11.mp3",
        background = R.drawable.sleep_05_1b_tara_mantra,
        foreground = R.drawable.sleep_05_1f_tara_mantra,
        sound = R.raw.ariya
    )

    val meditation9 = SoundModel(
        name = "m9",
        title = "Морская ария",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_12.mp3",
        background = R.drawable.sleep_02_1b_song_of_dolphins,
        foreground = R.drawable.sleep_02_1f_song_of_dolphins,
        sound = R.raw.ariya
    )

    val meditation10 = SoundModel(
        name = "m10",
        title = "Последний луч",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_14.mp3",
        background = R.drawable.sleep_02_1b_forest_harmony,
        foreground = R.drawable.sleep_02_1f_forest_harmony,
        sound = R.raw.ariya
    )

    val meditation11 = SoundModel(
        name = "m11",
        title = "Четвертая стихия",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_22.mp3",
        background = R.drawable.sleep_01_2b_wise_river,
        foreground = R.drawable.sleep_01_2f_wise_river,
        sound = R.raw.ariya
    )

    val meditation12 = SoundModel(
        name = "m12",
        title = "Созерцая вечность",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_15.mp3",
        background = R.drawable.sleep_07_1d_rainy_night,
        foreground = R.drawable.sleep_07_1f_rainy_night,
        sound = R.raw.ariya
    )

    val meditation13 = SoundModel(
        name = "m13",
        title = "Мир сноведений",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_16.mp3",
        background = R.drawable.sleep_07_2d_peaceful_sleep,
        foreground = R.drawable.sleep_07_2f_peaceful_sleep_prev,
        sound = R.raw.ariya
    )


    val meditation14 = SoundModel(
        name = "m14",
        title = "Лунный свет",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_19.mp3",
        background = R.drawable.sleep_07_2d_new_moon,
        foreground = R.drawable.sleep_07_2f_new_moon,
        sound = R.raw.ariya
    )


    val meditation15 = SoundModel(
        name = "m15",
        title = "Безмятежность",
        subtitle = "Для сна",
        url = "https://chakris.app/audio/meditation_21.mp3",
        background = R.drawable.sleep_08_2d_quiet_time,
        foreground = R.drawable.sleep_08_2f_quiet_time,
        sound = R.raw.ariya
    )


    fun getListForSleep(): List<SoundModel>{
        return listOf(meditation1, meditation2, meditation3, meditation4, meditation5, meditation6,
            meditation7, meditation8, meditation9, meditation10, meditation11, meditation12, meditation13, meditation14, meditation15)
    }


    val  meditation011 = SoundModel(
        name = "meditation011",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_21.mp3",
        background = R.drawable.meditation_01_1b,
        foreground = R.drawable.meditation_01_1f,
        sound = R.raw.ariya
    )


    val meditation021 = SoundModel(
        name = "meditation021",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_19.mp3",
        background = R.drawable.meditation_02_1b,
        foreground = R.drawable.meditation_02_1f,
        sound = R.raw.ariya
    )


    val meditation022 = SoundModel(
        name = "meditation022",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_16.mp3",
        background = R.drawable.meditation_02_2b,
        foreground = R.drawable.meditation_02_2f,
        sound = R.raw.ariya
    )


    val meditation031 = SoundModel(
        name = "meditation031",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_15.mp3",
        background = R.drawable.meditation_03_1b,
        foreground = R.drawable.meditation_03_1f,
        sound = R.raw.ariya
    )



    val meditation032 = SoundModel(
        name = "meditation032",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_22.mp3",
        background = R.drawable.meditation_03_2b,
        foreground = R.drawable.meditation_03_2f,
        sound = R.raw.ariya
    )



    val meditation041 = SoundModel(
        name = "meditation041",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_14.mp3",
        background = R.drawable.meditation_04_1b,
        foreground = R.drawable.meditation_04_1f,
        sound = R.raw.ariya
    )



    val meditation042 = SoundModel(
        name = "meditation042",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_12.mp3",
        background = R.drawable.meditation_04_2b,
        foreground = R.drawable.meditation_04_2f,
        sound = R.raw.ariya
    )



    val meditation051 = SoundModel(
        name = "meditation051",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_11.mp3",
        background = R.drawable.meditation_05_1b,
        foreground = R.drawable.meditation_05_1f,
        sound = R.raw.ariya
    )

    val meditation061 = SoundModel(
        name = "meditation061",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_9.mp3",
        background = R.drawable.meditation_06_1b,
        foreground = R.drawable.meditation_06_1f,
        sound = R.raw.ariya
    )


    val meditation062 = SoundModel(
        name = "meditation062",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_8.mp3",
        background = R.drawable.meditation_06_2b,
        foreground = R.drawable.meditation_06_2f,
        sound = R.raw.ariya
    )


    val meditation071 = SoundModel(
        name = "meditation071",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_7.mp3",
        background = R.drawable.meditation_07_1b,
        foreground = R.drawable.meditation_07_1f,
        sound = R.raw.ariya
    )


    val meditation081 = SoundModel(
        name = "meditation081",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_5.mp3",
        background = R.drawable.meditation_08_1b,
        foreground = R.drawable.meditation_08_1f,
        sound = R.raw.ariya
    )

    val meditation082 = SoundModel(
        name = "meditation082",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_4.mp3",
        background = R.drawable.meditation_08_2b,
        foreground = R.drawable.meditation_08_2f,
        sound = R.raw.ariya
    )


    val meditation091 = SoundModel(
        name = "meditation091",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_2.mp3",
        background = R.drawable.meditation_09_1b,
        foreground = R.drawable.meditation_09_1f,
        sound = R.raw.ariya
    )


    fun getListForMeditaion(): List<SoundModel>{
        return listOf(
            meditation011, meditation021, meditation022, meditation031, meditation032, meditation041,
            meditation042, meditation051, meditation061, meditation062, meditation071,
            meditation081, meditation082, meditation091)
    }


    val  focus011 = SoundModel(
        name = "focus011",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_2.mp3",
        background = R.drawable.focus_01_1b,
        foreground = R.drawable.focus_01_1f,
        sound = R.raw.ariya
    )

    val  focus021 = SoundModel(
        name = "focus021",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_4.mp3",
        background = R.drawable.focus_02_1b,
        foreground = R.drawable.focus_02_1f,
        sound = R.raw.ariya
    )


    val  focus022 = SoundModel(
        name = "focus022",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_5.mp3",
        background = R.drawable.focus_02_2b,
        foreground = R.drawable.focus_02_2f,
        sound = R.raw.ariya
    )


    val  focus031 = SoundModel(
        name = "focus031",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_7.mp3",
        background = R.drawable.focus_03_1b,
        foreground = R.drawable.focus_03_1f,
        sound = R.raw.ariya
    )


    val  focus032 = SoundModel(
        name = "focus032",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_8.mp3",
        background = R.drawable.focus_03_2b,
        foreground = R.drawable.focus_03_2f,
        sound = R.raw.ariya
    )

    val  focus041 = SoundModel(
        name = "focus041",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_9.mp3",
        background = R.drawable.focus_04_1b,
        foreground = R.drawable.focus_04_1f,
        sound = R.raw.ariya
    )


    val  focus051 = SoundModel(
        name = "focus051",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_11.mp3",
        background = R.drawable.focus_05_1b,
        foreground = R.drawable.focus_05_1f,
        sound = R.raw.ariya
    )

    val  focus052 = SoundModel(
        name = "focus052",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_12.mp3",
        background = R.drawable.focus_05_2b,
        foreground = R.drawable.focus_05_2f,
        sound = R.raw.ariya
    )


    val  focus061 = SoundModel(
        name = "focus061",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_14.mp3",
        background = R.drawable.focus_06_1b,
        foreground = R.drawable.focus_06_1f,
        sound = R.raw.ariya
    )

    val  focus062 = SoundModel(
        name = "focus062",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_22.mp3",
        background = R.drawable.focus_06_2b,
        foreground = R.drawable.focus_06_2f,
        sound = R.raw.ariya
    )

    val  focus071 = SoundModel(
        name = "focus071",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_15.mp3",
        background = R.drawable.focus_07_1b,
        foreground = R.drawable.focus_07_1f,
        sound = R.raw.ariya
    )

    val  focus072 = SoundModel(
        name = "focus072",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_16.mp3",
        background = R.drawable.focus_07_2b,
        foreground = R.drawable.focus_07_2f,
        sound = R.raw.ariya
    )


    val  focus081 = SoundModel(
        name = "focus081",
        title = "Безмятежность",
        subtitle = "Медитация",
        url = "https://chakris.app/audio/meditation_19.mp3",
        background = R.drawable.focus_08_1b,
        foreground = R.drawable.focus_08_1f,
        sound = R.raw.ariya
    )



    fun getListForFocus(): List<SoundModel>{
        return listOf(
            focus011, focus021, focus022, focus031, focus032, focus041,
            focus051, focus052, focus061, focus062, focus071,
            focus072, focus081)
    }


    fun getAllSounds(): List<SoundModel>{
      val sleepList = getListForSleep()
      val meditationList = getListForMeditaion()
      val focusList = getListForFocus()

      val allList = concatenate(sleepList, meditationList, focusList)

        return allList
    }

    fun <T> concatenate(vararg lists: List<T>): List<T> {
        return listOf(*lists).flatten()
    }














}