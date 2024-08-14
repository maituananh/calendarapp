package com.data.database.network.response.calender

import com.domain.model.calendar.Job
import com.domain.model.calendar.JobStatus
import com.example.data.network.response.calender.JobStatusRes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JobRes(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: JobStatusRes,
    @Json(name = "exercises")
    val exercises: Int
) {
    fun toJob(): Job {
        val jobStatus: JobStatus = when (status) {
            JobStatusRes.Missed -> JobStatus.MISSED
            JobStatusRes.Completed -> JobStatus.COMPLETED
            JobStatusRes.Assigned -> JobStatus.ASSIGNED
        }

        return Job(id, name, jobStatus, exercises, false)
    }
}