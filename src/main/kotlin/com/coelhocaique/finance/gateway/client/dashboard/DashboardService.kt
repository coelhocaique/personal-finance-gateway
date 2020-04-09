package com.coelhocaique.finance.gateway.client.dashboard

import com.coelhocaique.finance.gateway.client.dashboard.processor.DashboardDataProcessor
import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.debt.DebtService
import com.coelhocaique.finance.gateway.client.debt.threshold.DebtThresholdService
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.income.IncomeService
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import com.coelhocaique.finance.gateway.helper.Fields.FROM_DATE
import com.coelhocaique.finance.gateway.helper.Fields.TO_DATE
import com.coelhocaique.finance.gateway.helper.Messages.INVALID_PARAMETER
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.coelhocaique.finance.gateway.helper.exception.ApiException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just


@Service
class DashboardService(
        private val thresholdService: DebtThresholdService,
        private val debtService: DebtService,
        private val incomeService: IncomeService,
        private val dataProcessors: Set<DashboardDataProcessor>){

    fun retrieve(request: ParamsRequest): Mono<Map<String, Any>> {
        return just(request)
                .map { it.queryParams }
                .map { castToPair(it) }
                .flatMap { retrieveData(it, request)}
    }

    private fun castToPair(params: Map<String, Any>): Pair<String, String> {
        val from = params[FROM_DATE] ?: throw ApiException.business(INVALID_PARAMETER.format(FROM_DATE))
        val to = params[TO_DATE] ?: throw ApiException.business(INVALID_PARAMETER.format(TO_DATE))
        return Pair(from.toString(), to.toString())
    }

    private fun retrieveData(dates: Pair<String, String>,
                             request: ParamsRequest): Mono<Map<String, Any>> {

        val newRequest = request.copy(queryParams = mapOf(FROM_DATE to dates.first, TO_DATE to dates.second))
        val thresholds = thresholdService.retrieveByParam(newRequest).switchIfEmpty(just(listOf()))
        val incomes = incomeService.retrieveByParam(newRequest).switchIfEmpty(just(listOf()))
        val debts = debtService.retrieveByParam(request).switchIfEmpty(just(listOf()))

        return Mono.zip(thresholds, incomes, debts)
                .flatMap { collectData(it.t1, it.t2, it.t3) }
    }

    private fun collectData(parameters: List<ParameterResponse>,
                            incomes: List<IncomeResponse>,
                            debts: List<DebtResponse>): Mono<Map<String, Any>> {

        return dataProcessors.map { it.process(parameters, incomes, debts) }
                .fold(mapOf<String, Any>()) { acc, current -> acc + current }
                .let { just(it) }
    }
}
