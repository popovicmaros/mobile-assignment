package cz.cvut.popovma1.spacex.repository.mapper

import org.junit.Test

import org.junit.Assert.*

internal class DateConverterTest {

    @Test
    fun convertDateFormat() {
        val oldDate = "2006-03-24"
        val newDateExpected = "24.3.2006"
        val newDateActual = DateConverter().convertDateFormat(oldDate)
        assertEquals(newDateExpected, newDateActual)
    }
}