package com.gsu.vibe.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// данная модель используется для запуска плеера в сервисе
@Parcelize
data class TrackForServiceModel(
    val soundPaths : Array<String>,
    val soundVolume : Array<Float>,
    val soundName: Int,
    val soundType: Int,
    val soundImage: Int,
    val timer: Int,
    val duration: Int,

): Parcelable