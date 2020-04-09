package com.coelhocaique.finance.gateway.client

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserClient(
        mapper: ObjectMapper,
        @Value("\${account-service-api.url}") private val baseUrl: String
): HttpClientService(mapper){

    override fun <T> postRequest(
            uri: String, body: Any?,
            clazz: Class<T>,
            headers: Map<String, Any>
    ): Mono<T> {
        return super.postRequest(baseUrl.plus(uri), body, clazz, headers)
    }

    override fun <T> getRequest(
            uri: String,
            clazz: Class<T>,
            headers: Map<String, Any>
    ): Mono<T> {
        return super.getRequest(baseUrl.plus(uri), clazz, headers)
    }

    override fun deleteRequest(
            uri: String,
            headers: Map<String, Any>
    ): Mono<Void> {
        return super.deleteRequest(baseUrl.plus(uri), headers)
    }
}