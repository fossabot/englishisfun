package com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * View model responsible for preparing and managing the data for [CharactersFavoriteFragment].
 *
 * @see ViewModel
 */
class CharactersFavoriteViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val characterFavoriteRepository: CharacterFavoriteRepository
) : ViewModel() {

    val data = characterFavoriteRepository.getAllCharactersFavoriteLiveData()
    val state = Transformations.map(data) {
        if (it.isEmpty()) {
            CharactersFavoriteViewState.Empty
        } else {
            CharactersFavoriteViewState.Listed
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Remove the selected favorite character from database in case if exist.
     *
     * @param character Favorite character.
     */
    fun removeFavoriteCharacter(character: CharacterFavorite) {
        viewModelScope.launch {
            characterFavoriteRepository.deleteCharacterFavorite(character)
        }
    }
}
