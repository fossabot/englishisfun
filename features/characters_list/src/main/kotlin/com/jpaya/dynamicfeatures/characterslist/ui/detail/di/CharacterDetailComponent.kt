package com.jpaya.dynamicfeatures.characterslist.ui.detail.di

import com.jpaya.core.di.CoreComponent
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharacterDetailModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CharacterDetailModule::class],
    dependencies = [CoreComponent::class])
interface CharacterDetailComponent {

    /**
     * Inject dependencies on component.
     *
     * @param detailFragment Detail component.
     */
    fun inject(detailFragment: CharacterDetailFragment)
}
