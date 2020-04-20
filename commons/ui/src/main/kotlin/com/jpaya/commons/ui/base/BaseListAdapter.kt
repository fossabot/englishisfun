package com.jpaya.commons.ui.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Base list adapter to standardize and simplify initialization for this component.
 *
 * @param itemsSame Function called to check whether two objects represent the same item.
 * @param contentsSame Function called to check whether two items have the same data.
 * @see ListAdapter
 */
abstract class BaseListAdapter<T>(itemsSame: (T, T) -> Boolean, contentsSame: (T, T) -> Boolean) :
    BaseViewHolderListAdapter<T, RecyclerView.ViewHolder>(itemsSame, contentsSame)
