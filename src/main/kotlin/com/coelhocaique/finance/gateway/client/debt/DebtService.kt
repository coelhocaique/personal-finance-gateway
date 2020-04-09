package com.coelhocaique.finance.gateway.client.debt

import com.coelhocaique.finance.gateway.client.ClientConstants.DEBT_PATH
import com.coelhocaique.finance.gateway.client.PersonalFinanceClient
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class DebtService(
        private val client: PersonalFinanceClient
) {

    fun create(request: DebtRequest?, paramsRequest: ParamsRequest): Mono<Void> {
        return client.postRequest(
                DEBT_PATH,
                request,
                Void::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<DebtResponse> {
        return client.getRequest(
                DEBT_PATH.plus("/${request.id}"),
                DebtResponse::class.java,
                request.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<DebtResponse>> {
        return client.getListRequest(
                DEBT_PATH.plus("?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                DEBT_PATH.plus("/${request.id}"),
                request.headers)
    }

    fun deleteByParam(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                DEBT_PATH.plus("?${request.getQueryParam()}"),
                request.headers)
    }
}
