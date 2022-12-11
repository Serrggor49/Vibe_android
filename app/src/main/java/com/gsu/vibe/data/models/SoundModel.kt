package com.gsu.vibe.data.models

data class SoundModel(

    val name: String = " ",
    val title: String = " ",
    val subtitle: String = " ",
    val url: String =  "",
    val background: Int,
    val foreground: Int,
    val previewF: Int,
    val previewB: Int,
    val sound: Int,
    val isFavorite: Boolean = false

    )