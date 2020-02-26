package com.coelhocaique.finance.gateway.helper.exception

import java.lang.RuntimeException

data class ApiException(val status: Int,
                        val messages: List<String> = listOf(),
                        val ex: Throwable? = null
): RuntimeException(ex) {

    companion object ApiExceptionHelper {
        fun business(message: String, e: Throwable) = ApiException(400, listOf(message), e)
    }
}