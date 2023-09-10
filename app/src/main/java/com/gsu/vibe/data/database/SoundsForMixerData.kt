package com.gsu.vibe.data.database

import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel

object SoundsForMixerData {



    fun getAnimals() : ArrayList<SoundModel>{
        return arrayListOf(empty ,birdsSound, ladybugSound, birds2Sound, dolphinSound, catSound)
    }

    fun getNature() : ArrayList<SoundModel>{
        return arrayListOf(empty, rainSound, thunderstormSound, thunderSound, waterflowSound, seaSound, blizzardSound, riverSound)
    }

    fun getInstrument() : ArrayList<SoundModel>{
        return arrayListOf(empty, harpSound, flautaSound, guitarSound, pianoSound, tibetSound)
    }

    val empty = SoundModel(name = "null", mixerIcon = R.drawable.mixer_empty, sound = R.raw.empty)

//    val birdsSound = MixerSoundModel(name = "тропические птицы", backimage = R.drawable.mixer_birds, sound = R.raw.tropical_birds)
//    val birds2Sound = MixerSoundModel(name = "обычные птицц", backimage = R.drawable.mixer_birds_2, sound = R.raw.bird_nightingale) // соловьи, скачано
//    val ladybugSound = MixerSoundModel(name = "насекомые", backimage = R.drawable.mixer_ladybug, sound = R.raw.bugs)
//    val dolphinSound = MixerSoundModel(name = "дельфин", backimage = R.drawable.mixer_dolphin, sound = R.raw.dolphins)  // булькание на фоне надо убрать
//    val catSound = MixerSoundModel(name = "кот", backimage = R.drawable.mixer_cat, sound = R.raw.cat)
//
//    val rainSound = MixerSoundModel(name = "дождь", backimage = R.drawable.mixer_rain, sound = R.raw.rain)
//    val thunderstormSound = MixerSoundModel(name = "дождь и гром", backimage = R.drawable.mixer_thunderstorm, sound = R.raw.rain_thunder)
//    val thunderSound = MixerSoundModel(name = "гром", backimage = R.drawable.mixer_thunder, sound = R.raw.thunder)
//    val waterflowSound = MixerSoundModel(name = "водопад", backimage = R.drawable.mixer_waterflow, sound = R.raw.waterfall) // надо зациклить
//    val seaSound = MixerSoundModel(name = "море и чайки", backimage = R.drawable.mixer_sea, sound = R.raw.sea_seagulls)
//    val blizzardSound = MixerSoundModel(name = "ночная вьга", backimage = R.drawable.mixer_blizzard, sound = R.raw.blizzard)
//    val riverSound = MixerSoundModel(name = "река", backimage = R.drawable.mixer_river, sound = R.raw.river) // по картинке больше озеро
//
//    val harpSound = MixerSoundModel(name = "арфа", backimage = R.drawable.mixer_harp, sound = R.raw.harp_test)
//    val flautaSound = MixerSoundModel(name = "флейта", backimage = R.drawable.mixer_flauta, sound = R.raw.flaut)
//    val guitarSound = MixerSoundModel(name = "гитара", backimage = R.drawable.mixer_guitar, sound = R.raw.guitar)
//    val pianoSound = MixerSoundModel(name = "фортепиано", backimage = R.drawable.mixer_piano, sound = R.raw.piano)
//    val tibetSound = MixerSoundModel(name = "тибетские чаши", backimage = R.drawable.mixer_tibet, sound = R.raw.tibet_bowls)



    val birdsSound = SoundModel(name = "тропические птицы", mixerIcon = R.drawable.mixer_birds, sound = R.raw.tropical_birds)
    val birds2Sound = SoundModel(name = "обычные птицц", mixerIcon = R.drawable.mixer_birds_2, sound = R.raw.bird_nightingale) // соловьи, скачано
    val ladybugSound = SoundModel(name = "насекомые", mixerIcon = R.drawable.mixer_ladybug, sound = R.raw.bugs)
    val dolphinSound = SoundModel(name = "дельфин", mixerIcon = R.drawable.mixer_dolphin, sound = R.raw.dolphins)  // булькание на фоне надо убрать
    val catSound = SoundModel(name = "кот", mixerIcon = R.drawable.mixer_cat, sound = R.raw.cat)

    val rainSound = SoundModel(name = "дождь", mixerIcon = R.drawable.mixer_rain, sound = R.raw.rain)
    val thunderstormSound = SoundModel(name = "дождь и гром", mixerIcon = R.drawable.mixer_thunderstorm, sound = R.raw.rain_thunder)
    val thunderSound = SoundModel(name = "гром", mixerIcon = R.drawable.mixer_thunder, sound = R.raw.thunder)
    val waterflowSound = SoundModel(name = "водопад", mixerIcon = R.drawable.mixer_waterflow, sound = R.raw.waterfall) // надо зациклить
    val seaSound = SoundModel(name = "море и чайки", mixerIcon = R.drawable.mixer_sea, sound = R.raw.sea_seagulls)
    val blizzardSound = SoundModel(name = "ночная вьга", mixerIcon = R.drawable.mixer_blizzard, sound = R.raw.blizzard)
    val riverSound = SoundModel(name = "река", mixerIcon = R.drawable.mixer_river, sound = R.raw.river) // по картинке больше озеро

    val harpSound = SoundModel(name = "арфа", mixerIcon = R.drawable.mixer_harp, sound = R.raw.harp_test)
    val flautaSound = SoundModel(name = "флейта", mixerIcon = R.drawable.mixer_flauta, sound = R.raw.flaut)
    val guitarSound = SoundModel(name = "гитара", mixerIcon = R.drawable.mixer_guitar, sound = R.raw.guitar)
    val pianoSound = SoundModel(name = "фортепиано", mixerIcon = R.drawable.mixer_piano, sound = R.raw.piano)
    val tibetSound = SoundModel(name = "тибетские чаши", mixerIcon = R.drawable.mixer_tibet, sound = R.raw.tibet_bowls)

}