package com.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "job_id") var jobId: Int,
    @ColumnInfo(name = "isSelected") var isSelected: Boolean = false,
) {

}
