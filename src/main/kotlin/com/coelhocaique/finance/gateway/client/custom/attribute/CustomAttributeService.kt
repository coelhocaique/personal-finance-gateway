package com.coelhocaique.finance.gateway.client.custom.attribute

import com.coelhocaique.finance.gateway.client.HttpClientService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class CustomAttributeService {

    @Value("\${personal-finance-api.url}")
    private lateinit var baseUrl: String

    @Autowired
    private lateinit var client: HttpClientService

    fun create(request: CustomAttributeRequest?, paramsRequest: ParamsRequest): Mono<CustomAttributeResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/custom-attribute"),
                request,
                CustomAttributeResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<CustomAttributeResponse>> {
        return client.getListRequest(
                baseUrl.plus("/v1/custom-attribute?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                baseUrl.plus("/v1/custom-attribute/${request.id}"),
                request.headers)
    }
}
