package com.gsu.vibe.composeScreens

import com.gsu.vibe.R

sealed class Screens(val route: String, val icon: Int, val title: String) {
    object Sleep : Screens("sleep", R.drawable.ic_sleep_bar, "sleep")
    object Focus : Screens("focus", R.drawable.ic_focus_bar, "focus")
    object Meditation : Screens("meditation", R.drawable.ic_meditation_bar, "meditation")
    object Nature : Screens("nature", R.drawable.ic_nature_bar, "nature")
    object Mixer : Screens("mixer", R.drawable.ic_mixer_bar, "mixer")
}