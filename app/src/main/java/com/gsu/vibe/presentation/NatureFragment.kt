package com.gsu.vibe.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentMeditationBinding
import com.gsu.vibe.databinding.FragmentNatureBinding

class NatureFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentNatureBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNatureBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        Log.d("MyLogs", "button11")

        binding.nature011.setOnClickListener {
            mainViewmodel.setCurrentSound("nature011")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature012.setOnClickListener {
            mainViewmodel.setCurrentSound("nature012")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature021.setOnClickListener {
            mainViewmodel.setCurrentSound("nature021")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature022.setOnClickListener {
            mainViewmodel.setCurrentSound("nature022")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature031.setOnClickListener {
            mainViewmodel.setCurrentSound("nature031")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature032.setOnClickListener {
            mainViewmodel.setCurrentSound("nature032")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature041.setOnClickListener {
            mainViewmodel.setCurrentSound("nature041")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature051.setOnClickListener {
            mainViewmodel.setCurrentSound("nature051")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature052.setOnClickListener {
            mainViewmodel.setCurrentSound("nature052")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature061.setOnClickListener {
            mainViewmodel.setCurrentSound("nature061")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature062.setOnClickListener {
            mainViewmodel.setCurrentSound("nature062")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature071.setOnClickListener {
            mainViewmodel.setCurrentSound("nature071")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature081.setOnClickListener {
            mainViewmodel.setCurrentSound("nature081")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature082.setOnClickListener {
            mainViewmodel.setCurrentSound("nature082")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.nature091.setOnClickListener {
            mainViewmodel.setCurrentSound("nature091")
            val action = NatureFragmentDirections.actionNatureFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

    }


}