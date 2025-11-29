package com.cem.firebase_module.analytics

import android.content.Context
import android.util.Log

object CemAnalytics {

    fun logEventAndParams(context: Context, screenName:String? = "", eventName: String, params: HashMap<String, String>? = null) {
        val mScreenName = if(screenName?.length == 0){
            ""
        }else{
            "${screenName}_"
        }
        Log.d("LOG EVENT PARAMS","$mScreenName$eventName")
        TrackingEventImpl.getInstance(context).logEvent("$mScreenName$eventName", params)
    }

    fun logEventShowScreen(context: Context?,screenName:String?) {
        if(context == null || screenName == null) return
        val a = "${screenName}_show"
        Log.d("LOG EVENT SHOW","$a - ${a.length}")
        TrackingEventImpl.getInstance(context).logEventView("${screenName}_show")
    }

    fun logEventClickView(context: Context?, screenName:String?,actionName:String) {
        if(context == null || screenName == null) return
        val a = "${screenName}_click_$actionName"
        Log.d("LOG EVENT CLICK","$a - ${a.length}")
        TrackingEventImpl.getInstance(context).logEventView("${screenName}_click_$actionName")
    }

    fun logEventAndParams(
        context: Context,
        eventName: String,
        params: HashMap<String, String>? = null
    ) {
        Log.d(TAG, "logParams: $params")
        Log.d(TAG, "logEventName: $eventName")
        TrackingEventImpl.getInstance(context).logEvent(eventName, params)
    }

    fun logEventScreenView(context: Context, eventName: String) {
        TrackingEventImpl.getInstance(context).logEventView(eventName)
    }

    var TAG: String = CemAnalytics::class.java.simpleName
}