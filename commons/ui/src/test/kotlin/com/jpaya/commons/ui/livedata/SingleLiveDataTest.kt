package com.jpaya.commons.ui.livedata

import androidx.lifecycle.LifecycleOwner
import com.jpaya.commons.ui.extensions.observe
import com.jpaya.libraries.testutils.lifecycle.TestLifecycleOwner
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*

@ExtendWith(InstantExecutorExtension::class)
class SingleLiveDataTest {

    private lateinit var lifecycleOwner: LifecycleOwner

    @BeforeEach
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingSingleLiveData_WhenPostStringValue_ShouldTriggerOneEvent() {
        val singleLiveData = SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingSingleLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val singleLiveData = SingleLiveData<Int>()
        val observerPostValue = 1
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)
        singleLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        singleLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun multipleObservingSingleLiveData_WhenPostIntValue_ShouldTriggerOnlyFirstObserver() {
        val singleLiveData = SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer1 = mock<(String) -> Unit>()
        val observer2 = mock<(String) -> Unit>()
        val observer3 = mock<(String) -> Unit>()
        val observer1Captor = argumentCaptor<String>()

        lifecycleOwner.observe(singleLiveData, observer1)
        lifecycleOwner.observe(singleLiveData, observer2)
        lifecycleOwner.observe(singleLiveData, observer3)
        singleLiveData.postValue(observerPostValue)

        verify(observer1).invoke(observer1Captor.capture())
        verify(observer2, never()).invoke(anyString())
        verify(observer3, never()).invoke(anyString())

        assertEquals(observerPostValue, observer1Captor.lastValue)
    }

    @Test
    fun observingSingleLiveData_WithoutPostValue_ShouldNotTrigger() {
        val singleLiveData = SingleLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(singleLiveData, observer)

        verify(observer, never()).invoke(anyInt())
    }
}
