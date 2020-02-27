package com.coelhocaique.finance.gateway.client.debt.tag

import com.coelhocaique.finance.gateway.client.custom.attribute.CustomAttributeRequest
import com.coelhocaique.finance.gateway.client.custom.attribute.CustomAttributeResponse
import com.coelhocaique.finance.gateway.client.custom.attribute.CustomAttributeService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.coelhocaique.finance.gateway.helper.Fields.DEBT_TAG
import com.coelhocaique.finance.gateway.helper.Fields.PROPERTY_NAME
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class DebtTagService {

    @Autowired
    private lateinit var customAttributeService: CustomAttributeService

    fun create(request: CustomAttributeRequest?, paramsRequest: ParamsRequest): Mono<CustomAttributeResponse> {
        return customAttributeService.create(request?.let { it.copy(name = DEBT_TAG) }, paramsRequest)
    }

    fun retrieveAll(request: ParamsRequest): Mono<List<CustomAttributeResponse>> {
        return customAttributeService.retrieveByParam(
                request.copy(queryParams = mapOf(PROPERTY_NAME to DEBT_TAG)))
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return customAttributeService.deleteById(request)
    }

}
