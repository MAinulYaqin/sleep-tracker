package com.gabutproject.napapp.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabutproject.napapp.R
import com.gabutproject.napapp.database.SleepDatabase
import com.gabutproject.napapp.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding

    private lateinit var viewModel: SleepTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sleep_tracker,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        updateLiveData()

        return binding.root
    }

    private fun updateLiveData() {

        // to navigate
        viewModel.onNavigateToSleepQuality.observe(viewLifecycleOwner, Observer { night ->
            // if the value is already updated, then navigate
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(
                        night.nightId
                    )
                )
                viewModel.onNavigateCompleted()
            }
        })
    }
}