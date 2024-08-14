package com.data.database.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.data.database.dao.JobDao
import com.data.database.entity.JobEntity
import com.data.database.network.CalendarServer
import com.domain.i_repository.ICalendarRepository
import com.domain.model.calendar.Calendar
import com.domain.model.calendar.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class CalendarRepository
constructor(
    private val provideRetrofit: Retrofit,
    private val providesJobDao: JobDao
) : ICalendarRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun findCalendar(): Flow<List<Calendar>> = flow {
        val calendarListRes: List<Calendar> =
            provideRetrofit.create(CalendarServer::class.java).fetchCalendar().body()!!
                .map { it.toCalendar() }

        val jobListRes: List<Job> = calendarListRes.flatMap { it.jobs }.toList()

        val jobMap: Map<Int, JobEntity> = providesJobDao.getAllJobs().associateBy { it.id!! }

        jobListRes.map {
            if (jobMap.get(it.id) != null) {
                it.isSelected = jobMap.get(it.id)!!.isSelected
            } else {
                it.isSelected = false
            }
        }

        emit(calendarListRes)
    }


    override suspend fun insertJobToDB(job: Job) {
        val jobEntity = JobEntity(null, job.id, true)
        providesJobDao.insertJob(jobEntity)
    }

    override suspend fun updateIsSelected(job: Job) {
        val jobEntity = providesJobDao.getJobById(job.id)

        if (jobEntity == null) {
            providesJobDao.insertJob(JobEntity(null, job.id, true))
        } else {
            providesJobDao.updateJob(JobEntity(jobEntity.id, job.id, job.isSelected))
        }
    }
}