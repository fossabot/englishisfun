package com.jpaya.englishisfun.di

import com.jpaya.englishisfun.SampleApp
import com.jpaya.englishisfun.di.AppModule
import com.jpaya.core.di.CoreComponent
import com.jpaya.core.di.scopes.AppScope
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: SampleApp)
}
