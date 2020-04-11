package com.jpaya.core.database

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MarvelDatabaseTest : TestRobolectric() {

    @Mock
    lateinit var marvelDatabase: MarvelDatabase
    @Mock
    lateinit var characterFavoriteDao: CharacterFavoriteDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun obtainCharacterFavoriteDao() {
        doReturn(characterFavoriteDao).whenever(marvelDatabase).characterFavoriteDao()

        assertThat(
            marvelDatabase.characterFavoriteDao(),
            instanceOf(CharacterFavoriteDao::class.java)
        )
    }
}
