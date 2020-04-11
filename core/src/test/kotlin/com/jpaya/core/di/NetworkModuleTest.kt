package com.jpaya.core.di

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.jpaya.core.BuildConfig
import com.jpaya.core.di.modules.NetworkModule
import com.jpaya.core.network.services.MarvelService
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mock<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()

        assertEquals(BuildConfig.MARVEL_API_BASE_URL, retrofit.baseUrl().toUrl().toString())
    }

    @Test
    fun verifyProvidedMarvelService() {
        val retrofit = mock<Retrofit>()
        val marvelService = mock<MarvelService>()
        val serviceClassCaptor = argumentCaptor<Class<*>>()

        doReturn(marvelService).whenever(retrofit).create<MarvelService>(any())

        networkModule.provideMarvelService(retrofit)

        verify(retrofit).create(serviceClassCaptor.capture())
        assertEquals(MarvelService::class.java, serviceClassCaptor.lastValue)
    }

    @Test
    fun verifyProvidedMarvelRepository() {
        val marvelService = mock<MarvelService>()
        val marvelRepository = networkModule.provideMarvelRepository(marvelService)

        assertEquals(marvelService, marvelRepository.service)
    }
}
