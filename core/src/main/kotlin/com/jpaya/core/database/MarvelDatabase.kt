package com.jpaya.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpaya.core.BuildConfig
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao

/**
 * Marvel room database storing the different requested information like: characters, comics, etc...
 *
 * @see Database
 */
@Database(
    entities = [CharacterFavorite::class],
    exportSchema = BuildConfig.MARVEL_DATABASE_EXPORT_SCHEMA,
    version = BuildConfig.MARVEL_DATABASE_VERSION
)
abstract class MarvelDatabase : RoomDatabase() {

    /**
     * Get character favorite data access object.
     *
     * @return Character favorite dao.
     */
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
}
