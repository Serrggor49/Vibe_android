package com.gsu.vibe.presentation

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentPlayerBinding
import com.gsu.vibe.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentSplashBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackground()

        initTimer()
        mainViewmodel.visibilityBottomBarLivaData.postValue(false)
    }


    fun initBackground() {

        val kbv = binding.image1
        val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
        val generator = RandomTransitionGenerator(25000, ACCELERATE_DECELERATE)
       // kbv.setTransitionGenerator(generator)

    }


    fun initTimer(){
        val timer = object : CountDownTimer(500, 2000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                val action = SplashFragmentDirections.actionSplachFragmentToSleepFragment()
                view?.findNavController()?.navigate(action)
            }
        }
        timer.start()
    }


}