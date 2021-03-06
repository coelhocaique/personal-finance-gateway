package com.coelhocaique.finance.gateway.routes

import com.coelhocaique.finance.gateway.client.dashboard.DashboardHandler
import com.coelhocaique.finance.gateway.client.debt.DebtHandler
import com.coelhocaique.finance.gateway.client.debt.recurring.RecurringDebtHandler
import com.coelhocaique.finance.gateway.client.debt.tag.DebtTagHandler
import com.coelhocaique.finance.gateway.client.debt.threshold.DebtThresholdHandler
import com.coelhocaique.finance.gateway.client.debt.type.DebtTypeHandler
import com.coelhocaique.finance.gateway.client.income.IncomeHandler
import com.coelhocaique.finance.gateway.client.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class ApiRouting {

    @Bean
    fun userRoutes(handler: UserHandler) = router {
        POST("/register", handler::create)
        POST("/login", handler::authenticate)
    }

    @Bean
    fun incomeRoutes(handler: IncomeHandler) = router {
        POST("/income", handler::create)
        GET("/income", handler::retrieveByParam)
        GET("/income/{id}", handler::retrieveById)
        DELETE("/income/{id}", handler::deleteById)
    }

    @Bean
    fun debtRoutes(handler: DebtHandler) = router {
        POST("/debt", handler::create)
        GET("/debt", handler::retrieveByParam)
        GET("/debt/{id}", handler::retrieveById)
        GET("/debt-new", handler::retrieveCreation)
        DELETE("/debt/{id}", handler::deleteById)
        DELETE("/debt", handler::deleteByParam)
    }

    @Bean
    fun debtTypeRoutes(handler: DebtTypeHandler) = router {
        POST("/debt-type", handler::create)
        GET("/debt-type", handler::retrieveAll)
        DELETE("/debt-type/{id}", handler::deleteById)
    }

    @Bean
    fun debtTagRoutes(handler: DebtTagHandler) = router {
        POST("/debt-tag", handler::create)
        GET("/debt-tag", handler::retrieveAll)
        DELETE("/debt-tag/{id}", handler::deleteById)
    }

    @Bean
    fun debtThresholdRoutes(handler: DebtThresholdHandler) = router {
        POST("/debt-threshold", handler::create)
        GET("/debt-threshold", handler::retrieveAll)
        DELETE("/debt-threshold/{id}", handler::deleteById)
    }

    @Bean
    fun dashboardRoutes(handler: DashboardHandler) = router {
        GET("/dashboard", handler::retrieve)
    }

    @Bean
    fun recurringDebtRoutes(handler: RecurringDebtHandler) = router {
        POST("/recurring-debt", handler::create)
        GET("/recurring-debt", handler::retrieveAll)
        GET("/recurring-debt-new", handler::retrieveCreation)
        GET("/recurring-debt/{id}", handler::retrieveById)
        DELETE("/recurring-debt/{id}", handler::deleteById)
    }
}