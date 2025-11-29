package com.cemsofwave.gunsimulator.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.trinhbx.base.extension.dpToPx


/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 19/10/2023
 */
class SeekbarVertical @JvmOverloads constructor(context:Context, attrs:AttributeSet? = null, defStyle:Int = 0)
    : View(context,attrs,defStyle) {
    interface Listener {
        fun onProgressChanged(value:Int)
        fun onStopTrackingTouch()
    }
    private val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private var thumbSize = dpToPx(16f)
    var listener: Listener? = null

    private val pathBackground = Path()

    private var progressBgColor = Color.WHITE
    private val thumbScaleMax = 1.3f
    private var thumbScale = 1f
    private var progress = 1
    private var maxValue = 100

    init {
        paint.apply {
            flags = Paint.ANTI_ALIAS_FLAG
            paint.style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newW = (thumbSize*thumbScaleMax).toInt()
        setMeasuredDimension(newW, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawThumb(canvas)


    }
    private fun drawBackground(canvas: Canvas){
        val centerX = width/2f
        val halfThumb = thumbSize/2
        val halfThumbScale = halfThumb*thumbScaleMax
        pathBackground.apply {
            reset()
            moveTo(centerX-halfThumb,halfThumbScale)
            lineTo(centerX+halfThumb,halfThumbScale)
            lineTo(centerX,height-halfThumbScale)
            close()
        }
        paint.apply {
            color = progressBgColor
            this.alpha = (0.6*255).toInt()
        }
        canvas.drawPath(pathBackground,paint)
    }

    private fun drawThumb(canvas: Canvas) {
        val mHeight = height - thumbSize*thumbScaleMax
        val centerX = width/2f
        val halfThumb = (thumbSize * thumbScale) / 2
        val thumbPos = ((maxValue-progress).toFloat()/maxValue) * mHeight + halfThumb
        paint.apply {
            color = progressBgColor
            this.alpha = 255
        }
        canvas.drawCircle(centerX,thumbPos,halfThumb,paint)
    }

    fun getProgress(y:Float): Int {
        val thumbY = if(y<=0f){
            0f
        } else if(y>=measuredHeight){
            measuredHeight.toFloat()
        } else{
            y
        }
        return 100 - (thumbY / measuredHeight * 100).toInt()
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        postInvalidate()
    }

    private var canTouch = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    canTouch = true
                    thumbScale = thumbScaleMax
                    invalidate()
                }

                MotionEvent.ACTION_UP -> {
                    thumbScale = 1f
                    invalidate()
                    canTouch = false
                    performClick()
                    listener?.onStopTrackingTouch()
                }

                MotionEvent.ACTION_CANCEL -> {
                }

                MotionEvent.ACTION_MOVE -> {
                    if(canTouch){
                        progress = getProgress(it.y)
                        invalidate()
                        listener?.onProgressChanged(progress)
                    } else{}
                }

                else -> {}
            }
        }
        return true
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.dispatchTouchEvent(event)
    }
}