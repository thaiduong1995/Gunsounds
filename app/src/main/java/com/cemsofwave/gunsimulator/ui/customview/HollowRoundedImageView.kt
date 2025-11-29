package com.cemsofwave.gunsimulator.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class HollowRoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var borderWidth = 3f // Set the desired border width in pixels
    private var borderColor = Color.BLACK // Set the desired border color
    private var currentBackgroundColor = Color.RED
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height
        val radius = Math.min(width, height) / 2f

        paint.color = borderColor
        paint.style = Paint.Style.STROKE // Set the paint style to STROKE for border
        paint.strokeWidth = borderWidth
        paint.color = currentBackgroundColor
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
        val path = Path()
        path.addOval(RectF(0f, 0f, width.toFloat(), height.toFloat()), Path.Direction.CW)
        canvas.drawPath(path, paint)
    }

    fun setBorderWidth(newWidth: Float) {
        borderWidth = newWidth
        invalidate()
    }

    fun setBorderColor(newColor: Int) {
        borderColor = newColor
        invalidate()
    }

    override fun setBackgroundColor(newColor: Int) {
        currentBackgroundColor = newColor
        invalidate()
    }
}

