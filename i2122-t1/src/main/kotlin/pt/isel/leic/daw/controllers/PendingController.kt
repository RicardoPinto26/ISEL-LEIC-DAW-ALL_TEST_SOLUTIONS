package pt.isel.leic.daw.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.leic.daw.controllers.models.GetPendingOutputModel
import pt.isel.leic.daw.repositories.PendingServiceAndRepo

@RestController
class PendingController(val pendingServiceAndRepo: PendingServiceAndRepo) {

    @GetMapping("/pending")
    fun getPending(): ResponseEntity<*> {
        return ResponseEntity.status(200).body(GetPendingOutputModel(pendingServiceAndRepo.getPending()))
    }

    @GetMapping("/delay/{seconds}")
    fun getDelay(@PathVariable seconds: Int): Int {
        Thread.sleep(seconds * 1000L)
        return seconds
    }

    @PostMapping("/delay/{seconds}")
    fun postDelay(@PathVariable seconds: Int): Int {
        Thread.sleep(seconds * 1000L)
        return seconds
    }

    @PutMapping("/delay/{seconds}")
    fun putDelay(@PathVariable seconds: Int): Int {
        Thread.sleep(seconds * 1000L)
        return seconds
    }

    @DeleteMapping("/delay/{seconds}")
    fun deleteDelay(@PathVariable seconds: Int): Int {
        Thread.sleep(seconds * 1000L)
        return seconds
    }
}