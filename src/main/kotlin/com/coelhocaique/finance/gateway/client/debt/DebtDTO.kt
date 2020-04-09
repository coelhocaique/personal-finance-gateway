package com.coelhocaique.finance.gateway.client.debt

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class DebtRequest (
        val amount: BigDecimal? = null,
        val description: String? = null,
        val debtDate: LocalDate? = null,
        val installments: Int? = null,
        val type: String? = null,
        val tag: String? = null,
        val nextMonth: Boolean? = null
)

data class DebtResponse (
        val debtId: UUID? = null,
        val amount: BigDecimal? = null,
        val description: String? = null,
        val debtDate: LocalDate? = null,
        val installments: Int? = null,
        val type: String? = null,
        val tag: String? = null,
        val referenceCode: UUID? = null,
        val installmentNumber: Int? = null,
        val referenceDate: String? = null,
        val nextMonth: Boolean? = null,
        val totalAmount: BigDecimal? = null,
        val accountId: UUID? = null,
        val creationDate: LocalDateTime? = null
)

data class DebtRetrievalResponse (
        val debts: List<DebtResponse>? = null,
        val types: List<String>? = null,
        val tags: List<String>? = null,
        val threshold: BigDecimal? = null
)

