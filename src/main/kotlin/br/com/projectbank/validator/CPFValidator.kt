package br.com.projectbank.validator

import CPF
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class CPFValidator : ConstraintValidator<CPF, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return false
        val cleanCPF = value.filter { it.isDigit() }

        if (cleanCPF.length != 11 || cleanCPF.all { it == cleanCPF[0] }) return false

        val firstNineDigits = cleanCPF.substring(0, 9).map { it.toString().toInt() }
        val firstVerifierDigit = calculateVerifierDigit(firstNineDigits)
        val secondVerifierDigit = calculateVerifierDigit(firstNineDigits + firstVerifierDigit)

        return cleanCPF == firstNineDigits.joinToString("") + firstVerifierDigit + secondVerifierDigit
    }

    private fun calculateVerifierDigit(digits: List<Int>): Int {
        val weights = digits.size + 1 downTo 2
        val sum = digits.zip(weights).sumOf { it.first * it.second }
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}
