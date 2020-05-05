/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.jpaya.englishisfun.EnglishIsFunApp
import com.jpaya.commons.ui.base.BaseFragment
import com.jpaya.commons.ui.extensions.observe
import com.jpaya.core.database.characterfavorite.CharacterFavorite
import com.jpaya.dynamicfeatures.charactersfavorites.R
import com.jpaya.dynamicfeatures.charactersfavorites.databinding.FragmentCharactersFavoriteListBinding
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteAdapter
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.adapter.CharactersFavoriteTouchHelper
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.di.DaggerCharactersFavoriteComponent
import javax.inject.Inject

/**
 * View listing the added favorite characters with option to remove element by swiping.
 *
 * @see BaseFragment
 */
class CharactersFavoriteFragment :
    BaseFragment<FragmentCharactersFavoriteListBinding, CharactersFavoriteViewModel>(
        layoutId = R.layout.fragment_characters_favorite_list
    ) {

    @Inject
    lateinit var viewAdapter: CharactersFavoriteAdapter

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.data, ::onViewDataChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharactersFavoriteComponent
            .builder()
            .coreComponent(EnglishIsFunApp.coreComponent(requireContext()))
            .charactersFavoriteModule(CharactersFavoriteModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersFavoriteList.apply {
            adapter = viewAdapter

            ItemTouchHelper(CharactersFavoriteTouchHelper {
                viewModel.removeFavoriteCharacter(viewAdapter.currentList[it])
            }).attachToRecyclerView(this)
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [CharactersFavoriteViewModel].
     *
     * @param viewData List of favorite characters.
     */
    private fun onViewDataChange(viewData: List<CharacterFavorite>) {
        viewAdapter.submitList(viewData)
    }
}
