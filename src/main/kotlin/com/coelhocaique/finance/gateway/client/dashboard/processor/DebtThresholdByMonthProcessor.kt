package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import com.coelhocaique.finance.gateway.helper.parseAsYearMonth
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DebtThresholdByMonthProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {

        val thresholdByMonth = parameters.fold(mapOf<String, BigDecimal>().toMutableMap()) { acc, parameter ->
            val date = parseAsYearMonth(parameter.referenceDate!!)
            acc[date] = BigDecimal(parameter.value).add(acc[date] ?: BigDecimal.ZERO)
            acc
        }.entries.map { mapOf("y" to it.value.toString(), "name" to it.key) }

        return mapOf("debt_threshold_by_month" to thresholdByMonth)
    }
}