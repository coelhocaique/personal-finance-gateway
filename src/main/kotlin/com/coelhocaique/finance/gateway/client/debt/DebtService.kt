package com.coelhocaique.finance.gateway.client.debt

import com.coelhocaique.finance.gateway.client.HttpClientService
import com.coelhocaique.finance.gateway.helper.ParamsRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class DebtService {

    @Value("\${personal-finance-api.url}")
    private lateinit var baseUrl: String

    @Autowired
    private lateinit var client: HttpClientService

    fun create(request: DebtRequest?, paramsRequest: ParamsRequest): Mono<DebtResponse> {
        return client.postRequest(
                baseUrl.plus("/v1/debt"),
                request,
                DebtResponse::class.java,
                paramsRequest.headers)
    }

    fun retrieveById(request: ParamsRequest): Mono<DebtResponse> {
        return client.getRequest(
                baseUrl.plus("/v1/debt/${request.id}"),
                DebtResponse::class.java,
                request.headers)
    }

    fun retrieveByParam(request: ParamsRequest): Mono<List<DebtResponse>> {
        return client.getRequest(
                baseUrl.plus("/v1/debt?${request.getQueryParam()}"),
                List::class.java,
                request.headers) as Mono<List<DebtResponse>>
    }

    fun deleteById(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                baseUrl.plus("/v1/debt/${request.id}"),
                request.headers)
    }

    fun deleteByParam(request: ParamsRequest): Mono<Void> {
        return client.deleteRequest(
                baseUrl.plus("/v1/debt/${request.getQueryParam()}"),
                request.headers)
    }

}
