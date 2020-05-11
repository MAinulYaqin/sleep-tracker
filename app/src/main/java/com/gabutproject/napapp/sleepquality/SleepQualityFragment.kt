package com.gabutproject.napapp.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gabutproject.napapp.databinding.FragmentSleepQualityBinding
import com.gabutproject.napapp.R

class SleepQualityFragment : Fragment() {

    private lateinit var binding: FragmentSleepQualityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)

        return binding.root
    }
}