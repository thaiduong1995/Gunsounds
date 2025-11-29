package com.cemsofwave.gunsimulator.base.utils

import android.os.Handler
import android.os.Looper

class Timer(listener: OnTimerTickListener, private var delay:Long) {
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private var duration = 0L
//    private var delay = 100L
    init {
        runnable = Runnable {
            duration+= delay
            handler.postDelayed(runnable,delay)
            listener.onTimerTick(duration)
        }
    }
    fun start(){
        handler.postDelayed(runnable,delay)
    }
    fun pause(){
        handler.removeCallbacks(runnable)
    }
    fun stop(){
        handler.removeCallbacks(runnable)
        duration = 0L
    }
    fun getDuration():Long = duration

    interface OnTimerTickListener{
        fun onTimerTick(duration:Long)
    }
}