package com.example.livedatacodelab

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.livedatacodelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private val clock: ClockLegacy by lazy { ClockLegacy(this.applicationContext) }

    private val clockViewModel: ClockViewModel by lazy {
        ViewModelProviders.of(this).get(ClockViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = clockViewModel

        val lifecycleObserver = GenericLifecycleObserver { _, event ->
            Log.d("CodeLab", "onStateChanged state:$event")
        }

        lifecycle.addObserver(lifecycleObserver)
    }
}
