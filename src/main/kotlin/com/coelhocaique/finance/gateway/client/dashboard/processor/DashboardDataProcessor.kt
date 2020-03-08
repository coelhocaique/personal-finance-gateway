package com.coelhocaique.finance.gateway.client.dashboard.processor

import com.coelhocaique.finance.gateway.client.debt.DebtResponse
import com.coelhocaique.finance.gateway.client.income.IncomeResponse
import com.coelhocaique.finance.gateway.client.parameter.ParameterResponse
import org.springframework.stereotype.Component

@Component
interface DashboardDataProcessor {

    fun process(parameters: List<ParameterResponse>,
        incomes: List<IncomeResponse>, debts: List<DebtResponse>): Map<String, Any>
}