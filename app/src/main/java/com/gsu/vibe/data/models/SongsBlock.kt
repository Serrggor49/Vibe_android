package com.gsu.vibe.data.models

data class SongsBlock(
    val type: ItemType,
    val songs: List<SoundModel> = emptyList(),
)

enum class ItemType { Single, Double, Triple, Quadruple }