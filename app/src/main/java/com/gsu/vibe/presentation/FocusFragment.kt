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
import com.gsu.vibe.databinding.FragmentFocusBinding
import com.gsu.vibe.databinding.FragmentMeditationBinding
import com.gsu.vibe.databinding.FragmentSleepBinding


class FocusFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentFocusBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFocusBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        Log.d("MyLogs", "button11")

        binding.focus011.setOnClickListener {
            mainViewmodel.setCurrentSound("focus011")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus021.setOnClickListener {
            mainViewmodel.setCurrentSound("focus021")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus022.setOnClickListener {
            mainViewmodel.setCurrentSound("focus022")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus031.setOnClickListener {
            mainViewmodel.setCurrentSound("focus031")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus032.setOnClickListener {
            mainViewmodel.setCurrentSound("focus032")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus041.setOnClickListener {
            mainViewmodel.setCurrentSound("focus041")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus051.setOnClickListener {
            mainViewmodel.setCurrentSound("focus051")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus052.setOnClickListener {
            mainViewmodel.setCurrentSound("focus052")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus061.setOnClickListener {
            mainViewmodel.setCurrentSound("focus061")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus062.setOnClickListener {
            mainViewmodel.setCurrentSound("focus062")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus071.setOnClickListener {
            mainViewmodel.setCurrentSound("focus071")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus072.setOnClickListener {
            mainViewmodel.setCurrentSound("focus072")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

        binding.focus081.setOnClickListener {
            mainViewmodel.setCurrentSound("focus081")
            val action = FocusFragmentDirections.actionFocusFragmentToPlayerFragment()
            view?.findNavController()?.navigate(action)
        }

    }

}