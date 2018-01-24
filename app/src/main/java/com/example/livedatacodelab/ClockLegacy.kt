package com.example.livedatacodelab

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.util.*

class ClockLegacy(private val appContext: Context) {

    interface ClockListener {
        fun onReceive(date: Date)
    }

    private var listener: ClockListener? = null

    private val timeTickBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (listener != null) {
                listener!!.onReceive(Calendar.getInstance().time)
            }
        }
    }

    fun setClockListener(listener: ClockListener) {
        if (this.listener != null) {
            return
        }
        this.listener = listener
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        appContext.registerReceiver(timeTickBroadcastReceiver, intentFilter)
    }

    fun removeClockListener() {
        listener = null
        appContext.unregisterReceiver(timeTickBroadcastReceiver)
    }
}