package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite.di

import androidx.lifecycle.ViewModel
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharactersFavoriteModuleTest {

    @MockK
    lateinit var fragment: CharactersFavoriteFragment
    private lateinit var module: CharactersFavoriteModule

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersFavoriteModule_ShouldSetUpCorrectly() {
        module = CharactersFavoriteModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersFavoriteViewModel() {
        mockkStatic("com.jpaya.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersFavoriteViewModel>()

        val factoryCaptor = slot<() -> CharactersFavoriteViewModel>()
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        module = CharactersFavoriteModule(fragment)
        module.providesCharactersFavoriteViewModel(favoriteRepository)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(favoriteRepository, factoryCaptor.captured().characterFavoriteRepository)
    }

    @Test
    fun verifyProvidedCharactersFavoriteAdapter() {
        module = CharactersFavoriteModule(fragment)

        assertNotNull(module.providesCharactersFavoriteAdapter())
    }
}
