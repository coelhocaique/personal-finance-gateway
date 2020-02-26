package com.coelhocaique.finance.gateway.client

import com.coelhocaique.finance.gateway.helper.exception.ApiException
import com.coelhocaique.finance.gateway.helper.logger
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpRequest.BodyPublisher
import java.net.http.HttpResponse

@Component
class HttpClientService {

    @Autowired
    private lateinit var mapper: ObjectMapper

    fun <T> send(request: HttpRequest, clazz: Class<T>): Mono<T> {
        val httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val status = response.statusCode()

        logger().info("status=".plus(status).plus(", uri=").plus(request.uri().toString()))

        if (status <= 201) {
            return Mono.just(mapper.readValue(response.body(), clazz))
        } else {
            val error = mapper.readValue(response.body(), ErrorResponse::class.java)
            throw ApiException(status, error.errors)
        }
    }

    fun <T> postRequest(uri: String, body: Any?,
                        clazz: Class<T>,
                        headers: Map<String, Any> = mapOf()
                        ): Mono<T> {

        val httpRequest = buildHttpRequest(uri, body, "POST", headers)

        return send(httpRequest, clazz)
    }

    fun <T> getRequest(uri: String, clazz: Class<T>,
                       headers: Map<String, Any> = mapOf()
                       ): Mono<T> {

        val httpRequest = buildHttpRequest(uri, null, "GET", headers)

        return send(httpRequest, clazz)
    }

    private fun buildHttpRequest(uri: String,
                                 body: Any?,
                                 method: String,
                                 headers: Map<String, Any>): HttpRequest {

        val builder = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type" ,"application/json")
                .method(method, serializeBody(body))

        headers.forEach { (t, u) -> builder.header(t, u.toString()) }

        return builder.build()
    }

    private fun serializeBody(body: Any?): BodyPublisher? {
        return if (body != null )
            BodyPublishers.ofString(mapper.writeValueAsString(body))
        else
            BodyPublishers.noBody()
    }

}