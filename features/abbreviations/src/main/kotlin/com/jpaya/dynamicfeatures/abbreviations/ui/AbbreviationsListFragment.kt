package com.jpaya.dynamicfeatures.abbreviations.ui

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.commons.ui.base.BaseFragment
import com.jpaya.commons.ui.extensions.observe
import com.jpaya.dynamicfeatures.abbreviations.R
import com.jpaya.dynamicfeatures.abbreviations.databinding.FragmentAbbreviationsListBinding
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapterState
import com.jpaya.dynamicfeatures.abbreviations.ui.di.AbbreviationsModule
import com.jpaya.dynamicfeatures.abbreviations.ui.di.DaggerAbbreviationsComponent
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItem
import com.jpaya.englishisfun.SampleApp.Companion.coreComponent
import javax.inject.Inject

/**
 * View listing all abbreviations.
 *
 * @see BaseFragment
 */
class AbbreviationsListFragment :
    BaseFragment<FragmentAbbreviationsListBinding, AbbreviationsListViewModel>(
        layoutId = R.layout.fragment_abbreviations_list
    ) {

    @Inject
    lateinit var viewAdapter: AbbreviationsListAdapter

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
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.event, ::onViewEvent)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerAbbreviationsComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .abbreviationsModule(AbbreviationsModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        view!!.findViewById<RecyclerView>(R.id.abbreviations_list).apply {
            adapter = viewAdapter
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [AbbreviationsListViewModel].
     *
     * @param viewData Paged list of abbreviations.
     */
    private fun onViewDataChange(viewData: PagedList<AbbreviationItem>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observer view state change on [AbbreviationsListViewModel].
     *
     * @param viewState State of abbreviations list.
     */
    private fun onViewStateChange(viewState: AbbreviationsListViewState) {
        when (viewState) {
            is AbbreviationsListViewState.Loaded ->
                viewAdapter.submitState(AbbreviationsListAdapterState.Added)
            is AbbreviationsListViewState.AddLoading ->
                viewAdapter.submitState(AbbreviationsListAdapterState.AddLoading)
            is AbbreviationsListViewState.AddError ->
                viewAdapter.submitState(AbbreviationsListAdapterState.AddError)
            is AbbreviationsListViewState.NoMoreElements ->
                viewAdapter.submitState(AbbreviationsListAdapterState.NoMore)
        }
    }

    /**
     * Observer view event change on [AbbreviationsListViewModel].
     *
     * @param viewEvent Event on abbreviations list.
     */
    private fun onViewEvent(viewEvent: AbbreviationsListViewEvent) {}
}
