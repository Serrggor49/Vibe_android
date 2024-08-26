package com.gsu.vibe.data.models

data class SoundModel(
    var name: String = " ",
    val title: Int = 0,
    val subtitle: Int = 0,
    val url: String =  "",
    val background: Int = 0,
    val foreground: Int = 0,
    val mixerIcon: Int = 0, // иконка которая показывается в окне конфигурации миксера
    var isSelected: Boolean = false, // для миксера
    val preview: Int = 0,
    var sound: Int = 0,  // для миксера
    var soundVolume: Int = 1,
    val isFavorite: Boolean = false,
//    var volume: Int = 1,
    var durationInMs: Long = 600000, // продолжительность в мс
    var currentTrackTime: Int = 0, // текущее время трека
    var isDownloaded: Boolean = false,
    var isPlaying: Boolean = false,
)
