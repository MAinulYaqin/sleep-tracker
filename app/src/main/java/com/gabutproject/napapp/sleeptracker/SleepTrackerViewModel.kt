package com.gabutproject.napapp.sleeptracker

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.gabutproject.napapp.database.SleepDatabaseDao
import com.gabutproject.napapp.database.SleepNight
import com.gabutproject.napapp.formatNights
import kotlinx.coroutines.*


class SleepTrackerViewModel(private val database: SleepDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val _onNavigateToSleepQuality = MutableLiveData<SleepNight>()
    val onNavigateToSleepQuality: LiveData<SleepNight> get() = _onNavigateToSleepQuality

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // viewModel update the UI with LiveData after some processing
    // so we need the Coroutine to run in the Main thread
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _tonight = MutableLiveData<SleepNight?>()

    private val nights = database.getAllNights()

    var nightsString: LiveData<Spanned>? = Transformations.map(nights) {
        formatNights(it, application.resources)
    }

    init {
        initializeNights()
    }

    private fun initializeNights() {
        uiScope.launch {
            _tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()

            // initially, the endTime & startTime is the same value
            // but the endTime will updated when button stop presses
            // so if the user start the app and close it, then restart it in the morning, there's no value
            if (night?.endTimeMillis != night?.startTimeMillis) {
                night = null
            }

            night
        }
    }

    fun onStartTicking() {
        uiScope.launch {
            val newNight = SleepNight()

            insert(newNight)

            _tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    fun onStopTicking() {
        uiScope.launch {
            // null check, and set endTime to current Time
            val oldNight = _tonight.value ?: return@launch
            oldNight.endTimeMillis = System.currentTimeMillis()

            // update liveData
            _onNavigateToSleepQuality.value = oldNight

            update(oldNight) // update database
        }
    }

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            _tonight.value = null
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onNavigateCompleted() {
        _tonight.value = null
    }
}