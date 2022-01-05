package com.nsystem.features.reposearch.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nsystem.features.reposearch.data.model.RepoEntity

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepoEntity>)

    @Query("SELECT * FROM repo WHERE full_name LIKE :queryString OR description LIKE :queryString")
    fun searchRepo(queryString: String): PagingSource<Int, RepoEntity>

    @Query("DELETE FROM repo")
    suspend fun clearRepo()
}