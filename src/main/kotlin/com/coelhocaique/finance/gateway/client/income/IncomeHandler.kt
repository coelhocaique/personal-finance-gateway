package com.coelhocaique.finance.gateway.client.income

import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.extractBody
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveFetchRequest
import com.coelhocaique.finance.gateway.helper.ResponseHandler.generateResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class IncomeHandler (private val service: IncomeService) {

    fun create(req: ServerRequest): Mono<ServerResponse> {
        val response = extractBody<IncomeRequest>(req)
                .flatMap { service.create(it) }

        return generateResponse(response, 201)
    }

    fun retrieveById(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveFetchRequest(req)
                .flatMap { service.retrieveById(it) }

        return generateResponse(response)
    }

    fun retrieve(req: ServerRequest): Mono<ServerResponse> {
        val response = retrieveFetchRequest(req)
                .flatMap { service.retrieve(it) }

        return generateResponse(response)
    }
}