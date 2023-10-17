package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentNatureBinding
import com.gsu.vibe.setVibro

class NatureFragment : Fragment() {

    val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
    val transitionDuration = 20000L
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

    override fun onResume() {
        super.onResume()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)
        mainViewmodel.currentType = MainViewModel.CurrentType.NATURE
        (activity as MainActivity).updateBottomButtons()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun init() {

        if (mainViewmodel.getSubStatus()) binding.closeAdButton.visibility = View.GONE

        binding.closeAdButton.setOnClickListener {
            mainViewmodel.openSubscribeFragmentLivaData.postValue(true)
            mainViewmodel.visibilityBottomBarLivaData.postValue(false)
        }
//        binding.image2511222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image25112223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image31122222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image41122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image31122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image411223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image2.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image311.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image41133.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image25112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image3112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image21.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image31155.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image411.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image311552.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))

        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }


        binding.nature011.setOnTouchListener { v, event -> openPlayer(v, event, "nature011") }
        binding.nature012.setOnTouchListener { v, event -> openPlayer(v, event, "nature012") }
        binding.nature021.setOnTouchListener { v, event -> openPlayer(v, event, "nature021") }
        binding.nature022.setOnTouchListener { v, event -> openPlayer(v, event, "nature022") }
        binding.nature031.setOnTouchListener { v, event -> openPlayer(v, event, "nature031") }
        binding.nature032.setOnTouchListener { v, event -> openPlayer(v, event, "nature032") }
        binding.nature041.setOnTouchListener { v, event -> openPlayer(v, event, "nature041") }
        binding.nature051.setOnTouchListener { v, event -> openPlayer(v, event, "nature051") }
        binding.nature052.setOnTouchListener { v, event -> openPlayer(v, event, "nature052") }
        binding.nature061.setOnTouchListener { v, event -> openPlayer(v, event, "nature061") }
        binding.nature062.setOnTouchListener { v, event -> openPlayer(v, event, "nature062") }
        binding.nature071.setOnTouchListener { v, event -> openPlayer(v, event, "nature071") }
        binding.nature081.setOnTouchListener { v, event -> openPlayer(v, event, "nature081") }
        binding.nature082.setOnTouchListener { v, event -> openPlayer(v, event, "nature082") }
        binding.nature091.setOnTouchListener { v, event -> openPlayer(v, event, "nature091") }

        if(Repository.getFavoritesSounds(requireContext()).isEmpty()){
            binding.openFavoritesButton.visibility = View.GONE
        }
        else{
            binding.openFavoritesButton.visibility = View.VISIBLE
        }

    }


    fun openPlayer(v: View, event: MotionEvent, currentSound: String): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.animate().scaleX(0.96f).scaleY(0.96f).setDuration(200).start()
            }
            MotionEvent.ACTION_UP -> {
                // Пользователь отпустил кнопку
                setVibro()
                v.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .withEndAction {
                        mainViewmodel.setCurrentSound(currentSound)

                        if (mainViewmodel.showAd() && !mainViewmodel.getSubStatus()) {
                            val action = NatureFragmentDirections.actionNatureFragmentToInterstitialAdFragment()
                            view?.findNavController()?.navigate(action)
                        }
                        else{
                            val action =
                                NatureFragmentDirections.actionNatureFragmentToMediaPlayerServiceFragment()
                            view?.findNavController()?.navigate(action)
                        }
                    }
                    .start()
                //Log.d("MY_l124", "ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
            MotionEvent.ACTION_MOVE -> {
            }

            MotionEvent.ACTION_BUTTON_RELEASE -> {
            }
        }
        return true
    }

}