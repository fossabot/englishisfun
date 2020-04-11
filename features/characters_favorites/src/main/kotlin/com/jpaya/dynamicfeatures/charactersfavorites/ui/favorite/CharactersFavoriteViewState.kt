package com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite

import com.jpaya.commons.ui.base.BaseViewState

/**
 * Different states for [CharactersFavoriteFragment].
 *
 * @see BaseViewState
 */
sealed class CharactersFavoriteViewState :
    BaseViewState {

    /**
     * No favorite characters to display.
     */
    object Empty : CharactersFavoriteViewState()

    /**
     * Favorite characters displayed.
     */
    object Listed : CharactersFavoriteViewState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Listed].
     *
     * @return True if is listed state, otherwise false.
     */
    fun isListed() = this is Listed
}
