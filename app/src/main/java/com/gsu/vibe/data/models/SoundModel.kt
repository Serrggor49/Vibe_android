package com.gsu.vibe.data.models

data class SoundModel(

    val name: String = " ",
    val title: Int = 0,
    val subtitle: Int = 0,
    val url: String =  "",
    val background: Int,
    val foreground: Int,
    val previewF: Int,
    val previewB: Int,
    val sound: Int = 0,
    val isFavorite: Boolean = false

    )