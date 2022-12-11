package com.gsu.vibe.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.gsu.vibe.Playable
import com.gsu.vibe.R
import com.gsu.vibe.databinding.ActivityMainBinding
import com.gsu.vibe.services.DownloadAudioFromUrl
import com.gsu.vibe.services.OnClearFromRecentService
import java.io.File


class MainActivity : AppCompatActivity(),Playable {

    private lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initBar()

        testDownload()
    }

    fun testDownload(){

//        val urlString = "https://firebasestorage.googleapis.com/v0/b/vibe-3bd24.appspot.com/o/Ария%20-%20Беспечный%20Ангел%20(2).mp3?alt=media&token=e9ae6b4c-bcb7-4625-a963-3cee7e0151ab"
//        DownloadAudioFromUrl(this).execute(urlString)

//        val file = File("/data/data/com.gsu.vibe/files/audio.mp3")
//        val res = file.exists()




//        Log.d("MyLogs991", res.toString())
    }


    fun initBar() {
        mainViewModel.visibilityBottomBarLivaData.observe(this) {
            if (it) {
                binding.bottomBar.visibility = View.VISIBLE
                binding.imageGradientView.visibility = View.VISIBLE
                window.navigationBarColor = Color.parseColor("#17004A")
                WindowCompat.setDecorFitsSystemWindows(
                    window,
                    true
                )  // показывае верхний и нижний бары
            } else {
                binding.bottomBar.visibility = View.INVISIBLE
                binding.imageGradientView.visibility = View.INVISIBLE
                window.navigationBarColor = Color.parseColor("#0017004A")
                WindowCompat.setDecorFitsSystemWindows(
                    window,
                    false
                )  // убирает верхний и нижний бары
            }
        }

        binding.buttonBar1.setOnClickListener {
            clearNavButtons()
            binding.buttonBar1Icon.setImageResource(R.drawable.ic_sleep_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.sleepFragment)
        }

        binding.buttonBar2.setOnClickListener {
            clearNavButtons()
            binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.focusFragment)
        }

        binding.buttonBar3.setOnClickListener {
            clearNavButtons()
            binding.buttonBar3Icon.setImageResource(R.drawable.ic_meditation_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.meditationFragment)
        }

        binding.buttonBar4.setOnClickListener {
            clearNavButtons()
            binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.natureFragment)
        }

        binding.buttonBar4.setOnClickListener {
            clearNavButtons()
            binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.natureFragment)
        }

        binding.buttonBar5.setOnClickListener {
            clearNavButtons()
            binding.buttonBar5Icon.setImageResource(R.drawable.ic_favorites_bar_color)
            findNavController(R.id.fragmentContainerView).navigate(R.id.favoriteFragment)
        }


    }


    fun clearNavButtons() {
        binding.buttonBar1Icon.setImageResource(R.drawable.ic_sleep_bar)
        binding.buttonBar2Icon.setImageResource(R.drawable.ic_focus_bar)
        binding.buttonBar3Icon.setImageResource(R.drawable.ic_meditation_bar)
        binding.buttonBar4Icon.setImageResource(R.drawable.ic_nature_bar)
        binding.buttonBar5Icon.setImageResource(R.drawable.ic_favorites_bar)
    }

    override fun onTrackPrevius() {
        TODO("Not yet implemented")
    }

    override fun onTrackPlay() {
        TODO("Not yet implemented")
    }

    override fun onTrackPause() {
        TODO("Not yet implemented")
    }

    override fun onTrackNext() {
        TODO("Not yet implemented")
    }


}