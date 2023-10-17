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
import com.gsu.vibe.databinding.FragmentMeditationBinding
import com.gsu.vibe.setVibro

class MeditationFragment : Fragment() {

    val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
    val transitionDuration = 20000L
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


    override fun onResume() {
        super.onResume()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)
        mainViewmodel.currentType = MainViewModel.CurrentType.FOR_MEDITATION
        (activity as MainActivity).updateBottomButtons()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun init() {

        //val playIntent = Intent(requireContext(), MediaPlayerService::class.java).apply {
        if (mainViewmodel.getSubStatus()) binding.closeAdButton.visibility = View.GONE

        binding.closeAdButton.setOnClickListener {
            mainViewmodel.openSubscribeFragmentLivaData.postValue(true)
            mainViewmodel.visibilityBottomBarLivaData.postValue(false)
        }
        //binding.image2.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        //binding.image2511222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        //binding.image25112223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
 //       binding.image31122222.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        //binding.image41122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
        //binding.image31122.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image411223.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image21.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image4113334.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image311.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image41177.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image311277.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))
//        binding.image25112.setTransitionGenerator(RandomTransitionGeneratorForPrev(transitionDuration, ACCELERATE_DECELERATE))


        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }

        binding.meditation011.setOnTouchListener { v, event -> openPlayer(v, event, "meditation011") }
        binding.meditation021.setOnTouchListener { v, event -> openPlayer(v, event, "meditation021") }
        binding.meditation022.setOnTouchListener { v, event -> openPlayer(v, event, "meditation022") }
        binding.meditation031.setOnTouchListener { v, event -> openPlayer(v, event, "meditation031") }
        binding.meditation032.setOnTouchListener { v, event -> openPlayer(v, event, "meditation032") }
        binding.meditation041.setOnTouchListener { v, event -> openPlayer(v, event, "meditation041") }
        binding.meditation042.setOnTouchListener { v, event -> openPlayer(v, event, "meditation042") }
        binding.meditation051.setOnTouchListener { v, event -> openPlayer(v, event, "meditation051") }
        binding.meditation061.setOnTouchListener { v, event -> openPlayer(v, event, "meditation061") }
        binding.meditation062.setOnTouchListener { v, event -> openPlayer(v, event, "meditation062") }
        binding.meditation071.setOnTouchListener { v, event -> openPlayer(v, event, "meditation071") }
        binding.meditation081.setOnTouchListener { v, event -> openPlayer(v, event, "meditation081") }
        binding.meditation082.setOnTouchListener { v, event -> openPlayer(v, event, "meditation082") }

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
                            val action = MeditationFragmentDirections.actionMeditationFragmentToInterstitialAdFragment()
                            view?.findNavController()?.navigate(action)
                        }
                        else{
                            val action =
                                MeditationFragmentDirections.actionMeditationFragmentToMediaPlayerServiceFragment()
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