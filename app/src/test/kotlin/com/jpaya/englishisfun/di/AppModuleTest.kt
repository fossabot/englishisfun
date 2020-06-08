package com.jpaya.englishisfun.di

import com.jpaya.englishisfun.EnglishIsFunApp
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AppModuleTest {

    private lateinit var appModule: AppModule

    @Before
    fun setUp() {
        appModule = AppModule()
    }

    @Test
    fun verifyProvidedContext() {
        val application: EnglishIsFunApp = mock()
        doReturn(application).whenever(application).applicationContext

        assertNotNull(appModule.provideContext(application))
        assertEquals(application, appModule.provideContext(application))
    }
}
