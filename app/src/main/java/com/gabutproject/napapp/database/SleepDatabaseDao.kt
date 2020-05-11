package com.gabutproject.napapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao {
    @Insert
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    // manual query, because there's no convenience annotation
    // for the remaining functions

    // get single item from list
    @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    // delete all entries inside
    // didn't use @Delete from room, it's effective to deletes specific
    // but not efficient for clearing entries
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    // Select the latest items from database,
    // and add LIMIT 1 so it will return 1 item
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?

    // Grab all items from database
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>
}