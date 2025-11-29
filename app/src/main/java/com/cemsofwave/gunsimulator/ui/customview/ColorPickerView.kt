package com.cemsofwave.gunsimulator.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.cemsofwave.gunsimulator.R
import com.trinhbx.base.extension.dpToPx
import kotlin.math.roundToInt


/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 31/10/2023
 */
class ColorPickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    styleDef: Int = 0
) : View(context, attrs, styleDef) {
    private var progressBarSize = dpToPx(16f)
        set(value) {
            field = value
            barCornerRadius = value/2
        }
    private var barCornerRadius = progressBarSize/2

    private var thumbWidth = dpToPx(6f)
        set(value) {
            field = value
            thumbHeight = thumbWidth*2f
            thumbCornerRadius = thumbWidth/3
        }
    private var thumbHeight = thumbWidth*2f
    private var thumbCornerRadius = thumbWidth/3


    private var colorGradient:LinearGradient? = null
    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val barRect = RectF()

    private val thumbLeftPath = Path()
    private val thumbRightPath = Path()

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        pathEffect = CornerPathEffect(thumbCornerRadius)
    }
    private var thumbColor = Color.YELLOW
        set(value) {
            field = value
            thumbPaint.color = value
        }
    private val thumbScaleMax = 1.3f
    private var thumbScale = 1f

    private val colorSeeds = intArrayOf(
        Color.parseColor("#EA1600"),
        Color.parseColor("#FF00FD"),
        Color.parseColor("#1F1EFF"),
        Color.parseColor("#42FF30"),
        Color.parseColor("#00FCFF"),
        Color.parseColor("#FFFC00"),
        Color.parseColor("#F75F00"),
        Color.parseColor("#E70000")
    )

    var progress:Int = 100
        set(value) {
            field = if(value<0) 0
            else if(value>maxValue) maxValue
            else value
        }
    private val maxValue = 100

    var colorChangeListener: OnColorChangeListener? = null
    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ColorPickerView)
            progressBarSize = typedArray.getDimension(R.styleable.ColorPickerView_cpv_barSize, progressBarSize)
            thumbWidth = typedArray.getDimension(R.styleable.ColorPickerView_cpv_thumbSize, thumbWidth)
            progress = typedArray.getInt(R.styleable.ColorPickerView_cpv_progress, progress)
            thumbColor = typedArray.getColor(R.styleable.ColorPickerView_cpv_thumbColor, thumbColor)
            typedArray.recycle()
        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newW = (progressBarSize + (thumbWidth*2)*thumbScaleMax + paddingLeft + paddingRight).toInt()
        setMeasuredDimension(newW, heightMeasureSpec)
        colorChangeListener?.onColorChangeListener(pickColor(getThumbPos(), height),progress)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawProgressBar(canvas)
        drawThumb(canvas)
    }

    private fun drawProgressBar(canvas: Canvas){
        barRect.apply {
            left = thumbWidth*thumbScaleMax
            top = thumbHeight*thumbScaleMax/2
            right = left + progressBarSize
            bottom = measuredHeight - top
        }
        if(colorGradient==null){
            colorGradient = LinearGradient(0f, (thumbHeight*thumbScaleMax)/2, 0f, measuredHeight-(thumbHeight*thumbScaleMax)/2, colorSeeds, null, Shader.TileMode.CLAMP)
        }
        barPaint.shader = colorGradient
        canvas.drawRoundRect(barRect, barCornerRadius, barCornerRadius, barPaint)
    }

    private fun drawThumb(canvas: Canvas){
        val pos = getThumbPos()
        thumbLeftPath.apply {
            reset()
            moveTo(thumbWidth*thumbScaleMax,pos)
            lineTo(thumbWidth*(thumbScaleMax - thumbScale),pos - (thumbHeight*thumbScale)/2)
            lineTo(thumbWidth*(thumbScaleMax - thumbScale),pos + (thumbHeight*thumbScale)/2)
            close()
        }
        thumbRightPath.apply {
            reset()
            moveTo(width - thumbWidth*thumbScaleMax,pos)
            lineTo(width - thumbWidth*(thumbScaleMax - thumbScale),pos- (thumbHeight*thumbScale)/2)
            lineTo(width - thumbWidth*(thumbScaleMax - thumbScale),pos + (thumbHeight*thumbScale)/2)
            close()
        }
        canvas.drawPath(thumbLeftPath,thumbPaint)
        canvas.drawPath(thumbRightPath,thumbPaint)
    }

    private fun getThumbPos():Float{
        return thumbHeight*thumbScaleMax/2 + (height - thumbHeight*thumbScaleMax) * (1f*progress/maxValue)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    colorChangeListener?.onStartTrackingTouch()
                    thumbScale = thumbScaleMax
                    invalidate()
                }

                MotionEvent.ACTION_UP -> {
                    thumbScale = 1f
                    invalidate()
                    performClick()
                    colorChangeListener?.onColorChangeListener(pickColor(event.y, height),progress)
                }

                MotionEvent.ACTION_CANCEL -> {
                }

                MotionEvent.ACTION_MOVE -> {
                    progress = getProgress(it.y)
                    invalidate()
                }
                else -> {}
            }
        }
        return true
    }
    private fun getProgress(y:Float): Int {
        val thumbY = if(y<=0f){
            0f
        } else if(y>=measuredHeight){
            measuredHeight.toFloat()
        } else{
            y
        }
        return (thumbY / measuredHeight * 100).toInt()
    }

    private fun mix(start: Int, end: Int, position: Float): Int {
        return start + (position * (end - start)).roundToInt()
    }

    private fun pickColor(position: Float, canvasWidth: Int): Int {
        val value = (position - thumbHeight) / (canvasWidth - thumbHeight.times(2))
        when {
            value <= 0.0 -> return colorSeeds[0]
            value >= 1 -> return colorSeeds[colorSeeds.size - 1]
            else -> {
                var colorPosition = value * (colorSeeds.size - 1)
                val i = colorPosition.toInt()
                colorPosition -= i
                val c0 = colorSeeds[i]
                val c1 = colorSeeds[i + 1]

                val red = mix(Color.red(c0), Color.red(c1), colorPosition)
                val green = mix(Color.green(c0), Color.green(c1), colorPosition)
                val blue = mix(Color.blue(c0), Color.blue(c1), colorPosition)
                return Color.rgb(red, green, blue)
            }
        }
    }

    interface OnColorChangeListener {
        fun onColorChangeListener(color: Int,progress:Int)
        fun onStartTrackingTouch()
    }
}