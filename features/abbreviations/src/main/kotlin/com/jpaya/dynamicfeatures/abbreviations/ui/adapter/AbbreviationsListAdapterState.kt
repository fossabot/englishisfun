package com.jpaya.dynamicfeatures.abbreviations.ui.adapter

import com.jpaya.commons.ui.base.BaseViewState

/**
 * Different states for [AbbreviationsListAdapter].
 *
 * @see BaseViewState
 */
sealed class AbbreviationsListAdapterState(
    val hasExtraRow: Boolean
) {

    /**
     * Listed the added abbreviations into list.
     */
    object Added : AbbreviationsListAdapterState(hasExtraRow = true)

    /**
     * Loading for new abbreviations to add into list.
     */
    object AddLoading : AbbreviationsListAdapterState(hasExtraRow = true)

    /**
     * Error on add new abbreviations into list.
     */
    object AddError : AbbreviationsListAdapterState(hasExtraRow = true)

    /**
     * No more abbreviations to add into list.
     */
    object NoMore : AbbreviationsListAdapterState(hasExtraRow = false)

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Added].
     *
     * @return True if is added state, otherwise false.
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMore() = this is NoMore
}
