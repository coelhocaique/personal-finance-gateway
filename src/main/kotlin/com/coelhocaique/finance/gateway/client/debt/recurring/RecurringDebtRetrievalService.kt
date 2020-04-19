package com.coelhocaique.finance.gateway.client.debt.recurring

import com.coelhocaique.finance.gateway.client.debt.tag.DebtTagService
import com.coelhocaique.finance.gateway.client.debt.type.DebtTypeService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.zip


@Service
class RecurringDebtRetrievalService(
        private val typeService: DebtTypeService,
        private val tagService: DebtTagService
) {

    fun retrieveCreation(paramsRequest: ParamsRequest): Mono<RecurringDebtRetrievalResponse> {
        val tags = tagService.retrieveAll(paramsRequest).map { it.map { t -> t.value!! } }
        val types = typeService.retrieveAll(paramsRequest).map { it.map { t -> t.value!! } }
        return zip(tags, types)
                .map { RecurringDebtRetrievalResponse(tags = it.t1, types = it.t2) }

    }
}
