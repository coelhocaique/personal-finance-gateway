package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import com.coelhocaique.finance.gateway.helper.parseAsYearMonth
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class IncomeBySourceNameProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {

        val incomeNetBySourceName = incomes.fold(mapOf<String, BigDecimal>().toMutableMap()) { acc, income ->
            acc[income.sourceName!!] = income.netAmount!!.add(acc[income.sourceName] ?: BigDecimal.ZERO)
            acc
        }.entries.map { mapOf("y" to it.value.toString(), "name" to it.key) }

        return mapOf("income_net_by_source_name" to incomeNetBySourceName)
    }
}