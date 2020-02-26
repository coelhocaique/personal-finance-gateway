package com.coelhocaique.finance.gateway.helper


data class FetchRequest(
        val id: String? = null,
        val headers: Map<String, Any> = mapOf(),
        val queryParams: Map<String, Any> = mapOf()
){
    fun getQueryParam(): String {
        return queryParams.map { it.key.plus("=").plus(it.value) }.joinToString("&")
    }
}
