package com.coelhocaique.finance.gateway.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun <T : Any> T.logger(): Logger = LoggerFactory.getLogger(javaClass)


fun subtractFromReferenceDate(date: String, months: Long): LocalDate {
    return LocalDate.parse(date.plus("01"),
            DateTimeFormatter.ofPattern("YYYYMMDD"))
            .minusMonths(months)
}

fun extractReferenceDate(parsedDate: LocalDate) = parsedDate.toString().substring(0, 6)


