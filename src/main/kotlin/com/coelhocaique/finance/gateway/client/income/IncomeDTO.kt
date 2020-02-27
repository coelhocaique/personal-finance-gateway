package com.coelhocaique.finance.gateway.client.income

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class IncomeRequest (
        val grossAmount: BigDecimal? = null,
        val description: String? = null,
        val receiptDate: LocalDate? = null,
        val referenceDate: String? = null,
        val sourceName: String? = null,
        val accountId: String? = null,
        val discounts: List<NestedObject> = emptyList(),
        val additions: List<NestedObject> = emptyList()
)

data class IncomeResponse (
        val incomeId: UUID? = null,
        val grossAmount: BigDecimal? = null,
        val netAmount: BigDecimal? = null,
        val additionalAmount: BigDecimal? = null,
        val discountAmount: BigDecimal? = null,
        val description: String? = null,
        val receiptDate: LocalDate? = null,
        val referenceDate: String? = null,
        val sourceName: String? = null,
        val accountId: UUID? = null,
        val discounts: List<NestedObject>? = emptyList(),
        val additions: List<NestedObject>? = emptyList(),
        val creationDate: LocalDateTime? = null
)

data class NestedObject (
        val amount: BigDecimal? = null,
        val description: String? = null
)

