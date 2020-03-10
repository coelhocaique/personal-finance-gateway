package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class IncomeDiscountByNameProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {

        val incomeDiscountByName = incomes.map { it.discounts }
                .map { it.fold(mapOf<String, BigDecimal>().toMutableMap()){ acc, discount ->
                    acc[discount.description!!] = discount.amount!!.add(acc[discount.description] ?: BigDecimal.ZERO)
                    acc
                    }
                }
                .fold(mapOf<String, BigDecimal>()){ acc, cur -> acc + cur}
                .entries.map { mapOf("y" to it.value.toString(), "name" to it.key) }

        return mapOf("income_discount_by_name" to incomeDiscountByName)
    }
}