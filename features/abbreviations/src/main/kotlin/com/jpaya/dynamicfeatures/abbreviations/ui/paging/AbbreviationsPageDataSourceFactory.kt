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

package com.jpaya.dynamicfeatures.abbreviations.ui.paging

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem
import javax.inject.Inject
import javax.inject.Provider

/**
 * Data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI.
 *
 * @see DataSource.Factory
 */
class AbbreviationsPageDataSourceFactory
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val providerDataSource: Provider<AbbreviationsPageDataSource>
) : DataSource.Factory<Int, AbbreviationItem>() {

    var sourceLiveData = MutableLiveData<AbbreviationsPageDataSource>()

    /**
     * Create a DataSource.
     *
     * @return The new DataSource.
     * @see DataSource.Factory.create
     */
    override fun create(): DataSource<Int, AbbreviationItem> {
        val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    /**
     * Force refresh data source by invalidating and re-create again.
     */
    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    /**
     * Force retry last fetch operation on data source.
     */
    fun retry() {
        sourceLiveData.value?.retry()
    }
}
