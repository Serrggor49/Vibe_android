package com.gsu.vibe

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.media.MediaPlayer
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.gsu.vibe.databinding.FragmentMixerPlayerBinding
import com.gsu.vibe.presentation.MainViewModel

class MixerPlayerFragment : Fragment() {

    val mainViewModel: MainViewModel by activityViewModels()
    val stateLiveData = MutableLiveData(true)  // true - плеер в состоянии паузы, false - играет

    lateinit var timer: CountDownTimer

    var time = 0L

    lateinit var mediaPlayer1: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    lateinit var mediaPlayer3: MediaPlayer
    lateinit var mediaPlayer4: MediaPlayer

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


        initBackPress()
        initPlayers()

        binding.playButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(200).start()
                    //stateLiveData.postValue(!stateLiveData.value!!)
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                    stateLiveData.postValue(!stateLiveData.value!!)
                    true
                }

                else -> false
            }
        }

        time = mainViewModel.timeForMixerPlayerInMs.toLong()
        initTimer(time)
        binding.timerText.text = getFormtTime(time)
        initState()
        binding.playButtonText.text = getString(R.string.start)


        binding.closeButton.setOnClickListener {
            timer.cancel()
            val players = arrayOf(mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4)
            players.forEach { it.release() }
            findNavController().popBackStack()
        }

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

    fun initState() {

        val players = arrayOf(mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4)
        players.forEach { it.start() }
        players.forEach { it.pause() }

        stateLiveData.observe(requireActivity()) { state ->

            if (state) {  // пауза
                players.forEach { it.pause() }
                binding.playButtonText.text = getString(R.string.cont)
                binding.videoPlayer.pause()
                timer.cancel()
            } else {  // воспроизведение
                players.forEach { it.start() }
                binding.playButtonText.text = getString(R.string.pause)
                binding.videoPlayer.start()
                initTimer(time)
            }

        }
    }


    // передаем время в мс, получаем стрингу со временем в удобном для отображения формате
    fun getFormtTime(ms: Long): String {


        val hour = (ms / 1000 / 60 / 60).toString()
        var minutes = (ms / 1000 / 60 % 60).toString()
        var second = (ms / 1000 % 60).toString()

        if (minutes.length == 1) minutes = "0$minutes"
        if (second.length == 1) second = "0$second"

        var res = "$hour:$minutes:$second"

        if (hour == "0") {
            res = "$minutes:$second"
        }
        return res
    }

    fun initTimer(timeToFinish: Long) {

        var animalSoundVolume = mainViewModel.animalSoundVolume
        var natureSoundVolume = mainViewModel.natureSoundVolume
        var instrumentsSoundVolume = mainViewModel.instrumentsVolume
        var binuaSoundVolume = mainViewModel.binuaSoundVolume

        timer = object : CountDownTimer(timeToFinish, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                binding.timerText.text = getFormtTime(millisUntilFinished)
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

                    mediaPlayer1.setVolume(animalSoundVolume, animalSoundVolume)
                    mediaPlayer2.setVolume(natureSoundVolume, natureSoundVolume)
                    mediaPlayer3.setVolume(instrumentsSoundVolume, instrumentsSoundVolume)
                    mediaPlayer4.setVolume(binuaSoundVolume, binuaSoundVolume)
                }
            }

            override fun onFinish() {
                //currentVolume = 1f
                val players = arrayOf(mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4)
                players.forEach { it.release() }

                findNavController().popBackStack()
                mainViewModel.visibilityBottomBarLivaData.postValue(true)
            }
        }.start()
    }

    fun initPlayers() {

        mediaPlayer1 = MediaPlayer.create(requireContext(), mainViewModel.animalSound.sound)
        mediaPlayer1.setVolume(mainViewModel.animalSoundVolume, mainViewModel.animalSoundVolume)

        mediaPlayer2 = MediaPlayer.create(requireContext(), mainViewModel.natureSound.sound)
        mediaPlayer2.setVolume(mainViewModel.natureSoundVolume, mainViewModel.natureSoundVolume)

        mediaPlayer3 = MediaPlayer.create(requireContext(), mainViewModel.instrumentsSound.sound)
        mediaPlayer3.setVolume(mainViewModel.instrumentsVolume, mainViewModel.instrumentsVolume)

        mediaPlayer4 = MediaPlayer.create(requireContext(), mainViewModel.binuaSound.sound)
        mediaPlayer4.setVolume(mainViewModel.binuaSoundVolume, mainViewModel.binuaSoundVolume)

    }

    fun initBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    timer.cancel()
                    //binding.videoPlayer.release()

                    val players = arrayOf(mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4)
                    players.forEach { it.release() }

                    findNavController().popBackStack()
                    mainViewModel.visibilityBottomBarLivaData.postValue(true)
                }
            })
    }

}