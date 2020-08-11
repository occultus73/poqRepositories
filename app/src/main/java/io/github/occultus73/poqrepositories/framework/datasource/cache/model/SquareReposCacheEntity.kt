package io.github.occultus73.poqrepositories.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Square_Repos")
data class SquareReposCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "name")
    val name: String
)