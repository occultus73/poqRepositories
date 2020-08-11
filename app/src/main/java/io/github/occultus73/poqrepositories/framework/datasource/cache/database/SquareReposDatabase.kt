package io.github.occultus73.poqrepositories.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.occultus73.poqrepositories.framework.datasource.cache.model.SquareReposCacheEntity

@Database(entities = [SquareReposCacheEntity::class], version = 1)
abstract class SquareReposDatabase : RoomDatabase() {

    abstract fun squareReposDao(): SquareReposDao

    companion object {
        const val DATABASE_NAME = "square_repos_db"
    }
}