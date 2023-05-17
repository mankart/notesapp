package io.github.reskimulud.mynotesapp.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Helper {
    fun String.convertDateTimeFormatToCustom(): String {
        val startFormatter: DateTimeFormatter =
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateObject: DateTime = startFormatter.parseDateTime(this).withZone(DateTimeZone.forID("Asia/Jakarta"))

        val resultFormatter: DateTimeFormatter = DateTimeFormat.forPattern("dd MMMM yyyy HH:mm")

        return resultFormatter.print(dateObject)
    }
}