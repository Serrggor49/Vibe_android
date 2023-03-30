package com.gsu.vibe.presentation

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.databinding.FragmentSleepBinding
import com.gsu.vibe.services.RandomTransitionGeneratorForPrev


class SleepFragment : Fragment() {

    val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
    val transitionDuration = 20000L
    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentSleepBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "MyLogs444")
        mainViewmodel.currentType = MainViewModel.CurrentType.FOR_SLEEP
        (activity as MainActivity).updateBottomButtons()
    }

    fun init() {

        binding.image3112200.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image25112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image311.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image411.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image2.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image251100200.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image3110020.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image21.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image25112223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image31122222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image41122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        binding.image411223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))

       // binding.imageView2.
        //Glide.with(this).load(com.gsu.vibe.R.drawable.sleep_01_1f_quiet_harbor_prev).into(binding.imageView2)

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