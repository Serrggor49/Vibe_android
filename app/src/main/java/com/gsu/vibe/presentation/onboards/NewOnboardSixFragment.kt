package com.gsu.vibe.presentation.onboards

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.buttonPress
import com.gsu.vibe.databinding.FragmentNewOnboardSixBinding
import com.gsu.vibe.navOptions
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.presentation.MainViewModel

class NewOnboardSixFragment : Fragment() {

    val mainViewModel : MainViewModel by activityViewModels()

    lateinit var centralBall: ImageView

    private lateinit var _binding: FragmentNewOnboardSixBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewOnboardSixBinding.inflate(inflater, container, false)
        return _binding.root
    }



    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.visibilityBottomBarLivaData.postValue(false)
        init()

        binding.nextButton.setOnTouchListener { v, event ->
            buttonPress(v, event) {
                Log.d("MyLogs1112", "true")
                mainViewModel.setOnboardStatus(true)
                Log.d("MyLogs1112", "status  = ${mainViewModel.getOnboardStatus()}")

                //val action = NewOnboardSixFragmentDirections.actionNewOnboardSixFragmentToPaywallFragment()
                val action = NewOnboardSixFragmentDirections.actionNewOnboardSixFragmentToSleepFragment()
                view.findNavController().navigate(action, navOptions)
            }
        }

    }


    fun init(){

    }


    class AnimatedCircles(context: Context, attrs: AttributeSet) : View(context, attrs) {
        private val outerPaint = Paint().apply { color = Color.parseColor("#1FA8B7FF") }
        private val middlePaint = Paint().apply { color = Color.parseColor("#73D0D9FF") }
        private val innerPaint = Paint().apply { color = Color.parseColor("#BFFFFFFF") }

        private var outerRadius = 250f
        private var middleRadius = 130f
        private var innerRadius = 100f

        private var outerDelta = 4
        private var middleDelta = 2
        private var innerDelta = 1

        private val innerMinSize: Float = 100f // 60% от изначального размера 50 (40% уменьшение)

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val centerX = width / 2f
            val centerY = height / 2f

            // Рисуем круги
            canvas.drawCircle(centerX, centerY, outerRadius, outerPaint)
            canvas.drawCircle(centerX, centerY, middleRadius, middlePaint)
            canvas.drawCircle(centerX, centerY, innerRadius, innerPaint)

            // Обновляем радиусы
            outerRadius += outerDelta
            middleRadius += middleDelta
            innerRadius += innerDelta

            // Проверяем условия и меняем направление
            if (outerRadius <= middleRadius || outerRadius >= 350) outerDelta *= -1
            if (middleRadius <= innerRadius || middleRadius >= outerRadius) middleDelta *= -1
            if (innerRadius <= innerMinSize || innerRadius >= middleRadius) innerDelta *= -1

            // Перерисовываем View
            //postInvalidateDelayed(16)
            postInvalidateDelayed(32)
        }
    }

    val navOptions: NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in_long)
        .setExitAnim(R.anim.fade_out_long)
        .setPopEnterAnim(R.anim.fade_in_long)
        .setPopExitAnim(R.anim.fade_out_long)
        .setPopUpTo(R.id.nav_graph, true)  // Удаление SplashFragment из стека
        .build()

}