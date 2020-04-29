package com.jpaya.dynamicfeatures.home.ui.di

import androidx.lifecycle.ViewModel
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.dynamicfeatures.home.ui.HomeFragment
import com.jpaya.dynamicfeatures.home.ui.HomeViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeModuleTest {

    @MockK
    lateinit var fragment: HomeFragment
    private lateinit var module: HomeModule

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeHomeModule_ShouldSetUpCorrectly() {
        module = HomeModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedHomeViewModel() {
        mockkStatic("com.jpaya.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<HomeViewModel>()

        val factoryCaptor = slot<() -> ViewModel>()
        module = HomeModule(fragment)
        module.providesHomeViewModel()

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertThat(factoryCaptor.captured(), instanceOf(HomeViewModel::class.java))
    }
}
