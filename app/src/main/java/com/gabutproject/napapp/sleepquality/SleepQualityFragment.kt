package com.gabutproject.napapp.sleepquality

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabutproject.napapp.databinding.FragmentSleepQualityBinding
import com.gabutproject.napapp.R
import com.gabutproject.napapp.database.SleepDatabase

class SleepQualityFragment : Fragment() {

    private lateinit var binding: FragmentSleepQualityBinding

    private lateinit var viewModel: SleepQualityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(activity).application
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val datasource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory =
            SleepQualityViewModelFactory(arguments.sleepNightKey, datasource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = viewModel

        viewModel.onNavigateUp.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())

                viewModel.onNavigateUpCompleted()
            }
        })

        return binding.root
    }
}