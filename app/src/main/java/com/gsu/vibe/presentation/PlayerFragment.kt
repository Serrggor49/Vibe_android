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
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.gsu.vibe.ConnectionLiveData
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentPlayerBinding
import com.gsu.vibe.services.DownloadAudioFromUrlNew
import com.gsu.vibe.services.OnClearFromRecentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.contracts.contract

class PlayerFragment : Fragment() {

    val mediaPath = "/data/data/com.gsu.vibe/files/"

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
    var mpIsReady = false // если песня загружена, то true. Чтобы при отсутствии интернета, загруженная песня не слетала

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

        initFavoriteButton()
    }


    fun initFavoriteButton(){

        val favoriteList =  Repository.getFavoritesSounds(requireContext())
        Log.d("MyLogs311", favoriteList.toString())

        var status = Repository.getFavoritesStatus(requireContext(), mainViewModel.currentSound.name)

        if (status){
            binding.favoriteButton.setImageResource(R.drawable.player_favorites_true)
        }
        else {
            binding.favoriteButton.setImageResource(R.drawable.player_favorites_false)
        }

        binding.favoriteButton.setOnClickListener {
            Log.d("MyLogs311", "favoriteButton")
            status = !status
            Repository.addStatusSoundToFavorites(requireContext(), mainViewModel.currentSound.name, status )
            initFavoriteButton()
        }

    }

    fun initSetTimeButtom(){

        binding.setTimeButton.setOnClickListener{

            binding.timerView.visibility = View.GONE
            binding.setTimeButton.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            initSeekBar()

            downloadTrack()

        }

    }


    fun initFields(){
        binding.title.text = mainViewModel.currentSound.title
        binding.subTitle.text = mainViewModel.currentSound.subtitle
        binding.background.setImageResource(mainViewModel.currentSound.background)
        binding.foreground.setImageResource(mainViewModel.currentSound.foreground)
    }

    fun fileExists() : Boolean{
        val file = File("$mediaPath${mainViewModel.currentSound.name}")
        return file.exists()
    }

    fun downloadTrack() {

//        val file = File("$mediaPath${mainViewModel.currentSound.name}")
//        val fileExist = file.exists()
        if (!fileExists()) {
            CoroutineScope(Dispatchers.IO).launch {
                //DownloadAudioFromUrlNew.download( fileName = mainViewModel.currentSound.title, urlString = "https://firebasestorage.googleapis.com/v0/b/vibe-3bd24.appspot.com/o/Ария%20-%20Беспечный%20Ангел%20(2).mp3?alt=media&token=e9ae6b4c-bcb7-4625-a963-3cee7e0151ab", context = requireContext())
                DownloadAudioFromUrlNew.download(
                    fileName = mainViewModel.currentSound.name,
                    urlString = mainViewModel.currentSound.url,
                    context = requireContext()
                )

                CoroutineScope(Dispatchers.Main).launch {
                    initPlayer()
                }
            }
        }

        else{
            initPlayer()
        }

    }

    @SuppressLint("SetTextI18n")
    fun initPlayer() {

        initTimer(trackTime)

        binding.playerGroup.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        mpIsReady = true
        binding.percentage.visibility = View.GONE

        mp = MediaPlayer.create(requireActivity(),  Uri.parse("$mediaPath${mainViewModel.currentSound.name}"))
        mp?.apply {
            isLooping = true

            mutableLiveData.observe(requireActivity()) {

                if (it) {
                    Log.d("MyLogs332", "mutableLiveData changed = $it")

                    // CreateNotification().createNotification(requireContext(), 1)
                   // Log.d("MyLogs332", "createNotification(requireContext(), 1)")
                    mp?.pause()
                    //mutableLiveData.postValue(false)
                    CreateNotification().createNotification(context = requireContext(), pos = 2, nameType = mainViewModel.currentSound.subtitle, trackName = mainViewModel.currentSound.title, backgroundImage = mainViewModel.currentSound.previewB)
                    timer.cancel()

                    binding.playButton.setImageResource(R.drawable.ic_play_button_2)
                } else {
                    Log.d("MyLogs332", "mutableLiveData changed = $it")

                    //  CreateNotification().createNotification(requireContext(), 2)
                  //  Log.d("MyLogs332", "createNotification(requireContext(), 2)")

                    mp?.start()
                    //mutableLiveData.postValue(true)
                    initSeekBar()
                    CreateNotification().createNotification(context = requireContext(), pos = 1, nameType = mainViewModel.currentSound.subtitle, trackName = mainViewModel.currentSound.title, backgroundImage = mainViewModel.currentSound.previewB)
                    binding.playButton.setImageResource(R.drawable.ic_pause_button_2)
                    timer.start()

                }
            }
    //
            binding.playButton.setOnClickListener {
                //    mp?.start()
                Log.d("MyLogs991", "playButton")

                if (mutableLiveData.value == false) {
//                    mp?.start()
                    mutableLiveData.postValue(true)
//                    initSeekBar()
//                    CreateNotification().createNotification(requireContext(), 1)

                } else {
//                    mp?.pause()
                    mutableLiveData.postValue(false)
//                    CreateNotification().createNotification(requireContext(), 2)

                }

                mp?.setOnCompletionListener {
                    mutableLiveData.postValue(false)
                }

                configBroadcastReceiver()
            }
        }

    }


    fun initBackground(){

        val kbv = binding.background
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
                    if (mp?.duration != 0){
                        mp?.seekTo(currentTime % (mp?.duration!!))
                    }
                    else {
                        mp?.seekTo(0)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

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

    fun initInternetDetector() {

      if (mainViewModel.isOnline() || fileExists()){
          binding.timerView.visibility = View.VISIBLE
          binding.setTimeButton.visibility = View.VISIBLE
          binding.networkProblems.visibility = View.INVISIBLE
      }

      else{
          binding.timerView.visibility = View.INVISIBLE
          binding.setTimeButton.visibility = View.INVISIBLE

          binding.networkProblems.visibility = View.VISIBLE
        }
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.extras?.getString("actionName")

            if (action == CreateNotification().ACTION_PAUSE){
                Log.d("MyLogs332", "broadcastReceiver postValue(false)")

                CreateNotification().createNotification(context = requireContext(), pos = 1, nameType = mainViewModel.currentSound.subtitle, trackName = mainViewModel.currentSound.title, backgroundImage = mainViewModel.currentSound.previewB)

                mp?.start()
                mutableLiveData.postValue(false)

                timer.start()

            }
            else if (action == CreateNotification().ACTION_PLAY){

                CreateNotification().createNotification(context = requireContext(), pos = 2, nameType = mainViewModel.currentSound.subtitle, trackName = mainViewModel.currentSound.title, backgroundImage = mainViewModel.currentSound.previewB)

                Log.d("MyLogs332", "broadcastReceiver postValue(true)")
                mp?.pause()
                timer.cancel()

                mutableLiveData.postValue(true)
            }
        }
    }


    fun configBroadcastReceiver(){
        val channel = NotificationChannel(CreateNotification().CHANNEL_ID, "pau=pau", NotificationManager.IMPORTANCE_HIGH)
        val notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        requireActivity().registerReceiver(broadcastReceiver, IntentFilter("TRAKS_TRAKS"))
        requireActivity().startService(
            Intent(
                requireContext(),
                OnClearFromRecentService::class.java
            )
        )
    }

    fun initTimer(timeMs : Int){
         timer =  object : CountDownTimer(timeMs.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

                currentTime += 1000
                binding.seekBar.progress = currentTime
                Log.d("MyLogs3335", "millisUntilFinished = $millisUntilFinished")

                if(millisUntilFinished<15000){
                    Log.d("MyLogs3337", "$currentVolume")

                    currentVolume -= 0.065f

                    if(currentVolume<0) currentVolume = 0f

                    mp?.setVolume(currentVolume, currentVolume)
                }
            }

            override fun onFinish() {
                currentVolume = 1f
                findNavController().popBackStack()
                mainViewModel.visibilityBottomBarLivaData.postValue(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::timer.isInitialized) {timer.cancel()}
        mp?.release()

        Log.d("MyLogs332", "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs332", "onResume")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs332", "onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs332", "onPause")
//        if (this::timer.isInitialized) {timer.cancel()}
//        mp?.pause()
    }

}