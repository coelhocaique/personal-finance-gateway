package com.coelhocaique.finance.gateway.client.debt.threshold

import com.coelhocaique.finance.gateway.client.parameter.ParameterRequest
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterService
import com.coelhocaique.finance.gateway.helper.Fields.NAME
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.coelhocaique.finance.gateway.helper.Fields.REFERENCE_DATE
import com.coelhocaique.finance.gateway.helper.Fields.THRESHOLD
import com.coelhocaique.finance.gateway.helper.extractReferenceDate
import com.coelhocaique.finance.gateway.helper.parseReferenceDate
import com.coelhocaique.finance.gateway.helper.subtractFromReferenceDate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just
import reactor.core.publisher.switchIfEmpty
import java.math.BigDecimal

@Service
class DebtThresholdService(private val parameterService: ParameterService) {

    fun create(request: ParameterRequest?, paramsRequest: ParamsRequest): Mono<ParameterResponse> {
        return parameterService.create(request?.let { it.copy(name = THRESHOLD) }, paramsRequest)
    }

    fun retrieveByParam(request: ParamsRequest, fallbackLast: Boolean = true): Mono<List<ParameterResponse>> {
        val thresholdParams = request.copy(queryParams = request.queryParams + mapOf(NAME to THRESHOLD))
        return parameterService.retrieveByParam(thresholdParams)
                .switchIfEmpty { fallbackLast(thresholdParams, fallbackLast) }
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return parameterService.deleteById(request)
    }

    private fun fallbackLast(request: ParamsRequest, fallbackLast: Boolean): Mono<List<ParameterResponse>> {
        val date = request.queryParams[REFERENCE_DATE]

        return if (date != null && fallbackLast) {
            val parsedDate = subtractFromReferenceDate(date.toString(), 1)
            val newRequest = request.copy(queryParams = mapOf(REFERENCE_DATE to extractReferenceDate(parsedDate)))

            retrieveByParam(newRequest, false)
                    .map { it.map { itt -> BigDecimal(itt.value) }.reduce { acc, cur ->  acc.add(cur)} }
                    .map { ParameterRequest(value = it.toString(), referenceDate = parseReferenceDate(date.toString())) }
                    .flatMap { create(it, request) }
                    .flatMap { just(listOf(it)) }
                    .switchIfEmpty { just(listOf()) }

        }
        else just(listOf())
    }
}
