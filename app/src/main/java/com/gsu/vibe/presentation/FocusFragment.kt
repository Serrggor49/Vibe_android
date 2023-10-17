package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentFocusBinding
import com.gsu.vibe.setVibro

class FocusFragment : Fragment() {

    val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
    val transitionDuration = 20000L
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

    override fun onResume() {
        super.onResume()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)
        mainViewmodel.currentType = MainViewModel.CurrentType.FOR_FOCUS
        (activity as MainActivity).updateBottomButtons()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun init() {

        if (mainViewmodel.getSubStatus()) binding.closeAdButton.visibility = View.GONE

        binding.closeAdButton.setOnClickListener {
            mainViewmodel.openSubscribeFragmentLivaData.postValue(true)
            mainViewmodel.visibilityBottomBarLivaData.postValue(false)
        }
        // binding.image2.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
       // binding.image311.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
     //   binding.image411.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
    //    binding.image25112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
    //    binding.image3112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
     //   binding.image21.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
     //   binding.image2511222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
   //     binding.image25112223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
   //     binding.image31122222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
      //  binding.image41122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
   //     binding.image31122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
  //      binding.image411223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))

        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }


        binding.focus011.setOnTouchListener { v, event -> openPlayer(v, event, "focus011") }
        binding.focus021.setOnTouchListener { v, event -> openPlayer(v, event, "focus021") }
        binding.focus022.setOnTouchListener { v, event -> openPlayer(v, event, "focus022") }
        binding.focus031.setOnTouchListener { v, event -> openPlayer(v, event, "focus031") }
        binding.focus032.setOnTouchListener { v, event -> openPlayer(v, event, "focus032") }
        binding.focus041.setOnTouchListener { v, event -> openPlayer(v, event, "focus041") }
        binding.focus051.setOnTouchListener { v, event -> openPlayer(v, event, "focus051") }
        binding.focus052.setOnTouchListener { v, event -> openPlayer(v, event, "focus052") }
        binding.focus061.setOnTouchListener { v, event -> openPlayer(v, event, "focus061") }
        binding.focus062.setOnTouchListener { v, event -> openPlayer(v, event, "focus062") }
        binding.focus071.setOnTouchListener { v, event -> openPlayer(v, event, "focus071") }
        binding.focus072.setOnTouchListener { v, event -> openPlayer(v, event, "focus072") }

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
                            val action =
                                FocusFragmentDirections.actionFocusFragmentToInterstitialAdFragment()
                            view?.findNavController()?.navigate(action)
                        }
                        else{
                            val action =
                                FocusFragmentDirections.actionFocusFragmentToMediaPlayerServiceFragment()
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