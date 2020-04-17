package com.jpaya.dynamicfeatures.abbreviations.ui

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.jpaya.core.network.NetworkState
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSourceFactory
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [AbbreviationsListFragment].
 *
 * @see ViewModel
 */
class AbbreviationsListViewModel
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: AbbreviationsPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    AbbreviationsListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    AbbreviationsListViewState.Empty
                } else {
                    AbbreviationsListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    AbbreviationsListViewState.AddLoading
                } else {
                    AbbreviationsListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    AbbreviationsListViewState.AddError
                } else {
                    AbbreviationsListViewState.Error
                }
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Refresh abbreviations fetch them again and update the list.
     */
//    fun refreshLoadedAbbreviationsList() {
//        dataSourceFactory.refresh()
//    }
}
