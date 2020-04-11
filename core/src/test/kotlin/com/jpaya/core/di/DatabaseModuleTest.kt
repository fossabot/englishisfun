package com.jpaya.core.di

import android.content.Context
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.jpaya.core.database.MarvelDatabase
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.core.di.modules.DatabaseModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DatabaseModuleTest {

    private lateinit var databaseModule: DatabaseModule

    @Before
    fun setUp() {
        databaseModule = DatabaseModule()
    }

    @Test
    fun verifyProvidedMarvelDatabase() {
        val context: Context = mock()
        val marvelDatabase = databaseModule.provideMarvelDatabase(context)

        assertNotNull(marvelDatabase.characterFavoriteDao())
    }

    @Test
    fun verifyProvidedCharacterFavoriteDao() {
        val marvelDatabase: MarvelDatabase = mock()
        val characterFavoriteDao: CharacterFavoriteDao = mock()

        doReturn(characterFavoriteDao).whenever(marvelDatabase).characterFavoriteDao()

        assertEquals(
            characterFavoriteDao,
            databaseModule.provideCharacterFavoriteDao(marvelDatabase)
        )
        verify(marvelDatabase).characterFavoriteDao()
    }

    @Test
    fun verifyProvidedCharacterFavoriteRepository() {
        val characterFavoriteDao: CharacterFavoriteDao = mock()
        val repository = databaseModule.provideCharacterFavoriteRepository(characterFavoriteDao)

        assertEquals(characterFavoriteDao, repository.characterFavoriteDao)
    }
}
