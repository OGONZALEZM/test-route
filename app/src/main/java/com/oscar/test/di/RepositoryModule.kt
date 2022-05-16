package com.oscar.test.di

import android.app.Application
import android.content.Context
import com.oscar.test.network.APIService
import com.oscar.test.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideMainRepository (service: APIService): MainRepository {
        return MainRepository(service)
    }

}
