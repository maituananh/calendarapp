package com.domain.i_repository

import com.domain.model.calendar.Calendar
import com.domain.model.calendar.Job
import kotlinx.coroutines.flow.Flow

interface ICalendarRepository {
    suspend fun findCalendar(): Flow<List<Calendar>>
    suspend fun insertJobToDB(job: Job)
    suspend fun updateIsSelected(job: Job)
}