package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gsu.vibe.R
import com.gsu.vibe.data.models.OnboardItemObject
import com.gsu.vibe.data.models.OnboardItemType
import com.gsu.vibe.databinding.FragmentOnboardFirstBinding
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.presentation.adapters.FragmentFirstAdapter
import com.gsu.vibe.setVibro

class OnboardFirstFragment : Fragment() {

    val mainViewModel : MainViewModel by activityViewModels()

    private lateinit var _binding : FragmentOnboardFirstBinding
    private val binding
            get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnboardFirstBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }


    @SuppressLint("ClickableViewAccessibility")
    fun init(){

        mainViewModel.visibilityBottomBarLivaData.postValue(false)

        binding.nextButton.setOnTouchListener { v, event -> openSecondOnboard(v, event) }

//        binding.nextButton.setOnClickListener {
//            val action = OnboardFirstFragmentDirections.actionOnboardFirstFragmentToOnboardSecondFragment()
//            view?.findNavController()?.navigate(action)
//        }

        val recycler1 = binding.recycler
        recycler1.layoutManager = LinearLayoutManager(activity?.applicationContext,  LinearLayoutManager.HORIZONTAL, false)

        recycler1.adapter = FragmentFirstAdapter(
            listOf(
                OnboardItemObject(items = listOf(Pair(R.drawable.sleep_04_2b_northern_lights_prev, getString(R.string.northern_lights))), type = OnboardItemType.TYPE_1), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.meditation_01_1f_prev, getString(R.string.singing_bowls)), Pair(R.drawable.nature_01_2f_prev, getString( R.string.bonfire_sounds)), Pair(R.drawable.sleep_01_1b_quiet_harbor_prev, getString(R.string.through_time))), type = OnboardItemType.TYPE_2), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.meditation_08_2b_prev, getString(R.string.last_rays)), Pair(R.drawable.sleep_04_1f_milky_way_prev, getString(R.string.alone_in_infinity))), type = OnboardItemType.TYPE_3), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.sleep_03_01b_concentration_on_breathing_prev, getString(R.string.towards_the_sunset)), Pair(R.drawable.sleep_02_2b_calm_prev, getString(R.string.desert_melody)), Pair(R.drawable.focus_01_1b_prev, getString(R.string.cozy_evening))), type = OnboardItemType.TYPE_4), // 11

                OnboardItemObject(items = listOf(Pair(R.drawable.focus_03_2f_prev, getString(R.string.way_to_the_goal))), type = OnboardItemType.TYPE_1), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.nature_04_1f_prev, getString(R.string.tropical_birds)), Pair(R.drawable.nature_08_1f_prev, getString(R.string.lake_shore)), Pair(R.drawable.focus_03_1b_prev, getString(R.string.dreams_of_the_future))), type = OnboardItemType.TYPE_2), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.meditation_02_1f_prev, getString(R.string.path_of_zen)), Pair(R.drawable.nature_01_1f_prev, getString(R.string.internal_balance))), type = OnboardItemType.TYPE_3), // 11
                OnboardItemObject(items = listOf(Pair(R.drawable.focus_04_1f_prev, getString(R.string.new_horizons)), Pair(R.drawable.meditation_03_2f_prev, getString(R.string.sea_and_seagulls)), Pair(R.drawable.sleep_05_1f_tara_mantra_prev, getString(R.string.path_of_zen))), type = OnboardItemType.TYPE_4), // 11
            ))
        recycler1.scrollToPosition(Int.MAX_VALUE / 2)
        //recycler1.setOnTouchListener { _, _ -> true }


        val timer = object : CountDownTimer(99999999, 20) {
            override fun onTick(millisUntilFinished: Long) {
                recycler1.scrollBy(1, 0)

            }
            override fun onFinish() {
            }
        }
        timer.start()

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
                        val action = OnboardFirstFragmentDirections.actionOnboardFirstFragmentToOnboardSecondFragment()
                        view?.findNavController()?.navigate(action, navOptionsLong)
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


    // чтобы при нажатии кнопки назад приложение закрывалось
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}