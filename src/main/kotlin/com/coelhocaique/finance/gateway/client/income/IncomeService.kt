package com.coelhocaique.finance.gateway.client.income

import com.coelhocaique.finance.gateway.client.ClientConstants.INCOME_PATH
import com.coelhocaique.finance.gateway.client.PersonalFinanceClient
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class IncomeService (private val client: PersonalFinanceClient) {

    fun create(request: IncomeRequest?, paramsRequest: ParamsRequest): Mono<IncomeResponse> {
        return client.postRequest(
                INCOME_PATH,
                request,
                IncomeResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<IncomeResponse> {
        return client.getRequest(
                INCOME_PATH.plus("/${request.id}"),
                IncomeResponse::class.java,
                request.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<IncomeResponse>> {
        return client.getListRequest(
                INCOME_PATH.plus("?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                INCOME_PATH.plus("/${request.id}"),
                request.headers)
    }

}
