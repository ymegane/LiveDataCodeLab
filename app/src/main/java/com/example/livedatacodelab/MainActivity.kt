package com.example.livedatacodelab

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //private val clock: ClockLegacy by lazy { ClockLegacy(this.applicationContext) }

    private val clockViewModel: ClockViewModel by lazy {
        ViewModelProviders.of(this).get(ClockViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lifecycleObserver = GenericLifecycleObserver { _, event ->
            Log.d("CodeLab", "onStateChanged state:$event")
        }

        lifecycle.addObserver(lifecycleObserver)

        clockViewModel.getDate().observe(this, Observer<Date> {
            Log.d("CodeLab", "onChanged")
            textTime.text = SimpleDateFormat("HH:mm", Locale.US).format(it)
        })
    }
}
