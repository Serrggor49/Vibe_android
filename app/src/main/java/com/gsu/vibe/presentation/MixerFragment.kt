package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.data.models.MixerSoundModel
import com.gsu.vibe.databinding.FragmentMixerBinding
import com.gsu.vibe.presentation.adapters.MixerContentAdapter
import com.shawnlin.numberpicker.NumberPicker
import java.lang.String
import java.util.*
import kotlin.Boolean
import kotlin.Int
import kotlin.collections.ArrayList
import kotlin.concurrent.timer
import kotlin.getValue
import kotlin.math.roundToInt

class MixerFragment : Fragment() {

    private val MAX_VOLUME = 100

    lateinit var mediaPlayer1: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    lateinit var mediaPlayer3: MediaPlayer
    lateinit var mediaPlayer4: MediaPlayer
    
    val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var _binding: FragmentMixerBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMixerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.currentType = MainViewModel.CurrentType.MIXER
        (activity as MainActivity).updateBottomButtons()
    }

    lateinit var animalList: ArrayList<MixerSoundModel>
    lateinit var natureList: ArrayList<MixerSoundModel>
    lateinit var instrumentsList: ArrayList<MixerSoundModel>

    @SuppressLint("InflateParams")
    fun init() {

        animalList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.ANIMALS)
        natureList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.NATURE)
        instrumentsList = Repository.getSoundsForMixer(Repository.SoundsForMixerType.INSTRUMENTS)

        val display: Display = requireActivity().windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        val columns = (dpWidth / 100).roundToInt()

        binding.recyclerAnimals.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerAnimals.adapter = MixerContentAdapter(animalList) { pair ->
            itemClick(pair.first,
                pair.second,
                Repository.SoundsForMixerType.ANIMALS,
                animalList,
                binding.recyclerAnimals)
        }

        binding.recyclerNature.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerNature.adapter = MixerContentAdapter(natureList) { pair ->
            itemClick(pair.first,
                pair.second,
                Repository.SoundsForMixerType.NATURE,
                natureList,
                binding.recyclerNature)
        }

        binding.recyclerinstruments.layoutManager = GridLayoutManager(requireActivity(), columns)
        binding.recyclerinstruments.adapter = MixerContentAdapter(instrumentsList) { pair ->
            itemClick(pair.first,
                pair.second,
                Repository.SoundsForMixerType.INSTRUMENTS,
                instrumentsList,
                binding.recyclerinstruments)
        }

        binding.infoBinuaButton.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.alert_dialog_binua_info, null)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            myDialog.show()

            val okButton = dialogBinding.findViewById<View>(R.id.buttonOKalert)
            okButton.setOnClickListener {
                myDialog.dismiss()
            }
        }


        initButtonSetTimer()
        binuaInit()
        initPlayers()
        volumeInit()

    }

    fun initButtonSetTimer(){

        binding.setTimerButton.setOnClickListener {
            mainViewModel.timeForMixerPlayerInMs = (((binding.numberPickerHours.value*60) + (binding.numberPickerMinutes.value))*60 + binding.numberPickerSec.value)*1000

            var animalSoundVolume = mainViewModel.animalSoundVolume
            var natureSoundVolume = mainViewModel.natureSoundVolume
            var instrumentsSoundVolume = mainViewModel.instrumentsVolume
            var binuaSoundVolume = mainViewModel.binuaSoundVolume

            val timeToFinish = 200L
            object : CountDownTimer(timeToFinish, 20) {

                override fun onTick(millisUntilFinished: Long) {
                    animalSoundVolume -= 0.015f
                    natureSoundVolume -= 0.015f
                    instrumentsSoundVolume -= 0.015f
                    binuaSoundVolume -= 0.015f
                    mediaPlayer1.setVolume(animalSoundVolume, animalSoundVolume)
                    mediaPlayer2.setVolume(natureSoundVolume, natureSoundVolume)
                    mediaPlayer3.setVolume(instrumentsSoundVolume, instrumentsSoundVolume)
                    mediaPlayer4.setVolume(binuaSoundVolume, binuaSoundVolume)
                }

                override fun onFinish() {
                    val action = MixerFragmentDirections.actionMixerFragmentToMixerPlayerFragment()
                    mainViewModel.visibilityBottomBarLivaData.postValue(false)
                    view?.findNavController()?.navigate(action)
                }
            }.start()

        }

    }

    fun volumeInit(){

        binding.seekBarAnimal.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var log1 = 1f-(Math.log((MAX_VOLUME - progress).toDouble()) / Math.log(MAX_VOLUME.toDouble())).toFloat()
                if (log1>=0.99f) {log1 = 1f}
                mediaPlayer1.setVolume(log1,log1)
                mainViewModel.animalSoundVolume = log1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.seekBarNature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var log1 = 1f-(Math.log((MAX_VOLUME - progress).toDouble()) / Math.log(MAX_VOLUME.toDouble())).toFloat()
                if (log1>=0.99f) {log1 = 1f}
                mediaPlayer2.setVolume(log1,log1)
                mainViewModel.natureSoundVolume = log1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.seekBarInstruments.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var log1 = 1f-(Math.log((MAX_VOLUME - progress).toDouble()) / Math.log(MAX_VOLUME.toDouble())).toFloat()
                if (log1>=0.99f) {log1 = 1f}
                mediaPlayer3.setVolume(log1,log1)
                mainViewModel.instrumentsVolume = log1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.seekBarBinua.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var log1 = 1f-(Math.log((MAX_VOLUME - progress).toDouble()) / Math.log(MAX_VOLUME.toDouble())).toFloat()
                if (log1>=0.99f) {log1 = 1f}
                mediaPlayer4.setVolume(log1,log1)
                mainViewModel.binuaSoundVolume = log1
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }
    fun binuaInit() {

        binding.emptyButton.setOnClickListener {
            setShadows()
            binding.emptyShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.empty
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)

        }

        binding.deltaButton.setOnClickListener {
            setShadows()
            binding.deltaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.delta_binua
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)

        }

        binding.tettaButton.setOnClickListener {
            setShadows()
            binding.tettaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.tetta_binua
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)

        }

        binding.alphaButton.setOnClickListener {
            setShadows()
            binding.alphaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.alpha_binua
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)

        }

        binding.bettaButton.setOnClickListener {
            setShadows()
            binding.bettaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.betta_binua
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)

        }

        binding.gammaButton.setOnClickListener {
            setShadows()
            binding.gammaShadow.visibility = View.INVISIBLE
            mainViewModel.binuaSound.sound = R.raw.gamma_binua
            initChoiсePlayer(Repository.SoundsForMixerType.BINUA)
        }
    }



    fun setShadows() {
        val shadowList = arrayListOf(
            binding.emptyShadow,
            binding.deltaShadow,
            binding.tettaShadow,
            binding.alphaShadow,
            binding.bettaShadow,
            binding.gammaShadow)
        shadowList.forEach { it.visibility = View.VISIBLE }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun itemClick(
        pos: Int,
        choiceSound: MixerSoundModel,
        type: Repository.SoundsForMixerType,
        list: ArrayList<MixerSoundModel>,
        recyclerView: RecyclerView,
    ) {

        when (type) {
            Repository.SoundsForMixerType.ANIMALS -> {
                mainViewModel.animalSound = choiceSound
                initChoiсePlayer(Repository.SoundsForMixerType.ANIMALS)
            }
            Repository.SoundsForMixerType.NATURE -> {
                mainViewModel.natureSound = choiceSound
                initChoiсePlayer(Repository.SoundsForMixerType.NATURE)
            }

            Repository.SoundsForMixerType.INSTRUMENTS -> {
                mainViewModel.instrumentsSound = choiceSound
                initChoiсePlayer(Repository.SoundsForMixerType.INSTRUMENTS)
            }

            else -> {}
        }

        list.forEach { it.isSelected = false }
        list[pos].isSelected = true
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun initPlayers() {

        mediaPlayer1 = MediaPlayer.create(requireContext(), R.raw.tetta_binua)
        mediaPlayer2 = MediaPlayer.create(requireContext(), R.raw.tetta_binua)
        mediaPlayer3 = MediaPlayer.create(requireContext(), R.raw.tetta_binua)
        mediaPlayer4 = MediaPlayer.create(requireContext(), R.raw.tetta_binua)

        mediaPlayer1.setVolume(0.5f,0.5f)
        mediaPlayer2.setVolume(0.5f,0.5f)
        mediaPlayer3.setVolume(0.5f,0.5f)
        mediaPlayer4.setVolume(0.5f,0.5f)
    }

    fun initChoiсePlayer(type: Repository.SoundsForMixerType) {

        when(type){
            Repository.SoundsForMixerType.ANIMALS -> {

                mediaPlayer1.stop()
                mediaPlayer1.reset()
                if(mainViewModel.animalSound.sound != 0) {
                    mediaPlayer1 = MediaPlayer.create(requireContext(), mainViewModel.animalSound.sound)
                    mediaPlayer1.isLooping = true
                    mediaPlayer1.start()
                }
            }

            Repository.SoundsForMixerType.NATURE -> {

                mediaPlayer2.stop()
                mediaPlayer2.reset()
                if(mainViewModel.natureSound.sound != 0) {
                    mediaPlayer2 = MediaPlayer.create(requireContext(), mainViewModel.natureSound.sound)
                    mediaPlayer2.isLooping = true
                    mediaPlayer2.start()
                }
            }

            Repository.SoundsForMixerType.INSTRUMENTS -> {
                mediaPlayer3.stop()
                mediaPlayer3.reset()
                if(mainViewModel.instrumentsSound.sound != 0) {
                    mediaPlayer3 = MediaPlayer.create(requireContext(), mainViewModel.instrumentsSound.sound)
                    mediaPlayer3.isLooping = true
                    mediaPlayer3.start()
                }
            }

            Repository.SoundsForMixerType.BINUA -> {
                mediaPlayer4.stop()
                mediaPlayer4.reset()
                if(mainViewModel.binuaSound.sound != 0) {
                    mediaPlayer4 = MediaPlayer.create(requireContext(), mainViewModel.binuaSound.sound)
                    mediaPlayer4.isLooping = true
                    mediaPlayer4.start()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()

        val players = arrayOf(mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4)
        players.forEach { it.release() }
    }

}