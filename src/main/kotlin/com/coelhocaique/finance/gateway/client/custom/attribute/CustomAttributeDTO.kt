package com.coelhocaique.finance.gateway.client.custom.attribute

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class CustomAttributeRequest (
        val name: String? = null,
        val value: String? = null,
        val referenceDate: LocalDate? = null
)

data class CustomAttributeResponse (
        val parameterId: UUID? = null,
        val name: String? = null,
        val value: String? = null,
        val referenceDate: String? = null,
        val accountId: UUID? = null,
        val creationDate: LocalDateTime? = null
)

