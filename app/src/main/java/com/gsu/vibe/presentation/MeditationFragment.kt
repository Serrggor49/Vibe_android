package com.gsu.vibe.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.databinding.FragmentMeditationBinding


class MeditationFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentMeditationBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        Log.d("MyLogs", "button11")

        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }

        binding.meditation011.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation011")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation021.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation021")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation022.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation022")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation031.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation031")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation032.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation032")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation041.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation041")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation042.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation042")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation051.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation051")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation061.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation061")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation062.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation062")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }


        binding.meditation071.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation071")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation081.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation081")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation082.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation082")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.meditation091.setOnClickListener {
            mainViewmodel.setCurrentSound("meditation091")
            val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

    }

}