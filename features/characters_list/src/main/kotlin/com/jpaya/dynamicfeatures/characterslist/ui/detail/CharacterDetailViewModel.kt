package com.jpaya.dynamicfeatures.characterslist.ui.detail

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.dynamicfeatures.characterslist.ui.detail.model.CharacterDetail
import com.jpaya.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [CharacterDetailFragment].
 *
 * @see ViewModel
 */
class CharacterDetailViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val marvelRepository: MarvelRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    val characterFavoriteRepository: CharacterFavoriteRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    val characterDetailMapper: CharacterDetailMapper
) : ViewModel() {

    private val _data = MutableLiveData<CharacterDetail>()
    val data: LiveData<CharacterDetail>
        get() = _data

    private val _state = MutableLiveData<CharacterDetailViewState>()
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Fetch selected character detail info.
     *
     * @param characterId Character identifier.
     */
    fun loadCharacterDetail(characterId: Long) {
        _state.postValue(CharacterDetailViewState.Loading)
        viewModelScope.launch {
            val result = marvelRepository.getCharacter(characterId)
            _data.postValue(characterDetailMapper.map(result))

            characterFavoriteRepository.getCharacterFavorite(characterId)?.let {
                _state.postValue(CharacterDetailViewState.AlreadyAddedToFavorite)
            } ?: run {
                _state.postValue(CharacterDetailViewState.AddToFavorite)
            }
        }
    }

    /**
     * Store selected character to database favorite list.
     */
    fun addCharacterToFavorite() {
        _data.value?.let {
            viewModelScope.launch {
                characterFavoriteRepository.insertCharacterFavorite(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl
                )
                _state.postValue(CharacterDetailViewState.AddedToFavorite)
            }
        }
    }

    /**
     * Send interaction event for dismiss character detail view.
     */
    fun dismissCharacterDetail() {
        _state.postValue(CharacterDetailViewState.Dismiss)
    }
}
