package com.jpaya.core.di

import android.content.Context
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.core.di.modules.ContextModule
import com.jpaya.core.di.modules.DatabaseModule
import com.jpaya.core.di.modules.NetworkModule
import com.jpaya.core.di.modules.UtilsModule
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.core.network.services.MarvelService
import com.jpaya.core.utils.ThemeUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ContextModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    UtilsModule::class
])
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph MarvelService
     *
     * @return MarvelService
     */
    fun marvelService(): MarvelService

    /**
     * Provide dependency graph MarvelRepository
     *
     * @return MarvelRepository
     */
    fun marvelRepository(): MarvelRepository

    /**
     * Provide dependency graph CharacterFavoriteDao
     *
     * @return CharacterFavoriteDao
     */
    fun characterFavoriteDao(): CharacterFavoriteDao

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils
}
