/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.dynamicfeatures.abbreviations.ui.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.core.firebase.FireStoreProperties
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
        fireStore: FirebaseFirestore,
        fireStoreProperties: FireStoreProperties,
        viewModel: AbbreviationsListViewModel,
        mapper: AbbreviationItemMapper
    ) = AbbreviationsPageDataSource(
        fireStore = fireStore,
        fireStoreProperties = fireStoreProperties,
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
