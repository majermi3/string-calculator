package com.majernik.stringcalculator.utils

import java.lang.Exception

object SumCalculator {

    fun add(inputString: String?): Int {
        if (inputString.isNullOrEmpty()) {
            return 0
        }

        val numbers = getNumbers(inputString)

        val invalidNumbers = validateNumbers(numbers)
        if (invalidNumbers.isNotEmpty()) {
            throw Exception("Negative numbers ${invalidNumbers.joinToString(", ")} are not allowed!")
        }

        return numbers.sum()
    }

    fun getNumbers(inputString: String): List<Int> {
        val delimiter = getDelimiter(inputString)
        val numberParts = splitNumbers(inputString, delimiter)

        return numberParts.map {
            val number = it.toIntOrNull()

            if (number != null && number <= 1000) {
                number
            } else {
                0
            }
        }
    }

    fun splitNumbers(inputString: String, delimiter: String): List<String> {
        return if (delimiter != ",") {
            inputString.replace("\n", "").split(Regex("[${delimiter.split(",")}]"))
        } else {
            inputString.replace("\n", "").split(delimiter)
        }
    }

    fun validateNumbers(numbers: List<Int>): List<Int>  {
        val list = mutableListOf<Int>()
        numbers.forEach {
            if (it < 0) {
                list += it
            }
        }

        return list
    }

    fun getDelimiter(inputString: String): String {
        if (Regex("^[0-9\n]+").containsMatchIn(inputString)) {
            return ","
        }
        if (Regex("^(//)+").containsMatchIn(inputString)) {
            return inputString.substringAfter("//").substringBefore("\n", ",")
        }

        return ","
    }
}