package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.gsu.vibe.ConnectionLiveData
import com.gsu.vibe.R
import com.gsu.vibe.databinding.FragmentPlayerBinding
import com.gsu.vibe.services.OnClearFromRecentService

private lateinit var connectionLiveData: ConnectionLiveData

class PlayerFragment : Fragment() {

    var currentVolume = 1.0f

    lateinit var timer : CountDownTimer
    var trackTime = 0
    var currentTime = 0


    val mutableLiveData = MutableLiveData(false)
    val mainViewModel : MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentPlayerBinding
    private val binding
        get() = _binding

    var mp: MediaPlayer? = null
    var mpIsReady = false // если песня загружена, то true. Чтобы при отключении интернета, загруженная песня не слетала
    var currentSong = mutableListOf(R.raw.ariya)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.visibilityBottomBarLivaData.postValue(false)
        initSetTimeButtom()
        initBackground()
        initFields()
        initBackPress()
        initInternetDetector()
//        initBackPress()
//        initPlayer(currentSong[0])
//        initFields()

//        ininTimePicker()


    }

    fun initSetTimeButtom(){

        binding.setTimeButton.setOnClickListener{

            binding.timerView.visibility = View.GONE
            binding.setTimeButton.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            initSeekBar()

            initPlayer(currentSong[0])
        }

    }

    fun initFields(){
        binding.title.text = mainViewModel.currentSound.title
        binding.subTitle.text = mainViewModel.currentSound.subtitle
       // binding.background.setBackgroundResource(mainViewModel.currentSound.background)
        binding.background.setImageResource(mainViewModel.currentSound.background)
        //binding.foreground.setBackgroundResource(mainViewModel.currentSound.foreground)
        binding.foreground.setImageResource(mainViewModel.currentSound.foreground)
    }

    @SuppressLint("SetTextI18n")
    fun initPlayer(id: Int) {

        initTimer(trackTime)
        mp = MediaPlayer()
        mp?.isLooping = true
        mp?.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(mainViewModel.currentSound.url)
            prepareAsync()

            binding.percentage.visibility = View.VISIBLE

//            setOnPreparedListener { // файл готов к воспроизведению, показываем элементы управления
//                binding.playerGroup.visibility = View.VISIBLE
//                binding.progressBar.visibility = View.GONE
//                mpIsReady = true
//            }


            mp?.setOnBufferingUpdateListener { mp, percent ->

                binding.percentage.text = "${percent}%"
                if (percent == 100){
                    binding.playerGroup.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    mpIsReady = true
                    binding.percentage.visibility = View.GONE
                }

            }
        }

//        mp?.setDataSource("https://firebasestorage.googleapis.com/v0/b/vibe-3bd24.appspot.com/o/Ария%20-%20Беспечный%20Ангел%20(2).mp3?alt=media&token=e9ae6b4c-bcb7-4625-a963-3cee7e0151ab")
//       // mp?.setDataSource("https://drive.google.com/file/d/19oj0uCkvnVxV_JSYm_vRpDWfOosiiJj5/view?usp=sharing")
//       // mp?.setDataSource("https://cdn.mp3xa.cc/1Utbmu06ADtt2p7-ZzRNOg/1665302993/L21wMy8yMDIwLzA0L9CQ0YDQuNGPIC0g0JHQtdGB0L_QtdGH0L3Ri9C5INCQ0L3Qs9C10LsubXAz")


        mutableLiveData.observe(requireActivity()) {
            if (it) {
                binding.playButton.setImageResource(R.drawable.ic_pause_button)
                timer.start()
            } else {
                binding.playButton.setImageResource(R.drawable.ic_play_button)
                timer.cancel()
            }
        }

        binding.playButton.setOnClickListener {
            if (mutableLiveData.value == false) {
                if (mp == null) {
                    mp = MediaPlayer.create(requireActivity(), id)
                }
                mp?.start()
                mutableLiveData.postValue(true)
              //  initSeekBar()
            } else {
                if (mp != null) {
                    mp?.pause()
                }
                mutableLiveData.postValue(false)
            }

            mp?.setOnCompletionListener {
                mutableLiveData.postValue(false)
            }

            createChannel()

        }
    }


    fun initBackground(){

        val kbv = binding.background
        //kbv.setBackgroundResource(mainViewModel.currentSound.background)
        val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
        //val generator = RandomTransitionGenerator(50000, ACCELERATE_DECELERATE)
        val generator = RandomTransitionGenerator(25000, ACCELERATE_DECELERATE)
        kbv.setTransitionGenerator(generator)

    }


    fun getFormtTime(ms: Int) : String{

        val hour = (ms / 1000 / 60 / 60).toString()
        var minutes = (ms / 1000 / 60  % 60).toString()
        var second =  (ms / 1000 % 60).toString()

        if (minutes.length==1) minutes = "0$minutes"
        if (second.length==1) second = "0$second"

        var res = "$hour:$minutes:$second"

        if (hour == "0")  {
            res = "$minutes:$second"
        }
        return res
    }

    fun initSeekBar() {

        val hours = binding.numberPickerHours.value.toString().toInt()
        val minutes = binding.numberPickerMin.value.toString().toInt()

        trackTime = ((hours*60) + minutes)*60*1000
        binding.trackTimeText.text = getFormtTime(trackTime)

        binding.seekBar.max = trackTime
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //initTimer(trackTime)

                binding.durationTimeText.text = getFormtTime(progress)
                currentTime = progress

                if (fromUser) {
                    if (mp!!.duration != 0){
                        mp?.seekTo(currentTime % (mp!!.duration))
                    }
                    else {
                        mp?.seekTo(0)
                    }
                }


            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
              //  timer.onTick(currentTime.toLong())
              //  timer.onTick(340183)
              //  timer.cancel()
                timer.cancel()
                initTimer(trackTime - currentTime)
                timer.start()
            }

        })
    }

    fun initBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                mainViewModel.visibilityBottomBarLivaData.postValue(true)
            }
        })
    }

    fun initInternetDetector(){
        connectionLiveData = ConnectionLiveData(requireActivity())
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                if(it || mpIsReady){
                   // binding.playerGroup.visibility = View.VISIBLE
                    binding.networkProblems.visibility = View.INVISIBLE
                }
                else {
                    binding.playerGroup.visibility = View.INVISIBLE
                    binding.networkProblems.visibility = View.VISIBLE
                }
            }
        }
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val action = intent.extras?.getString("actionName")
            Log.d("MyLogs333", "action = $action")

        }
    }

    fun createChannel(){
        val channel = NotificationChannel("channel1", "pau=pau", NotificationManager.IMPORTANCE_HIGH)
        val notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        requireActivity().registerReceiver(broadcastReceiver, IntentFilter("TRAKS_TRAKS"))
        requireActivity().startService(
            Intent(
                requireContext(),
                OnClearFromRecentService::class.java
            )
        )

        CreateNotification().createNotification(requireContext(), 0)
    }


    fun initTimer(timeMs : Int){
//        timer.cancel()
//        initTimer(trackTime)
         timer =  object : CountDownTimer(timeMs.toLong(), 1000) {
//        timer =  object : CountDownTimer(trackTime.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {


                currentTime += 1000
                binding.seekBar.progress = currentTime
                Log.d("MyLogs3335", "millisUntilFinished = $millisUntilFinished")

                if(millisUntilFinished<10000){
                    Log.d("MyLogs3337", "$currentVolume")

                    currentVolume -= 0.1f
                    mp?.setVolume(currentVolume, currentVolume)
                }
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
//                mutableLiveData.postValue(false)
//                mp?.stop()
//                currentTime = 0
//                currentVolume = 1f
//                Log.d("MyLogs3337", "Timer onFinish")

                findNavController().popBackStack()
                mainViewModel.visibilityBottomBarLivaData.postValue(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.release()
    }

    override fun onPause() {
        super.onPause()
        mp?.pause()
    }



}