package com.coelhocaique.finance.gateway.client.debt

import com.coelhocaique.finance.gateway.client.debt.tag.DebtTagService
import com.coelhocaique.finance.gateway.client.debt.threshold.DebtThresholdService
import com.coelhocaique.finance.gateway.client.debt.type.DebtTypeService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.zip
import java.math.BigDecimal


@Service
class DebtRetrievalService(
        private val service: DebtService,
        private val thresholdService: DebtThresholdService,
        private val typeService: DebtTypeService,
        private val tagService: DebtTagService
) {

    fun retrieveCreation(paramsRequest: ParamsRequest): Mono<DebtRetrievalResponse> {
        val tags = tagService.retrieveAll(paramsRequest).map { it.map { t -> t.value!! } }
        val types = typeService.retrieveAll(paramsRequest).map { it.map { t -> t.value!! } }
        return zip(tags, types)
                .map { DebtRetrievalResponse(tags = it.t1, types = it.t2) }

    }

    fun retrieveByParam(paramsRequest: ParamsRequest): Mono<DebtRetrievalResponse> {
        val debts = service.retrieveByParam(paramsRequest)
        val threshold = thresholdService.retrieveByParam(paramsRequest)
                .map { it.map { t -> BigDecimal(t.value) }
                        .fold(BigDecimal.ZERO) { acc, cur -> acc.add(cur) } }

        return zip(debts, threshold)
                .map { DebtRetrievalResponse(debts = it.t1, threshold = it.t2) }
    }
}
