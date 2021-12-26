package com.nsystem.features.usersearch.di

import com.nsystem.features.usersearch.data.api.RepoApi
import com.nsystem.features.usersearch.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    @Provides
    @ViewModelScoped
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)

    @Provides
    @ViewModelScoped
    fun provideRepoApi(retrofit: Retrofit) = retrofit.create(RepoApi::class.java)
}