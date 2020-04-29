package com.jpaya.commons.ui.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jpaya.libraries.testutils.lifecycle.TestLifecycleOwner
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.verify

@ExtendWith(InstantExecutorExtension::class)
class LifecycleOwnerExtensionsTest {

    private lateinit var lifecycleOwner: LifecycleOwner

    @BeforeEach
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingMutableLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingMutableLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val observerPostValue = 3
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingMutableLiveDat_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)
        mutableLiveData.postValue(null)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingMutableLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(mutableLiveData, observer)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingLiveData_WhenPostStringValue_ShouldTrigger() {
        val mutableLiveData = MutableLiveData<String>()
        val liveData: LiveData<String> = mutableLiveData
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observerPostValue = 3
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(observerPostValue)
        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        mutableLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingLiveData_WhenPostNullValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)
        mutableLiveData.postValue(null)

        verify(observer, never()).invoke(anyInt())
    }

    @Test
    fun observingLiveData_WithoutPostValue_ShouldNotTrigger() {
        val mutableLiveData = MutableLiveData<Int>()
        val liveData: LiveData<Int> = mutableLiveData
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(liveData, observer)

        verify(observer, never()).invoke(anyInt())
    }
}
