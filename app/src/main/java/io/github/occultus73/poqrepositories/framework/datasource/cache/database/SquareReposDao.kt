package io.github.occultus73.poqrepositories.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity

@Dao
interface SquareReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(squareReposEntity: SquareReposCacheEntity): Long

    @Query("SELECT * FROM Square_Repos")
    suspend fun get(): List<SquareReposCacheEntity>
}