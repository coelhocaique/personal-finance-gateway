package com.coelhocaique.finance.gateway.client.dashboard

import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.retrieveParamsRequest
import com.coelhocaique.finance.gateway.helper.ResponseHandler.generateResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class DashboardHandler (private val service: DashboardService) {

    fun retrieve(req: ServerRequest): Mono<ServerResponse> {
        return retrieveParamsRequest(req)
                .flatMap { service.retrieve(it) }
                .let { generateResponse(it) }
    }
}