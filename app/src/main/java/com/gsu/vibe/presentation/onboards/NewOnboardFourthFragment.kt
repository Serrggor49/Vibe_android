package com.gsu.vibe.presentation.onboards

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentNewOnboardFourthBinding
import com.gsu.vibe.navOptions
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewOnboardFourthFragment : Fragment() {


    val mainViewModel : MainViewModel by activityViewModels()

    lateinit var centralBall: ImageView

    private lateinit var _binding: FragmentNewOnboardFourthBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewOnboardFourthBinding.inflate(inflater, container, false)
        return _binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.visibilityBottomBarLivaData.postValue(false)
        init()

        binding.nextButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                val action = NewOnboardFourthFragmentDirections.actionNewOnboardFourthFragmentToNewOnboardSixFragment()
                view.findNavController().navigate(action, navOptionsLong)
            }
        }

    }

    fun init(){


        CoroutineScope(Dispatchers.Main).launch {

            val view = binding.cardView
            while (true){

                for(step in 0..200){
                    view.radius = step.toFloat()
                    delay(25)
                }

                delay(1000)

                for(step in 200 downTo 0){
                    view.radius = step.toFloat()
                    delay(20)
                }

                delay(1000)

            }
        }

    }


}

