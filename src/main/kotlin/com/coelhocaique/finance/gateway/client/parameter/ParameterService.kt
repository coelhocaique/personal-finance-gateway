package com.coelhocaique.finance.gateway.client.parameter

import com.coelhocaique.finance.gateway.client.ClientConstants.PARAMETER_PATH
import com.coelhocaique.finance.gateway.client.PersonalFinanceClient
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class ParameterService(
        private val client: PersonalFinanceClient
) {

    fun create(request: ParameterRequest?, paramsRequest: ParamsRequest): Mono<ParameterResponse> {
        return client.postRequest(
                PARAMETER_PATH,
                request,
                ParameterResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<ParameterResponse>> {
        return client.getListRequest(
                PARAMETER_PATH.plus("?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                PARAMETER_PATH.plus("/${request.id}"),
                request.headers)
    }
}
