package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DebtSumProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {
        return mapOf("debt_total_amount" to debts.fold(BigDecimal.ZERO) {sum, debt -> sum.add(debt.amount)})
    }
}