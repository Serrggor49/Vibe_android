package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.flaviofaria.kenburnsview.RandomTransitionGenerator
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentPlayerBinding
import com.gsu.vibe.services.DownloadAudioFromUrlNew
import com.gsu.vibe.services.OnClearFromRecentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class PlayerFragment : Fragment() {


    var timerState = false  // false - timer не запущен

    val mediaPath = "/data/data/com.gsu.vibe/files/"
    var currentVolume = 1.0f

    lateinit var timer : CountDownTimer
    var trackTime = 0  // продолжительность воспроизведения, которую установил пользователь
    var currentTime = 0

    val stateLiveData = MutableLiveData(false)  // true - плеер в состоянии паузы, false - играет
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

        mainViewModel.visibilityBottomBarLivaData.postValue(false)  // убрали отображене бара
        initTimerPanel()
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

    fun initTimerPanel(){

        binding.setTimeButton.setOnClickListener{

            binding.timerView.visibility = View.GONE
            binding.setTimeButton.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            initSeekBar()

            downloadTrack()

        }

    }


    fun initFields(){
        binding.title.text = getString(mainViewModel.currentSound.title)
        binding.subTitle.text = getString(mainViewModel.currentSound.subtitle)
        binding.background.setImageResource(mainViewModel.currentSound.background)
        binding.foreground.setImageResource(mainViewModel.currentSound.foreground)
    }

    fun fileExists() : Boolean{
        val file = File("$mediaPath${mainViewModel.currentSound.name}")
        return file.exists()
    }

    fun downloadTrack() {

        if (!fileExists()) {  // если трека с таким имени нет в памяти, то скачиваем
            CoroutineScope(Dispatchers.IO).launch {
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
        binding.percentage.visibility = View.GONE

        mpIsReady = true

        mp = MediaPlayer.create(requireActivity(),  Uri.parse("$mediaPath${mainViewModel.currentSound.name}"))
        mp?.apply {

            isLooping = true // плеер зациклен, играет трек по кругу

            stateLiveData.observe(requireActivity()) {
                Log.d("MyLogs332", "mutableLiveData changed = $it")

                if (it) {
                    mp?.pause()
                    CreateNotification().createNotification(context = requireContext(), pos = 2, nameType = getString(mainViewModel.currentSound.subtitle), trackName = getString(mainViewModel.currentSound.title), backgroundImage = mainViewModel.currentSound.previewB)
                    timer.cancel()
                    timerState = false

                    binding.playButton.setImageResource(R.drawable.ic_play_button_2)
                } else {
                    mp?.start()
                    initSeekBar()
                    CreateNotification().createNotification(context = requireContext(), pos = 1, nameType = getString(mainViewModel.currentSound.subtitle), trackName = getString(mainViewModel.currentSound.title), backgroundImage = mainViewModel.currentSound.previewB)
                    binding.playButton.setImageResource(R.drawable.ic_pause_button)

                    if (!timerState){
                        timer.start()
                        timerState = true
                    }
                }
            }

            binding.playButton.setOnClickListener {
                Log.d("MyLogs991", "playButton")

                if (stateLiveData.value == false) {
                    stateLiveData.postValue(true)
                } else {
                    stateLiveData.postValue(false)
                }

//                mp?.setOnCompletionListener {
//                    stateLiveData.postValue(false)
//                }

//                configBroadcastReceiver()
            }

            configBroadcastReceiver()
        }

    }


    fun initBackground(){

        val kbv = binding.background
        val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
        //val generator = RandomTransitionGenerator(50000, ACCELERATE_DECELERATE)
        val generator = RandomTransitionGenerator(25000, ACCELERATE_DECELERATE)
        kbv.setTransitionGenerator(generator)

    }


    // передаем время в мс, получаем стрингу со временем в удобном для отображения формате
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

        trackTime = ((binding.numberPickerHours.value*60) + binding.numberPickerMin.value)*60*1000
        binding.trackTimeText.text = getFormtTime(trackTime)

        binding.seekBar.max = trackTime

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

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
                timerState = false

                initTimer(trackTime - currentTime)

                if (!timerState){
                    timer.start()
                    timerState = true
                }
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
        @RequiresApi(Build.VERSION_CODES.S)
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.extras?.getString("actionName")

            if (action == CreateNotification().ACTION_PAUSE){

                CreateNotification().createNotification(context = requireContext(), pos = 1, nameType = getString(mainViewModel.currentSound.subtitle), trackName = getString(mainViewModel.currentSound.title), backgroundImage = mainViewModel.currentSound.previewB)

                mp?.start()
                stateLiveData.postValue(false)

                if (!timerState){
                    timer.start()
                    timerState = true
                }

                //timerState = false
                Log.d("MyLogs332", "testState = ${timerState}")
            }
            else if (action == CreateNotification().ACTION_PLAY){

                CreateNotification().createNotification(context = requireContext(), pos = 2, nameType = getString(mainViewModel.currentSound.subtitle), trackName = getString(mainViewModel.currentSound.title), backgroundImage = mainViewModel.currentSound.previewB)

                mp?.pause()
                timer.cancel()
                timerState = false


                // таймер продолжает работать, если свернуть приложение. Но если нажать паузу в фоне, то таймер продолжит тикать

                stateLiveData.postValue(true)

                //timerState = true
                Log.d("MyLogs332", "testState = ${timerState}")
            }
        }
    }


    fun configBroadcastReceiver(){
        val channel = NotificationChannel(CreateNotification().CHANNEL_ID, "pau=pau", NotificationManager.IMPORTANCE_LOW)
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
                //Log.d("MyLogs3335", "millisUntilFinished = $millisUntilFinished")

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
        if (this::timer.isInitialized) {
            timer.cancel()
            timerState = false
        }
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