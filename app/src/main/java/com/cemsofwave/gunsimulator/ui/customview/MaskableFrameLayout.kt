package com.cemsofwave.gunsimulator.ui.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import com.cemsofwave.gunsimulator.R

class MaskableFrameLayout : FrameLayout {
    private var mHandler: Handler? = null

    //Mask props
    var drawableMask: Drawable? = null
        private set
    private var mFinalMask: Bitmap? = null
    private val mAntiAliasing = false

    //Drawing props
    private var mPaint: Paint? = null
    private var mPorterDuffXferMode: PorterDuffXfermode? = null

    constructor(context: Context?) : super(context!!)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        construct(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        construct(context, attrs)
    }

    private fun construct(context: Context, attrs: AttributeSet?) {
        mHandler = Handler()
        isDrawingCacheEnabled = true
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(LAYER_TYPE_SOFTWARE, null) //Only works for software layers
        }
        mPaint = createPaint(false)
        val theme = context.theme
        if (theme != null) {
            val a = theme.obtainStyledAttributes(
                attrs,
                R.styleable.MaskableLayout,
                0, 0
            )
            try {
                //Load the mask if specified in xml
                initMask(loadMask(a))
                //Load the mode if specified in xml
                mPorterDuffXferMode = getModeFromInteger(
                    a.getInteger(R.styleable.MaskableLayout_porterduffxfermode, 0)
                )
                initMask(drawableMask)
                //Check antiAlias
                if (a.getBoolean(R.styleable.MaskableLayout_anti_aliasing, false)) {
                    //Recreate paint with anti aliasing enabled
                    //This can take a performance hit.
                    mPaint = createPaint(true)
                }
            } finally {
                if (a != null) {
                    a.recycle()
                }
            }
        } else {
            log("Couldn't load theme, mask in xml won't be loaded.")
        }
        registerMeasure()
    }

    private fun createPaint(antiAliasing: Boolean): Paint {
        val output = Paint(Paint.ANTI_ALIAS_FLAG)
        output.isAntiAlias = antiAliasing
        output.xfermode = mPorterDuffXferMode
        return output
    }

    //Mask functions
    private fun loadMask(a: TypedArray): Drawable? {
        return a.getDrawable(R.styleable.MaskableLayout_mask)
    }

    private fun initMask(input: Drawable?) {
        if (input != null) {
            drawableMask = input
            (drawableMask as? AnimationDrawable)?.callback = this
        } else {
            log("Are you sure you don't want to provide a mask ?")
        }
    }

    private fun makeBitmapMask(drawable: Drawable?): Bitmap? {
        if (drawable != null) {
            return if (measuredWidth > 0 && measuredHeight > 0) {
                val mask = Bitmap.createBitmap(
                    measuredWidth, measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(mask)
                drawable.setBounds(0, 0, measuredWidth, measuredHeight)
                drawable.draw(canvas)
                mask
            } else {
                log("Can't create a mask with height 0 or width 0. Or the layout has no children and is wrap content")
                null
            }
        } else {
            log("No bitmap mask loaded, view will NOT be masked !")
        }
        return null
    }

    fun setMask(drawableRes: Int) {
        val res = resources
        if (res != null) {
            setMask(res.getDrawable(drawableRes))
        } else {
            log("Unable to load resources, mask will not be loaded as drawable")
        }
    }

    fun setMask(input: Drawable?) {
        initMask(input)
        swapBitmapMask(makeBitmapMask(drawableMask))
        invalidate()
    }

    //Once the size has changed we need to remake the mask.
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setSize(w, h)
    }

    private fun setSize(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            if (drawableMask != null) {
                //Remake the 9patch
                swapBitmapMask(makeBitmapMask(drawableMask))
            }
        } else {
            log("Width and height must be higher than 0")
        }
    }

    //Drawing
    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (mFinalMask != null && mPaint != null) {
            mPaint!!.xfermode = mPorterDuffXferMode
            canvas.drawBitmap(mFinalMask!!, 0.0f, 0.0f, mPaint)
            mPaint!!.xfermode = null
        } else {
            log("Mask or paint is null ...")
        }
    }

    //Once inflated we have no height or width for the mask. Wait for the layout.
    private fun registerMeasure() {
        val treeObserver = this@MaskableFrameLayout.viewTreeObserver
        if (treeObserver != null && treeObserver.isAlive) {
            treeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    var aliveObserver = treeObserver
                    if (!aliveObserver.isAlive) {
                        aliveObserver = this@MaskableFrameLayout.viewTreeObserver
                    }
                    if (aliveObserver != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            aliveObserver.removeOnGlobalLayoutListener(this)
                        } else {
                            aliveObserver.removeGlobalOnLayoutListener(this)
                        }
                    } else {
                        log("GlobalLayoutListener not removed as ViewTreeObserver is not valid")
                    }
                    swapBitmapMask(makeBitmapMask(drawableMask))
                }
            })
        }
    }

    //Logging
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    //Animation
    override fun invalidateDrawable(dr: Drawable) {
        if (dr != null) {
            initMask(dr)
            swapBitmapMask(makeBitmapMask(dr))
            invalidate()
        }
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        if (who != null && what != null) {
            mHandler!!.postAtTime(what, `when`)
        }
    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        if (who != null && what != null) {
            mHandler!!.removeCallbacks(what)
        }
    }

    private fun swapBitmapMask(newMask: Bitmap?) {
        if (newMask != null) {
            if (mFinalMask != null && !mFinalMask!!.isRecycled) {
                mFinalMask!!.recycle()
            }
            mFinalMask = newMask
        }
    }

    //Utils
    private fun getModeFromInteger(index: Int): PorterDuffXfermode {
        var mode: PorterDuff.Mode? = null
        when (index) {
            MODE_ADD -> {
                if (Build.VERSION.SDK_INT >= 11) {
                    mode = PorterDuff.Mode.ADD
                } else {
                    log("MODE_ADD is not supported on api lvl " + Build.VERSION.SDK_INT)
                }
                mode = PorterDuff.Mode.CLEAR
            }

            MODE_CLEAR -> mode = PorterDuff.Mode.CLEAR
            MODE_DARKEN -> mode = PorterDuff.Mode.DARKEN
            MODE_DST -> mode = PorterDuff.Mode.DST
            MODE_DST_ATOP -> mode = PorterDuff.Mode.DST_ATOP
            MODE_DST_IN -> mode = PorterDuff.Mode.DST_IN
            MODE_DST_OUT -> mode = PorterDuff.Mode.DST_OUT
            MODE_DST_OVER -> mode = PorterDuff.Mode.DST_OVER
            MODE_LIGHTEN -> mode = PorterDuff.Mode.LIGHTEN
            MODE_MULTIPLY -> mode = PorterDuff.Mode.MULTIPLY
            MODE_OVERLAY -> {
                if (Build.VERSION.SDK_INT >= 11) {
                    mode = PorterDuff.Mode.OVERLAY
                } else {
                    log("MODE_OVERLAY is not supported on api lvl " + Build.VERSION.SDK_INT)
                }
                mode = PorterDuff.Mode.SCREEN
            }

            MODE_SCREEN -> mode = PorterDuff.Mode.SCREEN
            MODE_SRC -> mode = PorterDuff.Mode.SRC
            MODE_SRC_ATOP -> mode = PorterDuff.Mode.SRC_ATOP
            MODE_SRC_IN -> mode = PorterDuff.Mode.SRC_IN
            MODE_SRC_OUT -> mode = PorterDuff.Mode.SRC_OUT
            MODE_SRC_OVER -> mode = PorterDuff.Mode.SRC_OVER
            MODE_XOR -> mode = PorterDuff.Mode.XOR
            else -> mode = PorterDuff.Mode.DST_IN
        }
        log("Mode is $mode")
        return PorterDuffXfermode(mode)
    }

    companion object {
        //Constants
        private const val TAG = "MaskableFrameLayout"
        private const val MODE_ADD = 0
        private const val MODE_CLEAR = 1
        private const val MODE_DARKEN = 2
        private const val MODE_DST = 3
        private const val MODE_DST_ATOP = 4
        private const val MODE_DST_IN = 5
        private const val MODE_DST_OUT = 6
        private const val MODE_DST_OVER = 7
        private const val MODE_LIGHTEN = 8
        private const val MODE_MULTIPLY = 9
        private const val MODE_OVERLAY = 10
        private const val MODE_SCREEN = 11
        private const val MODE_SRC = 12
        private const val MODE_SRC_ATOP = 13
        private const val MODE_SRC_IN = 14
        private const val MODE_SRC_OUT = 15
        private const val MODE_SRC_OVER = 16
        private const val MODE_XOR = 17
    }
}