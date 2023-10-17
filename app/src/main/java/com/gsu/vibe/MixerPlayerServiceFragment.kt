package com.gsu.vibe

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gsu.vibe.data.models.TrackForServiceModel
import com.gsu.vibe.databinding.FragmentMixerPlayerBinding
import com.gsu.vibe.presentation.MainViewModel
import com.gsu.vibe.services.MediaPlayerService

class MixerPlayerServiceFragment : Fragment() {

    val mainViewModel: MainViewModel by activityViewModels()
    private val pref = "android.resource://"

    private var currentTime = 0

    lateinit var timer: CountDownTimer

    var time = 0L

    private lateinit var _binding: FragmentMixerPlayerBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMixerPlayerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.registerReceiver(receiver, IntentFilter().apply {
            addAction(V_ACTION_PLAY_FOR_FRAGMENT)
            addAction(V_ACTION_PAUSE_FOR_FRAGMENT)
        })

        initBackPress()

        binding.playButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).start()
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                    val mixerSound = TrackForServiceModel(
                        soundImage = R.drawable.focus_01_1f_prev,
                        soundName = (R.string.mixer),
                        soundPaths = arrayOf(
                            (pref + context?.packageName + "/" + mainViewModel.animalSound.sound),
                            (pref + context?.packageName + "/" + mainViewModel.natureSound.sound),
                            (pref + context?.packageName + "/" + mainViewModel.instrumentsSound.sound),
                            (pref + context?.packageName + "/" + mainViewModel.binuaSound.sound)
                        ),
                        soundVolume = arrayOf(
                            mainViewModel.animalSoundVolume,
                            mainViewModel.natureSoundVolume,
                            mainViewModel.instrumentsVolume,
                            mainViewModel.binuaSoundVolume
                        ),
                        soundType = R.string.mixer,
                        timer = time.toInt() - currentTime,
                        duration = time.toInt(),
                    )

                    val playIntent =
                        Intent(requireActivity(), MediaPlayerService::class.java).apply {
                            action = V_CHANGE_PLAYER_STATE
                            putExtra(trackForServiceModelKey, mixerSound)
                        }
                    requireActivity().startService(playIntent)
                    true
                }

                else -> false
            }
        }

        time = mainViewModel.timeForMixerPlayerInMs.toLong()
        //initTimer(time)
        binding.timerText.text = getFormtTime(time.toInt())
        binding.playButtonText.text = getString(R.string.start)

        binding.closeButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).start()
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(200).start()

                    if (::timer.isInitialized) {
                        timer.cancel()
                    }
                    val closeServiceIntent =
                        Intent(requireContext(), MediaPlayerService::class.java).apply {
                            action = V_ACTION_CLOSE
                        }
                    context?.unregisterReceiver(receiver)

                    requireContext().startService(closeServiceIntent)
                    NotificationManagerCompat.from(requireContext()).cancelAll() // спрятать все уведомления

                    mainViewModel.visibilityBottomBarLivaData.postValue(true)
                    findNavController().popBackStack()
                    true
                }

                else -> false
            }
        }


//        binding.closeButton.setOnClickListener {
//            timer.cancel()
//            val closeServiceIntent =
//                Intent(requireContext(), MediaPlayerService::class.java).apply {
//                    action = V_ACTION_CLOSE
//                }
//            context?.unregisterReceiver(receiver)
//
//            requireContext().startService(closeServiceIntent)
//            NotificationManagerCompat.from(requireContext()).cancelAll() // спрятать все уведомления
//
//            findNavController().popBackStack()
//        }

        val videoView = binding.videoPlayer
        val videoUri: Uri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.balls_w)
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            mp.start()
            mp.pause()
        }
    }

    fun initTimer(timeToFinish: Long) {

        var t = timeToFinish

        var animalSoundVolume = mainViewModel.animalSoundVolume
        var natureSoundVolume = mainViewModel.natureSoundVolume
        var instrumentsSoundVolume = mainViewModel.instrumentsVolume
        var binuaSoundVolume = mainViewModel.binuaSoundVolume

        timer = object : CountDownTimer(timeToFinish, 1000) {

            override fun onTick(millisUntilFinished: Long) {

              //  currentTime += 1000
                t += 1000

                binding.timerText.text = getFormtTime(millisUntilFinished.toInt())
                time = millisUntilFinished
                if (millisUntilFinished < 15000) {
                    animalSoundVolume -= 0.065f
                    Log.d("MyLogs444", "animalSoundVolume = $animalSoundVolume")
                    if (animalSoundVolume < 0) animalSoundVolume = 0f

                    natureSoundVolume -= 0.065f
                    if (natureSoundVolume < 0) natureSoundVolume = 0f

                    instrumentsSoundVolume -= 0.065f
                    if (instrumentsSoundVolume < 0) instrumentsSoundVolume = 0f

                    binuaSoundVolume -= 0.065f
                    if (binuaSoundVolume < 0) binuaSoundVolume = 0f

                }
            }

            override fun onFinish() {
                findNavController().popBackStack()
                mainViewModel.visibilityBottomBarLivaData.postValue(true)
            }
        }.start()
    }


    fun initBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    if (::timer.isInitialized) {
                        timer.cancel()
                    }
                    val closeServiceIntent =
                        Intent(requireContext(), MediaPlayerService::class.java).apply {
                            action = V_ACTION_CLOSE
                        }
                    requireContext().startService(closeServiceIntent)
                    NotificationManagerCompat.from(requireContext()).cancelAll() // спрятать все уведомления
                    context?.unregisterReceiver(receiver)

                    findNavController().popBackStack()
                    mainViewModel.visibilityBottomBarLivaData.postValue(true)
                }
            })
    }


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            when (intent?.action) {
                V_ACTION_PAUSE_FOR_FRAGMENT -> {
                    Log.d("MyLogs88", "V_ACTION_PAUSE_FOR_FRAGMENT")
                    binding.playButtonText.text = getString(R.string.pause)
                    if (::timer.isInitialized) {
                        timer.cancel()
                    }
                    initTimer(intent.getIntExtra(timerKey, -1).toLong())
                    binding.videoPlayer.start()

                    //timer.start()
                }

                V_ACTION_PLAY_FOR_FRAGMENT -> {
                    Log.d("MyLogs88", "V_ACTION_PLAY_FOR_FRAGMENT")
                    binding.playButtonText.text = getString(R.string.start)

                    if (::timer.isInitialized) {
                        timer.cancel()
                    }
                    binding.videoPlayer.pause()

                }
            }
        }
    }


}