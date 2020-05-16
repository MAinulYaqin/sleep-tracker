package com.gabutproject.napapp.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SleepDatabaseDao {
    // 1. we need insert data to SleepDatabse
    @Insert
    fun insert(night: SleepNight)

    // 2. after user push the start and data created, the data need to be updated
    //    later to change end endTimeMillis
    @Update
    fun update(night: SleepNight)

    // 3. we need to get specific item to update based on key
    @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    // 4. user can push clearButton to clear data from database
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    // 5. grep the latest data, in summary, tonight
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight? // put null check, there's case when data is not stored yet

    // 6. we need to get all nights / get data to show to the list
    @Query("SELECT * FROM daily_sleep_quality_table")
    fun getAllNights(): LiveData<List<SleepNight>>
}