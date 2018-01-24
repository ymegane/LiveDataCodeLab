package com.example.livedatacodelab

import android.arch.lifecycle.MutableLiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.util.*

class ClockLiveData(private val appContext: Context) : MutableLiveData<Date>() {

    private val timeTickBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("CodeLab", "onReceive")
            postValue(Calendar.getInstance().time)
        }
    }

    override fun onActive() {
        Log.d("CodeLab", "onActive")
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        appContext.registerReceiver(timeTickBroadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        Log.d("CodeLab", "onInactive")
        appContext.unregisterReceiver(timeTickBroadcastReceiver)
    }
}