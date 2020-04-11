package com.jpaya.dynamicfeatures.characterslist.ui.list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.jpaya.commons.ui.livedata.SingleLiveData
import com.jpaya.core.network.NetworkState
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import com.jpaya.dynamicfeatures.characterslist.ui.list.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [CharactersListFragment].
 *
 * @see ViewModel
 */
class CharactersListViewModel
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: CharactersPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val event = SingleLiveData<CharactersListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    CharactersListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    CharactersListViewState.Empty
                } else {
                    CharactersListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    CharactersListViewState.AddLoading
                } else {
                    CharactersListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    CharactersListViewState.AddError
                } else {
                    CharactersListViewState.Error
                }
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Refresh characters fetch them again and update the list.
     */
    fun refreshLoadedCharactersList() {
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add characters into list.
     */
    fun retryAddCharactersList() {
        dataSourceFactory.retry()
    }

    /**
     * Send interaction event for open character detail view from selected character.
     *
     * @param characterId Character identifier.
     */
    fun openCharacterDetail(characterId: Long) {
        event.postValue(CharactersListViewEvent.OpenCharacterDetail(characterId))
    }
}
