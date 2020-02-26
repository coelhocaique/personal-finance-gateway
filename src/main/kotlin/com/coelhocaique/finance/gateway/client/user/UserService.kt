package com.coelhocaique.finance.gateway.client.user

import com.coelhocaique.finance.gateway.client.HttpClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService {

    @Value("\${account-service-api.url}")
    private lateinit var baseUrl: String

    @Autowired
    private lateinit var client: HttpClientService

    fun authenticate(request: UserAuthenticationRequest?): Mono<UserResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/user/authenticate"),
                request,
                UserResponse::class.java)
    }

    fun create(request: UserCreationRequest?): Mono<UserResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/user"),
                request,
                UserResponse::class.java)
    }

}
