package com.coelhocaique.finance.gateway.client.user

import com.coelhocaique.finance.gateway.helper.RequestParameterHandler.extractBody
import com.coelhocaique.finance.gateway.helper.ResponseHandler.generateResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler (private val service: UserService) {

    fun create(req: ServerRequest): Mono<ServerResponse> {
        val response = extractBody<UserCreationRequest>(req)
                .flatMap { service.create(it) }

        return generateResponse(response, 201)
    }

    fun authenticate(req: ServerRequest): Mono<ServerResponse> {
        val response = extractBody<UserAuthenticationRequest>(req)
                .flatMap { service.authenticate(it) }

        return generateResponse(response)
    }
}