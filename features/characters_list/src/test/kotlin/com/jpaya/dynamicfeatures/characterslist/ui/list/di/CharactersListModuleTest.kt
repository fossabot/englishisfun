package com.jpaya.dynamicfeatures.characterslist.ui.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.jpaya.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharactersListModuleTest {

    @MockK
    lateinit var fragment: CharactersListFragment
    private lateinit var module: CharactersListModule

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersListModule_ShouldSetUpCorrectly() {
        module = CharactersListModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersListViewModel() {
        mockkStatic("com.jpaya.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersListViewModel>()

        val factoryCaptor = slot<() -> CharactersListViewModel>()
        val dataFactory = mockk<CharactersPageDataSourceFactory>(relaxed = true)
        module = CharactersListModule(fragment)
        module.providesCharactersListViewModel(dataFactory)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(dataFactory, factoryCaptor.captured().dataSourceFactory)
    }

    @Test
    fun verifyProvidedCharactersPageDataSource() {
        val repository = mockk<MarvelRepository>(relaxed = true)
        val mapper = mockk<CharacterItemMapper>(relaxed = true)
        val viewModel = mockk<CharactersListViewModel>(relaxed = true)
        val scope = mockk<CoroutineScope>()
        every { viewModel.viewModelScope } returns scope

        module = CharactersListModule(fragment)
        val dataSource = module.providesCharactersPageDataSource(
            viewModel = viewModel,
            repository = repository,
            mapper = mapper
        )

        assertEquals(repository, dataSource.repository)
        assertEquals(mapper, dataSource.mapper)
        assertEquals(scope, dataSource.scope)
    }
}
