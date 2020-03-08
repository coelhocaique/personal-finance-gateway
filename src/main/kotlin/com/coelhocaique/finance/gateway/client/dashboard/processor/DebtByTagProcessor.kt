package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DebtByTagProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {

        val debtByTag = debts.fold(mapOf<String, BigDecimal>().toMutableMap()) { acc, debt ->
            acc[debt.tag!!] = debt.amount!!.add(acc[debt.tag] ?: BigDecimal.ZERO)
            acc
        }.entries.map { mapOf("y" to it.value.toString(), "name" to it.key) }

        return mapOf("debt_by_tag" to debtByTag)
    }
}