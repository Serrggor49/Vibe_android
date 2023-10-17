package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentOnboardSecondBinding
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.setVibro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OnboardSecondFragment : Fragment() {

    private lateinit var _binding: FragmentOnboardSecondBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardSecondBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    @SuppressLint("ClickableViewAccessibility")
    fun init() {

        binding.nextButton.setOnTouchListener { v, event -> openSecondOnboard(v, event) }


        initPlayer()
    }


    fun initPlayer() {
        val videoView = binding.videoPlayer
        val videoUri: Uri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.balls_w)

        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            mp.setOnCompletionListener {
                mp.start()
            }
        }

        videoView.start()

        CoroutineScope(Dispatchers.IO).launch {
            delay(500)

            CoroutineScope(Dispatchers.Main).launch {
                videoView.visibility = View.VISIBLE

            }
        }




}

fun openSecondOnboard(v: View, event: MotionEvent): Boolean {
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
                    val action =
                        OnboardSecondFragmentDirections.actionOnboardSecondFragmentToPaywallFragment()
                    view?.findNavController()?.navigate(action, navOptions)
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


    val navOptions: NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in_long)
        .setExitAnim(R.anim.fade_out_long)
        .setPopEnterAnim(R.anim.fade_in_long)
        .setPopExitAnim(R.anim.fade_out_long)
        .setPopUpTo(R.id.onboardSecondFragment, true)  // Удаление SplashFragment из стека
        .build()


}
