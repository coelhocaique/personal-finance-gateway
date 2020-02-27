package com.coelhocaique.finance.gateway.client.debt

import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.extractBody
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveHeaders
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveParamsRequest
import com.coelhocaique.finance.gateway.helper.ResponseHandler.generateResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DebtHandler (private val service: DebtService) {

    fun create(req: ServerRequest): Mono<ServerResponse> {
        val response = extractBody<DebtRequest>(req)
                .flatMap { service.create(it, retrieveHeaders(req)) }

        return generateResponse(response, 201)
    }

    fun retrieveById(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveParamsRequest(req)
                .flatMap { service.retrieveById(it) }

        return generateResponse(response)
    }

    fun retrieveByParam(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveParamsRequest(req)
                .flatMap { service.retrieveByParam(it) }

        return generateResponse(response)
    }

    fun deleteById(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveParamsRequest(req)
                .flatMap { service.deleteById(it) }

        return generateResponse(response)
    }

    fun deleteByParam(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveParamsRequest(req)
                .flatMap { service.deleteByParam(it) }

        return generateResponse(response)
    }
}