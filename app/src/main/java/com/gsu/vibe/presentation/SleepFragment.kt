package com.gsu.vibe.presentation

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentSleepBinding

class SleepFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentSleepBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)


        //val mediaPlayer = MediaPlayer.create(requireActivity(),  Uri.parse("/data/data/com.gsu.vibe/files/audio.mp3"))
        //mediaPlayer.start()

    }

    fun init() {
        Log.d("MyLogs", "button11")

        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }

        binding.quietHarbor011.setOnClickListener {
            mainViewmodel.setCurrentSound("m1")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.endlessSpace012.setOnClickListener {
            mainViewmodel.setCurrentSound("m2")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.summerEvening0201.setOnClickListener {
            mainViewmodel.setCurrentSound("m3")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.calm0202.setOnClickListener {
            mainViewmodel.setCurrentSound("m4")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m5Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m5")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m6Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m6")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m7Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m7")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m8Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m8")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m9Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m9")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m10Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m10")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }


        binding.m11Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m11")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m12Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m12")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m13Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m13")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m14Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m14")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.m15Button.setOnClickListener {
            mainViewmodel.setCurrentSound("m15")
            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

//        binding.imageView3.setOnClickListener {
//            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
//            view?.findNavController()?.navigate(action)
//        }

//        binding.quietHarbor01100.setOnClickListener {
//            mainViewmodel.setCurrentSound("m7")
//            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
//            view?.findNavController()?.navigate(action)
//        }
//
//
//        binding.quietHarbor01100.setOnClickListener {
//            mainViewmodel.setCurrentSound("m8")
//            val action = SleepFragmentDirections.actionSleepFragmentToPlayerFragment()
//            view?.findNavController()?.navigate(action)
//        }





    }

}