package com.gsu.vibe.presentation.onboards

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentNewOnboardFirstBinding
import com.gsu.vibe.navOptions
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.presentation.MainViewModel
import java.lang.Math.cos
import java.lang.Math.sin

class NewOnboardFirstFragment : Fragment() {

    val mainViewModel: MainViewModel by activityViewModels()

    lateinit var centralBall: ImageView
    lateinit var orbitingBall: ImageView

    private lateinit var _binding: FragmentNewOnboardFirstBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewOnboardFirstBinding.inflate(inflater, container, false)
        return _binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        centralBall = binding.centralBall
        orbitingBall = binding.orbitBall

        initCentralBall()
        initOrbitBall()
        initCentralBall2()

        mainViewModel.visibilityBottomBarLivaData.postValue(false)

        binding.nextButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                val action = NewOnboardFirstFragmentDirections.actionNewOnboardFirstFragmentToNewOnboardSecondFragment()
                view.findNavController().navigate(action, navOptionsLong)
            }
        }

//        binding.nextButton.setOnClickListener {
//            val action = NewOnboardFirstFragmentDirections.actionNewOnboardFirstFragmentToNewOnboardSecondFragment()
//            view.findNavController().navigate(action)
//        }
    }


    var event = false

    fun initCentralBall() {
        val animScaleUpX = ObjectAnimator.ofFloat(centralBall, "scaleX", 1.4f)
        val animScaleUpY = ObjectAnimator.ofFloat(centralBall, "scaleY", 1.4f)
        val animScaleDownX = ObjectAnimator.ofFloat(centralBall, "scaleX", 1f)
        val animScaleDownY = ObjectAnimator.ofFloat(centralBall, "scaleY", 1f)

        animScaleUpX.duration = 3000
        animScaleUpY.duration = 3000
        animScaleDownX.duration = 3500
        animScaleDownY.duration = 3500

        animScaleUpX.interpolator = LinearInterpolator()
        animScaleUpY.interpolator = LinearInterpolator()
        animScaleDownX.interpolator = LinearInterpolator()
        animScaleDownY.interpolator = LinearInterpolator()

        val scaleUp = AnimatorSet()
        scaleUp.playTogether(animScaleUpX, animScaleUpY)

        val scaleDown = AnimatorSet()
        scaleDown.playTogether(animScaleDownX, animScaleDownY)

        val fullAnimation = AnimatorSet()
        fullAnimation.playSequentially(scaleUp, scaleDown)

        scaleUp.startDelay = 150  // задержка перед увеличением
        scaleDown.startDelay = 150  // задержка перед уменьшением

        fullAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                fullAnimation.start()

                if (event) {
                    orbitingBall.translationZ = 1f
                    centralBall.translationZ = 0f
                    event = !event
                } else {
                    orbitingBall.translationZ = 0f
                    centralBall.translationZ = 1f
                    event = !event
                }
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })

        // Задержка перед началом анимации
        centralBall.postDelayed({ fullAnimation.start() }, 500)
    }


    private var angle = 0.0 // начальный угол


    fun initOrbitBall() {

        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 5000L // скорость вращения
        valueAnimator.addUpdateListener {

            // координаты центрального ImageView
            val xCenter = centralBall.x + centralBall.width / 2
            val yCenter = centralBall.y + centralBall.height / 2

            // расстояние от центра
            val radius =
                centralBall.width / 2 + 100f // 100f - дополнительное расстояние, можно изменять

            // вычисляем новые координаты для вращающегося ImageView
            val x = xCenter + radius * cos(Math.toRadians(angle)).toFloat()
            val y = yCenter + radius * sin(Math.toRadians(angle)).toFloat()

            // устанавливаем новые координаты для вращающегося ImageView
            orbitingBall.x = x - orbitingBall.width / 2
            orbitingBall.y = y - orbitingBall.height / 2

            angle += 1 // шаг вращения
            if (angle >= 360) {
                angle = 0.0
            }
        }
        valueAnimator.start()
    }


    fun initCentralBall2() {

        val animScaleUpX = ObjectAnimator.ofFloat(orbitingBall, "scaleX", 3.1f)
        val animScaleUpY = ObjectAnimator.ofFloat(orbitingBall, "scaleY", 3.1f)
        val animScaleDownX = ObjectAnimator.ofFloat(orbitingBall, "scaleX", 1f)
        val animScaleDownY = ObjectAnimator.ofFloat(orbitingBall, "scaleY", 1f)

        animScaleUpX.duration = 3000
        animScaleUpY.duration = 3000
        animScaleDownX.duration = 4000
        animScaleDownY.duration = 4000

        animScaleUpX.interpolator = LinearInterpolator()
        animScaleUpY.interpolator = LinearInterpolator()
        animScaleDownX.interpolator = LinearInterpolator()
        animScaleDownY.interpolator = LinearInterpolator()

        val scaleUp = AnimatorSet()
        scaleUp.playTogether(animScaleUpX, animScaleUpY)

        val scaleDown = AnimatorSet()
        scaleDown.playTogether(animScaleDownX, animScaleDownY)

        val fullAnimation = AnimatorSet()
        fullAnimation.playSequentially(scaleUp, scaleDown)

        scaleUp.startDelay = 150  // задержка перед увеличением
        scaleDown.startDelay = 150  // задержка перед уменьшением

        fullAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                fullAnimation.start()
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }

        })

        // Задержка перед началом анимации
        orbitingBall.postDelayed({ fullAnimation.start() }, 500)
    }
}


//
//package com.gsu.vibe.presentation.onboards
//
//import android.animation.Animator
//import android.animation.AnimatorSet
//import android.animation.ObjectAnimator
//import android.animation.ValueAnimator
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.animation.LinearInterpolator
//import androidx.fragment.app.Fragment
//import com.gsu.vibe.databinding.FragmentNewOnboardFirstBinding
//import java.lang.Math.cos
//import java.lang.Math.sin
//
//class NewOnboardFirstFragment : Fragment() {
//
//    private lateinit var _binding: FragmentNewOnboardFirstBinding
//    private val binding
//        get() = _binding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        _binding = FragmentNewOnboardFirstBinding.inflate(inflater, container, false)
//        return _binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initCentralBall()
//        initOrbitBall()
//    }
//
//    fun initCentralBall() {
//        val imageView = binding.centralBall
//        val animScaleUpX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.5f)
//        val animScaleUpY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.5f)
//        val animScaleDownX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f)
//        val animScaleDownY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f)
//
//        animScaleUpX.duration = 2500
//        animScaleUpY.duration = 2500
//        animScaleDownX.duration = 3000
//        animScaleDownY.duration = 3000
//
//        animScaleUpX.interpolator = LinearInterpolator()
//        animScaleUpY.interpolator = LinearInterpolator()
//        animScaleDownX.interpolator = LinearInterpolator()
//        animScaleDownY.interpolator = LinearInterpolator()
//
//        val scaleUp = AnimatorSet()
//        scaleUp.playTogether(animScaleUpX, animScaleUpY)
//
//        val scaleDown = AnimatorSet()
//        scaleDown.playTogether(animScaleDownX, animScaleDownY)
//
//        val fullAnimation = AnimatorSet()
//        fullAnimation.playSequentially(scaleUp, scaleDown)
//
//        scaleUp.startDelay = 150  // задержка перед увеличением
//        scaleDown.startDelay = 150  // задержка перед уменьшением
//
//        fullAnimation.addListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(p0: Animator) {
//            }
//
//            override fun onAnimationEnd(p0: Animator) {
//                fullAnimation.start()
//            }
//
//            override fun onAnimationCancel(p0: Animator) {
//            }
//
//            override fun onAnimationRepeat(p0: Animator) {
//            }
//
//        })
//
//        // Задержка перед началом анимации
//        imageView.postDelayed({ fullAnimation.start() }, 500)
//    }
//
//
//    private var angle = 0.0 // начальный угол
//
//
//    fun initOrbitBall() {
//
//        val centralImageView = binding.centralBall
//        val orbitingImageView = binding.orbitBall
//
//
//        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
//        valueAnimator.repeatCount = ValueAnimator.INFINITE
//        valueAnimator.interpolator = LinearInterpolator()
//        valueAnimator.duration = 3000L // скорость вращения
//        valueAnimator.addUpdateListener {
//
//            // координаты центрального ImageView
//            val xCenter = centralImageView.x + centralImageView.width / 2
//            val yCenter = centralImageView.y + centralImageView.height / 2
//
//            // расстояние от центра
//            val radius =
//                centralImageView.width / 2 + 100f // 100f - дополнительное расстояние, можно изменять
//
//            // вычисляем новые координаты для вращающегося ImageView
//            val x = xCenter + radius * cos(Math.toRadians(angle)).toFloat()
//            val y = yCenter + radius * sin(Math.toRadians(angle)).toFloat()
//
//            // устанавливаем новые координаты для вращающегося ImageView
//            orbitingImageView.x = x - orbitingImageView.width / 2
//            orbitingImageView.y = y - orbitingImageView.height / 2
//
//            angle += 3 // шаг вращения
//            if (angle >= 360) {
//                angle = 0.0
//            }
//        }
//        valueAnimator.start()
//    }
//}
