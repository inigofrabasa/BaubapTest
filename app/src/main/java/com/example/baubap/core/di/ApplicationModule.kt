package com.example.baubap.core.di

import com.example.baubap.feature.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginRepository.LoginDataSource): LoginRepository =
        dataSource

}
