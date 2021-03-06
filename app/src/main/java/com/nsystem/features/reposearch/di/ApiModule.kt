package com.nsystem.features.reposearch.di

import com.nsystem.features.reposearch.data.api.RepoApi
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
    fun provideRepoApi(retrofit: Retrofit) = retrofit.create(RepoApi::class.java)
}