package com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteAdapter
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersFavoriteComponent].
 *
 * @see Module
 */
@Module
class CharactersFavoriteModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CharactersFavoriteFragment
) {

    /**
     * Create a provider method binding for [CharactersFavoriteViewModel].
     *
     * @param characterFavoriteRepository Handler character favorite repository.
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesCharactersFavoriteViewModel(
        characterFavoriteRepository: CharacterFavoriteRepository
    ) = fragment.viewModel {
        CharactersFavoriteViewModel(
            characterFavoriteRepository = characterFavoriteRepository
        )
    }

    /**
     * Create a provider method binding for [CharactersFavoriteAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesCharactersFavoriteAdapter() = CharactersFavoriteAdapter()
}
