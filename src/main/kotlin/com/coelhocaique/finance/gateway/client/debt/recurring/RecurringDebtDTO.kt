package com.coelhocaique.finance.gateway.client.debt.recurring

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class RecurringDebtRequest (
        val amount: BigDecimal? = null,
        val description: String? = null,
        val type: String? = null,
        val tag: String? = null
)

data class RecurringDebtResponse (
        val recurringDebtId: UUID? = null,
        val amount: BigDecimal? = null,
        val description: String? = null,
        val type: String? = null,
        val tag: String? = null,
        val referenceCode: UUID? = null,
        val creationDate: LocalDateTime? = null
)

