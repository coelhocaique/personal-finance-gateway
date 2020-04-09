package com.coelhocaique.finance.gateway.client.custom.attribute

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class CustomAttributeRequest (
        val propertyName: String? = null,
        val value: String? = null,
        val referenceDate: LocalDate? = null
)

data class CustomAttributeResponse (
        val customAttributeId: UUID? = null,
        val value: String? = null,
        val creationDate: LocalDateTime? = null
)

