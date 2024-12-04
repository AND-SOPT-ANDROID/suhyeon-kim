package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.remote.datasource.UserDataRemoteSource
import org.sopt.and.data.remote.datasourceimpl.UserDataRemoteSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserDataRemoteSource(
        userDataRemoteSourceImpl: UserDataRemoteSourceImpl
    ): UserDataRemoteSource
}