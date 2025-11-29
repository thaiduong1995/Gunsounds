package com.cemsofwave.gunsimulator.ui.customview

import androidx.appcompat.widget.AppCompatImageView
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var currentBackgroundColor = Color.RED
    private var currentPadding = 10

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height
        val radius = width.coerceAtMost(height) / 2f

        paint.color = currentBackgroundColor
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }

    override fun setBackgroundColor(newColor: Int) {
        currentBackgroundColor = newColor
        invalidate()
    }
}
