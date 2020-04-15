package com.jpaya.core.database.characterfavorite

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import javax.inject.Inject

/**
 * Repository module for handling character favorite data operations [CharacterFavoriteDao].
 */
class CharacterFavoriteRepository @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val characterFavoriteDao: CharacterFavoriteDao
) {

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return [LiveData] List with favorite characters.
     */
    fun getAllCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>> =
        characterFavoriteDao.getAllCharactersFavoriteLiveData()

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return List with favorite characters.
     */
    suspend fun getAllCharactersFavorite(): List<CharacterFavorite> =
        characterFavoriteDao.getAllCharactersFavorite()

    /**
     * Obtain database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     *
     * @return Favorite character if exist, otherwise null
     */
    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite? =
        characterFavoriteDao.getCharacterFavorite(characterFavoriteId)

    /**
     * Delete all database favorite characters.
     */
    suspend fun deleteAllCharactersFavorite() =
        characterFavoriteDao.deleteAllCharactersFavorite()

    /**
     * Delete database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     */
    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long) =
        characterFavoriteDao.deleteCharacterFavoriteById(characterFavoriteId)

    /**
     * Delete database favorite character.
     *
     * @param character Character favorite.
     */
    suspend fun deleteCharacterFavorite(character: CharacterFavorite) =
        characterFavoriteDao.deleteCharacterFavorite(character)

    /**
     * Add to database a list of favorite characters.
     *
     * @param characters List of favorite characters.
     */
    suspend fun insertCharactersFavorites(characters: List<CharacterFavorite>) =
        characterFavoriteDao.insertCharactersFavorites(characters)

    /**
     * Add to database a favorite character.
     *
     * @param id Character identifier.
     * @param name Character name.
     * @param imageUrl Character thumbnail url.
     */
    suspend fun insertCharacterFavorite(id: Long, name: String, imageUrl: String) {
        val characterFavorite = CharacterFavorite(
            id = id,
            name = name,
            imageUrl = imageUrl
        )
        characterFavoriteDao.insertCharacterFavorite(characterFavorite)
    }
}
