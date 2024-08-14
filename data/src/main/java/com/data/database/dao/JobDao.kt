package com.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.data.database.entity.JobEntity

@Dao
interface JobDao {

    @Query("SELECT * FROM `jobs`")
    fun getAllJobs(): List<JobEntity>

    @Query("SELECT * FROM `jobs` Where `id` in (:ids)")
    fun getAllJobsByIdIn(ids: List<Int>): List<JobEntity>

    @Query("SELECT * FROM `jobs` Where `id` = :id")
    fun getJobById(id: Int): JobEntity?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateJob(job: JobEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobEntity)
}