package br.com.projectbank.utils

import java.time.LocalDate
import java.time.Period
import java.util.*

object AgeUtils {
    fun calculateDate(dataNascimento: LocalDate): Int {
        return Period.between(dataNascimento, LocalDate.now()).years
    }
}