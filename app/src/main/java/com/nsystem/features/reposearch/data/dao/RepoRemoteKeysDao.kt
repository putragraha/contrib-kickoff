package com.nsystem.features.reposearch.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsystem.features.reposearch.data.model.RepoRemoteKeysEntity

@Dao
interface RepoRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repoRemoteKeysEntities: List<RepoRemoteKeysEntity>)

    @Query("SELECT * FROM repo_remote_keys WHERE repo_id = :repoId")
    suspend fun getRepoRemoteKeys(repoId: Long): RepoRemoteKeysEntity?

    @Query("DELETE FROM repo_remote_keys")
    suspend fun clearRepoRemoteKeys()
}