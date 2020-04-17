package com.jpaya.dynamicfeatures.abbreviations.ui.model

import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListFragment

/**
 * Model view to display on the screen [AbbreviationsListFragment].
 */
data class AbbreviationItem(
    val id: Long,
    val abbr: String,
    val desc: String
)
