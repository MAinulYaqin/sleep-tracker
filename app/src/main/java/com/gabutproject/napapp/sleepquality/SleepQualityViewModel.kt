package com.gabutproject.napapp.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabutproject.napapp.database.SleepDatabaseDao
import kotlinx.coroutines.*

class SleepQualityViewModel(private val nightId: Long, val database: SleepDatabaseDao) :
    ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _onNavigateUp = MutableLiveData<Boolean>()
    val onNavigateUp: LiveData<Boolean> get() = _onNavigateUp

    fun onNavigateUpCompleted() {
        _onNavigateUp.value = false
    }

    fun onSetSleepQuality(quality: Int) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val tonight = database.get(nightId) ?: return@withContext
                tonight.sleepQuality = quality

                database.update(tonight)
            }
        }
        _onNavigateUp.value = true
    }
}