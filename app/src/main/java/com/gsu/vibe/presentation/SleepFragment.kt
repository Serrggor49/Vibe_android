package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RemoteViews
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.data.Repository
import com.gsu.vibe.databinding.FragmentSleepBinding
import com.gsu.vibe.services.YourButton1Receiver
import com.gsu.vibe.services.YourButton2Receiver
import com.gsu.vibe.setVibro


class SleepFragment : Fragment() {

    val ACCELERATE_DECELERATE = AccelerateDecelerateInterpolator()
    val transitionDuration = 20000L
    val mainViewmodel: MainViewModel by activityViewModels()

    private lateinit var _binding: FragmentSleepBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)




       // testCustomNotif()
    }

    override fun onResume() {
        super.onResume()
        mainViewmodel.visibilityBottomBarLivaData.postValue(true)
        mainViewmodel.currentType = MainViewModel.CurrentType.FOR_SLEEP
        (activity as MainActivity).updateBottomButtons()
    }

    fun testCustomNotif() {

        val notificationManager =activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "your_channel_id",
            "Channel Name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)


// Интенты для обработки нажатия кнопок
        val button1Intent = Intent(requireContext(), YourButton1Receiver::class.java)
        val button1PendingIntent = PendingIntent.getBroadcast(requireContext(), 0, button1Intent,
            PendingIntent.FLAG_IMMUTABLE)

        val button2Intent = Intent(requireContext(), YourButton2Receiver::class.java)
        val button2PendingIntent = PendingIntent.getBroadcast(requireContext(), 0, button2Intent,
            PendingIntent.FLAG_IMMUTABLE)

// Инфляция и настройка разметки
        val contentView = RemoteViews(activity?.packageName, R.layout.notification_player)
        contentView.setOnClickPendingIntent(R.id.button1, button1PendingIntent)
        contentView.setOnClickPendingIntent(R.id.button2, button2PendingIntent)
        contentView.setOnClickPendingIntent(R.id.imageView75, button2PendingIntent)

        //val remoteViews = RemoteViews(packageName, R.layout.your_custom_notification_layout)
        contentView.setImageViewResource(R.id.imageView75, R.drawable.binua_betta)
        contentView.setImageViewResource(R.id.button1, com.google.android.material.R.drawable.ic_m3_chip_close)
        contentView.setImageViewResource(R.id.button2, R.drawable.ic_pause_button)


// Создание уведомления
        val notification = NotificationCompat.Builder(requireContext(), "your_channel_id")
            .setSmallIcon(R.drawable.ic_play_buuton_3)
            .setCustomContentView(contentView)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

// Показ уведомления
        notificationManager.notify(1, notification)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun init() {

        if (mainViewmodel.getSubStatus()) binding.closeAdButton.visibility = View.GONE

        binding.closeAdButton.setOnClickListener {
            mainViewmodel.openSubscribeFragmentLivaData.postValue(true)
            mainViewmodel.visibilityBottomBarLivaData.postValue(false)
        }

        binding.openFavoritesButton.setOnClickListener {
            mainViewmodel.openFavoriteLivaData.postValue(true)
        }



        binding.quietHarbor011.setOnTouchListener { v, event -> openPlayer(v, event, "m1") }
        binding.endlessSpace012.setOnTouchListener { v, event -> openPlayer(v, event, "m2") }
        binding.summerEvening0201.setOnTouchListener { v, event -> openPlayer(v, event, "m3") }
        binding.calm0202.setOnTouchListener { v, event -> openPlayer(v, event, "m4") }
        binding.m5Button.setOnTouchListener { v, event -> openPlayer(v, event, "m5") }
        binding.m6Button.setOnTouchListener { v, event -> openPlayer(v, event, "m6") }
        binding.m7Button.setOnTouchListener { v, event -> openPlayer(v, event, "m7") }
        binding.m8Button.setOnTouchListener { v, event -> openPlayer(v, event, "m8") }
        binding.m9Button.setOnTouchListener { v, event -> openPlayer(v, event, "m9") }
        binding.m10Button.setOnTouchListener { v, event -> openPlayer(v, event, "m10") }
        binding.m11Button.setOnTouchListener { v, event -> openPlayer(v, event, "m11") }
        binding.m12Button.setOnTouchListener { v, event -> openPlayer(v, event, "m12") }
        binding.m13Button.setOnTouchListener { v, event -> openPlayer(v, event, "m13") }
        binding.m14Button.setOnTouchListener { v, event -> openPlayer(v, event, "m14") }
        binding.m15Button.setOnTouchListener { v, event -> openPlayer(v, event, "m15") }

        if (Repository.getFavoritesSounds(requireContext()).isEmpty()) {
            binding.openFavoritesButton.visibility = View.GONE
        } else {
            binding.openFavoritesButton.visibility = View.VISIBLE
        }

    }


    fun openPlayer(v: View, event: MotionEvent, currentSound: String): Boolean {
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
                        mainViewmodel.setCurrentSound(currentSound)

                        if (mainViewmodel.showAd() && !mainViewmodel.getSubStatus()) {
                            val action = SleepFragmentDirections.actionSleepFragmentToInterstitialAdFragment()
                            view?.findNavController()?.navigate(action)
                        }
                        else{
                            val action =
                                SleepFragmentDirections.actionSleepFragmentToMediaPlayerServiceFragment()
                            view?.findNavController()?.navigate(action)
                        }

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




}