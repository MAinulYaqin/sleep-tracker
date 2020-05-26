package com.gabutproject.napapp.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.napapp.database.SleepDatabaseDao
import com.gabutproject.napapp.database.SleepNight
import kotlinx.coroutines.Job

class SleepDetailViewModel(private val key: Long = 0L, dataSource: SleepDatabaseDao) : ViewModel() {
    // hold reference to sleepDatabase view SleepDatabaseDao
    val database = dataSource

    // data to show
    private lateinit var night: LiveData<SleepNight>

    // navigating liveData
    private val _onNavigateToSleepTracker = MutableLiveData(false)
    val onNavigateToSleepTracker: LiveData<Boolean> get() = _onNavigateToSleepTracker

    private val viewModelJob = Job()

    fun getNight() = night

    init {
        night = database.getNightById(key)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onClose() {
        _onNavigateToSleepTracker.value = true
    }

    fun onSleepDetailNavigated() {
        _onNavigateToSleepTracker.value = false
    }

}