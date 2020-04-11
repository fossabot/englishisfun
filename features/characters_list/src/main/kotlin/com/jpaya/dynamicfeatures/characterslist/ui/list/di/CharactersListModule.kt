package com.jpaya.dynamicfeatures.characterslist.ui.list.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.jpaya.dynamicfeatures.characterslist.ui.list.adapter.CharactersListAdapter
import com.jpaya.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSource
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersListComponent].
 *
 * @see Module
 */
@Module
class CharactersListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CharactersListFragment
) {

    /**
     * Create a provider method binding for [CharactersListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: CharactersPageDataSourceFactory
    ) = fragment.viewModel {
        CharactersListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [CharactersPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesCharactersPageDataSource(
        viewModel: CharactersListViewModel,
        repository: MarvelRepository,
        mapper: CharacterItemMapper
    ) = CharactersPageDataSource(
            repository = repository,
            scope = viewModel.viewModelScope,
            mapper = mapper
        )

    /**
     * Create a provider method binding for [CharacterItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterItemMapper() = CharacterItemMapper()

    /**
     * Create a provider method binding for [CharactersListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: CharactersListViewModel
    ) = CharactersListAdapter(viewModel)
}
