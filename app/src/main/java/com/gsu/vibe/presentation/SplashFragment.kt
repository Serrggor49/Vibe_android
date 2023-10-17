package com.gsu.vibe.presentation

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentSplashBinding
import com.gsu.vibe.navOptionsLong

class SplashFragment : Fragment() {

    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentSplashBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

        //val kbv = binding.image1

        // kbv.setTransitionGenerator(generator)

    }


    fun initTimer() {
        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                Log.d("MyLogs1112", "status  = ${mainViewmodel.getOnboardStatus()}")

                if (mainViewmodel.getOnboardStatus()) {
                    val action =
                        SplashFragmentDirections.actionSplachFragmentToSleepFragment()
                    view?.findNavController()?.navigate(action, navOptionsSplash)
                } else {
                    val action =
                        SplashFragmentDirections.actionSplachFragmentToNewOnboardFirstFragment()
                    view?.findNavController()?.navigate(action, navOptionsSplash)
                }
            }
        }
        timer.start()
    }


    val navOptionsSplash: NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in_long)
        .setExitAnim(R.anim.fade_out_long)
        .setPopEnterAnim(R.anim.fade_in_long)
        .setPopExitAnim(R.anim.fade_out_long)
        .setPopUpTo(R.id.splachFragment, true)  // Удаление SplashFragment из стека
        .build()


}