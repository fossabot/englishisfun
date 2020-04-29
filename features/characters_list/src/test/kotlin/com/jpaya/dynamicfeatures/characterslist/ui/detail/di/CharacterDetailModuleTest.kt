package com.jpaya.dynamicfeatures.characterslist.ui.detail.di

import androidx.lifecycle.ViewModel
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment
import com.jpaya.dynamicfeatures.characterslist.ui.detail.CharacterDetailViewModel
import com.jpaya.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterDetailModuleTest {

    @MockK
    lateinit var fragment: CharacterDetailFragment
    lateinit var module: CharacterDetailModule

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharacterDetailModule_ShouldSetUpCorrectly() {
        module = CharacterDetailModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharacterDetailViewModel() {
        mockkStatic("com.jpaya.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharacterDetailViewModel>()

        val factoryCaptor = slot<() -> CharacterDetailViewModel>()
        val marvelRepository = mockk<MarvelRepository>(relaxed = true)
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        val mapper = mockk<CharacterDetailMapper>(relaxed = true)
        module = CharacterDetailModule(fragment)
        module.providesCharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = favoriteRepository,
            characterDetailMapper = mapper
        )

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        factoryCaptor.captured().run {
            assertEquals(marvelRepository, this.marvelRepository)
            assertEquals(favoriteRepository, this.characterFavoriteRepository)
            assertEquals(mapper, this.characterDetailMapper)
        }
    }
}
