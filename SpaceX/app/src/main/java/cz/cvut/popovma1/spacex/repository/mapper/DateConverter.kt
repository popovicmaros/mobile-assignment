package cz.cvut.popovma1.spacex.repository.mapper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter(
    private val oldDateFormat: String = "yyyy-MM-dd",
    private val newDateFormat: String = "d.M.yyyy"
) {

    private val sdf: SimpleDateFormat = SimpleDateFormat(oldDateFormat, Locale.US)

    fun convertDateFormat(oldDate: String): String {
        val oldDateAsDate: Date = sdf.parse(oldDate) as Date
        sdf.applyPattern(newDateFormat)
        val newDate: String = sdf.format(oldDateAsDate)
        return newDate
    }
}
