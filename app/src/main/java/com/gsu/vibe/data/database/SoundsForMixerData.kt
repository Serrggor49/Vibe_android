package com.gsu.vibe.data.database

import com.gsu.vibe.R
import com.gsu.vibe.data.models.MixerSoundModel

object SoundsForMixerData {



    fun getAnimals() : ArrayList<MixerSoundModel>{
        return arrayListOf(empty ,birdsSound, ladybugSound, birds2Sound, dolphinSound, catSound)
    }

    fun getNature() : ArrayList<MixerSoundModel>{
        return arrayListOf(empty, rainSound, thunderstormSound, thunderSound, waterflowSound, seaSound, blizzardSound, riverSound)
    }

    fun getInstrument() : ArrayList<MixerSoundModel>{
        return arrayListOf(empty, harpSound, flautaSound, guitarSound, pianoSound, tibetSound)
    }

    val empty = MixerSoundModel(name = "null", backimage = R.drawable.mixer_empty)

    val birdsSound = MixerSoundModel(name = "птицы", backimage = R.drawable.mixer_ladybug, sound = R.raw.bird_test)
    val ladybugSound = MixerSoundModel(name = "насекомые", backimage = R.drawable.mixer_birds, sound = R.raw.kit_test)
    val birds2Sound = MixerSoundModel(name = "птицы2", backimage = R.drawable.mixer_birds_2, sound = R.raw.bird_test)
    val dolphinSound = MixerSoundModel(name = "дельфин", backimage = R.drawable.mixer_dolphin, sound = R.raw.bird_test)
    val catSound = MixerSoundModel(name = "кот", backimage = R.drawable.mixer_cat, sound = R.raw.bird_test)


    val rainSound = MixerSoundModel(name = "дождь", backimage = R.drawable.mixer_rain, sound = R.raw.grom_test)
    val thunderstormSound = MixerSoundModel(name = "шторм", backimage = R.drawable.mixer_thunderstorm, sound = R.raw.grom_test2)
    val thunderSound = MixerSoundModel(name = "гром", backimage = R.drawable.mixer_thunder, sound = R.raw.bird_test)
    val waterflowSound = MixerSoundModel(name = "водопад", backimage = R.drawable.mixer_waterflow, sound = R.raw.bird_test)
    val seaSound = MixerSoundModel(name = "море", backimage = R.drawable.mixer_sea, sound = R.raw.bird_test)
    val blizzardSound = MixerSoundModel(name = "метель", backimage = R.drawable.mixer_blizzard, sound = R.raw.bird_test)
    val riverSound = MixerSoundModel(name = "река", backimage = R.drawable.mixer_river, sound = R.raw.bird_test)

    val harpSound = MixerSoundModel(name = "арфа", backimage = R.drawable.mixer_harp, sound = R.raw.harp_test)
    val flautaSound = MixerSoundModel(name = "флейта", backimage = R.drawable.mixer_flauta, sound = R.raw.bird_test)
    val guitarSound = MixerSoundModel(name = "гитара", backimage = R.drawable.mixer_guitar, sound = R.raw.bird_test)
    val pianoSound = MixerSoundModel(name = "фортепиано", backimage = R.drawable.mixer_piano, sound = R.raw.bird_test)
    val tibetSound = MixerSoundModel(name = "тибетские чаши", backimage = R.drawable.mixer_tibet, sound = R.raw.bird_test)

}