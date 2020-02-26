package com.coelhocaique.finance.gateway.client.income

import com.coelhocaique.finance.gateway.client.HttpClientService
import com.coelhocaique.finance.gateway.helper.FetchRequest
import com.coelhocaique.finance.gateway.helper.logger
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

    fun create(request: IncomeRequest?): Mono<IncomeResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/income"),
                request,
                IncomeResponse::class.java)
    }

    fun retrieveById(request: FetchRequest): Mono<IncomeResponse> {
        return client.getRequest(
                baseUrl.plus("/v1/income/").plus(request.id),
                IncomeResponse::class.java,
                request.headers)
    }

    fun retrieve(request: FetchRequest): Mono<List<IncomeResponse>> {
        return client.getRequest(
                baseUrl.plus("/v1/income?").plus(request.getQueryParam()),
                List::class.java,
                request.headers) as Mono<List<IncomeResponse>>
    }

}
