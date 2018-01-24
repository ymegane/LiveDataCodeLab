package com.example.livedatacodelab

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import java.text.SimpleDateFormat
import java.util.*

class ClockViewModel(application: Application) : AndroidViewModel(application) {

    val stringClock: LiveData<String>
    private val clock: ClockLiveData by lazy { ClockLiveData(application) }

    init {
        stringClock = Transformations.map(clock, Function {
            return@Function SimpleDateFormat("HH:mm", Locale.US).format(it)
        })
    }
}