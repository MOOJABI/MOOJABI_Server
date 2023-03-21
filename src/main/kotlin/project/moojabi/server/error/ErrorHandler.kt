package project.moojabi.server.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.nio.charset.StandardCharsets

@RestControllerAdvice
class ErrorHandler(
    private val objectMapper: ObjectMapper
) {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse(
            status = e.status,
            message = e.message
        )

        response.status = e.status
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}

data class ErrorResponse(
    val status: Int,
    val message: String
)
