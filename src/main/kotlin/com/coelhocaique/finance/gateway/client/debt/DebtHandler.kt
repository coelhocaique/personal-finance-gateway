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
class DebtHandler (private val service: DebtService,
                   private val retrievalService: DebtRetrievalService) {

    fun create(req: ServerRequest): Mono<ServerResponse> {
        return extractBody<DebtRequest>(req)
                .flatMap { service.create(it, retrieveHeaders(req)) }
                .let { generateResponse(it, 201) }
    }

    fun retrieveById(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.retrieveById(it) }
                .let { generateResponse(it) }
    }

    fun retrieveByParam(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { retrievalService.retrieveByParam(it)}
                .let { generateResponse(it) }
    }

    fun retrieveCreation(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { retrievalService.retrieveCreation(it)}
                .let { generateResponse(it) }
    }

    fun deleteById(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.deleteById(it) }
                .let { generateResponse(it) }
    }

    fun deleteByParam(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.deleteByParam(it) }
                .let { generateResponse(it) }
    }
}