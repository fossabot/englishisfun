package com.jpaya.core.network.responses

import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataResponseTest {

    @Test
    fun createDataResponse_ShouldAddCorrectAttributes() {
        val offset = 10
        val limit = 50
        val total = 1000
        val count = 50
        val results: List<String> = mock()

        val dataResponse = DataResponse(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            results = results
        )

        assertEquals(offset, dataResponse.offset)
        assertEquals(limit, dataResponse.limit)
        assertEquals(total, dataResponse.total)
        assertEquals(count, dataResponse.count)
        assertEquals(results, dataResponse.results)
    }
}
