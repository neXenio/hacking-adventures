package com.nexenio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@SpringBootApplication
class DemoApplication
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(value = [(IllegalArgumentException::class)])
    fun handleIllegalArgumentException(exception: IllegalArgumentException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

}
