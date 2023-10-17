package com.gsu.vibe.presentation.onboards

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentNewOnboardSecondBinding
import com.gsu.vibe.navOptions
import com.gsu.vibe.navOptionsLong


class NewOnboardSecondFragment : Fragment() {

    lateinit var centralBall: ImageView
    lateinit var orbitingBall: ImageView

    private lateinit var _binding: FragmentNewOnboardSecondBinding
    private val binding
        get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewOnboardSecondBinding.inflate(inflater, container, false)
        return _binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.nextButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                val action = NewOnboardSecondFragmentDirections.actionNewOnboardSecondFragmentToNewOnboardThirdFragment()
                view.findNavController().navigate(action, navOptionsLong)
            }
        }
    }

    fun init(){
        val myImageView = binding.centralBall

        val rotateAnimator = ObjectAnimator.ofFloat(myImageView, "rotation", 0f, 360f).apply {
            duration = 8500
            interpolator = LinearInterpolator()
        }

        val scaleUpX = ObjectAnimator.ofFloat(myImageView, "scaleX", 1f, 1.22f)
        val scaleUpY = ObjectAnimator.ofFloat(myImageView, "scaleY", 1f, 1.22f)
        val scaleDownX = ObjectAnimator.ofFloat(myImageView, "scaleX", 1.22f, 1f)
        val scaleDownY = ObjectAnimator.ofFloat(myImageView, "scaleY", 1.22f, 1f)

        val scaleUpSet = AnimatorSet().apply {
            play(scaleUpX).with(scaleUpY)
            duration = 4000
        }

        val scaleDownSet = AnimatorSet().apply {
            play(scaleDownX).with(scaleDownY)
            duration = 4000
        }

        val scaleSet = AnimatorSet().apply {
            playSequentially(scaleUpSet, scaleDownSet)
        }

        val animatorSet = AnimatorSet().apply {
            playTogether(rotateAnimator, scaleSet)
        }

        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animation.start()
            }
        })

        animatorSet.start()

    }

}