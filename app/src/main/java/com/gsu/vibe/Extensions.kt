package com.gsu.vibe

import android.content.Context
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions

fun Fragment.setVibro() {
    val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    if (vibrator?.hasVibrator() == true) {
        vibrator.vibrate(50)
    }
}

fun getFormtTime(ms: Int): String {

    val hour = (ms / 1000 / 60 / 60).toString()
    var minutes = (ms / 1000 / 60 % 60).toString()
    var second = (ms / 1000 % 60).toString()

    if (minutes.length == 1) minutes = "0$minutes"
    if (second.length == 1) second = "0$second"

    var res = "$hour:$minutes:$second"

    if (hour == "0") {
        res = "$minutes:$second"
    }
    return res
}


val Fragment.navOptionsLong: NavOptions
    get() = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in_long)
        .setExitAnim(R.anim.fade_out_long)
        .setPopEnterAnim(R.anim.fade_in_long)
        .setPopExitAnim(R.anim.fade_out_long)
        .build()



val Fragment.navOptions: NavOptions
    get() = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()


fun Fragment.buttonPress(v: View, event: MotionEvent, method: () -> Unit): Boolean {
    when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            v.animate().scaleX(0.96f).scaleY(0.96f).setDuration(200).start()
        }
        MotionEvent.ACTION_UP -> {
            setVibro()
            v.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(100)
                .withEndAction {
                    method()
                }
                .start()
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