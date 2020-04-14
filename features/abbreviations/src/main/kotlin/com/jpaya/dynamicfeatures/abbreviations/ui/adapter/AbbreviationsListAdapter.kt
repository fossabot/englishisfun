package com.jpaya.dynamicfeatures.abbreviations.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.base.BaseListAdapter
import com.jpaya.commons.ui.base.BasePagedListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.holders.AbbreviationViewHolder
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem
import javax.inject.Inject

/**
 * Class for presenting abbreviations List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BaseListAdapter
 */
class AbbreviationsListAdapter @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val viewModel: AbbreviationsListViewModel
) : BasePagedListAdapter<AbbreviationItem>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: AbbreviationsListAdapterState = AbbreviationsListAdapterState.Added

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param inflater Instantiates a layout XML file into its corresponding View objects.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see BaseListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = AbbreviationViewHolder(inflater)

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see BaseListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if (holder is AbbreviationViewHolder) {
                holder.bind(viewModel, it)
            }
        }
    }

    /**
     * Return the stable ID for the item at position.
     *
     * @param position Adapter position to query.
     * @return The stable ID of the item at position.
     * @see BasePagedListAdapter.getItemId
     */
    override fun getItemId(position: Int) = getItem(position)?.id ?: -1L

    /**
     * Update current adapter state with the new one, applying visual changes.
     *
     * @param newState State of list adapter to update.
     */
    fun submitState(newState: AbbreviationsListAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}
