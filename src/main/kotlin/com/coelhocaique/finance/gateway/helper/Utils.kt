package com.coelhocaique.finance.gateway.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun <T : Any> T.logger(): Logger = LoggerFactory.getLogger(javaClass)

fun parseReferenceDate(date: String) =
        LocalDate.parse(date.plus("01").trim(), DateTimeFormatter.ofPattern("yyyyMMdd"))

fun parseAsYearMonth(date: String): String {
    val parsedDate = parseReferenceDate(date)
    return "${parsedDate.year}/${parsedDate.month.name.toLowerCase()}"
}

fun subtractFromReferenceDate(date: String, months: Long): LocalDate {
    return parseReferenceDate(date).minusMonths(months)
}

fun extractReferenceDate(parsedDate: LocalDate) = parsedDate.toString().replace("-", "").substring(0, 6)


