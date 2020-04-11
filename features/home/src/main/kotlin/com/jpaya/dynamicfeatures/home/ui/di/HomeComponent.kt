package com.jpaya.dynamicfeatures.home.ui.di

import com.jpaya.core.di.CoreComponent
import com.jpaya.core.di.scopes.FeatureScope
import com.jpaya.dynamicfeatures.home.ui.HomeFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [HomeModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class])
interface HomeComponent {

    /**
     * Inject dependencies on component.
     *
     * @param homeFragment Home component.
     */
    fun inject(homeFragment: HomeFragment)
}
