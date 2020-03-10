package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import com.coelhocaique.finance.gateway.helper.parseAsYearMonth
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DebtByMonthProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {

        val debtByMonth = debts.fold(mapOf<String, BigDecimal>().toMutableMap()) { acc, debt ->
            val date = parseAsYearMonth(debt.referenceDate!!)
            acc[date] = debt.amount!!.add(acc[date] ?: BigDecimal.ZERO)
            acc
        }.entries.map { mapOf("y" to it.value.toString(), "name" to it.key) }

        return mapOf("debt_by_month" to debtByMonth)
    }
}