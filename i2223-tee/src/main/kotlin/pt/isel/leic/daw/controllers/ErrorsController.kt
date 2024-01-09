package pt.isel.leic.daw.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.leic.daw.controllers.models.GetErrorsOutputModel
import pt.isel.leic.daw.repositories.ErrorsRepository

@RestController
class ErrorsController(val errorsRepository: ErrorsRepository) {

    @GetMapping("/errors")
    fun getErrors() =
        GetErrorsOutputModel(errorsRepository.getErrors())

    @GetMapping("/500")
    fun get500() =
        ResponseEntity.status(500).body("Internal Server Error")
}