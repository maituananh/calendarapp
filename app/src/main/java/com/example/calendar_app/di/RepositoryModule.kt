package com.example.calendar_app.di

import com.data.database.dao.JobDao
import com.data.database.repository.CalendarRepository
import com.domain.i_repository.ICalendarRepository
import com.domain.i_repository.IUserRepository
import com.data.database.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCalendarRepository(
        provideCalendarRetrofit: Retrofit,
        providesJobDao: JobDao
    ): ICalendarRepository =
        CalendarRepository(provideCalendarRetrofit, providesJobDao)

    @Provides
    @Singleton
    fun provideUserRepository(): IUserRepository = UserRepository()
}