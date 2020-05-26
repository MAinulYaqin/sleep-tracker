package com.gabutproject.napapp.sleepdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gabutproject.napapp.R
import com.gabutproject.napapp.database.SleepDatabase
import com.gabutproject.napapp.databinding.FragmentSleepDetailBinding

class SleepDetailFragment : Fragment() {
    private lateinit var binding: FragmentSleepDetailBinding
    private lateinit var viewModel: SleepDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail, container, false)

        // we need application to get reference to this layout
        // after that create an instance to sleepDatabase with this layout
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        // we need to get the arguments from this layout and pass it to viewModelFactory
        // and pass it again to ViewModel
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = SleepDetailViewModelFactory(arguments.id, dataSource)

        // define viewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepDetailViewModel::class.java)

        binding.sleepDetailViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}