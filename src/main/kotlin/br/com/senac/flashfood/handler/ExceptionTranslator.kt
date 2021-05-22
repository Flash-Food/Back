package br.com.senac.flashfood.handler

import br.com.senac.flashfood.model.dto.user.ApiResponseDTO
import javassist.NotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.naming.AuthenticationException

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class ExceptionTranslator : ResponseEntityExceptionHandler() {

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun processRuntimeException(e: RuntimeException?) =
            ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                   e?.message.toString())

    @ExceptionHandler(UsernameNotFoundException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun processAuthenticationException(e: UsernameNotFoundException?) =
            ApiResponseDTO(HttpStatus.FORBIDDEN.value(),
                    "Username invalid.")

    @ExceptionHandler(AuthenticationException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun processAccessDeniedException(e: AuthenticationException?) =
            ApiResponseDTO(HttpStatus.FORBIDDEN.value(),
                    "Authentication invalid.")

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun processNotFoundException(e: NotFoundException) =
            ApiResponseDTO(HttpStatus.NOT_FOUND.value(),
            e.message.toString())
}