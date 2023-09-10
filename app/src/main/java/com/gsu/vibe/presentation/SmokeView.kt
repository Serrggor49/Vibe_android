package com.gsu.vibe.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class SmokeView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val particles = LinkedList<Particle>()
    private val paint = Paint()
    private val random = Random()

    init {
        paint.color = Color.GRAY
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val iterator = particles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()
            particle.update()
            if (particle.alpha <= 0) {
                iterator.remove()
            } else {
                paint.alpha = particle.alpha
                canvas.drawCircle(particle.x, particle.y, particle.radius.toFloat(), paint)
            }
        }
        invalidate()
    }

    fun addParticle(x: Float, y: Float) {
        particles.add(Particle(x, y, random.nextInt(20) + 10, random.nextInt(5) + 2))
    }

    private class Particle(
        var x: Float,
        var y: Float,
        val radius: Int,
        val speed: Int
    ) {
        var alpha = 255

        fun update() {
            y -= speed
            alpha -= 5
        }
    }
}
