package com.cemsofwave.gunsimulator.utils

import android.content.Context
import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.widget.ImageView
import com.google.android.gms.common.util.IOUtils

class MyAnimationDrawable {
    data class Frame(val data: ByteArray, val duration: Int, var drawable: Drawable? = null, var isRecycled: Boolean = false)

    interface OnAnimationLoadedListener {
        fun onLoaded(frames: List<Frame>)
    }

    fun loadAnimation(resourceId: Int, context: Context, listener: OnAnimationLoadedListener?) {
        val frames = parseAnimation(context.resources.getXml(resourceId), context)
        listener?.onLoaded(frames)
//        playAnimation(frames, imageView, null)
    }

    fun playAnimation(frames: List<Frame>, imageView: ImageView?, onEnd: Runnable?) {
        playFrame(frames, imageView, onEnd, 0)
    }

    private fun playFrame(
        frames: List<Frame>, imageView: ImageView?, onEnd: Runnable?, currentFrame: Int
    ) {
        val frame = frames[currentFrame]
        if (currentFrame == 0) {
            val bitmap = BitmapFactory.decodeByteArray(frame.data, 0, frame.data.size)
            frame.drawable = BitmapDrawable(imageView!!.context.resources, bitmap)
        } else {
            // Recycle previous frame's bitmap if needed
            try {
                (frames[currentFrame - 1].drawable as BitmapDrawable)
                frames[currentFrame - 1].drawable = null
                frames[currentFrame - 1].isRecycled = false
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        imageView?.setImageDrawable(frame.drawable)
        Handler(imageView!!.context.mainLooper).postDelayed({
            if (imageView.drawable === frame.drawable) {
                val nextFrame = currentFrame + 1
                if (nextFrame < frames.size) {
                    if (frames[nextFrame].isRecycled) {
                        playFrame(frames, imageView, onEnd, nextFrame)
                    } else {
                        frames[nextFrame].isRecycled = true
                    }
                } else {
                    onEnd?.run()
                }
            }
        }, frame.duration.toLong())

        if (currentFrame + 1 < frames.size) {
            Thread {
                prepareNextFrame(frames, currentFrame, imageView, onEnd)
            }.start()
        }
    }

    private fun prepareNextFrame(frames: List<Frame>, currentFrame: Int, imageView: ImageView?, onEnd: Runnable?) {
        try {
            val nextFrame = frames[currentFrame + 1]
            val bitmap = BitmapFactory.decodeByteArray(nextFrame.data, 0, nextFrame.data.size)
            nextFrame.drawable = BitmapDrawable(imageView!!.context.resources, bitmap)
            if (nextFrame.isRecycled) {
                playFrame(frames, imageView, onEnd, currentFrame + 1)
            } else {
                nextFrame.isRecycled = true
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun parseAnimation(parser: XmlResourceParser, context: Context): List<Frame> {
        val frames = ArrayList<Frame>()
        try {
            var eventType = parser.eventType
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG && parser.name == "item") {
                    var data: ByteArray? = null
                    var duration = 100
                    for (i in 0 until parser.attributeCount) {
                        val name = parser.getAttributeName(i)
                        val value = parser.getAttributeValue(i)
                        when (name) {
                            "drawable" -> data = IOUtils.toByteArray(context.resources.openRawResource(value.substring(1).toInt()))
                            "duration" -> duration = parser.getAttributeIntValue(i, 100)
                        }
                    }
                    frames.add(Frame(data!!, duration))
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            parser.close()
        }
        return frames
    }
}