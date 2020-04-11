package com.jpaya.dynamicfeatures.characterslist.ui.list.di

import com.jpaya.core.di.CoreComponent
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharactersListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CharactersListModule::class],
    dependencies = [CoreComponent::class])
interface CharactersListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Characters list component.
     */
    fun inject(listFragment: CharactersListFragment)
}
