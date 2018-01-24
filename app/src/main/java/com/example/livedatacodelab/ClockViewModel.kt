package com.example.livedatacodelab

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import java.util.*

class ClockViewModel(application: Application) : AndroidViewModel(application) {

    private val clock: ClockLiveData by lazy { ClockLiveData(application) }

    fun getDate(): LiveData<Date> = clock
}