package com.gsu.vibe.presentation.onboards

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.gsu.vibe.R
import com.gsu.vibe.buttonPress

import com.gsu.vibe.databinding.FragmentNewOnboardThirdBinding
import com.gsu.vibe.navOptions
import com.gsu.vibe.navOptionsLong
import com.gsu.vibe.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewOnboardThirdFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var _binding: FragmentNewOnboardThirdBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewOnboardThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.visibilityBottomBarLivaData.postValue(false)

        val context = requireContext()
        val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball_new_third_onboard)

        blur(context, originalBitmap)

        binding.nextButton.setOnTouchListener { v, event ->
            // Вместо функции buttonPress используйте вашу реализацию
            buttonPress(v, event) {
                val action = NewOnboardThirdFragmentDirections.actionNewOnboardThirdFragmentToNewOnboardFourthFragment()
                view.findNavController().navigate(action, navOptionsLong)
            }
        }
    }

    private fun blur(context: Context, originalBitmap: Bitmap) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val blurredImages = (1..25 step 2).map { i ->
                tripleBlurImage(context, originalBitmap, i.toFloat())
            }

            while (true) {
                for (image in blurredImages) {
                    delay(100)
                    withContext(Dispatchers.Main) {
                        binding.centralBall.setImageBitmap(image)
                    }
                }
                delay(1000)

                for (image in blurredImages.reversed()) {
                    delay(100)
                    withContext(Dispatchers.Main) {
                        binding.centralBall.setImageBitmap(image)
                    }
                }
                delay(1000)
            }
        }
    }

    private fun tripleBlurImage(context: Context, originalBitmap: Bitmap, blurRadius: Float): Bitmap {
        val firstBlur = blurImage(context, originalBitmap, blurRadius)
        val secondBlur = blurImage(context, firstBlur, blurRadius)
        return blurImage(context, secondBlur, blurRadius)
    }

    private fun blurImage(context: Context, originalBitmap: Bitmap, blurRadius: Float): Bitmap {
        val blurredBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888
        )
        val rs = RenderScript.create(context)
        val input = Allocation.createFromBitmap(rs, originalBitmap)
        val output = Allocation.createFromBitmap(rs, blurredBitmap)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setInput(input)
        script.setRadius(blurRadius)
        script.forEach(output)
        output.copyTo(blurredBitmap)
        input.destroy()
        output.destroy()
        script.destroy()
        rs.destroy()
        return blurredBitmap
    }
}



//class NewOnboardThirdFragment : Fragment() {
//
//    val mainViewModel : MainViewModel by activityViewModels()
//
//    lateinit var centralBall: ImageView
//
//    private lateinit var _binding: FragmentNewOnboardThirdBinding
//    private val binding
//        get() = _binding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        _binding = FragmentNewOnboardThirdBinding.inflate(inflater, container, false)
//        return _binding.root
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        mainViewModel.visibilityBottomBarLivaData.postValue(false)
//        blur()
//
//    }
//
//
//
//
//    fun blur(){
//        CoroutineScope(Dispatchers.IO).launch{
//            val blurredImages = mutableListOf<Bitmap>()
//            val originalBitmap = BitmapFactory.decodeResource(requireContext().resources, R.drawable.ball_new_third_onboard)
//            for (i in 1..25) {
//                val doubleBlurredImage = doubleBlurImage(requireContext(), originalBitmap, i.toFloat())
//                val tripleBlurredImage = tripleBlurImage(requireContext(), doubleBlurredImage, i.toFloat())
//                blurredImages.add(tripleBlurredImage)
//            }
//
//            while(true) { // Бесконечный цикл
//                // Идем от наименьшего размытия к наибольшему
//                for (image in blurredImages) {
//                    delay(50)
//                    withContext(Dispatchers.Main) {
//                        binding.centralBall.setImageBitmap(image)
//                    }
//                }
//
//                delay(1000)
//
//
//                // Идем обратно к наименьшему размытию
//                for (image in blurredImages.reversed()) {
//                    delay(50)
//                    withContext(Dispatchers.Main) {
//                        binding.centralBall.setImageBitmap(image)
//                    }
//                }
//                delay(1000)
//
//            }
//        }
//    }
//
//
//    fun doubleBlurImage(context: Context, originalBitmap: Bitmap, blurRadius: Float): Bitmap {
//        val firstBlur = blurImage(context, originalBitmap, blurRadius)
//        return blurImage(context, firstBlur, blurRadius)
//    }
//
//    fun tripleBlurImage(context: Context, originalBitmap: Bitmap, blurRadius: Float): Bitmap {
//        val firstBlur = blurImage(context, originalBitmap, blurRadius)
//        val secondBlur = blurImage(context, firstBlur, blurRadius)
//        return blurImage(context, secondBlur, blurRadius)
//    }
//
//    fun blurImage(context: Context, originalBitmap: Bitmap, blurRadius: Float): Bitmap {
//        val blurredBitmap = Bitmap.createBitmap(
//            originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888
//        )
//        val rs = RenderScript.create(context)
//        val input = Allocation.createFromBitmap(rs, originalBitmap)
//        val output = Allocation.createFromBitmap(rs, blurredBitmap)
//        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
//        script.setInput(input)
//        script.setRadius(blurRadius)
//        script.forEach(output)
//        output.copyTo(blurredBitmap)
//        input.destroy()
//        output.destroy()
//        script.destroy()
//        rs.destroy()
//        return blurredBitmap
//    }
//
//
//}