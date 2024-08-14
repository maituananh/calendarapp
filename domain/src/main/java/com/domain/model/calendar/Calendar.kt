package com.domain.model.calendar

import java.time.LocalDate

class Calendar (
    val date: LocalDate?,
    val day: Int?,
    val jobs: List<Job>,
) {
}