package com.jpaya.core.network.services

import com.jpaya.core.network.responses.BaseResponse
import com.jpaya.core.network.responses.CharacterResponse
import com.jpaya.libraries.testutils.rules.InstantExecutorExtension
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

object MockResponses {
    object GetCharacters {
        const val STATUS_200 = "mock-responses/get-characters-status200.json"
        const val STATUS_204 = "mock-responses/get-characters-status204.json"
        const val STATUS_401 = "mock-responses/get-characters-status401.json"
    }

    object GetCharacterId {
        const val STATUS_200 = "mock-responses/get-character-id-status200.json"
        const val STATUS_401 = "mock-responses/get-character-id-status401.json"
        const val STATUS_404 = "mock-responses/get-character-id-status404.json"
    }
}

@ExtendWith(InstantExecutorExtension::class)
class MarvelServiceTest {

    private lateinit var service: MarvelService
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestGetCharacters() = runBlocking {
        enqueueResponse(MockResponses.GetCharacters.STATUS_200)
        val apiKey = "mockApiKey"
        val hash = "mockHash"
        val ts = "mockTimestamp"
        val limit = 20
        val offset = 0
        service.getCharacters(
            apiKey = apiKey,
            hash = hash,
            timestamp = ts,
            offset = offset,
            limit = limit
        )

        val request = mockWebServer.takeRequest()
        assertEquals("GET", request.method)
        assertEquals(
            "/v1/public/characters?apikey=$apiKey&hash=$hash&ts=$ts&offset=$offset&limit=$limit",
            request.path
        )
    }

    @Test
    fun responseGetCharacters_StatusCode200() = runBlocking {
        enqueueResponse(MockResponses.GetCharacters.STATUS_200)
        val limit = 20
        val offset = 0
        val response = service.getCharacters(
            apiKey = "",
            hash = "",
            timestamp = "",
            offset = offset,
            limit = limit
        )

        assertCodeStatus(200, response.code)
        assertEquals("Ok", response.status)
        assertNull(response.message)

        val responseData = response.data
        assertEquals(offset, responseData.offset)
        assertEquals(limit, responseData.limit)
        assertEquals(limit, responseData.count)
        assertEquals(1492, responseData.total)
        assertEquals(limit, responseData.results.size)
        assertThat(responseData.results, instanceOf(List::class.java))
    }

    @Test
    fun responseGetCharacters_StatusCode204() = runBlocking {
        enqueueResponse(MockResponses.GetCharacters.STATUS_204)
        val limit = 20
        val offset = 10000
        val response = service.getCharacters(
            offset = offset,
            limit = limit
        )

        assertCodeStatus(204, response.code)
        assertEquals("Empty", response.status)
        assertNull(response.message)

        val responseData = response.data
        assertEquals(offset, responseData.offset)
        assertEquals(limit, responseData.limit)
        assertEquals(0, responseData.count)
        assertEquals(1493, responseData.total)
        assertEquals(0, responseData.results.size)
    }

    @Test
    fun responseGetCharacters_StatusCode401() = runBlocking {
        enqueueResponse(MockResponses.GetCharacters.STATUS_401)
        val response = service.getCharacters()

        assertEquals("InvalidCredentials", response.code)
        assertEquals("That hash, timestamp and key combination is invalid.", response.message)
        assertNull(response.status)
        assertNull(response.data)
    }

    @Test
    fun requestCharacterId() = runBlocking {
        enqueueResponse(MockResponses.GetCharacterId.STATUS_200)
        val id = 1L
        val apiKey = "MockApiKey"
        val hash = "MockHash"
        val timestamp = "MockTimestamp"
        service.getCharacter(
            id = id,
            apiKey = apiKey,
            hash = hash,
            timestamp = timestamp
        )

        val request = mockWebServer.takeRequest()
        assertEquals("GET", request.method)
        assertEquals(
            "/v1/public/characters/$id?apikey=$apiKey&hash=$hash&ts=$timestamp",
            request.path
        )
    }

    @Test
    fun responseCharacterId_StatusCode200() {
        runBlocking {
            enqueueResponse(MockResponses.GetCharacterId.STATUS_200)
            val characterId = 1011334L
            val response = service.getCharacter(characterId)

            assertCodeStatus(200, response.code)
            assertEquals("Ok", response.status)
            assertNull(response.message)

            response.data.run {
                assertEquals(0, offset)
                assertEquals(20, limit)
                assertEquals(1, count)
                assertEquals(1, total)
                assertEquals(1, results.size)
            }

            response.data.results.first().run {
                assertEquals(characterId, id)
                assertEquals("3-D Man", name)
                assertEquals("", description)

                assertEquals(
                    "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                    thumbnail.path
                )
                assertEquals("jpg", thumbnail.extension)
            }
        }
    }

    @Test
    fun responseCharacterId_StatusCode401() = runBlocking {
        enqueueResponse(MockResponses.GetCharacterId.STATUS_401)
        val response = service.getCharacter()

        assertEquals("InvalidCredentials", response.code)
        assertEquals("That hash, timestamp and key combination is invalid.", response.message)
        assertNull(response.status)
        assertNull(response.data)
    }

    @Test
    fun responseCharacterId_StatusCode404() = runBlocking {
        enqueueResponse(MockResponses.GetCharacterId.STATUS_404)
        val response = service.getCharacter()

        assertCodeStatus(404, response.code)
        assertEquals("We couldn't find that character", response.status)
        assertNull(response.message)
        assertNull(response.data)
    }

    private fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource!!.readString(Charsets.UTF_8)
            )
        )
    }

    private fun assertCodeStatus(number1: Number, number2: Any) {
        assertEquals(number1, (number2 as Double).roundToInt())
    }

    private suspend fun MarvelService.getCharacter(
        id: Long = 0L
    ): BaseResponse<CharacterResponse> {
        return service.getCharacter(
            id = id,
            apiKey = "",
            hash = "",
            timestamp = ""
        )
    }

    private suspend fun MarvelService.getCharacters(
        apiKey: String = "",
        hash: String = "",
        timestamp: String = "",
        offset: Int = 0,
        limit: Int = 20
    ): BaseResponse<CharacterResponse> {
        return service.getCharacters(
            apiKey = apiKey,
            hash = hash,
            timestamp = timestamp,
            offset = offset,
            limit = limit
        )
    }
}
