package com.majernik.stringcalculator.utils

import org.junit.Test
import org.junit.Assert.*
import org.hamcrest.Matchers.*

class SumCalculatorTest {

    @Test
    fun getDelimiter_withEmptyString_returnsDefault() {
        assertThat(SumCalculator.getDelimiter(""), `is`(","))
    }

    @Test
    fun getDelimiter_withNewLine_returnsDefault() {
        assertThat(SumCalculator.getDelimiter("1,\n2,3"), `is`(","))
        assertThat(SumCalculator.getDelimiter("\n1,\n2,3"), `is`(","))
    }

    @Test
    fun getDelimiter_withPipeString_returnsPipe() {
        assertThat(SumCalculator.getDelimiter("//|\n1|2|3"), `is`("|"))
    }

    @Test
    fun add_withEmptyString_returnsZero() {
        assertThat(SumCalculator.add(""), `is`(0))
        assertThat(SumCalculator.add("\n"), `is`(0))
    }

    @Test
    fun add_withDefaultDelimiter_returnsCorrectSum() {
        assertThat(SumCalculator.add("1,2,3"), `is`(6))
        assertThat(SumCalculator.add("1,\n2,3"), `is`(6))
        assertThat(SumCalculator.add("1,\n2,4"), `is`(7))
        assertThat(SumCalculator.add("1,\n2\n,\n\n4"), `is`(7))
        assertThat(SumCalculator.add("\n\n1,\n2\n,\n\n4"), `is`(7))
    }

    @Test
    fun add_withDelimiter_returnsCorrectSum() {
        assertThat(SumCalculator.add("//$\n1$2$3"), `is`(6))
        assertThat(SumCalculator.add("//@\n2@3@8"), `is`(13))
    }

    @Test
    fun add_withMultipleDelimiter_returnsCorrectSum() {
        assertThat(SumCalculator.add("//$,@\n1$2@3"), `is`(6))
        assertThat(SumCalculator.add("//$,***\n1$2***3"), `is`(6))
    }

    @Test
    fun add_with1001Number_skipsNumbers() {
        assertThat(SumCalculator.add("1001,2,3"), `is`(5))
    }

    @Test
    fun add_withNegativeNumber_throwsException() {
        var hasException = false
        try {
            SumCalculator.add("1,-1")
        } catch (e: Exception) {
            hasException = true
        }
        assertTrue(hasException)
    }
}