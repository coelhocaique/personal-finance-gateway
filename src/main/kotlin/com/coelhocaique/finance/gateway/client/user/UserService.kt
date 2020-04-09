package com.coelhocaique.finance.gateway.client.user

import com.coelhocaique.finance.gateway.client.ClientConstants.USER_AUTHENTICATION_PATH
import com.coelhocaique.finance.gateway.client.ClientConstants.USER_PATH
import com.coelhocaique.finance.gateway.client.UserClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService(
        private val client: UserClient
) {

    fun authenticate(request: UserAuthenticationRequest?): Mono<UserResponse> {
        return client.postRequest(
                USER_AUTHENTICATION_PATH,
                request,
                UserResponse::class.java)
    }

    fun create(request: UserCreationRequest?): Mono<UserResponse> {
        return client.postRequest(
                USER_PATH,
                request,
                UserResponse::class.java)
    }
}
