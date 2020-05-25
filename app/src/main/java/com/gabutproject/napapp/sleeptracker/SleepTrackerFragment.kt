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
import androidx.recyclerview.widget.GridLayoutManager
import com.gabutproject.napapp.R
import com.gabutproject.napapp.database.SleepDatabase
import com.gabutproject.napapp.databinding.FragmentSleepTrackerBinding
import com.google.android.material.snackbar.Snackbar

class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding
    private lateinit var viewModel: SleepTrackerViewModel
    private lateinit var adapter: SleepNightAdapter

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


        // adding layout manager, to configure the layout
        // we use gridLayout to make it looks gallery picker
        val layoutManager = GridLayoutManager(activity, 3)
        binding.sleepList.layoutManager = layoutManager

        adapter = SleepNightAdapter()

        binding.sleepList.adapter = adapter

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

        // to show snackBar
        viewModel.showSnackBar.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.onShowSnackBarCompleted()
            }
        })

        // to update the list of data to show in RecyclerView
        viewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }
}