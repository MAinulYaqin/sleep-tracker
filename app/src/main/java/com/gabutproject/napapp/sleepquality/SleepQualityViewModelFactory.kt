package com.gabutproject.napapp.sleepquality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabutproject.napapp.database.SleepDatabaseDao
import java.lang.IllegalArgumentException


class SleepQualityViewModelFactory(private val nightId: Long, private val datasource: SleepDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(nightId, datasource) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }

}