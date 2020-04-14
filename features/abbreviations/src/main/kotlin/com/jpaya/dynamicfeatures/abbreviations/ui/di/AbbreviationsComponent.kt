package com.jpaya.dynamicfeatures.abbreviations.ui.di

import com.jpaya.core.di.CoreComponent
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [AbbreviationsModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [AbbreviationsModule::class],
    dependencies = [CoreComponent::class])
interface AbbreviationsComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Abbreviations list component.
     */
    fun inject(listFragment: AbbreviationsListFragment)
}
