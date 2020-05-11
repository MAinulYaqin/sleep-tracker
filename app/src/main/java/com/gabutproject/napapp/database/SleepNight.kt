package com.gabutproject.napapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var nightId: Long = 0L,

    @ColumnInfo(name = "startTimeMillis")
    val startTimeMillis: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "endTimeMillis")
    var endTimeMillis: Long = startTimeMillis,
    @ColumnInfo(name = "sleepQuality")
    var sleepQuality: Int = -1
)