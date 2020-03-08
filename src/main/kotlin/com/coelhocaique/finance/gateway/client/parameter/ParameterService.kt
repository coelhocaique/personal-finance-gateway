package com.coelhocaique.finance.gateway.client.parameter

import com.coelhocaique.finance.gateway.client.HttpClientService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class ParameterService {

    @Value("\${personal-finance-api.url}")
    private lateinit var baseUrl: String

    @Autowired
    private lateinit var client: HttpClientService

    fun create(request: ParameterRequest?, paramsRequest: ParamsRequest): Mono<ParameterResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/parameter"),
                request,
                ParameterResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<ParameterResponse> {
        return client.getRequest(
                baseUrl.plus("/v1/parameter/${request.id}"),
                ParameterResponse::class.java,
                request.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<ParameterResponse>> {
        return client.getListRequest(
                baseUrl.plus("/v1/parameter?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                baseUrl.plus("/v1/parameter/${request.id}"),
                request.headers)
    }
}
