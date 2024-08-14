package com.example.data.network.response.calender

import com.squareup.moshi.Json

enum class JobStatusRes {
    @Json(name = "Missed")
    Missed,
    @Json(name = "Completed")
    Completed,
    @Json(name = "Assigned")
    Assigned
}