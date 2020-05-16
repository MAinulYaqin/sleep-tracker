package com.gabutproject.napapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gabutproject.napapp.database.SleepDatabaseDao
import com.gabutproject.napapp.database.SleepNight

class SleepQualityViewModel(private val nightId: Long, val database: SleepDatabaseDao) :
    ViewModel() {

    private lateinit var night: SleepNight

    init {
        Log.i("SleepQualityViewModel", nightId.toString())
    }


    fun onSetSleepQuality(quality: Int) {

    }

    private fun update(nightId: SleepNight) {
        database.update(nightId)
    }
}