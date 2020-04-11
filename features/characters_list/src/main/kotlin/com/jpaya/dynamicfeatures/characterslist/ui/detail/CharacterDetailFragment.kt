package com.jpaya.dynamicfeatures.characterslist.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.jpaya.commons.ui.base.BaseFragment
import com.jpaya.englishisfun.SampleApp.Companion.coreComponent
import com.jpaya.commons.ui.extensions.observe
import com.jpaya.commons.views.ProgressBarDialog
import com.jpaya.dynamicfeatures.characterslist.R
import com.jpaya.dynamicfeatures.characterslist.databinding.FragmentCharacterDetailBinding
import com.jpaya.dynamicfeatures.characterslist.ui.detail.di.CharacterDetailModule
import com.jpaya.dynamicfeatures.characterslist.ui.detail.di.DaggerCharacterDetailComponent
import javax.inject.Inject

/**
 * View detail for selected character, displaying extra info and with option to add it to favorite.
 *
 * @see BaseFragment
 */
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        layoutId = R.layout.fragment_character_detail
    ) {

    @Inject
    lateinit var progressDialog: ProgressBarDialog

    private val args: CharacterDetailFragmentArgs by navArgs()

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
        observe(viewModel.state, ::onViewStateChange)
        viewModel.loadCharacterDetail(args.characterId)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharacterDetailComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .characterDetailModule(CharacterDetailModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view state change on [CharacterDetailViewState].
     *
     * @param viewState State of character detail.
     */
    private fun onViewStateChange(viewState: CharacterDetailViewState) {
        when (viewState) {
            is CharacterDetailViewState.Loading ->
                progressDialog.show(R.string.character_detail_dialog_loading_text)
            is CharacterDetailViewState.Error ->
                progressDialog.dismissWithErrorMessage(R.string.character_detail_dialog_error_text)
            is CharacterDetailViewState.AddedToFavorite ->
                Snackbar.make(
                    requireView(),
                    R.string.character_detail_added_to_favorite_message,
                    Snackbar.LENGTH_LONG
                ).show()
            is CharacterDetailViewState.Dismiss ->
                findNavController().navigateUp()
            else -> progressDialog.dismiss()
        }
    }
}
