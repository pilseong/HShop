package net.philipheur.hshop.userservice.controller.exporter

import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsvBuilder
import jakarta.servlet.http.HttpServletResponse
import net.philipheur.hshop.userservice.domain.service.dto.UserDto
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.Date

@Component
class CSVExporter {

    fun export(users: List<UserDto>, response: HttpServletResponse) {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
        val timestamp = dateFormatter.format(Date())
        val fileName = "users_$timestamp.csv"

        response.contentType = "text/csv"

        val headerKey = "Content-Disposition"
        val headerValue = "attachment; filename=$fileName"

        response.setHeader(headerKey, headerValue)

        val statefulBeanToCsvBuilder = StatefulBeanToCsvBuilder<UserDto>(response.writer)
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .build()

        statefulBeanToCsvBuilder.write(users)
    }
}