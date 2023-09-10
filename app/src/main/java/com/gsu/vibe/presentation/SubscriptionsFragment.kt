package com.gsu.vibe.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gsu.vibe.R
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentSubscriptionsBinding

class SubscriptionsFragment : Fragment() {

    lateinit private var _binding: FragmentSubscriptionsBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initButtons()


    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initButtons() {

        binding.weekButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.weekButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
            }
        }

        binding.monthlyButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.monthlyButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
            }
        }

        binding.annualButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.annualButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
            }
        }

        binding.foreverButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                clearStates()
                binding.foreverButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_on)
            }
        }
    }

    private fun clearStates() {
        binding.weekButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.monthlyButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.annualButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
        binding.foreverButton.setBackgroundResource(R.drawable.stroke_for_subscribe_buttons_off)
    }

}