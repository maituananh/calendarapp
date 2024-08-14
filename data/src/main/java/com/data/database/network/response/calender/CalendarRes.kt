package com.data.database.network.response.calender

import android.os.Build
import androidx.annotation.RequiresApi
import com.domain.model.calendar.Calendar
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CalendarRes(
    @Json(name = "date")
    val date: String,
    @Json(name = "day")
    val day: Int,
    @Json(name = "jobs")
    val jobs: List<JobRes>
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toCalendar(): Calendar {
        return Calendar(LocalDate.parse(date), day, jobs.map { it.toJob() })
    }
}