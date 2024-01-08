package pt.isel.leic.daw.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pt.isel.leic.daw.repositories.MyRepository

@RestController
class MyController(val repository: MyRepository) {
    @GetMapping("/ping")
    fun ping() = "pong"

    @GetMapping("/delay/{seconds}")
    fun delayedPing(@PathVariable seconds: Int) =
        repository.delay(seconds)
}