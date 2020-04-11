package com.jpaya.core.database.characterfavorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity represents when a user adds a character to favorite, containing the different
 * info required for display on screen.
 */
@Entity(tableName = "character_favorite")
data class CharacterFavorite(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String
)
