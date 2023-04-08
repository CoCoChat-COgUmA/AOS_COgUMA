package com.CCC.CoCoChat.DI

import com.CCC.CoCoChat.Data.Repository.LoginRepository
import com.CCC.CoCoChat.Data.RepositoryImpl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }
}