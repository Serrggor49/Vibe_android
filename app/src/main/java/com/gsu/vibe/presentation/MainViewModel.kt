package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gsu.vibe.ConnectionLiveData
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.SoundModel


class MainViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = application.applicationContext

    var currentType = CurrentType.FOR_SLEEP

    val visibilityBottomBarLivaData = MutableLiveData(true) // отвечает за показ бара на главном экране
    val openFavoriteLivaData = MutableLiveData(true) // отвечает за открытие окна с изабранными аудиозаписями

    val repository: Repository = Repository

    var listAllSounds = repository.getSounds((Repository.SoundType.ALL))

    lateinit var currentSound : SoundModel

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    fun setCurrentSound(name: String){
        currentSound = listAllSounds.filter { it.name == name }[0]
    }

    enum class CurrentType {
        FOR_SLEEP, FOR_MEDITATION, FOR_FOCUS, NATURE, FAVORITE
    }

}