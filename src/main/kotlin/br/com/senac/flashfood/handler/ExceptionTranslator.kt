package br.com.senac.flashfood.handler

import br.com.senac.flashfood.model.dto.user.ApiResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.naming.AuthenticationException

@RestControllerAdvice
class ExceptionTranslator : ResponseEntityExceptionHandler() {

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun processRuntimeException(e: RuntimeException?) =
            ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An internal server error occurred.")

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
}