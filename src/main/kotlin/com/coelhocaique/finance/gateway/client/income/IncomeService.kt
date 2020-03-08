package com.coelhocaique.finance.gateway.client.income

import com.coelhocaique.finance.gateway.client.HttpClientService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class IncomeService {

    @Value("\${personal-finance-api.url}")
    private lateinit var baseUrl: String

    @Autowired
    private lateinit var client: HttpClientService

    fun create(request: IncomeRequest?, paramsRequest: ParamsRequest): Mono<IncomeResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/income"),
                request,
                IncomeResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<IncomeResponse> {
        return client.getRequest(
                baseUrl.plus("/v1/income/${request.id}"),
                IncomeResponse::class.java,
                request.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<IncomeResponse>> {
        return client.getListRequest(
                baseUrl.plus("/v1/income?${request.getQueryParam()}"),
                jacksonTypeRef(),
                request.headers)
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                baseUrl.plus("/v1/income/${request.id}"),
                request.headers)
    }

}
