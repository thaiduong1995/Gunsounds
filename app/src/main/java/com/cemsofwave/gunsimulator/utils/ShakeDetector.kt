package com.cemsofwave.gunsimulator.utils

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlin.math.sqrt

class ShakeDetector : SensorEventListener {

    private val SHAKE_THRESHOLD_GRAVITY = 25f // Ngưỡng gia tốc để coi là lắc
    private val SHAKE_SLOP_TIME_MS = 200 // Khoảng thời gian cho phép giữa hai lần lắc
    private val SHAKE_COUNT_RESET_TIME_MS = 1000 // Thời gian để đặt lại số lần lắc

    private var mShakeTimestamp: Long = 0
    private var mShakeCount: Int = 0
    private var mListener: OnShakeListener? = null

    private var mLastShakeResetTime: Long = 0 // Biến mới để theo dõi thời gian đặt lại số lần lắc


    interface OnShakeListener {
        fun onShake(count: Int)
    }

    fun setOnShakeListener(listener: OnShakeListener) {
        mListener = listener
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (mListener != null) {
            val x = event.values[0]

            val gForce = sqrt((x * x + 0f + 0f).toDouble())

            if (gForce >= SHAKE_THRESHOLD_GRAVITY) {
                val now = System.currentTimeMillis()

                if (mShakeTimestamp.toInt() != 0 && now - mShakeTimestamp < SHAKE_SLOP_TIME_MS) {
                    mShakeCount++
                } else {
                    mShakeCount = 1
                }

                mShakeTimestamp = now

                mListener?.onShake(mShakeCount)

                if (now - mLastShakeResetTime >= SHAKE_COUNT_RESET_TIME_MS) {
                    mShakeCount = 0
                    mLastShakeResetTime = now
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Bỏ qua
    }
}

