package com.coelhocaique.finance.gateway.client.debt.tag

import com.coelhocaique.finance.gateway.client.custom.attribute.CustomAttributeRequest
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.extractBody
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveHeaders
import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveParamsRequest
import com.coelhocaique.finance.gateway.helper.ResponseHandler.generateResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DebtTagHandler (private val service: DebtTagService) {

    fun create(req: ServerRequest): Mono<ServerResponse> {
        return extractBody<CustomAttributeRequest>(req)
                .flatMap { service.create(it, retrieveHeaders(req)) }
                .let { generateResponse(it, 201) }
    }

    fun retrieveAll(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.retrieveAll(it) }
                .let { generateResponse(it) }
    }

    fun deleteById(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.deleteById(it) }
                .let { generateResponse(it) }
    }
}