package com.coelhocaique.finance.gateway.routes

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
        GET("/income/{id}", handler::retrieveById)
        GET("/income", handler::retrieve)
    }

}