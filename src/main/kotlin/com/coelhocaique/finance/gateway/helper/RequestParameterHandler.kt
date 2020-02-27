package com.coelhocaique.finance.gateway.helper

import com.coelhocaique.finance.gateway.helper.Messages.INVALID_REQUEST
import com.coelhocaique.finance.gateway.helper.exception.ApiException.ApiExceptionHelper.business
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just

object RequestParameterHandler {

    inline fun <reified T> extractBody(req: ServerRequest): Mono<T> {
        return req.bodyToMono(T::class.java)
                .onErrorMap { business(INVALID_REQUEST, it) }
    }

    fun retrieveParamsRequest(req: ServerRequest): Mono<ParamsRequest> {
        val id = retrieveId(req)
        val headers = req.headers()
                .header("Authorization")
                .map { "Authorization" to it }
                .toMap()

        val params = req.queryParams().toSingleValueMap()

        return just(ParamsRequest(id, headers, params))
    }

    fun retrieveHeaders(req: ServerRequest): ParamsRequest {
        val headers = req.headers()
                .header("Authorization")
                .map { "Authorization" to it }
                .toMap()

        return ParamsRequest(headers = headers)
    }

    private fun retrieveId(req: ServerRequest): String? {
        return try {
            req.pathVariable("id")
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}