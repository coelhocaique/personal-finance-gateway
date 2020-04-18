package com.coelhocaique.finance.gateway.client.debt.recurring

import com.coelhocaique.finance.gateway.client.ClientConstants.RECURRING_DEBT_PATH
import com.coelhocaique.finance.gateway.client.PersonalFinanceClient
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class RecurringDebtService(private val client: PersonalFinanceClient) {

    fun create(request: RecurringDebtRequest?, paramsRequest: ParamsRequest): Mono<Void> {
        return client.postRequest(
                RECURRING_DEBT_PATH,
                request,
                Void::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<RecurringDebtResponse> {
        return client.getRequest(
                RECURRING_DEBT_PATH.plus("/${request.id}"),
                RecurringDebtResponse::class.java,
                request.headers)
    }

    fun retrieveAll(request: ParamsRequest): Mono<List<RecurringDebtResponse>> {
        return client.getListRequest(
                RECURRING_DEBT_PATH,
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                RECURRING_DEBT_PATH.plus("/${request.id}"),
                request.headers)
    }
}
