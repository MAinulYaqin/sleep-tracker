package com.gabutproject.napapp.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.napapp.database.SleepDatabaseDao
import com.gabutproject.napapp.database.SleepNight

class SleepDetailViewModel(private val key: Long = 0L, dataSource: SleepDatabaseDao) : ViewModel() {

    // data to show
    var night: LiveData<SleepNight> = dataSource.getNightById(key)

    // navigating liveData
    private val _onNavigateToSleepTracker = MutableLiveData(false)
    val onNavigateToSleepTracker: LiveData<Boolean> get() = _onNavigateToSleepTracker

    override fun onCleared() {
        super.onCleared()
    }

    fun onClose() {
        _onNavigateToSleepTracker.value = true
    }

    fun onSleepDetailNavigated() {
        _onNavigateToSleepTracker.value = false
    }

}