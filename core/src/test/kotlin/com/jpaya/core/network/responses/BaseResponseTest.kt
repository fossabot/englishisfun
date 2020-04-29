package com.jpaya.core.network.responses

import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BaseResponseTest {

    @Test
    fun createBaseResponse_ShouldAddCorrectAttributes() {
        val code = 200
        val status = "Ok"
        val message = "Ok"
        val data: DataResponse<String> = mock()

        val baseResponse = BaseResponse(
            code = code,
            status = status,
            message = message,
            data = data
        )

        assertEquals(code, baseResponse.code)
        assertEquals(status, baseResponse.status)
        assertEquals(message, baseResponse.message)
        assertEquals(data, baseResponse.data)
    }
}
