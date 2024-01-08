package pt.isel.leic.daw.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pt.isel.leic.daw.controllers.models.GetHandlerDetailsOutputModel
import pt.isel.leic.daw.controllers.models.GetHandlersOutputModel
import pt.isel.leic.daw.repositories.HandlersRepository

@RestController
class HandlersController(val repository: HandlersRepository) {
    @GetMapping("handlers")
    fun getHandlers() =
        GetHandlersOutputModel(repository.getHandlers())

    @GetMapping("handlers/{handler-id}")
    fun getHandlerDetails(
        @PathVariable("handler-id") handlerID: String
    ) =
        GetHandlerDetailsOutputModel(repository.getHandlerByID(handlerID))
}