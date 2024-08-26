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
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.TrackForServiceModel
import com.gsu.vibe.databinding.FragmentMediaPlayerServiceBinding
import com.gsu.vibe.presentation.MainViewModel
import com.gsu.vibe.services.DownloadAudioFromUrlNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MediaPlayerServiceFragment : Fragment() {

    private val mediaPath = "/data/data/com.gsu.vibe/files/"
    private val mainViewModel: MainViewModel by activityViewModels()
    private var mpIsReady = false // если песня загружена, то true. Чтобы при отсутствии интернета, загруженная песня не слетала
    private var playerIsPlaing = false // трек не воспроизводится

    private lateinit var timer: CountDownTimer
    private var trackTime = 0  // продолжительность воспроизведения, которую установил пользователь
    private var currentTime = 0

    private lateinit var _binding: FragmentMediaPlayerServiceBinding
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMediaPlayerServiceBinding.inflate(inflater, container, false)
        return _binding.root
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.registerReceiver(receiver, IntentFilter().apply {
            addAction(V_ACTION_PLAY_FOR_FRAGMENT)
            addAction(V_ACTION_PAUSE_FOR_FRAGMENT)
        })

        mainViewModel.visibilityBottomBarLivaData.postValue(false)

        initBackgroundsAndFields()
        initTimerPanel()
        initPlayButton()

        onBackOverride()
        initFavoriteButton()
        initInternetDetector()
    }

    fun initInternetDetector() {

        if (mainViewModel.isOnline() || fileExists()) {
            binding.timerView.visibility = View.VISIBLE
            binding.setTimeButton.visibility = View.VISIBLE
            binding.networkProblems.visibility = View.INVISIBLE
        } else {
            binding.timerView.visibility = View.INVISIBLE
            binding.setTimeButton.visibility = View.INVISIBLE

            binding.networkProblems.visibility = View.VISIBLE
        }
    }

    fun fileExists(): Boolean {
        val file = File("$mediaPath${mainViewModel.currentSound.name}")
        return file.exists()
    }

    fun initBackgroundsAndFields(){

        val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
        val generator = com.gsu.vibe.services.RandomTransitionGenerator(25000, ACCELERATE_DECELERATE)
        binding.background.setTransitionGenerator(generator)

        binding.title.text = getString(mainViewModel.currentSound.title).replace("\n", " ")
        binding.subTitle.text = getString(mainViewModel.currentSound.subtitle)
        binding.background.setImageResource(mainViewModel.currentSound.background)
        binding.foreground.setImageResource(mainViewModel.currentSound.foreground)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun initTimerPanel() {
        binding.resetTimeButton.setOnTouchListener { v, event -> buttonPress(v, event, ::resetTimer ) }
        binding.setTimeButton.setOnTouchListener { v, event -> buttonPress(v, event, ::confirmTimer ) }
    }

    fun initPlayButton(){

//        val uri = Uri.parse("$mediaPath${mainViewModel.currentSound.name}").toString()
//        binding.playButton.setOnClickListener {
//            val playIntent = Intent(requireActivity(), MediaPlayerService::class.java).apply {
//                action = V_CHANGE_PLAYER_STATE
//                 putExtra(trackForServiceModelKey, TrackForServiceModel(
//                    soundImage = mainViewModel.currentSound.preview,
//                    soundName = mainViewModel.currentSound.title,
//                    soundPaths = arrayOf(uri),
//                    soundVolume = arrayOf(1f),
//                    soundType = mainViewModel.currentSound.subtitle,
//                    timer = trackTime - currentTime,
//                    duration = trackTime))
//            }
//            requireContext().startService(playIntent)
//        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            Log.d("MyLogs093", intent?.action.toString())
            when (intent?.action) {
                V_ACTION_PLAY_FOR_FRAGMENT -> {
                    binding.playButton.setImageResource(R.drawable.ic_play_buuton_3)
                    if(::timer.isInitialized){
                        timer.cancel()
                    }
                    playerIsPlaing = false
                    //initTimer(intent.getIntExtra(timerKey, -1))
                }
                V_ACTION_PAUSE_FOR_FRAGMENT -> {
                    binding.playButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
                    playerIsPlaing = true

                    initTimer(intent.getIntExtra(timerKey, -1))
                    timer.start()
                }
            }
        }
    }

    fun initSeekBar(){
        trackTime = ((binding.numberPickerHours.value * 60) + binding.numberPickerMinutes.value) * 60 * 1000 + binding.numberPickerSec.value * 1000
        binding.trackTimeText.text = getFormtTime(trackTime)
        binding.seekBar.max = trackTime

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                binding.durationTimeText.text = getFormtTime(progress)
                currentTime = progress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //timer.cancel()


                // на случай, если seekBar переместится, когда плеер не будет в состоянии паузы
//                if(playerIsPlaing) {
//
//                    val playIntent = Intent(requireActivity(), MediaPlayerService::class.java).apply {
//                        //action = V_ACTION_PLAY_FOR_FRAGMENT
//                        putExtra(timerKey, (trackTime - currentTime))
//                        Log.d("MyLogs332", "(trackTime - currentTime) = ${(trackTime - currentTime)}")
//
//                    }
//                    requireContext().startService(playIntent)
//                    Log.d("MyLogs332", "onStopTrackingTouch")
//
//                    initTimer((trackTime - currentTime))
//                    timer.start()
//                }


//                val uri = Uri.parse("$mediaPath${mainViewModel.currentSound.name}").toString()
//                    val playIntent = Intent(requireActivity(), MediaPlayerService::class.java).apply {
//                        action = V_ACTION_CHANGE_TIME
//                        putExtra(trackForServiceModelKey, TrackForServiceModel(
//                            soundImage = mainViewModel.currentSound.preview,
//                            soundName = mainViewModel.currentSound.title,
//                            soundPaths = arrayOf(uri),
//                            soundVolume = arrayOf(1f),
//                            soundType = mainViewModel.currentSound.subtitle,
//                            timer = trackTime - currentTime,
//                            duration = trackTime))
//                    }
//                    requireContext().startService(playIntent)


            }
        })
    }

    fun resetTimer() {
        binding.numberPickerHours.value = 0
        binding.numberPickerMinutes.value = 10
        binding.numberPickerSec.value = 0
    }

    fun confirmTimer(){
        binding.timerView.visibility = View.GONE
        binding.setTimeButton.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        initSeekBar()
        downloadTrack()
    }

    fun downloadTrack() {
        CoroutineScope(Dispatchers.Main).launch {
            if (!File("$mediaPath${mainViewModel.currentSound.name}").exists()) {  // если трека с таким имени нет в памяти, то скачиваем
                val job = CoroutineScope(Dispatchers.IO).launch {
                    try {
                        DownloadAudioFromUrlNew.download(
                            fileName = mainViewModel.currentSound.name,
                            urlString = mainViewModel.currentSound.url,
                            context = requireContext()
                        )
                    } catch (e: java.lang.Exception) { // без этого, если будет отключение интернета во время скачивания, приложение вылетает
                        CoroutineScope(Dispatchers.Main).launch {
                            requireActivity().onBackPressed()
                        }
                    }
                }
                job.join()
            }
            initPlayer()
        }
    }



    fun initPlayer() {
        binding.favoriteButton.visibility = View.VISIBLE
        binding.playerGroup.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.percentage.visibility = View.GONE
        mpIsReady = true
    }

    fun initFavoriteButton() {

        var status =
            Repository.getFavoritesStatus(requireContext(), mainViewModel.currentSound.name)

        if (status) {
            binding.favoriteButton.setImageResource(R.drawable.player_favorites_true)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.player_favorites_false)
        }

        binding.favoriteButton.setOnClickListener {
            status = !status
            Repository.addStatusSoundToFavorites(requireContext(),
                mainViewModel.currentSound.name,
                status)
            initFavoriteButton()
        }
    }

    fun initTimer(timeMs: Int) {
        Log.d("MyLogs332", "initTimer")

        if (::timer.isInitialized){
            timer.cancel()
            Log.d("MyLogs332", "timer.cancel()")
        }
        if (timeMs != -1) {
            Log.d("MyLogs332", "timeMs != -1")

            timer = object : CountDownTimer(timeMs.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    currentTime += 1000
                    binding.seekBar.progress = currentTime
                }

                override fun onFinish() {
                    //findNavController().popBackStack()
                    findNavController().popBackStack(R.id.sleepFragment, false)
                    mainViewModel.visibilityBottomBarLivaData.postValue(true)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::timer.isInitialized) timer.cancel()
    }

    fun onBackOverride(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                val closeServiceIntent = Intent(requireContext(), MediaPlayerService::class.java).apply {
//                    action = V_ACTION_CLOSE
//                }
              //  requireContext().startService(closeServiceIntent)
                NotificationManagerCompat.from(requireContext()).cancelAll() // спрятать все уведомления
                findNavController().popBackStack(R.id.sleepFragment, false)
            }
        })
    }

}