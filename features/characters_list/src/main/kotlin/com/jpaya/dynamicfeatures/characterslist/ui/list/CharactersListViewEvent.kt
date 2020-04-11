package com.jpaya.dynamicfeatures.characterslist.ui.list

/**
 * Different interaction events for [CharactersListFragment].
 */
sealed class CharactersListViewEvent {

    /**
     * Open character detail view.
     *
     * @param id Character identifier
     */
    data class OpenCharacterDetail(val id: Long) : CharactersListViewEvent()
}
