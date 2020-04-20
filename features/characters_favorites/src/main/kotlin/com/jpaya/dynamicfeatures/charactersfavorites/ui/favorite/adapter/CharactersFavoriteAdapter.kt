package com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.base.BaseListAdapter
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.holders.CharacterFavoriteViewHolder

/**
 * Class for presenting characters favorite List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 *
 * @see BaseListAdapter
 */
class CharactersFavoriteAdapter : BaseListAdapter<CharacterFavorite>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    /**
     * Called when RecyclerView needs a new [RecyclerView.ViewHolder] of the given type to
     * represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see BaseListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterFavoriteViewHolder(LayoutInflater.from(parent.context))

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @see BaseListAdapter.onBindViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterFavoriteViewHolder -> holder.bind(getItem(position))
        }
    }
}
