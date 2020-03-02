package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import java.math.BigDecimal

class IncomeSumProcessor: DashboardDataProcessor {

    override fun process(parameters: List<ParameterResponse>,
                         incomes: List<IncomeResponse>,
                         debts: List<DebtResponse>): Map<String, Any> {
        return mapOf(
                "totalIncomeGrossAmount" to incomes.fold(BigDecimal.ZERO) {sum, income -> sum.add(income.grossAmount)},
                "totalIncomeNetAmount" to incomes.fold(BigDecimal.ZERO) {sum, income -> sum.add(income.netAmount)}
        )
    }
}