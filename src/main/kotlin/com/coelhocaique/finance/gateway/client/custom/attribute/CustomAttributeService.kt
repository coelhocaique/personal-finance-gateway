package com.coelhocaique.finance.gateway.client.custom.attribute

import com.coelhocaique.finance.gateway.client.ClientConstants.CUSTOM_ATTRIBUTE_PATH
import com.coelhocaique.finance.gateway.client.PersonalFinanceClient
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class CustomAttributeService(
        private val client: PersonalFinanceClient
) {
    fun create(request: CustomAttributeRequest?, paramsRequest: ParamsRequest): Mono<CustomAttributeResponse> {
        return client.postRequest(
                CUSTOM_ATTRIBUTE_PATH,
                request,
                CustomAttributeResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<CustomAttributeResponse>> {
        return client.getListRequest(
                CUSTOM_ATTRIBUTE_PATH.plus("?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                CUSTOM_ATTRIBUTE_PATH.plus("/${request.id}"),
                request.headers)
    }
}
