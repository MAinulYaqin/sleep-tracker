package com.gabutproject.napapp.sleepdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabutproject.napapp.database.SleepDatabaseDao

class SleepDetailViewModelFactory(private val key: Long, private val dataSource: SleepDatabaseDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)) {
            return SleepDetailViewModel(key, dataSource) as T
        }

        throw IllegalArgumentException("Unknown modelClass viewModel")
    }

}