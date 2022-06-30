package com.nsystem.features.reposearch.di

import com.nsystem.features.reposearch.data.repository.RepoDataRepository
import com.nsystem.features.reposearch.domain.repository.RepoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsRepoRepository(
        repoRepository: RepoDataRepository
    ): RepoRepository
}