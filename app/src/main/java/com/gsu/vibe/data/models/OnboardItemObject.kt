package com.gsu.vibe.data.models

data class OnboardItemObject (
    val items: List<Pair<Int, String>>,
    val type: OnboardItemType
        )

enum class OnboardItemType{
    TYPE_1, TYPE_2, TYPE_3, TYPE_4
}