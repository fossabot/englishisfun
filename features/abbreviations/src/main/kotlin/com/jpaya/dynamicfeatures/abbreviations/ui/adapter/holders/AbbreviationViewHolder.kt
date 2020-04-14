package com.jpaya.dynamicfeatures.abbreviations.ui.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.base.BaseViewHolder
import com.jpaya.dynamicfeatures.abbreviations.databinding.ListItemAbbreviationBinding
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem

/**
 * Class describes abbreviation view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class AbbreviationViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemAbbreviationBinding>(
    binding = ListItemAbbreviationBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel Abbreviation list view model.
     * @param item Abbreviation list item.
     */
    fun bind(viewModel: AbbreviationsListViewModel, item: AbbreviationItem) {
        binding.viewModel = viewModel
        binding.abbreviation = item
        binding.executePendingBindings()
    }
}
