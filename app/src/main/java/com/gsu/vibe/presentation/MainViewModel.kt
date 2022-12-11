package com.gsu.vibe.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel

class MainViewModel : ViewModel() {


    var currentType = CurrentType.FOR_SLEEP

    val visibilityBottomBarLivaData = MutableLiveData(true) // отвечает за показ бара на главном экране

    val repository: Repository = Repository

    var listAllSounds = repository.getSounds((Repository.SoundType.ALL))

    lateinit var currentSound : SoundModel
    fun setCurrentSound(name: String){
        currentSound = listAllSounds.filter { it.name == name }[0]
    }


    enum class CurrentType {
        FOR_SLEEP, FOR_MEDITATION, FOR_FOCUS, NATURE, FAVORITE
    }

}