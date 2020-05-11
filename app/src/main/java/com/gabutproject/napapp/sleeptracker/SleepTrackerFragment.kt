package com.gabutproject.napapp.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gabutproject.napapp.R
import com.gabutproject.napapp.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSleepTrackerBinding>(
            inflater,
            R.layout.fragment_sleep_tracker,
            container,
            false
        )

        return binding.root
    }
}