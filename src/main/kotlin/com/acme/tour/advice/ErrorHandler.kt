package com.acme.tour.advice

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
    return ResponseEntity(ErrorMessage("Json ERROR Mensagem feita por Phelipe", exception.message ?: "Invalid Json"), HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
        exception: Exception):ResponseEntity<ErrorMessage> {
            return ResponseEntity(ErrorMessage("Promocao Nao Localizada", exception.message !!), HttpStatus.NOT_FOUND)
        }
    }
