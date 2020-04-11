package com.jpaya.core.di

import android.app.Application
import com.jpaya.core.di.modules.ContextModule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ContextModuleTest {

    @Mock
    lateinit var application: Application
    private lateinit var contextModule: ContextModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        contextModule = ContextModule(application)
    }

    @Test
    fun verifyProvidedContext() {
        assertEquals(application, contextModule.provideContext())
    }
}
