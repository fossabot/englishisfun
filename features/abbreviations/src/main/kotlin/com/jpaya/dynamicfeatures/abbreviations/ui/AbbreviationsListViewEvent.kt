package com.jpaya.dynamicfeatures.abbreviations.ui

/**
 * Different interaction events for [AbbreviationsListFragment].
 */
sealed class AbbreviationsListViewEvent {

    /**
     * Open abbreviation detail view.
     *
     * @param id Abbreviation identifier
     */
    data class OpenAbbreviationDetail(val id: Long) : AbbreviationsListViewEvent()
}
