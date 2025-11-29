package com.cem.firebase_module.analytics

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

/**
 * Created by Hưng Nguyễn on 28/03/2024
 * Phone: 0335236374
 * Email: nguyenhunghung2806@gmail.com
 */
class AppFlyerTrackingEvent @Inject constructor(
    private val context: Context
) : CemEventTracking {
    override fun logEvent(eventName: String, params: HashMap<String, String>?) {
        val eventValues = HashMap<String, Any>()
        params?.entries?.forEach {
            eventValues[it.key] = it.value
        }
        AppsFlyerLib.getInstance().logEvent(
            context, eventName,
            eventValues,
            object : AppsFlyerRequestListener {
                override fun onSuccess() {
                    Log.d(CemAnalytics.TAG, "Event sent successfully")
                }

                override fun onError(errorCode: Int, errorDesc: String) {
                    Log.d(
                        CemAnalytics.TAG, "Event failed to be sent:\n" +
                                "Error code: " + errorCode + "\n"
                                + "Error description: " + errorDesc
                    )
                }
            }
        )
    }

    override fun logEventView(screenName: String) {
        val eventValues = HashMap<String, Any>()
        eventValues[FirebaseAnalytics.Event.SCREEN_VIEW] = screenName
        AppsFlyerLib.getInstance().logEvent(
            context, FirebaseAnalytics.Event.SCREEN_VIEW,
            eventValues
        )
    }

    companion object {

        private var mInstance: AppFlyerTrackingEvent? = null
        fun getInstance(context: Context): AppFlyerTrackingEvent {
            return mInstance ?: synchronized(this) {
                val instance = AppFlyerTrackingEvent(context)
                mInstance = instance
                instance
            }
        }
    }
}