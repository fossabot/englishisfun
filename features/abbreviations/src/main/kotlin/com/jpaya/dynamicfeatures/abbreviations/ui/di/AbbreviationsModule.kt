package com.jpaya.dynamicfeatures.abbreviations.ui.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListFragment
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItemMapper
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSource
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AbbreviationsComponent].
 *
 * @see Module
 */
@Module
class AbbreviationsModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: AbbreviationsListFragment
) {

    /**
     * Create a provider method binding for [AbbreviationsListViewModel].
     *
     * @param dataFactory Data source factory for abbreviations.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesAbbreviationsListViewModel(
        dataFactory: AbbreviationsPageDataSourceFactory
    ) = fragment.viewModel {
        AbbreviationsListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [AbbreviationsPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesAbbreviationsPageDataSource(
        viewModel: AbbreviationsListViewModel,
        repository: MarvelRepository,
        mapper: AbbreviationItemMapper
    ) = AbbreviationsPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    /**
     * Create a provider method binding for [AbbreviationItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesAbbreviationItemMapper() = AbbreviationItemMapper()

    /**
     * Create a provider method binding for [AbbreviationsListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesAbbreviationsListAdapter(
        viewModel: AbbreviationsListViewModel
    ) = AbbreviationsListAdapter(viewModel)
}
